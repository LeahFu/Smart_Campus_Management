/*
Navicat MySQL Data Transfer

Source Database: Smart_Campus_Management_System
Date: 2023-7-7 10:01:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_course
-- ----------------------------
DROP TABLE IF EXISTS `s_course`;
CREATE TABLE `s_course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) NOT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `course_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Course Information Table';

-- ----------------------------
-- Records of s_course
-- ----------------------------
INSERT INTO `s_course` VALUES ('1', '1', '2023-05-18 10:59:59.497000', 'WEB PROGRAMMING', '1', '2023-05-18 10:59:59.498000', 'WEB PROGRAMMING', 'c001');
INSERT INTO `s_course` VALUES ('2', '1', '2023-05-18 11:00:31.283000', 'PROGRAMMING ALGORITHMS', '1', '2023-05-18 11:00:31.284000', 'PROGRAMMING ALGORITHMS', 'c002');
INSERT INTO `s_course` VALUES ('3', '1', '2023-05-18 11:00:50.909000', 'SYSTEM DEVELOPMENT', '1', '2023-05-18 11:00:50.910000', 'SYSTEM DEVELOPMENT', 'c003');
INSERT INTO `s_course` VALUES ('4', '1', '2023-05-18 11:01:17.114000', 'WEB SERVICES', '1', '2023-05-18 11:01:17.115000', 'WEB SERVICES', 'c004');
INSERT INTO `s_course` VALUES ('5', '1', '2023-05-18 11:01:37.747000', 'APPLICATION DEVELOPMENT', '1', '2023-05-18 11:01:37.748000', 'APPLICATION DEVELOPMENT', 'c005');
INSERT INTO `s_course` VALUES ('6', '1', '2023-05-18 11:01:58.498000', 'DATABASE', '1', '2023-05-18 11:01:58.498000', 'DATABASE', 'c006');
INSERT INTO `s_course` VALUES ('7', '1', '2023-05-18 11:02:11.274000', 'TRANSACTIONAL WEB APP', '1', '2023-05-18 11:02:11.275000', 'TRANSACTIONAL WEB APP', 'c007');
INSERT INTO `s_course` VALUES ('8', '1', '2023-05-18 11:02:34.564000', 'CYBER SECURITY', '1', '2023-05-18 11:02:34.564000', 'CYBER SECURITY', 'c008');
INSERT INTO `s_course` VALUES ('9', '1', '2023-05-18 11:03:01.895000', 'PROGRAMMING CONCEPTS', '1', '2023-05-18 11:03:01.895000', 'PROGRAMMING CONCEPTS', 'c009');
INSERT INTO `s_course` VALUES ('10', '1', '2023-05-18 11:04:31.361000', 'PYTHON', '1', '2023-05-18 11:04:31.361000', 'PYTHON', 'c010');
INSERT INTO `s_course` VALUES ('11', '1', '2023-05-18 11:04:53.763000', 'OPERATING SYSTEMS', '1', '2023-05-18 11:04:53.763000', 'OPERATING SYSTEMS', 'c011');
INSERT INTO `s_course` VALUES ('12', '1', '2023-05-18 11:05:08.130000', 'MATH LOGIC', '1', '2023-05-18 11:05:08.130000', 'MATH LOGIC', 'c012');
INSERT INTO `s_course` VALUES ('13', '1', '2023-05-18 11:05:23.760000', 'WORK STUDY', '1', '2023-05-18 11:05:23.760000', 'WORK STUDY', 'c013');
INSERT INTO `s_course` VALUES ('14', '1', '2023-05-18 11:06:00.019000', 'INTERNSHIP', '1', '2023-05-18 11:06:00.019000', 'INTERNSHIP', 'c014');

-- ----------------------------
-- Table structure for s_grade_class
-- ----------------------------
DROP TABLE IF EXISTS `s_grade_class`;
CREATE TABLE `s_grade_class` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) NOT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `clazz` int DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Class Information Table';

-- ----------------------------
-- Records of s_grade_class
-- ----------------------------
INSERT INTO `s_grade_class` VALUES ('1', '1', '2023-05-12 17:44:50.000000', 'G21C01', '1', '2023-05-12 17:44:53.000000', '1', '202101', '2021', 'G21C01');
INSERT INTO `s_grade_class` VALUES ('2', '1', '2023-05-12 18:02:08.000000', 'G21C02', '1', '2023-05-12 18:02:11.000000', '2', '202102', '2021', 'G21C02');
INSERT INTO `s_grade_class` VALUES ('3', '1', '2023-05-12 11:32:26.550000', 'G21C03', '1', '2023-05-12 11:32:26.552000', '3', '202103', '2021', 'G21C03');
INSERT INTO `s_grade_class` VALUES ('4', '1', '2023-05-12 11:34:52.609000', 'G21C04', '1', '2023-05-12 11:34:52.609000', '4', '202104', '2021', 'G21C04');
INSERT INTO `s_grade_class` VALUES ('5', '1', '2023-05-12 11:35:22.735000', 'G22C01', '1', '2023-05-12 11:35:22.735000', '1', '202201', '2022', 'G22C01');
INSERT INTO `s_grade_class` VALUES ('6', '1', '2023-05-12 11:42:29.701000', 'G22C02', '1', '2023-05-12 11:42:29.701000', '2', '202202', '2022', 'G22C02');
INSERT INTO `s_grade_class` VALUES ('7', '1', '2023-05-12 11:50:06.672000', 'G22C03', '1', '2023-05-12 11:50:06.672000', '3', '202203', '2022', 'G22C03');
INSERT INTO `s_grade_class` VALUES ('8', '1', '2023-05-12 12:29:46.799000', 'G22C04', '1', '2023-05-12 12:32:27.696000', '4', '202204', '2022', 'G22C04');

-- ----------------------------
-- Table structure for s_student
-- ----------------------------
DROP TABLE IF EXISTS `s_student`;
CREATE TABLE `s_student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) NOT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `stuno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `grade_class_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhqce64ggjxx3othwe3cu4hdsd` (`grade_class_id`) USING BTREE,
  CONSTRAINT `s_student_ibfk_1` FOREIGN KEY (`grade_class_id`) REFERENCES `s_grade_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Student Information Table';

-- ----------------------------
-- Records of s_student
-- ----------------------------
INSERT INTO `s_student` VALUES ('1', '1', '2023-05-18 20:39:17.000000', 'Katharine Hepburn', '1', '2023-05-18 20:39:23.000000', 'Katharine Hepburn', '13800138001', 'Katharine@gmail.com', 'female', '202101001', '1');
INSERT INTO `s_student` VALUES ('3', '1', '2023-05-18 12:27:33.138000', 'Humphrey Bogart', '1', '2023-05-18 12:27:33.143000', 'Humphrey Bogart', '13800138001', 'Humphrey@gmail.com', 'male', '202201001', '5');
INSERT INTO `s_student` VALUES ('4', '1', '2023-05-18 12:33:12.369000', 'Bette Davis', '1', '2023-05-18 12:33:12.370000', 'Bette Davis', '13800138002', 'Bette@gmail.com', 'female', '202101002', '1');
INSERT INTO `s_student` VALUES ('5', '1', '2023-05-18 12:34:28.220000', 'Cary Grant', '1', '2023-05-18 12:34:28.221000', 'Cary Grant', '13800138003', 'Cary@gmail.com', 'male', '202201002', '5');
INSERT INTO `s_student` VALUES ('6', '1', '2023-05-18 12:35:47.631000', 'Audrey Hepburn', '1', '2023-05-18 12:35:47.631000', 'Audrey Hepburn', '13800138004', 'Audrey@gmail.com', 'female', '202201003', '5');
INSERT INTO `s_student` VALUES ('7', '1', '2023-05-18 12:38:29.538000', 'James Stewart', '1', '2023-05-18 12:38:29.538000', 'James Stewart', '13800138005', 'James@gmail.com', 'male', '202202001', '6');
INSERT INTO `s_student` VALUES ('8', '1', '2023-05-18 12:39:47.920000', 'Ingrid Bergman', '1', '2023-05-18 12:39:47.920000', 'Ingrid Bergman', '13800138006', 'Ingrid@gmail.com', 'female', '202202002', '6');
INSERT INTO `s_student` VALUES ('9', '1', '2023-05-18 12:40:42.839000', 'Marlon Brando', '1', '2023-05-18 12:40:42.839000', 'Marlon Brando', '13800138007', 'Marlon@gmail.com', 'male', '202203001', '8');
INSERT INTO `s_student` VALUES ('10', '1', '2023-05-18 12:41:29.804000', 'Greta Garbo', '1', '2023-05-18 12:41:29.804000', 'Greta Garbo', '13800138008', 'Greta@gmail.com', 'female', '202203002', '8');
INSERT INTO `s_student` VALUES ('11', '1', '2023-05-18 12:42:13.932000', 'Fred Astaire', '1', '2023-05-18 12:46:05.678000', 'Fred Astaire', '13800138009', 'Fred@gmail.com', 'male', '202204001', '9');
INSERT INTO `s_student` VALUES ('12', '1', '2023-05-18 12:46:47.361000', 'Marilyn Monroe', '1', '2023-05-18 12:46:47.361000', 'Marilyn Monroe', '13800138010', 'Marilyn@gmail.com', 'female', '202204002', '9');
INSERT INTO `s_student` VALUES ('13', '1', '2023-05-19 06:51:11.668000', 'Henry Fonda', '1', '2023-05-19 06:51:11.669000', 'Henry Fonda', '13800138000', 'Henry@gmail.com', 'male', '202101003', '1');
INSERT INTO `s_student` VALUES ('14', '1', '2023-05-19 06:52:16.308000', 'Elizabeth Taylor', '1', '2023-05-19 06:52:16.309000', 'Elizabeth Taylor', '13800138000', 'Elizabeth@gmail.com', 'female', '202101004', '1');
INSERT INTO `s_student` VALUES ('15', '1', '2023-05-19 06:53:20.579000', 'Clark Gable', '1', '2023-05-19 06:53:20.579000', 'Clark Gable', '13800138000', 'Clark@gmail.com', 'male', '202101005', '1');
INSERT INTO `s_student` VALUES ('16', '1', '2023-05-19 06:54:00.011000', 'Judy Garland', '1', '2023-05-19 06:54:00.011000', 'Judy Garland', '13800138000', 'Judy@gmail.com', 'female', '202101006', '1');
INSERT INTO `s_student` VALUES ('17', '1', '2023-05-19 06:54:41.843000', 'James Cagney', '1', '2023-05-19 06:54:41.843000', 'James Cagney', '13800138000', 'James@gmail.com', 'male', '202101007', '1');
INSERT INTO `s_student` VALUES ('18', '1', '2023-05-19 06:55:31.434000', 'Marlene Dietrich', '1', '2023-05-19 06:55:31.434000', 'Marlene Dietrich', '13800138000', 'Marlene@gmail.com', 'female', '202101008', '1');
INSERT INTO `s_student` VALUES ('19', '1', '2023-05-20 06:56:22.065000', 'Charlie Chaplin', '1', '2023-05-20 06:56:22.065000', 'Charlie Chaplin', '13800138000', 'Charlie@gmail.com', 'male', '202101009', '1');
INSERT INTO `s_student` VALUES ('20', '1', '2023-05-20 06:57:13.643000', 'Joan Crawford', '1', '2023-05-20 06:57:13.643000', 'Joan Crawford', '13800138000', 'Joan@gmail.com', 'female', '202101010', '1');
INSERT INTO `s_student` VALUES ('21', '1', '2023-05-20 06:58:31.090000', 'Gregory Peck', '1', '2023-05-20 06:58:31.090000', 'Gregory Peck', '13800138000', 'Gregory@gmail.com', 'male', '202101011', '5');
INSERT INTO `s_student` VALUES ('22', '1', '2023-05-20 07:00:11.864000', 'Claudette Colbert', '1', '2023-05-20 07:00:11.864000', 'Claudette Colbert', '13800138000', 'Claudette@gmail.com', 'female', '202201004', '5');
INSERT INTO `s_student` VALUES ('23', '1', '2023-05-20 07:00:56.730000', 'Laurence Olivier', '1', '2023-05-20 07:00:56.731000', 'Laurence Olivier', '13800138000', 'Laurence@gmail.com', 'male', '202201005', '5');
INSERT INTO `s_student` VALUES ('24', '1', '2023-05-20 07:03:38.410000', 'Grace Kelly', '1', '2023-05-20 07:03:38.410000', 'Grace Kelly', '13800138000', 'Grace@gmail.com', 'female', '202201006', '5');
INSERT INTO `s_student` VALUES ('25', '1', '2023-05-20 07:04:26.494000', 'Orson Welles', '1', '2023-05-20 07:04:26.495000', 'Orson Welles', '13800138000', 'Orson@gmail.com', 'male', '202201007', '5');
INSERT INTO `s_student` VALUES ('26', '1', '2023-05-20 07:06:19.690000', 'Vivien Leigh', '1', '2023-05-20 07:06:19.691000', 'Vivien Leigh', '13800138000', 'Vivien@gmail.com', 'female', '202201008', '5');
INSERT INTO `s_student` VALUES ('27', '1', '2023-05-20 07:07:06.107000', 'Burt Lancaster', '1', '2023-05-20 07:07:06.107000', 'Burt Lancaster', '13800138000', 'Burt@gmail.com', 'male', '202201009', '5');
INSERT INTO `s_student` VALUES ('28', '1', '2023-05-20 07:07:48.235000', 'Lillian Gish', '1', '2023-05-20 07:07:48.235000', 'Lillian Gish', '13800138000', 'Lillian@gmail.com', 'female', '202201010', '5');
INSERT INTO `s_student` VALUES ('29', '1', '2023-05-20 07:08:36.588000', 'Kirk Douglas', '1', '2023-05-20 07:08:36.588000', 'Kirk Douglas', '13800138000', 'Kirk@gmail.com', 'male', '202201011', '5');

-- ----------------------------
-- Table structure for s_student_score
-- ----------------------------
DROP TABLE IF EXISTS `s_student_score`;
CREATE TABLE `s_student_score` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) NOT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  `gradeclass_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrm1fvg0dausplxq9nphxb47j9` (`course_id`) USING BTREE,
  KEY `FKfb1pcum4u0xlbod236kmngx4f` (`student_id`) USING BTREE,
  KEY `FK8mac4aaiivjtr0kd4kpyt4vm5` (`gradeclass_id`) USING BTREE,
  CONSTRAINT `s_student_score_ibfk_1` FOREIGN KEY (`gradeclass_id`) REFERENCES `s_grade_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_student_score_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `s_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_student_score_ibfk_3` FOREIGN KEY (`course_id`) REFERENCES `s_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Student Score Table ';

-- ----------------------------
-- Records of s_student_score
-- ----------------------------
INSERT INTO `s_student_score` VALUES ('33', '1', '2023-05-18 10:07:32.817000', 'initial score', '1', '2023-05-18 10:08:02.795000', '89', 'corrected', '2', '1', '1');
INSERT INTO `s_student_score` VALUES ('34', '1', '2023-05-18 10:07:32.833000', 'initial score', '1', '2023-05-18 10:07:56.206000', '82', 'corrected', '2', '4', '1');
INSERT INTO `s_student_score` VALUES ('35', '1', '2023-05-18 10:08:13.135000', 'initial score', '1', '2023-05-18 10:08:35.563000', '100', 'corrected', '1', '3', '5');
INSERT INTO `s_student_score` VALUES ('36', '1', '2023-05-18 10:08:13.140000', 'initial score', '1', '2023-05-18 10:08:29.790000', '98', 'corrected', '1', '5', '5');
INSERT INTO `s_student_score` VALUES ('37', '1', '2023-05-18 10:08:13.145000', 'initial score', '1', '2023-05-18 03:29:28.934000', '68', 'corrected', '1', '6', '5');
INSERT INTO `s_student_score` VALUES ('38', '1', '2023-05-18 10:10:05.151000', 'initial score', '1', '2023-05-18 10:10:18.330000', '98', 'corrected', '1', '1', '1');
INSERT INTO `s_student_score` VALUES ('39', '1', '2023-05-18 10:10:05.156000', 'initial score', '1', '2023-05-18 01:29:28.455000', '59', 'corrected', '1', '4', '1');
INSERT INTO `s_student_score` VALUES ('40', '1', '2023-05-18 07:10:46.872000', 'initial score', '1', '2023-05-18 07:10:56.101000', '52', 'corrected', '1', '13', '1');
INSERT INTO `s_student_score` VALUES ('41', '1', '2023-05-18 07:10:46.877000', 'initial score', '1', '2023-05-18 07:11:04.504000', '66', 'corrected', '1', '14', '1');
INSERT INTO `s_student_score` VALUES ('42', '1', '2023-05-18 07:10:46.881000', 'initial score', '1', '2023-05-18 07:11:09.886000', '77', 'corrected', '1', '15', '1');
INSERT INTO `s_student_score` VALUES ('43', '1', '2023-05-18 07:10:46.890000', 'initial score', '1', '2023-05-18 07:11:17.568000', '88', 'corrected', '1', '16', '1');
INSERT INTO `s_student_score` VALUES ('44', '1', '2023-05-18 07:10:46.894000', 'initial score', '1', '2023-05-18 07:11:27.049000', '55', 'corrected', '1', '17', '1');
INSERT INTO `s_student_score` VALUES ('45', '1', '2023-05-18 07:10:46.899000', 'initial score', '1', '2023-05-18 07:11:33.257000', '68', 'corrected', '1', '18', '1');
INSERT INTO `s_student_score` VALUES ('46', '1', '2023-05-18 07:10:46.905000', 'initial score', '1', '2023-05-18 07:11:41.978000', '84', 'corrected', '1', '19', '1');
INSERT INTO `s_student_score` VALUES ('47', '1', '2023-05-18 07:10:46.909000', 'initial score', '1', '2023-05-18 07:11:51.490000', '58', 'corrected', '1', '20', '1');
INSERT INTO `s_student_score` VALUES ('48', '1', '2023-05-18 07:10:46.913000', 'initial score', '1', '2023-05-18 07:11:59.425000', '100', 'corrected', '1', '21', '1');
INSERT INTO `s_student_score` VALUES ('49', '1', '2023-05-18 05:10:53.235000', 'initial score', '1', '2023-05-18 05:12:14.932000', '88', 'corrected', '3', '1', '1');
INSERT INTO `s_student_score` VALUES ('50', '1', '2023-05-18 05:10:53.265000', 'initial score', '1', '2023-05-18 05:12:07.171000', '94', 'corrected', '3', '4', '1');
INSERT INTO `s_student_score` VALUES ('51', '1', '2023-05-18 05:10:53.271000', 'initial score', '1', '2023-05-18 05:12:00.644000', '81', 'corrected', '3', '13', '1');
INSERT INTO `s_student_score` VALUES ('52', '1', '2023-05-18 05:10:53.278000', 'initial score', '1', '2023-05-18 05:11:53.845000', '43', 'corrected', '3', '14', '1');
INSERT INTO `s_student_score` VALUES ('53', '1', '2023-05-18 05:10:53.286000', 'initial score', '1', '2023-05-18 05:11:48.443000', '68', 'corrected', '3', '15', '1');
INSERT INTO `s_student_score` VALUES ('54', '1', '2023-05-18 05:10:53.291000', 'initial score', '1', '2023-05-18 05:11:38.904000', '90', 'corrected', '3', '16', '1');
INSERT INTO `s_student_score` VALUES ('55', '1', '2023-05-19 05:10:53.296000', 'initial score', '1', '2023-05-19 05:11:32.396000', '75', 'corrected', '3', '17', '1');
INSERT INTO `s_student_score` VALUES ('56', '1', '2023-05-19 05:10:53.302000', 'initial score', '1', '2023-05-19 05:11:25.756000', '85', 'corrected', '3', '18', '1');
INSERT INTO `s_student_score` VALUES ('57', '1', '2023-05-19 05:10:53.307000', 'initial score', '1', '2023-05-19 05:11:18.744000', '56', 'corrected', '3', '19', '1');
INSERT INTO `s_student_score` VALUES ('58', '1', '2023-05-19 05:10:53.312000', 'initial score', '1', '2023-05-19 05:11:12.436000', '77', 'corrected', '3', '20', '1');
INSERT INTO `s_student_score` VALUES ('59', '1', '2023-05-19 05:10:53.317000', 'initial score', '1', '2023-05-19 05:11:07.113000', '69', 'corrected', '3', '21', '1');
INSERT INTO `s_student_score` VALUES ('60', '1', '2023-05-19 05:12:59.515000', 'initial score', '1', '2023-05-19 05:14:12.996000', '99', 'corrected', '3', '3', '5');
INSERT INTO `s_student_score` VALUES ('61', '1', '2023-05-19 05:12:59.519000', 'initial score', '1', '2023-05-19 05:14:06.403000', '92', 'corrected', '3', '5', '5');
INSERT INTO `s_student_score` VALUES ('62', '1', '2023-05-19 05:12:59.523000', 'initial score', '1', '2023-05-19 05:14:00.899000', '88', 'corrected', '3', '6', '5');
INSERT INTO `s_student_score` VALUES ('63', '1', '2023-05-19 05:12:59.527000', 'initial score', '1', '2023-05-19 05:13:51.683000', '71', 'corrected', '3', '22', '5');
INSERT INTO `s_student_score` VALUES ('64', '1', '2023-05-19 05:12:59.531000', 'initial score', '1', '2023-05-19 05:13:46.450000', '64', 'corrected', '3', '23', '5');
INSERT INTO `s_student_score` VALUES ('65', '1', '2023-05-19 05:12:59.534000', 'initial score', '1', '2023-05-19 05:13:39.419000', '60', 'corrected', '3', '24', '5');
INSERT INTO `s_student_score` VALUES ('66', '1', '2023-05-19 05:12:59.541000', 'initial score', '1', '2023-05-19 05:13:34.615000', '59', 'corrected', '3', '25', '5');
INSERT INTO `s_student_score` VALUES ('67', '1', '2023-05-19 05:12:59.545000', 'initial score', '1', '2023-05-19 05:13:29.741000', '58', 'corrected', '3', '26', '5');
INSERT INTO `s_student_score` VALUES ('68', '1', '2023-05-19 05:12:59.548000', 'initial score', '1', '2023-05-19 05:13:24.883000', '67', 'corrected', '3', '27', '5');
INSERT INTO `s_student_score` VALUES ('69', '1', '2023-05-19 05:12:59.552000', 'initial score', '1', '2023-05-19 05:13:19.915000', '69', 'corrected', '3', '28', '5');
INSERT INTO `s_student_score` VALUES ('70', '1', '2023-05-19 05:12:59.556000', 'initial score', '1', '2023-05-19 05:13:13.948000', '77', 'corrected', '3', '29', '5');
INSERT INTO `s_student_score` VALUES ('71', '1', '2023-05-19 05:15:18.549000', 'initial score', '1', '2023-05-19 05:16:17.435000', '77', 'corrected', '4', '1', '1');
INSERT INTO `s_student_score` VALUES ('72', '1', '2023-05-19 05:15:18.553000', 'initial score', '1', '2023-05-19 05:16:10.259000', '94', 'corrected', '4', '4', '1');
INSERT INTO `s_student_score` VALUES ('73', '1', '2023-05-19 05:15:18.561000', 'initial score', '1', '2023-05-19 05:16:03.164000', '73', 'corrected', '4', '13', '1');
INSERT INTO `s_student_score` VALUES ('74', '1', '2023-05-19 05:15:18.567000', 'initial score', '1', '2023-05-19 05:15:56.420000', '86', 'corrected', '4', '14', '1');
INSERT INTO `s_student_score` VALUES ('75', '1', '2023-05-19 05:15:18.574000', 'initial score', '1', '2023-05-19 05:15:50.964000', '58', 'corrected', '4', '15', '1');
INSERT INTO `s_student_score` VALUES ('76', '1', '2023-05-19 05:15:18.580000', 'initial score', '1', '2023-05-19 05:15:46.315000', '66', 'corrected', '4', '16', '1');
INSERT INTO `s_student_score` VALUES ('77', '1', '2023-05-19 05:15:18.593000', 'initial score', '1', '2023-05-19 05:15:42.243000', '53', 'corrected', '4', '17', '1');
INSERT INTO `s_student_score` VALUES ('78', '1', '2023-05-19 05:15:18.599000', 'initial score', '1', '2023-05-19 05:15:37.339000', '87', 'corrected', '4', '18', '1');
INSERT INTO `s_student_score` VALUES ('79', '1', '2023-05-20 05:15:18.604000', 'initial score', '1', '2023-05-20 05:15:32.932000', '96', 'corrected', '4', '19', '1');
INSERT INTO `s_student_score` VALUES ('80', '1', '2023-05-20 05:15:18.611000', 'initial score', '1', '2023-05-20 05:15:27.060000', '75', 'corrected', '4', '20', '1');
INSERT INTO `s_student_score` VALUES ('81', '1', '2023-05-20 05:15:18.617000', 'initial score', '1', '2023-05-20 05:15:22.739000', '44', 'corrected', '4', '21', '1');

-- ----------------------------
-- Table structure for s_teacher
-- ----------------------------
DROP TABLE IF EXISTS `s_teacher`;
CREATE TABLE `s_teacher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) NOT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `teach_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgpacv4uc6gmdaridy3afaf5co` (`course_id`) USING BTREE,
  CONSTRAINT `s_teacher_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `s_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Teacher Information Table';

-- ----------------------------
-- Records of s_teacher
-- ----------------------------
INSERT INTO `s_teacher` VALUES ('1', '1', '2023-05-12 12:16:52.932000', 'Steven Spielberg', '1', '2023-05-12 12:16:52.933000', 'Steven Spielberg', '13800138001', 'Steven@gmail.com', 'male', 't001', '1');
INSERT INTO `s_teacher` VALUES ('2', '1', '2023-05-12 12:18:29.890000', 'Ang Lee', '1', '2023-05-12 12:18:29.890000', 'Ang Lee', '13800138002', 'Ang@gmail.com', 'male', 't002', '2');
INSERT INTO `s_teacher` VALUES ('3', '1', '2023-05-12 12:20:35.666000', 'Clint Eastwood', '1', '2023-05-12 12:20:35.666000', 'Clint Eastwood', '13800138003', 'Clint@gmail.com', 'male', 't003', '3');
INSERT INTO `s_teacher` VALUES ('4', '1', '2023-05-12 12:21:57.329000', 'Martin Scorsese', '1', '2023-05-12 12:21:57.329000', 'Martin Scorsese', '13800138004', 'Martin@gmail.com', 'male', 't004', '4');
INSERT INTO `s_teacher` VALUES ('5', '1', '2023-05-12 12:22:55.723000', 'Allen Konigsberg', '1', '2023-05-12 12:22:55.723000', 'Allen Konigsberg', '13800138005', 'Allen@gmail.com', 'male', 't005', '5');
INSERT INTO `s_teacher` VALUES ('6', '1', '2023-05-12 12:25:14.578000', 'David Lean', '1', '2023-05-12 12:25:14.578000', 'David Lean', '13800138006', 'David@gmail.com', 'male', 't006', '6');
INSERT INTO `s_teacher` VALUES ('7', '1', '2023-05-12 12:26:16.961000', 'William Wyler', '1', '2023-05-12 12:26:16.961000', 'William Wyler', '13800138007', 'William@gmail.com', 'male', 't007', '7');
INSERT INTO `s_teacher` VALUES ('8', '1', '2023-05-12 12:27:26.298000', 'John Ford', '1', '2023-05-12 12:27:26.298000', 'John Ford', '13800138008', 'John@gmail.com', 'male', 't008', '8');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) NOT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='System Role Information Table';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '1', '2023-05-10 19:12:03.000000', 'System Administrator', '1', '2023-05-10 19:11:59.000000', 'ROLE_ADMIN', 'System Administrator');
INSERT INTO `sys_role` VALUES ('2', '1', '2023-05-10 19:12:15.000000', 'General User', '1', '2023-05-10 19:12:30.000000', 'ROLE_USER', 'General User');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) NOT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4dm5kxn3potpfgdigehw7pdyu` (`role_id`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='System User Information Table';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', '2023-05-10 12:55:05.000000', 'Nicole Kidman', '1', '2023-05-10 03:05:28.241000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Nicole Kidman', 'female', '1', 'admin', 'Nicole@gmail.com', '27a0cd9a-581c-44bf-acba-5de5cab72b0a.png', '1');
INSERT INTO `sys_user` VALUES ('2', '1', '2023-05-10 13:06:48.040000', 'Kristen Stewart', '1', '2023-05-10 03:36:51.824000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Kristen Stewart', 'female', '1', 'Kristen', 'Kristen@gmail.com', null, '2');
INSERT INTO `sys_user` VALUES ('3', '1', '2023-05-10 13:09:44.441000', 'Andrea Riseborough', '1', '2023-05-10 03:30:05.735000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Andrea Riseborough', 'female', '1', 'Andrea', 'Andrea@gmail.com', null, '2');
INSERT INTO `sys_user` VALUES ('4', '1', '2023-05-10 13:10:42.589000', 'Michelle Williams', '1', '2023-05-10 13:10:42.589000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Michelle Williams', 'female', '1', 'Michelle', 'Michelle@gmail.com', null, '2');
INSERT INTO `sys_user` VALUES ('5', '1', '2023-05-10 13:14:47.104000', 'Ana Armas', '1', '2023-05-10 03:37:28.681000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Ana Armas', 'female', '1', 'Ana', 'Ana@gmail.com', null, '2');
INSERT INTO `sys_user` VALUES ('6', '1', '2023-05-10 13:36:24.775000', 'Cate Blanchett', '1', '2023-05-10 13:36:24.776000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Cate Blanchett', 'female', '1', 'Cate', 'Cate@gmail.com', null, '2');
INSERT INTO `sys_user` VALUES ('7', '1', '2023-05-10 13:38:54.366000', 'Olivia Colman', '1', '2023-05-10 13:38:54.366000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Olivia Colman', 'female', '1', 'Olivia', 'Olivia@gmail.com', null, '2');
INSERT INTO `sys_user` VALUES ('10', '1', '2023-05-10 01:13:56.953000', 'Jessica Chastain', '1', '2023-05-10 01:13:56.953000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Jessica Chastain', 'female', '1', 'Jessica', 'Jessica@gmail.com', null, '2');
INSERT INTO `sys_user` VALUES ('11', '1', '2023-05-10 03:46:19.410000', 'Carey Mulligan', '1', '2023-05-10 03:46:19.410000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Carey Mulligan', 'female', '1', 'Carey', 'Carey@gmail.com', null, '2');
INSERT INTO `sys_user` VALUES ('12', '1', '2023-05-10 03:47:02.119000', 'Vanessa Kirby', '1', '2023-05-10 03:47:02.119000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Vanessa Kirby', 'female', '1', 'Vanessa', 'Vanessa@gmail.com', null, '2');
INSERT INTO `sys_user` VALUES ('13', '1', '2023-05-10 03:47:40.257000', 'Frances McDormand', '1', '2023-05-10 03:47:40.258000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Frances McDormand', 'female', '1', 'Frances', 'Frances@gmail.com', null, '2');
INSERT INTO `sys_user` VALUES ('14', '1', '2023-05-10 03:48:40.648000', 'Saoirse Ronan', '1', '2023-05-10 03:48:40.648000', 'E10ADC3949BA59ABBE56E057F20F883E', 'Saoirse Ronan', 'female', '1', 'Saoirse', 'Saoirse@gmail.com', null, '2');
