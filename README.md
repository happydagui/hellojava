# Hello Java - A tutorials (notes) for Java and relatives

use `git clone https://happydagui.github.com` to clone the repository.

** Prerequisites **

- Develpment enviroment is as below:
- Ubuntu 16.04
- jdk 1.7+
- Apache Maven 3.0+
- Apache Tomcat 7+
- Intel Idea 2016, and Sublime Text 3

** Tips

All projects are created with Idea, start from *create a maven project* and no archetype support for simplicity. Add Web Application Framework for *helloweb*.

Maven is used with all projects for dependencies.


## Practical JDK

** How to **

use `git checkout hellojdk` to 

** Manefiest **

- Java IO Operations - Decoration pattern


## Build a spring mvc application

run `git checkout helloweb` to switch to the branch. 

All Topic:

- Spring mvc quickstart
- with jstl
- with freemarker
- with velocity


* Notes *

A basic springmvc's key element is as below: 

- web.xml, to launch servlet and load applicationContext with listener
- <dispatch-name>-sevlet.xml, spring mvc configuration segments (viewResolvers)
- applicationContext.xml, all spring relative declaration is here (context,datasource, bean and etc), you can split it to multiple files and load them in web.xml.
- a controller
- a view template

* jstl as view resolver *

Declare *viewClass* in servlet config file, add jstl api and implimentation lib to maven, add taglib header to your template page.

* Speicial notes *

use project's artifact tab panel to auto-deploy maven dependencies to /WEB-INF/lib folder.

> redo it whenever pom.xml is changed (for dependency changed).


## Integration with MyBatis for database operations
see [MyBatis](hellomybatis/)
