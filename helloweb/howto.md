# 使用 IntelliJ IDEA 快速构建 Spring MVC 工程
## 快速创建 Web Application

- 创建 Maven 工程 (Create a Maven project without any artifact)
- 添加 Web 应用支持 (Add Framework Support/Web Application)
- 在输出选项中把依赖库部署到 lib 目录
Project Settings/Artifact/Output Layout/
Create folder */WEB-INF/lib* and add MAVEN libraries to this folder.

> 每次如果变换了 MAVEN 依赖的话，这个地方需要更新，否则不会自动部署依赖的 jar

- 添加应用服务器servlet库

**Project Settings/Module/helloweb/Dependencies** + Library... Select "Application Server Library"

- 配置应用服务器
Run/Edit Configuration ...

## 让基本的 Web Application 添加 Spring 支持

- pom.xml: 添加依赖库
- /WEB-INF/web.xml， 添加 servlet 声明，
- /WEB-INF/<servlet-name>-servlet.xml: servlet 配置 
- classpath:applicationContext.xml: spring 配置
- classpath:lj.controllers.HelloController: 控制器
- /WEB-INF/jsp/hello.jsp: 模板


> 注： servlet 默认配置文件就是 /WEB-INF/<servlet-name>-servlet.xml, 可以不需要 init-param 配置，如果放在 classpath 
中的话，需要在 servlet 的 init-param 中声明 contextConfigLocation 参数
```
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/<servlet-name>-servlet.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
```

> 建议分离 servlet 配置文件和 spring bean 配置，主要是为了测试分离web层，把web层以外的spring配置分离，可以单独测试。

## 如何测试

```
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>4.3.4.RELEASE</version>
    <scope>test</scope>
</dependency>
```

> junit 4.12+ is required for spring-test v4



## 注意的几个坑

- pom.xml 不需要添加 servlet 库，tomcat等应用服务器都有了。tomcat 7+ 已经使用 3.0+ 的 servlet, 而 maven 依赖没有3.0+的
```
<!--<dependency>-->
    <!--<groupId>javax.servlet</groupId>-->
    <!--<artifactId>servlet-api</artifactId>-->
    <!--<version>2.5</version>-->
    <!--<scope>provided</scope>-->
<!--</dependency>-->

<!--<dependency>-->
    <!--<groupId>javax.servlet.jsp</groupId>-->
    <!--<artifactId>jsp-api</artifactId>-->
    <!--<version>2.2</version>-->
    <!--<scope>provided</scope>-->
<!--</dependency>-->
```
> idea 或者其他 IDE 可以直接引用应用服务器的 servlet 库作为编译的依赖

- web.xml 中使用 context-params 加载 spring 配置要和 listener 结合使用，否则不会加载
```
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
</context-param>
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

- 使用 Hibernate 连接 MySQL 一定要使用 MySQL5InnoDBDialect ，而不是 MySQLInnoDBDialect
```<prop key="hibernate.dialect">org.hibernate.dialect.MySQL5InnoDBDialect</prop>```

不然查询的时候总是提示找不到表 _<用户名>.<表名>_

- 使用 Apache Tomcat 7.0+ 
不要选 Apache Tomcat 6.0，Tomcat 7 以后才支持 Servlet 3.0



- web.xml配置中的url-pattern的'/'和'/*'的区别吗？
  哎，基本功不扎实，这个问题折腾了好久。'/'表示该项目的所有请求路径；'/*'表示只能有一级子路径，它匹配'/a.jsp'但是不匹配'/jsp/a.jsp'。

## 参考
- [Maven Repository: Search/Browse/Explore](http://mvnrepository.com/)
- (http://www.mamicode.com/info-detail-467301.html)
- druid：一款高效的数据库连接池
- Hibernate4的改动较大只有spring3.1以上版本能够支持，Spring3.1取消了HibernateTemplate，因为Hibernate4的事务管理已经很好了，不用Spring再扩展了
