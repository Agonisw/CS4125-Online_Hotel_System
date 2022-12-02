/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : hotel_one

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2022-11-23 17:21:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `product_id` int(10) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  `spec_id` int(10) DEFAULT NULL,
  `total_price` decimal(10,4) DEFAULT NULL,
  `create_time` varchar(100) DEFAULT NULL,
  `start_time` varchar(100) DEFAULT NULL,
  `end_time` varchar(100) DEFAULT NULL,
  `day` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------

-- ----------------------------
-- Table structure for discount
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `discount_rate` double(10,2) DEFAULT NULL,
  `is_use` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discount
-- ----------------------------
INSERT INTO `discount` VALUES ('35', '21', '0.90', '1');
INSERT INTO `discount` VALUES ('36', '22', '0.90', '1');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(64) NOT NULL,
  `path` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限码 授权(多个用逗号分隔，如：user:list,user:create)',
  `component` varchar(255) DEFAULT NULL,
  `type` int(5) NOT NULL COMMENT '类型     0：目录   1：菜单   2：按钮',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `orderNum` int(11) DEFAULT NULL COMMENT '排序',
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `statu` int(5) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '系统管理', '', 'sys:manage', '', '0', 'el-icon-s-operation', '1', '2021-01-15 18:58:18', '2021-08-12 16:39:34', '1');
INSERT INTO `menu` VALUES ('2', '1', '用户管理', '/user', 'sys:user:list', 'User', '1', 'el-icon-s-custom', '1', '2021-01-15 19:03:45', '2022-11-01 02:00:54', '1');
INSERT INTO `menu` VALUES ('3', '1', '角色管理', '/role', 'sys:role:list', 'Role', '1', 'el-icon-rank', '2', '2021-01-15 19:03:45', '2021-01-15 19:03:48', '1');
INSERT INTO `menu` VALUES ('4', '1', '菜单管理', '/menu', 'sys:menu:list', 'Menu', '1', 'el-icon-menu', '3', '2021-01-15 19:03:45', '2021-01-15 19:03:48', '1');
INSERT INTO `menu` VALUES ('7', '3', '添加角色', '', 'sys:role:save', '', '2', '', '1', '2021-01-15 23:02:25', '2021-01-17 21:53:14', '0');
INSERT INTO `menu` VALUES ('9', '2', '添加用户', null, 'sys:user:save', null, '2', null, '1', '2021-01-17 21:48:32', null, '1');
INSERT INTO `menu` VALUES ('10', '2', '修改用户', null, 'sys:user:update', null, '2', null, '2', '2021-01-17 21:49:03', '2021-01-17 21:53:04', '1');
INSERT INTO `menu` VALUES ('11', '2', '删除用户', null, 'sys:user:delete', null, '2', null, '3', '2021-01-17 21:49:21', null, '1');
INSERT INTO `menu` VALUES ('12', '2', '分配角色', null, 'sys:user:role', null, '2', null, '4', '2021-01-17 21:49:58', null, '1');
INSERT INTO `menu` VALUES ('13', '2', '修改密码', null, 'sys:user:updatepass', null, '2', null, '5', '2021-01-17 21:50:36', '2022-10-17 06:24:58', '1');
INSERT INTO `menu` VALUES ('14', '3', '修改角色', null, 'sys:role:update', null, '2', null, '2', '2021-01-17 21:51:14', null, '1');
INSERT INTO `menu` VALUES ('15', '3', '删除角色', null, 'sys:role:delete', null, '2', null, '3', '2021-01-17 21:51:39', null, '1');
INSERT INTO `menu` VALUES ('16', '3', '分配权限', null, 'sys:role:perm', null, '2', null, '5', '2021-01-17 21:52:02', null, '1');
INSERT INTO `menu` VALUES ('17', '4', '添加菜单', null, 'sys:menu:save', null, '2', null, '1', '2021-01-17 21:53:53', '2021-01-17 21:55:28', '1');
INSERT INTO `menu` VALUES ('18', '4', '修改菜单', null, 'sys:menu:update', null, '2', null, '2', '2021-01-17 21:56:12', null, '1');
INSERT INTO `menu` VALUES ('19', '4', '删除菜单', null, 'sys:menu:delete', null, '2', null, '3', '2021-01-17 21:56:36', null, '1');
INSERT INTO `menu` VALUES ('59', '1', '商品管理', '/product', 'sys:product:list', 'Product', '1', 'el-icon-s-goods', '4', '2022-11-23 08:50:51', '2022-11-23 09:24:58', '1');
INSERT INTO `menu` VALUES ('60', '1', '订单管理', '/order', 'sys:order:list', 'Order', '1', 'el-icon-s-order', '5', '2022-11-23 08:51:27', '2022-11-23 09:25:24', '1');
INSERT INTO `menu` VALUES ('61', '1', '资讯管理', '/notice', 'sys:news:list', 'News2', '1', 'el-icon-s-promotion', '6', '2022-11-23 08:51:54', '2022-11-23 09:26:18', '1');
INSERT INTO `menu` VALUES ('62', '1', '统计管理', '/statistical', 'sys:statistical:list', 'Statistical', '1', 'el-icon-s-data', '7', '2022-11-23 08:52:50', '2022-11-23 09:25:34', '1');
INSERT INTO `menu` VALUES ('63', '59', '商品添加', '/', 'sys:product:save', '/', '2', '/', '1', '2022-11-23 09:33:59', null, '1');
INSERT INTO `menu` VALUES ('64', '59', '商品编辑', '/', 'sys:product:update', '/', '2', '/', '2', '2022-11-23 09:37:45', null, '1');
INSERT INTO `menu` VALUES ('65', '59', '商品删除', '/', 'sys:product:del', '/', '2', '/', '3', '2022-11-23 09:38:12', null, '1');
INSERT INTO `menu` VALUES ('66', '60', '订单删除', '/', 'sys:order:del', '/', '2', '/', '1', '2022-11-23 09:38:48', null, '1');
INSERT INTO `menu` VALUES ('67', '61', '资讯添加', '/', 'sys:news:save', '/', '2', '/', '1', '2022-11-23 09:39:27', null, '1');
INSERT INTO `menu` VALUES ('68', '61', '资讯编辑', '/', 'sys:news:update', '/', '2', '/', '2', '2022-11-23 09:39:47', null, '1');
INSERT INTO `menu` VALUES ('69', '61', '资讯删除', '/', 'sys:news:del', '/', '2', '/', '3', '2022-11-23 09:40:07', null, '1');
INSERT INTO `menu` VALUES ('70', '59', '添加规格', '/', 'sys:spec:save', '/', '2', '/', '4', '2022-11-23 11:25:35', null, '1');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `news` longtext,
  `create_time` varchar(30) DEFAULT NULL,
  `title` varchar(80) DEFAULT NULL,
  `image_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '我的个人博客总共展示了三个版本，界面也经历了由“简单”到“复杂”再到“简单”，颜色从“色泽单一”到“五彩斑斓”再到“局部点缀”的过程。原来一年一个版本！而每次改版的契机都是被百度惩罚！界面不要频繁更换！好好检查代码，有没有冗余、结构有没有不合理的地方。我的个人博客总共展示了三个版本，界面也经历了由“简单”到“复杂”再到“简单”，颜色从“色泽单一”到“五彩斑斓”再到“局部点缀”的过程。原来一年一个版本！而每次改版的契机都是被百度惩罚！界面不要频繁更换！好好检查代码，有没有冗余、结构有没有不合理的地方。', '2022-11-15 11:24:21', '分享我的个人博客访问量如何做到IP从10到2000的(图文)', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-05//d3c75c7b-5c69-4119-89ca-c5443d5c984b_006mowZngy1gj2yt2wmqeg308c069dxy.gif');
INSERT INTO `news` VALUES ('2', '11月4日上午，国家主席习近平在北京人民大会堂会见来华正式访问的德国总理朔尔茨。 新华社记者 姚大伟 摄  习近平指出，你是中共二十大召开后首位来访的欧洲领导人，这也是你就任以来首次访华。相信访问将增进双方了解和互信，深化各领域务实合作，为下阶段中德关系发展做好谋划。  习近平强调，中德关系发展到今天的高水平，离不开中德几代领导人的高瞻远瞩和政治魄力。今年恰逢中德建交50周年。50载历程表明，只要秉持相互尊重、求同存异、交流互鉴、合作共赢原则，两国关系的大方向就不会偏，步子也会走得很稳。当前，国际形势复杂多变，中德作为有影响力的大国，在变局、乱局中更应该携手合作，为世界和平与发展作出更多贡献。中方愿同德方共同努力，构建面向未来的全方位战略伙伴关系，推动中德、中欧关系取得新的发展。', '2022-11-15 11:32:20', '习近平会见德国总理朔尔茨', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-05//791c9ea6-7761-403d-816c-5f7d28a34c93_472309f790529822e9c44965fe5e4ec00846d4c4.webp');
INSERT INTO `news` VALUES ('3', '2021年4月29日，中国空间站的首舱，也就是天和核心舱发射升空，核心舱里配置的环控生保系统，让它具备了和地面一样的空气、温度等生存环境，所携带的实验机柜也让核心舱具备了开展科学实验的基本条件。  有了实验室的场所，还得有物资保障。去年5月，天舟二号货运飞船发射并与天和核心舱对接，带去了航天员进入空间站所需要的吃穿用度。屋子和物资都有了，就可以有人上太空工作了。去年6月17日，航天员聂海胜、刘伯明、汤洪波搭乘神舟十二号载人飞船飞向太空、进驻天和核心舱。作为空间站建设阶段的首个飞行乘组，他们开始了太空家园的建设。三名航天员在核心舱内搞装修、拆包裹、装WIFI，还到舱外安装了工具、设备、验证了舱外服和机械臂的性能。', '2022-11-15 11:34:08', '“硬核”筑梦！3D动画演示中国空间站建造历程', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-05//156956ec-f828-4ac9-80fb-c09e7abbeb64_2103bc67dd881dca5edf61adcd7cad06.gif');
INSERT INTO `news` VALUES ('4', '据奔腾融媒，今天召开的呼和浩特市新冠肺炎疫情防控新闻发布会上表示：为科学精准细致做好社会管控工作，呼和浩特要求必须畅通应急通道、强化服务保障：不准对居民户门一封了之、单元门一锁了之、小区大门一关了之。  近期呼和浩特将对全市中高风险小区（村）管控措施落实情况开展一轮全面摸底排查。单元门、隔离围挡只能通过张贴封条的方式进行封闭，坚决杜绝设置门锁、插销、挂钩等锁闭设施。同时，安排24小时巡逻值守，并充分运用电子监控设备加强管理。如再出现锁门事件，广大市民可拨打12345投诉举报，一经查实，严肃查处相关责任人。', '2022-11-15 23:34:21', '呼和浩特：不准对居民户门一封了之、单元门一锁了之、小区大门一关了之', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-06//3b099b1b-45dc-4363-8378-dd05ec1c84d4_1.png');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(100) DEFAULT NULL,
  `product_id` int(10) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `create_time` varchar(100) DEFAULT NULL,
  `is_delivery` varchar(10) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `spec_name` varchar(20) DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  `start_time` varchar(100) DEFAULT NULL,
  `end_time` varchar(100) DEFAULT NULL,
  `day` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `introduction` varchar(500) DEFAULT NULL,
  `launch_time` varchar(100) DEFAULT NULL,
  `sort` varchar(10) DEFAULT NULL,
  `release_area` varchar(100) DEFAULT NULL,
  `image_url` varchar(200) DEFAULT NULL,
  `origin_image_url` varchar(200) DEFAULT NULL,
  `show_image_url_one` varchar(200) DEFAULT NULL,
  `show_image_url_two` varchar(200) DEFAULT NULL,
  `recommend_type` varchar(10) DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  `wifi` varchar(30) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '测试酒店', '如家', '100', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-09 11:20:32', '1', '11', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//4636de91-5ed3-4e80-80ea-e40287de657b_9.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//cdda94a3-0ef5-41d1-884a-e0bfac3b5d2f_10.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//c21ee0c2-0d9b-4590-88ef-01c0c5ac1286_11.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//e026d019-cd51-4131-8f7c-31c781ee804a_12.png', null, '0', null, null);
INSERT INTO `product` VALUES ('2', '测试酒店11', '如家', '1', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-09 11:35:33', '1', '11', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//675974f7-84d7-4bb3-8a44-1e32c0a8f1fe_13.png', null, null, null, null, '11', '111111', '5674');
INSERT INTO `product` VALUES ('3', '测试酒店', '如家', '1', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-09 11:35:54', '1', '1', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//1a9661f7-d7b2-4492-895b-eb3c2b939b64_14.png', null, null, null, null, '0', null, null);
INSERT INTO `product` VALUES ('4', '测试酒店', '如家', '1', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-09 11:36:16', '1', '1', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//9d8d77ec-afc9-4f59-8449-6826ea9b006c_15.png', null, null, null, null, '0', null, null);
INSERT INTO `product` VALUES ('5', '测试酒店', '如家', '1', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-10 00:00:00', '1', '1', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//eadcc4cd-5ada-4806-8dd4-443efaa4d4bf_16.png', null, null, null, null, '0', null, null);
INSERT INTO `product` VALUES ('6', '测试酒店', '如家', '1', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-09 11:56:46', '1', '1', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//141f273e-4567-4c64-84c5-4e479ac3fa58_2.png', null, null, null, null, '0', null, null);
INSERT INTO `product` VALUES ('7', '测试酒店', '如家', '1', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-09 11:57:09', '1', '1', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//86312e16-1f52-435d-86ca-b58352b66171_6.png', null, null, null, null, '0', null, null);
INSERT INTO `product` VALUES ('8', '测试酒店', '如家', '1', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-09 11:57:23', '1', '1', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//c9ff58e8-1992-4dfc-8881-8aad861e0491_15.png', null, null, null, null, '0', null, null);
INSERT INTO `product` VALUES ('9', '测试酒店', '如家', '1', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-09 11:57:37', '1', '1', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//2cf6a487-9df8-4b42-8252-174edae1ad16_9.png', null, null, null, null, '0', null, null);
INSERT INTO `product` VALUES ('10', '测试酒店', '如家', '1', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-09 14:29:26', '1', '1', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//034b7ca6-3f3e-4e5f-825c-b8169f4ce4ef_7.png', null, null, null, null, '0', null, null);
INSERT INTO `product` VALUES ('11', '测试酒店', '如家', '1', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-09 14:30:02', '1', '1', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//adc372fd-381b-40ad-84d0-77b4441fce33_9.png', null, null, null, null, '0', null, null);
INSERT INTO `product` VALUES ('19', '测试酒店', '如家', '11', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-10-10 11:03:27', '1', '11', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//5804c9a3-075e-4fab-84b2-34326d53c667_1.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//546eac53-1488-4f4e-8e4e-79a0736d2c8e_2.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//59e573c4-cc7a-4dff-830f-436792f9e4b9_3.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//e34b32ee-3f45-45b1-8bc8-298a90f62c77_4.png', '1', '35', '12323', '11');
INSERT INTO `product` VALUES ('31', '测试酒店', '如家', '200', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-11-06 23:22:45', '3', '日本', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//79fb2ffa-733b-444e-8821-ef0720c03de7_5.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//178ca99f-ce59-4970-818e-6811366b8e56_6.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//eaa11e27-a6b3-411d-8c87-6ede666121a2_7.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-12//48f00cbd-c06b-4790-828b-ab552a182468_7.pnh.webp', '2', '3', '12345', '7#102');
INSERT INTO `product` VALUES ('33', '胡月万', '如家', '100', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-11-14 21:02:01', '2', '上海市广松大道109', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-14//2371d001-f719-4282-894d-b3cbf8e9a611_3.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-14//d0e52b33-bd7b-410e-88ea-e4a079414e0b_4.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-14//a0bae573-1cf6-4315-8168-f6d531e4f709_5.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-14//bdcd89c3-711d-49c7-82db-ca6a5d711477_11.png', null, '9', '12345', '8011');
INSERT INTO `product` VALUES ('34', '柏悦湾', '如家', '100', '酒店共有客房340套，设有不同价位的标准间、商务间、三人间、套房、摄制组专业用房等多种房型，房间按照四星级酒店标准配置。中西餐厅可容纳600人同时用餐，自助餐厅可容纳230人同时就餐，并带有一个可容纳40人的百花厅，户外烧烤场地可容纳200人同时就餐并提供全套专业环保型烧烤设备，6个包间宽敞舒适，并设有咖啡厅、茶吧等商务洽谈场所。6间豪华音效视听室，可容纳25人-80人娱乐、拍摄、小型发布会等；同时还有配套齐全的棋牌室、台球、乒乓球、羽毛球、拓展训练、健身器材等康体娱乐设施一应俱全。', '2022-11-14 21:39:20', '2', '上海市繁华大道', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-14//d6b10924-6316-460e-88a3-9d5248d7379f_3.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-14//b297e365-0c39-4c52-8c1e-3d28dc5ac22a_4.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-14//8e4daf0c-99aa-417c-886d-4f7c2ebe1974_5.png', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-14//c3afd150-7e19-4f4c-84c2-d12f2fcaea11_10.png', null, '1', '12345', '9001');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `code` varchar(64) NOT NULL,
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `created` varchar(100) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `statu` int(5) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('6', '超级管理员1', 'admin', '系统默认最高权限，不可以编辑和任意修改', '2021-01-16 13:29:03', '2021-01-17 15:50:45', '1');
INSERT INTO `role` VALUES ('25', '运维管理', 'TEST', '运维管理', '2022-10-17 14:27:26', null, '1');
INSERT INTO `role` VALUES ('40', '工作人员', 'GZ', '工作人员', '2022-11-23 11:18:54', null, '1');
INSERT INTO `role` VALUES ('41', '测试角色', 'TER', '测试的角色', '2022-11-23 12:39:41', null, '1');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2277 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('2068', '25', '1');
INSERT INTO `role_menu` VALUES ('2069', '25', '2');
INSERT INTO `role_menu` VALUES ('2070', '25', '9');
INSERT INTO `role_menu` VALUES ('2071', '25', '10');
INSERT INTO `role_menu` VALUES ('2072', '25', '3');
INSERT INTO `role_menu` VALUES ('2073', '25', '7');
INSERT INTO `role_menu` VALUES ('2074', '25', '14');
INSERT INTO `role_menu` VALUES ('2076', '25', '15');
INSERT INTO `role_menu` VALUES ('2077', '25', '16');
INSERT INTO `role_menu` VALUES ('2078', '25', '4');
INSERT INTO `role_menu` VALUES ('2079', '25', '17');
INSERT INTO `role_menu` VALUES ('2080', '25', '18');
INSERT INTO `role_menu` VALUES ('2081', '25', '19');
INSERT INTO `role_menu` VALUES ('2250', '6', '1');
INSERT INTO `role_menu` VALUES ('2251', '6', '2');
INSERT INTO `role_menu` VALUES ('2252', '6', '9');
INSERT INTO `role_menu` VALUES ('2253', '6', '10');
INSERT INTO `role_menu` VALUES ('2254', '6', '11');
INSERT INTO `role_menu` VALUES ('2255', '6', '12');
INSERT INTO `role_menu` VALUES ('2256', '6', '13');
INSERT INTO `role_menu` VALUES ('2257', '6', '3');
INSERT INTO `role_menu` VALUES ('2258', '6', '7');
INSERT INTO `role_menu` VALUES ('2259', '6', '14');
INSERT INTO `role_menu` VALUES ('2260', '6', '15');
INSERT INTO `role_menu` VALUES ('2261', '6', '16');
INSERT INTO `role_menu` VALUES ('2262', '6', '4');
INSERT INTO `role_menu` VALUES ('2263', '6', '17');
INSERT INTO `role_menu` VALUES ('2264', '6', '18');
INSERT INTO `role_menu` VALUES ('2265', '6', '19');
INSERT INTO `role_menu` VALUES ('2266', '6', '59');
INSERT INTO `role_menu` VALUES ('2267', '6', '60');
INSERT INTO `role_menu` VALUES ('2268', '6', '61');
INSERT INTO `role_menu` VALUES ('2269', '6', '62');
INSERT INTO `role_menu` VALUES ('2270', '40', '1');
INSERT INTO `role_menu` VALUES ('2271', '40', '59');
INSERT INTO `role_menu` VALUES ('2272', '40', '63');
INSERT INTO `role_menu` VALUES ('2273', '41', '1');
INSERT INTO `role_menu` VALUES ('2274', '41', '59');
INSERT INTO `role_menu` VALUES ('2275', '41', '63');
INSERT INTO `role_menu` VALUES ('2276', '41', '65');

-- ----------------------------
-- Table structure for spec
-- ----------------------------
DROP TABLE IF EXISTS `spec`;
CREATE TABLE `spec` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `spec_name` varchar(100) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `product_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spec
-- ----------------------------
INSERT INTO `spec` VALUES ('16', '单人间/晚', '100', '19');
INSERT INTO `spec` VALUES ('17', '双人间/晚', '200', '19');
INSERT INTO `spec` VALUES ('18', '三人间/晚', '300', '19');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `user_type` varchar(10) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `image_url` varchar(200) DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  `money` double(10,2) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '$2a$10$TThv225ACwuvkiwoeBP2W.atsNAABsIzdPpQivXglxbr5ZfwdblEi', '管理员', '1', '19932434', '海南', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-10-09//23d00b1c-fbfa-44f0-8e84-cc3c0aeb3bfd_2.png', '0', '0.00', '999343333@qq.com');
INSERT INTO `user` VALUES ('12', 'xiaobai', '12345', '小白', '2', '1998834333', '上海市', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-14//928ca929-7101-4cb4-85a5-74504c6c6d1a_4.png', '4', '5419.00', '2979817520@qq.com');
INSERT INTO `user` VALUES ('17', 'ceshi', '$2a$10$i6ouZc94vJwYdqLG.jpz/u.f/jKpO5ZBBLYUeYbqWPJiy98qHIZVO', '测试', '1', '1998838433', '海南', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-23//28ef500f-b4fe-49c2-86f7-591b21697a99_胡歌_34240999234394329_汉族_男_安徽.jpg', '0', '0.00', '19993');
INSERT INTO `user` VALUES ('19', 'test', '$2a$10$2jqnyoPlXs7gVXCkK.LibepdJs4FtcmrK8NAypV26.YUfrIJbZWFW', '测试', '1', '19988834833', '上海市', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-23//d0a2da6a-2d3e-4540-8753-7cd86237c4d8_6.png', '0', '0.00', '1999934@qq.com');
INSERT INTO `user` VALUES ('22', 'xiaohuang', '$2a$10$ShpFDOJUljaAoPVVcgTPc./EOhyex3ikkATVczht/M7zvW7aFu5MK', '小黄', '2', '199888384833', '上海市11', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-11-23//4ac2c067-aac4-4757-8207-a0cd9102fe33_6.png', '2', '85.00', '1828838@qq.com');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('104', '1', '6');
INSERT INTO `user_role` VALUES ('109', '12', '25');
INSERT INTO `user_role` VALUES ('112', '17', '40');
INSERT INTO `user_role` VALUES ('113', '19', '41');
