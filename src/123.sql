/*
SQLyog Professional v12.08 (64 bit)
MySQL - 8.0.18 : Database - tourism
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tourism` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `tourism`;

/*Table structure for table `tb_activity` */

DROP TABLE IF EXISTS `tb_activity`;

CREATE TABLE `tb_activity` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动内容',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收款账户',
  `term_id` bigint(11) DEFAULT NULL COMMENT '团队id',
  `money` int(10) DEFAULT NULL COMMENT '费用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tb_activity` */

insert  into `tb_activity`(`id`,`content`,`account`,`term_id`,`money`) values (1,'野炊','wangyi@163.com',2,9000),(2,'踏青','aliyunCss.com',2,10000),(3,'野餐','123123@qq.com',2,4000),(4,'漫步人生路','163@qq.com',2,5000);

/*Table structure for table `tb_activity_agency` */

DROP TABLE IF EXISTS `tb_activity_agency`;

CREATE TABLE `tb_activity_agency` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `agency_id` bigint(11) DEFAULT NULL COMMENT '会员id',
  `agency_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '会员名',
  `activity_id` bigint(11) DEFAULT NULL COMMENT '活动id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_activity_agency` */

/*Table structure for table `tb_activity_cost` */

DROP TABLE IF EXISTS `tb_activity_cost`;

CREATE TABLE `tb_activity_cost` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `activity_id` bigint(11) DEFAULT NULL COMMENT '活动id',
  `activity_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动内容',
  `type` int(1) DEFAULT NULL COMMENT '1团队费用，2个人费用',
  `agency_id` bigint(11) DEFAULT NULL COMMENT '为0时代表这是团队费用',
  `money` int(10) DEFAULT NULL COMMENT '活动经费',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_activity_cost` */

insert  into `tb_activity_cost`(`id`,`activity_id`,`activity_name`,`type`,`agency_id`,`money`) values (1,1,'踏青',NULL,1,10000),(2,1,'野餐',NULL,1,4000),(3,1,'漫步人生路',NULL,1,5000);

/*Table structure for table `tb_agency` */

DROP TABLE IF EXISTS `tb_agency`;

CREATE TABLE `tb_agency` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '会员名',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `sex` int(1) DEFAULT NULL COMMENT '性别0女1男',
  `brithday` datetime DEFAULT NULL COMMENT '出生日期',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标志',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `level` int(1) DEFAULT NULL COMMENT '1管理员2普通用户',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_agency` */

insert  into `tb_agency`(`id`,`name`,`phone`,`sex`,`brithday`,`email`,`create_time`,`del_flag`,`nick_name`,`level`,`password`) values (1,'admin','17312345678',1,'2020-06-22 16:00:00','1232141@163.com','2020-06-12 07:04:44',0,'admin',1,'admin'),(2,'小红','18387853241',0,'2020-06-23 16:00:00','hongjiejie@aliemail.com','2020-06-13 00:47:22',0,'红姐姐',2,'xiaohong');

/*Table structure for table `tb_term` */

DROP TABLE IF EXISTS `tb_term`;

CREATE TABLE `tb_term` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varbinary(50) DEFAULT NULL COMMENT '活动内容',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `total_budget` int(10) DEFAULT NULL COMMENT '总预算',
  `person_budget` int(10) DEFAULT NULL COMMENT '个人预算',
  `create_id` bigint(11) DEFAULT NULL COMMENT '创建者id',
  `create_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者昵称',
  `status` int(1) DEFAULT NULL COMMENT '0未开始，1已开始，2已结束',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_term` */

insert  into `tb_term`(`id`,`content`,`start_time`,`total_budget`,`person_budget`,`create_id`,`create_name`,`status`) values (1,'武汉三日游','2020-06-13 11:14:40',100000,1000,2,'红姐姐',0),(2,'清明寻亲','2020-06-02 16:00:00',100000,100,1,'admin',2);

/*Table structure for table `tb_term_record` */

DROP TABLE IF EXISTS `tb_term_record`;

CREATE TABLE `tb_term_record` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `term_id` bigint(11) DEFAULT NULL,
  `term_content` varchar(50) DEFAULT NULL,
  `agency_id` bigint(11) DEFAULT NULL,
  `agency_name` varchar(50) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1被邀请，2已接受，3已接受',
  `create_name` varchar(50) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_term_record` */

insert  into `tb_term_record`(`id`,`term_id`,`term_content`,`agency_id`,`agency_name`,`status`,`create_name`,`start_time`) values (1,1,'武汉三日游',1,'admin',2,'红姐姐','2020-06-13 15:22:03'),(2,2,'清明寻亲',1,'admin',2,'admin','2020-06-13 16:14:25');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
