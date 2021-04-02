-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: sqa
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `config_score`
--

DROP TABLE IF EXISTS `config_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_score` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` int NOT NULL,
  `subject_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc6ruebqicama3rskjxnymw84` (`subject_id`),
  CONSTRAINT `FKc6ruebqicama3rskjxnymw84` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_score`
--

LOCK TABLES `config_score` WRITE;
/*!40000 ALTER TABLE `config_score` DISABLE KEYS */;
INSERT INTO `config_score` VALUES (1,1,1),(2,1,4);
/*!40000 ALTER TABLE `config_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_score_detail`
--

DROP TABLE IF EXISTS `config_score_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_score_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `percent` varchar(255) DEFAULT NULL,
  `config_socre_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh4smt9v65nujv8yyvtx641t2u` (`config_socre_id`),
  CONSTRAINT `FKh4smt9v65nujv8yyvtx641t2u` FOREIGN KEY (`config_socre_id`) REFERENCES `config_score` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_score_detail`
--

LOCK TABLES `config_score_detail` WRITE;
/*!40000 ALTER TABLE `config_score_detail` DISABLE KEYS */;
INSERT INTO `config_score_detail` VALUES (1,'attendance','10',1),(2,'exercise','20',1),(3,'practice','20',1),(4,'examFinal','50',1),(5,'attendance','10',2),(6,'exercise','30',2),(7,'examFinal','60',2);
/*!40000 ALTER TABLE `config_score_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'D17'),(2,'D18');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_major` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,'Công nghệ thông tin','DCCN'),(2,'An toàn thông tin','DCAT'),(3,'Công nghệ đa phương tiện','DCPT');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_of_user`
--

DROP TABLE IF EXISTS `result_of_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result_of_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `attendance` float DEFAULT NULL,
  `by_word` varchar(255) DEFAULT NULL,
  `exam_final` float DEFAULT NULL,
  `exercise` float DEFAULT NULL,
  `final_score` float NOT NULL,
  `practice` float DEFAULT NULL,
  `test` float DEFAULT NULL,
  `user_in_class_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsehtjfmpghiciibxkf17910fq` (`user_in_class_id`),
  CONSTRAINT `FKsehtjfmpghiciibxkf17910fq` FOREIGN KEY (`user_in_class_id`) REFERENCES `user_in_class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_of_user`
--

LOCK TABLES `result_of_user` WRITE;
/*!40000 ALTER TABLE `result_of_user` DISABLE KEYS */;
INSERT INTO `result_of_user` VALUES (1,10,'A',9,8,8.8,NULL,9,1),(2,9,'C',4,8,5.7,NULL,6,2),(3,10,'C',4,8,6.4,NULL,9,3),(4,9,'B',7.5,7,7.5,NULL,NULL,4),(5,10,'A',9,7,8.5,NULL,NULL,5),(6,10,'B',6.5,7.5,7.2,NULL,NULL,6),(7,10,'B',7.5,7.5,7.8,NULL,NULL,7);
/*!40000 ALTER TABLE `result_of_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,'Học kì 1 năm học 2020-2021'),(2,'Học kì 2 năm học 2020-2021');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `credits` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'INT1234',3,'Lập trình mạng'),(2,'INT1200',3,'Lập trình Web'),(3,'INT12341',2,'Cơ sở dữ liệu phân tán'),(4,'INT12341',2,'Quản lý dự án phần mềm');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_class`
--

DROP TABLE IF EXISTS `subject_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_class` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `credits` int NOT NULL,
  `teacher` varchar(255) DEFAULT NULL,
  `config_score_id` bigint DEFAULT NULL,
  `semester_id` bigint DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK43hkplanpixalxg862eq17geb` (`config_score_id`),
  KEY `FKfys4252naxblhuvtodx0ggw4j` (`semester_id`),
  KEY `FKiuy8yyhpqpr52bk049yrvxrxr` (`subject_id`),
  CONSTRAINT `FK43hkplanpixalxg862eq17geb` FOREIGN KEY (`config_score_id`) REFERENCES `config_score` (`id`),
  CONSTRAINT `FKfys4252naxblhuvtodx0ggw4j` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`),
  CONSTRAINT `FKiuy8yyhpqpr52bk049yrvxrxr` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_class`
--

LOCK TABLES `subject_class` WRITE;
/*!40000 ALTER TABLE `subject_class` DISABLE KEYS */;
INSERT INTO `subject_class` VALUES (1,3,'Thầy Hùng',1,1,1,'Nhóm 1'),(2,3,'Thầy Hùng',1,1,1,'Nhóm 2'),(3,2,'Cô Thuỷ',2,1,4,'Nhóm 1');
/*!40000 ALTER TABLE `subject_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `major_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj8ce5cjkm11igsffixdxexrr9` (`course_id`),
  KEY `FKc1q9juawhjvqie7wi3p44me9y` (`major_id`),
  CONSTRAINT `FKc1q9juawhjvqie7wi3p44me9y` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`),
  CONSTRAINT `FKj8ce5cjkm11igsffixdxexrr9` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'kaka','B17DCCN1','1999-08-08','haicarry@gmail.com','Hải Phạm',1,1),(2,'xx','B17DCAT2','1999-02-12','99@gmail.com','Khánh Nguyễn',1,2),(3,'Hà Đông ','B17DCCN3','1999-12-02','abcx@123','Đỗ Q',1,1),(4,'Vĩnh Phúc','B17DCAT4','1999-12-09','az@gmail.com','Đỗ tuấn',1,2),(5,'vv','B17DCPT5','1998-11-11','anhcz@gmail.com','Tintin',1,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_in_class`
--

DROP TABLE IF EXISTS `user_in_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_in_class` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `subject_class_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7x5ndd8gsr8rtwihlajn0vpfg` (`subject_class_id`),
  KEY `FKknh6cowc2mkb6765lvvc5by7` (`user_id`),
  CONSTRAINT `FK7x5ndd8gsr8rtwihlajn0vpfg` FOREIGN KEY (`subject_class_id`) REFERENCES `subject_class` (`id`),
  CONSTRAINT `FKknh6cowc2mkb6765lvvc5by7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_in_class`
--

LOCK TABLES `user_in_class` WRITE;
/*!40000 ALTER TABLE `user_in_class` DISABLE KEYS */;
INSERT INTO `user_in_class` VALUES (1,1,1),(2,1,2),(3,1,3),(4,3,1),(5,3,2),(6,3,3),(7,3,4);
/*!40000 ALTER TABLE `user_in_class` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-02 14:36:48
