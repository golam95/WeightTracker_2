-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 27, 2018 at 12:34 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tracker`
--

-- --------------------------------------------------------

--
-- Table structure for table `calorie_burn`
--

DROP TABLE IF EXISTS `calorie_burn`;
CREATE TABLE IF NOT EXISTS `calorie_burn` (
  `calorie_id` int(11) NOT NULL AUTO_INCREMENT,
  `calorie_intake` varchar(100) NOT NULL,
  `calorie_burn` varchar(100) NOT NULL,
  `calorie_difference` varchar(100) NOT NULL,
  `calorie_date` varchar(100) NOT NULL,
  `calorie_username` varchar(100) NOT NULL,
  PRIMARY KEY (`calorie_id`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `calorie_burn`
--

INSERT INTO `calorie_burn` (`calorie_id`, `calorie_intake`, `calorie_burn`, `calorie_difference`, `calorie_date`, `calorie_username`) VALUES
(35, '230.0', '121.89', '-108.11', '13-01-2018', 'Naim'),
(36, '30.0', '115.24', '85.24', '12-01-2018', 'mehedi'),
(34, '30.0', '182.06', '152.06', '12-01-2018', 'Naim');

-- --------------------------------------------------------

--
-- Table structure for table `daily_change`
--

DROP TABLE IF EXISTS `daily_change`;
CREATE TABLE IF NOT EXISTS `daily_change` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `change_amount` varchar(100) NOT NULL,
  `change_date` varchar(100) NOT NULL,
  `change_username` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daily_change`
--

INSERT INTO `daily_change` (`id`, `change_amount`, `change_date`, `change_username`) VALUES
(56, '2.0', '14-01-2018', 'mehedi'),
(55, '2.0', '13-01-2018', 'mehedi'),
(54, '0.0', '12-01-2018', 'mehedi'),
(53, '2.0', '14-01-2018', 'Naim'),
(52, '2.0', '13-01-2018', 'Naim'),
(51, '0.0', '12-01-2018', 'Naim');

-- --------------------------------------------------------

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
CREATE TABLE IF NOT EXISTS `exercise` (
  `exercise_id` int(11) NOT NULL AUTO_INCREMENT,
  `exercise_name` varchar(100) NOT NULL,
  `exercise_calories` varchar(100) NOT NULL,
  `exercise_date` varchar(100) NOT NULL,
  `exercise_username` varchar(100) NOT NULL,
  PRIMARY KEY (`exercise_id`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exercise`
--

INSERT INTO `exercise` (`exercise_id`, `exercise_name`, `exercise_calories`, `exercise_date`, `exercise_username`) VALUES
(37, 'Bicycling,Push Up', '115.24', '12-01-2018', 'mehedi'),
(36, 'Bicycling,Dancing,', '121.89', '13-01-2018', 'Naim'),
(35, 'Cooking,Push Up,', '182.06', '12-01-2018', 'Naim');

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
CREATE TABLE IF NOT EXISTS `food` (
  `food_id` int(11) NOT NULL AUTO_INCREMENT,
  `food_name` varchar(100) NOT NULL,
  `food_gram` varchar(100) NOT NULL,
  `food_date` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `food_calorie` varchar(100) NOT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`food_id`, `food_name`, `food_gram`, `food_date`, `username`, `food_calorie`) VALUES
(39, 'milk,rice', '7.500', '12-01-2018', 'mehedi', '30.0'),
(40, 'milk,rice,', '7.500', '26-01-2018', 'golam', '30.0'),
(38, 'milk,rice,', '57.500', '13-01-2018', 'Naim', '230.0'),
(37, 'milk,rice,', '7.500', '12-01-2018', 'Naim', '30.0');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(100) NOT NULL,
  `pcatagory` varchar(100) NOT NULL,
  `pdescription` varchar(100) NOT NULL,
  `price` varchar(100) NOT NULL,
  `pcondition` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `stock` varchar(100) NOT NULL,
  `manu` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `pname`, `pcatagory`, `pdescription`, `price`, `pcondition`, `status`, `stock`, `manu`) VALUES
(1, 's', 'd', 'g', 'h', 'j', 'k', 'l', 'j');

-- --------------------------------------------------------

--
-- Table structure for table `storefood`
--

DROP TABLE IF EXISTS `storefood`;
CREATE TABLE IF NOT EXISTS `storefood` (
  `foodvirtual_id` int(11) NOT NULL AUTO_INCREMENT,
  `foodvirtual_name` varchar(100) NOT NULL,
  `foodvirtual_cal` varchar(100) NOT NULL,
  `foodvirtual_username` varchar(100) NOT NULL,
  PRIMARY KEY (`foodvirtual_id`)
) ENGINE=MyISAM AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `storefood`
--

INSERT INTO `storefood` (`foodvirtual_id`, `foodvirtual_name`, `foodvirtual_cal`, `foodvirtual_username`) VALUES
(71, 'rice', '10', 'golam'),
(70, 'rice', '10', 'mehedi'),
(69, 'milk', '20', 'Naim'),
(68, 'rice', '10', 'Naim');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(300) NOT NULL,
  `user_gender` varchar(50) NOT NULL,
  `user_age` varchar(50) NOT NULL,
  `uset_date` varchar(200) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `user_gender`, `user_age`, `uset_date`) VALUES
(57, 'Ali', 'Male', '23', '26-01-2018'),
(55, 'mehedi', 'Male', '22', '12-01-2018'),
(54, 'Golam', 'Male', '44', '2018/01/13'),
(56, 'Khan', 'Male', '23', '26-01-2018');

-- --------------------------------------------------------

--
-- Table structure for table `waist`
--

DROP TABLE IF EXISTS `waist`;
CREATE TABLE IF NOT EXISTS `waist` (
  `waist_id` int(11) NOT NULL AUTO_INCREMENT,
  `waist_morning` varchar(50) NOT NULL,
  `waist_evening` varchar(50) NOT NULL,
  `waist_average` varchar(50) NOT NULL,
  `waist_date` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`waist_id`)
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `waist`
--

INSERT INTO `waist` (`waist_id`, `waist_morning`, `waist_evening`, `waist_average`, `waist_date`, `username`) VALUES
(43, '33', '33', '33', '12-01-2018', 'mehedi');

-- --------------------------------------------------------

--
-- Table structure for table `weight`
--

DROP TABLE IF EXISTS `weight`;
CREATE TABLE IF NOT EXISTS `weight` (
  `weight_id` int(11) NOT NULL AUTO_INCREMENT,
  `weight_morning` varchar(40) DEFAULT NULL,
  `weight_evening` varchar(200) NOT NULL,
  `weight_average` varchar(200) NOT NULL,
  `weight_date` varchar(200) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`weight_id`)
) ENGINE=MyISAM AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `weight`
--

INSERT INTO `weight` (`weight_id`, `weight_morning`, `weight_evening`, `weight_average`, `weight_date`, `username`) VALUES
(62, '74', '74', '74', '14-01-2018', 'mehedi'),
(61, '73', '72', '72', '13-01-2018', 'mehedi'),
(60, '70', '70', '70', '12-01-2018', 'mehedi');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
