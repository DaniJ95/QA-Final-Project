drop table if exists pokemon CASCADE;
CREATE TABLE pokemon (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255),
    type VARCHAR(255),
    height VARCHAR(255),
    weight VARCHAR(255),
    gender VARCHAR(255),
    hp INT,
    attack INT,
    defence INT,
    specialattack INT,
    specialdefence INT,
    PRIMARY KEY (id)
);