
-- --------------------------------------------------------

--
-- Struttura della tabella `packet`
--

CREATE TABLE `packet` (
  `Id` int(11) NOT NULL,
  `Description` varchar(120) NOT NULL,
  `id_destination` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `person_number` int(11) NOT NULL,
  `id_transport` int(11) NOT NULL,
  `id_accomodation` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
