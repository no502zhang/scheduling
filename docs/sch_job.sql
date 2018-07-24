/*
Navicat MySQL Data Transfer

Source Server         : 虚拟机
Source Server Version : 50722
Source Host           : 192.168.118.71:3306
Source Database       : dev

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-24 18:11:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sch_job
-- ----------------------------
DROP TABLE IF EXISTS `sch_job`;
CREATE TABLE `sch_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
