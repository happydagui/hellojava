package suggestions.reflect;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by min on 17-3-15.
 * Notes: 动态代理可以使代理模式更灵活
 */
public class Suggestion106 {
    public static void main(String[] args) {
        // 传统的调用方式
        Subject subject = new SubjectImpl();
        subject.request();

        // 如何使用代理来增强功能
        // 1.　静态代理，一个接口一个代理类
        Subject proxy1 = new StaticProxy(new SubjectImpl());
        proxy1.request();

        // 2. 动态代理，代理任何实现了接口的类
        Subject proxy2 = (Subject) new MyAdvice().bind(new SubjectImpl());
        proxy2.request();

        // 3. 动态cglib代理，可以代理有接口的类
        Subject proxy3 = (Subject) new MyAdviceWithCglib().getInstance(new SubjectImpl());
        proxy3.request();

        // 4. 动态cglib代理，可以代理无接口的类
        SubjectImplWithoutInterface proxy4 = (SubjectImplWithoutInterface) new MyAdviceWithCglib()
                .getInstance(new SubjectImplWithoutInterface());
        proxy4.request();

    }
}
interface Subject {
    void request();
}

class SubjectImpl implements Subject {
    @Override
    public void request() {
        System.out.println("it is the implement.");
    }
}

class SubjectImplWithoutInterface{
    public void request() {
        System.out.println("it is the implement.");
    }
}


class StaticProxy implements Subject {
    private Subject subject;

    public StaticProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        System.out.println("[static]before");
        subject.request();
        System.out.println("[static]end");
    }
}
// ---------------------------------------------------------

class MyAdvice implements InvocationHandler {
    private Object target; // any object with interface

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[dynamic]before");
        System.out.println(proxy.getClass().getName()); // suggestions.reflect.$Proxy0
        // Notice: invoke 第一个参数不是 proxy，是被代理的对象实例
        Object ret = method.invoke(target, args);
        System.out.println("[dynamic]after");
        return ret;
    }
}

class MyAdviceWithCglib implements MethodInterceptor {
    private Object target;
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("[cglib]before");
        Object ret = methodProxy.invokeSuper(o, args);
        System.out.println("[cglib]after");

        return ret;
    }
}