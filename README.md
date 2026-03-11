# InventarySoft

InventarySoft es una plataforma de software web orientada a la gestión integral de procesos administrativos, ventas e inventarios para organizaciones comerciales.

El proyecto se desarrolla bajo metodología ágil **Scrum**, con una arquitectura modular basada en repositorios independientes.

---

## 🎯 Objetivo del Proyecto

Diseñar e implementar una solución web escalable que permita:
- Gestión de inventarios
- Control de ventas
- Administración de procesos internos
- Separación clara entre frontend, backend y base de datos

---

## 🧱 Arquitectura del Proyecto

El sistema está organizado en múltiples repositorios, cada uno con una responsabilidad específica:

| Módulo | Descripción | Enlace |
|------|------------|-------|
| Portal Web | Interfaz principal del sistema | https://github.com/DiegoGuzman1999/InventarySoft-portal |
| Aplicación Cliente | Aplicación frontend del sistema | https://github.com/DiegoGuzman1999/InventarySoft-App |
| API Backend | Servicios y lógica de negocio | https://github.com/DiegoGuzman1999/InventarySoft-Api |
| Repositorio Principal | Gestión del proyecto y documentación | https://github.com/DiegoGuzman1999/InventarySoft |

---
## Gestión Ágil del Proyecto

El seguimiento de actividades, planificación de Sprints y control del backlog se realiza mediante la herramienta Jira.

Acceso al tablero oficial del proyecto:
[InventarySoft – SCRUM Board](https://inventarysoft.atlassian.net/jira/software/projects/IDT2/boards/34/backlog)


---
## Arquitectura de microservicios (flujo de peticiones)

```
Frontend (8084)  →  API Gateway (8082)  →  Inventory Service (8081)  →  PostgreSQL (5432)
     │                      │                         │
     │  /api/products       │  /api/products/**       │  /api/products
     └─────────────────────┴─────────────────────────┴──────────────────
```

- **Frontend** solo conoce la URL del Gateway (`http://localhost:8082/api/products`).
- **Gateway** enruta `/api/products/**` al inventory-service en 8081.
- **Inventory-service** expone la API REST y usa PostgreSQL (puerto 5432; pgAdmin en 5050 si usas Docker).

---
## Cómo levantar los servicios (monorepo)

Desde la raíz `InventarySoft`:

| Servicio           | Puerto | Comando (desde la carpeta indicada) |
|--------------------|--------|-------------------------------------|
| PostgreSQL (Docker)| 5432   | `docker compose up -d` (opcional: perfil `dev` en inventory usa H2) |
| inventory-service  | 8081   | `InventarySoft\inventory-service` → `.\mvnw.cmd clean spring-boot:run` |
| api-gateway        | 8082   | `InventarySoft\api-gateway` → `.\mvnw.cmd clean spring-boot:run` |
| frontend-landing   | 8084   | `InventarySoft\frontend-landing` → `.\mvnw.cmd clean spring-boot:run` |

**Orden:** 1) Base de datos (o perfil `dev`), 2) inventory-service, 3) api-gateway, 4) frontend-landing.

---
## 📌 Notas

Este repositorio actúa como punto central de documentación, organización y referencia para todos los componentes del sistema InventorySoft.

