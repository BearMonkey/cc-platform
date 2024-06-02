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

 Date: 02/06/2024 11:48:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cc_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `cc_warehouse`;
CREATE TABLE `cc_warehouse`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `warehouse_country_id` int NULL DEFAULT NULL COMMENT '国家id',
  `warehouse_province_id` int NULL DEFAULT NULL COMMENT '省份id',
  `warehouse_city_id` int NULL DEFAULT NULL COMMENT '城市id',
  `warehouse_area_id` int NULL DEFAULT NULL COMMENT '地区id',
  `warehouse_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  `del_flag` int NOT NULL COMMENT '删除标记',
  `create_time` datetime NULL DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cc_warehouse
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
