CREATE USER 'goldentour'@'localhost' IDENTIFIED BY 'goldentour';
set password for 'goldentour'@'localhost' = PASSWORD('123456789root');
GRANT ALL ON goldentour.* TO 'goldentour'@'localhost';

