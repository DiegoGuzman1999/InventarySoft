## inventory-service

Spring Boot service con **Maven Wrapper** (no necesitas Maven instalado).

### Requisitos
- Java 17+
- Postgres

### Configuración (variables de entorno)
- `DB_URL` (default: `jdbc:postgresql://localhost:5432/inventory_db`)
- `DB_USERNAME` (default: `postgres`)
- `DB_PASSWORD` (default: `postgres`)
- `SERVER_PORT` (default: `8080`)
- `DDL_AUTO` (default: `update`)

### Ejecutar
- En Windows (PowerShell / CMD):

```bash
cd inventory-service
.\mvnw.cmd spring-boot:run
```

- En Git Bash / Linux / macOS:

```bash
cd inventory-service
./mvnw spring-boot:run
```

### Endpoints (CRUD Productos)
- `GET /api/products`
- `GET /api/products/{id}`
- `POST /api/products`
- `PUT /api/products/{id}`
- `DELETE /api/products/{id}`

