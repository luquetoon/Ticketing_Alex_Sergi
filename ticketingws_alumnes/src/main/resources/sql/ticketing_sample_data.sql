-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 24, 2022 at 01:55 PM
-- Server version: 8.0.29
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `marianao`
--

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `name`) VALUES
(2, 'Sala JÚPITER'),
(1, 'Sala SATURN'),
(3, 'Sala VENUS');

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `role`, `full_name`, `password`, `extension`, `room_id`, `place`, `company`) VALUES
('alex', 'EMPLOYEE', 'Àlex Macia Pérez', '$2a$12$7.CmA6uR/7.zsPPsFGp9YOeJ9i/nVS1Uoz9z74..3OehapDIQ8Swm', 3515, 1, 'L15', NULL),
('genis', 'TECHNICIAN', 'Genís Esteve i Prats', '$2a$12$dAFbp3rQRLYIhO/O03H9oO1O80YeJUgj6dHC/azpKyv/qp4K7/J3S', 1515, NULL, NULL, 'INET'),
('laia', 'SUPERVISOR', 'Laia Vives i Marsans', '$2a$12$ANCcorlrUjKYXzy81G/g3.gnNXLZzG4WklmLMIS.713CO/LSlOnJi', 1501, NULL, NULL, 'INET'),
('lola', 'EMPLOYEE', 'Lola Valls i Vilalta', '$2a$12$RD6/FeFMYgKn.wvccYOhVOIs/cd243leOPmxIpzwtZUc4hQSLa2dy', 2501, 3, 'L08', NULL),
('maria', 'EMPLOYEE', 'Maria Lopez i Castells', '$2a$12$XZbQjY.nrREh6xlPP7Z7fupdDbbGwwlaA4BiK.NU69AFEArUSVVEu', 3513, 1, 'L13', NULL),
('raul', 'TECHNICIAN', 'Raul Casanova i Ferrer', '$2a$12$QEa88.2eEXb418iv4DWhG.gQfPC7L1ok4HHgrqg4Hhas5NPLvKUoy', 1504, NULL, NULL, 'T-SYSTEMS'),
('robert', 'EMPLOYEE', 'Robert Planes i Pujol', '$2a$12$fr5HQBhf2ASYonUY8HDzu..JlkjS9MC7g32PuqSZg/Tf.HHQCgpve', 3510, 1, 'L10', NULL),
('toni', 'EMPLOYEE', 'Antoni Bosc i Cases', '$2a$12$ljkGlV6eJe4QIwvg8mX6A.mXyYvMtvEs/WlNfuXqkzEuSYvUsjMM6', 2508, 3, 'L08', NULL);


--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`id`, `category`, `description`) VALUES
(1, 'HARDWARE', 'El ratolí no funciona bé, a vegades desapareix o no es mou. A més sovint he de posar un paper a sota. Ho podeu revisar? Gràcies'),
(2, 'NETWORK', 'Internet funciona molt lent, fent servir el navegador Firefox les pàgines triguen molt a carregar-se i s\'obren finestres emergents de propaganda. He provat d\'instal·lar Chrome però em demana la contrasenya d\'administrador. Necessito una solució urgentment!!!'),
(3, 'SUPPORT', 'Estic intentant fer el desglossament de l\'IVA pels apunts importats al programa de comptabilitat però no me\'n surto. Necessito un cop de ma perquè la propera setmana ve l\'auditor. Gràcies'),
(4, 'PRINTER', 'No puc imprimir a doble cara a la impressora de la Sala VENUS. Tot i que marco l\'opció abans d\'enviar a imprimir documents no surten a doble cara');


--
-- Dumping data for table `actions`
--

INSERT INTO `actions` (`id`, `type`, `performer_username`, `date`, `ticket_id`, `priority`, `technician_username`, `description`, `hours`) VALUES
(1, 'OPENING', 'alex', '2022-10-01 08:15:31', 1, NULL, NULL, NULL, NULL),
(2, 'OPENING', 'maria', '2022-09-24 07:54:13', 2, NULL, NULL, NULL, NULL),
(3, 'OPENING', 'lola', '2022-09-24 17:32:04', 3, NULL, NULL, NULL, NULL),
(4, 'OPENING', 'toni', '2022-09-27 11:27:50', 4, NULL, NULL, NULL, NULL),
(5, 'ASSIGNMENT', 'laia', '2022-10-02 11:21:15', 1, 4, 'raul', NULL, NULL),
(6, 'INTERVENTION', 'raul', '2022-10-04 13:25:11', 1, NULL, NULL, 'És un ratolí inalàmbric, les piles estaven gastades. S\'han substituït però segueix sense funcionar bé del tot. Cal canviar el ratolí.', 2),
(7, 'INTERVENTION', 'genis', '2022-10-05 15:44:37', 1, NULL, NULL, 'Ratolí inalàmbric canviat per model USB.', 1),
(8, 'CLOSE', 'raul', '2022-10-05 19:02:00', 1, NULL, NULL, NULL, NULL),
(9, 'ASSIGNMENT', 'laia', '2022-09-26 13:39:14', 2, 9, 'genis', NULL, NULL),
(10, 'INTERVENTION', 'genis', '2022-09-26 15:15:54', 2, NULL, NULL, 'Google Chrome instal·lat. Firefox actualitzat i restablert a la configuració de fàbrica, l\'ordinador està ple de virus. S\'ha actualitzat l\'antivirus, un escaneig complet en profunditat i programació d\'una tasca per automatitzar un escaneig dos cops per setmana (dilluns i dijous al migdia)', 4),
(11, 'ASSIGNMENT', 'laia', '2022-09-29 14:32:04', 3, 3, 'raul', NULL, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
