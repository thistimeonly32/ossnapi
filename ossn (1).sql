-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 18, 2019 at 04:01 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ossn`
--

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `to_user_id` bigint(20) NOT NULL,
  `from_user_id` bigint(20) NOT NULL,
  `message` text NOT NULL,
  `creation_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`),
  KEY `to_user_id_fk` (`to_user_id`),
  KEY `from_user_id_fk` (`from_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`message_id`, `to_user_id`, `from_user_id`, `message`, `creation_timestamp`) VALUES
(1, 2, 1, 'shivam message', '2019-05-27 10:04:35'),
(2, 1, 2, 'krishna message', '2019-05-27 10:04:35'),
(4, 2, 1, 'shivam 2nd message', '2019-05-27 10:04:35'),
(6, 2, 1, 'test messages', '2019-05-27 10:04:35'),
(8, 2, 1, 'test message again', '2019-05-27 10:24:17'),
(9, 2, 1, 'test message agn', '2019-05-27 13:11:10'),
(10, 2, 1, 'hey', '2019-05-27 13:19:56'),
(11, 1, 2, 'what happen bro', '2019-05-27 13:20:17'),
(12, 2, 1, 'test', '2019-05-27 13:38:39'),
(13, 2, 1, 'sdsdsd', '2019-05-27 13:39:34'),
(14, 2, 1, 'hey', '2019-05-27 13:40:18'),
(15, 1, 2, 'these all are testing messages.', '2019-05-27 13:40:37'),
(16, 2, 1, 'What are you doing.', '2019-06-12 09:52:19'),
(17, 2, 3, 'hey', '2019-06-12 10:49:45'),
(18, 3, 2, 'hey man', '2019-06-12 10:49:52'),
(19, 2, 3, 'df', '2019-06-12 10:50:28'),
(20, 2, 1, 'hey', '2019-06-12 14:33:09'),
(21, 2, 1, 'hey man what are you doing', '2019-06-14 10:22:17'),
(22, 1, 2, 'type back message', '2019-06-14 10:22:31');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `password`, `name`) VALUES
(1, 'krishna@abc.com', '123456', 'Krishna Joshi'),
(2, 'shivam@abc.com', '123456', 'Shivam Pal'),
(3, 'hridesh@abc.com', '123456', 'Hridesh');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `from_user_id_fk` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `to_user_id_fk` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
