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
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `term_id` bigint(11) DEFAULT NULL,
  `money` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_activity` */

/*Table structure for table `tb_activity_agency` */

DROP TABLE IF EXISTS `tb_activity_agency`;

CREATE TABLE `tb_activity_agency` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `agency_id` bigint(11) DEFAULT NULL,
  `agency_name` varchar(50) DEFAULT NULL,
  `activity_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_activity_agency` */

/*Table structure for table `tb_activity_cost` */

DROP TABLE IF EXISTS `tb_activity_cost`;

CREATE TABLE `tb_activity_cost` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `activity_id` bigint(11) DEFAULT NULL,
  `activity_name` varchar(50) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '1团队费用，2个人费用',
  `agency_id` bigint(11) DEFAULT NULL COMMENT '为0时代表这是团队费用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_activity_cost` */

/*Table structure for table `tb_agency` */

DROP TABLE IF EXISTS `tb_agency`;

CREATE TABLE `tb_agency` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `brithday` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `level` int(1) DEFAULT NULL COMMENT '1管理员2普通用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_agency` */

insert  into `tb_agency`(`id`,`name`,`phone`,`sex`,`brithday`,`email`,`create_time`,`del_flag`,`nick_name`,`level`) values (1,'admin','17312345678',1,'2020-06-12 07:04:33','1232141@163.com','2020-06-12 07:04:44',0,'阿达米',1);

/*Table structure for table `tb_term` */

DROP TABLE IF EXISTS `tb_term`;

CREATE TABLE `tb_term` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `content` varbinary(50) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `total_budget` int(10) DEFAULT NULL,
  `person_budget` int(10) DEFAULT NULL,
  `create_id` bigint(11) DEFAULT NULL,
  `create_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_term` */

/*Table structure for table `tb_term_record` */

DROP TABLE IF EXISTS `tb_term_record`;

CREATE TABLE `tb_term_record` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `term_id` bigint(11) DEFAULT NULL,
  `term_content` varchar(50) DEFAULT NULL,
  `agency_id` bigint(11) DEFAULT NULL,
  `agency_name` varchar(50) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1被邀请，2已接受，3已接受',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_term_record` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
