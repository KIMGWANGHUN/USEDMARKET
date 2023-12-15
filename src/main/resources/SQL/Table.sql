-- //User Table
CREATE TABLE User (
	Email	varchar(25)	NOT NULL,
	Password	varchar(20)	NOT NULL,
	Name	varchar(20)	NOT NULL,
	Phone	varchar(20)	NOT NULL,
	NickName	varchar(20)	NOT NULL,
	Address	varchar(20)	NOT NULL
);


-- //Board Table
CREATE TABLE Board (
	b_num	int	NOT NULL PRIMARY KEY,
	b_title	varchar(50)	NOT NULL,
	b_category	varchar(20)	NOT NULL,
	b_price	varchar(50)	NOT NULL,
	b_content	varchar(255)	NOT NULL,
	b_address	varchar(255)	NOT NULL,
	b_detailAddress	varchar(255),
	b_date	date	NOT NULL,
	b_views	int	NOT NULL
	filename varchar(150),
	filepath varchar(300),
);