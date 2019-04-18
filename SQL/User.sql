CREATE TABLE user 
(id SERIAL PRIMARY KEY UNIQUE Not Null,
login_id varchar(255) UNIQUE Not Null,
name varchar(255) Not Null,
birth_date DATE Not Null,
password varchar(255) Not Null,
create_date DATETIME Not Null,
update_date DATETIME Not Null);
