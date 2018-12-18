/*
Navicat MySQL Data Transfer

Source Server         : MySql_1
Source Server Version : 50529
Source Host           : 127.0.0.1:3306
Source Database       : utility

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2018-10-22 14:25:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bom
-- ----------------------------
DROP TABLE IF EXISTS `bom`;
CREATE TABLE `bom` (
  `materiel_id` varchar(255) NOT NULL,
  `materiel_detail` varchar(255) NOT NULL,
  `materiel_package` varchar(255) NOT NULL,
  `materiel_supplier` varchar(255) NOT NULL,
  `materiel_type` varchar(255) NOT NULL,
  PRIMARY KEY (`materiel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for inventorytransaction
-- ----------------------------
DROP TABLE IF EXISTS `inventorytransaction`;
CREATE TABLE `inventorytransaction` (
  `order_code` varchar(255) NOT NULL,
  `action_type` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `item` varchar(255) NOT NULL,
  `current_location` varchar(255) DEFAULT NULL,
  `destination_location` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `location_id` varchar(11) NOT NULL,
  `materiel_number` varchar(11) NOT NULL,
  `materiel_detail` varchar(255) NOT NULL,
  `materiel_group_code` varchar(255) NOT NULL,
  `location_detail` varchar(255) NOT NULL,
  `materiel_unit` varchar(255) NOT NULL,
  `materiel_NRO` double(11,0) NOT NULL,
  `materiel_UO` int(255) NOT NULL DEFAULT '0',
  `materiel_PRC` int(255) NOT NULL DEFAULT '0',
  `materiel_FO` int(255) NOT NULL DEFAULT '0',
  `materiel_FC` int(255) NOT NULL DEFAULT '0',
  `materiel_FCO` int(255) NOT NULL DEFAULT '0',
  `materiel_CS` int(11) NOT NULL DEFAULT '0',
  `materiel_CN` int(255) NOT NULL DEFAULT '0',
  `materiel_OIC` int(255) NOT NULL DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  `openingInentory` varchar(255) DEFAULT NULL,
  `endInentory` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`materiel_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
