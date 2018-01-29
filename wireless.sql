/*
Navicat MySQL Data Transfer

Source Server         : shun_fzll
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : wireless

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2014-01-09 22:23:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `menutbl`
-- ----------------------------
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

-- ----------------------------
-- Records of menutbl
-- ----------------------------
INSERT INTO `menutbl` VALUES ('1', '1', '鱼香肉丝', '20', 'yxrs.jpg', '鱼香肉丝是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟');
INSERT INTO `menutbl` VALUES ('2', '1', '糖醋排骨', '30', 'tcpg.jpg', '糖醋排骨是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟');
INSERT INTO `menutbl` VALUES ('3', '1', '黄焖鸡翅', '20', '58772a5e-4553-40f2-8294-77d1d0b65f89.jpg', '黄焖鸡翅是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试哟');
INSERT INTO `menutbl` VALUES ('4', '1', '红烧冬瓜球', '15', 'hsdgq.jpg', '红烧冬瓜球是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟');
INSERT INTO `menutbl` VALUES ('5', '1', '红烧肉', '25', 'hsr.jpg', '红烧肉是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟');
INSERT INTO `menutbl` VALUES ('6', '1', '可乐鸡翅', '25', 'kljc.jpg', '可乐鸡翅是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟');
INSERT INTO `menutbl` VALUES ('7', '1', '北京烤鸭', '40', 'bjky.jpg', '北京烤鸭是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟');
INSERT INTO `menutbl` VALUES ('8', '1', '耗油虾球', '35', 'hyxq.jpg', '耗油虾球是一道人人喜欢的家常菜，有些厨房新手做出的味道总是没有饭店的好吃，不过不要紧，现在超市有卖鱼香肉丝的调料包了。只用一包调料就可以做出一道美味经典的川菜，厨房新手们不妨试试哟');
INSERT INTO `menutbl` VALUES ('9', '1', '1', '1', 'dda82505-e966-4a8b-8a19-e0abe2d96363.jpg', '1');
INSERT INTO `menutbl` VALUES ('10', '1', '新菜名', '1', null, '1');
INSERT INTO `menutbl` VALUES ('11', '3', '红米', '799', 'ff5f8860-9a3c-4453-aabe-64c2e05e980f.jpg', '玲丽小弟抢红米啊！');

-- ----------------------------
-- Table structure for `menutypetbl`
-- ----------------------------
DROP TABLE IF EXISTS `menutypetbl`;
CREATE TABLE `menutypetbl` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of menutypetbl
-- ----------------------------
INSERT INTO `menutypetbl` VALUES ('1', '热菜');
INSERT INTO `menutypetbl` VALUES ('2', '凉菜');
INSERT INTO `menutypetbl` VALUES ('3', '主食');
INSERT INTO `menutypetbl` VALUES ('4', '甜品');
INSERT INTO `menutypetbl` VALUES ('5', '汤');
INSERT INTO `menutypetbl` VALUES ('6', '开胃');

-- ----------------------------
-- Table structure for `orderdetailtbl`
-- ----------------------------
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

-- ----------------------------
-- Records of orderdetailtbl
-- ----------------------------
INSERT INTO `orderdetailtbl` VALUES ('1', '1', '1', '1', '1');
INSERT INTO `orderdetailtbl` VALUES ('2', '2', '1', '1', '1');
INSERT INTO `orderdetailtbl` VALUES ('3', '6', '1', '1', '1');
INSERT INTO `orderdetailtbl` VALUES ('4', '5', '1', '1', '1');
INSERT INTO `orderdetailtbl` VALUES ('5', '4', '1', '1', '1');
INSERT INTO `orderdetailtbl` VALUES ('6', '4', '1', '1', '1');

-- ----------------------------
-- Table structure for `ordertbl`
-- ----------------------------
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

-- ----------------------------
-- Records of ordertbl
-- ----------------------------
INSERT INTO `ordertbl` VALUES ('1', '2', '2013-9-24 10:33:56', '1', '10', '1', '无');

-- ----------------------------
-- Table structure for `tabletbl`
-- ----------------------------
DROP TABLE IF EXISTS `tabletbl`;
CREATE TABLE `tabletbl` (
  `id` int(11) NOT NULL,
  `Ord_id` int(11) DEFAULT NULL,
  `num` varchar(128) DEFAULT NULL,
  `flag` varchar(128) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tabletbl
-- ----------------------------
INSERT INTO `tabletbl` VALUES ('1', '0', '10', '0', '-');
INSERT INTO `tabletbl` VALUES ('2', '0', '10', '1', '无');
INSERT INTO `tabletbl` VALUES ('3', '0', '10', '0', '无');
INSERT INTO `tabletbl` VALUES ('4', '0', '15', '0', '无');
INSERT INTO `tabletbl` VALUES ('5', '0', '15', '0', '无');
INSERT INTO `tabletbl` VALUES ('6', '0', '15', '1', '无');
INSERT INTO `tabletbl` VALUES ('7', '0', '7', '1', '无');

-- ----------------------------
-- Table structure for `usertbl`
-- ----------------------------
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

-- ----------------------------
-- Records of usertbl
-- ----------------------------
INSERT INTO `usertbl` VALUES ('1', '科帮网', '52itstyle', 'shun22', '男22', '1', 'http://www.52itstyle.com');
