drop table member, academy, admin, freeboard, review, class, report, filter, imagefile;

CREATE TABLE member(						// 일반회원(USER),기업회원(COMPANY)으로 구분 (스프링시큐리티에서 자체 구분)
	mIndex INT(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,	// 회원 번호 (기본키)
    id varchar(20) NOT NULL,					// 아이디
    pwd varchar(100) NOT NULL,					// 비밀번호
    mName varchar(20) NOT NULL,					// 이름	
    phone varchar(20) NOT NULL,					// 전화번호
    email varchar(30) NOT NULL,					// 이메일
    companyNum varchar(20),					// 사업자번호(기업)
    address varchar(50),					// 주소(기업)
    joinDate DATETIME default NOW()				// 가입일(기본값)
);

CREATE TABLE academy(
	aIndex INT(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id VARCHAR(10) NOT NULL,
    pwd VARCHAR(10) NOT NULL,
    aName VARCHAR(10) NOT NULL,
    phone VARCHAR(13) NOT NULL,
    email VARCHAR(25) NOT NULL,
    companyNum VARCHAR(15) NOT NULL, //크기 12에서 15로 변경 했음
    address VARCHAR(25) NOT NULL,
    location VARCHAR(50) NOT NULL,
    grade int(10) default 1200,
    totalscore int(10) default 0,
    reviewcount int(10) default 0
);

CREATE TABLE admin(
	adminIndex INT(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    adminId varchar(10) not null,
    adminPwd varchar(20) not null
);

CREATE TABLE freeboard(
	bIndex int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title varchar(20) not null,
    contents varchar(200),
    m_index int(5) not null,
    writedate datetime default now(),
    
    report int(5) default 0
);

CREATE TABLE imagefile(
	iIndex int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    iName varchar(10) not null
);

CREATE TABLE review(
	rvIndex int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    writedate datetime default now(),
    score double(4,2) default 0,
    contents varchar(200),
    
    report int(5) default 0,
    
    mIndex int(5) not null,
    mId varchar(10) not null,
    
    cIndex int(5) not null,
    cName varchar(10) not null,
    cScore double(4,2) default 0
);

CREATE TABLE filter(
	category varchar(20),
    location varchar(20)
);

CREATE TABLE report(
	rpDate datetime default now(),
    count int(5) default 0,
    contents varchar(100),
    
    rwIndex int(5) default -1,  # 둘 중 하나만 숫자를 게시글 번호로 바꾸어 게시판 성격 구별하도록 구현하려 합니다.
    fIndex int(5) default -1,
    
    mIndex int(5),
    mId varchar(15)
);

CREATE TABLE class(
	cIndex int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cName varchar(50) not null,
    category varchar(20) not null,
    price int(11) default 0,
    score double(4,2) default 0,
    cStatus varchar(10) default 'limited',
    sPeriod datetime default now(),
    ePeriod datetime not null,
    
    aIndex int(5) not null,
    aName varchar(10),
    location varchar(50),
    grade int(10),
    
    iIndex int(5) not null
);

