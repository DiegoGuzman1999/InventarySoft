<!--
CONFIG
FULL_NAME: Diego Armando Penagos Guzman
GITHUB_USER: DiegoGuzman
EMAIL: diegopenagos984@gmail.com
CODE_ORGANIZATION: code-corhuila 
-->




# 📦 InventarySoft

## 🚀 Introducción
InventarySoft es una plataforma de software empresarial distribuida, enfocada en la gestión de inventario, ventas y control de acceso. La solución evolucionó desde una arquitectura monolítica hacia una arquitectura basada en microservicios para mejorar el despliegue independiente, la mantenibilidad y la escalabilidad horizontal.

La plataforma está diseñada con servicios modulares, límites claros por dominio y enrutamiento centralizado mediante el patrón API Gateway. Este enfoque permite crecer de forma controlada y reducir el acoplamiento entre componentes.

El proyecto se desarrolla bajo metodología ágil Scrum, con arquitectura modular basada en microservicios y repositorios independientes.

## 🎯 Objetivo del Proyecto
Diseñar y estructurar una solución web escalable que permita:

- 📦 Gestión de inventarios
- 💰 Control de ventas
- 🏢 Administración de procesos internos
- 🔗 Separación clara entre frontend, backend y base de datos
- ⚙️ Organización del ecosistema bajo arquitectura de microservicios

## 🧠 Visión de Arquitectura
InventarySoft se implementa como un sistema distribuido con los siguientes principios:

- 🧩 Arquitectura de microservicios
- 🚪 API Gateway como punto de entrada unificado
- 🛠️ Servicios orientados a dominio para inventario, ventas y autenticación
- 🗄️ Persistencia relacional con acceso controlado por servicio
- 🔄 Separación de responsabilidades entre frontend, gateway, servicios y base de datos

## 🧱 Componentes y responsabilidades

| Componente | Responsabilidad |
|---|---|
| Frontend | Interfaz de usuario y experiencia web |
| API Gateway | Enrutamiento de solicitudes y capa de acceso centralizada |
| Inventory Service | Gestión de inventario y catálogo de productos |
| Sales Service | Gestión de ventas y flujos transaccionales |
| Auth Service | Autenticación y autorización |
| PostgreSQL | Persistencia relacional del sistema |

## 🌐 Puertos por entorno

### 🗄️ Base de datos

| Componente | Main | QA | Dev |
|---|---:|---:|---:|
| PostgreSQL | localhost:5432 | localhost:5433 | localhost:5434 |

### ⚙️ Microservicios

| Servicio | Main | QA | Dev |
|---|---|---|---|
| Inventory Service | http://localhost:8081 | http://localhost:8082 | http://localhost:8083 |
| Sales Service | http://localhost:9000 | http://localhost:9001 | http://localhost:9002 |
| Auth Service | http://localhost:8888 | http://localhost:8889 | http://localhost:8890 |

### 🚪 API Gateway

| Entorno | URL |
|---|---|
| Main | http://localhost:8000 |
| QA | http://localhost:8001 |
| Dev | http://localhost:8002 |

### 💻 Frontend

| Entorno | URL |
|---|---|
| Main | http://localhost:80 |
| QA | http://localhost:81 |
| Dev | http://localhost:82 |

## 🔗 URLs locales por entorno

| Entorno | Frontend | API Gateway | Inventory | Sales | Auth | PostgreSQL |
|---|---|---|---|---|---|---|
| Main | http://localhost:80 | http://localhost:8000 | http://localhost:8081 | http://localhost:9000 | http://localhost:8888 | localhost:5432 |
| QA | http://localhost:81 | http://localhost:8001 | http://localhost:8082 | http://localhost:9001 | http://localhost:8889 | localhost:5433 |
| Dev | http://localhost:82 | http://localhost:8002 | http://localhost:8083 | http://localhost:9002 | http://localhost:8890 | localhost:5434 |

## 🔄 Flujo de solicitudes

Flujo general del sistema:

`Usuario → Frontend → API Gateway → Microservicios → Base de Datos`

### 📌 Descripción del flujo
1. El usuario interactúa con el Frontend.
2. El Frontend envía solicitudes HTTP al API Gateway.
3. El API Gateway enruta cada solicitud al microservicio correspondiente.
4. El microservicio ejecuta la lógica de negocio.
5. Los datos se consultan o persisten en PostgreSQL.
6. La respuesta retorna al Frontend a través del mismo flujo.

## 📋 Modelo de trabajo Scrum
InventarySoft se desarrolla con Scrum para asegurar entregas iterativas, visibilidad para los interesados y control del alcance.

- 🧭 Épicas para objetivos de alto nivel
- 📝 Historias de usuario para incrementos funcionales
- ⏱️ Sprints para organización del desarrollo y validación
- 📊 Jira para planificación, seguimiento y trazabilidad

## 🗂️ Topología de repositorios
La plataforma está compuesta por múltiples repositorios con límites de responsabilidad definidos:

- `inventory-service`
- `sales-service`
- `auth-service`
- `gateway-service`
- `inventorysoft-database`
- `frontend`

Este repositorio funciona como punto central de documentación y referencia de arquitectura del ecosistema InventarySoft.

## 🔗 Repositorios oficiales del ecosistema
Para implementación y despliegue de los componentes, consultar los repositorios oficiales:

- Sales Service: `DiegoGuzman1999/Sales-service`
- Inventory Service: `DiegoGuzman1999/Inventory-service`
- Auth Service: `DiegoGuzman1999/Auth-service`
- Database: `DiegoGuzman1999/inventorysoft-database`
- Frontend: `DiegoGuzman1999/InventarySoft-frontend`
- Gateway Service: `DiegoGuzman1999/Gateway-Service`

## 📚 Alcance de la documentación
Este repositorio contiene:

- 🏗️ Documentación de arquitectura y diseño técnico
- 📖 Documentación de referencia y evidencias de evaluación
- 🖼️ Prototipos del portal y diagramas
- 🗓️ Documentación de sprints y retrospectivas

Los detalles de implementación y el código ejecutable se mantienen en los repositorios específicos de cada servicio.

## 📁 Estructura de documentación
En la carpeta `Docs/` se encuentra, entre otros, el siguiente contenido:

- `Docs/explanation/` → arquitectura y diagramas
- `Docs/reference/` → documentos de referencia y evaluación
- `Docs/portal-prototype/` → prototipo del portal
- `Docs/retrospectives/` → retrospectivas y evidencias de sprint

---

## Gestión ágil del proyecto

El seguimiento de actividades, planificación de sprints y backlog se realiza en **Jira**:

[InventarySoft – SCRUM Board](https://inventarysoft.atlassian.net/jira/software/projects/SCRUM/boards/1/backlog)


## 📌 Notas
Este repositorio es el punto central de documentación y referencia del ecosistema InventarySoft. El código de cada microservicio vive en sus repositorios correspondientes; aquí se describe la visión global, componentes y convenciones del sistema.
