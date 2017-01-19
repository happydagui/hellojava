CREATE TABLE `hellomybatis_user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL ,
  `password` varchar(255) DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `hellomybatis_user` VALUES (1, "xiaomin", 28, "password");
INSERT INTO `hellomybatis_user` VALUES (2, "wangyaping", 22, "password");




-- 创建序列，下面的例子在console中运行

DROP TABLE IF EXISTS hellomybatis_order_sequence;

CREATE TABLE hellomybatis_sequence (
   name       VARCHAR(50) NOT NULL,
     current_value   BIGINT UNSIGNED NOT NULL DEFAULT 0,
     increment     INT NOT NULL DEFAULT 1,
     PRIMARY KEY (name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


set global log_bin_trust_function_creators=1;

DELIMITER /

DROP FUNCTION IF EXISTS currval /

CREATE FUNCTION currval(seq_name VARCHAR(50))
RETURNS BIGINT
BEGIN
     DECLARE value BIGINT;
     SELECT current_value INTO value
     FROM hellomybatis_sequence
     WHERE upper(name) = upper(seq_name);
     RETURN value;
END;
/

DELIMITER ;


DELIMITER /

DROP FUNCTION IF EXISTS nextval /

CREATE FUNCTION nextval (seq_name VARCHAR(50))
RETURNS BIGINT
BEGIN
     DECLARE value BIGINT;
     UPDATE hellomybatis_sequence
     SET current_value = current_value + increment
     WHERE upper(name) = upper(seq_name);
     RETURN currval(seq_name);
END;
/

DELIMITER ;

DELIMITER /

DROP FUNCTION IF EXISTS setval /

CREATE FUNCTION setval (seq_name VARCHAR(50), value BIGINT)
RETURNS BIGINT
BEGIN
     UPDATE hellomybatis_sequence
     SET current_value = value
     WHERE upper(name) = upper(seq_name);
     RETURN currval(seq_name);
END;
/

DELIMITER ;


insert into hellomybatis_sequence set name='order_sequence_1';
insert into hellomybatis_sequence set name='order_sequence_2';
insert into hellomybatis_sequence set name='order_sequence_3';


-- call PROCEDURE
--select nextval('order_sequence_1');



CREATE TABLE `hellomybatis_orders_1` (
  order_id int(11) NOT NULL ,
  user_id int(11) NOT NULL,
  order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `hellomybatis_orders_2` (
  order_id int(11) NOT NULL ,
  user_id int(11) NOT NULL,
  order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `hellomybatis_orders_3` (
  order_id int(11) NOT NULL ,
  user_id int(11) NOT NULL,
  order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;