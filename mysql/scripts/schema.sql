DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `tbl_role` WRITE;
INSERT INTO `tbl_role` VALUES (1,'Developer');
UNLOCK TABLES;

DROP TABLE IF EXISTS `tbl_company`;
CREATE TABLE `tbl_company` (
  `id` int NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `tbl_company` WRITE;
INSERT INTO `tbl_company` VALUES (1,'Gaming Company');
UNLOCK TABLES;


DROP TABLE IF EXISTS `tbl_country`;
CREATE TABLE `tbl_country` (
  `id` int NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `tbl_country` WRITE;
INSERT INTO `tbl_country` VALUES (1,'Italy'),(2,'USA'),(3,'UK');
UNLOCK TABLES;


DROP TABLE IF EXISTS `rel_country_company`;
CREATE TABLE `rel_country_company` (
  `country` int NOT NULL,
  `company` int NOT NULL,
  PRIMARY KEY (`country`,`company`),
  KEY `company` (`company`),
  CONSTRAINT `rel_country_company_ibfk_1` FOREIGN KEY (`country`) REFERENCES `tbl_country` (`id`),
  CONSTRAINT `rel_country_company_ibfk_2` FOREIGN KEY (`company`) REFERENCES `tbl_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `rel_country_company` WRITE;
INSERT INTO `rel_country_company` VALUES (1,1),(2,1),(3,1);
UNLOCK TABLES;


DROP TABLE IF EXISTS `tbl_employees`;
CREATE TABLE `tbl_employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(250) NOT NULL,
  `last_name` varchar(250) NOT NULL,
  `email` varchar(250) DEFAULT NULL,
  `ROLE` int DEFAULT NULL,
  `COMPANY` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ROLE` (`ROLE`),
  KEY `FK_COMP` (`COMPANY`),
  CONSTRAINT `FK_COMP` FOREIGN KEY (`COMPANY`) REFERENCES `tbl_company` (`id`),
  CONSTRAINT `tbl_employees_ibfk_1` FOREIGN KEY (`ROLE`) REFERENCES `tbl_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `tbl_employees` WRITE;
INSERT INTO `tbl_employees` VALUES (1,'Lokesh','Gupta','howtodoinjava@gmail.com',1,1),(2,'John','Doe','xyz@email.com',1,1),(3,'Luca','Cirillo','luca.cirillo@capgemini.com',1,1);
UNLOCK TABLES;

DROP TABLE IF EXISTS `coffee`;
CREATE TABLE `coffee`  (
    coffee_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    brand varchar(20),
    origin varchar(20),
    characteristics varchar(30)
);

DROP TABLE IF EXISTS `product_single_table`;
CREATE TABLE `product_single_table`  (
    product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type varchar(20),
	name varchar(20),
    description varchar(150),
    quantity int,
	price float(10,2),
	color varchar(30),
	manufacture varchar(30),
	city varchar(30),
	address varchar(200),
	wheels_num int,
	displacement int,
	door_num int,
	motorcycle_type varchar(150),
	cpu varchar(150),
	gpu varchar(150),
	ram varchar(150)
);
INSERT INTO `product_single_table` VALUES 
(1,'MTR','Z1000SX','best motorcycle',3,15000.00, 'black', 'Kawasaki', 'Roma', 'Via del Corso', null, 1000, null, 'sport touring', null, null, null),
(2,'MTR','H2R','dream motorcycle',1,50000.00, 'black green', 'Kawasaki', 'Roma', 'Piazza Navona', null, 1000, null, 'racing', null, null, null),
(3,'CAR','Clio 2','my car',3,8000.00, 'red', 'Renault', 'Roma', 'Via di Torre Spaccata', 4, 1000, 5, null, null, null, null),
(4,'CAR','Punto','friend car',10,12000.00, 'white', 'FIAT', 'Roma', 'Via Francesco Coccu Ortu', 4, 1200, 5, null, null, null, null),
(5,'PC','Asus Rog Strix','pc of mine',21,1200.00, null, 'Asus', 'Roma', 'Via Ubaldo Comandini', null, null, null, null, 'Intel I9 10th', 'RTX3060', '16BG'),
(6,'PC','Assembled','pc of parent',21,1500.00, null, 'Assembled', 'Roma', 'Via Raimondo Scintu', null, null, null, null, 'Intel I9 10th', 'GTX1060', '16BG');


DROP TABLE IF EXISTS `product_jt_parent_table`;
CREATE TABLE `product_jt_parent_table`  (
    product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(20),
    description varchar(150),
    quantity int,
	price float(10,2),
	manufacture varchar(30),
	city varchar(30),
	address varchar(200)
);
INSERT INTO `product_jt_parent_table` VALUES 
(1,'Z1000SX','best motorcycle',3, 15000.00, 'Kawasaki', 'Roma', 'Via del Corso'),
(2,'H2R','dream motorcycle',1,50000.00, 'Kawasaki', 'Roma', 'Piazza Navona'),
(3,'Clio 2','my car',3,8000.00, 'Renault', 'Roma', 'Via di Torre Spaccata'),
(4,'Punto','friend car',10,12000.00, 'FIAT', 'Roma', 'Via Francesco Coccu Ortu'),
(5,'Asus Rog Strix','pc of mine',21,1200.00, 'Asus', 'Roma', 'Via Ubaldo Comandini'),
(6,'Assembled','pc of parent',21,1500.00, 'Assembled', 'Roma', 'Via Raimondo Scintu');


DROP TABLE IF EXISTS `product_jt_motorcycle_table`;
CREATE TABLE `product_jt_motorcycle_table`  (
    mtr_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	color varchar(30),
	displacement int,
	motorcycle_type varchar(150),
	CONSTRAINT `FK_MTR` FOREIGN KEY (`mtr_id`) REFERENCES `product_jt_parent_table` (`product_id`)
);
INSERT INTO `product_jt_motorcycle_table` VALUES 
(1, 'black', 1000, 'sport touring'),
(2, 'black green', 1000,'racing');

DROP TABLE IF EXISTS `product_jt_car_table`;
CREATE TABLE `product_jt_car_table`  (
    car_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	color varchar(30),
	displacement int,
	wheels_num int,
	door_num int,
	CONSTRAINT `FK_CAR` FOREIGN KEY (`car_id`) REFERENCES `product_jt_parent_table` (`product_id`)
);
INSERT INTO `product_jt_car_table` VALUES 
(3, 'red', 1000, 4, 5),
(4, 'white', 1200, 4, 5);


DROP TABLE IF EXISTS `product_jt_pc_table`;
CREATE TABLE `product_jt_pc_table`  (
    pc_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cpu varchar(150),
	gpu varchar(150),
	ram varchar(150),
	CONSTRAINT `FK_PC` FOREIGN KEY (`pc_id`) REFERENCES `product_jt_parent_table` (`product_id`)
);
INSERT INTO `product_jt_pc_table` VALUES 
(5, 'Intel I9 10th', 'RTX3060', '16BG'),
(6, 'Intel I9 10th', 'GTX1060', '16BG');


DROP TABLE IF EXISTS `product_msc_motorcycle_table`;
CREATE TABLE `product_msc_motorcycle_table`  (
    product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(20),
    description varchar(150),
    quantity int,
	price float(10,2),
	manufacture varchar(30),
	city varchar(30),
	address varchar(200),
	color varchar(30),
	displacement int,
	motorcycle_type varchar(150)
);
INSERT INTO `product_msc_motorcycle_table` VALUES 
(1,'Z1000SX','best motorcycle',3, 15000.00, 'Kawasaki', 'Roma', 'Via del Corso', 'black', 1000, 'sport touring'),
(2,'H2R','dream motorcycle',1,50000.00, 'Kawasaki', 'Roma', 'Piazza Navona', 'black green', 1000,'racing');


DROP TABLE IF EXISTS `product_msc_car_table`;
CREATE TABLE `product_msc_car_table`  (
    product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(20),
    description varchar(150),
    quantity int,
	price float(10,2),
	manufacture varchar(30),
	city varchar(30),
	address varchar(200),
	color varchar(30),
	displacement int,
	wheels_num int,
	door_num int
);
INSERT INTO `product_msc_car_table` VALUES 
(3,'Clio 2','my car',3,8000.00, 'Renault', 'Roma', 'Via di Torre Spaccata', 'red', 1000, 4, 5),
(4,'Punto','friend car',10,12000.00, 'FIAT', 'Roma', 'Via Francesco Coccu Ortu', 'white', 1200, 4, 5);


DROP TABLE IF EXISTS `product_msc_pc_table`;
CREATE TABLE `product_msc_pc_table`  (
    product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(20),
    description varchar(150),
    quantity int,
	price float(10,2),
	manufacture varchar(30),
	city varchar(30),
	address varchar(200),
	cpu varchar(150),
	gpu varchar(150),
	ram varchar(150)
);
INSERT INTO `product_msc_pc_table` VALUES 
(5,'Asus Rog Strix','pc of mine',21,1200.00, 'Asus', 'Roma', 'Via Ubaldo Comandini', 'Intel I9 10th', 'RTX3060', '16BG'),
(6,'Assembled','pc of parent',21,1500.00, 'Assembled', 'Roma', 'Via Raimondo Scintu', 'Intel I9 10th', 'GTX1060', '16BG');


DROP TABLE IF EXISTS `product_tpc_motorcycle_table`;
CREATE TABLE `product_tpc_motorcycle_table`  (
    product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(20),
    description varchar(150),
    quantity int,
	price float(10,2),
	manufacture varchar(30),
	city varchar(30),
	address varchar(200),
	color varchar(30),
	displacement int,
	motorcycle_type varchar(150)
);
INSERT INTO `product_tpc_motorcycle_table` VALUES 
(1,'Z1000SX','best motorcycle',3, 15000.00, 'Kawasaki', 'Roma', 'Via del Corso', 'black', 1000, 'sport touring'),
(2,'H2R','dream motorcycle',1,50000.00, 'Kawasaki', 'Roma', 'Piazza Navona', 'black green', 1000,'racing');


DROP TABLE IF EXISTS `product_tpc_car_table`;
CREATE TABLE `product_tpc_car_table`  (
    product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(20),
    description varchar(150),
    quantity int,
	price float(10,2),
	manufacture varchar(30),
	city varchar(30),
	address varchar(200),
	color varchar(30),
	displacement int,
	wheels_num int,
	door_num int
);
INSERT INTO `product_tpc_car_table` VALUES 
(3,'Clio 2','my car',3,8000.00, 'Renault', 'Roma', 'Via di Torre Spaccata', 'red', 1000, 4, 5),
(4,'Punto','friend car',10,12000.00, 'FIAT', 'Roma', 'Via Francesco Coccu Ortu', 'white', 1200, 4, 5);


DROP TABLE IF EXISTS `product_tpc_pc_table`;
CREATE TABLE `product_tpc_pc_table`  (
    product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(20),
    description varchar(150),
    quantity int,
	price float(10,2),
	manufacture varchar(30),
	city varchar(30),
	address varchar(200),
	cpu varchar(150),
	gpu varchar(150),
	ram varchar(150)
);
INSERT INTO `product_tpc_pc_table` VALUES 
(5,'Asus Rog Strix','pc of mine',21,1200.00, 'Asus', 'Roma', 'Via Ubaldo Comandini', 'Intel I9 10th', 'RTX3060', '16BG'),
(6,'Assembled','pc of parent',21,1500.00, 'Assembled', 'Roma', 'Via Raimondo Scintu', 'Intel I9 10th', 'GTX1060', '16BG');