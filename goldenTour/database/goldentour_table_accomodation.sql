
-- --------------------------------------------------------

--
-- Struttura della tabella `accomodation`
--

CREATE TABLE `accomodation` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(60) NOT NULL,
  `id_destination` int(11) NOT NULL,
  `id_type` int(45) NOT NULL,
  `telephone` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
