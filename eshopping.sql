/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : eshopping

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2021-09-14 22:40:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category_info
-- ----------------------------
DROP TABLE IF EXISTS `category_info`;
CREATE TABLE `category_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of category_info
-- ----------------------------
INSERT INTO `category_info` VALUES ('1', '食品类', '123');
INSERT INTO `category_info` VALUES ('2', '生活类', '123');
INSERT INTO `category_info` VALUES ('3', '娱乐类', '123');
INSERT INTO `category_info` VALUES ('4', '装饰类', '123');
INSERT INTO `category_info` VALUES ('5', '外设类', '123');
INSERT INTO `category_info` VALUES ('6', '家居类', '6');

-- ----------------------------
-- Table structure for dept_info
-- ----------------------------
DROP TABLE IF EXISTS `dept_info`;
CREATE TABLE `dept_info` (
  `id` int(11) NOT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_info
-- ----------------------------
INSERT INTO `dept_info` VALUES ('1', '电商部');
INSERT INTO `dept_info` VALUES ('2', '金融部');
INSERT INTO `dept_info` VALUES ('3', '运营部');

-- ----------------------------
-- Table structure for goods_info
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gsname` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `gsprice` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `gsamount` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of goods_info
-- ----------------------------
INSERT INTO `goods_info` VALUES ('1', '零食', '11rmb', '53003', '1');
INSERT INTO `goods_info` VALUES ('2', '矿泉水', '2rmb', '42098', '1');
INSERT INTO `goods_info` VALUES ('3', '毛巾', '20rmb', '535', '2');
INSERT INTO `goods_info` VALUES ('4', '牙刷', '15rmb', '345', '2');
INSERT INTO `goods_info` VALUES ('5', '扫帚', '10rmb', '543', '2');
INSERT INTO `goods_info` VALUES ('6', '玩具', '60rmb', '234', '3');
INSERT INTO `goods_info` VALUES ('7', 'PS4', '2999rmb', '23', '3');
INSERT INTO `goods_info` VALUES ('8', '项链', '9999rmb', '52', '4');
INSERT INTO `goods_info` VALUES ('9', '戒指', '99999rmb', '12', '4');
INSERT INTO `goods_info` VALUES ('10', '手环', '200rmb', '353', '4');
INSERT INTO `goods_info` VALUES ('11', '笔记本电脑', '7999rmb', '56', '5');
INSERT INTO `goods_info` VALUES ('12', '主机', '5222rmb', '40', '5');
INSERT INTO `goods_info` VALUES ('30', '咖啡豆', '55rmb', '876', '1');
INSERT INTO `goods_info` VALUES ('14', '魔芋爽', '1rmb', '3021', '1');
INSERT INTO `goods_info` VALUES ('15', '螺狮粉', '10rmb', '120', '1');
INSERT INTO `goods_info` VALUES ('16', '椰汁', '7rmb', '302', '1');
INSERT INTO `goods_info` VALUES ('17', '脉动', '5rmb', '433', '1');
INSERT INTO `goods_info` VALUES ('18', '康师傅方便面', '7rmb', '304', '1');
INSERT INTO `goods_info` VALUES ('19', '拌面', '9rmb', '403', '1');
INSERT INTO `goods_info` VALUES ('20', '好吃点', '15rmb', '1112', '1');
INSERT INTO `goods_info` VALUES ('21', '薛梓洵', '无价', '1', '3');
INSERT INTO `goods_info` VALUES ('22', '可乐', '3rmb', '4032', '1');
INSERT INTO `goods_info` VALUES ('23', '雪碧', '3rmb', '3003', '1');
INSERT INTO `goods_info` VALUES ('24', '王老吉', '7rmb', '3002', '6');
INSERT INTO `goods_info` VALUES ('25', '洗手液', '20rmb', '303', '2');
INSERT INTO `goods_info` VALUES ('26', '纯牛奶', '4rmb', '3021', '1');
INSERT INTO `goods_info` VALUES ('27', '咖啡', '8rmb', '422', '1');
INSERT INTO `goods_info` VALUES ('28', '薯片', '8rmb', '3302', '1');
INSERT INTO `goods_info` VALUES ('31', '果茶', '25rmb', '532', '1');
INSERT INTO `goods_info` VALUES ('32', '洁厕灵', '26rmb', '767', '2');
INSERT INTO `goods_info` VALUES ('33', '口香糖', '5rmb', '3232', '1');
INSERT INTO `goods_info` VALUES ('34', '漱口水', '32rmb', '212', '2');
INSERT INTO `goods_info` VALUES ('35', '豆浆', '3rmb', '4412', '1');
INSERT INTO `goods_info` VALUES ('45', '土豆片', '10rmb', '3002', '1');
INSERT INTO `goods_info` VALUES ('44', '浴巾', '39rmb', '855', '2');
INSERT INTO `goods_info` VALUES ('42', '马桶刷', '20rmb', '533', '2');
INSERT INTO `goods_info` VALUES ('41', '手机壳', '40rmb', '552', '5');

-- ----------------------------
-- Table structure for handled_info
-- ----------------------------
DROP TABLE IF EXISTS `handled_info`;
CREATE TABLE `handled_info` (
  `id` int(11) DEFAULT NULL,
  `orNum` int(11) DEFAULT NULL,
  `orName` varchar(255) DEFAULT NULL,
  `orAmount` int(255) DEFAULT NULL,
  `orUserno` varchar(255) DEFAULT NULL,
  `orCus` varchar(255) DEFAULT NULL,
  `orAddress` varchar(255) DEFAULT NULL,
  `orPhone` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of handled_info
-- ----------------------------
INSERT INTO `handled_info` VALUES ('4', '10004', '矿泉水', '5', '用户2', '用户2', '北京', '654321');
INSERT INTO `handled_info` VALUES ('3', '10003', '矿泉水', '3', '用户1', '用户1', '山东青岛', '123456');
INSERT INTO `handled_info` VALUES ('4', '10014', '矿泉水', '3', '用户12', '用户12', '山东青岛', '123456');
INSERT INTO `handled_info` VALUES ('1', '10001', '矿泉水', '2', '张三', '张三', '山东青岛', '123456');
INSERT INTO `handled_info` VALUES ('9', '10009', '矿泉水', '3', '用户7', '用户7', '山东青岛', '123456');
INSERT INTO `handled_info` VALUES ('6', '10014', '矿泉水', '3', '用户12', '用户12', '山东青岛', '123456');
INSERT INTO `handled_info` VALUES ('51', '10019', '方便面', '2', '用户11', '用户11', '山东青岛', '1231232');
INSERT INTO `handled_info` VALUES ('1', '10014', '矿泉水', '3', '用户12', '用户12', '山东青岛', '123456');
INSERT INTO `handled_info` VALUES ('2', '10014', '矿泉水', '3', '用户12', '用户12', '山东青岛', '123456');
INSERT INTO `handled_info` VALUES ('3', '10014', '矿泉水', '3', '用户12', '用户12', '山东青岛', '123456');
INSERT INTO `handled_info` VALUES ('52', '10016', '可乐', '4', '用户99', '用户99', '山东淄博', '1919119');
INSERT INTO `handled_info` VALUES ('5', '10014', '矿泉水', '3', '用户12', '用户12', '山东青岛', '123456');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orNum` int(11) DEFAULT NULL,
  `orName` varchar(255) DEFAULT NULL,
  `orAmount` int(11) DEFAULT NULL,
  `orCus` varchar(255) DEFAULT NULL,
  `orUserno` varchar(11) DEFAULT NULL,
  `orAddress` varchar(255) DEFAULT NULL,
  `orPhone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('53', '10019', '可乐', '5', '用户99', '用户99', '山东淄博', '1919119');
INSERT INTO `order_info` VALUES ('54', '11111', '可乐', '5', '用户99', '用户99', '山东淄博', '+86121212121');
INSERT INTO `order_info` VALUES ('10', '10010', '矿泉水', '3', '用户8', '用户8', '山东青岛', '123456');
INSERT INTO `order_info` VALUES ('11', '10011', '矿泉水', '3', '用户9', '用户9', '山东青岛', '123456');
INSERT INTO `order_info` VALUES ('12', '10012', '矿泉水', '3', '用户10', '用户10', '山东青岛', '123456');
INSERT INTO `order_info` VALUES ('13', '10013', '矿泉水', '3', '用户11', '用户11', '山东青岛', '123456');
INSERT INTO `order_info` VALUES ('14', '10014', '矿泉水', '3', '用户12', '用户12', '山东青岛', '123456');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sex` varchar(255) DEFAULT NULL,
  `userno` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '男', '张三', '111', '12316@126.com', '18795673321', '山东青岛', '1');
INSERT INTO `user_info` VALUES ('2', '男', '李四', '222', '12518@126.com', '17795277856', '山东青岛', '1');
INSERT INTO `user_info` VALUES ('3', '男', '王五', '333', '12392@126.com', '18999765370', '山东青岛', '2');
INSERT INTO `user_info` VALUES ('4', '女', '薇薇安', '123', '18277@126.com', '17966214231', '韩国首尔', '1');
INSERT INTO `user_info` VALUES ('5', '男', '安迪', '123', '12999@126.com', '18468183588', '辽宁沈阳', '3');
INSERT INTO `user_info` VALUES ('6', '男', '陈信', '123', '12272@126.com', '13988468972', '山东青岛', '2');
INSERT INTO `user_info` VALUES ('7', '男', '富兰克林', '123', '19288@126.com', '18788473987', '美国纽约', '2');
INSERT INTO `user_info` VALUES ('8', '女', '红雪', '123', '17277@126.com', '15966884231', '山东青岛', '1');
INSERT INTO `user_info` VALUES ('9', '男', '李察', '123', '18222@126.com', '19223467827', '山东青岛', '2');
INSERT INTO `user_info` VALUES ('10', '男', '陈恺鑫', '123', '12845@126.com', '15860698871', '福建福鼎', '3');
INSERT INTO `user_info` VALUES ('11', '男', '陈炳一', '111', '13992@126.com', '15887002391', '云南昆明', '2');
INSERT INTO `user_info` VALUES ('12', '男', '王晨晨', '123', '18222@126.com', 'N--U--L--L', '福建福鼎', '1');
INSERT INTO `user_info` VALUES ('13', '男', '吴鑫锭', '123', '12738@126.com', 'N--U--L--L', '福建福鼎', '1');
INSERT INTO `user_info` VALUES ('14', '男', 'zs', '111', '13827@126.com', 'N--U--L--L', 'NUll', '3');
INSERT INTO `user_info` VALUES ('15', '未知', '皮卡丘', '111', '13123@126.com', '184681825', '宝可梦世界', '2');
INSERT INTO `user_info` VALUES ('16', '未知', '薛梓洵', '111', '12341@126.com', 'N--U--L--L', '山西太原', '3');
INSERT INTO `user_info` VALUES ('17', '男', '赵信', '111', '12738@126.com', '111', '福建福鼎', '1');
INSERT INTO `user_info` VALUES ('18', '男', '晓晨', '111', '13827@126.com', '11111', 'NUll', '2');
INSERT INTO `user_info` VALUES ('19', '未知', '陈悦悦', '12121', '13123@126.com', '12121212133', '宝可梦世界', '3');
INSERT INTO `user_info` VALUES ('20', '未知', '锁钰', '12121', '12341@126.com', '19191192132', '山西太原', '3');
INSERT INTO `user_info` VALUES ('21', '未知', '乐齐', '12121', '12341@126.com', '19191193111', '山西太原', '3');
INSERT INTO `user_info` VALUES ('22', '女', '吴邪', '123', '18217@126.com', '17232414233', '韩国', '1');
INSERT INTO `user_info` VALUES ('23', '男', 'admin', '111', '18217@126.com', '17232414234', '山东青岛', '1');
INSERT INTO `user_info` VALUES ('24', null, '于冰', '2121', '12698@126.com', '17865327780', '山东青岛', null);
INSERT INTO `user_info` VALUES ('25', null, null, 'admin', null, '123', null, null);
INSERT INTO `user_info` VALUES ('27', null, '薛金', '123456', '12698@126.com', '17865327780', '山东青岛', null);
INSERT INTO `user_info` VALUES ('28', null, '齐三元', '123456', '12698@126.com', '17865327780', '云南昆明', null);
INSERT INTO `user_info` VALUES ('29', null, '薛梓洵', '1213', '12698@126.com', '17865327780', '山东青岛', null);
