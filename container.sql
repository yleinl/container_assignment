/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50743
 Source Host           : localhost:3306
 Source Schema         : container

 Target Server Type    : MySQL
 Target Server Version : 50743
 File Encoding         : 65001

 Date: 08/02/2024 21:56:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT 'incremental course id\r\n',
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'course name\r\n',
  `lecturer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'lecturer name\r\n',
  `study_guide` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `username`(`course_name`(191)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1024859055654637579 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1024859055654637575, 'Software Containerization', 'dr. I. Malavolta', 'This course covers the principles and practices of software containerization, including Docker and Kubernetes. Students will learn how to deploy, manage, and scale containerized applications in distributed environments.');
INSERT INTO `course` VALUES (1024859055654637576, 'Distributed Systems', 'ir. J.J.R. Donkervliet MSc', 'In this course, students will explore the design, implementation, and operation of distributed systems. Topics include distributed algorithms, consensus protocols, fault tolerance, and scalability.');
INSERT INTO `course` VALUES (1024859055654637577, 'Parallel Programming Practical', 'Dr. prof. dr. ir. H.E. Bal', 'This practical course focuses on parallel programming techniques for multi-core processors and distributed systems. Students will gain hands-on experience with parallel programming frameworks and tools.');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT 'incremental id\r\n',
  `vunetid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'user name',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'password',
  `courses` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'selected course',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`vunetid`(191)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1024859055654637575 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'yle215', '123456', NULL, 'y.lei2@student.vu.nl', 'Yitao Lei');

SET FOREIGN_KEY_CHECKS = 1;
