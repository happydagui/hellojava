## Quickstart



## 如何处理数据库分表


 [MyBatis]诡异的Invalid bound statement (not found)错误 
在仔细检查了之前的项目后，发现Mapper.xml根本就没打包进去。妈蛋，竟然犯了一个弱智错误！



### Cause: java.sql.SQLSyntaxErrorException: FUNCTION helloworld.nextvar does not exist

ERROR 1305 (42000): FUNCTION helloworld.nextvar does not exist

> nextvar 写错了，总是提示找不到hellworld.nextvar，呵呵！！！！！！


控制台可以测试

```
set global log_bin_trust_function_creators=1;
select nextval("order_sequence_1");
```

add `log_bin_trust_function_creators=1`  to the end of (/etc/mysql/mysql.conf.d/mysqld.cnf)
