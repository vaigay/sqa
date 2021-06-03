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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=364 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_score`
--

LOCK TABLES `config_score` WRITE;
/*!40000 ALTER TABLE `config_score` DISABLE KEYS */;
INSERT INTO `config_score` VALUES (1),(2),(11),(12),(13),(14),(15),(16),(17),(18);
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
  `config_socre_id` bigint DEFAULT NULL,
  `percent` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh4smt9v65nujv8yyvtx641t2u` (`config_socre_id`),
  CONSTRAINT `FKh4smt9v65nujv8yyvtx641t2u` FOREIGN KEY (`config_socre_id`) REFERENCES `config_score` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1258 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_score_detail`
--

LOCK TABLES `config_score_detail` WRITE;
/*!40000 ALTER TABLE `config_score_detail` DISABLE KEYS */;
INSERT INTO `config_score_detail` VALUES (1,'attendance',1,10),(2,'exercise',1,20),(3,'practice',1,20),(4,'examFinal',1,50),(5,'attendance',2,10),(6,'exercise',2,30),(7,'examFinal',2,60),(26,'attendance',11,10),(27,'practice',11,10),(28,'test',11,20),(29,'examFinal',11,60),(30,'attendance',12,10),(31,'exercise',12,20),(32,'practice',12,10),(33,'test',12,10),(34,'examFinal',12,50),(35,'attendance',13,10),(36,'exercise',13,20),(37,'practice',13,20),(38,'examFinal',13,50),(39,'attendance',14,10),(40,'practice',14,10),(41,'test',14,10),(42,'examFinal',14,70),(43,'attendance',15,10),(44,'practice',15,10),(45,'examFinal',15,80),(46,'attendance',16,10),(47,'exercise',16,20),(48,'practice',16,20),(49,'examFinal',16,50),(50,'attendance',17,10),(51,'exercise',17,20),(52,'practice',17,20),(53,'examFinal',17,50),(54,'attendance',18,10),(55,'exercise',18,20),(56,'practice',18,20),(57,'examFinal',18,50);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'D17'),(2,'D18'),(3,'D19');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,'Công nghệ thông tin','DCCN'),(2,'An toàn thông tin','DCAT'),(3,'Công nghệ đa phương tiện','DCPT'),(4,'Marketing','MAR'),(5,'Viễn Thông','DCVT');
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
  `config_score_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn4xqskxpcf27s3uvux25lhus` (`config_score_id`),
  CONSTRAINT `FKn4xqskxpcf27s3uvux25lhus` FOREIGN KEY (`config_score_id`) REFERENCES `config_score` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'INT1234',3,'Lập trình mạng',18),(2,'INT1200',3,'Lập trình Web',11),(3,'INT12341',2,'Cơ sở dữ liệu phân tán',15),(4,'INT12341',2,'Quản lý dự án phần mềm',2),(5,'INT1299',3,'An toàn bảo mật thông tin',NULL);
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
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj8ce5cjkm11igsffixdxexrr9` (`course_id`),
  KEY `FKc1q9juawhjvqie7wi3p44me9y` (`major_id`),
  CONSTRAINT `FKc1q9juawhjvqie7wi3p44me9y` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`),
  CONSTRAINT `FKj8ce5cjkm11igsffixdxexrr9` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'kaka','B18DCPT1','1999-08-08','haicarry@gmail.com','Hải Phạm',2,3,0),(2,'exxcc','B19DCVT2','1999-02-12','99@gmail.com','Khánh Nguyễn',3,5,0),(3,'Hà Đông ','B17DCCN3','1999-12-02','abcx@123','Đỗ Q',1,1,0),(4,'Vĩnh Phúc','B17DCCN4','1999-12-09','az@gmail.com','Đỗ tuấn',1,1,0),(5,'vvxx','B17DCPT5','1998-11-11','anhcz@gmail.com','Tintin',1,3,0),(9,'xxvvv','B18DCVT9','1998-11-04','avc@123.com','Serah',2,5,0),(10,'summor','B18DCPT10','1998-11-08','kiki@g.com','riki',2,3,0),(11,'viva','B18DCAT11','1999-08-09','2@123.com','virus',2,2,0),(12,'ff13','B18DCAT12','1997-01-01','de@snow.com','snow',2,2,1),(13,'bb','B17DCAT13','1993-12-03','8899@gmail.com','Nguyễn Văn K',1,2,1),(14,'sdf','B17DCCN14','1119-11-11','haicarry@gmail.com','a',1,1,1),(15,'sdf','B17DCCN15','2020-10-20','haicarry@gmail.com','a',1,1,1),(16,'sdf','B18DCAT16','2019-02-13','haicarry@gmail.com','hai',2,2,1),(17,'sdf','B17DCCN17','2020-02-04','haicarry@gmail.com','hai',1,1,1),(18,'sdf','B17DCCN18','2020-07-01','haicarry@gmail.com','hai',1,1,1),(19,'123','B17DCPT19','2020-10-30','123@123.123','123',1,3,1),(20,'sdf','B17DCCN20','2019-10-16','haicarry@gmail.com','hai',1,1,1),(102,'x','B17DCCN102','1990-11-11','x@123.xtz','a',1,1,1),(103,'xa','B17DCAT103','1998-01-01','123@123.123','123',1,2,0),(104,'gg','B17DCPT104','1889-02-03','gng@gmail.com','g',1,3,1),(105,'okokok','B17DCPT105','1997-04-03','wqewq@123.qwe','ok',1,3,0),(106,'123x','B18MAR106','1885-11-13','avc@123.123','testLast',2,4,1),(107,'123','B18MAR107','1990-12-31','123@123.123','xasd',2,4,1);
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

-- Dump completed on 2021-06-03 20:06:42
