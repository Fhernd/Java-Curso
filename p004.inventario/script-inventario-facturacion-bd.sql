CREATE TABLE IF NOT EXISTS cliente (
  `cedula` TEXT NOT NULL,
  `nombres` TEXT NOT NULL,
  `apellidos` TEXT NOT NULL,
  `telefono` TEXT NOT NULL,
  `direccion` TEXT NOT NULL,
  `correoe` TEXT NOT NULL,
  PRIMARY KEY (`cedula`));

CREATE TABLE IF NOT EXISTS proveedor (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `nombre` TEXT NOT NULL,
  `direccion` TEXT NOT NULL,
  `telefono` TEXT NOT NULL);
  
  CREATE TABLE IF NOT EXISTS producto (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `nombre` TEXT NOT NULL,
  `descripcion` TEXT NOT NULL,
  `precio_compra` REAL NOT NULL,
  `precio_venta` REAL NOT NULL,
  `cantidad` INTEGER NOT NULL,
  `cantidad_minima_stock` INTEGER NOT NULL,
  `proveedor_id` INT NOT NULL,
  CONSTRAINT fk_proveedor
  FOREIGN KEY (proveedor_id)
  REFERENCES proveedor(id));
  
  CREATE TABLE IF NOT EXISTS factura (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `fecha` TEXT NOT NULL,
  `impuesto` REAL NOT NULL,
  `valor_total` REAL NOT NULL,
  `cliente_cedula` TEXT NOT NULL,
  CONSTRAINT fk_cliente
  FOREIGN KEY (cliente_cedula)
  REFERENCES cliente(cedula));
  
  CREATE TABLE IF NOT EXISTS factura_producto (
  `factura_id` INTEGER NOT NULL,
  `producto_id` INTEGER NOT NULL,
  `cantidad` INTEGER NOT NULL,
  PRIMARY KEY (`factura_id`, `producto_id`),
  CONSTRAINT fk_factura
  FOREIGN KEY (factura_id)
  REFERENCES factura(id),
  CONSTRAINT fk_producto
  FOREIGN KEY (producto_id)
  REFERENCES producto(id));