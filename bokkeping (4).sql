-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2023 at 08:29 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bokkeping`
--

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` varchar(6) NOT NULL,
  `nama_pengguna` varchar(30) NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_pengguna`, `no_telp`, `alamat`, `username`, `password`) VALUES
('US02', 'Meta Nur', '085895769832', 'Gondang', 'Arfiya', '*317565FA013E855E75CCD03259F55E4F68586F9C'),
('US03', 'Annisa', '088231146944', 'Nganjuk', 'A', '*23AE809DDACAF96AF0FD78ED04B6A265E05AA257'),
('US04', 'amell', '085732308707', 'Tanjunganom', 'amell', '*BB6BED17A61350A661395C19EE6B9261A461D205'),
('US05', 'Marcella', '087752839338', 'Bogo', 'cella', '*84AAC12F54AB666ECFC2A83C676908C8BBC381B1');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id_produk` varchar(5) NOT NULL,
  `nama_produk` varchar(30) NOT NULL,
  `stok_produk` int(5) NOT NULL,
  `harga_jual` int(10) NOT NULL,
  `harga_beli` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id_produk`, `nama_produk`, `stok_produk`, `harga_jual`, `harga_beli`) VALUES
('PD01', 'Mie Instan', 35, 4000, 3000),
('PD02', 'Indomilk', 12, 3500, 2500),
('PD03', 'Nutrisari', 31, 4000, 3000),
('PD04', 'Pop Ice', 75, 4000, 3000),
('PD05', 'Qtela', 1, 3000, 2000),
('PD06', 'Milku', 35, 3000, 2000),
('PD07', 'Marimas', 45, 1500, 700),
('PD08', 'Mikako', 30, 1000, 750),
('PD09', 'Mainan', 40, 2000, 1500),
('PD10', 'Nyam-nyam', 80, 2000, 1500),
('PD11', 'Gerry', 30, 2000, 1500),
('PD12', 'Twisko', 50, 2000, 1000),
('PD13', 'Beng-beng', 20, 3000, 2000),
('PD14', 'Nabati', 35, 2000, 1500),
('PD15', 'Godday', 20, 3000, 2000);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` varchar(5) NOT NULL,
  `nama_supplier` varchar(30) NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama_supplier`, `no_telp`, `alamat`) VALUES
('KS001', 'Saputra', '08999999999', 'Nganjuk'),
('SP02', 'meta', '123456787654', 'gondang'),
('SP03', 'paimin', '098754321234', 'nganjuk'),
('SP04', 'Faisal', '098876646578', 'nganjuk');

-- --------------------------------------------------------

--
-- Table structure for table `tf_beli`
--

CREATE TABLE `tf_beli` (
  `id_tf_beli` varchar(6) NOT NULL,
  `id_pengguna` varchar(6) NOT NULL,
  `id_supplier` varchar(6) NOT NULL,
  `id_produk` varchar(6) NOT NULL,
  `nama_produk` varchar(30) NOT NULL,
  `jumlah` int(5) NOT NULL,
  `total_harga` int(10) NOT NULL,
  `tanggal_tf_beli` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `tf_beli`
--
DELIMITER $$
CREATE TRIGGER `tambah_stok` AFTER INSERT ON `tf_beli` FOR EACH ROW BEGIN
UPDATE produk
SET stok_produk = stok_produk + NEW.jumlah
WHERE `id_produk` = NEW.id_produk;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tf_jual`
--

CREATE TABLE `tf_jual` (
  `id_tf_jual` varchar(6) NOT NULL,
  `id_pengguna` varchar(6) NOT NULL,
  `id_produk` varchar(6) NOT NULL,
  `nama_produk` varchar(30) NOT NULL,
  `jumlah` int(5) NOT NULL,
  `total_harga` int(10) NOT NULL,
  `tanggal_tf_jual` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `tf_jual`
--
DELIMITER $$
CREATE TRIGGER `kurang_stok` AFTER INSERT ON `tf_jual` FOR EACH ROW BEGIN
UPDATE produk
SET stok_produk = stok_produk - NEW.jumlah
WHERE `id_produk` = NEW.id_produk;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indexes for table `tf_beli`
--
ALTER TABLE `tf_beli`
  ADD PRIMARY KEY (`id_tf_beli`),
  ADD KEY `id_pengguna` (`id_pengguna`,`id_supplier`,`id_produk`),
  ADD KEY `id_produk` (`id_produk`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- Indexes for table `tf_jual`
--
ALTER TABLE `tf_jual`
  ADD PRIMARY KEY (`id_tf_jual`),
  ADD KEY `id_pengguna` (`id_pengguna`,`id_produk`),
  ADD KEY `id_produk` (`id_produk`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tf_beli`
--
ALTER TABLE `tf_beli`
  ADD CONSTRAINT `tf_beli_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tf_beli_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tf_beli_ibfk_3` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tf_jual`
--
ALTER TABLE `tf_jual`
  ADD CONSTRAINT `tf_jual_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tf_jual_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
