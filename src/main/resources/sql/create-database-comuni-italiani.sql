CREATE DATABASE  IF NOT EXISTS `database_comuni` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `database_comuni`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: database_comuni
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `italy_cap`
--

DROP TABLE IF EXISTS `italy_cap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `italy_cap` (
  `istat` int(11) NOT NULL,
  `cap` char(11) DEFAULT NULL,
  PRIMARY KEY (`istat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `italy_cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `italy_cities` (
  `istat` int(11) NOT NULL,
  `comune` varchar(255) DEFAULT NULL,
  `regione` varchar(50) DEFAULT NULL,
  `provincia` varchar(2) DEFAULT NULL,
  `prefisso` varchar(7) CHARACTER SET utf8 DEFAULT NULL,
  `cod_fisco` varchar(10) DEFAULT NULL,
  `superficie` double DEFAULT NULL,
  `num_residenti` int(11) DEFAULT NULL,
  PRIMARY KEY (`istat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `italy_provincies`
--

DROP TABLE IF EXISTS `italy_provincies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `italy_provincies` (
  `sigla` varchar(2) NOT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `superficie` double DEFAULT NULL,
  `residenti` int(11) DEFAULT NULL,
  `num_comuni` int(11) DEFAULT NULL,
  `id_regione` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`sigla`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `italy_provincies`
--

/*!40000 ALTER TABLE `italy_provincies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `italy_regions`
--

DROP TABLE IF EXISTS `italy_regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `italy_regions` (
  `id_regione` smallint(6) NOT NULL,
  `regione` varchar(50) DEFAULT NULL,
  `superficie` double DEFAULT NULL,
  `num_residenti` int(11) DEFAULT NULL,
  `num_comuni` int(11) DEFAULT NULL,
  `num_provincie` int(11) DEFAULT NULL,
  `presidente` varchar(45) DEFAULT NULL,
  `cod_istat` varchar(2) DEFAULT NULL,
  `cod_fiscale` varchar(11) DEFAULT NULL,
  `piva` varchar(11) DEFAULT NULL,
  `pec` varchar(100) DEFAULT NULL,
  `sito` varchar(100) DEFAULT NULL,
  `sede` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_regione`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `italy_regions`
--

/*!40000 ALTER TABLE `italy_regions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `italy_removed`
--

DROP TABLE IF EXISTS `italy_removed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `italy_removed` (
  `istat` int(11) NOT NULL,
  `comune` varchar(255) DEFAULT NULL,
  `provincia` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`istat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `italy_removed`
--


DROP TABLE IF EXISTS `italy_sardegna_2017`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `italy_sardegna_2017` (
  `istat_old` int(11) NOT NULL,
  `istat_new` int(11) DEFAULT NULL,
  `comune` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`istat_old`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `italy_sardegna_2017`
--

/*!40000 ALTER TABLE `italy_sardegna_2017` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `view_all`
--

DROP TABLE IF EXISTS `view_all`;
/*!50001 DROP VIEW IF EXISTS `view_all`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_all` AS SELECT 
 1 AS `istat`,
 1 AS `comune`,
 1 AS `cap`,
 1 AS `regione`,
 1 AS `provincia`,
 1 AS `prefisso`,
 1 AS `cod_fisco`,
 1 AS `superficie`,
 1 AS `num_residenti`,
 1 AS `lng`,
 1 AS `lat`,
 1 AS `abitanti`,
 1 AS `patrono_nome`,
 1 AS `patrono_data`,
 1 AS `indirizzo`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `view_all`
--

/*!50001 DROP VIEW IF EXISTS `view_all`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_all` AS select `italy_cap`.`istat` AS `istat`,`italy_cities`.`comune` AS `comune`,`italy_cap`.`cap` AS `cap`,`italy_cities`.`regione` AS `regione`,`italy_cities`.`provincia` AS `provincia`,`italy_cities`.`prefisso` AS `prefisso`,`italy_cities`.`cod_fisco` AS `cod_fisco`,`italy_cities`.`superficie` AS `superficie`,`italy_cities`.`num_residenti` AS `num_residenti`,`italy_geo`.`lng` AS `lng`,`italy_geo`.`lat` AS `lat`,`italy_info`.`abitanti` AS `abitanti`,`italy_info`.`patrono_nome` AS `patrono_nome`,`italy_info`.`patrono_data` AS `patrono_data`,`italy_munic`.`indirizzo` AS `indirizzo` from ((((`italy_cap` left join `italy_cities` on((`italy_cities`.`istat` = `italy_cap`.`istat`))) left join `italy_geo` on((`italy_geo`.`istat` = `italy_cap`.`istat`))) left join `italy_info` on((`italy_info`.`istat` = `italy_cap`.`istat`))) left join `italy_munic` on((`italy_munic`.`istat` = `italy_cap`.`istat`))) limit 999999 */;
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

-- Dump completed on 2018-05-14 14:21:35
