/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : appdemo

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 06/03/2020 17:39:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api` (
  `API_ID` int(11) NOT NULL AUTO_INCREMENT,
  `API_URL` varchar(50) DEFAULT NULL,
  `API_KEY` varchar(50) DEFAULT NULL,
  `API_SECRET` varchar(50) DEFAULT NULL,
  `CREARE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `COUNSRLOR_ID` int(11) DEFAULT NULL COMMENT '辅导员ID',
  `STATE` int(4) DEFAULT '0' COMMENT '是否启用',
  PRIMARY KEY (`API_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of api
-- ----------------------------
BEGIN;
INSERT INTO `api` VALUES (4, 'https://api-cn.faceplusplus.com/facepp/v3/compare', 'L_mT-oglbWdX2uw20G3tGl5TJqBy9v3L', 'jaoj6KDENsq7JcgUL8mD-tMQVSMZRFlX', '2019-12-28 17:40:48', 3, 1);
COMMIT;

-- ----------------------------
-- Table structure for counselor
-- ----------------------------
DROP TABLE IF EXISTS `counselor`;
CREATE TABLE `counselor` (
  `counselor_id` int(11) NOT NULL AUTO_INCREMENT,
  `counselor_name` varchar(11) DEFAULT NULL,
  `counselor_password` varchar(255) DEFAULT NULL,
  `profession_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `counselor_cno` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`counselor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of counselor
-- ----------------------------
BEGIN;
INSERT INTO `counselor` VALUES (3, '郑艺欣', '983d89d5b52941021909de3fc76c53f7a27c34c64a199788', 1, '2019-08-23 23:46:53', '1001');
INSERT INTO `counselor` VALUES (4, 'wangxianlin', '41420cc83b5431e533079266246c9163782095c71c877906', 2, '2019-08-23 23:47:14', '1002');
INSERT INTO `counselor` VALUES (5, 'admin', 'e5f56c809401e6272814406417b20467c92d92b451099d82', 1, '2019-11-07 22:27:05', '1003');
COMMIT;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) DEFAULT NULL,
  `classArrangement` int(11) DEFAULT NULL COMMENT '上课安排',
  `startTime` datetime DEFAULT NULL COMMENT '开课时间',
  `endTIme` datetime DEFAULT NULL COMMENT '结课时间',
  `profession` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `ceate_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES (15, '高等数学', 1, '2020-06-01 00:00:00', '2020-12-01 00:00:00', 1, 1, '2020-02-24 13:27:25');
INSERT INTO `course` VALUES (18, '线性代数', 1, '2020-02-01 00:00:00', '2020-06-01 00:00:00', 1, 1, '2020-03-03 10:07:11');
INSERT INTO `course` VALUES (19, '概率论', 3, '2020-02-01 00:00:00', '2020-05-01 00:00:00', 1, 1, '2020-03-03 10:07:12');
INSERT INTO `course` VALUES (20, '课程设计', 1, '2020-02-01 00:00:00', '2020-06-01 00:00:00', 1, 2, '2020-03-03 10:07:13');
INSERT INTO `course` VALUES (21, 'Python课程', 1, '2020-01-01 00:00:00', '2020-02-01 00:00:00', 4, 1, '2020-03-06 10:24:42');
INSERT INTO `course` VALUES (22, '滴滴滴', 5, '2020-03-01 00:00:00', '2020-07-01 00:00:00', 4, 11, '2020-03-06 15:42:52');
INSERT INTO `course` VALUES (23, '英语', 1, '2020-03-01 00:00:00', '2020-07-01 00:00:00', 4, 1, '2020-03-06 17:07:31');
INSERT INTO `course` VALUES (24, '市场营销', 3, '2020-01-01 00:00:00', '2020-02-01 00:00:00', 1, 1, '2020-03-06 17:08:01');
COMMIT;

-- ----------------------------
-- Table structure for leaves
-- ----------------------------
DROP TABLE IF EXISTS `leaves`;
CREATE TABLE `leaves` (
  `leave_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '请假单ID',
  `student_sno` varchar(255) DEFAULT NULL COMMENT '学生学号',
  `leave_title` varchar(255) DEFAULT NULL,
  `leave_reason` varchar(255) DEFAULT NULL COMMENT '请假缘由',
  `start_time` date DEFAULT NULL COMMENT '开始时间',
  `end_time` date DEFAULT NULL COMMENT '结束时间',
  `coundelor_id` int(11) DEFAULT '1' COMMENT '辅导员ID',
  `is_read` int(11) DEFAULT '0' COMMENT '是否同意 0 未批阅 1 为同意',
  `course_id` int(11) DEFAULT NULL COMMENT '课程ID',
  PRIMARY KEY (`leave_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leaves
-- ----------------------------
BEGIN;
INSERT INTO `leaves` VALUES (19, '161303039', '发烧', '去医院', '2020-02-29', '2020-02-29', 1, 1, 15);
INSERT INTO `leaves` VALUES (20, '161303039', '肚子不舒服呀', '啦啦啦', '2020-03-05', '2020-03-05', 1, 1, 20);
INSERT INTO `leaves` VALUES (21, '161303039', '发烧了', '我太难了', '2020-03-05', '2020-03-06', 1, 2, 15);
INSERT INTO `leaves` VALUES (22, '161303040', 'gggg', 'fffff', '2020-03-06', '2020-03-06', 1, 1, 20);
INSERT INTO `leaves` VALUES (23, '001', '我丢', '点点滴滴', '2020-03-06', '2020-03-06', 1, 1, 21);
COMMIT;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `class` varchar(200) DEFAULT NULL,
  `method` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `log_level` varchar(200) DEFAULT NULL,
  `log_line` varchar(200) DEFAULT NULL,
  `msg` varchar(500) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
BEGIN;
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 10:37:01', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 4);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 10:37:08', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 5);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 10:40:54', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 6);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 10:41:02', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 7);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 10:42:16', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 8);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 10:42:26', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 9);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 10:56:37', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 10);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 10:56:44', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 11);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 11:13:52', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 12);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 11:14:00', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 13);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 12:02:23', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 14);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 12:02:31', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 15);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 12:29:12', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 16);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 14:32:31', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 17);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 15:00:39', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 18);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 15:00:46', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 19);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 15:06:14', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 20);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 15:06:22', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 21);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 15:46:47', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 22);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 16:52:53', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 23);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 17:15:50', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 24);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'init', '2020-03-06 17:15:57', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:655)', '{dataSource-1} inited', 25);
INSERT INTO `log` VALUES ('com.alibaba.druid.pool.DruidDataSource', 'close', '2020-03-06 17:38:27', 'INFO', 'com.alibaba.druid.pool.DruidDataSource.close(DruidDataSource.java:1370)', '{dataSource-1} closed', 26);
COMMIT;

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession` (
  `profession_id` int(11) NOT NULL AUTO_INCREMENT,
  `profession_name` varchar(255) DEFAULT NULL,
  `apartment` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`profession_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profession
-- ----------------------------
BEGIN;
INSERT INTO `profession` VALUES (1, '信息管理与信息系统', '数学与计算机性科学学院', '泉州师范学院', '2019-08-23 13:53:08');
INSERT INTO `profession` VALUES (2, '计算机科学与技术', '数学与计算机性科学学院', '泉州师范学院', '2019-08-23 13:53:33');
INSERT INTO `profession` VALUES (3, '物联网', '数学与计算机性科学学院', '泉州师范学院', '2019-08-30 19:48:20');
INSERT INTO `profession` VALUES (4, '大数据科学与技术', '数学与计算机性科学学院', '泉州师范学院', '2019-08-30 19:48:21');
INSERT INTO `profession` VALUES (7, '食品科学与工程', '海洋学院', '泉州师范学院', '2020-02-27 14:00:13');
INSERT INTO `profession` VALUES (8, '商务英语', '英语学院', '泉州师范学院', '2019-12-17 23:59:13');
COMMIT;

-- ----------------------------
-- Table structure for release
-- ----------------------------
DROP TABLE IF EXISTS `release`;
CREATE TABLE `release` (
  `release_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `profession_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`release_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of release
-- ----------------------------
BEGIN;
INSERT INTO `release` VALUES (8, 1, '2020-03-06 10:30:00', '2020-03-06 10:31:00', 1, 15);
INSERT INTO `release` VALUES (9, 1, '2020-03-06 11:43:00', '2020-03-06 11:44:00', 1, 21);
INSERT INTO `release` VALUES (10, 1, '2020-03-06 11:47:00', '2020-03-06 11:48:00', 3, 15);
INSERT INTO `release` VALUES (11, 1, '2020-03-06 11:48:00', '2020-03-06 11:49:00', 1, 18);
INSERT INTO `release` VALUES (15, 1, '2020-03-06 12:03:00', '2020-03-06 12:05:00', 1, 15);
INSERT INTO `release` VALUES (16, 1, '2020-03-06 12:10:00', '2020-03-06 12:11:00', 1, 15);
INSERT INTO `release` VALUES (17, 1, '2020-03-06 15:14:00', '2020-03-06 15:16:00', 1, 21);
INSERT INTO `release` VALUES (18, 11, '2020-03-06 15:43:00', '2020-03-06 15:44:00', 4, 22);
INSERT INTO `release` VALUES (19, 1, '2020-03-06 17:08:00', '2020-03-06 17:10:00', 1, 18);
COMMIT;

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `sign_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '签到ID',
  `sign_location` varchar(255) DEFAULT NULL COMMENT '签到地点',
  `start_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上课时间',
  `end_time` datetime DEFAULT NULL COMMENT '下课时间',
  `student_id` int(11) DEFAULT NULL COMMENT '学生ID',
  `course_id` int(11) DEFAULT NULL COMMENT '课程ID',
  `is_start_status` int(1) DEFAULT '1' COMMENT '是否进行上课签到',
  `is_end_status` int(1) DEFAULT '0' COMMENT '是否进行下课签到',
  `sign_out_location` varchar(255) DEFAULT NULL,
  `release_id` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '考勤状态',
  PRIMARY KEY (`sign_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------
BEGIN;
INSERT INTO `sign` VALUES (51, '安徽省合肥市庐阳区长江中路337号-1-2层', '2020-03-06 17:21:37', NULL, 1, 18, 1, 0, NULL, 19, 1);
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) DEFAULT NULL,
  `student_sno` varchar(50) DEFAULT NULL,
  `student_password` varchar(255) DEFAULT NULL,
  `student_age` varchar(255) DEFAULT NULL,
  `student_qq` varchar(255) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL,
  `student_pic` varchar(255) DEFAULT 'default',
  `student_sex` varchar(255) DEFAULT NULL,
  `profession_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `img_base_64` text,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (1, '夏怡景', '161303039', '036a8f298f35515b2a43be9692dc6c433696b2228e774805', '20', '1244316812', '1244316812@qq.com', '2020/03/06/1720558779486.jpeg', '女', 1, '2020-03-06 17:20:55', NULL);
INSERT INTO `student` VALUES (6, '张诗晴', '161303004', '420f28a7540f368041175a9a957d1be6704db6207e774258', '20', '565467', '565467@qq.com', 'default', '男', 1, '2020-03-05 02:50:43', NULL);
INSERT INTO `student` VALUES (7, '肖艳', '161303040', '17344619979221e747a8e96904b558b6b56f93842f47f156', '21', '007', '007@qq.com', 'default', '男', 1, '2020-03-05 02:50:42', NULL);
INSERT INTO `student` VALUES (8, '林义景', '161303023', '46f757e7170e30327fd8756084017a26a680c6503970f015', '21', '12333', '3333333@qq，com', 'default', '男', 2, '2020-03-05 02:50:44', NULL);
INSERT INTO `student` VALUES (9, '测试账号', '001', 'b4995575f897d4b35102996004049223a55b09252319e30a', '21', '1234567', '1234567@qq.com', 'default', '男', 4, '2020-03-06 15:26:26', NULL);
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(255) DEFAULT NULL,
  `teacher_tno` varchar(255) DEFAULT NULL,
  `teacher_password` varchar(255) DEFAULT NULL,
  `teacher_job_title` varchar(255) DEFAULT NULL,
  `teacher_sex` char(255) DEFAULT NULL,
  `teacher_age` int(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `profession_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES (1, '戴端旭', '2001', '036a8f298f35515b2a43be9692dc6c433696b2228e774805', '教授', '男', 32, '2020-01-27 17:30:48', 1);
INSERT INTO `teacher` VALUES (2, '陈育明', '2002', '036a8f298f35515b2a43be9692dc6c433696b2228e774805', '讲师', '男', 34, '2019-11-07 23:27:29', 3);
INSERT INTO `teacher` VALUES (6, '蔡芬', '2006', '25766f056727a84e5419233a73ec4618434f677145c4a773', '讲师', '女', 30, '2019-08-30 20:26:04', 1);
INSERT INTO `teacher` VALUES (8, '王龙', '2008', '93c00144669b05e294b68422017a92e82f17660b9d879e0b', '助教', '男', 22, '2019-08-30 22:58:15', 1);
INSERT INTO `teacher` VALUES (11, 'teacher', '001', 'a4b306508364a6593992242a37fd8954764a50869805d28f', '助教', '男', NULL, '2020-03-06 15:36:57', 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
