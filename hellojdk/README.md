# JDK
主题列表

- 数据结构
- [模式](#使用 slf4j)
- annotation
- 线程
- slf4j
- java class 文件手动解析

- btrace
- Java VisualVM (jvisualvm) or Visual VM(visualvm)
前者是jdk自带的，后者是官网提供的，功能基本相同。建议用后者
VisualVM comes in two distributions: VisualVM at GitHub and Java VisualVM as a JDK tool. VisualVM at GitHub is a bleeding-edge distribution with the latest features and bug fixes. To get a stable tool, use the Java VisualVM available in your Oracle JDK. 
[http://visualvm.java.net/gettingstarted.html] and [https://plugins.jetbrains.com/idea/plugin/7115-visualvm-launcher]

- MAT (Eclipse) 


## 使用 slf4j

```
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.22</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>1.7.22</version>
</dependency>
```

使用 slf4j 的好处是使用占位符，不用拼接字符串，例如
```logger.info("Calling ({})", "testHashMap");```

输出异常使用下面的语句

```logger.error("a short description", e);```


## btrace
[https://github.com/xxzmxx/btrace_extend/]

```
git clone --depth 1 https://github.com/xxzmxx/btrace_extend/
cd btrace_extend/bin
sudo chmod +x btrace
./btrace <pid>

```
Query <pid> via `jps`, `./btrace <pid>` 进入交互界面，
`btrace>help` 查看可用的命令




- java class 文件手动解析
@see hello.hotspot.ASimpleClassDecompiler
@see hello.hotspot.ASimpleClass


## MAT
**How to**
Help/Install Software
Work with: http://mirrors.ustc.edu.cn/eclipse/releases/neon/201609281000
Performance, Profiling and Tracing Tools/Memory Analyzer

安装完成后提示重启Eclipse，重启后打开window - > open perspective，看到Memory Analysis证明安装成功。
> (https://lug.ustc.edu.cn/wiki/mirrors/help/eclipse)
