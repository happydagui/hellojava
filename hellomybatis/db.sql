CREATE TABLE `hellomybatis_user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL ,
  `password` varchar(255) DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `hellomybatis_user` VALUES (1, "xiaomin", 28, "password");
INSERT INTO `hellomybatis_user` VALUES (2, "wangyaping", 22, "password");