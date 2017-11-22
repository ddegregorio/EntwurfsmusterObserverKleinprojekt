-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 22. Nov 2017 um 18:05
-- Server-Version: 10.1.28-MariaDB
-- PHP-Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `shopKonditoreidb`
--
CREATE DATABASE shopkonditoreidb;
USE shopKonditoreidb;


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `articles`
--

CREATE TABLE `articles` (
  `a_id` int(11) NOT NULL,
  `articlename` varchar(255) COLLATE utf8_german2_ci NOT NULL,
  `price` double NOT NULL,
  `discount` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `articles`
--

INSERT INTO `articles` (`a_id`, `articlename`, `price`, `discount`) VALUES
(1, 'Apfelstrudel', 3.5, 0),
(4, 'Erdbeertorte', 4, 0),
(6, 'Sachertorte', 3.4, 0),
(8001, 'Schwarzwälder Kirschtorte', 3.1, 0),
(8002, 'Donauwelle', 3.3, 0),
(8003, 'Linzertorte', 3.6, 0),
(8004, 'Prinzregententorte', 3.1, 0),
(8005, 'Mille-feuille', 3.8, 0),
(8006, 'Dobos torte', 3.6, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `userarticlemappings`
--

CREATE TABLE `userarticlemappings` (
  `u_id` int(11) NOT NULL,
  `a_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `userarticlemappings`
--

INSERT INTO `userarticlemappings` (`u_id`, `a_id`) VALUES
(1, 6),
(5, 6),
(1000, 6),
(1003, 6),
(1004, 4),
(1005, 8001),
(1006, 8002),
(1007, 8002),
(1008, 8003),
(1009, 8005),
(1010, 8005),
(1011, 8005),
(1012, 8004),
(1013, 8005);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE `users` (
  `u_id` int(11) NOT NULL,
  `u_username` varchar(255) COLLATE utf8_german2_ci NOT NULL,
  `u_password` varchar(255) COLLATE utf8_german2_ci NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`u_id`, `u_username`, `u_password`, `admin`) VALUES
(1, 'Dominik', 'test', 0),
(5, 'Tobias', 'testi', 0),
(1000, 'Sabine', 'testitest', 1),
(1003, 'Johannes', 'Password', 0),
(1004, 'Magdalena', 'Password', 0),
(1005, 'Immanuel', 'Password', 0),
(1006, 'Franz', 'Password', 0),
(1007, 'Andrea', 'Password', 0),
(1008, 'Anna', 'Password', 0),
(1009, 'Lukas', 'Password', 0),
(1010, 'Daniel', 'Password', 0),
(1011, 'Elias', 'Password', 0),
(1012, 'Julia', 'Password', 0),
(1013, 'Michael', 'Password', 0);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`a_id`);

--
-- Indizes für die Tabelle `userarticlemappings`
--
ALTER TABLE `userarticlemappings`
  ADD PRIMARY KEY (`u_id`,`a_id`);

--
-- Indizes für die Tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `articles`
--
ALTER TABLE `articles`
  MODIFY `a_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8007;
--
-- AUTO_INCREMENT für Tabelle `users`
--
ALTER TABLE `users`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1014;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
