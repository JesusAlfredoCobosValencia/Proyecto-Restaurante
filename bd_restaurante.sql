Use restaurante;

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL,
    rol VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO usuario (username, password, rol) VALUES	
('gerente', '1234', 'gerente'), ('mesero', '1234', 'mesero'), ('cajero', '1234', 'cajero'),
('recepcionista', '1234', 'recepcionista'), ('chef', '1234', 'chef');

drop table usuario;
select * from usuario;


 drop table mesa;
 
 CREATE TABLE mesa(
    id_mesa INT AUTO_INCREMENT PRIMARY KEY,
    numero INT,
    estado VARCHAR(20));
    
    INSERT INTO mesa (numero, estado) VALUES
(1, 'libre'), (2, 'libre'), (3, 'libre'), (4, 'libre'), (5, 'libre'), (6, 'libre');


CREATE TABLE reservacion (
    id_reservacion INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cliente VARCHAR(50),
    id_mesa INT,
    fecha_hora DATETIME NOT NULL
);

drop table reservacion;

update mesa set estado = 'libre';

select * from  reservacion;
select * from mesa;


CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_mesa INT,
    fecha DATETIME
);



CREATE TABLE detalle_pedido (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    producto VARCHAR(100),
    cantidad INT
);


CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10,2),
    categoria VARCHAR(50),
    disponible BOOLEAN
);

select * from detalle_pedido;


INSERT INTO producto (nombre, precio, categoria, disponible) VALUES
('Hamburguesa sencilla', 70.00, 'Hamburguesas', true),
('Hamburguesa doble cheese', 90.00, 'Hamburguesas', true),
('Hamburguesa especial', 110.00, 'Hamburguesas', true);


INSERT INTO producto (nombre, precio, categoria, disponible) VALUES
('Coca Cola', 25.00, 'Bebidas', true),
('Pepsi', 25.00, 'Bebidas', true),
('Fanta', 25.00, 'Bebidas', true),
('Agua', 20.00, 'Bebidas', true);


INSERT INTO producto (nombre, precio, categoria, disponible) VALUES
('Papas a la francesa', 50.00, 'Papas', true),
('Papas gajo', 60.00, 'Papas', true),
('Papas con queso', 70.00, 'Papas', true);

SELECT * FROM producto;
SELECT * FROM producto WHERE categoria = 'Hamburguesas';
SELECT * FROM producto WHERE categoria = 'Bebidas';
SELECT * FROM producto WHERE categoria = 'Papas';






CREATE TABLE asistencia (
    id_usuario INT NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR(1) NOT NULL,
    PRIMARY KEY (id_usuario, fecha),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

select * from asistencia;
truncate table asistencia;

ALTER TABLE asistencia DROP FOREIGN KEY asistencia_ibfk_1;

ALTER TABLE asistencia
ADD CONSTRAINT asistencia_ibfk_1
FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
ON DELETE CASCADE;


select * from mesa;
truncate table reservacion;	





CREATE TABLE lista_espera (
    id_espera INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cliente VARCHAR(50) NOT NULL,
    id_mesa INT,
    FOREIGN KEY (id_mesa) REFERENCES mesa(id_mesa)
);




CREATE TABLE inventario (
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    ingrediente VARCHAR(100) UNIQUE NOT NULL,
    unidades INT NOT NULL,
    minimo INT DEFAULT 10,
    estado VARCHAR(20) NOT NULL
);

CREATE TABLE movimientos (
    id_mov INT AUTO_INCREMENT PRIMARY KEY,
    id_inventario INT NOT NULL,
    accion VARCHAR(50) NOT NULL,
    cantidad INT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_inventario) REFERENCES inventario(id_inventario)
);

select * from movimientos;

DROP TABLE inventario CASCADE;
DROP TABLE movimientos CASCADE;

DESCRIBE movimientos;
truncate table movimientos;	


ALTER TABLE movimientos DROP FOREIGN KEY movimientos_ibfk_1;
ALTER TABLE movimientos ADD COLUMN ingrediente VARCHAR(100);
	



