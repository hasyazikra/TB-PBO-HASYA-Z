-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 01 Jan 2022 pada 21.33
-- Versi Server: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE IF NOT EXISTS `buku` (
  `no_buku` int(10) NOT NULL,
  `judul_buku` varchar(20) NOT NULL,
  `nama_pengarang` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`no_buku`, `judul_buku`, `nama_pengarang`) VALUES
(1155, 'the magic of blender 3d', 'Jane Foster'),
(2266, 'animation tutorials', 'Thor'),
(3377, 'Die For You', 'Joji'),
(4488, 'Cook n Bake', 'Summer Walker'),
(5599, 'fashion design', 'Donatella Versace'),
(661010, 'Models', 'Yves Saint Laurent');

-- --------------------------------------------------------

--
-- Struktur dari tabel `daftar_pengembali`
--

CREATE TABLE IF NOT EXISTS `daftar_pengembali` (
  `id_anggota` int(10) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `no_buku` int(10) NOT NULL,
  `denda` varchar(99) NOT NULL,
  `tanggal_pengembalian` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `daftar_pengembali`
--

INSERT INTO `daftar_pengembali` (`id_anggota`, `nama`, `no_buku`, `denda`, `tanggal_pengembalian`) VALUES
(3021, 'Hasya', 661010, 'RP20000', '2022-01-01'),
(3022, 'Taylor', 3377, 'RP20000', '2022-01-01'),
(3025, 'Adam', 4488, '1 Buku', '2022-01-01'),
(3023, 'Julian', 1155, 'Rp20000', '2022-01-01'),
(3024, 'Sarah', 2266, '1 Buku', '2022-01-01'),
(3027, 'Fanes', 5599, 'Rp15000', '2022-01-01');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
 ADD PRIMARY KEY (`no_buku`);

--
-- Indexes for table `daftar_pengembali`
--
ALTER TABLE `daftar_pengembali`
 ADD PRIMARY KEY (`id_anggota`), ADD UNIQUE KEY `no_buku` (`no_buku`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `daftar_pengembali`
--
ALTER TABLE `daftar_pengembali`
ADD CONSTRAINT `daftar_pengembali_ibfk_1` FOREIGN KEY (`no_buku`) REFERENCES `buku` (`no_buku`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
