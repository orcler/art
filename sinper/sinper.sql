/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50712
Source Host           : 10.8.3.24:3306
Source Database       : sinper

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-06-06 17:16:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for com
-- ----------------------------
DROP TABLE IF EXISTS `com`;
CREATE TABLE `com` (
  `comcode` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `shortname` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `zipcode` varchar(6) DEFAULT NULL,
  `phone` varchar(18) DEFAULT NULL,
  `fax` varchar(18) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `validcode` varchar(2) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `owner` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`comcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of com
-- ----------------------------
INSERT INTO `com` VALUES ('00000001', '山东振邦保安公司', '振邦保安公司', null, '250000', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for iuser
-- ----------------------------
DROP TABLE IF EXISTS `iuser`;
CREATE TABLE `iuser` (
  `USERID` varchar(80) NOT NULL,
  `USERNAME` varchar(80) DEFAULT NULL,
  `PWD` varchar(100) DEFAULT NULL,
  `STATE` varchar(10) DEFAULT NULL,
  `COMCODE` varchar(8) DEFAULT NULL,
  `MOBILE` varchar(20) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `OPERATOR` varchar(80) DEFAULT NULL,
  `MAKEDATE` date DEFAULT NULL,
  `MAKETIME` char(8) DEFAULT NULL,
  `MODIFYDATE` date DEFAULT NULL,
  `MODIFYTIME` char(8) DEFAULT NULL,
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iuser
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `nodecode` varchar(5) NOT NULL,
  `parentnodecode` varchar(5) DEFAULT NULL,
  `nodelevel` char(1) DEFAULT NULL,
  `nodename` varchar(100) DEFAULT NULL,
  `childflag` varchar(10) DEFAULT NULL,
  `nodekey` varchar(10) DEFAULT NULL,
  `runscript` varchar(150) DEFAULT NULL,
  `nodedescription` varchar(100) DEFAULT NULL,
  `nodesign` varchar(5) DEFAULT NULL,
  `nodeorder` int(11) DEFAULT NULL,
  PRIMARY KEY (`nodecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for menugrp
-- ----------------------------
DROP TABLE IF EXISTS `menugrp`;
CREATE TABLE `menugrp` (
  `menugrpcode` varchar(10) NOT NULL,
  `menugrpname` varchar(40) DEFAULT NULL,
  `menugrpdescription` varchar(100) DEFAULT NULL,
  `menusign` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`menugrpcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menugrp
-- ----------------------------

-- ----------------------------
-- Table structure for menugrptomenu
-- ----------------------------
DROP TABLE IF EXISTS `menugrptomenu`;
CREATE TABLE `menugrptomenu` (
  `menugrpcode` varchar(10) NOT NULL,
  `nodecode` varchar(10) NOT NULL,
  PRIMARY KEY (`menugrpcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menugrptomenu
-- ----------------------------

-- ----------------------------
-- Table structure for process
-- ----------------------------
DROP TABLE IF EXISTS `process`;
CREATE TABLE `process` (
  `processid` varchar(10) NOT NULL,
  `processname` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`processid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of process
-- ----------------------------
INSERT INTO `process` VALUES ('0000000001', '入库管理');
INSERT INTO `process` VALUES ('0000000002', '出库管理');
INSERT INTO `process` VALUES ('0000000003', '库存查询');

-- ----------------------------
-- Table structure for traffic
-- ----------------------------
DROP TABLE IF EXISTS `traffic`;
CREATE TABLE `traffic` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `EngineNo` varchar(60) DEFAULT NULL,
  `VIN` varchar(60) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `uwflag` varchar(2) DEFAULT NULL,
  `model` varchar(60) DEFAULT NULL,
  `cost` varchar(60) DEFAULT NULL,
  `cert` varchar(60) DEFAULT NULL,
  `mileage` double(20,0) DEFAULT NULL,
  `color` varchar(60) DEFAULT NULL,
  `attn` varchar(60) DEFAULT NULL,
  `phone` varchar(60) DEFAULT NULL,
  `comcode` varchar(60) DEFAULT NULL,
  `group` varchar(60) DEFAULT NULL,
  `remark` varchar(600) DEFAULT NULL,
  `info1` varchar(100) DEFAULT NULL,
  `info2` varchar(100) DEFAULT NULL,
  `info3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of traffic
-- ----------------------------

-- ----------------------------
-- Table structure for usertomenugrp
-- ----------------------------
DROP TABLE IF EXISTS `usertomenugrp`;
CREATE TABLE `usertomenugrp` (
  `usercode` varchar(80) NOT NULL,
  `menugrpcode` varchar(10) NOT NULL,
  PRIMARY KEY (`usercode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usertomenugrp
-- ----------------------------

-- ----------------------------
-- Table structure for wmission
-- ----------------------------
DROP TABLE IF EXISTS `wmission`;
CREATE TABLE `wmission` (
  `missionid` varchar(20) NOT NULL,
  `submissionid` varchar(20) NOT NULL,
  `processid` varchar(10) NOT NULL,
  `activityid` varchar(10) NOT NULL,
  `activitystatus` char(1) NOT NULL,
  `missionprop1` varchar(90) DEFAULT NULL,
  `missionprop2` varchar(90) DEFAULT NULL,
  `missionprop3` varchar(90) DEFAULT NULL,
  `defaultoperator` varchar(80) DEFAULT NULL,
  `lastoperator` varchar(80) DEFAULT NULL,
  `createoperator` varchar(80) NOT NULL,
  `makedate` date NOT NULL,
  `maketime` char(8) NOT NULL,
  `modifydate` date NOT NULL,
  `modifytime` char(8) NOT NULL,
  `indate` date DEFAULT NULL,
  `intime` varchar(8) DEFAULT NULL,
  `outdate` date DEFAULT NULL,
  `outtime` varchar(8) DEFAULT NULL,
  `standenddate` date DEFAULT NULL,
  `standendtime` varchar(8) DEFAULT NULL,
  `operatecom` varchar(20) DEFAULT NULL,
  `mainmissionid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`missionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wmission
-- ----------------------------

-- ----------------------------
-- Table structure for workflow
-- ----------------------------
DROP TABLE IF EXISTS `workflow`;
CREATE TABLE `workflow` (
  `wfid` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `busitype` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`wfid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workflow
-- ----------------------------
