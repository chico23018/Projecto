DROP TABLE IF EXISTS regions;
CREATE TABLE regions(
id_regione INTEGER PRIMARY KEY,
regione VARCHAR(70) not null ,
superficie  DOUBLE not null,
num_residenti INTEGER not null,
presidente VARCHAR(70) not null
);

DROP TABLE IF EXISTS PROVINCIA;
CREATE TABLE PROVINCIA(
sigla VARCHAR(70) PRIMARY KEY,
provincia VARCHAR(70) not null ,
superficie DOUBLE  not null,
residenti INTEGER not null,
id_regione  INTEGER ,
foreign key (id_regione ) references regions (id_regione ) ON DELETE CASCADE ON UPDATE CASCADE

);

DROP TABLE IF EXISTS CITTA;
CREATE TABLE CITTA(
istat INTEGER  PRIMARY KEY,
comune VARCHAR(70) not null ,
sigla   VARCHAR(70) not null,
id_regione VARCHAR(70) not null,
prefisso INTEGER not null,
cod_fisco VARCHAR(70) not null,
superficie DECIMAL  not null,
num_residenti INTEGER not null,

foreign key (sigla ) references PROVINCIA (sigla ) ON DELETE CASCADE ON UPDATE CASCADE,
foreign key (id_regione ) references regions (id_regione ) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS geographical;
CREATE TABLE geographical(
id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
istat INTEGER not null unique,
longitude DOUBLE  not null,
latitude DOUBLE  not null,
foreign key (istat ) references CITTA (istat ) ON DELETE CASCADE ON UPDATE CASCADE
);