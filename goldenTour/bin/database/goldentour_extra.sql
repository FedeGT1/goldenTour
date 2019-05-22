
--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `accomodation`
--
ALTER TABLE `accomodation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id_type`),
  ADD KEY `id2` (`id_destination`);

--
-- Indici per le tabelle `accomodation_type`
--
ALTER TABLE `accomodation_type`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`Id`);

--
-- Indici per le tabelle `packet`
--
ALTER TABLE `packet`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `id_destination` (`id_destination`),
  ADD KEY `id_accomodation` (`id_accomodation`),
  ADD KEY `id_transport` (`id_transport`),
  ADD KEY `id_user` (`id_user`);

--
-- Indici per le tabelle `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `accomodation`
--
ALTER TABLE `accomodation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `accomodation_type`
--
ALTER TABLE `accomodation_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `destination`
--
ALTER TABLE `destination`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `packet`
--
ALTER TABLE `packet`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `transport`
--
ALTER TABLE `transport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `accomodation`
--
ALTER TABLE `accomodation`
  ADD CONSTRAINT `id` FOREIGN KEY (`id_type`) REFERENCES `accomodation_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id2` FOREIGN KEY (`id_destination`) REFERENCES `destination` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `packet`
--
ALTER TABLE `packet`
  ADD CONSTRAINT `id_accomodation` FOREIGN KEY (`id_accomodation`) REFERENCES `accomodation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_destination` FOREIGN KEY (`id_destination`) REFERENCES `destination` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_transport` FOREIGN KEY (`id_transport`) REFERENCES `transport` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;
