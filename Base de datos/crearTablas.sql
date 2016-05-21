CREATE TABLE Usuario
(
	email		VARCHAR(50),
	contrasena	VARCHAR(50),
	nick		VARCHAR(20),
	nombre		VARCHAR(20),
	nacimiento	VARCHAR(10),
	CONSTRAINT usuario_pk PRIMARY KEY (email)
);

CREATE TABLE Pelicula
(
	id		NUMBER(5),
	titulo		VARCHAR(50),
	fecha		VARCHAR(15),
	director	VARCHAR(40),
	duracion	NUMBER(3),
	valoracion	NUMBER(10,5),
	sinopsis	VARCHAR(1500),
	url		VARCHAR(600),
	puntuacion	NUMBER(38,5),
	numVistas	NUMBER DEFAULT 0,
	CONSTRAINT pelicula_pk PRIMARY KEY (id)
);

CREATE TABLE Categoria
(
	nombre	VARCHAR(10),
	CONSTRAINT categoria_pk PRIMARY KEY (nombre)
);

CREATE TABLE Publico
(
	nombre	VARCHAR(10),
	CONSTRAINT publico_pk PRIMARY KEY (nombre)
);

CREATE TABLE Vista
(
	usuario		VARCHAR(50),
	pelicula	NUMBER(5),
	valoracion	NUMBER(10,5),
	CONSTRAINT usuario_fk1 FOREIGN KEY (usuario) REFERENCES usuario(email),
	CONSTRAINT pelicula_fk1 FOREIGN KEY (pelicula) REFERENCES pelicula(id),
	CONSTRAINT vista_pk PRIMARY KEY (usuario,pelicula)
);

CREATE TABLE Pendiente
(
	usuario		VARCHAR(50),
	pelicula	NUMBER(5),
	CONSTRAINT usuario_fk2 FOREIGN KEY (usuario) REFERENCES usuario(email),
	CONSTRAINT pelicula_fk2 FOREIGN KEY (pelicula) REFERENCES pelicula(id),
	CONSTRAINT pendiente_pk PRIMARY KEY (usuario,pelicula)
);

CREATE TABLE Es
(
	pelicula	NUMBER(5),
	categoria 	VARCHAR(10),
	CONSTRAINT pelicula_fk3 FOREIGN KEY (pelicula) REFERENCES pelicula(id),
	CONSTRAINT categoria_fk1 FOREIGN KEY (categoria) REFERENCES categoria(nombre),
	CONSTRAINT es_pk PRIMARY KEY (pelicula,categoria)
);

CREATE TABLE Dirigida
(
	pelicula	NUMBER(5),
	publico 	VARCHAR(10),
	CONSTRAINT pelicula_fk4 FOREIGN KEY (pelicula) REFERENCES pelicula(id),
	CONSTRAINT publico_fk1 FOREIGN KEY (publico) REFERENCES publico(nombre),
	CONSTRAINT dirigida_pk PRIMARY KEY (pelicula,publico)
);
