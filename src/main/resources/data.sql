DROP TABLE IF EXISTS  Municipalities;
DROP TABLE IF EXISTS geographical;
DROP TABLE IF EXISTS CITIES;
DROP TABLE IF EXISTS PROVINCIA;
DROP TABLE IF EXISTS REGIONS;
CREATE TABLE REGIONS(
id_regione BIGINT PRIMARY KEY,
regione VARCHAR(70) not null ,
superficie  DOUBLE not null,
num_residenti INTEGER not null,
presidente VARCHAR(70) not null,
cod_istat VARCHAR(70) not null,
cod_fiscale bigint,
piva bigint ,
pec VARCHAR(70) not null,
sito VARCHAR(70) not null,
sede VARCHAR(255) not null
);


CREATE TABLE PROVINCIA(
sigla VARCHAR(70) PRIMARY KEY,
provincia VARCHAR(70) not null ,
superficie DOUBLE  not null,
residenti INTEGER not null,
id_regione  INTEGER ,
foreign key (id_regione ) references regions (id_regione ) ON DELETE CASCADE ON UPDATE CASCADE

);


CREATE TABLE CITIES (
istat BIGINT  PRIMARY KEY,
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


CREATE TABLE geographical(
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
istat INTEGER not null unique,
longitude DOUBLE  not null,
latitude DOUBLE  not null,
foreign key (istat ) references CITIES (istat ) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Municipalities(
id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
istat INTEGER not null unique,
municipality VARCHAR(70) not null,
regions VARCHAR(70) not null,
provincia VARCHAR(70) not null,
address VARCHAR(70) not null,
foreign key (istat ) references CITIES (istat ) ON DELETE CASCADE ON UPDATE CASCADE
);