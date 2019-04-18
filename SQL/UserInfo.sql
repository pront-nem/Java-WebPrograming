CREATE TABLE user (ID id SERIAL PRIMARYKEY UNIQUE Not Null,
ログインID login_id varchar(255) UNIQUE Not Null,
名前 name varchar(255) Not Null,
生年月日 birth_date DATE Not Null
パスワード password varchar(255) Not Null
作成日時 create_date DATETIME Not Null
更新日時 update_date DATETIME Not Null)
