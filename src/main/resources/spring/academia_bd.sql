CREATE  TABLE IF NOT EXISTS USERS (
  USERS_ID INTEGER IDENTITY PRIMARY KEY, 
  USERNAME VARCHAR(50) NOT NULL,
  password VARCHAR(128) NOT NULL,
  name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50),
  email VARCHAR(255) NOT NULL,
  enabled BOOLEAN NOT NULL,
  image_url VARCHAR(255),
  UNIQUE (USERNAME)
);

CREATE TABLE IF NOT EXISTS USER_ROLES (
  USER_ROLES_ID INTEGER IDENTITY PRIMARY KEY, 
  USERNAME varchar(45) NOT NULL,
  role varchar(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS QUESTION (
	QUESTION_ID INTEGER IDENTITY PRIMARY KEY, 
	CATEGORY VARCHAR(20) NOT NULL,
	TITLE VARCHAR(255) NOT NULL,
	DESCRIPTION VARCHAR(2550) NOT NULL,
	ANSWERED BOOLEAN NOT NULL,
	USERS_ID INTEGER NOT NULL,
	CREATE_DATE TIMESTAMP NOT NULL,
	FOREIGN KEY (USERS_ID) REFERENCES USERS(USERS_ID)
);

CREATE TABLE IF NOT EXISTS COMMENT (
	COMMENT_ID INTEGER IDENTITY PRIMARY KEY,
	QUESTION_ID INTEGER NOT NULL,
	USERS_ID INTEGER NOT NULL,
	DESCRIPTION VARCHAR(2550) NOT NULL,
	ANSWER_DATE TIMESTAMP NOT NULL,
	HELPFUL BOOLEAN NOT NULL,
	FOREIGN KEY (QUESTION_ID) REFERENCES QUESTION(QUESTION_ID),
	FOREIGN KEY (USERS_ID) REFERENCES USERS(USERS_ID)
);

CREATE TABLE IF NOT EXISTS MESSAGE (
	MESSAGE_ID INTEGER IDENTITY PRIMARY KEY,
	DESCRIPTION VARCHAR(255) NOT NULL,
	QUESTION_ID INTEGER NOT NULL,
	SENDER_USER_ID INTEGER NOT NULL,
	RECIPIENT_USER_ID INTEGER NOT NULL,
	MESSAGE_DATE TIMESTAMP NOT NULL,
	IS_NEW BOOLEAN NOT NULL, 
	FOREIGN KEY (QUESTION_ID) REFERENCES QUESTION(QUESTION_ID),
	FOREIGN KEY (SENDER_USER_ID) REFERENCES USERS(USERS_ID),
	FOREIGN KEY (RECIPIENT_USER_ID) REFERENCES USERS(USERS_ID)
);

INSERT INTO users(username, password, enabled, name, email, image_url)
VALUES ('admin2','admin123', true, 'SuperAdmin', 'super.admin@softtek.com', 'C:\Users\CURSO\Documents\workspace-sts-3.3.0.RELEASE\user.PNG');
INSERT INTO users(username, password, enabled, name, email, image_url)
VALUES ('admin','admin123', true, 'Administrator', 'ruben.valderrabano@softtek.com', 'C:\Users\CURSO\Documents\workspace-sts-3.3.0.RELEASE\user.PNG');
INSERT INTO users(username, password, enabled, name, last_name, email, image_url)
VALUES ('user','user123', true, 'Ruben', 'Valderrabano', 'ruben.valderrabano@softtek.com', 'C:\Users\CURSO\Documents\workspace-sts-3.3.0.RELEASE\user.PNG');

INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('user', 'ROLE_USER');

INSERT INTO QUESTION (CATEGORY, TITLE, DESCRIPTION, ANSWERED, USERS_ID, CREATE_DATE)
VALUES ('SERVICE', 'What is an academy?', 'Academies are state-maintained but independently-run schools in England set up with the help of outside sponsors. They have more freedoms than schools under local authority control.', false, 1, NOW());

INSERT INTO QUESTION (CATEGORY, TITLE, DESCRIPTION, ANSWERED, USERS_ID, CREATE_DATE)
VALUES ('APP', 'Whats the app about?', 'Can anyone explain me this app please?', false, 1, NOW());

INSERT INTO QUESTION (CATEGORY, TITLE, DESCRIPTION, ANSWERED, USERS_ID, CREATE_DATE)
VALUES ('PRODUCT', 'What is your Product?', 'I unserstand that you sell some books and other products, can someone explain me more? ', false, 1, NOW());
