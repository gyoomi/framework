/*
Navicat MySQL Data Transfer

Source Server         : conn
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : fr_db

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-10-30 21:53:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fr_dept
-- ----------------------------
DROP TABLE IF EXISTS `fr_dept`;
CREATE TABLE `fr_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `node_no` varchar(200) DEFAULT NULL,
  `parent_node_no` varchar(200) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_user_name` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_user_name` varchar(200) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_dept
-- ----------------------------

-- ----------------------------
-- Table structure for fr_log
-- ----------------------------
DROP TABLE IF EXISTS `fr_log`;
CREATE TABLE `fr_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) DEFAULT NULL,
  `module_name` varchar(200) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(200) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `parameters` varchar(500) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `ip_address` varchar(15) DEFAULT NULL,
  `mac_address` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_log
-- ----------------------------

-- ----------------------------
-- Table structure for fr_menu
-- ----------------------------
DROP TABLE IF EXISTS `fr_menu`;
CREATE TABLE `fr_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `node_no` varchar(100) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `icon` varchar(200) DEFAULT NULL COMMENT '存储图片的URL地址',
  `parent_id` int(11) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `is_short_cut` int(11) DEFAULT NULL COMMENT '1 - 是；2 - 否',
  `short_cut_name` varchar(200) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_user_name` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_user_name` varchar(200) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_menu
-- ----------------------------

-- ----------------------------
-- Table structure for fr_menu_privilege
-- ----------------------------
DROP TABLE IF EXISTS `fr_menu_privilege`;
CREATE TABLE `fr_menu_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '界面显示文字',
  `node_no` varchar(200) DEFAULT NULL COMMENT '字符串，同一菜单下不能重复',
  `url` varchar(200) DEFAULT NULL,
  `value` int(11) DEFAULT '0' COMMENT '默认值 - 0；0 - 没有该权限；其他 - 拥有该权限',
  `type` int(11) DEFAULT NULL COMMENT '1 - 后台；2 - 钉钉',
  `create_user` int(11) DEFAULT NULL,
  `create_user_name` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_user_name` varchar(200) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FK_RBAC_07` (`menu_id`),
  CONSTRAINT `FK_FK_RBAC_07` FOREIGN KEY (`menu_id`) REFERENCES `fr_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_menu_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for fr_resource
-- ----------------------------
DROP TABLE IF EXISTS `fr_resource`;
CREATE TABLE `fr_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(500) DEFAULT NULL,
  `name` varchar(500) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_user_name` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_user_name` varchar(200) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_resource
-- ----------------------------

-- ----------------------------
-- Table structure for fr_role
-- ----------------------------
DROP TABLE IF EXISTS `fr_role`;
CREATE TABLE `fr_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `node_no` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `is_initial` int(11) DEFAULT NULL COMMENT '1 - 系统初始化；1 - 以上表示用户创建',
  `create_user` int(11) DEFAULT NULL,
  `create_user_name` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_user_name` varchar(200) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_role
-- ----------------------------

-- ----------------------------
-- Table structure for fr_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `fr_role_menu`;
CREATE TABLE `fr_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FK_RBAC_05` (`menu_id`),
  KEY `FK_FK_RBAC_06` (`role_id`),
  CONSTRAINT `FK_FK_RBAC_05` FOREIGN KEY (`menu_id`) REFERENCES `fr_menu` (`id`),
  CONSTRAINT `FK_FK_RBAC_06` FOREIGN KEY (`role_id`) REFERENCES `fr_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for fr_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `fr_role_resource`;
CREATE TABLE `fr_role_resource` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FK_RBAC_08` (`resource_id`),
  KEY `FK_FK_RBAC_09` (`role_id`),
  CONSTRAINT `FK_FK_RBAC_08` FOREIGN KEY (`resource_id`) REFERENCES `fr_resource` (`id`),
  CONSTRAINT `FK_FK_RBAC_09` FOREIGN KEY (`role_id`) REFERENCES `fr_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for fr_user
-- ----------------------------
DROP TABLE IF EXISTS `fr_user`;
CREATE TABLE `fr_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(100) DEFAULT NULL COMMENT '登录时使用的名称',
  `user_name` varchar(200) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `head_img_url` varchar(200) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL COMMENT '1 - 男；2 - 女',
  `phone_number` varchar(32) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0 - 禁用；1 - 启用',
  `type` int(11) DEFAULT NULL COMMENT '1 - 系统管理员；2 - 普通用户',
  `bind_type` int(11) DEFAULT NULL COMMENT '1 - 不绑定；2 - ip；3 - mac',
  `ip_or_mac` char(10) DEFAULT NULL,
  `item1` varchar(200) DEFAULT NULL,
  `item2` varchar(200) DEFAULT NULL,
  `item3` varchar(200) DEFAULT NULL,
  `item4` varchar(200) DEFAULT NULL,
  `item5` varchar(200) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_user_name` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_user_name` varchar(200) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_user
-- ----------------------------
INSERT INTO `fr_user` VALUES ('2', 'admin', null, '$2a$10$7YCiuyMqgXeXxWlqUDlKFOIt7Qx5OYdFOUk0XQWSXGoj1zEcXKdkK', null, null, null, null, '1', null, '1', null, null, null, null, null, null, null, null, '2018-09-04 22:03:40', null, null, null, null);

-- ----------------------------
-- Table structure for fr_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `fr_user_dept`;
CREATE TABLE `fr_user_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FK_RBAC_01` (`dept_id`),
  KEY `FK_FK_RBAC_02` (`user_id`),
  CONSTRAINT `FK_FK_RBAC_01` FOREIGN KEY (`dept_id`) REFERENCES `fr_dept` (`id`),
  CONSTRAINT `FK_FK_RBAC_02` FOREIGN KEY (`user_id`) REFERENCES `fr_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_user_dept
-- ----------------------------

-- ----------------------------
-- Table structure for fr_user_role
-- ----------------------------
DROP TABLE IF EXISTS `fr_user_role`;
CREATE TABLE `fr_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FK_RBAC_03` (`role_id`),
  KEY `FK_FK_RBAC_04` (`user_id`),
  CONSTRAINT `FK_FK_RBAC_03` FOREIGN KEY (`role_id`) REFERENCES `fr_role` (`id`),
  CONSTRAINT `FK_FK_RBAC_04` FOREIGN KEY (`user_id`) REFERENCES `fr_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fr_user_role
-- ----------------------------
