CREATE TABLE proveedor (
  id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
  nombre VARCHAR(32) NOT NULL,
  direccion VARCHAR(64) NOT NULL,
  telefono VARCHAR(16) NOT NULL);
  
  