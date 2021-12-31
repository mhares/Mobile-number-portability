CREATE USER 'khalifah'@'localhost' IDENTIFIED BY 'khalifah';

GRANT ALL PRIVILEGES ON * . * TO 'khalifah'@'localhost';

ALTER USER 'khalifah'@'localhost' IDENTIFIED WITH mysql_native_password BY 'khalifah';