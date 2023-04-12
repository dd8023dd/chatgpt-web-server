DROP TABLE IF EXISTS tb_apikey;
CREATE TABLE tb_apikey (
row_id bigint NOT NULL AUTO_INCREMENT,
create_date int NOT NULL,
create_time int NOT NULL,
update_date int NOT NULL,
update_time int NOT NULL,
update_times int NOT NULL,
api_key varchar(128) NOT NULL COMMENT 'api key;',
balance varchar(64) NOT NULL COMMENT '余额;',
expire_date int NOT NULL COMMENT '失效日期',
use_times int(8) NOT NULL COMMENT '使用次数',
valid_status varchar(2) NOT NULL COMMENT '正常-1,失效-2',
PRIMARY KEY (row_id) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index tb_apikey on tb_apikey(valid_status);

DROP TABLE IF EXISTS tb_user_key;
CREATE TABLE tb_user_key (
row_id bigint NOT NULL AUTO_INCREMENT,
create_date int NOT NULL,
create_time int NOT NULL,
update_date int NOT NULL,
update_time int NOT NULL,
update_times int NOT NULL,
user_key varchar(128) NOT NULL COMMENT 'user key;',
remaining_count int(8) NOT NULL COMMENT '剩余次数;',
expire_date int NOT NULL COMMENT '失效日期',
use_times int(8) NOT NULL COMMENT '使用次数',
valid_status varchar(2) NOT NULL COMMENT '正常-1,失效-2,未激活-3',
bind_ip varchar(32) NOT NULL COMMENT '绑定IP',
bind_phone varchar(16) NOT NULL COMMENT '绑定手机',
PRIMARY KEY (row_id) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index tb_user_key on tb_user_key(user_key);