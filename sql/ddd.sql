/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.7.17-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `food` (
	`id` int (11),
	`name` varchar (90),
	`price` float ,
	`des` varchar (600),
	`eatery_id` int (11)
); 
insert into `food` (`id`, `name`, `price`, `des`, `eatery_id`) values('1','酸辣土豆丝','8','咸鲜酸辣味','1');
insert into `food` (`id`, `name`, `price`, `des`, `eatery_id`) values('2','咸肉焗菜花','454.55','咸鲜味；有机菜花是蔬菜的一种，属于甘蓝的变种，花椰菜为十字花科植物，一种叶子宽大，基部钝形，边缘呈波状，平滑无毛，开黄白色花的一或二年生的草本植物。极受消费者的欢迎。','1');
insert into `food` (`id`, `name`, `price`, `des`, `eatery_id`) values('3','西红柿炒鸡蛋','11.58','西红柿炒鸡蛋，是许多百姓家庭中一道普通的大众菜肴。烹饪方法简单易学，营养搭配合理。色泽鲜艳，口味宜人，爽口、开胃，深受大众喜爱。','1');
insert into `food` (`id`, `name`, `price`, `des`, `eatery_id`) values('4','外婆铁锅饭','12.5','\"酱油炒饭是一道家常主食炒饭，主要食材是米饭。酱油炒饭可以根据个人口感添加少许酸或辣，并且配料也可以根据个人喜好适量加入。','1');
insert into `food` (`id`, `name`, `price`, `des`, `eatery_id`) values('5','外婆手工枣馍','3','传统手工制作，口味香甜、','1');
insert into `food` (`id`, `name`, `price`, `des`, `eatery_id`) values('6','南瓜粥','5',NULL,'1');
insert into `food` (`id`, `name`, `price`, `des`, `eatery_id`) values('7','宫保鸡球','3','宫保鸡球：色泽红亮，鸡球细嫩，花生米酥脆，胡辣荔汁味，食之可养身滋补、增进食欲。','1');
