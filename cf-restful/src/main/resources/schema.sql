-- MySQL Script generated by MySQL Workbench
-- Tue Apr  4 11:19:04 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema career_focus
-- -----------------------------------------------------
-- DB created from ER Diagram.

-- -----------------------------------------------------
-- Schema career_focus
--
-- DB created from ER Diagram.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `career_focus` DEFAULT CHARACTER SET utf8 ;
USE `career_focus` ;

-- -----------------------------------------------------
-- Table `career_focus`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '1 - student\n2 - super admin\n3 - branch admin',
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role` int(2) NOT NULL,
  `first_name` varchar(46) NOT NULL,
  `last_name` varchar(46) NOT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`user_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_address` (
  `user_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  KEY `fk_user_address_user_idx` (`user_id`),
  KEY `fk_user_address_address_idx` (`address_id`),
  CONSTRAINT `FKdaaxogn1ss81gkcsdn05wi6jp` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `FKk2ox3w9jm7yd6v1m5f68xibry` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_user_address_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_address_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------------------------
-- Table `career_focus`.`user_phone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_phone` (
  `user_phone_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` int(2) NOT NULL COMMENT '1 - mobile\n2 - home\n3 - other',
  `phone_no` varchar(45) NOT NULL,
  `is_primary` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_phone_id`),
  KEY `fk_user_phone_user1_idx` (`user_id`),
  CONSTRAINT `FKaqeg9vtqjgkgi9vw7xy66va7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_user_phone_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`user_profile_pic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_profile_pic` (
  `user_id` int(11) NOT NULL,
  `picture` blob,
  PRIMARY KEY (`user_id`),
  KEY `fk_user_profile_pic_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_profile_pic_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`states`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `states` (
  `state_id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `street_address` varchar(2000) DEFAULT NULL,
  `land_mark` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state_id` int(11) DEFAULT NULL,
  `pin_code` int(6) DEFAULT NULL,
  `student_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `fk_address_state_idx` (`state_id`),
  CONSTRAINT `FK1uk1eurcj7mhj5tpql2yurw32` FOREIGN KEY (`state_id`) REFERENCES `states` (`state_id`),
  CONSTRAINT `fk_address_state` FOREIGN KEY (`state_id`) REFERENCES `states` (`state_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`center`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `center` (
  `center_id` int(11) NOT NULL AUTO_INCREMENT,
  `center_code` varchar(45) NOT NULL,
  `place` varchar(45) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `center_latitude` varchar(45) DEFAULT NULL,
  `center_longitude` varchar(45) DEFAULT NULL,
  `is_franchise` tinyint(1) NOT NULL,
  PRIMARY KEY (`center_id`),
  UNIQUE KEY `center_id_UNIQUE` (`center_id`),
  UNIQUE KEY `center_code_UNIQUE` (`center_code`),
  KEY `fk_address_id_idx` (`address_id`),
  CONSTRAINT `FKt1fq4spc0h4xy74nxrxxcmsfe` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `fk_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student` (
  `user_id` int(11) NOT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `center_id` int(11) NOT NULL,
  `fee_status` varchar(45) NOT NULL,
  `expiry_date` date NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `fk_user_id_idx` (`user_id`),
  KEY `fk_center_id_idx` (`center_id`),
  CONSTRAINT `FKm9qatplyi1302g7wgq75743fj` FOREIGN KEY (`center_id`) REFERENCES `center` (`center_id`),
  CONSTRAINT `fk_center_id` FOREIGN KEY (`center_id`) REFERENCES `center` (`center_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`center_classroom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`center_classroom` (
  `center_classroom_id` INT NOT NULL,
  `center_id` INT NOT NULL,
  PRIMARY KEY (`center_classroom_id`),
  INDEX `fk_center_classroom_center1_idx` (`center_id` ASC),
  CONSTRAINT `fk_center_classroom_center1`
    FOREIGN KEY (`center_id`)
    REFERENCES `career_focus`.`center` (`center_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`center_working_days`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`center_working_days` (
  `center_working_days_id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  PRIMARY KEY (`center_working_days_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`center_classroom_subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`center_classroom_subject` (
  `center_classroom_subject_id` INT NOT NULL AUTO_INCREMENT,
  `center_classroom_id` INT NOT NULL,
  `subject` VARCHAR(150) NULL,
  `user_id` INT NOT NULL,
  `center_working_days_id` INT NOT NULL,
  PRIMARY KEY (`center_classroom_subject_id`),
  INDEX `fk_center_classroom_subject_center_classroom1_idx` (`center_classroom_id` ASC),
  INDEX `fk_center_classroom_subject_user1_idx` (`user_id` ASC),
  INDEX `fk_center_classroom_subject_center_working_days1_idx` (`center_working_days_id` ASC),
  CONSTRAINT `fk_center_classroom_subject_center_classroom1`
    FOREIGN KEY (`center_classroom_id`)
    REFERENCES `career_focus`.`center_classroom` (`center_classroom_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_center_classroom_subject_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `career_focus`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_center_classroom_subject_center_working_days1`
    FOREIGN KEY (`center_working_days_id`)
    REFERENCES `career_focus`.`center_working_days` (`center_working_days_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`attendance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`attendance` (
  `user_id` INT NOT NULL,
  `center_working_days_id` INT NOT NULL,
  `status` TINYINT(1) NOT NULL COMMENT '0 - Absent\n1 - Present',
  INDEX `fk_attendance_user1_idx` (`user_id` ASC),
  INDEX `fk_attendance_center_working_days1_idx` (`center_working_days_id` ASC),
  PRIMARY KEY (`user_id`, `center_working_days_id`),
  CONSTRAINT `fk_attendance_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `career_focus`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_attendance_center_working_days1`
    FOREIGN KEY (`center_working_days_id`)
    REFERENCES `career_focus`.`center_working_days` (`center_working_days_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`category` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`question_paper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `question_paper` (
  `question_paper_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `exam_code` varchar(45) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `duration` int(11) NOT NULL COMMENT 'minutes',
  `no_of_questions` int(11) NOT NULL,
  `no_of_options` int(2) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_demo` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`question_paper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `question_paper_category` (
  `question_paper_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_paper_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `no_of_questions` int(11) NOT NULL,
  `no_of_sub_category` int(11) NOT NULL,
  `correct_answer_mark` float NOT NULL,
  `negative_mark` float NOT NULL,
  `r_order` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`question_paper_category_id`),
  KEY `FK1u18p6vxpcfrbptvvjpk6cr53` (`category_id`),
  KEY `FKe3h7gf2fchw73pyeriowtbfxi` (`question_paper_id`),
  CONSTRAINT `FK1u18p6vxpcfrbptvvjpk6cr53` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FKe3h7gf2fchw73pyeriowtbfxi` FOREIGN KEY (`question_paper_id`) REFERENCES `question_paper` (`question_paper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`question_paper_sub_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `question_paper_sub_category` (
  `question_paper_sub_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_paper_category_id` int(11) NOT NULL,
  `no_of_questions` int(3) NOT NULL,
  `direction` text,
  `description` text,
  `r_order` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`question_paper_sub_category_id`),
  KEY `FK6t91utt20hhd58qm9njbm3qyp` (`question_paper_category_id`),
  CONSTRAINT `FK6t91utt20hhd58qm9njbm3qyp` FOREIGN KEY (`question_paper_category_id`) REFERENCES `question_paper_category` (`question_paper_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;



-- -----------------------------------------------------
-- Table `career_focus`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(2000) NOT NULL,
  `correct_option_no` int(11) NOT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`question_option`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `question_option` (
  `question_id` int(11) NOT NULL,
  `option_no` int(11) NOT NULL,
  `option` varchar(1000) NOT NULL,
  PRIMARY KEY (`question_id`,`option_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`question_paper_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `question_paper_question` (
  `question_paper_sub_category_id` int(11) NOT NULL,
  `question_no` int(3) NOT NULL COMMENT 'indicates the question number in the question paper\n',
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`question_paper_sub_category_id`,`question_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`question_paper_demo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`question_paper_demo` (
  `question_paper_demo_id` INT NOT NULL,
  `question_paper_id` INT NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  PRIMARY KEY (`question_paper_demo_id`),
  INDEX `fk_question_paper_demo_question_paper1_idx` (`question_paper_id` ASC),
  CONSTRAINT `fk_question_paper_demo_question_paper1`
    FOREIGN KEY (`question_paper_id`)
    REFERENCES `career_focus`.`question_paper` (`question_paper_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`question_paper_bundle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`question_paper_bundle` (
  `question_paper_bundle_id` INT NOT NULL AUTO_INCREMENT,
  `duration` INT NOT NULL,
  `duration_type` INT(1) NOT NULL COMMENT '1 - days\n2 - weeks\n3 - months',
  `tests_per_week` VARCHAR(45) NULL,
  `no_of_questions` INT NULL,
  `status` TINYINT(1) NULL COMMENT '0 - disabled\n1 - enabled',
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`question_paper_bundle_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`question_paper_bundle_question_papers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`question_paper_bundle_question_papers` (
  `id` INT NOT NULL,
  `question_paper_bundle_id` INT NOT NULL,
  `is_demo` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_question_paper_bundle_question_papers_question_paper_bun_idx` (`question_paper_bundle_id` ASC),
  CONSTRAINT `fk_question_paper_bundle_question_papers_question_paper_bundle1`
    FOREIGN KEY (`question_paper_bundle_id`)
    REFERENCES `career_focus`.`question_paper_bundle` (`question_paper_bundle_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`center_exam_question_paper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`center_exam_question_paper` (
  `center_exam_question_paper_id` INT NOT NULL AUTO_INCREMENT,
  `question_paper_id` INT NOT NULL,
  `center_id` INT NOT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  PRIMARY KEY (`center_exam_question_paper_id`),
  INDEX `fk_center_exam_question_paper_question_paper1_idx` (`question_paper_id` ASC),
  INDEX `fk_center_exam_question_paper_center1_idx` (`center_id` ASC),
  CONSTRAINT `fk_center_exam_question_paper_question_paper1`
    FOREIGN KEY (`question_paper_id`)
    REFERENCES `career_focus`.`question_paper` (`question_paper_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_center_exam_question_paper_center1`
    FOREIGN KEY (`center_id`)
    REFERENCES `career_focus`.`center` (`center_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`question_paper_bundle_combo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`question_paper_bundle_combo` (
  `question_paper_bundle_combo_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`question_paper_bundle_combo_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`question_paper_bundle_combo_bundles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`question_paper_bundle_combo_bundles` (
  `question_paper_bundle_combo_bundle_id` INT NOT NULL AUTO_INCREMENT,
  `question_paper_bundle_combo_id` INT NOT NULL,
  `question_paper_bundle_id` INT NOT NULL,
  PRIMARY KEY (`question_paper_bundle_combo_bundle_id`),
  INDEX `fk_question_paper_bundle_combo_bundles_question_paper_bundl_idx` (`question_paper_bundle_combo_id` ASC),
  INDEX `fk_question_paper_bundle_combo_bundles_question_paper_bundl_idx1` (`question_paper_bundle_id` ASC),
  CONSTRAINT `fk_question_paper_bundle_combo_bundles_question_paper_bundle_1`
    FOREIGN KEY (`question_paper_bundle_combo_id`)
    REFERENCES `career_focus`.`question_paper_bundle_combo` (`question_paper_bundle_combo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_paper_bundle_combo_bundles_question_paper_bundle1`
    FOREIGN KEY (`question_paper_bundle_id`)
    REFERENCES `career_focus`.`question_paper_bundle` (`question_paper_bundle_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`question_paper_bundle_price`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`question_paper_bundle_price` (
  `question_paper_bundle_price_id` INT NOT NULL AUTO_INCREMENT,
  `is_bundle_combo` TINYINT(1) NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  `bundle_or_combo_id` INT NOT NULL,
  PRIMARY KEY (`question_paper_bundle_price_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`video_tutorials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`video_tutorials` (
  `video_tutorial_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`video_tutorial_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`testimonials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`testimonials` (
  `testimonial_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`testimonial_id`),
  INDEX `fk_testimonials_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_testimonials_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `career_focus`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;





-- -----------------------------------------------------
-- Table `career_focus`.`syllabus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`syllabus` (
  `syllabus_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `sections_id` INT NOT NULL,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`syllabus_id`),
  INDEX `fk_syllabus_category1_idx` (`category_id` ASC),
  INDEX `fk_syllabus_sections1_idx` (`sections_id` ASC),
  CONSTRAINT `fk_syllabus_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `career_focus`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_syllabus_sections1`
    FOREIGN KEY (`sections_id`)
    REFERENCES `career_focus`.`sections` (`sections_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`question_paper_mark`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`question_paper_mark` (
  `question_paper_mark_id` INT NOT NULL AUTO_INCREMENT,
  `question_paper_question_paper_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `mark` INT NULL,
  PRIMARY KEY (`question_paper_mark_id`),
  INDEX `fk_question_paper_mark_question_paper1_idx` (`question_paper_question_paper_id` ASC),
  INDEX `fk_question_paper_mark_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_question_paper_mark_question_paper1`
    FOREIGN KEY (`question_paper_question_paper_id`)
    REFERENCES `career_focus`.`question_paper` (`question_paper_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_paper_mark_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `career_focus`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`user_question_paper_answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_question_paper_answer` (
  `user_question_paper_answer_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `question_question_id` int(11) NOT NULL,
  `question_option_question_option_id` int(11) NOT NULL,
  PRIMARY KEY (`user_question_paper_answer_id`),
  KEY `fk_user_question_paper_answer_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_question_paper_answer_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`fee_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`fee_details` (
  `fee_details_id` INT NOT NULL AUTO_INCREMENT,
  `user_user_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `amount` FLOAT NOT NULL,
  `question_paper_bundle_id` INT NOT NULL,
  `transaction_status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`fee_details_id`),
  INDEX `fk_fee_details_user1_idx` (`user_user_id` ASC),
  INDEX `fk_fee_details_question_paper_bundle1_idx` (`question_paper_bundle_id` ASC),
  CONSTRAINT `fk_fee_details_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `career_focus`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fee_details_question_paper_bundle1`
    FOREIGN KEY (`question_paper_bundle_id`)
    REFERENCES `career_focus`.`question_paper_bundle` (`question_paper_bundle_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_focus`.`announcements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `announcements` (
  `announcement_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) NOT NULL,
  `description` text,
  `is_current` tinyint(1) NOT NULL DEFAULT '0',
  `announcement_order` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`announcement_id`),
  UNIQUE KEY `announcement_id_UNIQUE` (`announcement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`announcement_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `announcement_image` (
  `announcement_id` int(11) NOT NULL,
  `image` blob NOT NULL,
  PRIMARY KEY (`announcement_id`),
  UNIQUE KEY `accouncement_id_UNIQUE` (`announcement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `career_focus`.`sections`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `career_focus`.`sections` (
  `sections_id` INT NOT NULL,
  `name` VARCHAR(84) NOT NULL,
  PRIMARY KEY (`sections_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
