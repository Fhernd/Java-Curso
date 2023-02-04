-- La tabla servicio en tiene las siguientes columnas:
-- id: int, clave primaria
-- fecha_hora_recepcion: datetime
-- fecha_hora_entrega: datetime

-- Actualizar la columna fecha_hora_entrega de la tabla servicio sumando 3 días a la columna fecha_hora_recepcion usando MySQL:
UPDATE servicio SET fecha_hora_entrega = DATE_ADD(fecha_hora_recepcion, INTERVAL 3 DAY) WHERE id >= 9;
