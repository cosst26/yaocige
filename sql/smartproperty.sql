/*
 Navicat Premium Data Transfer

 Source Server         : 218.91.114.137
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : 218.91.114.137:3306
 Source Schema         : smartproperty

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 06/03/2025 02:56:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_announce
-- ----------------------------
DROP TABLE IF EXISTS `t_announce`;
CREATE TABLE `t_announce`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` bigint(20) NULL DEFAULT NULL COMMENT '业务状态',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '内容',
  `img_url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '图片',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '公告表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_announce
-- ----------------------------
INSERT INTO `t_announce` VALUES (4, '2024-10-25 16:49:51', '1712471522', NULL, NULL, b'0', NULL, NULL, '尊敬的业主们，我们激动地宣布，智慧物业管理系统已经成功升级并正式上线！这一系统的引入将为小区管理带来革命性的变化，为我们的生活带来更多便利与舒适。通过智慧物业管理系统，您可以实现在线缴费、报修、预约服务等功能，不再受限于传统的线下办理方式。此外，系统还将提供小区公告、安全警报等实时信息，让您随时了解小区动态。我们期待着您的积极参与与支持，让智慧物业成为我们共同打造的智慧社区的重要一环！', NULL, NULL, '迈向智慧物业，系统升级完成！');
INSERT INTO `t_announce` VALUES (5, '2024-10-25 16:49:51', '1712471522', NULL, NULL, b'0', NULL, NULL, '亲爱的业主们，为了让您更好地使用智慧物业管理系统，我们准备了以下使用说明：账号注册与登录时，请您使用手机号码注册账号，并在首次登录时设置密码。登录后，您可以根据自己的需求进行个人信息完善。在“缴费”模块中，您可以查看当前的物业费、水电费等账单，并选择在线支付，让账单支付更加便捷快速。如有小区内的设施损坏或需要维修，请在“报修”模块中提交报修申请，我们的工作人员将及时处理。您可以通过系统预约小区内的公共设施使用、保洁服务等，让您的生活更加便利。请保持系统消息通知开启，以便第一时间收到小区内的重要通知与警报信息。我们相信，随着大家的共同努力，智慧物业管理系统一定能够为我们的小区生活带来更多便利与快乐！感谢您的支持与配合！', NULL, NULL, '简单便捷，智慧生活从这里开始！');

-- ----------------------------
-- Table structure for t_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_bill`;
CREATE TABLE `t_bill`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态',
  `property_management_id` bigint(20) NULL DEFAULT NULL COMMENT '房源ID',
  `information_of_payee` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '收(付)款人信息',
  `accounts_receivable` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '应收(付)',
  `received` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '已收(付)',
  `to_be_collected` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '待收(付)',
  `payment_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '应收(付)日期',
  `complete_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '完成/作废日期',
  `bill_summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账单摘要',
  `billing_cycle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账单周期',
  `billing_notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账单备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '账单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_bill
-- ----------------------------
INSERT INTO `t_bill` VALUES (4, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, 2, 1, '萨伊', '2000', '1000', '1000', '2024-04-17T16:00:00.000Z', '2024-04-17T16:00:00.000Z', '1', '1', '1');
INSERT INTO `t_bill` VALUES (8, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, 1, 1, '智慧物业', '100', '0', '100', '2024-04-25T16:00:00.000Z', '2024-04-26T16:00:00.000Z', '无', '一个月', '二月电费');
INSERT INTO `t_bill` VALUES (9, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, 2, 1, '智慧物业', '50', '0', '50', '2024-04-25T16:00:00.000Z', '2024-04-25T16:00:00.000Z', '无', '一个月', '一月水费');
INSERT INTO `t_bill` VALUES (10, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, 1, 2, '智慧物业', '500', '0', '500', '2024-04-26T16:00:00.000Z', '2024-04-26T16:00:00.000Z', '三月水费', '一个月', '三月水费');
INSERT INTO `t_bill` VALUES (11, '2024-10-25 16:49:51', '1712471522', '2024-10-25 21:55:48', NULL, b'0', NULL, 2, 2, '滴滴物业', '100', '0', '100', '2024-05-22T16:00:00.000Z', '2024-05-22T16:00:00.000Z', '物业服务费', '一次', '空调维修');

-- ----------------------------
-- Table structure for t_chat
-- ----------------------------
DROP TABLE IF EXISTS `t_chat`;
CREATE TABLE `t_chat`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '内容',
  `sender_id` bigint(20) NULL DEFAULT NULL COMMENT '发送人id',
  `sender_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '发送人姓名',
  `sender_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '发送人头像',
  `recipient_id` bigint(20) NULL DEFAULT NULL COMMENT '接收人id',
  `recipient_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '接收人姓名',
  `recipient_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '接收人头像',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '聊天表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_chat
-- ----------------------------
INSERT INTO `t_chat` VALUES (107, '2024-10-25 16:49:51', '1712471523', NULL, NULL, b'0', NULL, NULL, '你好啊', 1712471523, 'user', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713082694511e3e968b1d3484c70a00a1c130472c91f.jpeg', 1712471522, 'admin', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/17130826872973efdbcc3af7f4c949c3b5433ed1521d5.jpeg');
INSERT INTO `t_chat` VALUES (108, '2024-10-25 16:49:51', '1712471522', NULL, NULL, b'0', NULL, NULL, '111', 1712471522, 'admin', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/17130826872973efdbcc3af7f4c949c3b5433ed1521d5.jpeg', 1712471523, 'user', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713082694511e3e968b1d3484c70a00a1c130472c91f.jpeg');
INSERT INTO `t_chat` VALUES (109, '2024-10-25 16:49:51', '1712471522', NULL, NULL, b'0', NULL, NULL, '阿松大阿萨', 1712471522, 'admin', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/17130826872973efdbcc3af7f4c949c3b5433ed1521d5.jpeg', 1712471523, 'user', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713082694511e3e968b1d3484c70a00a1c130472c91f.jpeg');
INSERT INTO `t_chat` VALUES (110, '2024-10-25 16:49:51', '1712471523', NULL, NULL, b'0', NULL, NULL, '你好呀', 1712471523, 'user', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713082694511e3e968b1d3484c70a00a1c130472c91f.jpeg', 1712471522, 'admin', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/17130826872973efdbcc3af7f4c949c3b5433ed1521d5.jpeg');
INSERT INTO `t_chat` VALUES (111, '2024-10-25 16:49:51', '1712471522', NULL, NULL, b'0', NULL, NULL, '你好呀', 1712471522, 'admin', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/17130826872973efdbcc3af7f4c949c3b5433ed1521d5.jpeg', 1712471523, 'user', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713082694511e3e968b1d3484c70a00a1c130472c91f.jpeg');
INSERT INTO `t_chat` VALUES (112, '2024-10-25 16:49:51', '1712471522', NULL, NULL, b'0', NULL, NULL, '你好', 1712471522, 'admin', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/17130826872973efdbcc3af7f4c949c3b5433ed1521d5.jpeg', 1712471523, 'user', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713082694511e3e968b1d3484c70a00a1c130472c91f.jpeg');
INSERT INTO `t_chat` VALUES (113, '2024-10-25 16:49:51', '1712471523', NULL, NULL, b'0', NULL, NULL, '你好', 1712471523, 'user', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713082694511e3e968b1d3484c70a00a1c130472c91f.jpeg', 1712471522, 'admin', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/17130826872973efdbcc3af7f4c949c3b5433ed1521d5.jpeg');
INSERT INTO `t_chat` VALUES (114, '2024-10-25 16:49:51', '1712471522', NULL, NULL, b'0', NULL, NULL, '你好', 1712471522, 'admin', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/17130826872973efdbcc3af7f4c949c3b5433ed1521d5.jpeg', 1712471523, 'user', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713082694511e3e968b1d3484c70a00a1c130472c91f.jpeg');

-- ----------------------------
-- Table structure for t_chat_list
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_list`;
CREATE TABLE `t_chat_list`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `recipient_id` bigint(20) NULL DEFAULT NULL COMMENT '接收人id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '聊天列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_chat_list
-- ----------------------------
INSERT INTO `t_chat_list` VALUES (13, '2024-10-25 16:49:51', '1712471522', NULL, NULL, b'0', NULL, NULL, 'user', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713082694511e3e968b1d3484c70a00a1c130472c91f.jpeg', 1712471523);
INSERT INTO `t_chat_list` VALUES (14, '2024-10-25 16:49:51', '1712471523', NULL, NULL, b'0', NULL, NULL, 'admin', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/17130826872973efdbcc3af7f4c949c3b5433ed1521d5.jpeg', 1712471522);

-- ----------------------------
-- Table structure for t_cleaning
-- ----------------------------
DROP TABLE IF EXISTS `t_cleaning`;
CREATE TABLE `t_cleaning`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态（1退房保洁;2日常保洁）',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `content` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '需求描述',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `image` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片路径',
  `address` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '保洁地址',
  `start_time` datetime NULL DEFAULT NULL COMMENT '期望上门时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '保洁表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_cleaning
-- ----------------------------
INSERT INTO `t_cleaning` VALUES (15, '1712471522', '2024-10-25 16:49:51', NULL, '2024-10-25 16:49:51', b'0', NULL, 1, '室内保洁', '打扫房子', '13899801450', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240418/1713417268487WechatIMG26.jpg', '幸福小区', '2024-04-08 00:00:00');
INSERT INTO `t_cleaning` VALUES (23, '1712471523', '2024-10-25 16:49:51', NULL, '2024-10-25 21:54:50', b'0', NULL, 1, '房间保洁', '窗户保洁', '13321607795', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240426/1714117938594保洁.webp', '二单元122', '2024-04-27 00:00:00');

-- ----------------------------
-- Table structure for t_complaint
-- ----------------------------
DROP TABLE IF EXISTS `t_complaint`;
CREATE TABLE `t_complaint`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态（1噪音问题;2保洁服务;3管家服务;4邻里问题）',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '投诉人',
  `content` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '内容',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `image` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片路径',
  `address` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '地址来源',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投诉表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_complaint
-- ----------------------------
INSERT INTO `t_complaint` VALUES (15, '1712471522', '2024-10-25 16:49:51', NULL, '2024-10-25 21:54:56', b'0', NULL, 1, '1', '1', '13899801450', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240408/1712511371667WechatIMG25.jpg', '1');
INSERT INTO `t_complaint` VALUES (19, '1712471523', '2024-10-25 16:49:51', NULL, '2024-10-25 16:49:51', b'0', NULL, 1, '卫生投诉', '垃圾乱堆', '13321607795', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240426/1714118021877垃圾.webp', '小区垃圾场');

-- ----------------------------
-- Table structure for t_contract
-- ----------------------------
DROP TABLE IF EXISTS `t_contract`;
CREATE TABLE `t_contract`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态',
  `user_id` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '预订人',
  `property_management_id` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '房源ID',
  `booking_type` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '预定类型',
  `signing_date` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '预定签约日期',
  `check_in_date` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '预计入住日期',
  `check_in_duration` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '预计入住时长',
  `payment_method` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '约定付款方式',
  `agreed_rent` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '约定租金',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '合约表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_contract
-- ----------------------------

-- ----------------------------
-- Table structure for t_cover
-- ----------------------------
DROP TABLE IF EXISTS `t_cover`;
CREATE TABLE `t_cover`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态',
  `image` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `remak` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '封面表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_cover
-- ----------------------------
INSERT INTO `t_cover` VALUES (3, NULL, '2024-10-25 16:49:51', NULL, NULL, b'0', NULL, NULL, 'http://localhost:7007/file/20211103/1635950269225.jpg', 'banner1');
INSERT INTO `t_cover` VALUES (4, NULL, '2024-10-25 16:49:51', NULL, NULL, b'0', NULL, NULL, 'http://localhost:7007/file/20211103/1635950357660.png', 'banner2');
INSERT INTO `t_cover` VALUES (5, NULL, '2024-10-25 16:49:51', NULL, NULL, b'0', NULL, NULL, 'http://localhost:7007/file/20211103/1635950384427.png', 'banner3');

-- ----------------------------
-- Table structure for t_door_lock
-- ----------------------------
DROP TABLE IF EXISTS `t_door_lock`;
CREATE TABLE `t_door_lock`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态（1离线;2在线）',
  `door_lock` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '门锁品牌',
  `electricity_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '门锁电量',
  `coding` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '设备序列号/编码',
  `property_management_id` bigint(20) NULL DEFAULT NULL COMMENT '房源ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '门锁智能表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_door_lock
-- ----------------------------
INSERT INTO `t_door_lock` VALUES (2, '2024-10-25 16:49:51', NULL, NULL, NULL, b'0', NULL, NULL, '大王门锁', '68', '00010001', 1);
INSERT INTO `t_door_lock` VALUES (3, '2024-10-25 16:49:51', '1712471522', NULL, NULL, b'0', NULL, NULL, '小王门锁', '66', '10000012', 2);

-- ----------------------------
-- Table structure for t_electricity_meter
-- ----------------------------
DROP TABLE IF EXISTS `t_electricity_meter`;
CREATE TABLE `t_electricity_meter`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态（1供电;2断电;3离线;4异常）',
  `door_lock` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '品牌',
  `coding` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '设备序列号/编码',
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '价格',
  `electricity_consumption` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '当前电量',
  `electricity_volume` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '剩余电量',
  `property_management_id` bigint(20) NULL DEFAULT NULL COMMENT '房源ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '智能电表表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_electricity_meter
-- ----------------------------
INSERT INTO `t_electricity_meter` VALUES (1, '2024-10-25 16:49:51', '1712471522', NULL, NULL, b'0', NULL, NULL, '1', '2', '3', '4', '5', 1);

-- ----------------------------
-- Table structure for t_home_service
-- ----------------------------
DROP TABLE IF EXISTS `t_home_service`;
CREATE TABLE `t_home_service`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态(1家电;2其他;3疏通)',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `content` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '故障描述',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `image` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片路径',
  `address` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '地址',
  `start_time` datetime NULL DEFAULT NULL COMMENT '期望上门时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '维修表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_home_service
-- ----------------------------
INSERT INTO `t_home_service` VALUES (16, '1712471523', '2024-10-25 16:49:51', NULL, '2024-10-25 16:49:51', b'0', NULL, 1, '维修空调', '维修空调', '13324566789', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240418/1713417245525WechatIMG3.jpg', '阳光小区', '2024-04-18 00:00:00');
INSERT INTO `t_home_service` VALUES (17, '1712471523', '2024-10-25 16:49:51', NULL, '2024-10-25 21:55:39', b'0', NULL, 1, '维修门锁', '维修门锁', '133346211325', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240418/1713417599061WechatIMG3.jpg', '光明小区', '2024-10-25 00:00:00');
INSERT INTO `t_home_service` VALUES (22, '1712471523', '2024-10-25 16:49:51', NULL, '2024-10-25 16:49:51', b'0', NULL, 1, '空调维修', '空调坏了', '13321607795', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240426/1714117014355空调维修.jpg', '一单元204', '2024-04-26 00:00:00');

-- ----------------------------
-- Table structure for t_inspection_tasks
-- ----------------------------
DROP TABLE IF EXISTS `t_inspection_tasks`;
CREATE TABLE `t_inspection_tasks`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态',
  `inspection_plan` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '巡检计划',
  `property_management_id` bigint(20) NULL DEFAULT NULL COMMENT '房源ID',
  `set_inspection_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规定巡检时间',
  `completion_inspection_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '完成巡检时间',
  `inspection_items` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '巡检项',
  `feedback` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '反馈',
  `inspection_results` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '巡检结果',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '巡检任务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_inspection_tasks
-- ----------------------------

-- ----------------------------
-- Table structure for t_meter_reading_electricity
-- ----------------------------
DROP TABLE IF EXISTS `t_meter_reading_electricity`;
CREATE TABLE `t_meter_reading_electricity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态',
  `property_management_id` bigint(20) NULL DEFAULT NULL COMMENT '房源ID',
  `electricity_meter_id` bigint(20) NULL DEFAULT NULL COMMENT '电表ID',
  `billing_notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '抄表备注',
  `base_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '底数',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单价',
  `water_volume` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '本次抄表共用电量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '抄电表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_meter_reading_electricity
-- ----------------------------
INSERT INTO `t_meter_reading_electricity` VALUES (2, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, 1, 1, '1', '1', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713081820787帐篷.png', '1', '1');
INSERT INTO `t_meter_reading_electricity` VALUES (3, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, 1, 1, '1', '106', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713084180861地铁站台广告位的墙贴和柱贴广告6.jpg', '3', '9');
INSERT INTO `t_meter_reading_electricity` VALUES (7, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, 1, 1, '一月电表', '1000', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240426/1714117442243电表.jpg', '1', '100');
INSERT INTO `t_meter_reading_electricity` VALUES (8, '2024-10-25 21:55:07', '1712471522', NULL, NULL, b'0', NULL, NULL, 1, 1, '1', '1', '', '1', '1');

-- ----------------------------
-- Table structure for t_meter_reading_water
-- ----------------------------
DROP TABLE IF EXISTS `t_meter_reading_water`;
CREATE TABLE `t_meter_reading_water`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态',
  `property_management_id` bigint(20) NULL DEFAULT NULL COMMENT '房源ID',
  `water_meter_id` bigint(20) NULL DEFAULT NULL COMMENT '水表ID',
  `billing_notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '抄表备注',
  `base_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '底数',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单价',
  `water_volume` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '本次抄表共用水量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '抄水表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_meter_reading_water
-- ----------------------------
INSERT INTO `t_meter_reading_water` VALUES (3, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, 1, 1, '2', '103', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713084222821636917161325891217835.jpg', '2', '20');
INSERT INTO `t_meter_reading_water` VALUES (8, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, 1, 1, '一月水表', '200', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240425/1714032943531WechatIMG3.jpg', '1.9', '300');
INSERT INTO `t_meter_reading_water` VALUES (9, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, 1, 1, '一月水表', '50', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240426/1714118260668水表.webp', '1', '50');
INSERT INTO `t_meter_reading_water` VALUES (10, '2024-10-25 21:45:36', '1712471522', NULL, NULL, b'0', NULL, NULL, 1, 1, '1', '1', NULL, '2', '1');

-- ----------------------------
-- Table structure for t_owner_pool
-- ----------------------------
DROP TABLE IF EXISTS `t_owner_pool`;
CREATE TABLE `t_owner_pool`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `rental_location` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '房子位置',
  `acceptable_price` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '价格/月',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '业主池表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_owner_pool
-- ----------------------------
INSERT INTO `t_owner_pool` VALUES (1, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, '陈先生', '16628533247', '8号楼103', '4000');

-- ----------------------------
-- Table structure for t_property_management
-- ----------------------------
DROP TABLE IF EXISTS `t_property_management`;
CREATE TABLE `t_property_management`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态（1整租;2独栋;3合租）',
  `property_information` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '房源信息',
  `vacant_days` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '空置天数',
  `expiration_date` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '业主到期日',
  `price` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '价格(元/月)',
  `unit_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '户型',
  `area` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '面积',
  `facing` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '朝向',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '房源管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_property_management
-- ----------------------------
INSERT INTO `t_property_management` VALUES (1, '2024-10-25 16:49:51', '1712471522', '2024-10-25 21:54:35', NULL, b'0', NULL, NULL, '阳光小区一室一厅', '20', '2024-10-26', '4000', '一室一厅', '58', '南');
INSERT INTO `t_property_management` VALUES (2, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, '阳光小区二室一厅', '3', '2024-04-10', '6000', '二室一厅', '110', '南');

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态是否默认(1是)',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `link_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `image_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图片url',
  `enable_flag` bit(1) NULL DEFAULT NULL COMMENT '是否禁用（0启用1禁用）',
  `sort_id` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES (1, NULL, NULL, NULL, NULL, b'0', NULL, NULL, 0, '用户删除', '/user/delete', NULL, NULL, NULL);
INSERT INTO `t_sys_menu` VALUES (2, NULL, NULL, NULL, NULL, b'0', NULL, NULL, 1, '用户删除', '/user/delete', NULL, NULL, NULL);
INSERT INTO `t_sys_menu` VALUES (3, NULL, NULL, NULL, NULL, b'0', NULL, NULL, 0, '用户修改', '/user/update', NULL, NULL, NULL);
INSERT INTO `t_sys_menu` VALUES (4, NULL, NULL, NULL, NULL, b'0', NULL, NULL, 0, '用户查询', '/user/findByModal', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态是否默认(1是)',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `oper_role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作功能权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (0, NULL, NULL, NULL, NULL, b'0', NULL, NULL, '管理员', NULL, NULL);
INSERT INTO `t_sys_role` VALUES (1, NULL, NULL, NULL, NULL, b'0', NULL, NULL, '用户', NULL, NULL);

-- ----------------------------
-- Table structure for t_tenant_pool
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_pool`;
CREATE TABLE `t_tenant_pool`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `rental_location` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '意向租房位置',
  `unit_type` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '需求户型',
  `acceptable_price` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '可接受价格范围',
  `check_in_date` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '预计入住日期',
  `check_in_period` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '预计入住周期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '租客池表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_tenant_pool
-- ----------------------------
INSERT INTO `t_tenant_pool` VALUES (1, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, '小宋', '16625877456', '阳关小区', '一室一厅', '4000-6000', '2024/4/20', '1周内');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态是否默认(1是)',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '(0管理员;1用户)',
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '身份证',
  `birthday` datetime NULL DEFAULT NULL COMMENT '出生日期',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别(0女;1男)',
  `user_desc` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '用户描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1712471526 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1712471522, '2024-10-25 16:49:51', NULL, '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, 'admin', '123456', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/17130826872973efdbcc3af7f4c949c3b5433ed1521d5.jpeg', '10654126843@qq.com', '18454169854', 0, '372930155478201540', '2021-04-27 07:50:59', 1, NULL);
INSERT INTO `t_user` VALUES (1712471523, '2024-10-25 16:49:51', NULL, '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, 'user', '123456', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240414/1713082694511e3e968b1d3484c70a00a1c130472c91f.jpeg', '16843@qq.com', '16621385541', 1, '372930199802410140', '2023-04-01 16:09:29', 0, NULL);
INSERT INTO `t_user` VALUES (1712471525, '2024-10-25 16:49:51', NULL, '2024-10-25 16:49:51', NULL, b'0', NULL, 1, '测试', '111111', 'https://mingzhe-file.oss-cn-hangzhou.aliyuncs.com/20240425/1714032647509WechatIMG3.jpg', '947050757@qq.com', '13321607795', 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_water_meter
-- ----------------------------
DROP TABLE IF EXISTS `t_water_meter`;
CREATE TABLE `t_water_meter`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除(0false未删除 1true已删除)',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `status` int(11) NULL DEFAULT NULL COMMENT '业务状态（1供电;2断电;3离线;4异常）',
  `door_lock` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '品牌',
  `coding` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '设备序列号/编码',
  `water_consumption` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '当前用水量',
  `water_volume` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '剩余水量',
  `property_management_id` bigint(20) NULL DEFAULT NULL COMMENT '房源ID',
  `state` int(11) NULL DEFAULT NULL COMMENT '1热水;2冷水',
  `price` decimal(11, 2) NULL DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ID`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '智能水表表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_water_meter
-- ----------------------------
INSERT INTO `t_water_meter` VALUES (1, '2024-10-25 16:49:51', '1712471522', '2024-10-25 16:49:51', NULL, b'0', NULL, NULL, '1', '2', '3', '4', 1, NULL, 12.00);

SET FOREIGN_KEY_CHECKS = 1;
