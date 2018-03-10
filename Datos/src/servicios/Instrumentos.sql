


CREATE TABLE TipoInstrumento (
  codigo varchar(5) NOT NULL,
  nombre varchar(30),
  unidad varchar(30),  
  PRIMARY KEY  (codigo)
  );

CREATE TABLE  Instrumento (
  serie varchar(10) NOT NULL,
  tipo varchar(5),
  descripcion varchar(30),
  minimo integer,
  maximo integer,
  tolerancia integer,  
  PRIMARY KEY (serie)
);

CREATE TABLE  Calibracion (
  numero integer NOT NULL AUTO_INCREMENT,
  instrumento varchar(10),
  fecha date,
  mediciones integer,  
  PRIMARY KEY (instrumento , fecha)
);

CREATE TABLE  Medida (
  calibracion integer,
  medida integer NOT NULL,
  referencia integer, 
  lectura integer,  
  PRIMARY KEY (calibracion,medida)
);

ALTER TABLE Instrumento ADD Foreign Key (tipo) REFERENCES TipoInstrumento(codigo); 

ALTER TABLE Calibracion ADD Foreign Key (instrumento) REFERENCES Instrumento(serie);

ALTER TABLE Medida ADD Foreign Key (calibracion) REFERENCES Calibracion(numero);


INSERT INTO TipoInstrumento (codigo,nombre,unidad) VALUES 
 ('001','Termometro','Grados Celcius'),
 ('002','Barometro','PSI');
 
 INSERT INTO Instrumento (serie,tipo,descripcion,minimo,maximo,tolerancia) VALUES 
 ('1546715','001','Termometro manual marca Hush',-20,150,1),
 ('2598652','002','Barometro de mesa Harrison',0,100,2),
 ('1784216','001','Termometro industrial Beck ',0,120,2); 
 
 INSERT INTO Calibracion (instrumento,fecha,mediciones) VALUES 
 ('1546715','2017/06/21',3),
 ('2598652','2017/07/25',1);
 
 INSERT INTO Medida (calibracion,medida, referencia,lectura) VALUES 
 (1,1,10,11),
 (1,2,25,24), 
 (1,3,70,70),
 (2,1,20,20); 

 
