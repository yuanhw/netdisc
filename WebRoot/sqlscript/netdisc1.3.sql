create database netdisc1_3;
use netdisc1_3;
/*用户表*/
create table users(
	id int primary key auto_increment,
	username varchar(49) not null unique,
	phone char(11) not null unique,
	password varchar(23) not null ,
	initcapacity double ,
	usedcapacity double ,
	account double(8,2) 
);
/*虚拟目录类型*/
create table cltype(
	id int primary key auto_increment,
	title varchar(125) not null,
	imgpath varchar(49)
);
/*真实文件表*/
create table rfiles(
	md5 char(32) primary key,
	alltitle varchar(36) not null,
	filepath varchar(30) ,
	refcount int not null
);
/*虚拟目录表*/
create table vcatalog(
	id int primary key auto_increment,
	title varchar(115) not null,
	typeid int not null,
	size double ,
	md5 char(32),
	altertime timestamp not null,
	uid int not null,
	preid int ,
	layer int not null,
	foreign key (uid) references users(id),
	foreign key (typeid) references cltype(id)
);


/*视频类别表*/
create table vtype(
	id int primary key auto_increment,
	title varchar(49) not null
);
/*观看表*/
create table plays(
	id int primary key auto_increment,
	title varchar(115) not null,
	tid int not null,
	uname varchar(49) not null,
	soucount long,
	playcount long,
	altertime date not null,
	md5 char(32) not null,
	foreign key (uname) references users(username),
	foreign key (tid) references vtype(id)
);
/*数据插入1*/
insert into vtype values(1,'电视剧');
insert into vtype values(2,'电影');
insert into vtype values(3,'短视频');
insert into vtype values(4,'动漫');
insert into vtype values(5,'其它');
/*2*/
INSERT INTO `cltype` VALUES (1, 'vfile', 'image/folder.jpg');
INSERT INTO `cltype` VALUES (2, 'audio/mpeg', 'image/music.png');
INSERT INTO `cltype` VALUES (3, 'text/plain', 'image/doc.png');
INSERT INTO `cltype` VALUES (4, 'video/mp4', 'image/video.png');
INSERT INTO `cltype` VALUES (5, 'application/octet-stream', 'image/zip.png');
INSERT INTO `cltype` VALUES (6, 'other', 'image/stream.png');
INSERT INTO `cltype` VALUES (7, 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'image/xlsx.png');
INSERT INTO `cltype` VALUES (8, 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'image/word.png');
INSERT INTO `cltype` VALUES (9, 'audio/mp3', 'image/music.png');
INSERT INTO `cltype` VALUES (10, 'application/vnd.ms-powerpoint', 'image/ppt.png');
INSERT INTO `cltype` VALUES (11, 'application/vnd.openxmlformats-officedocument.presentationml.presentation', 'image/ppt.png');
INSERT INTO `cltype` VALUES (12, 'application/msword', 'image/word.png');
INSERT INTO `cltype` VALUES (13, 'image/jpeg', 'image/picture.png');
INSERT INTO `cltype` VALUES (14, 'image/png', 'image/picture.png');
INSERT INTO `cltype` VALUES (15, 'application/x-msdownload', 'image/exe.png');