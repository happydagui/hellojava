教程目录

- 配置 MyBatis 并完成单元测试
- 配置 Spring 和 MyBatis 一起工作
- 如何处理数据库分表


## How To

在 Idea 中打开目录，打开右侧 Maven Projects 面板，刷新一下，就可以恢复工程状态了。（默认情况下左侧Project显示不正常）

## 准备

add `log_bin_trust_function_creators=1`  to the end of (/etc/mysql/mysql.conf.d/mysqld.cnf)

> `sudo service mysql restart`
> `mysql \> show variables like "%function%"`




 [MyBatis]诡异的Invalid bound statement (not found)错误 
在仔细检查了之前的项目后，发现Mapper.xml根本就没打包进去。妈蛋，竟然犯了一个弱智错误！



### Cause: java.sql.SQLSyntaxErrorException: FUNCTION helloworld.nextvar does not exist

ERROR 1305 (42000): FUNCTION helloworld.nextvar does not exist

> nextvar 写错了，总是提示找不到hellworld.nextvar，呵呵！！！！！！


控制台可以测试 MySQL 函数调用

```
set global log_bin_trust_function_creators=1;
select nextval("order_sequence_1");
```
