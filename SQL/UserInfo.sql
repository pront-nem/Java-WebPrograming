CREATE TABLE user (ID id SERIAL PRIMARYKEY UNIQUE Not Null,
���O�C��ID login_id varchar(255) UNIQUE Not Null,
���O name varchar(255) Not Null,
���N���� birth_date DATE Not Null
�p�X���[�h password varchar(255) Not Null
�쐬���� create_date DATETIME Not Null
�X�V���� update_date DATETIME Not Null)
