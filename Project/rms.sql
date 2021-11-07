-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2021 at 02:03 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rms`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Admin_Id` int(200) NOT NULL,
  `Name` varchar(500) NOT NULL,
  `Email` varchar(500) NOT NULL,
  `LoginId` varchar(500) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `Address` varchar(500) NOT NULL,
  `ContactNumber` varchar(500) NOT NULL,
  `CNIC` varchar(500) NOT NULL,
  `Role` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Admin_Id`, `Name`, `Email`, `LoginId`, `Password`, `Address`, `ContactNumber`, `CNIC`, `Role`) VALUES
(1, 'Ayesha Fatima', 'ayeshafatima@gmail.com', 'ayesha.fatima@ce.uet', 'Ayesha', 'Lahore', '0321-0000000', '31111-3111111-1', 'Administrator');

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

CREATE TABLE `application` (
  `Opportunity_Id` int(200) NOT NULL,
  `Student_Id` int(200) NOT NULL,
  `CGPA` varchar(500) NOT NULL,
  `Degree` varchar(500) NOT NULL,
  `Field` varchar(500) NOT NULL,
  `Reason` varchar(5000) NOT NULL,
  `Status` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`Opportunity_Id`, `Student_Id`, `CGPA`, `Degree`, `Field`, `Reason`, `Status`) VALUES
(1, 1, '3.8', 'CE', 'CE', 'thyin', 'CE'),
(1, 1, '3.8', 'CE', 'CE', 'thyin', 'n');

-- --------------------------------------------------------

--
-- Table structure for table `closed_research`
--

CREATE TABLE `closed_research` (
  `Research_Id` int(200) NOT NULL,
  `Status` varchar(500) NOT NULL,
  `Title` varchar(500) NOT NULL,
  `Teacher_Id` int(200) NOT NULL,
  `Domain` varchar(500) NOT NULL,
  `Starting_Date` date NOT NULL,
  `Ending_Date` date NOT NULL,
  `Success_Level` varchar(500) NOT NULL,
  `Introduction` varchar(500) NOT NULL,
  `Abstract` varchar(5000) NOT NULL,
  `Conclusion` varchar(5000) NOT NULL,
  `Report` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `open_research`
--

CREATE TABLE `open_research` (
  `Research_Id` int(200) NOT NULL,
  `Title` varchar(500) NOT NULL,
  `Teacher_Id` int(200) NOT NULL,
  `Domain` varchar(500) NOT NULL,
  `Starting_Date` date NOT NULL,
  `About` varchar(5000) NOT NULL,
  `Progress` varchar(5000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `research_opportunities`
--

CREATE TABLE `research_opportunities` (
  `Application_id` int(200) NOT NULL,
  `Title` varchar(500) NOT NULL,
  `Teacher_Id` int(200) NOT NULL,
  `Domain` varchar(500) NOT NULL,
  `No of Student` varchar(500) NOT NULL,
  `Min_Edu` varchar(500) NOT NULL,
  `Last_date_to_apply` date NOT NULL,
  `Duration` varchar(500) NOT NULL,
  `Starting date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `research_tracker`
--

CREATE TABLE `research_tracker` (
  `Paper_Id` int(200) NOT NULL,
  `Student_Id` int(200) NOT NULL,
  `Title` varchar(500) NOT NULL,
  `Author` varchar(500) NOT NULL,
  `Domain` varchar(500) NOT NULL,
  `Abstract` varchar(5000) NOT NULL,
  `Notes` varchar(5000) NOT NULL,
  `Conclusion` varchar(600) NOT NULL,
  `Date_of_Publishing` date NOT NULL,
  `Reviewing_Date` timestamp(5) NOT NULL DEFAULT current_timestamp(5) ON UPDATE current_timestamp(5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `Reg_No` varchar(500) NOT NULL,
  `Degree` varchar(500) NOT NULL,
  `Class` varchar(500) NOT NULL,
  `Field` varchar(500) NOT NULL,
  `FatherName` varchar(500) NOT NULL,
  `CNIC` varchar(500) NOT NULL,
  `DOB` date NOT NULL,
  `ContactNumber` varchar(500) NOT NULL,
  `Email` varchar(500) NOT NULL,
  `LoginId` varchar(500) NOT NULL,
  `Password` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `student_research_closed`
--

CREATE TABLE `student_research_closed` (
  `Student_Id` int(200) NOT NULL,
  `Research_Id` int(200) NOT NULL,
  `Remarks` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `student_research_open`
--

CREATE TABLE `student_research_open` (
  `Student_Id` int(200) NOT NULL,
  `Research_Id` int(200) NOT NULL,
  `Progress` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `Teacher_Id` int(200) NOT NULL,
  `Department` varchar(200) NOT NULL,
  `Name` varchar(500) NOT NULL,
  `FatherName` varchar(500) NOT NULL,
  `CNIC` varchar(500) NOT NULL,
  `DOB` varchar(500) NOT NULL,
  `Weight_Qual` varchar(500) NOT NULL,
  `Majors` varchar(500) NOT NULL,
  `ContactNumber` varchar(500) NOT NULL,
  `Email` varchar(500) NOT NULL,
  `LoginId` varchar(500) NOT NULL,
  `Password` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `research_opportunities`
--
ALTER TABLE `research_opportunities`
  ADD PRIMARY KEY (`Application_id`);

--
-- Indexes for table `research_tracker`
--
ALTER TABLE `research_tracker`
  ADD PRIMARY KEY (`Paper_Id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`Reg_No`),
  ADD UNIQUE KEY `LoginId` (`LoginId`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`Teacher_Id`) USING BTREE,
  ADD UNIQUE KEY `LoginId` (`LoginId`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `research_opportunities`
--
ALTER TABLE `research_opportunities`
  MODIFY `Application_id` int(200) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `research_tracker`
--
ALTER TABLE `research_tracker`
  MODIFY `Paper_Id` int(200) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `Teacher_Id` int(200) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
