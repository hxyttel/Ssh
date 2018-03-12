/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : enrollment

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-09-21 15:07:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for academy
-- ----------------------------
DROP TABLE IF EXISTS `academy`;
CREATE TABLE `academy` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(255) DEFAULT NULL,
  `amajor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of academy
-- ----------------------------
INSERT INTO `academy` VALUES ('6', '北京大学', '成人教育');
INSERT INTO `academy` VALUES ('8', '上海交通大学', '国家开放大学');
INSERT INTO `academy` VALUES ('9', '江西理工', '成人教育');

-- ----------------------------
-- Table structure for accounting
-- ----------------------------
DROP TABLE IF EXISTS `accounting`;
CREATE TABLE `accounting` (
  `agid` int(11) NOT NULL AUTO_INCREMENT,
  `agname` varchar(255) DEFAULT NULL,
  `agpicture` varchar(255) DEFAULT NULL,
  `agaddress` varchar(255) DEFAULT NULL,
  `agtitle` varchar(255) DEFAULT NULL,
  `agphoto1` varchar(255) DEFAULT NULL,
  `agphoto2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`agid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accounting
-- ----------------------------
INSERT INTO `accounting` VALUES ('1', '会计培训', 'img/mainImg/rulertriangle.png', 'accounting.action', '会计培训', 'img/acc_art_voc/acc1_0.jpg', 'img/acc_art_voc/acc2_0.png');

-- ----------------------------
-- Table structure for adult
-- ----------------------------
DROP TABLE IF EXISTS `adult`;
CREATE TABLE `adult` (
  `atid` int(11) NOT NULL AUTO_INCREMENT,
  `atname` varchar(255) DEFAULT NULL,
  `atpicture` varchar(255) DEFAULT NULL,
  `ataddress` varchar(255) DEFAULT NULL,
  `attitle` varchar(255) DEFAULT NULL,
  `atphoto1` varchar(255) DEFAULT NULL,
  `atcontent` varchar(255) DEFAULT NULL,
  `atphoto2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`atid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adult
-- ----------------------------
INSERT INTO `adult` VALUES ('1', '成人教育', 'img/mainImg/rocket.png', 'getSchool_adult', '成人考试', 'img/adu_dis_cou/adu1_0.jpg', '在职专/本科2017春季入学', 'img/adu_dis_cou/adu2_0.png');

-- ----------------------------
-- Table structure for arts
-- ----------------------------
DROP TABLE IF EXISTS `arts`;
CREATE TABLE `arts` (
  `asid` int(11) NOT NULL AUTO_INCREMENT,
  `asname` varchar(255) DEFAULT NULL,
  `aspicture` varchar(255) DEFAULT NULL,
  `asaddress` varchar(255) DEFAULT NULL,
  `astitle` varchar(255) DEFAULT NULL,
  `asphoto1` varchar(255) DEFAULT NULL,
  `asphoto2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`asid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of arts
-- ----------------------------
INSERT INTO `arts` VALUES ('1', '艺考培训', 'img/mainImg/trophy.png', 'arts.action', '艺术考试', 'img/acc_art_voc/art1_2.jpg', 'img/acc_art_voc/art3_0.png');

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `ctype` varchar(255) DEFAULT NULL,
  `cqqnumber` varchar(255) DEFAULT NULL,
  `cphone` varchar(255) DEFAULT NULL,
  `cpeople` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES ('1', '成考', '894040369', '15279089961', '郭老师');
INSERT INTO `contact` VALUES ('2', '成考', '235641294', '13678730821', '王老师');
INSERT INTO `contact` VALUES ('3', '远程', '287908123', '15167981235', '邱老师');
INSERT INTO `contact` VALUES ('6', '远程', '894340169', '13109876541', '黄老师');

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `cyid` int(11) NOT NULL AUTO_INCREMENT,
  `cyname` varchar(255) DEFAULT NULL,
  `cypicture` varchar(255) DEFAULT NULL,
  `cyaddress` varchar(255) DEFAULT NULL,
  `cytitle` varchar(255) DEFAULT NULL,
  `cyphoto1` varchar(255) DEFAULT NULL,
  `cycontent` varchar(255) DEFAULT NULL,
  `cyphoto2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cyid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES ('1', '国家开放', 'img/mainImg/document.png', 'getSchool_country', '国家开放大学', 'img/adu_dis_cou/cou1_0.jpg', '教育部直属，以现代信息技术为支撑，学历教育与非学历教育并举，实施远程开放教育的新型高等学校。学校在广播电视大学基础上组建，面向全社会成员，强调优质教育资源的聚集、整合与共享，强调以现代信息技术为支撑，探索现代信息技术与教育的深度融合。', 'img/adu_dis_cou/cou2_0.png');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `dnumber` varchar(255) DEFAULT NULL,
  `ddatetime` varchar(255) DEFAULT NULL,
  `daddress` varchar(255) DEFAULT NULL,
  `dname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '001', '2016-02-12', '赣州市章贡区红旗大道', '董事会');
INSERT INTO `department` VALUES ('2', '002', '2016-10-05', '赣州市章贡区楼梯岭', '招生部');
INSERT INTO `department` VALUES ('5', '003', '2017-09-04', '赣州市章贡区红旗大道', '财务部');

-- ----------------------------
-- Table structure for distance
-- ----------------------------
DROP TABLE IF EXISTS `distance`;
CREATE TABLE `distance` (
  `deid` int(11) NOT NULL AUTO_INCREMENT,
  `dename` varchar(255) DEFAULT NULL,
  `depicture` varchar(255) DEFAULT NULL,
  `deaddress` varchar(255) DEFAULT NULL,
  `detitle` varchar(255) DEFAULT NULL,
  `dephoto1` varchar(255) DEFAULT NULL,
  `decontent` varchar(255) DEFAULT NULL,
  `dephoto2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of distance
-- ----------------------------
INSERT INTO `distance` VALUES ('1', '远程教育', 'img/mainImg/cloud.png', 'getSchool_distance', '远程教育', 'img/adu_dis_cou/dis1_0.jpg', '可以在家上的大学', 'img/adu_dis_cou/dis2_0.png');

-- ----------------------------
-- Table structure for finance
-- ----------------------------
DROP TABLE IF EXISTS `finance`;
CREATE TABLE `finance` (
  `feid` int(11) NOT NULL AUTO_INCREMENT,
  `feneeddmoney` float DEFAULT NULL,
  `frpractical` float DEFAULT NULL,
  `fedate` varchar(255) DEFAULT NULL,
  `feway` varchar(255) DEFAULT NULL,
  `studentid` int(11) DEFAULT NULL,
  `sfestate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`feid`),
  KEY `FKCD244FDAC5E0D93A` (`studentid`),
  CONSTRAINT `FKCD244FDAC5E0D93A` FOREIGN KEY (`studentid`) REFERENCES `student` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of finance
-- ----------------------------
INSERT INTO `finance` VALUES ('1', '3000', '3000', '2017-09-11 20:52:25', '现金', '4', '已缴费');
INSERT INTO `finance` VALUES ('3', '4000', '4000', '2017-09-12', '支付宝', '39', '已缴费');
INSERT INTO `finance` VALUES ('4', '3000', '2000', '2017-08-27', '支付宝', '21', '已缴费');
INSERT INTO `finance` VALUES ('5', '1500', '1500', '2017-08-12', '微信', '40', '已缴费');
INSERT INTO `finance` VALUES ('6', '5000', '3000', '2017-09-01', '现金', '42', '未缴完');
INSERT INTO `finance` VALUES ('7', '6000', '6000', '2017-08-30', '微信', '24', '已缴费');
INSERT INTO `finance` VALUES ('8', '5000', '5000', '2017-09-14', '支付宝', '43', '已缴费');
INSERT INTO `finance` VALUES ('9', '6000', '4000', '2017-08-23', '支付宝', '38', '未缴完');
INSERT INTO `finance` VALUES ('10', '5000', '5000', '2017-09-13', '支付宝', '6', '已缴费');
INSERT INTO `finance` VALUES ('11', '3000', '3000', '2017-09-21 08:28:46', '现金', '5', '已缴费');
INSERT INTO `finance` VALUES ('12', '8500', '4500', '2017-09-13', '微信', '8', '未缴完');
INSERT INTO `finance` VALUES ('13', '4500', '4500', '2017-09-20 16:32:02', '现金', '48', '已缴费');
INSERT INTO `finance` VALUES ('14', '8000', '8000', '2017-09-20 16:32:02', '微信', '44', '已缴费');
INSERT INTO `finance` VALUES ('15', '8000', '8000', '2017-09-20 17:01:33', '微信', '39', '已缴费');
INSERT INTO `finance` VALUES ('16', '3000', '3000', '2017-09-21 10:19:51', '现金', '45', '已缴费');
INSERT INTO `finance` VALUES ('17', '5000', '5000', '42802', '微信', '49', '已缴费');

-- ----------------------------
-- Table structure for general
-- ----------------------------
DROP TABLE IF EXISTS `general`;
CREATE TABLE `general` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `glogo` varchar(255) DEFAULT NULL,
  `gintroduce` varchar(1000) DEFAULT NULL,
  `gpicture` varchar(255) DEFAULT NULL,
  `gtitle` varchar(255) DEFAULT NULL,
  `gmajor` varchar(255) DEFAULT NULL,
  `gdiploma` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of general
-- ----------------------------
INSERT INTO `general` VALUES ('9', 'backstage/upload/s1.png', '北京外国语大学（Beijing Foreign Studies University），简称北外，由中华人民共和国教育部直属，位列国家首批“211工程”，入选“985工程优势学科创新平台”、“2011计划”，为财政部6所“小规模试点高校“之一，国际大学翻译学院联合会成员，设有研究生院，是中国外国语类高等院校中历史悠久、教授语种最多、办学层次齐全的全国重点大学，被誉为“共和国外交官摇篮”。 北京外国语大学前身是1941年成立于延安的中国人民抗日军事政治大学三分校俄文大队，后发展为延安外国语学校，建校始隶属于中国共产党领导。新中国成立后，学校归外交部领导。1954年，更名为北京外国语学院；1959年，与北京俄语学院合并组建新的北京外国语学院。1980年后直属国家教育部领导。1994年，正式更名为北京外国语大学。', 'backstage/upload/school1.jpg', '北京大学 北京外国语大学，简称北外，由中华人民共和国教育部直属，位列国家首批“211工程”，入选“985工程优势学科创新平台”、“2011计划”', 'backstage/upload/zy1.png', 'backstage/upload/byz1.jpg');
INSERT INTO `general` VALUES ('11', 'backstage/upload/s3.png', '东华理工大学位于著名的“才子之乡”——江西省抚州市，是江西省人民政府与工业和信息化部国防科技工业局（原国防科工委）共建的一所具有地学和核科学特色，以理工为主，文、管、经、法、教兼备的综合性大学。 学校现有在校生29000余人（含留学生），是全国首批学士学位和第四批硕士学位授权单位。现有57个本科专业，其中11个国防军工专业、1个国防重点专业、1个国防紧缺专业、3个国家级特色专业、14个江西省品牌专业、8个省级特色专业，14个省部级重点学科，65个硕士点，有4个专业合作培养博士生，有10个领域的工程硕士学位授予权，具有在职人员以同等学历申请硕士学位授权资格。', 'backstage/upload/school3.jpg', '东华理工大学 涵盖法学 、行政管理 、会计学 、英语 、汉语言文学 、体育教育 、艺术设计学 、市场营销 、电子信息工程 、计算机科学与技术 、通信工程 、自动化 、资源勘查工程 、土木工程 、测绘工程广告学', 'backstage/upload/zy3.png', 'backstage/upload/byz3.jpg');
INSERT INTO `general` VALUES ('12', 'backstage/upload/s4.png', '学校创办于1958年6月，赣南师范专科学校，1984年升格为本科师范院校，成为省属两所本科师范院校之一，2003年成为硕士学位授予权单位，2009获教育硕士专业学位研究生培养资格。 涵盖法学、经济学、史学、文学、理学、工学、教育学、管理学、艺术学等9个学科门类，形成了以教师教育、文理学科为特色，文理管工多学科协调发展的办学格局。', 'backstage/upload/school4.jpg', '赣南师范大学 涵盖法学、经济学、史学、文学、理学、工学、教育学、管理学、艺术学等9个学科门类，形成了以教师教育、文理学科为特色，文理管工多学科协调发展的办学格局。', 'backstage/upload/zy4.png', 'backstage/upload/byz4.jpg');
INSERT INTO `general` VALUES ('13', 'backstage/upload/s5.png', '江西省唯一独立设置的普通高等本科医学院，坐落在江西南部、京九铁路线上的历史文化名城---赣州市内。中国科学院院士韩济生教授担任名誉院长。学校创办于1941年，先后经历了江西省赣县高级助产职业学校、江西省赣县高级医事职业学校、江西省赣州护士助产学校、江西省赣州卫生学校等时期；1958年8月建立赣南医学专科学校，1988年4月升格为赣南医学院并开始招收本科生。1997年通过教育部本科教学工作合格评估，2007年获教育部普通高等学校本科教学工作水平评估优良成绩。', 'backstage/upload/school5.jpg', '赣南医学院 赣南医学院是江西省唯一独立设置的普通高等本科医学院，坐落在江西南部、京九铁路线上的历史文化名城---赣州市内。', 'backstage/upload/zy5.png', 'backstage/upload/byz5.jpg');
INSERT INTO `general` VALUES ('14', 'backstage/upload/s6.png', '国家开放大学是教育部直属的，以现代信息技术为支撑，学历教育与非学历教育并举，实施远程开放教育的新型高等学校。学校在广播电视大学基础上组建，面向全体社会成员，强调优质教育资源的集聚、整合和共享，强调以现代信息技术为支撑，探索现代信息技术与教育的深度融合。', 'backstage/upload/school6.jpg', '国家开放大学 国家开放大学直属教育部，以现代信息技术为支撑，学历教育与非学历教育并举，实施远程开放教育的新型高等学校。', 'backstage/upload/zy6.png', 'backstage/upload/byz6.jpg');
INSERT INTO `general` VALUES ('16', 'backstage/upload/s7.png', '华南师范大学（South China Normal University），简称“华师”，坐落于南方名城广州市，由中华人民共和国教育部和广东省人民政府共建，入选中国首批“211工程”、“卓越教师培养计划”，为广东省省属重点大学、中国100 所首批联入CERNET和INTERNET网的高等院校之一。 华南师范大学始建于1933年，前身是当代著名教育家林砺儒先生创建的广东省立勷勤大学师范学院；1982年10月，易名为华南师范大学；2006年，学校通过“十五”“211工程”建设整体验收。2004年，原中共中央总书记、国家主席胡锦涛出席澳门回归五周年庆典期间，称该校是中国数家名牌师范大学之一。', 'backstage/upload/school7.jpg', '华南师范大学 华南师范大学（South China Normal University），简称“华师”，坐落于南方名城广州市，由中华人民共和国教育部和广东省人民政府共建，入选中国首批“211工程”、“卓越教师培养计划”，为广东省省属重点大学、中国100 所首批联入CERNET和INTERNET网的高等院校之一。', 'backstage/upload/zy7.png', 'backstage/upload/byz7.jpg');
INSERT INTO `general` VALUES ('17', 'backstage/upload/s8.png', '创建于1979年1月，是经江西省人民政府批准创办的直属高校，主要运用广播、电视、文字教材、音像教材和计算机课件及网络等多种媒体进行现代远程开放教育的新型高等学校，自办学以来，学校先后开设了60个科类专业，开设课程340多门。今天，江西广播电视大学已形成了具有中国特色的现代远距离教育系统和模式，截止2012年底，全省各类学历教育在校生111976人，其中开放教育89791人，高职教育4354人，网络教育10332人，自考3379人，成人大专1439人，中专教育2681人。在校生规模和年度招生规模位居全国省级电大前列。', 'backstage/upload/school8.jpg', '江西广播电视大学 创建于1979年1月，是经江西省人民政府批准创办的直属高校，主要运用广播、电视、文字教材、音像教材和计算机课件及网络等多种媒体进行现代远程开放教育的新型高等学校', 'backstage/upload/zy8.png', 'backstage/upload/byz8.jpg');
INSERT INTO `general` VALUES ('18', 'backstage/upload/s9.png', '江西科技师范大学坐落于具有“物华天宝、人杰地灵”声誉的历史文化名城——南昌，是我国首批建立的高等职业技术师范院校，是教育部重点建设的培养职业教育师资的高等院校，也是江西省唯一一所培养职业教育师资的多科性本科院校，主要培养职教师资、普教师资和应用型专门人才。经过60多年的建设与发展，学校形成了以本科、研究生教育为主体，统筹发展继续教育的多学科、多层次的办学格局。2008年，学校以“优秀”成绩通过教育部本科教学工作水平评估。', 'backstage/upload/school9.jpg', '江西科技师范大学 江西科技师范大学坐落于具有“物华天宝、人杰地灵”声誉的历史文化名城——南昌，是我国首批建立的高等职业技术师范院校', 'backstage/upload/zy9.png', 'backstage/upload/byz9.jpg');
INSERT INTO `general` VALUES ('19', 'backstage/upload/s10.png', '教育部、江西省人民政府共建高校和中西部高校基础能力建设工程高校，是江西省人民政府确定为优先发展省属重点(师范)大学。学校为博士学位授予权单位，融教育学、哲学、文学、历史学、法学、经济学、管理学、理学、工学、艺术学等十大学科门类于一体，属师范与非师范并举的省属综合性重点大学。', 'backstage/upload/school10.jpg', '江西师范大学 江西师范大学是教育部、江西省人民政府共建高校和中西部高校基础能力建设工程高校，是江西省人民政府确定为优先发展的省属重点(师范)大学。', 'backstage/upload/zy10.png', 'backstage/upload/byz10.jpg');
INSERT INTO `general` VALUES ('20', 'backstage/upload/s11.png', '江西中医学院成人教育创办于1959年，是全国最早举办成人教育的院校之一。50年来，依托江西中医学院普通高等教育优良的教学资源和雄厚的师资力量，共培养医药类本、专科毕业生11600多人，培训各类中医药专业技术人员及乡村医师3.3万余人次。 学院成人高等学历教育目前开设有业余（夜大学）、函授两种学习形式，有专科起点升本科、高中起点升本科、高中起点达专科三个学历层次，现有各类在校生5500余人。', 'backstage/upload/school11.jpg', '江西中西药大学 江西中医学院成人教育创办于1959年，是全国最早举办成人教育的院校之一。50年来，依托江西中医学院普通高等教育优良的教学资源和雄厚的师资力量', 'backstage/upload/zy11.png', 'backstage/upload/byz11.jpg');
INSERT INTO `general` VALUES ('21', 'backstage/upload/s12.png', '井冈山大学是一所全日制多科性普通高等本科学校。学校位于革命摇篮井冈山所在地——江西省吉安市中心城区，占地面积2554亩，校舍建筑面积52.74万平方米。教学科研设备总值9036.81万元，图书馆藏书130余万册，拥有外文数据库、光盘数据库、试用数据库等各类数据库17个，拥有电子图书28.45万册。现有全日制在校本专科学生1.7万余人，其中本科生9000余人，外国留学生193人，各类成人教育学生1万余人。', 'backstage/upload/school12.jpg', '井冈山大学 井冈山大学是一所全日制多科性普通高等本科学校。学校位于革命摇篮井冈山所在地——江西省吉安市中心城区，占地面积2554亩，校舍建筑面积52.74万平方米。', 'backstage/upload/zy12.png', 'backstage/upload/byz12.jpg');
INSERT INTO `general` VALUES ('22', 'backstage/upload/s13.png', '九江学院是经国家教育部批准设立的国有公办全日制本科普通高等院校, 办学历史可上溯至1901年由美国基督教卫理公会创办的但福德学校。学校位于庐山之麓、鄱阳湖之畔，占地2850多亩，现有五个校区，设有1所直辖“三级甲等”医院。 　　 学校现有专任教师1900余人，副高以上830余人，博士230余人;研究生导师70余人;赣鄱英才555工程3人;国务院特殊津贴和省政府特殊津贴10人;江西省高校学科带头人和青年骨干教师44人;江西省高校名师6人;江西省“百千万人才工程”14人。 　　 学校现有21个二级学院。设有经济学、法学、教育学、文学、理学、工学、农学、医学、管理学等十一大学科门类，涵盖82个本科专业。有35个国家级、省级“质量工程”项目;86个国家级、省级“本科教学工程”项目;10个省级重点学科，并设立了“流域管理与生态保护”博士后科研工作站和杨叔子院士工作站。2015年，学校完成的课题成果获江西省政府自然科学二等奖。 　　 学校建有庐山文化研究中心、鄱阳湖生态经济研究中心、沿江产业开发研究中心、社会系统学研究中心、数控技术研究所等32个科研机构。2015年，学校获得国家自然科学基金和社会科学基金项目27项，各类科研项目经费达3000多万元。 　　 学校现有各类实验教学中心/综合实验室28个。其中，5个省级重点实验室/工程中心，5个省级实验教学示范中心、2个省高校人文社科重点研究基地。有37个中央与地方共建实验室、中央与地方共建特色优势学科实验室。现有教学科研仪器设备总值2.27亿元。', 'backstage/upload/school13.jpg', '九江学院 九江学院是经国家教育部批准设立的国有公办全日制本科普通高等院校, 办学历史可上溯至1901年由美国基督教卫理公会创办的但福德学校。', 'backstage/upload/zy13.png', 'backstage/upload/byz13.jpg');
INSERT INTO `general` VALUES ('23', 'backstage/upload/s14.png', '南昌师范学院属普通高等师范本科院校，2013年由江西教育学院改制而成。该校是江西省最早举办的八所本科高校之一，前身是成立于1952年的江西省中等师资进修学校，1956年更名为南昌师范专科学校，1958年创办江西教育学院，江西省第一任省长邵式平为学院亲笔题写了校名。学院学习环境优美、教学风气优良、学术氛围浓郁、文化品位高雅，现有青山湖、昌北两个校区，各类在籍学生近2万人。61年来，为社会培养、培训各类专门人才16万余人。', 'backstage/upload/school14.jpg', '南昌师范大学 省属普通高等师范本科院校，2013年由江西教育学院改制而成。该校是江西省最早举办的八所本科高校之一', 'backstage/upload/zy14.png', 'backstage/upload/byz14.jpg');
INSERT INTO `general` VALUES ('24', 'backstage/upload/s15.png', '陕西师范大学是国家“211工程”重点建设大学，国家教师教育“985”优势学科创新平台建设高校，是国家培养高等院校，中等学校师资和教育管理干部以及其他高级专门人才的重要基地被誉为西北地区“教师的摇篮”。陕西师范大学有五十多年的成人教育历史，是西北地区普通高等学校中最早设立成人教育的机构。', 'backstage/upload/school15.jpg', '陕西师范大学 陕西师范大学是教育部直属，国家“211工程”重点建设大学，国家教师教育“985”优势学科创新平台建设高校，是国家培养高等院校', 'backstage/upload/zy15.png', 'backstage/upload/byz15.jpg');
INSERT INTO `general` VALUES ('25', 'backstage/upload/s16.png', '四川农业大学是四川省人民政府主管的一所以生物科技为特色、农业科技为优势，农、理、工、经、管、医、文、教、法、艺多学科协调发展的省部共建重点大学，是国家“211工程”建设院校之一，四川省“2011协同创新中心”高校，是全国首批高等学校新农村发展研究院试点单位，具有自主招生资格。', 'backstage/upload/school16.jpg', '四川农业大学 是四川省人民政府主管的一所以生物科技为特色、农业科技为优势，农、理、工、经、管、医、文、教、法、艺多学科协调发展的省部共建重点大学，是国家“211工程”建设院校之一', 'backstage/upload/zy16.png', 'backstage/upload/byz16.jpg');
INSERT INTO `general` VALUES ('26', 'backstage/upload/s17.png', '天津大学坐落于历史文化名城、中国北方经济中心天津，始建于1895年10月2日，是中国第一所现代大学，开中国现代高等教育之先河，素以“实事求是”的校训、“严谨治学”的校风和“爱国奉献”的传统享誉海内外。1951年经国家院系调整定名为“天津大学”，沿用至今。', 'backstage/upload/school17.jpg', '天津大学 天津大学坐落于历史文化名城、中国北方经济中心天津，始建于1895年10月2日，是中国第一所现代大学，开中国现代高等教育之先河，素以“实事求是”的校训、“严谨治学”的校风和“爱国奉献”的传统享誉海内外。', 'backstage/upload/zy17.png', 'backstage/upload/byz17.jpg');
INSERT INTO `general` VALUES ('27', 'backstage/upload/s18.png', '西北工业大学，简称“西工大”，位于古都西安，是中华人民共和国工业和信息化部直属的一所航空、航天、航海工程为特色。工、理为主，管、文、经、法协调发展的研究型、多科性、开放试全国重点大学，是国家“985”、“211工程”重点建设高校，入选“2011计划”、“111计划”、“卓越工程师教育培养计划”，是“卓越大学联盟”、“中俄工科大学联盟”成员，中管副部级建制，设有研究生院。', 'backstage/upload/school18.jpg', '西北工业大学 西北工业大学，简称“西工大”，位于古都西安，是中华人民共和国工业和信息化部直属的一所航空、航天、航海工程为特色。', 'backstage/upload/zy18.png', 'backstage/upload/byz18.jpg');
INSERT INTO `general` VALUES ('28', 'backstage/upload/s19.png', '郑州大学（Zhengzhou University），简称郑大，是中华人民共和国教育部与河南省人民政府共建高校，国家“211工程”重点建设高校，“中西部高校综合实力提升工程”入选高校，也是“卓越工程师教育培养计划”、“卓越法律人才教育培养计划”和“卓越医生教育培养划”入选高校。', 'backstage/upload/school19.jpg', '郑州大学 郑州大学（Zhengzhou University），简称郑大，是中华人民共和国教育部与河南省人民政府共建高校，国家“211工程”重点建设高校', 'backstage/upload/zy19.png', 'backstage/upload/byz19.jpg');
INSERT INTO `general` VALUES ('29', 'backstage/upload/s20.png', '中国石油大学是一所石油特色鲜明，以工为主，理工管文相结合的国重点大学。它的前身是1953年创建的北京石油学院；1960年被确定为全国重点高等院校；1997年11月，经国家计划委员会批准，正式进入国家“211工程”首批重点建设的高等学校行列；2000年2月划归教育部直属；2000年6月，经国家教育部批准，成立研究生院。 中国石油大学远程教育学院成立于2001年。学院依托学校优秀的教学资源，采用基于计算机互联网的远程教学模式和学分制教学管理模式，面向社会自主招生，开展高起专、专升本两个层次的学历教育，颁发国家承认的学历证书。', 'backstage/upload/school20.jpg', '中国石油大学 中国石油大学是一所石油特色鲜明，以工为主，理工管文相结合的全国重点大学', 'backstage/upload/zy20.png', 'backstage/upload/byz20.jpg');
INSERT INTO `general` VALUES ('31', 'backstage/upload/s5.png', '江西省唯一独立设置的普通高等本科医学院，坐落在江西南部、京九铁路线上的历史文化名城---赣州市内。中国科学院院士韩济生教授担任名誉院长。学校创办于1941年，先后经历了江西省赣县高级助产职业学校、江西省赣县高级医事职业学校、江西省赣州护士助产学校、江西省赣州卫生学校等时期；1958年8月建立赣南医学专科学校，1988年4月升格为赣南医学院并开始招收本科生。1997年通过教育部本科教学工作合格评估，2007年获教育部普通高等学校本科教学工作水平评估优良成绩', 'backstage/upload/school5.jpg', '赣南医学院', 'backstage/upload/zy5.png', 'backstage/upload/byz5.jpg');
INSERT INTO `general` VALUES ('32', 'backstage/upload/IMG_0024.JPG', '北京大学', 'backstage/upload/null', '123', 'backstage/upload/null', 'backstage/upload/null');

-- ----------------------------
-- Table structure for hotmajor
-- ----------------------------
DROP TABLE IF EXISTS `hotmajor`;
CREATE TABLE `hotmajor` (
  `hid` int(11) NOT NULL AUTO_INCREMENT,
  `htitle` varchar(255) DEFAULT NULL,
  `hpicture` varchar(255) DEFAULT NULL,
  `hcontent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotmajor
-- ----------------------------
INSERT INTO `hotmajor` VALUES ('2', '电子与信息类', 'backstage/upload/major1.jpg', '电子信息科学与技术、光信息科学与技术、电气工程及其自动化、自动化、电子信息工程、通信工程。\r\n最近几年大力发展的方向，这就是一次第三次工业革命，这个行业会持续走强，十分有前途');
INSERT INTO `hotmajor` VALUES ('3', '法学类', 'backstage/upload/major2_0.jpg', '法学、社会学、治安学、侦查学\r\n这个行业随着社会的进步而进步，看看美国如今法学低位，那就是中国未来法学地位了');
INSERT INTO `hotmajor` VALUES ('4', '机械类', 'backstage/upload/major3_0.jpg', '机械设计制造及其自动化、材料成型及控制工程、工业设计、\r\n过程装备与控制工程、测控技术与仪器\r\n这个行业一直都很火，必进是国民生活、国家建设的基础性行业，需要大量的人力物力，不过工资嘛，也都一直不温不火');

-- ----------------------------
-- Table structure for indexcontent
-- ----------------------------
DROP TABLE IF EXISTS `indexcontent`;
CREATE TABLE `indexcontent` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `icode` varchar(255) DEFAULT NULL,
  `iphone` varchar(255) DEFAULT NULL,
  `iaddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of indexcontent
-- ----------------------------
INSERT INTO `indexcontent` VALUES ('1', 'backstage/upload/code1.png', '0708-0789', '江西省赣州市章贡区沙石镇88号');

-- ----------------------------
-- Table structure for indexpicture
-- ----------------------------
DROP TABLE IF EXISTS `indexpicture`;
CREATE TABLE `indexpicture` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `ptitle` varchar(255) DEFAULT NULL,
  `pcontent` varchar(255) DEFAULT NULL,
  `ppicture` varchar(255) DEFAULT NULL,
  `ipictureid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `FK9CEF03ECC36612EE` (`ipictureid`),
  CONSTRAINT `FK9CEF03ECC36612EE` FOREIGN KEY (`ipictureid`) REFERENCES `indexcontent` (`iid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of indexpicture
-- ----------------------------
INSERT INTO `indexpicture` VALUES ('1', '成人高考', '成考专升本，成人高考组成部分，属国民教育系列，列入国家招生计划，国家承认学历，参加全国招生统一考试，各省、自治区统一组织录取。是为我国各类成人高等院校选拔合格的毕业生以进入更高层次学历教育的入学考试', 'backstage/upload/index12.jpg', null);
INSERT INTO `indexpicture` VALUES ('3', '零基础大学', '报考临床医学、口腔医学、预防医学、中医学等临床类专业的人员，应当取得省级卫生行政部门颁发的相应类别的执业助理医师及以上资格证书或取得国家认可的普通中专相应专业学历；或者县级及以上卫生行政部门颁发的乡村医生执业证书并具有中专学历或中专水平证书', 'backstage/upload/index3.jpg', null);
INSERT INTO `indexpicture` VALUES ('4', '远程教育', '成人高考制度是一项重要的高校入学考试制度，其历史可分为“文革”前和“文革”后两个时期。“文革”前成人高等学历教育的主要形式是大学函授教育和夜大教育', 'backstage/upload/index5.jpg', null);
INSERT INTO `indexpicture` VALUES ('8', '成考报名时间', '成人高考报名时间  （一）网上报名时间为9月1日－9月10日（9月10日18时关闭网上报名系统）。报名时间过后不补报名。  （二）现场报名确认时间为9月11日－9月15日。', 'backstage/upload/index11.jpg', null);

-- ----------------------------
-- Table structure for joinwork
-- ----------------------------
DROP TABLE IF EXISTS `joinwork`;
CREATE TABLE `joinwork` (
  `jwid` int(11) NOT NULL AUTO_INCREMENT,
  `jwtitle` varchar(255) DEFAULT NULL,
  `jwpicture` varchar(255) DEFAULT NULL,
  `jwcontent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`jwid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of joinwork
-- ----------------------------
INSERT INTO `joinwork` VALUES ('1', '江西理科大学提供·文凭', 'backstage/upload/TsQBDrvDll.dll', '本科文凭可以不用再在学校就读，也能得到');

-- ----------------------------
-- Table structure for jurisdication
-- ----------------------------
DROP TABLE IF EXISTS `jurisdication`;
CREATE TABLE `jurisdication` (
  `jtid` int(11) NOT NULL AUTO_INCREMENT,
  `jtname` varchar(255) DEFAULT NULL,
  `jtsort` int(11) DEFAULT NULL,
  `jtstatus` int(11) DEFAULT NULL,
  `jturl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`jtid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jurisdication
-- ----------------------------
INSERT INTO `jurisdication` VALUES ('1', '权限管理', '0', '1', null);
INSERT INTO `jurisdication` VALUES ('2', '主页内容', '0', '0', null);
INSERT INTO `jurisdication` VALUES ('3', '报名管理', '0', '0', null);
INSERT INTO `jurisdication` VALUES ('4', '缴费管理', '0', '0', null);
INSERT INTO `jurisdication` VALUES ('5', '报名类型', '0', '0', null);
INSERT INTO `jurisdication` VALUES ('6', '院校管理', '0', '0', null);
INSERT INTO `jurisdication` VALUES ('7', '报表中心', '0', '0', null);
INSERT INTO `jurisdication` VALUES ('8', '招生简章', '0', '0', null);
INSERT INTO `jurisdication` VALUES ('9', '日志管理', '0', '0', null);
INSERT INTO `jurisdication` VALUES ('10', '角色管理', '1', '1', '/Enrollment/backstage/role.jsp');
INSERT INTO `jurisdication` VALUES ('11', '部门管理', '1', '0', '/Enrollment/backstage/department.jsp');
INSERT INTO `jurisdication` VALUES ('12', '用户管理', '1', '0', '/Enrollment/teacher.action');
INSERT INTO `jurisdication` VALUES ('13', '轮播图管理', '2', '0', '/Enrollment/backstage/Indexpicture.jsp');
INSERT INTO `jurisdication` VALUES ('14', '首页内容', '2', '0', '/Enrollment/backstage/Indexcontent.jsp');
INSERT INTO `jurisdication` VALUES ('15', '公告管理', '2', '0', '/Enrollment/backstage/Notice.jsp');
INSERT INTO `jurisdication` VALUES ('16', '联系表管理', '2', '0', '/Enrollment/contact.action');
INSERT INTO `jurisdication` VALUES ('17', '热门专业', '2', '0', '/Enrollment/backstage/HotMajors.jsp');
INSERT INTO `jurisdication` VALUES ('18', '共享合作', '2', '0', '/Enrollment/backstage/joinwork.jsp');
INSERT INTO `jurisdication` VALUES ('19', '培训报名管理', '3', '0', '/Enrollment/train.action');
INSERT INTO `jurisdication` VALUES ('20', '教育报名管理', '3', '0', '/Enrollment/education.action');
INSERT INTO `jurisdication` VALUES ('21', '文件上传管理', '3', '0', '/Enrollment/backstage/Upload.jsp');
INSERT INTO `jurisdication` VALUES ('22', '缴费管理', '4', '0', '/Enrollment/fiance.action?tid=');
INSERT INTO `jurisdication` VALUES ('23', '成人教育', '5', '0', '/Enrollment/backstage/Adult.jsp');
INSERT INTO `jurisdication` VALUES ('24', '会计报名', '5', '0', '/Enrollment/backstage/Accounting.jsp');
INSERT INTO `jurisdication` VALUES ('25', '国家开发', '5', '0', '/Enrollment/backstage/Country.jsp');
INSERT INTO `jurisdication` VALUES ('26', '远程教育', '5', '0', '/Enrollment/backstage/Distance.jsp');
INSERT INTO `jurisdication` VALUES ('27', '艺考培训', '5', '0', '/Enrollment/backstage/Arts.jsp');
INSERT INTO `jurisdication` VALUES ('28', '职业资格', '5', '0', '/Enrollment/backstage/Vocational.jsp');
INSERT INTO `jurisdication` VALUES ('29', '院校管理', '6', '0', '/Enrollment/backstage/academy.jsp');
INSERT INTO `jurisdication` VALUES ('30', '专业管理', '6', '0', '/Enrollment/getAcademy.action');
INSERT INTO `jurisdication` VALUES ('31', '老师工作状况', '7', '0', '/Enrollment/backstage/JFreeTeaStu.jsp');
INSERT INTO `jurisdication` VALUES ('32', '学生报名情况', '7', '0', '/Enrollment/backstage/PieChart.jsp');
INSERT INTO `jurisdication` VALUES ('33', '每天学生报名情况', '7', '0', '/Enrollment/backstage/JFreeTimeCount.jsp');
INSERT INTO `jurisdication` VALUES ('34', '招生简章管理', '8', '0', '/Enrollment/backstage/general.jsp');
INSERT INTO `jurisdication` VALUES ('35', '日志管理', '9', '0', '/Enrollment/backstage/logf.jsp');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `logtime` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=1037 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `mrid` int(11) NOT NULL AUTO_INCREMENT,
  `mrname` varchar(255) DEFAULT NULL,
  `academyid` int(11) DEFAULT NULL,
  PRIMARY KEY (`mrid`),
  KEY `FK62DB5B97AD3AA5C` (`academyid`),
  CONSTRAINT `FK62DB5B97AD3AA5C` FOREIGN KEY (`academyid`) REFERENCES `academy` (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('11', '计算机', '6');
INSERT INTO `major` VALUES ('13', '物流管理', '8');
INSERT INTO `major` VALUES ('14', '高等函数', '9');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `ntitle` varchar(255) DEFAULT NULL,
  `ncontent` varchar(255) DEFAULT NULL,
  `ndate` varchar(255) DEFAULT NULL,
  `nflag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '《2017成人高考规定》', '17年2月27号教育部再出文件，\r\n中专和高中毕业证2018年起学信网联网查询\r\n也就是说没有高中或者中专毕业证的，\r\n从2018年开始不允许报考大专。\r\n学信网的版块已经更新升级。', '2017-08-29 10:31:57', '公告');
INSERT INTO `notice` VALUES ('2', '《成人高考时间》', '成人高考报名从2007年开始实行网上报名，此举标志着成人高考步入网上报名、网上阅卷、网上录取信息化时代。成人高考网上报名一般在每年的8月中旬至9月上旬，随后，考生要到各区县指定地点进行现场确认，现场确认需要带网报号及身份证件，毕业证原件，复印件。外地考生需要带暂住证。10月中旬的一个双休日参加全国统考。', '2017-08-29 10:49:48', '简章');
INSERT INTO `notice` VALUES ('3', '《成人高考注意事项》', '考生必须自觉服从监考员等考试工作人员管理，不得以任何理由妨碍监考员等考试工作人员履行职责，不得扰乱考场及其他考试工作地点的秩序。\r\n\r\n2、考生凭准考证、身份证，按规定时间和准考证上各科目的考试试室、座位号参加考试。开考信号发出后才能开始答题。\r\n\r\n6、开考15分钟后禁止迟到考生进入考场、试室，离每科考试结束前30分钟，方可交卷出场，交卷出场后不得再进入试室，也不准在考场附近逗留或交谈。', '2017-09-05 02:33:25', '简章');
INSERT INTO `notice` VALUES ('4', '《热门报名专业》', '电子与信息类：电子信息科学与技术、光信息科学与技术、电气工程及其自动化、自动化、电子信息工程、通信工程。', '2017-09-05 02:36:56', '公告');

-- ----------------------------
-- Table structure for roels
-- ----------------------------
DROP TABLE IF EXISTS `roels`;
CREATE TABLE `roels` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) DEFAULT NULL,
  `rdes` varchar(255) DEFAULT NULL,
  `rstatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roels
-- ----------------------------
INSERT INTO `roels` VALUES ('1', '超级管理员', '超级管理', '正常');
INSERT INTO `roels` VALUES ('2', '管理员', '可以看自己的权限', '正常');
INSERT INTO `roels` VALUES ('3', '用户', '维护自己利益', '正常');

-- ----------------------------
-- Table structure for rolejur
-- ----------------------------
DROP TABLE IF EXISTS `rolejur`;
CREATE TABLE `rolejur` (
  `rjid` int(11) NOT NULL AUTO_INCREMENT,
  `whether` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `jid` int(11) DEFAULT NULL,
  PRIMARY KEY (`rjid`),
  KEY `FK5211C051403ACAD6` (`jid`),
  KEY `FK5211C0511B7100FD` (`jid`),
  KEY `FK5211C0511B711F05` (`rid`),
  CONSTRAINT `FK5211C0511B711F05` FOREIGN KEY (`rid`) REFERENCES `roels` (`rid`),
  CONSTRAINT `FK5211C051403ACAD6` FOREIGN KEY (`jid`) REFERENCES `jurisdication` (`jtid`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolejur
-- ----------------------------
INSERT INTO `rolejur` VALUES ('1', '1', '1', '1');
INSERT INTO `rolejur` VALUES ('2', '1', '1', '2');
INSERT INTO `rolejur` VALUES ('3', '1', '1', '3');
INSERT INTO `rolejur` VALUES ('5', '1', '1', '5');
INSERT INTO `rolejur` VALUES ('6', '1', '1', '6');
INSERT INTO `rolejur` VALUES ('8', '1', '1', '9');
INSERT INTO `rolejur` VALUES ('9', '1', '1', '10');
INSERT INTO `rolejur` VALUES ('10', '1', '1', '11');
INSERT INTO `rolejur` VALUES ('11', '1', '1', '12');
INSERT INTO `rolejur` VALUES ('12', '1', '1', '13');
INSERT INTO `rolejur` VALUES ('13', '1', '1', '14');
INSERT INTO `rolejur` VALUES ('14', '1', '1', '15');
INSERT INTO `rolejur` VALUES ('15', '1', '1', '4');
INSERT INTO `rolejur` VALUES ('16', '1', '1', '16');
INSERT INTO `rolejur` VALUES ('17', '1', '1', '33');
INSERT INTO `rolejur` VALUES ('18', '1', '1', '35');
INSERT INTO `rolejur` VALUES ('19', '1', '1', '34');
INSERT INTO `rolejur` VALUES ('20', '1', '1', '17');
INSERT INTO `rolejur` VALUES ('21', '1', '1', '32');
INSERT INTO `rolejur` VALUES ('22', '1', '1', '31');
INSERT INTO `rolejur` VALUES ('23', '1', '1', '30');
INSERT INTO `rolejur` VALUES ('24', '1', '1', '29');
INSERT INTO `rolejur` VALUES ('25', '1', '1', '28');
INSERT INTO `rolejur` VALUES ('26', '1', '1', '27');
INSERT INTO `rolejur` VALUES ('27', '1', '1', '26');
INSERT INTO `rolejur` VALUES ('28', '1', '1', '25');
INSERT INTO `rolejur` VALUES ('29', '1', '1', '24');
INSERT INTO `rolejur` VALUES ('30', '1', '1', '23');
INSERT INTO `rolejur` VALUES ('31', '1', '1', '22');
INSERT INTO `rolejur` VALUES ('32', '1', '1', '21');
INSERT INTO `rolejur` VALUES ('33', '1', '1', '20');
INSERT INTO `rolejur` VALUES ('34', '1', '1', '19');
INSERT INTO `rolejur` VALUES ('35', '1', '1', '18');
INSERT INTO `rolejur` VALUES ('36', '1', '2', '1');
INSERT INTO `rolejur` VALUES ('37', '0', '2', '10');
INSERT INTO `rolejur` VALUES ('38', '0', '2', '11');
INSERT INTO `rolejur` VALUES ('39', '1', '2', '12');
INSERT INTO `rolejur` VALUES ('40', '1', '2', '2');
INSERT INTO `rolejur` VALUES ('41', '1', '2', '13');
INSERT INTO `rolejur` VALUES ('42', '1', '2', '14');
INSERT INTO `rolejur` VALUES ('43', '1', '2', '15');
INSERT INTO `rolejur` VALUES ('44', '0', '2', '16');
INSERT INTO `rolejur` VALUES ('45', '0', '2', '17');
INSERT INTO `rolejur` VALUES ('46', '1', '2', '18');
INSERT INTO `rolejur` VALUES ('47', '1', '2', '3');
INSERT INTO `rolejur` VALUES ('48', '1', '2', '19');
INSERT INTO `rolejur` VALUES ('49', '1', '2', '20');
INSERT INTO `rolejur` VALUES ('50', '1', '2', '21');
INSERT INTO `rolejur` VALUES ('51', '1', '2', '4');
INSERT INTO `rolejur` VALUES ('52', '1', '2', '22');
INSERT INTO `rolejur` VALUES ('53', '1', '2', '5');
INSERT INTO `rolejur` VALUES ('54', '1', '2', '23');
INSERT INTO `rolejur` VALUES ('55', '1', '2', '24');
INSERT INTO `rolejur` VALUES ('56', '1', '2', '25');
INSERT INTO `rolejur` VALUES ('57', '1', '2', '26');
INSERT INTO `rolejur` VALUES ('58', '1', '2', '27');
INSERT INTO `rolejur` VALUES ('59', '1', '2', '28');
INSERT INTO `rolejur` VALUES ('60', '1', '2', '7');
INSERT INTO `rolejur` VALUES ('61', '1', '2', '29');
INSERT INTO `rolejur` VALUES ('62', '1', '2', '30');
INSERT INTO `rolejur` VALUES ('63', '1', '2', '6');
INSERT INTO `rolejur` VALUES ('64', '1', '2', '31');
INSERT INTO `rolejur` VALUES ('65', '1', '2', '32');
INSERT INTO `rolejur` VALUES ('66', '1', '2', '33');
INSERT INTO `rolejur` VALUES ('67', '1', '2', '8');
INSERT INTO `rolejur` VALUES ('68', '1', '2', '34');
INSERT INTO `rolejur` VALUES ('69', '1', '2', '9');
INSERT INTO `rolejur` VALUES ('70', '1', '2', '35');
INSERT INTO `rolejur` VALUES ('71', '0', '3', '1');
INSERT INTO `rolejur` VALUES ('72', '0', '3', '10');
INSERT INTO `rolejur` VALUES ('73', '0', '3', '11');
INSERT INTO `rolejur` VALUES ('74', '0', '3', '12');
INSERT INTO `rolejur` VALUES ('75', '0', '3', '7');
INSERT INTO `rolejur` VALUES ('76', '0', '3', '13');
INSERT INTO `rolejur` VALUES ('77', '0', '3', '14');
INSERT INTO `rolejur` VALUES ('78', '0', '3', '15');
INSERT INTO `rolejur` VALUES ('79', '0', '3', '16');
INSERT INTO `rolejur` VALUES ('80', '1', '3', '17');
INSERT INTO `rolejur` VALUES ('81', '1', '3', '18');
INSERT INTO `rolejur` VALUES ('82', '1', '3', '3');
INSERT INTO `rolejur` VALUES ('83', '1', '3', '19');
INSERT INTO `rolejur` VALUES ('84', '0', '3', '20');
INSERT INTO `rolejur` VALUES ('85', '0', '3', '21');
INSERT INTO `rolejur` VALUES ('86', '1', '3', '4');
INSERT INTO `rolejur` VALUES ('87', '1', '3', '22');
INSERT INTO `rolejur` VALUES ('88', '1', '3', '5');
INSERT INTO `rolejur` VALUES ('89', '1', '3', '23');
INSERT INTO `rolejur` VALUES ('90', '1', '3', '24');
INSERT INTO `rolejur` VALUES ('91', '0', '3', '25');
INSERT INTO `rolejur` VALUES ('92', '0', '3', '26');
INSERT INTO `rolejur` VALUES ('93', '1', '3', '27');
INSERT INTO `rolejur` VALUES ('94', '1', '3', '28');
INSERT INTO `rolejur` VALUES ('95', '1', '3', '6');
INSERT INTO `rolejur` VALUES ('96', '1', '3', '29');
INSERT INTO `rolejur` VALUES ('97', '1', '3', '30');
INSERT INTO `rolejur` VALUES ('98', '1', '3', '2');
INSERT INTO `rolejur` VALUES ('99', '0', '3', '31');
INSERT INTO `rolejur` VALUES ('100', '0', '3', '32');
INSERT INTO `rolejur` VALUES ('101', '0', '3', '33');
INSERT INTO `rolejur` VALUES ('102', '1', '3', '8');
INSERT INTO `rolejur` VALUES ('103', '1', '3', '34');
INSERT INTO `rolejur` VALUES ('104', '0', '3', '9');
INSERT INTO `rolejur` VALUES ('105', '0', '3', '35');
INSERT INTO `rolejur` VALUES ('106', '1', '1', '8');
INSERT INTO `rolejur` VALUES ('107', '1', '1', '7');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `stuno` varchar(255) DEFAULT NULL,
  `sname` varchar(255) DEFAULT NULL,
  `snumber` varchar(255) DEFAULT NULL,
  `sphone` varchar(255) DEFAULT NULL,
  `academyid` int(11) DEFAULT NULL,
  `teacherid` int(11) NOT NULL DEFAULT '0',
  `sgradations` varchar(255) DEFAULT NULL,
  `ssystem` varchar(255) DEFAULT NULL,
  `acontent` varchar(255) DEFAULT NULL,
  `sdate` varchar(255) DEFAULT NULL,
  `stype` varchar(255) DEFAULT NULL,
  `festate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`,`teacherid`),
  KEY `FK8FFE823B7AD3AA5C` (`academyid`),
  KEY `FK8FFE823B3A9B9EC8` (`teacherid`),
  CONSTRAINT `FK8FFE823B3A9B9EC8` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `FK8FFE823B7AD3AA5C` FOREIGN KEY (`academyid`) REFERENCES `academy` (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('4', '20170304', '香菇', null, '17370134043', null, '1', null, null, 'C++', '2017-09-09 10:29:59', '艺考培训报名', '已缴费');
INSERT INTO `student` VALUES ('5', '20170305', 'Marry', null, '17370134044', null, '3', null, null, 'JacaScript', '2017-09-10 11:03:44', '会计培训报名', '已缴费');
INSERT INTO `student` VALUES ('6', '20170306', 'Jnery', null, '17370134045', null, '3', null, null, '针灸师', '2017-09-10 11:09:10', '职业资格报名', '已缴费');
INSERT INTO `student` VALUES ('8', '20170308', 'Mick', null, '13456789012', null, '1', null, null, 'IT', '2017-09-11 14:52:54', '会计培训报名', '未缴完');
INSERT INTO `student` VALUES ('21', '20170311', '李丽丽', null, '13579749961', null, '1', null, null, '护理师', '2017-09-12 09:50:10', '职业资格报名', '已缴费');
INSERT INTO `student` VALUES ('24', '20170312', '吴倩', null, '13779749961', null, '1', null, null, '音乐', '2017-09-13 09:56:58', '艺考培训报名', '已缴费');
INSERT INTO `student` VALUES ('38', '20170313', '小丽', '511521188605031236', '15112345671', '8', '1', '高达专', '3年', '物流管理', '2017-09-09 09:00:18', '成人高考报名', '未缴费');
INSERT INTO `student` VALUES ('39', '20170314', '大力', '511521188605031231', '13779781008', '9', '3', '高达本', '4年', '高等函数', '2017-09-11 09:32:49', '远程报名', '已缴费');
INSERT INTO `student` VALUES ('40', '20170315', '小红', '511521188605011333', '18675731236', '6', '1', '专达本', '3年', '计算机', '2017-09-12 10:15:54', '成人高考报名', '已缴费');
INSERT INTO `student` VALUES ('42', '20170317', '小勋', null, '18713146789', null, '1', null, null, '文饰师', '2017-09-13 10:22:42', '职业资格报名', '未缴完');
INSERT INTO `student` VALUES ('43', '20170318', '小凯', null, '18179701235', null, '1', null, null, '健康调理师', '2017-09-12 11:28:54', '职业资格报名', '已缴费');
INSERT INTO `student` VALUES ('44', '20170319', '红花', '511521200107129321', '17828130610', '8', '3', '高达专', '3年', '物流管理', '2017-09-19 08:42:04', '成人高考报名', '已缴费');
INSERT INTO `student` VALUES ('45', '20170320', '胡椒', '511521200107129322', '17828130611', '9', '1', '高达专', '3年', '高等函数', '2017-09-19 09:14:14', '远程报名', '已缴费');
INSERT INTO `student` VALUES ('46', '20170321', '红枣', '511521200107129323', '18308180578', '6', '1', '高达本', '4年', '计算机', '2017-09-19 10:33:13', '国家报名', '未缴费');
INSERT INTO `student` VALUES ('47', '20170322', '小黑', '511521199709031111', '13109071234', '8', '3', '专达本', '3年', '物流管理', '2017-09-20 11:33:16', '成人高考报名', '未缴费');
INSERT INTO `student` VALUES ('48', '20170323', '周俣帆', '360111199708052533', '18370787539', '6', '1', '高达专', '3年', '计算机', '2017-09-20 14:39:07', '成人高考报名', '已缴费');
INSERT INTO `student` VALUES ('49', '20170324', '凌芳', null, '15779897850', null, '1', null, null, '中医调理师', '2017-09-21 07:38:02', '职业资格报名', '已缴费');
INSERT INTO `student` VALUES ('50', '20170325', '甘富川', null, '18783124360', null, '1', null, null, 'IT', '2017-09-21 08:03:47', '艺考培训报名', '未缴费');
INSERT INTO `student` VALUES ('51', '20170326', '郑肖', null, '17370134050', null, '1', null, null, 'Java高级', '2017-09-21 08:19:31', '会计培训报名', '未缴费');

-- ----------------------------
-- Table structure for studentfile
-- ----------------------------
DROP TABLE IF EXISTS `studentfile`;
CREATE TABLE `studentfile` (
  `sfid` int(11) NOT NULL AUTO_INCREMENT,
  `sfphoto` varchar(255) DEFAULT NULL,
  `sfvoucher` varchar(255) DEFAULT NULL,
  `sfpaper` varchar(255) DEFAULT NULL,
  `sfdate` varchar(255) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sfid`),
  KEY `FK8C5CE2373BAFBF52` (`sfid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentfile
-- ----------------------------
INSERT INTO `studentfile` VALUES ('4', 'backstage/upload/3.jpg', 'backstage/upload/pic7.jpg', 'backstage/upload/layout-1.html', '2017-09-20 11:23:54', '6');
INSERT INTO `studentfile` VALUES ('5', 'backstage/upload/c1.jpg', 'backstage/upload/a1.jpg', 'backstage/upload/m1.jpg', '2017-09-11 09:23:23', '4');
INSERT INTO `studentfile` VALUES ('6', 'backstage/upload/p2.jpg', 'backstage/upload/a2.jpg', 'backstage/upload/m2.jpg', '2017-09-11 09:33:36', '5');
INSERT INTO `studentfile` VALUES ('8', 'backstage/upload/index10.jpg', 'backstage/upload/layout-1.html', 'backstage/upload/pic2.jpg', '2017-09-12 11:40:42', '8');
INSERT INTO `studentfile` VALUES ('21', 'backstage/upload/a1.jpg', 'backstage/upload/m1.jpg', 'backstage/upload/a6.jpg', '2017-09-19 12:15:02', '21');
INSERT INTO `studentfile` VALUES ('24', 'backstage/upload/p9.jpg', 'backstage/upload/a6.jpg', 'backstage/upload/m6.jpg', '2017-09-19 12-25-59', '45');
INSERT INTO `studentfile` VALUES ('35', 'backstage/upload/p4.jpg', 'backstage/upload/a7.jpg', 'backstage/upload/m7.jpg', '2017-09-19 02:35:26', '40');
INSERT INTO `studentfile` VALUES ('36', 'backstage/upload/p6.jpg', 'backstage/upload/a6.jpg', 'backstage/upload/m6.jpg', '2017-09-19 02:37:15', '46');
INSERT INTO `studentfile` VALUES ('37', 'backstage/upload/c1.jpg', 'backstage/upload/a1.jpg', 'backstage/upload/m1.jpg', '2017-09-19 04:46:39', '42');
INSERT INTO `studentfile` VALUES ('38', 'backstage/upload/p5.jpg', 'backstage/upload/a5.jpg', 'backstage/upload/m5.jpg', '2017-09-19 04:49:53', '44');
INSERT INTO `studentfile` VALUES ('39', 'backstage/upload/p7.jpg', 'backstage/upload/a7.jpg', 'backstage/upload/m7.jpg', '2017-09-19 04:52:51', '43');
INSERT INTO `studentfile` VALUES ('40', 'backstage/upload/6.jpg', 'backstage/upload/pic6.jpg', 'backstage/upload/layout-1.html', '2017-09-20 11:35:29', '47');
INSERT INTO `studentfile` VALUES ('41', 'backstage/upload/p7.jpg', 'backstage/upload/a1.jpg', 'backstage/upload/m2.jpg', '2017-09-21 07:38:45', '49');
INSERT INTO `studentfile` VALUES ('42', 'backstage/upload/c1.jpg', 'backstage/upload/m1.jpg', 'backstage/upload/a4.jpg', '2017-09-21 07:43:40', '49');
INSERT INTO `studentfile` VALUES ('43', 'backstage/upload/p4.jpg', 'backstage/upload/a6.jpg', 'backstage/upload/m2.jpg', '2017-09-21 07:49:37', '49');
INSERT INTO `studentfile` VALUES ('44', 'backstage/upload/p6.jpg', 'backstage/upload/m2.jpg', 'backstage/upload/a7.jpg', '2017-09-21 08:04:49', '50');
INSERT INTO `studentfile` VALUES ('45', 'backstage/upload/p6.jpg', 'backstage/upload/a4.jpg', 'backstage/upload/m2.jpg', '2017-09-21 08:20:27', '51');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(255) DEFAULT NULL,
  `tpassword` varchar(255) DEFAULT NULL,
  `tsex` varchar(255) DEFAULT NULL,
  `tage` int(11) DEFAULT NULL,
  `tphone` varchar(255) DEFAULT NULL,
  `tstatus` varchar(255) DEFAULT NULL,
  `departmentid` int(11) DEFAULT NULL,
  `tcreatetime` varchar(255) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `ttype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  KEY `FKAA31CBE2899D1836` (`departmentid`),
  KEY `FKAA31CBE2E446AE09` (`roleid`),
  CONSTRAINT `FKAA31CBE2899D1836` FOREIGN KEY (`departmentid`) REFERENCES `department` (`did`),
  CONSTRAINT `FKAA31CBE2E446AE09` FOREIGN KEY (`roleid`) REFERENCES `roels` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', 'admin', '123456', '女', '28', '15179084193', '正常', '1', '2015-12-06 09:30:20', '1', null);
INSERT INTO `teacher` VALUES ('3', 'sehun', '123456', '男', '25', '15179749961', '正常', '2', '2017-09-01 08:01:46', '2', null);
INSERT INTO `teacher` VALUES ('6', 'kai', '123456', '男', '24', '15379749962', '正常', '5', '2017-09-04 03:21:40', '3', null);
INSERT INTO `teacher` VALUES ('7', 'Mike', '123456', '男', '25', '15579749963', '正常', '5', '2017-09-06 13:36:15', '3', null);
INSERT INTO `teacher` VALUES ('8', 'xinming', '123456', '男', '29', '15679749964', '正常', '2', '2017-09-04 07:21:16', '2', null);
INSERT INTO `teacher` VALUES ('10', '小明', '123456', '男', '18', '13215167908', '停用', '2', '2017-09-20 10:32:48', '3', null);

-- ----------------------------
-- Table structure for vocational
-- ----------------------------
DROP TABLE IF EXISTS `vocational`;
CREATE TABLE `vocational` (
  `vlid` int(11) NOT NULL AUTO_INCREMENT,
  `vlname` varchar(255) DEFAULT NULL,
  `vlpicture` varchar(255) DEFAULT NULL,
  `vladdress` varchar(255) DEFAULT NULL,
  `vltitle` varchar(255) DEFAULT NULL,
  `vlphoto1` varchar(255) DEFAULT NULL,
  `vlphoto2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vlid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vocational
-- ----------------------------
INSERT INTO `vocational` VALUES ('1', '职业资格', 'img/mainImg/hotair.png', 'vocational.action', '职业课程', 'img/acc_art_voc/voc1_0.jpg', 'img/acc_art_voc/voc1_1.jpg');

-- ----------------------------
-- Table structure for workassist
-- ----------------------------
DROP TABLE IF EXISTS `workassist`;
CREATE TABLE `workassist` (
  `wa_id` int(11) NOT NULL AUTO_INCREMENT,
  `wa_title` varchar(255) DEFAULT NULL,
  `wa_createtime` varchar(255) DEFAULT NULL,
  `wa_content` varchar(255) DEFAULT NULL,
  `wa_state` int(11) DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  PRIMARY KEY (`wa_id`),
  KEY `FKABC5E4DA3A9B9EC8` (`teacherid`),
  CONSTRAINT `FKABC5E4DA3A9B9EC8` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workassist
-- ----------------------------
INSERT INTO `workassist` VALUES ('1', '需求', '2017-09-15 16:06:28', '了解需求分析', '1', '1');
INSERT INTO `workassist` VALUES ('2', '配置', '2017-09-01 08:30:00', '搭建环境', '1', '1');
INSERT INTO `workassist` VALUES ('3', '合并', '2017-09-17 08:30:40', '项目合并', '1', '3');
INSERT INTO `workassist` VALUES ('5', '建表', '2017-09-15 16:57:03', '创建相应表', '1', '1');
INSERT INTO `workassist` VALUES ('6', '分工', '2017-09-15 08:12:06', '组员分工', '1', '3');
INSERT INTO `workassist` VALUES ('7', '测试', '2017-09-18 09:30:40', '测试bug', '1', '3');
