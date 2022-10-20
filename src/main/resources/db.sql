-- ----------------------------
-- 创建用户表
-- ----------------------------
DROP TABLE IF EXISTS `ydl_user`;
CREATE TABLE `ydl_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30)  NOT NULL COMMENT '用户昵称',
  `email` varchar(50)  NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11)  NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1)  NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100)  NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100)  NULL DEFAULT '' COMMENT '密码',
  `status` char(1)  NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1)  NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128)  NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64)  NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表';

-- ----------------------------
-- 增加几条用户信息
-- ----------------------------
INSERT INTO `ydl_user` VALUES (1, 'ydl_admin', '管理员', '253546544@qq.com', '18888888888', '0', '', 'xxxxxx', '0', '0', '10.25.245.45', '2022-01-23 15:50:27', 'admin', '2022-01-23 15:50:48', 'ydl', '2022-01-26 15:50:53');





-- ----------------------------
-- Table structure for ydl_role
-- ----------------------------
DROP TABLE IF EXISTS `ydl_role`;
CREATE TABLE `ydl_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30)  NOT NULL COMMENT '角色名称',
  `role_tag` varchar(30)  NULL DEFAULT NULL COMMENT '角色标签',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1)  NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1)  NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64)  NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表' ;

-- ----------------------------
-- Records of ydl_role
-- ----------------------------
INSERT INTO `ydl_role` VALUES (1, '管理员', NULL, 1, '0', '0', 'admin', '2022-01-28 15:57:57', 'admin', '2022-01-29 15:58:04');










-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ydl_user_role`;
CREATE TABLE `ydl_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `ydl_user_role` VALUES (1, 1);







-- ----------------------------
-- Table structure for ydl_menu
-- ----------------------------
DROP TABLE IF EXISTS `ydl_menu`;
CREATE TABLE `ydl_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50)  NOT NULL COMMENT '菜单名称',
  `perms` varchar(50)  NULL DEFAULT NULL COMMENT '权限标识',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200)  NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255)  NULL DEFAULT NULL COMMENT '组件路径',
  `menu_type` char(1)  NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 A按钮）',
  `visible` char(1)  NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `icon` varchar(100)  NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64)  NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ;

-- ----------------------------
-- Records of ydl_menu
-- ----------------------------
INSERT INTO `ydl_menu` VALUES (1, '系统管理', 'system', 0, 0, 'system', 'null', '1', '0', '#', '', NULL, '', NULL);
INSERT INTO `ydl_menu` VALUES (2, '用户管理', 'system:user', 1, 1, 'system/user/index', NULL, '', '0', '#', '', NULL, '', NULL);
INSERT INTO `ydl_menu` VALUES (3, '角色管理', 'system:role', 1, 2, 'system/role/index', NULL, '', '0', '#', '', NULL, '', NULL);
INSERT INTO `ydl_menu` VALUES (4, '菜单管理', 'system:menu', 1, 3, 'system/menu/index', NULL, '', '0', '#', '', NULL, '', NULL);


INSERT INTO `ydl_menu` VALUES (NULL, '用户管理', 'insert', '1', '3', '/system-management/user-management', NULL, 'A', '0', '#', '', NULL, '', NULL);
INSERT INTO `ydl_menu` VALUES (NULL, '用户管理', 'update', '1', '3', '/system-management/user-management', NULL, 'A', '0', '#', '', NULL, '', NULL);
INSERT INTO `ydl_menu` VALUES (NULL, '用户管理', 'delete', '1', '3', '/system-management/user-management', NULL, 'A', '0', '#', '', NULL, '', NULL);
INSERT INTO `ydl_menu` VALUES (NULL, '用户管理', 'select', '1', '3', '/system-management/user-management', NULL, 'A', '0', '#', '', NULL, '', NULL);




-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ydl_role_menu`;
CREATE TABLE `ydl_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `ydl_role_menu` VALUES (1, 1);
INSERT INTO `ydl_role_menu` VALUES (1, 2);
INSERT INTO `ydl_role_menu` VALUES (1, 3);
INSERT INTO `ydl_role_menu` VALUES (1, 4);



-- ----------------------------
-- log table 日志表
-- ----------------------------

DROP TABLE IF EXISTS `ydl_oper_log`;
CREATE TABLE `ydl_oper_log` (
  `oper_id` int NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `business_module` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '业务模块',
  `business_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '业务类型',
  `business_describe` varchar(255) DEFAULT NULL COMMENT '具体描述',
  `method` varchar(255) DEFAULT NULL COMMENT 'api方法',
  `request_method` varchar(255) DEFAULT NULL COMMENT '请求方式',
  `oper_name` varchar(255) DEFAULT NULL COMMENT '操作人员',
  `oper_url` varchar(255) DEFAULT NULL COMMENT '请求url',
  `oper_ip` varchar(255) DEFAULT NULL COMMENT '操作地址',
  `status` int DEFAULT NULL COMMENT '操作状态',
  `errorMsg` varchar(255) DEFAULT NULL COMMENT '错误消息',
  `operTime` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志'



-- ----------------------------
-- 创建用户登录日志表
-- ----------------------------
DROP TABLE IF EXISTS `ydl_user_login_log`;
CREATE TABLE `ydl_user_login_log`  (
  `login_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `token` varchar(100) NOT NULL COMMENT '登录凭证',
  `username` varchar(30) NOT NULL COMMENT '用户账号',
  `login_ip` varchar(128)  NULL DEFAULT '' COMMENT '登录IP',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `login_localtion` varchar(50)  NULL DEFAULT '' COMMENT '用户登录地址',
  `browser` varchar(50)  NULL DEFAULT '' COMMENT '浏览器类型',
	`system_os` varchar(50)  NULL DEFAULT '' COMMENT '操作系统',
  PRIMARY KEY (`login_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户登录表';





-- ----------------------------
-- 创建附件表
-- ----------------------------

DROP TABLE IF EXISTS `ydl_attachment`;
CREATE TABLE `ydl_attachment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` varchar(100) NOT NULL COMMENT '访问地址',
  `create_by_user_id` int DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统附件表';




-- ----------------------------
-- 创建系统字典表
-- ----------------------------
DROP TABLE IF EXISTS `ydl_sys_dict`;
CREATE TABLE `ydl_sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_code` varchar(255) NOT NULL COMMENT '数据类型编码',
  `dict_name` varchar(255) NOT NULL COMMENT '数据类型名称',
  `dict_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `sort` int(5) DEFAULT '1' COMMENT '排序',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_by_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_by_user_id` int(11) DEFAULT NULL COMMENT '修改人id',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dict_code` (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统字典类型表';

