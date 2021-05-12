DROP TABLE Ciudadano;
DROP TABLE Reserva CASCADE;
DROP TABLE ReservaZonas CASCADE;
DROP TABLE Zona CASCADE;
DROP TABLE ServicioTemporal;
DROP TABLE Servicio;
DROP TABLE PeriodoAsignado;
DROP TABLE Periodo;
DROP TABLE ResponsableMunicipio;
DROP TABLE Controlador;
DROP TABLE Area CASCADE;
DROP TABLE Municipio CASCADE;


CREATE TABLE Municipio(
	cp	VARCHAR(9),
	nombre VARCHAR(50) NOT NULL,
	direccion   	 VARCHAR(50),
    email   		 VARCHAR(50),
    telefono 	 INTEGER,

CONSTRAINT 	cp_municipio PRIMARY KEY (cp));

CREATE TABLE Area(
	idArea		INTEGER,
	nombre	VARCHAR(50) NOT NULL,
	descripcion	VARCHAR(100),
	caracteristicasFisicas VARCHAR(100),
	localizacion	VARCHAR(100),
	tipoAcceso	VARCHAR (20),
	municipio	VARCHAR(9) NOT NULL,

	CONSTRAINT cp_area PRIMARY KEY (idArea),
	CONSTRAINT cp_area_municipio FOREIGN KEY(municipio) REFERENCES Municipio(cp) ON DELETE RESTRICT ON UPDATE CASCADE,
CONSTRAINT ri_caracteristicasFisicas CHECK (caracteristicasFisicas IN('montana','desierto','playa','bosque')),
CONSTRAINT ri_tipoAcceso CHECK (tipoAcceso IN('restringido','norestringido','cerrado'))
);

CREATE TABLE Controlador (
    identificador    	 INTEGER,
    nombre   		 VARCHAR(50) NOT NULL,
    direccion   	 	 VARCHAR(50),
    email   		 VARCHAR(50),
    telefono   	   	 INTEGER,
    fechaInicio  	 	 DATE,
    fechaFin   		 DATE	NULL,

    CONSTRAINT 	cp_controlador PRIMARY KEY (identificador),
    CONSTRAINT ri_fechaFin CHECK (fechaFin>fechaInicio)
);

CREATE TABLE ResponsableMunicipio(
	nombre	VARCHAR(50) NOT NULL,
	email		VARCHAR(50),
	numerotelefono	INTEGER,
	fechaInicio	DATE,
	fechaFin	DATE,
	identificador	INTEGER ,
	municipio	VARCHAR(9) NOT NULL,

	CONSTRAINT cp_responsableMunicipio PRIMARY KEY(identificador),
	CONSTRAINT ca_responsable_municipio FOREIGN KEY (municipio) REFERENCES Municipio(cp) ON DELETE RESTRICT ON UPDATE CASCADE ,
    CONSTRAINT ri_fechaFin CHECK (fechaFin>fechaInicio)

);

CREATE TABLE Periodo(
	identificador 		INTEGER ,
	fechaInicio		Date,
	fechaFin		Date	NULL,
	horaInicio		Time,
	horaFin		Time,
	idArea			INTEGER  NOT NULL,

	CONSTRAINT cp_periodo PRIMARY KEY(identificador),
	CONSTRAINT ca_periodo_area FOREIGN KEY(idArea) REFERENCES Area(idArea) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT ri_fechaFin CHECK (fechaFin>fechaInicio),
    CONSTRAINT ri_horaFin CHECK (horaFin>horaInicio)
);

CREATE TABLE PeriodoAsignado(
identificador    	 INTEGER ,
 	fechaInicio   	 	Date,
    	fechaFin  		 Date	NULL,
	nombreControlador	INTEGER  NOT NULL,
	nombreArea		INTEGER  NOT NULL,

CONSTRAINT 	cp_periodoAsignado PRIMARY KEY (identificador),
    	CONSTRAINT ca_controlador_periodo FOREIGN KEY (nombreControlador) REFERENCES
   Controlador(identificador) ON DELETE RESTRICT ON UPDATE CASCADE,
 CONSTRAINT ca_area_periodo FOREIGN KEY (nombreArea) REFERENCES
   Area(idArea) ON DELETE RESTRICT ON UPDATE CASCADE,
CONSTRAINT ri_fechaFin CHECK (fechaFin>fechaInicio)
);

CREATE TABLE Servicio(
	nombre VARCHAR(20) NOT NULL,
	descripcion VARCHAR(20),
	estado	VARCHAR(20),
	nombreArea		VARCHAR(50) NOT NULL,

    CONSTRAINT 	cp_servicio PRIMARY KEY (nombre),
    CONSTRAINT ca_area_periodo FOREIGN KEY (nombreArea) REFERENCES
   Area(idArea) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE ServicioTemporal(
	nombre VARCHAR(20),
	fechaInicio Date,
	fechaFin Date		NULL,
	horaInicio Time,
	horaFin Time,
	nombreArea	VARCHAR(20) NULL,

    CONSTRAINT 	cp_servicioTemporal PRIMARY KEY (nombre),
    CONSTRAINT ca_area_servicioTemporal FOREIGN KEY (nombreArea) REFERENCES
   Area(idArea) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT ri_fechaFin CHECK (fechaFin>fechaInicio),
    CONSTRAINT ri_horaFin CHECK (horaFin>horaInicio)
);

CREATE TABLE Zona(
	identificador	INTEGER ,
	capacidad	INTEGER,
	idArea		INTEGER  NOT NULL,

	CONSTRAINT cp_zona PRIMARY KEY(identificador),
	CONSTRAINT ca_zona_area FOREIGN KEY (idArea) REFERENCES Area(idArea) ON DELETE RESTRICT ON UPDATE CASCADE,
CONSTRAINT ri_capacidad CHECK (capacidad>0)
);

CREATE TABLE Ciudadano(
	nombre 	VARCHAR(50),
	nif 		VARCHAR(9),
	email		VARCHAR(50),
	residencia	VARCHAR(50),
	fechaRegistro	Date,

	CONSTRAINT cp_ciudadano PRIMARY KEY(nif)
);

CREATE TABLE Reserva(
	identificador 	INTEGER,
	hora		Time,
	fecha		Date,
	numeroPersonas	INTEGER,
	estado		VARCHAR(20),
	zona		VARCHAR(9) NOT NULL,
	ciudadano   VARCHAR(9),

	CONSTRAINT cp_reserva PRIMARY KEY(identificador),
	CONSTRAINT ca_reserva_zona FOREIGN KEY (zona) REFERENCES Zona(identificador) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT ca_ciudadano FOREIGN KEY (ciudadano) REFERENCES Ciudadano(nif) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT ri_numeroPersonas CHECK (numeroPersonas>0),
    CONSTRAINT ri_estado CHECK (estado IN('usada','cancelada', 'disponible'))
);

CREATE TABLE ReservaZonas(
	id_reserva 	INTEGER ,
	id_zona	INTEGER ,

	CONSTRAINT cp_reserva2 PRIMARY KEY(id_reserva,id_zona),
	CONSTRAINT cp_zona FOREIGN KEY (id_zona) REFERENCES Zona(identificador) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT cp_reservaAux FOREIGN KEY (id_reserva) REFERENCES Reserva(identificador) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE Ciudadano(
	nombre 	VARCHAR(50) NOT NULL,
	nif 		VARCHAR(9) NOT NULL,
	email		VARCHAR(50),
	residencia	VARCHAR(50),
	fechaRegistro	Date,
	reserva	VARCHAR(9),

	CONSTRAINT cp_ciudadano PRIMARY KEY(nif),
	CONSTRAINT ca_ciudadano_reserva FOREIGN KEY (reserva)
REFERENCES Reserva(identificador) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO Municipio VALUES('12590','Castellon','C/Jaume I','castellon@caste.com','12345678');
INSERT INTO Area VALUES('A01', 'Campus UJI', 'Universitat Jaume I', 'desierto', 'UJI', 'norestringido', 12590 );
INSERT INTO Controlador VALUES('C01','Juan','C/ De la guerra','juan@caste.com','54678912','2021-04-08',NULL);
INSERT INTO ResponsableMunicipio VALUES('Paco','paco@caste.com','09874564','2021-04-08',NULL,'R01','12590');
INSERT INTO Periodo VALUES('P01','2021-04-08',NULL,'12:35:38','18:45:20','A01');
INSERT INTO PeriodoAsignado VALUES('PA01','2021-04-08',NULL,'C01','A01');
INSERT INTO Servicio VALUES('Piscina','Baño en la piscina','usada','A01');
INSERT INTO ServicioTemporal VALUES('ControladorPlaya','2011-08-10','2012-08-25','10:00:00','20:15:00','A01');
INSERT INTO Zona VALUES('Z01','50','A01');
INSERT INTO Ciudadano VALUES('Joaquin', '20939567Z', 'joaquin@caste.com', 'Fuentes Claras','2021-04-08');
INSERT INTO Reserva VALUES('R01','15:15:15','2021-04-08','50','cancelada','Z01','20939567Z');
INSERT INTO ReservaZonas ('R01','Z01');

