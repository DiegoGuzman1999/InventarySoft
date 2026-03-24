# InventarySoft

InventarySoft es una plataforma de software web orientada a la gestión integral de procesos administrativos, ventas e inventarios para organizaciones comerciales.

El proyecto se desarrolla bajo metodología ágil **Scrum**, con arquitectura modular basada en **microservicios** y repositorios independientes.

---

## 🎯 Objetivo del Proyecto

Diseñar e implementar una solución web escalable que permita:

- Gestión de inventarios
- Control de ventas
- Administración de procesos internos
- Separación clara entre frontend, backends y base de datos

---

## 🧩 Componentes, tecnología y puertos por entorno

Cada servicio expone su API en un puerto distinto según el **entorno** (para evitar conflictos al levantar varios a la vez en la misma máquina).

| Componente | Rol | Tecnología | **Main** | **QA** | **Dev** |
|------------|-----|------------|:--------:|:------:|:-------:|
| **Micro-1** | Backend (Carrito) | Java / Spring Boot | `8080` | `8081` | `8082` |
| **Micro-2** | Backend (Users) | Node.js / Express | `9000` | `9001` | `9002` |
| **Micro-3** | Backend (Products) | Python / FastAPI | `8000` | `8001` | `8002` |
| **Base de datos** | Persistencia | PostgreSQL | `5432` | `5433` | `5434` |
| **Front-end** | Interfaz web | React / Tailwind | `8081` | `8082` | *(definir)* |

> **Nota:** Los puertos del front-end pueden alinearse con la convención del equipo (por ejemplo `3000` / `3001` / `3002` si usan Vite o Create React App). Ajusta la última columna cuando la tengan cerrada.

### Cómo leer esta tabla

- **Main:** entorno de integración o “producción” de pruebas.
- **QA:** entorno donde QA valida antes de subir a Main.
- **Dev:** entorno local de desarrollo del equipo.

Ejemplo de URL local en Dev para el carrito: `http://localhost:8082` (Micro-1 Dev).

---

## 🧱 Repositorios del ecosistema

| Módulo | Descripción | Enlace |
|--------|-------------|--------|
| Portal Web | Interfaz principal del sistema | [InventarySoft-portal](https://github.com/DiegoGuzman1999/InventarySoft-portal) |
| Aplicación Cliente | Frontend del sistema | [InventarySoft-App](https://github.com/DiegoGuzman1999/InventarySoft-App) |
| API Backend | Servicios y lógica de negocio | [InventarySoft-Api](https://github.com/DiegoGuzman1999/InventarySoft-Api) |
| Repositorio principal | Documentación y coordinación | [InventarySoft](https://github.com/DiegoGuzman1999/InventarySoft) |

---

## Gestión ágil del proyecto

El seguimiento de actividades, planificación de sprints y backlog se realiza en **Jira**:

[InventarySoft – SCRUM Board](https://inventarysoft.atlassian.net/jira/software/projects/SCRUM/boards/1/backlog)

---

## Flujo lógico (alto nivel)

```
Front-end (React)  →  API Gateway / BFF (opcional)  →  Microservicios (Carrito, Users, Products)  →  PostgreSQL
```

Cada microservicio se despliega de forma independiente; los puertos concretos por entorno están en la tabla superior.

---

## 📚 Documentación en este repositorio

En la carpeta **`Docs/`** encontrarás (entre otros):

- Arquitectura y diagramas (`Docs/explanation/`)
- Rúbricas y referencia (`Docs/reference/`)
- Prototipo del portal (`Docs/portal-prototype/`)
- Retrospectivas (`Docs/retrospectives/`)

---

## 📌 Notas

Este repositorio es el **punto central de documentación** y referencia del ecosistema InventarySoft. El código de cada microservicio vive en sus repositorios correspondientes; aquí se describe la visión global, componentes y convenciones.
