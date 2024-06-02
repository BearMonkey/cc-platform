/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : cc-platform

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 02/06/2024 11:47:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cc_platform_user
-- ----------------------------
DROP TABLE IF EXISTS `cc_platform_user`;
CREATE TABLE `cc_platform_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `birth` date NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cc_platform_user
-- ----------------------------
INSERT INTO `cc_platform_user` VALUES (12, 'monkey', '2024-05-30', '1', '2024-05-31 11:49:15', 'System', '2024-05-31 14:05:51', 'System', '0');
INSERT INTO `cc_platform_user` VALUES (13, 'monkey', '2024-05-31', '1', '2024-05-31 11:51:18', 'System', NULL, NULL, '0');
INSERT INTO `cc_platform_user` VALUES (14, 'monkey', '2024-05-31', '1', '2024-05-31 11:56:57', 'System', NULL, NULL, '0');
INSERT INTO `cc_platform_user` VALUES (15, 'monkey', '2024-05-31', '1', '2024-05-31 11:59:28', 'System', NULL, NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
