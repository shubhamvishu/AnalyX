-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: galleria
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buy`
--

DROP TABLE IF EXISTS `buy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `buy` (
  `cid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `dop` date NOT NULL,
  `qty` int(11) NOT NULL,
  KEY `cid` (`cid`),
  KEY `pid` (`pid`),
  KEY `sid` (`sid`),
  CONSTRAINT `buy_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `customer` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `buy_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `buy_ibfk_3` FOREIGN KEY (`sid`) REFERENCES `shop` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy`
--

LOCK TABLES `buy` WRITE;
/*!40000 ALTER TABLE `buy` DISABLE KEYS */;
INSERT INTO `buy` VALUES (1,3,4,'2018-09-07',10),(3,5,5,'2017-08-13',1),(2,5,5,'2017-05-10',5),(5,6,5,'2017-10-14',4),(10,16,5,'2017-12-30',4),(2,11,10,'2018-06-15',3),(1,15,12,'2018-07-28',1),(11,2,1,'2018-01-01',2),(11,3,6,'2018-02-20',4),(10,6,8,'2018-01-30',2),(10,6,9,'2018-03-15',5),(12,6,9,'2018-04-25',3),(13,11,10,'2018-09-21',10),(1,4,4,'2017-04-21',15),(5,1,2,'2018-05-20',5),(20,6,7,'2017-12-12',2),(10,14,13,'2018-06-06',2),(2,7,7,'2017-10-25',7),(6,11,10,'2018-11-07',4),(10,5,3,'2018-05-28',2);
/*!40000 ALTER TABLE `buy` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `t1` BEFORE INSERT ON `buy` FOR EACH ROW if new.dop>curdate() then insert into buy values(new.cid,new.pid,new.sid,new.dop,new.qty);end if */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `t2` BEFORE INSERT ON `buy` FOR EACH ROW if new.qty<=0 then insert into buy values(new.cid,new.pid,new.sid,new.dop,new.qty);end if */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `catid` int(11) NOT NULL,
  `catname` varchar(20) NOT NULL,
  `desp` varchar(60) NOT NULL DEFAULT 'A product category',
  PRIMARY KEY (`catid`),
  UNIQUE KEY `catname` (`catname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Food','All food and beverages'),(2,'Clothes','Every kind of wear except the foorwears'),(3,'Jewelleries','Gold,diamond etc precious jewels'),(4,'Footwear','Shoes,sandals etc'),(5,'Furniture','Sofa,bed,chairs etc'),(6,'Stationary','Pen,pencil,notebooks and other stuff'),(7,'Electronic','Gagets like mobile phone,laptop,AC etc'),(8,'Wooden art & craft','Beautiful artistic wooden show pieces');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `cid` int(11) NOT NULL,
  `cname` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `phno` varchar(14) NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phno` (`phno`),
  UNIQUE KEY `phno_2` (`phno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'shubham','shubh@gmail.com','128987972'),(2,'Vishal','vishalshubham123@gmail.com','8676576437'),(3,'Rohan','abcd1122@gmail.com','8990850437'),(4,'Kapil','kapilchahal@gmail.com','54321345'),(5,'Rohit','xyz@gmail.com','9765467777'),(6,'shubh','shubh123@gmail.com','898989732'),(10,'Ayush','ayush.b.1998@gmail.com','9740671580'),(11,'suhas','suhaskamath@gmail.com','992553268'),(12,'Madhu','madhu123@gmail.com','9880066723'),(13,'Vinay','vinaysharma@gmail.com','9822126363'),(14,'Rahul','rahulverma@gmail.com','7896781093'),(18,'abcd','abcd@gmail.com','788900987'),(19,'Shubham','shubh13@gmail.com','8978623827'),(20,'sudesh','bala@gmail.com','999121228');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `eid` int(11) NOT NULL,
  `ename` varchar(20) NOT NULL,
  `phno` varchar(14) NOT NULL,
  `address` varchar(50) NOT NULL,
  `sal` int(11) NOT NULL DEFAULT '0',
  `up` int(11) NOT NULL DEFAULT '0',
  `down` int(11) NOT NULL DEFAULT '0',
  `sid` int(11) DEFAULT NULL,
  `doj` date NOT NULL,
  PRIMARY KEY (`eid`),
  UNIQUE KEY `phno` (`phno`),
  UNIQUE KEY `phno_2` (`phno`),
  KEY `sid` (`sid`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `shop` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Suraj','9876736','Bangalore',67000,45,20,6,'2018-09-09'),(2,'Pratap','98834736','Marathahalli',45000,182,70,1,'2018-09-09'),(3,'Ranjan','988388','Jayanagar',30000,56,80,5,'2018-09-09'),(4,'Amit','9778388','JPnagar',32000,50,12,4,'2012-04-15'),(5,'Pooja','9198388','Hebbal',56000,70,18,4,'2016-10-08'),(6,'Kiran','9008388','whitefield',50000,110,40,3,'2014-07-09'),(7,'Shirshti','7108388','Domlur',28000,70,140,3,'2016-10-08'),(8,'Shivraj','7908352','Jalahalli',40000,130,200,2,'2016-10-08'),(9,'Sonish','8808352','Yehlanka',65000,505,90,12,'2017-11-13'),(10,'Nikita','8990502','Yehlanka',65000,35,10,10,'2018-10-01'),(11,'Rohan','7221591','Kundanahalli',65000,35,10,9,'2018-10-01'),(12,'Rohit','9441591','KRPuram',90000,265,68,7,'2012-12-12'),(13,'Kushal','911278','Koramangala',34000,20,6,6,'2016-08-13'),(14,'Preeti','711244','Majestic',18000,40,38,3,'2015-05-24'),(15,'Neha','85611244','Indiranagar',56000,90,118,8,'2015-12-30'),(16,'Meena','791244','Indiranagar',23000,30,28,11,'2018-01-10'),(17,'Vinay','792229','Marathahalli',78000,80,14,4,'2015-11-04'),(18,'Kapil','8802139','Majestic',47000,20,30,1,'2013-06-06'),(19,'Akshay','887719','Hebbal',29500,20,50,7,'2018-01-10'),(20,'Nikhil','997719','Hebbal',89500,102,10,5,'2015-05-24'),(21,'Satyam','7876832','HSR',62100,73,65,4,'2015-05-24'),(23,'abcd','43433','bnmklkj',89050,67,12,3,'2018-11-19');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `t3` BEFORE INSERT ON `employee` FOR EACH ROW if new.doj>curdate() then insert into employee values(new.eid,new.ename,new.phno,new.address,new.sal,new.up,new.down,new.sid,new.doj);end if */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `t4` BEFORE INSERT ON `employee` FOR EACH ROW if new.sal<=0 then insert into employee values(new.eid,new.ename,new.phno,new.address,new.sal,new.up,new.down,new.sid,new.doj);end if */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `owner`
--

DROP TABLE IF EXISTS `owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `owner` (
  `oid` int(11) NOT NULL,
  `oname` varchar(20) NOT NULL,
  `phno` varchar(14) NOT NULL,
  `address` varchar(30) NOT NULL,
  PRIMARY KEY (`oid`),
  UNIQUE KEY `phno` (`phno`),
  UNIQUE KEY `phno_2` (`phno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner`
--

LOCK TABLES `owner` WRITE;
/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
INSERT INTO `owner` VALUES (1,'Shivani','9066346','HSR'),(2,'Manish','9466346','Marathahalli'),(3,'Vikram','8816346','Marathahalli'),(4,'Vikrant','79516346','Jayanagar'),(5,'Siddhant','9951800','Majestic'),(6,'Vishal','977090','Hebbal'),(7,'Suman','707090','Indiranagar'),(8,'Mahir','907090','Kormangala'),(9,'Mohit','807022','Kormangala'),(10,'Manak','922122','Whitefield'),(11,'Karan','88821833','MG Road');
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ownshop`
--

DROP TABLE IF EXISTS `ownshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ownshop` (
  `oid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  KEY `oid` (`oid`),
  KEY `sid` (`sid`),
  CONSTRAINT `ownshop_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `owner` (`oid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ownshop_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `shop` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ownshop`
--

LOCK TABLES `ownshop` WRITE;
/*!40000 ALTER TABLE `ownshop` DISABLE KEYS */;
INSERT INTO `ownshop` VALUES (2,7),(3,9),(2,8),(6,4),(4,4),(4,9),(1,8),(3,12),(3,6),(6,5),(6,1),(10,1),(8,11),(9,2),(6,10),(10,10),(3,7),(7,9),(5,13),(10,12);
/*!40000 ALTER TABLE `ownshop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `pid` int(11) NOT NULL,
  `pname` varchar(20) NOT NULL,
  `pcost` int(11) NOT NULL,
  `profit` int(11) NOT NULL,
  `catid` int(11) NOT NULL,
  PRIMARY KEY (`pid`),
  KEY `catid` (`catid`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`catid`) REFERENCES `category` (`catid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Shoes',1200,280,4),(2,'Colddrink',70,15,1),(3,'Burger',55,10,1),(4,'Coffee',98,60,1),(5,'Slippers',320,128,4),(6,'T-shirt',580,300,2),(7,'Jeans',1150,475,2),(8,'Sofa',25000,6000,5),(9,'Necklace',50000,5500,3),(10,'Earring',9700,1250,3),(11,'Notebook',60,10,6),(12,'Pen',20,5,6),(13,'Phone',14000,3900,7),(14,'AC',30000,9000,7),(15,'Laptop',75000,6000,7),(16,'Bed',18000,3500,5),(17,'Table',1400,635,5),(18,'Dinning Table',5260,2770,5),(19,'Wooden pen holder',880,470,8),(20,'Wooden key holder',410,230,8);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shop` (
  `sid` int(11) NOT NULL,
  `sname` varchar(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `catid` int(11) NOT NULL,
  PRIMARY KEY (`sid`),
  KEY `catid` (`catid`),
  CONSTRAINT `shop_ibfk_1` FOREIGN KEY (`catid`) REFERENCES `category` (`catid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'ABC',500000,1),(2,'DEF',150000,4),(3,'GHI',300000,3),(4,'JKL',430000,7),(5,'MNO',250000,5),(6,'PQR',160000,1),(7,'STU',220000,2),(8,'VWX',190000,2),(9,'XYZ',315000,2),(10,'STA1',640000,6),(11,'STA2',1300000,6),(12,'ELE1',5250000,7),(13,'ELE2',3200000,7);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v1`
--

DROP TABLE IF EXISTS `v1`;
/*!50001 DROP VIEW IF EXISTS `v1`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v1` AS SELECT 
 1 AS `cname`,
 1 AS `pname`,
 1 AS `sname`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v2`
--

DROP TABLE IF EXISTS `v2`;
/*!50001 DROP VIEW IF EXISTS `v2`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v2` AS SELECT 
 1 AS `cid`,
 1 AS `cname`,
 1 AS `count(pid)`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v3`
--

DROP TABLE IF EXISTS `v3`;
/*!50001 DROP VIEW IF EXISTS `v3`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v3` AS SELECT 
 1 AS `cid`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v1`
--

/*!50001 DROP VIEW IF EXISTS `v1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v1` AS select `c`.`cname` AS `cname`,`p`.`pname` AS `pname`,`s`.`sname` AS `sname` from (((`customer` `c` join `product` `p`) join `shop` `s`) join `buy` `b`) where ((`b`.`cid` = `c`.`cid`) and (`b`.`pid` = `p`.`pid`) and (`b`.`sid` = `s`.`sid`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v2`
--

/*!50001 DROP VIEW IF EXISTS `v2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v2` AS select `b`.`cid` AS `cid`,`c`.`cname` AS `cname`,count(`b`.`pid`) AS `count(pid)` from (`buy` `b` join `customer` `c`) where (`b`.`cid` = `c`.`cid`) group by `b`.`cid` order by `b`.`cid` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v3`
--

/*!50001 DROP VIEW IF EXISTS `v3`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v3` AS select `buy`.`cid` AS `cid` from `buy` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-14 20:38:23
