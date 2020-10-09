
-- ----------------------------
-- Table structure for `classes`
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'dfsdfsadf',
  `name` varchar(50) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `ctc`
-- ----------------------------
DROP TABLE IF EXISTS `ctc`;
CREATE TABLE `ctc` (
  `couid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  `claid` int(11) NOT NULL,
  PRIMARY KEY (`couid`,`tid`,`claid`),
  KEY `tid` (`tid`),
  KEY `claid` (`claid`),
  KEY `couid` (`couid`),
  CONSTRAINT `claid` FOREIGN KEY (`claid`) REFERENCES `classes` (`id`) ON DELETE CASCADE,
  CONSTRAINT `couid` FOREIGN KEY (`couid`) REFERENCES `course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT '0',
  `cid` int(11) DEFAULT '0',
  `tid` int(11) DEFAULT NULL,
  `pgrade` double DEFAULT NULL,
  `kgrade` double DEFAULT NULL,
  `zgrade` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cid2` (`cid`),
  KEY `tid2` (`tid`),
  KEY `sid2` (`sid`),
  CONSTRAINT `cid2` FOREIGN KEY (`cid`) REFERENCES `course` (`id`),
  CONSTRAINT `sid2` FOREIGN KEY (`sid`) REFERENCES `student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tid2` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;


-- ----------------------------
-- Table structure for `sc`
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `sid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`sid`,`cid`),
  KEY `cId1` (`cid`),
  KEY `tId1` (`tid`),
  CONSTRAINT `cId1` FOREIGN KEY (`cid`) REFERENCES `course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `sId1` FOREIGN KEY (`sid`) REFERENCES `student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tId1` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  `classid` int(30) DEFAULT NULL,
  `usertype` int(11) DEFAULT '2',
  `password` varchar(30) DEFAULT NULL,
  `loginname` varchar(30) DEFAULT NULL,
  `files` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_c` (`classid`),
  CONSTRAINT `classid` FOREIGN KEY (`classid`) REFERENCES `classes` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `usertype` int(11) DEFAULT '3',
  `loginname` varchar(255) DEFAULT NULL,
  `password` varchar(30) DEFAULT 'aaaaaa',
  `workId` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `usertype` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

