/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50735
Source Host           : localhost:3306
Source Database       : kaori

Target Server Type    : MYSQL
Target Server Version : 50735
File Encoding         : 65001

Date: 2022-01-20 09:42:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` varchar(50) NOT NULL,
  `param_key` varchar(255) DEFAULT NULL,
  `param_value` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(50) NOT NULL,
  `parent_id` varchar(50) DEFAULT NULL,
  `org_id` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_file_list
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_list`;
CREATE TABLE `sys_file_list` (
  `id` varchar(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `file_uuid_name` varchar(255) DEFAULT NULL,
  `file_per_path` varchar(255) DEFAULT NULL,
  `file_last_path` varchar(255) DEFAULT NULL,
  `file_size` double DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_file_list
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` varchar(50) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('0597ac21fd048f4cfc4097ab311060e0', 'admin', null, '2022-01-12 09:39:46', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('05bbac1ba0df60d4a9430edda15d80fc', 'admin', null, '2021-11-10 13:57:48', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('1', null, null, '2021-11-05 15:45:06', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('179f1c994a6d6f697d33c3fc0e3aa521', 'admin', null, '2022-01-12 10:09:56', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('1a69d3f07bdee3c5c81aafd5a9a086ac', 'admin', null, '2022-01-12 09:49:08', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('20f09437d9618f41f81ec3b7f286ecb2', 'admin', null, '2022-01-12 10:01:52', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('3ca59c287fc0c2bfdb95912eb18d4471', 'admin', null, '2021-11-11 13:42:46', '192.168.28.192');
INSERT INTO `sys_login_log` VALUES ('3e0f7d4e862c67cd5fd8976f22351cf9', 'admin', null, '2022-01-12 09:50:21', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('5457adfb9b20996a85efc9f93ad81260', 'admin', null, '2021-11-10 14:09:50', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('5ee55a21045fbdacb8f5e10b36393a74', 'admin', null, '2022-01-12 10:55:26', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES ('5f015f9a5a819efb4c39eea956393b7d', 'admin', null, '2022-01-12 09:56:23', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('5f8297d0c5f730f428e96bbf04ccce56', 'admin', null, '2021-11-11 13:19:14', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('620bf97fdc4142ef9b3a4f54ce7fde2f', 'admin', null, '2021-11-10 13:46:17', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('633c67735f8fd2596ed423c4eba3d3cb', 'admin', null, '2021-11-10 14:03:11', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('78f6c35473b8e5706946501386960403', 'admin', null, '2022-01-12 09:47:47', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('7d7b0505f4efbaeb4b5d3b746a9240d6', 'admin', null, '2022-01-12 09:41:38', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('838ae3094285e58713d3aa8a27f029f4', 'admin', null, '2021-11-10 14:12:55', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('853e98043bd18fb709d803384a4031b5', 'admin', null, '2021-11-10 14:06:39', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('920b9d09465990eca6e02c0f6f3dab5f', 'admin', null, '2022-01-06 17:03:46', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('93a6a10a7de5106d7c5893000d9af202', 'admin', null, '2022-01-12 09:41:11', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('99fd468f2e805e5080a8f6b45b859fad', 'admin', null, '2021-11-10 16:04:18', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('ae2008086b947d1f9163edd173a06c5e', 'admin', null, '2022-01-12 09:46:20', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('b391596c7d22af4f9d59aa7e6a488351', 'admin', null, '2022-01-12 09:59:34', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('c2f532d43cfc10268a26a61d9a24f5a1', 'admin', null, '2022-01-12 09:45:13', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('c86baa4c4bd6618fac44a79c6ac5a6e3', 'admin', null, '2022-01-12 10:00:52', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('cfc353bcdcf1583c315dcc4bd37622c9', 'admin', null, '2021-11-10 14:03:41', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('d284d1883f3cfd0e3566eb6aebaeb7f4', 'admin', null, '2022-01-12 10:05:07', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('dbe9ca6be6f74d67624bdb2b1d4bd290', 'admin', null, '2021-11-11 13:17:00', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('e554737add54c44cd27b1c8af0d22b61', 'admin', null, '2021-11-11 15:00:51', '192.168.28.192');
INSERT INTO `sys_login_log` VALUES ('f63a1be6944e76c8153a4eeede8d6293', 'admin', null, '2022-01-12 10:11:15', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('fa53104e0bf9c9a5a68f7c5f58a08b8f', 'admin', null, '2022-01-12 10:14:31', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '1', '', 'modules/sys/config', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('2', '1', '查看', null, 'sys:config:list,sys:config:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('3', '1', '新增', null, 'sys:config:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('4', '1', '修改', null, 'sys:config:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('5', '1', '删除', null, 'sys:config:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('6', '1', '', 'modules/sys/userrole', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:userrole:list,sys:userrole:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:userrole:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:userrole:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:userrole:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('11', '1', '', 'modules/sys/user', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('12', '11', '查看', null, 'sys:user:list,sys:user:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('13', '11', '新增', null, 'sys:user:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('14', '11', '修改', null, 'sys:user:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('15', '11', '删除', null, 'sys:user:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('16', '1', '', 'modules/sys/rolemenu', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('17', '16', '查看', null, 'sys:rolemenu:list,sys:rolemenu:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('18', '16', '新增', null, 'sys:rolemenu:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('19', '16', '修改', null, 'sys:rolemenu:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('20', '16', '删除', null, 'sys:rolemenu:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('21', '1', '', 'modules/sys/roledept', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('22', '21', '查看', null, 'sys:roledept:list,sys:roledept:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('23', '21', '新增', null, 'sys:roledept:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('24', '21', '修改', null, 'sys:roledept:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('25', '21', '删除', null, 'sys:roledept:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('26', '1', '', 'modules/sys/role', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('27', '26', '查看', null, 'sys:role:list,sys:role:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('28', '26', '新增', null, 'sys:role:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('29', '26', '修改', null, 'sys:role:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('30', '26', '删除', null, 'sys:role:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('31', '1', '菜单管理', 'modules/sys/menu', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('32', '31', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'sys:menu:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', null, 'sys:menu:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'sys:menu:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('36', '1', '', 'modules/sys/loginlog', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('37', '36', '查看', null, 'sys:loginlog:list,sys:loginlog:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('38', '36', '新增', null, 'sys:loginlog:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('39', '36', '修改', null, 'sys:loginlog:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('40', '36', '删除', null, 'sys:loginlog:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('41', '1', '', 'modules/sys/log', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('42', '41', '查看', null, 'sys:log:list,sys:log:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('43', '41', '新增', null, 'sys:log:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('44', '41', '修改', null, 'sys:log:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('45', '41', '删除', null, 'sys:log:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('46', '1', '', 'modules/sys/filelist', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('47', '46', '查看', null, 'sys:filelist:list,sys:filelist:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('48', '46', '新增', null, 'sys:filelist:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('49', '46', '修改', null, 'sys:filelist:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('50', '46', '删除', null, 'sys:filelist:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('51', '1', '', 'modules/sys/dict', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('52', '51', '查看', null, 'sys:dict:list,sys:dict:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('53', '51', '新增', null, 'sys:dict:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('54', '51', '修改', null, 'sys:dict:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('55', '51', '删除', null, 'sys:dict:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('56', '1', '', 'modules/sys/dept', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('57', '56', '查看', null, 'sys:dept:list,sys:dept:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('58', '56', '新增', null, 'sys:dept:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('59', '56', '修改', null, 'sys:dept:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('60', '56', '删除', null, 'sys:dept:delete', '2', null, '6');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(50) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` varchar(50) DEFAULT NULL,
  `role_id` varchar(50) NOT NULL,
  `dept_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(50) NOT NULL,
  `role_id` varchar(50) DEFAULT NULL,
  `menu_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `dept_id` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);
INSERT INTO `sys_user` VALUES ('10', 'admin2', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员2', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);
INSERT INTO `sys_user` VALUES ('11', 'admin2', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员2', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);
INSERT INTO `sys_user` VALUES ('2', 'admin2', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员2', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);
INSERT INTO `sys_user` VALUES ('3', 'admin2', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员2', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);
INSERT INTO `sys_user` VALUES ('4', 'admin2', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员2', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);
INSERT INTO `sys_user` VALUES ('5', 'admin2', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员2', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);
INSERT INTO `sys_user` VALUES ('6', 'admin2', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员2', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);
INSERT INTO `sys_user` VALUES ('7', 'admin2', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员2', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);
INSERT INTO `sys_user` VALUES ('8', 'admin2', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员2', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);
INSERT INTO `sys_user` VALUES ('9', 'admin2', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '管理员2', 'YzcmCZNvbXocrsz9dm8e', null, null, '1', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `role_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');

-- ----------------------------
-- Procedure structure for proc_initData
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_initData`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_initData`()
BEGIN
DECLARE i INT DEFAULT 1;
WHILE i<=10000 DO
    INSERT INTO test(a,b,c,d) VALUES(i,i,i,i);
    SET i = i+1;
END WHILE;
END
;;
DELIMITER ;
