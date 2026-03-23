# Guía de consulta – Rúbrica de evaluación (InventarySoft)

Documento **solo para consulta**: qué verificar y qué evidencia usar en cada criterio.  
Usa la columna de puntuación según lo que el evaluador indique (ej. 0/1 o escala).

---

| # | Criterio | Qué verificar / Evidencia | Nota para tu proyecto |
|---|----------|---------------------------|------------------------|
| **1** | Repositorio en GitHub accesible y funcional | Repo público, clonable, sin errores 404. | Tienes referencias en README a varios repos (InventarySoft, Portal, App, Api). Verifica que el repo que presentes esté accesible. |
| **2** | Estructura organizada del monorepo | Carpetas por módulo (inventory-service, api-gateway, frontend-landing, etc.). | ✅ Monorepo con `inventory-service`, `api-gateway`, `frontend-landing`, `Portal`, `database`, `pgadmin`, `Docs`. |
| **3** | README.md claro | Descripción, cómo ejecutar, arquitectura, enlaces. | ✅ README con objetivo, arquitectura, tabla de servicios y puertos, orden de arranque. |
| **4** | .gitignore correcto para Java/Maven | Incluir `target/`, `*.jar`, `*.war`, `.idea`, etc. | ⚠️ Revisar: tu .gitignore es más orientado a Node/npm. Añade entradas Java/Maven si el evaluador lo exige (target/, *.class, .mvn/wrapper/*.jar si aplica). |
| **5** | Carpeta docs/ con documentación | Existencia de `docs/` o `Docs/` con archivos útiles. | ✅ Tienes `Docs/` con `architecture.md`, `Diagrama_del_Sistema.md`, `estimacion_moscow.md`. Si piden `docs/` en minúscula, renombra o indica que usas `Docs/`. |
| **6** | Uso correcto de ramas main, develop y qa | Ramas creadas y usadas en el flujo. | Consultar en GitHub: `git branch -a`. Crear `develop` y `qa` si no existen y documentar su uso en README o docs. |
| **7** | Uso de feature branches | Ramas tipo `feature/nombre` o `feature/nombre-funcionalidad`. | Consultar en GitHub. Crear y fusionar al menos un par de feature branches para mostrar el flujo. |
| **8** | Historial de commits significativo | Mensajes claros, commits que reflejen avances. | Revisar en GitHub el historial. Asegurar mensajes descriptivos (ej. "feat: CRUD productos", "fix: gateway route"). |
| **9** | Uso de Pull Requests | PRs de feature/develop a main o develop. | Debe verse en GitHub: PRs cerrados/mergeados. Si no hay, hacer al menos uno de ejemplo. |
| **10** | Uso de GitHub Issues | Issues para tareas, bugs o mejoras. | Revisar pestaña Issues. Crear algunas si están vacías (ej. "Configurar Gateway", "Documentar API"). |
| **11** | Existe API Gateway configurado | Proyecto api-gateway con Spring Cloud Gateway (o similar). | ✅ `api-gateway` con `application.yml` y rutas (ej. `/api/products/**` → inventory-service). |
| **12** | Gateway enruta correctamente | Petición a Gateway llega al servicio de backend. | Probar: `GET http://localhost:8082/api/products` y comprobar que responde lo mismo que el servicio en 8081. |
| **13** | Arquitectura por capas | Capas definidas (presentación, negocio, datos). | ✅ inventory-service con paquetes controller/api, service, repository, entity, dto. |
| **14** | Separación Controller/Service/Repository | Clases distintas para cada responsabilidad. | ✅ ProductController, ProductService, ProductRepository (y variantes Producto*). |
| **15** | Uso de DTOs | Objetos para entrada/salida de API (request/response). | ✅ DTOs en `product/dto`, `product/api/dto` (ProductResponse, ProductCreateRequest, ProductUpdateRequest, ProductoDTO). |
| **16** | CRUD completo implementado | Crear, leer, actualizar y eliminar en al menos una entidad. | Revisar ProductController/ProductoController: endpoints GET, POST, PUT, DELETE para productos. |
| **17** | Endpoints accesibles desde Gateway | Toda la API de negocio se consume vía Gateway, no directo al servicio. | Frontend usa `http://localhost:8082/api/products`. Comprobar que no se llama a 8081 desde el cliente. |
| **18** | Persistencia con PostgreSQL | Base de datos PostgreSQL en uso. | ✅ `application.yml`: `jdbc:postgresql://localhost:5432/inventarysoft_db`; Docker Compose con servicio `db-inventory`. |
| **19** | Uso de Docker Compose | Archivo docker-compose.yml y servicios definidos. | ✅ `docker-compose.yml` con `db-inventory` (PostgreSQL) y `pgadmin-inventory`. |
| **20** | Entidad mapeada con JPA | Clase con @Entity y mapeo a tabla. | ✅ Entidades `Product.java` y `Producto.java` con `@Entity`. |
| **21** | Explican el problema | En la presentación se describe el problema que resuelve el sistema. | Preparar 1–2 diapositivas: problema (gestión de inventarios/ventas), objetivo y solución. |
| **22** | Presentan diagrama de arquitectura | Diagrama (flujo Frontend → Gateway → Servicios → DB). | ✅ Tienes `Docs/Diagrama_del_Sistema.md` y `Docs/architecture.md`. Incluir en presentación o README. |
| **23** | Demo funcional del sistema | Ejecución en vivo: levantar servicios y mostrar flujo. | Orden: Docker (DB) → inventory-service (8081) → api-gateway (8082) → frontend (8084). Mostrar listado/CRUD desde la app. |
| **24** | Muestran repositorio GitHub | Pantalla o enlace al repo durante la presentación. | Tener abierto el repo principal (o el que corresponda) y mostrar estructura, README, docs. |
| **25** | Responden preguntas técnicas | Respuestas coherentes sobre arquitectura, tecnologías, decisiones. | Repasar: puertos (8081, 8082, 8084, 5432, 5050), flujo de una petición, capas, DTOs, JPA, Docker. |

---

## Resumen rápido para marcar

- **Repositorio y organización (1–5):** Repo accesible, monorepo ordenado, README claro, .gitignore Java/Maven, carpeta docs.
- **Git / flujo (6–10):** Ramas main/develop/qa, feature branches, commits y PRs e Issues en GitHub.
- **Arquitectura y API (11–17):** Gateway configurado y enrutando, capas, Controller/Service/Repository, DTOs, CRUD, acceso vía Gateway.
- **Datos e infra (18–20):** PostgreSQL, Docker Compose, entidades JPA.
- **Presentación (21–25):** Explicar problema, diagrama, demo en vivo, mostrar repo y responder preguntas técnicas.

---

*Documento de consulta. No sustituye las instrucciones del evaluador sobre cómo rellenar la columna de puntuación.*
