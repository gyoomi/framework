
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `user_code` varchar(10) DEFAULT NULL,
  `login_name` varchar(200) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL COMMENT '1 - 男；2 - 女',
  `age` tinyint(4) DEFAULT NULL,
  `year_salary` decimal(10,2) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_user_id` int(11) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `remark` longtext,
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '1 - 删除；0 - 未删除；默认 - 0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('2', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('3', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('4', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('5', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('6', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('7', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('8', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('9', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('10', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('11', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('12', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('13', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('14', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('15', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('16', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('17', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('18', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('19', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('20', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('21', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('22', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('23', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('24', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('25', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888888.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('26', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888899.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('27', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888899.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('28', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888899.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('29', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888899.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('30', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888899.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('31', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888899.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('32', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888899.00', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('33', '周杰伦', '21124214234', '100324', 'zhoujielun', '1', '28', '88888899.00', null, null, null, null, null, null);
