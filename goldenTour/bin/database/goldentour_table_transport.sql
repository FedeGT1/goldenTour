
-- --------------------------------------------------------

--
-- Struttura della tabella `transport`
--

CREATE TABLE `transport` (
  `id` int(11) NOT NULL,
  `id_booking` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
