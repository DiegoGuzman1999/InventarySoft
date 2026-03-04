CREATE TABLE IF NOT EXISTS productos (
  id SERIAL PRIMARY KEY,
  codigo VARCHAR(255) UNIQUE NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  descripcion TEXT,
  precio DECIMAL(12, 2) NOT NULL,
  stock_actual INT NOT NULL,
  stock_minimo INT NOT NULL
);

