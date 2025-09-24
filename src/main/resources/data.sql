-- Insertar en la tabla Usuario
INSERT IGNORE INTO Usuario (nombre, apellido, telefono, nit)
VALUES
('Juan', 'Pérez', '1234567890', '1234567890'),
('Maria', 'Gómez', '0987654321', '9876543210');

-- Insertar en la tabla Credenciales
INSERT IGNORE INTO Credenciales (id_Usuario, email, contraseña, fecha_Registro)
VALUES
(1, 'juan.perez@example.com', 'contraseña123', '2023-01-01'),
(2, 'maria.gomez@example.com', 'contraseña456', '2023-02-01');

-- Insertar en la tabla Presupuesto
INSERT IGNORE INTO Presupuesto (id_Usuario, nombre_Presupuesto, periodo_Presupuesto, fecha_Inicio, fecha_Fin,estado, monto_totalPlanificado)
VALUES
(1, 'Presupuesto Enero', 'Enero 2023', '2023-01-01', '2023-01-31','COMPLETADO', 5000.00),
(2, 'Presupuesto Febrero', 'Febrero 2023', '2023-02-01', '2023-02-28','COMPLETADO', 4000.00);

-- Insertar en la tabla Categoria
INSERT IGNORE INTO Categoria (id_Presupuesto, nombre_Categoria, tipo_Categoria, color_Identificacion, monto_Planificado, monto_Actual)
VALUES
(1, 'Alimentación', 'Fijo', 'Rojo', 1500.00, 1200.00),
(1, 'Transporte', 'Variable', 'Azul', 1000.00, 800.00),
(2, 'Entretenimiento', 'Variable', 'Verde', 500.00, 300.00);

-- Insertar en la tabla Transaccion
INSERT IGNORE INTO Transaccion (id_Categoria, descripcion_Transaccion, monto_Transaccion, fecha_Transaccion, tipo_Transaccion)
VALUES
(1, 'Compra supermercado', 300.00, '2023-01-05', 'INGRESO'),
(2, 'Gasolina', 150.00, '2023-01-10', 'GASTO'),
(3, 'Cine', 120.00, '2023-02-05', 'INGRESO');

-- Insertar en la tabla Ingreso
INSERT IGNORE INTO Ingreso (id_Presupuesto, id_Categoria, descripcion_Ingreso, monto_Ingreso, fecha_Ingreso)
VALUES
(1, 1, 'Ingreso por venta de productos', 2000.00, '2023-01-15'),
(2, 3, 'Ingreso por freelance', 1000.00, '2023-02-10');

-- Insertar en la tabla GASTO
INSERT INTO Gasto (id_Presupuesto, id_Categoria, descripcion_Gasto, categoria_Gasto, monto_Gasto, fecha_Gasto)
VALUES
(1, 1, 'Compra de víveres para el hogar', 'Alimentos', 150.75, '2025-09-20'),
(1, 2, 'Factura de electricidad de septiembre', 'Servicios', 85.00, '2025-09-21');

-- Insertar en la tabla MetaAhorro
INSERT IGNORE INTO Meta_ahorro (id_Presupuesto, nombre_Meta, monto_Objetivo, monto_Actual, fecha_Limite)
VALUES
(1, 'Fondo de emergencia', 2000.00, 1000.00, '2023-12-31'),
(2, 'Viaje a la playa', 1500.00, 500.00, '2023-12-31');