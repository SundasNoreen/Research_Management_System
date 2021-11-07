-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 06, 2021 at 05:29 PM
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
  `Name` varchar(300) NOT NULL,
  `CNIC` varchar(500) NOT NULL,
  `Email` varchar(300) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `Address` varchar(500) NOT NULL,
  `Contact Number` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Name`, `CNIC`, `Email`, `Password`, `Address`, `Contact Number`) VALUES
('Ayesha Fatima', '31111-3111111-1', 'ayeshafatima@gmail.com', 'Ayesha', 'Lahore, Pakistan', '0300-0000000');

-- --------------------------------------------------------

--
-- Table structure for table `researchopportnities`
--

CREATE TABLE `researchopportnities` (
  `Field` varchar(300) NOT NULL,
  `No of students` int(30) NOT NULL,
  `Discipline of students` varchar(300) NOT NULL,
  `Degree` varchar(300) NOT NULL,
  `Cgpa` varchar(255) NOT NULL,
  `Description` varchar(5000) NOT NULL,
  `Last to apply` date NOT NULL,
  `Duration` varchar(300) NOT NULL,
  `Starting Date` date NOT NULL,
  `Teacher id` varchar(500) NOT NULL,
  `Reasearch id` int(11) NOT NULL,
  `Headings` varchar(500) NOT NULL,
  `Category` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `researchopportnities`
--

INSERT INTO `researchopportnities` (`Field`, `No of students`, `Discipline of students`, `Degree`, `Cgpa`, `Description`, `Last to apply`, `Duration`, `Starting Date`, `Teacher id`, `Reasearch id`, `Headings`, `Category`) VALUES
('Artificial Intelligence', 3, 'Computer Science, Computer Engineering, Electrical Engineering, Software Engineering, Information Technology ', 'Bachelors', '3.3', 'Artificial intelligence (AI), machine learning (ML), and Digital Twins (DT) have the potential to transform cardiology. We are seeking to appoint research students to develop and apply state of the art AI methods to benefit patient outcomes at St Thomas’ Hospital and other major centers for cardiovascular disease. This project will develop and apply state-of-the-art machine learning methods to automatically analyze longitudinal patient data that will be encoded in a digital twin of the patient’s heart, in order to provide doctors with detailed information on the trajectory of heart disease. ', '2021-11-10', '3 Months', '2021-12-01', '10', 1, 'Digital Twins', 'Computer Sciences'),
('Machine Learning, Artificial Intelligence', 2, 'Computer Sciences, Computer Engineering, Software Engineering, Electrical Engineering, Information Technology', 'Masters', '3.0 or Above', 'We are looking for talented students for a new grant looking to develop novel radiological support tools which significantly improve the precision with which brain abnormalities may be detected in individual brains. \r\nThe project will involve an extension of MSM (Multimodal Surface Matching) – a tool for brain image registration that has already greatly improved understanding of brain organization and development (e.g. Glasser MF Nature 2016, Garcia K PNAS 2018) - in a way that will more explicitly account for individual variability in brain shape and organization. This will support much more accurate comparisons of brain shape, function, and microstructure across large groups of individuals.\r\n', '2021-11-07', '4 Months', '2022-01-01', '3', 2, 'Image Registration', 'Computer Sciences'),
('Networking, Telecommunications', 3, 'Electrical Engineering, Networking Engineering, Telecommnications, Computer Engineering ', 'Bachelors', '3.0 or Above', 'The project is central to KubeNet\'s ambitious growth plans and transition from a small regional telecoms (network and technology) business to a Carrier-Grade network provider capable of delivering value-added managed services across their own high performance \"intelligent\" network, be that current 1/10GB or 100GB and Dark Fibre networks of the future.\r\n\r\nStudents will embed intelligence within the KubeNet and enhance network resilience and establish a platform for accelerated growth (scalability). The project will also underpin the growth of KubeNet\'s managed services offering; providing a one-stop-shop for clients and building genuine competitive advantage in terms of network performance, real-time client reporting, and new value-added managed services whilst continuing to leverage the personal service client experience on which the business has built its reputation. Crucially, the project will also provide KubeNet with the tools, knowledge, and expertise to move up the value chain and deliver increasingly high-profile projects for corporates and public sector clients; significantly enhancing business profitability.', '2021-11-11', '5 Months', '2022-01-15', '4', 3, 'Network Design', 'Engineering'),
('Engineering, Research, Computer Sciences', 2, 'Computer Science, Electrical Engineering, Computer Engineering', 'Masters', '3.3 or Above', 'Students will be working on software technologies for qualitatively and quantitatively analyzing data for prototyping and validating sensor-based assessments of passengers’ preferences and improving the passengers’ inflight experience. You will work closely with hardware engineers and project managers throughout the software lifecycle in successfully monitoring and collecting data from sensors in a cabin stimulator.', '2021-10-29', '2 Months', '2021-12-01', '4', 4, 'Digital Aviation Corporate', 'Engineering');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `regid` int(11) NOT NULL,
  `approval` text NOT NULL,
  `Name` varchar(500) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `Email` varchar(300) NOT NULL,
  `FatherName` varchar(500) NOT NULL,
  `CNIC` varchar(13) NOT NULL,
  `DOB` varchar(20) NOT NULL,
  `Contact` varchar(15) NOT NULL,
  `CityRes` varchar(300) NOT NULL,
  `HeighestEdu` varchar(500) NOT NULL,
  `Ongoingedu` varchar(300) NOT NULL,
  `Field` varchar(500) NOT NULL,
  `institute` varchar(300) NOT NULL,
  `Citylns` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`regid`, `approval`, `Name`, `Password`, `Email`, `FatherName`, `CNIC`, `DOB`, `Contact`, `CityRes`, `HeighestEdu`, `Ongoingedu`, `Field`, `institute`, `Citylns`) VALUES
(1, 'Yes', 'Aqsa Ayaz', 'Aqsa', 'aqsaayazrao@gmail.com', 'Ayaz Rao', '31111-3112345', '2002-01-04', '0300-111111', 'Multan', 'Intermediate', 'Bachelors', 'Engineering', 'UET Lahore', 'Lahore'),
(41, 'No', 'Sundas Noreen', 'nnn', 'noreensundas@gmail.com', 'Waheed', '3110213418574', '2010-12-28', '+923219559545', 'Multan', '', '', '', '', ''),
(42, 'No', 'Sundas Noreen', 'bbb', 'nmn@gmail.com', 'Waheed', '3110213418574', '2010-12-30', '+923219559545', 'Multan', '2', '3', '4', 'UET LAhore', 'Chitral'),
(43, 'No', 'Sundas Noreen', '1234', 'derr@gmail.com', 'Waheed', '3110213418574', '2010-12-24', '+923219559545', 'Multan', '2', '3', '5', 'UET LAhore', 'Chaman');

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `regid` int(11) NOT NULL,
  `approval` text NOT NULL,
  `Name` varchar(500) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `Email` varchar(300) NOT NULL,
  `FatherName` varchar(500) NOT NULL,
  `CNIC` varchar(13) NOT NULL,
  `DOB` varchar(20) NOT NULL,
  `Contact` varchar(15) NOT NULL,
  `CityRes` varchar(300) NOT NULL,
  `HeighestEdu` varchar(500) NOT NULL,
  `Ongoingedu` varchar(300) NOT NULL,
  `Field` varchar(500) NOT NULL,
  `institute` varchar(300) NOT NULL,
  `Citylns` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`regid`, `approval`, `Name`, `Password`, `Email`, `FatherName`, `CNIC`, `DOB`, `Contact`, `CityRes`, `HeighestEdu`, `Ongoingedu`, `Field`, `institute`, `Citylns`) VALUES
(4, 'No', 'Sundas Noreen', 'bbb', 'noreensundas@gmail.com', 'Waheed', '3110213418574', '2010-12-28', '+923219559545', 'Multan', '3', '', '2', 'UET LAhore', 'Charsadda'),
(6, 'No', 'Sundas Noreen', '123', '1234@gmail.com', 'Waheed', '3110213418574', '2010-12-30', '+923219559545', 'Multan', '4', '', '2', 'UET LAhore', 'Chaman'),
(7, 'No', 'Sundas Noreen', '12345', '123456@gmail.com', 'Waheed', '566788', '2010-12-06', '+923219559545', 'Malakand', '2', '', '5', 'UET LAhore', 'Lahore');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Email`);

--
-- Indexes for table `researchopportnities`
--
ALTER TABLE `researchopportnities`
  ADD PRIMARY KEY (`Reasearch id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD UNIQUE KEY `regid` (`regid`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD UNIQUE KEY `regid` (`regid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `researchopportnities`
--
ALTER TABLE `researchopportnities`
  MODIFY `Reasearch id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `regid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `regid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
