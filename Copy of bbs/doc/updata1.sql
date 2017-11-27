create database bbs1;

use bbs1;

CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(20)NOT NULL,
  password varchar(50)NOT NULL,
  sex varchar(2) not null,
  headImage varchar(100) not null,
  crtTime datetime,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

#创建板块表
create table board(
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(20) not null,
	pid int(11) not null,
	primary key(id)
	)engine=InnoDB default charset=utf8;




#往board表中插入数据
insert into board(name,pid)values(".net技术",0);
insert into board(name,pid)values("java技术",0);
insert into board(name,pid)values("数据库技术",0);
insert into board(name,pid)values("娱乐",0);

insert into board(name,pid)values("c#语言",1);
insert into board(name,pid)values("WinForms技术",1);
insert into board(name,pid)values("ADO.NET",1);
insert into board(name,pid)values("ASP.NET.1",1);

insert into board(name,pid)values("java技术",2);
insert into board(name,pid)values("jsp技术",2);
insert into board(name,pid)values("servlet技术",2);
insert into board(name,pid)values("eclipse应用",2);

insert into board(name,pid)values("sql server基础",3);
insert into board(name,pid)values("sql server高级",3);

insert into board(name,pid)values("灌水乐园",4);



#帖子表
create table topic(
	id int(11) not null auto_increment,
	title varchar(100)not null,
	body text not null,
	uid int(11)not null,
	bid int(11)not null,
	creTime datetime not null,
	upTime datetime not null,
	replyCount int(11) default 0,
	primary key (id)
	)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

	
	#回复帖子表
create table reply(
	id int(11) not null auto_increment,
	title varchar(100)not null,
	body text not null,
	uid int(11)not null,
	tid int(11)not null,
	creTime datetime not null,
	upTime datetime not null,	
	primary key (id)
	)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
	

