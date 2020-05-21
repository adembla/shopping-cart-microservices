drop table if exists users;
create table users(
    username varchar(255) not null primary key,
    password varchar(255) not null,
    enabled boolean not null
)Engine=InnoDB;

create table groups (
    id bigint AUTO_INCREMENT primary key,
    group_name varchar(50) not null
)Engine=InnoDB;

create table group_authorities (
    group_id bigint not null,
    authority varchar(50) not null,
    constraint fk_group_authorities_group foreign key(group_id) references groups(id)
)Engine=InnoDB;

create table group_members (
    id bigint AUTO_INCREMENT primary key,
    username varchar(50) not null,
    group_id bigint not null,
    constraint fk_group_members_group foreign key(group_id) references groups(id)
)Engine=InnoDB;

drop table if exists oauth_client_details;
create table oauth_client_details (
    client_id varchar(255) primary key,
    resource_ids varchar(255),
    client_secret varchar(255),
    scope varchar(255),
    authorized_grant_types varchar(255),
    web_server_redirect_uri varchar(255),
    authorities varchar(255),
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information varchar(4096),
    autoapprove varchar(255)
)Engine=InnoDB;

drop table if exists oauth_access_token;
create table oauth_access_token (
  authentication_id VARCHAR(255),
  token_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  token VARBINARY(100),
  authentication VARBINARY(100),
  refresh_token VARCHAR(255),
  PRIMARY KEY(authentication_id)
)Engine=InnoDB;

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token VARBINARY(100),
  authentication VARBINARY(100)
)Engine=InnoDB;


--
--Users: john/john@123 kelly/kelly@123
--Password encrypted using CodeachesBCryptPasswordEncoder.java (4 rounds)
--

INSERT INTO users (username,password,enabled) 
    VALUES ('john', '$2a$04$xqJH/AWpC89pBBFb7i9VU.zoWbOrE2gvdFcfTAOE1bCF5.tNvVXXu', TRUE);
INSERT INTO users (username,password,enabled) 
    VALUES ('kelly','$2a$04$IpZnGqXXgNvvMbqlg/tc7uJUM.1nj/5KtqnFlxRpRN2RqWUFV4lg6', TRUE);

INSERT INTO groups (id, group_name) VALUES (1, 'INVENTORY_GROUP_1');
INSERT INTO groups (id, group_name) VALUES (2, 'INVENTORY_GROUP_2');

INSERT INTO group_authorities (group_id, authority) VALUES (1, 'INVENTORY_VIEW');
INSERT INTO group_authorities (group_id, authority) VALUES (1, 'INVENTORY_ADD');

INSERT INTO group_authorities (group_id, authority) VALUES (2, 'INVENTORY_VIEW');

INSERT INTO group_members (username, group_id) VALUES ('john', 1);
INSERT INTO group_members (username, group_id) VALUES ('kelly', 2);

--
--Client: appclient/appclient@123
--Password encrypted using CodeachesBCryptPasswordEncoder.java (4 rounds)
--
INSERT INTO
  oauth_client_details (
    client_id,
    client_secret,
    resource_ids,
    scope,
    authorized_grant_types,
    access_token_validity,
    refresh_token_validity
  )
VALUES
  (
    'appclient',
    '$2a$04$ZVENvHhtvDKPSgMsP9AK0usr9o3Dpo2G3aSAT1HQZSZUB7CoAP6QC',
    'carInventory',
    'read,write',
    'authorization_code,check_token,refresh_token,password',
    1000000,
    1000000
  );
