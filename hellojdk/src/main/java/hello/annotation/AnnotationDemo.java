package hello.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * annotation 的目的在类上面添加一些元信息，在处理的时候，可以根据这些标注来做附加的一些功能
 * 1. 注解对代码的语意没有直接影响, 他们只负责提供信息给相关的程序使用. 注解永远不会改变被注解代码的含义,
 *      但可以通过工具对被注解的代码进行特殊处理.
 */
public class AnnotationDemo {
	public static void main(String[] args) {
		Class cls = BusinessLogic.class;
		for (Method method : cls.getMethods()) {
			// 获取在方法上声明的标注
			Todo anno = (Todo) method.getAnnotation(Todo.class);
			if (anno != null) {
				System.out.println("method name: " + method.getName());
				System.out.println("anno author: " + anno.author());
				System.out.println("anno priority: " + anno.priority());
				System.out.println("anno status: " + anno.status());
			}
		}
	} 
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Todo {
	enum Priority {LOW, MEDIUM, HIGH}
	enum Status {STARTED, NOT_STARTED}

	String author() default "Yash";
	Priority priority() default Priority.LOW;
	Status status() default Status.NOT_STARTED;
}

class BusinessLogic {
	@Todo(author = "min", priority = Todo.Priority.HIGH)
	public void amethod() {
		System.out.println("hello, it is a method.");
	}
}