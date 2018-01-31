/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.7.17-log : Database - ordering
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ordering` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ordering`;

/*Table structure for table `menutbl` */

DROP TABLE IF EXISTS `menutbl`;

CREATE TABLE `menutbl` (
  `id` int(11) NOT NULL,
  `typeID` int(11) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `price` varchar(128) DEFAULT NULL,
  `pic` varchar(128) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK38A5B9FFAA26CD92` (`typeID`),
  CONSTRAINT `FK38A5B9FFAA26CD92` FOREIGN KEY (`typeID`) REFERENCES `menutypetbl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `menutbl` */

insert  into `menutbl`(`id`,`typeID`,`name`,`price`,`pic`,`remark`) values (1,1,'鱼香肉丝','20','yxrs.jpg','鱼香肉丝是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟'),(2,1,'糖醋排骨','30','tcpg.jpg','糖醋排骨是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟'),(3,1,'黄焖鸡翅','20','58772a5e-4553-40f2-8294-77d1d0b65f89.jpg','黄焖鸡翅是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试哟'),(4,1,'红烧冬瓜球','15','hsdgq.jpg','红烧冬瓜球是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟'),(5,1,'红烧肉','25','hsr.jpg','红烧肉是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟'),(6,1,'可乐鸡翅','25','kljc.jpg','可乐鸡翅是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟'),(7,1,'北京烤鸭','40','bjky.jpg','北京烤鸭是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟'),(8,1,'耗油虾球','35','hyxq.jpg','耗油虾球是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟'),(9,1,'1','1','dda82505-e966-4a8b-8a19-e0abe2d96363.jpg','1'),(10,1,'新菜名','1',NULL,'1'),(11,3,'红米','799','ff5f8860-9a3c-4453-aabe-64c2e05e980f.jpg','玲丽小弟抢红米啊！');

/*Table structure for table `menutypetbl` */

DROP TABLE IF EXISTS `menutypetbl`;

CREATE TABLE `menutypetbl` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `menutypetbl` */

insert  into `menutypetbl`(`id`,`name`) values (1,'热菜'),(2,'凉菜'),(3,'主食'),(4,'甜品'),(5,'汤'),(6,'开胃');

/*Table structure for table `orderdetailtbl` */

DROP TABLE IF EXISTS `orderdetailtbl`;

CREATE TABLE `orderdetailtbl` (
  `id` int(11) NOT NULL,
  `menuID` int(11) DEFAULT NULL,
  `orderID` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK383C4A3F2BCE9471` (`menuID`),
  KEY `FK383C4A3F3AEC29E1` (`orderID`),
  CONSTRAINT `FK383C4A3F2BCE9471` FOREIGN KEY (`menuID`) REFERENCES `menutbl` (`id`),
  CONSTRAINT `FK383C4A3F3AEC29E1` FOREIGN KEY (`orderID`) REFERENCES `ordertbl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `orderdetailtbl` */

insert  into `orderdetailtbl`(`id`,`menuID`,`orderID`,`num`,`remark`) values (1,1,1,1,'1'),(2,2,1,1,'1'),(3,6,1,1,'1'),(4,5,1,1,'1'),(5,4,1,1,'1'),(6,4,1,1,'1');

/*Table structure for table `ordertbl` */

DROP TABLE IF EXISTS `ordertbl`;

CREATE TABLE `ordertbl` (
  `id` int(11) NOT NULL,
  `TableID` int(11) DEFAULT NULL,
  `OrderTime` varchar(128) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `personNum` int(11) DEFAULT NULL,
  `idPay` int(11) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK49924DB0A75EB9E1` (`TableID`),
  CONSTRAINT `FK49924DB0A75EB9E1` FOREIGN KEY (`TableID`) REFERENCES `tabletbl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `ordertbl` */

insert  into `ordertbl`(`id`,`TableID`,`OrderTime`,`UserID`,`personNum`,`idPay`,`remark`) values (1,2,'2013-9-24 10:33:56',1,10,1,'无');

/*Table structure for table `tabletbl` */

DROP TABLE IF EXISTS `tabletbl`;

CREATE TABLE `tabletbl` (
  `id` int(11) NOT NULL,
  `Ord_id` int(11) DEFAULT NULL,
  `num` varchar(128) DEFAULT NULL,
  `flag` varchar(128) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `tabletbl` */

insert  into `tabletbl`(`id`,`Ord_id`,`num`,`flag`,`description`) values (1,0,'10','0','-'),(2,0,'10','1','无'),(3,0,'10','0','无'),(4,0,'15','0','无'),(5,0,'15','0','无'),(6,0,'15','1','无'),(7,0,'7','1','无');

/*Table structure for table `usertbl` */

DROP TABLE IF EXISTS `usertbl`;

CREATE TABLE `usertbl` (
  `id` int(11) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `gender` varchar(128) DEFAULT NULL,
  `permission` int(11) NOT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `usertbl` */

insert  into `usertbl`(`id`,`username`,`password`,`name`,`gender`,`permission`,`remark`) values (1,'董文强','123','dwq','男',1,'-----');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
