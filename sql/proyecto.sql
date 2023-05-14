drop table usuario;
drop  table  productos;

create database caducidad;
use caducidad;
CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT,
    correo VARCHAR(50) NOT NULL,
    contraseña VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_usuario)
);
CREATE TABLE productos (
    id_producto INT  AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    cantidad INT(11) NOT NULL,
    nombre_producto VARCHAR(50) NOT NULL,
    fecha DATE NOT NULL,
    PRIMARY KEY (id_producto),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);
