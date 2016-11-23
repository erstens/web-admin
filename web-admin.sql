/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.27 : Database - web_admin
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `sys_menu` */

CREATE TABLE `sys_menu` (
  `mid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `txt_name` varchar(20) NOT NULL COMMENT '名称',
  `p_id` int(11) NOT NULL COMMENT '父级菜单',
  `url` varchar(255) NOT NULL COMMENT '链接地址',
  `create_time` char(19) NOT NULL COMMENT '创建时间',
  `seq_no` int(11) NOT NULL COMMENT '序号',
  `icon_class` varchar(64) NOT NULL COMMENT '图标类',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`mid`,`txt_name`,`p_id`,`url`,`create_time`,`seq_no`,`icon_class`) values (1,'快捷菜单',0,'#','2016-11-21 12:29:17',0,'icon-application-cascade'),(2,'用户管理',1,'user','2016-11-21 01:27:46',100,'icon-users'),(3,'角色管理',1,'role','2016-11-21 01:28:13',200,'icon-user-group'),(4,'菜单管理',1,'menu','2016-11-21 14:52:42',300,'icon-chart-organisation'),(7,'数据字典',1,'#','2016-11-22 14:35:00',400,'icon-book'),(8,'系统参数',1,'#','2016-11-22 14:35:19',500,'icon-cog'),(9,'操作日志',1,'#','2016-11-22 14:35:52',600,'icon-application-osx-error');

/*Table structure for table `sys_role` */

CREATE TABLE `sys_role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `r_code` varchar(20) NOT NULL COMMENT '权限编码',
  `txt_name` varchar(20) NOT NULL COMMENT '权限名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` char(19) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`rid`,`r_code`,`txt_name`,`remark`,`create_time`) values (1,'SUPER_ADMIN','超级管理员','超级管理员','2016-11-18 06:14:41'),(2,'ADMIN','管理员','管理员','2016-11-18 06:25:00');

/*Table structure for table `sys_role_menu_rela` */

CREATE TABLE `sys_role_menu_rela` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` int(11) DEFAULT NULL COMMENT '权限主键',
  `mid` int(11) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='权限菜单关系表';

/*Data for the table `sys_role_menu_rela` */

insert  into `sys_role_menu_rela`(`id`,`rid`,`mid`) values (18,2,1),(19,2,2),(22,1,1),(23,1,2),(24,1,3),(25,1,4),(26,1,7),(27,1,8),(28,1,9);

/*Table structure for table `sys_user` */

CREATE TABLE `sys_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uname` varchar(15) NOT NULL COMMENT '用户名',
  `pwd` char(32) NOT NULL COMMENT '密码,MD5加密',
  `real_name` varchar(12) NOT NULL COMMENT '真实姓名',
  `is_used` char(1) NOT NULL COMMENT '是否启用',
  `create_time` char(19) NOT NULL COMMENT '创建时间',
  `rid` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`uid`,`uname`,`pwd`,`real_name`,`is_used`,`create_time`,`rid`) values (1,'admin','admin','王奥','1','2016-10-10 10:10:10',1),(2,'root','michaelsea','王奥','1','2016-11-10 03:13:07',1),(3,'wangao','michael','王奥','0','2016-11-10 03:13:22',1),(4,'lixue','michael','王奥','1','2016-11-10 03:14:24',1),(5,'okl9990','kkij980','王奥','1','2016-11-10 03:14:42',1),(6,'w89980','abcabcabc','王奥','1','2016-11-10 03:14:57',1),(7,'sa_admin','admin','王奥','1','2016-11-10 03:15:14',1),(8,'gk00909','michael','王奥','1','2016-11-10 03:15:27',1),(9,'mlk0009','1234567','王奥','1','2016-11-10 03:15:44',1),(10,'ol990','michael','王奥','0','2016-11-10 03:15:57',1),(11,'test','889890','王奥','1','2016-11-10 03:16:09',1),(12,'kl11113','lkmichl','王奥','1','2016-11-10 03:16:24',1),(13,'l9981','kkk1889','王奥','1','2016-11-10 04:29:00',1),(14,'th990','imchael123','王奥','1','2016-11-10 05:53:52',2),(15,'db_admin','admin','王奥','1','2016-11-11 03:16:53',1),(16,'t_admin','admin','王奥','1','2016-11-14 05:13:15',1),(19,'sdjkjkjjk','michael','sdjkjkjjk','1','2016-11-23 11:19:04',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
