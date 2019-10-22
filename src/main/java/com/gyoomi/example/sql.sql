/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 22/10/2019 17:41:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adam_interface_client
-- ----------------------------
DROP TABLE IF EXISTS `adam_interface_client`;
CREATE TABLE `adam_interface_client`  (
                                          `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                          `client` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                          `ak` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                          `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                          `create_date` datetime(0) DEFAULT NULL,
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adam_interface_client
-- ----------------------------
INSERT INTO `adam_interface_client` VALUES ('3', 'web端', 'stwreewrwerw', '1', '2019-10-22 16:36:34');

-- ----------------------------
-- Table structure for adam_user
-- ----------------------------
DROP TABLE IF EXISTS `adam_user`;
CREATE TABLE `adam_user`  (
                              `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                              `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `gender` int(255) DEFAULT NULL,
                              `status` int(255) DEFAULT NULL,
                              `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `create_date` datetime(0) DEFAULT NULL,
                              `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `update_date` datetime(0) DEFAULT NULL,
                              `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `phone_number` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adam_user
-- ----------------------------
INSERT INTO `adam_user` VALUES ('1186483742505680898', 'admin', NULL, '$2a$10$sx94I9cQN3j7H3UjZxjDDuFB5SxQcmyV/jG2IWmh0MfAihyo4l9La', NULL, NULL, 1, NULL, '2019-10-22 11:25:43', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for example
-- ----------------------------
DROP TABLE IF EXISTS `example`;
CREATE TABLE `example`  (
                            `id` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                            `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                            `total` int(255) DEFAULT NULL,
                            `create_date` datetime(0) DEFAULT NULL,
                            `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
