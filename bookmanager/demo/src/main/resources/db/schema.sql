CREATE DATABASE IF NOT EXISTS biblioteca;
CREATE TABLE IF NOT EXISTS libro (
    id_libro int NOT NULL AUTO_INCREMENT,
    titolo varchar(255) DEFAULT NULL,
    autore varchar(255) DEFAULT NULL,
    data_pubblicazione date DEFAULT NULL,
    PRIMARY KEY (id_libro)
);
CREATE TABLE IF NOT EXISTS utente (
    id_utente int NOT NULL AUTO_INCREMENT,
    nome_utente varchar(100) DEFAULT NULL,
    email varchar(100) DEFAULT NULL,
    PRIMARY KEY (id_utente),
    UNIQUE KEY email (email)
);
CREATE TABLE IF NOT EXISTS prestito (
    id_prestito int NOT NULL AUTO_INCREMENT,
    data_prestito date DEFAULT NULL,
    data_restituzione date DEFAULT NULL,
    id_libro int DEFAULT NULL,
    id_utente int DEFAULT NULL,
    PRIMARY KEY (id_prestito),
    KEY id_libro (id_libro),
    KEY id_utente (id_utente),
    CONSTRAINT prestito_ibfk_1 FOREIGN KEY (id_libro) REFERENCES libro (id_libro) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT prestito_ibfk_2 FOREIGN KEY (id_utente) REFERENCES utente (id_utente) ON DELETE RESTRICT ON UPDATE CASCADE
);