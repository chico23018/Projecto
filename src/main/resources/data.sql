DROP TABLE IF EXISTS regions;
CREATE TABLE regions(
id_regione VARCHAR(70) PRIMARY KEY,
regione VARCHAR(70) not null ,
superficie  BIGINT not null,
num_residenti INTEGER not null,
presidente VARCHAR(70) not null
);

DROP TABLE IF EXISTS PROVINCIA;
CREATE TABLE PROVINCIA(
sigla VARCHAR(70) PRIMARY KEY,
provincia VARCHAR(70) not null ,
superficie BIGINT  not null,
residenti INTEGER not null,
id_regione  VARCHAR(70) NOT NULL,
foreign key (id_regione ) references regions (id_regione ) ON DELETE CASCADE ON UPDATE CASCADE

);

DROP TABLE IF EXISTS CITTA;
CREATE TABLE CITTA(
istat VARCHAR(70) PRIMARY KEY,
comune VARCHAR(70) not null ,
sigla   VARCHAR(70) not null,
id_regione VARCHAR(70) not null,
prefisso INTEGER not null,
cod_fisco INTEGER not null,
superficie BIGINT  not null,
num_residenti INTEGER not null,
foreign key (sigla ) references PROVINCIA (sigla ) ON DELETE CASCADE ON UPDATE CASCADE,
foreign key (id_regione ) references regions (id_regione ) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS geographical;
CREATE TABLE geographical(
id INTEGER PRIMARY KEY,
istat VARCHAR(70) not null unique,
longitude BIGINT  not null,
latitude BIGINT  not null,
foreign key (istat ) references CITTA (istat ) ON DELETE CASCADE ON UPDATE CASCADE
);