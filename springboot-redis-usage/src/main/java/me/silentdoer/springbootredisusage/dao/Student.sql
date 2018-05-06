-- auto Generated on 2018-05-05 18:30:27 
-- DROP TABLE IF EXISTS `student`; 
CREATE TABLE student(
    `uid` BIGINT NOT NULL DEFAULT -1 COMMENT 'uid',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'student';
