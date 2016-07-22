/*
Navicat MySQL Data Transfer

Source Server         : pro
Source Server Version : 50621
Source Host           : 10.1.3.243:3306
Source Database       : sinper

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-07-22 16:03:02
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
-- Table structure for icode
-- ----------------------------
DROP TABLE IF EXISTS `icode`;
CREATE TABLE `icode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codetype` varchar(30) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `codename` varchar(60) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of icode
-- ----------------------------
INSERT INTO `icode` VALUES ('8', 'comcode', '000001', '振邦保安', null);
INSERT INTO `icode` VALUES ('9', 'comcode', '000002', '经销商', null);
INSERT INTO `icode` VALUES ('10', 'comcode', '000003', '贷款商', null);
INSERT INTO `icode` VALUES ('11', 'uwflag', '0', '确认', null);
INSERT INTO `icode` VALUES ('12', 'uwflag', '1', '审核通过', null);
INSERT INTO `icode` VALUES ('13', 'uwflag', '2', '录入', null);
INSERT INTO `icode` VALUES ('14', 'uwflag', '3', '拒绝', null);

-- ----------------------------
-- Table structure for Imgs
-- ----------------------------
DROP TABLE IF EXISTS `Imgs`;
CREATE TABLE `Imgs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serialno` varchar(20) DEFAULT NULL,
  `pages` int(10) DEFAULT NULL,
  `iurl` varchar(200) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Imgs
-- ----------------------------

-- ----------------------------
-- Table structure for IUSER
-- ----------------------------
DROP TABLE IF EXISTS `IUSER`;
CREATE TABLE `IUSER` (
  `USERID` varchar(80) NOT NULL,
  `NAME` varchar(80) DEFAULT NULL,
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
-- Records of IUSER
-- ----------------------------
INSERT INTO `IUSER` VALUES ('101', 'jack', '001', null, '000002', null, null, null, '2016-06-06', null, null, null);
INSERT INTO `IUSER` VALUES ('102', 'jack', '001', null, '000003', null, null, null, null, null, null, null);
INSERT INTO `IUSER` VALUES ('103', 'jack', '001', null, '000001', null, null, null, null, null, null, null);

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
INSERT INTO `menu` VALUES ('10000', '00000', '0', '入库管理', null, null, '', null, null, null);
INSERT INTO `menu` VALUES ('10001', '10000', '1', '入库登记', null, null, 'inRgterUI()', null, null, null);
INSERT INTO `menu` VALUES ('10002', '10000', '1', '入库审核', null, null, 'inUw()', null, null, null);
INSERT INTO `menu` VALUES ('10003', '10000', '1', '打印凭证和入库确认', null, null, 'confInPrint()', null, null, null);
INSERT INTO `menu` VALUES ('10004', '10000', '1', '批量入库', null, null, 'batchregster()', null, null, null);
INSERT INTO `menu` VALUES ('20000', '00000', '0', '出库管理', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('20001', '20000', '1', '经销商出库申请', null, null, 'outRgterUI()', null, null, null);
INSERT INTO `menu` VALUES ('20002', '20000', '1', '出库审核', null, null, 'outUw()', null, null, null);
INSERT INTO `menu` VALUES ('20003', '20000', '1', '打印凭证和出入库确认', null, null, 'confOutPrint()', null, null, null);
INSERT INTO `menu` VALUES ('30000', '00000', '0', '库存管理', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('30001', '30000', '1', '入库查询', null, null, 'inQuery()', null, null, null);
INSERT INTO `menu` VALUES ('30002', '30000', '1', '出库查询', null, null, 'outQuery()', null, null, null);
INSERT INTO `menu` VALUES ('30003', '30000', '1', '库存查询', null, null, 'existsQuery()', null, null, null);
INSERT INTO `menu` VALUES ('30004', '30000', '1', '库存盘点', null, null, 'invertApply()', null, null, null);
INSERT INTO `menu` VALUES ('30005', '30000', '1', '盘点上传', null, null, 'invertUpload()', null, null, null);
INSERT INTO `menu` VALUES ('30006', '30000', '1', '盘点确认', null, null, 'invertConf()', null, null, null);
INSERT INTO `menu` VALUES ('40000', '00000', '0', '权限管理', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('40001', '40000', '1', '权限管理', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('40002', '40000', '1', '用户管理', null, null, null, null, null, null);

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
INSERT INTO `menugrp` VALUES ('loaner', null, null, null);
INSERT INTO `menugrp` VALUES ('saler', null, null, null);
INSERT INTO `menugrp` VALUES ('security', null, null, null);

-- ----------------------------
-- Table structure for menugrptomenu
-- ----------------------------
DROP TABLE IF EXISTS `menugrptomenu`;
CREATE TABLE `menugrptomenu` (
  `menugrpcode` varchar(10) NOT NULL,
  `nodecode` varchar(10) NOT NULL,
  PRIMARY KEY (`menugrpcode`,`nodecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menugrptomenu
-- ----------------------------
INSERT INTO `menugrptomenu` VALUES ('loaner', '10000');
INSERT INTO `menugrptomenu` VALUES ('loaner', '10002');
INSERT INTO `menugrptomenu` VALUES ('loaner', '20000');
INSERT INTO `menugrptomenu` VALUES ('loaner', '20002');
INSERT INTO `menugrptomenu` VALUES ('loaner', '30000');
INSERT INTO `menugrptomenu` VALUES ('loaner', '30001');
INSERT INTO `menugrptomenu` VALUES ('loaner', '30002');
INSERT INTO `menugrptomenu` VALUES ('loaner', '30003');
INSERT INTO `menugrptomenu` VALUES ('loaner', '30004');
INSERT INTO `menugrptomenu` VALUES ('loaner', '30006');
INSERT INTO `menugrptomenu` VALUES ('saler', '10000');
INSERT INTO `menugrptomenu` VALUES ('saler', '10001');
INSERT INTO `menugrptomenu` VALUES ('saler', '10004');
INSERT INTO `menugrptomenu` VALUES ('saler', '20000');
INSERT INTO `menugrptomenu` VALUES ('saler', '20001');
INSERT INTO `menugrptomenu` VALUES ('saler', '30000');
INSERT INTO `menugrptomenu` VALUES ('saler', '30001');
INSERT INTO `menugrptomenu` VALUES ('saler', '30002');
INSERT INTO `menugrptomenu` VALUES ('saler', '30003');
INSERT INTO `menugrptomenu` VALUES ('security', '10000');
INSERT INTO `menugrptomenu` VALUES ('security', '10003');
INSERT INTO `menugrptomenu` VALUES ('security', '20000');
INSERT INTO `menugrptomenu` VALUES ('security', '20003');
INSERT INTO `menugrptomenu` VALUES ('security', '30000');
INSERT INTO `menugrptomenu` VALUES ('security', '30001');
INSERT INTO `menugrptomenu` VALUES ('security', '30002');
INSERT INTO `menugrptomenu` VALUES ('security', '30003');
INSERT INTO `menugrptomenu` VALUES ('security', '30005');

-- ----------------------------
-- Table structure for MISSION
-- ----------------------------
DROP TABLE IF EXISTS `MISSION`;
CREATE TABLE `MISSION` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of MISSION
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
INSERT INTO `process` VALUES ('0000000003', '库存管理');

-- ----------------------------
-- Table structure for SERIALNO
-- ----------------------------
DROP TABLE IF EXISTS `SERIALNO`;
CREATE TABLE `SERIALNO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notype` varchar(30) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `nolength` int(11) DEFAULT NULL,
  `prefix` varchar(20) DEFAULT NULL,
  `maxno` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SERIALNO
-- ----------------------------
INSERT INTO `SERIALNO` VALUES ('1', 'WF', '工作流水', '20', '0', '35', null);
INSERT INTO `SERIALNO` VALUES ('2', 'TF', '库存流水', '16', '0', '28', null);

-- ----------------------------
-- Table structure for SYSVAR
-- ----------------------------
DROP TABLE IF EXISTS `SYSVAR`;
CREATE TABLE `SYSVAR` (
  `sysvar` varchar(30) NOT NULL,
  `sysvartype` varchar(2) DEFAULT NULL,
  `sysvarvalue` varchar(500) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYSVAR
-- ----------------------------
INSERT INTO `SYSVAR` VALUES ('pdfpath', 'pr', '/was', 'pdf模板路径');

-- ----------------------------
-- Table structure for TRAFFIC
-- ----------------------------
DROP TABLE IF EXISTS `TRAFFIC`;
CREATE TABLE `TRAFFIC` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `SerialNo` varchar(16) NOT NULL,
  `EngineNo` varchar(60) DEFAULT NULL,
  `VIN` varchar(60) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `paymode` varchar(2) DEFAULT NULL,
  `uwflag` varchar(2) DEFAULT NULL,
  `model` varchar(60) DEFAULT NULL,
  `cost` varchar(60) DEFAULT NULL,
  `cert` varchar(60) DEFAULT NULL,
  `mileage` double(20,0) DEFAULT NULL,
  `color` varchar(60) DEFAULT NULL,
  `attn` varchar(60) DEFAULT NULL,
  `phone` varchar(60) DEFAULT NULL,
  `comcode` varchar(60) DEFAULT NULL,
  `indate` date DEFAULT NULL,
  `intime` varchar(8) DEFAULT NULL,
  `outdate` date DEFAULT NULL,
  `outtime` varchar(8) DEFAULT NULL,
  `grpname` varchar(60) DEFAULT NULL,
  `remark` varchar(600) DEFAULT NULL,
  `info1` varchar(100) DEFAULT NULL,
  `info2` varchar(100) DEFAULT NULL,
  `info3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of TRAFFIC
-- ----------------------------
INSERT INTO `TRAFFIC` VALUES ('305', '0000000000000014', '1000001K', 'L000000001', '2', '', '0', '东风', '10.0', 'ISO10000001', '60', '黑色', '张三', '0531-55690001', '000001', '2016-07-07', '13:55:52', '2016-07-07', '15:44:00', null, '0', '1', null, null);
INSERT INTO `TRAFFIC` VALUES ('306', '0000000000000015', '1000002K', 'L000000002', '1', null, '0', '宝马X5', '11.0', 'ISO10000002', '61', '红色', '李四', '010-55690001', '000002', '2016-07-07', '13:55:52', '2016-07-07', '15:39:22', null, '111', '1', null, null);
INSERT INTO `TRAFFIC` VALUES ('307', '0000000000000016', '1000003K', 'L000000003', '1', null, '0', '奥迪A6', '12.0', 'ISO10000003', '62', '白色', '三五', '1.5112344321E10', '000003', '2016-07-07', '13:55:52', '2016-07-07', '14:05:33', null, null, null, null, null);
INSERT INTO `TRAFFIC` VALUES ('308', '0000000000000017', '1000004K', 'L000000004', '1', null, '0', '奔驰200', '13.0', 'ISO10000004', '63', '绿色', '赵子龙', '1.5112344322E10', '000001', '2016-07-07', '13:55:52', '2016-07-07', '14:04:37', null, null, '1', null, null);
INSERT INTO `TRAFFIC` VALUES ('319', '0000000000000028', '010', '00', '1', null, '0', '00', '10.0', '10', '0', '0', '0', '0', '000001', '2016-07-07', '15:44:00', null, null, null, '0', null, null, null);

-- ----------------------------
-- Table structure for usertomenugrp
-- ----------------------------
DROP TABLE IF EXISTS `usertomenugrp`;
CREATE TABLE `usertomenugrp` (
  `userid` varchar(80) NOT NULL,
  `menugrpcode` varchar(10) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usertomenugrp
-- ----------------------------
INSERT INTO `usertomenugrp` VALUES ('101', 'saler');
INSERT INTO `usertomenugrp` VALUES ('102', 'loaner');
INSERT INTO `usertomenugrp` VALUES ('103', 'security');

-- ----------------------------
-- Table structure for workflow
-- ----------------------------
DROP TABLE IF EXISTS `workflow`;
CREATE TABLE `workflow` (
  `wfid` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `busitype` varchar(10) DEFAULT NULL,
  `busname` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`wfid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workflow
-- ----------------------------
INSERT INTO `workflow` VALUES ('1000000001', '入库登记', 'IN', '入库管理');
INSERT INTO `workflow` VALUES ('1000000002', '入库审核', 'IN', '入库管理');
INSERT INTO `workflow` VALUES ('1000000003', '入库确认', 'IN', '入库管理');
INSERT INTO `workflow` VALUES ('1000000004', '入库结束', 'IN', '入库管理');
INSERT INTO `workflow` VALUES ('2000000001', '出库登记', 'OUT', '出库管理');
INSERT INTO `workflow` VALUES ('2000000002', '出库审核', 'OUT', '出库管理');
INSERT INTO `workflow` VALUES ('2000000003', '出库确认', 'OUT', '出库管理');
INSERT INTO `workflow` VALUES ('2000000004', '出库结束', 'OUT', '出库管理');
