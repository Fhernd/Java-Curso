DROP TABLE factura_producto;
DROP TABLE factura;
DROP TABLE proveedor;
DROP TABLE cliente;
DROP TABLE producto;

CREATE TABLE proveedor (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(32) NOT NULL,
  direccion VARCHAR(64) NOT NULL,
  telefono VARCHAR(16) NOT NULL);
  
CREATE TABLE cliente (
  cedula VARCHAR(10) NOT NULL,
  nombres VARCHAR(32) NOT NULL,
  apellidos VARCHAR(32) NOT NULL,
  telefono VARCHAR(16) NOT NULL,
  direccion VARCHAR(64) NOT NULL,
  correoe VARCHAR(64) NOT NULL,
  PRIMARY KEY (cedula));
  
CREATE TABLE producto (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(32) NOT NULL,
  descripcion VARCHAR(128) NOT NULL,
  precio_compra DOUBLE NOT NULL,
  precio_venta DOUBLE NOT NULL,
  cantidad INTEGER NOT NULL,
  cantidad_minima_stock INTEGER NOT NULL,
  proveedor_id INTEGER NOT NULL,
    FOREIGN KEY (proveedor_id)
    REFERENCES proveedor (id));

    
CREATE TABLE factura (
  id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
  fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  impuesto DOUBLE NOT NULL,
  valor_total DOUBLE NOT NULL,
  cliente_cedula VARCHAR(10) NOT NULL,
    FOREIGN KEY (cliente_cedula)
    REFERENCES cliente (cedula));
    
CREATE TABLE factura_producto (
  factura_id INTEGER NOT NULL,
  producto_id INTEGER NOT NULL,
  cantidad INTEGER NOT NULL,
  PRIMARY KEY (factura_id, producto_id),
  FOREIGN KEY (factura_id) REFERENCES factura (id),
  FOREIGN KEY (producto_id) REFERENCES producto (id));
  
  