-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.7.17-log - MySQL Community Server (GPL)
-- Операционная система:         Win64
-- HeidiSQL Версия:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных mydb
CREATE DATABASE IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET cp1251 */;
USE `mydb`;

-- Дамп структуры для таблица mydb.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT '',
  `lastName` varchar(45) DEFAULT '',
  `salary` float DEFAULT '0',
  `age` int(11) DEFAULT '0',
  `dateOfBirth` date NOT NULL DEFAULT '1970-01-01',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=cp1251;

-- Дамп данных таблицы mydb.user: ~9 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `firstName`, `lastName`, `salary`, `age`, `dateOfBirth`) VALUES
	(1, 'Mihail', 'Dzigovskii', 1000, 27, '1989-12-08'),
	(2, 'Alexei', 'Rozhkov', 1000, 28, '1988-04-11'),
	(3, 'Kate', 'Guz', 1500, 0, '1970-01-01'),
	(4, 'Pups', 'Anton', 50, 19, '1970-01-01'),
	(5, 'Salt', 'Andru', 500, 29, '1987-08-25'),
	(6, 'Anatolii', '', 2000, 0, '1970-01-01'),
	(7, 'Alexander', 'Pervii', 10, 47, '1970-01-01'),
	(8, '1-2', '1', 1, 1, '2017-03-02'),
	(9, '2', '2', 2, 2, '2016-12-13'),
	(10, '3', '3', 3, 3, '2017-03-16');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
