# Diagrama del Sistema  
## Sistema Web de Control Administrativo, Ventas e Inventario

---

## 1. Introducción al diagrama del sistema

El presente documento describe de forma clara y estructurada el **diagrama conceptual del sistema web de Control Administrativo, Ventas e Inventario**, desarrollado como proyecto académico bajo la metodología **Scrum**.

El objetivo principal es proporcionar al **Product Owner (PO)** y al equipo de **QA** una visión de alto nivel sobre:

- Actores que interactúan con el sistema  
- Arquitectura general (capas y componentes)  
- Flujos de interacción principales  
- Stack tecnológico propuesto y consideraciones técnicas clave  

Este documento se centra en el **diseño y la documentación del sistema**, no en el detalle de implementación ni en el código.

---

## 2. Objetivo del diagrama del sistema

El diagrama del sistema tiene como objetivo:

- Definir el alcance funcional a alto nivel del sistema de Control Administrativo, Ventas e Inventario.  
- Representar la arquitectura general (frontend, backend y base de datos) y sus interacciones.  
- Identificar los actores principales y la forma en que se comunican con el sistema.  
- Facilitar la validación por parte del Product Owner respecto al funcionamiento esperado del sistema.  
- Proveer a QA de una base clara para revisar coherencia, estructura y posibles escenarios de prueba a nivel de sistema.

---

## 3. Actores principales del sistema

### Product Owner (PO)
- Valida el alcance funcional del sistema.  
- Revisa que la solución cumpla los objetivos de negocio y académicos.  
- No es un usuario operativo diario, pero puede acceder a reportes y paneles de alto nivel.

### Administrador del Sistema
- Gestiona la configuración global (usuarios, roles, parámetros del sistema).  
- Administra catálogos maestros (productos, categorías, sucursales, impuestos, etc.).  
- Supervisa indicadores generales de ventas e inventario.

### Vendedor / Cajero
- Registra ventas y facturas.  
- Consulta disponibilidad de inventario en tiempo real.  
- Gestiona devoluciones simples y emisión de comprobantes.

### Encargado de Almacén / Inventario
- Registra entradas y salidas de mercancía.  
- Actualiza conteos de inventario y realiza ajustes.  
- Consulta existencias por producto, categoría y ubicación.

### Supervisor / Gerente
- Accede a reportes consolidados de ventas e inventario.  
- Monitorea el desempeño de sucursales y vendedores.  
- Autoriza ajustes de inventario sensibles o descuentos especiales.

### Cliente Final (opcional)
- Puede ser registrado en el sistema para asociar ventas y generar historial de compras.  
- No interactúa directamente con la interfaz web (es atendido por el vendedor).

### Equipo de Desarrollo (Dev) y QA
- **Dev:** utiliza el sistema en entornos de desarrollo y pruebas para validar la implementación.  
- **QA:** diseña casos de prueba a partir de esta documentación y verifica la coherencia entre lo documentado y la funcionalidad real.

---

## 4. Descripción de la arquitectura general

La arquitectura del sistema se basa en un **modelo de tres capas**:

### 4.1 Capa de Presentación (Frontend Web)
- Ejecutada en el navegador del usuario.  
- Proporciona la interfaz gráfica para todos los roles.  
- Se comunica exclusivamente con el backend mediante **APIs REST (HTTP/JSON)**.

### 4.2 Capa de Lógica de Negocio (Backend / API)
- Implementa las reglas de negocio del sistema.  
- Expone endpoints REST para autenticación, ventas, inventario y reportes.  
- Valida datos, gestiona seguridad y orquesta el acceso a la base de datos.

### 4.3 Capa de Datos (Base de Datos Relacional)
- Almacena información estructurada del sistema.  
- Garantiza integridad referencial y consistencia de transacciones.  
- Soporta consultas de reportes y análisis.

### Vista lógica de alto nivel
- Los usuarios acceden al sistema desde un navegador web.  
- El Frontend consume servicios del Backend vía API REST.  
- El Backend persiste y consulta información en la Base de Datos.  
- Opcionalmente se integran servicios externos (correo electrónico, notificaciones).

---

## 5. Componentes principales del sistema

### 5.1 Frontend (Capa de Presentación)

**Responsabilidades:**
- Interfaz web responsiva por rol.  
- Validaciones básicas del lado del cliente.  
- Visualización de tablas, reportes y gráficos.  
- Gestión de sesión y token de autenticación.

**Stack tecnológico propuesto:**
- Lenguajes: HTML5, CSS3, TypeScript  
- Framework: React o Vue.js  
- UI: Bootstrap, Tailwind CSS o similar  
- Consumo de API: Fetch API o Axios

---

### 5.2 Backend (Capa de Lógica de Negocio / API REST)

**Responsabilidades:**
- Autenticación y autorización de usuarios.  
- Gestión de productos, ventas e inventario.  
- Aplicación de reglas de negocio y permisos por rol.  
- Gestión de transacciones y consistencia de datos.

**Stack tecnológico propuesto:**
- Plataforma: Node.js  
- Framework: Express.js o NestJS  
- Estilo de API: REST (JSON sobre HTTP/HTTPS)  
- Autenticación: JWT o cookies seguras  
- Configuración: variables de entorno (.env)

---

### 5.3 Base de Datos (Capa de Datos)

**Responsabilidades:**
- Almacenamiento de datos maestros y transaccionales.  
- Soporte a consultas de negocio y reportes.  
- Mantenimiento de relaciones entre entidades.

**Stack tecnológico propuesto:**
- Motor: PostgreSQL o MySQL/MariaDB  

**Modelo de datos (alto nivel):**
- Usuarios y roles  
- Productos, categorías y proveedores  
- Inventario por producto y ubicación  
- Ventas y detalle de ventas  
- Movimientos de inventario

---

## 6. Flujo general de interacción del sistema

### 6.1 Flujo de autenticación
1. El usuario accede al sistema desde un navegador.  
2. El Frontend muestra la pantalla de login.  
3. Se envía `POST /auth/login` al Backend.  
4. El Backend valida credenciales y genera un token.  
5. El Frontend almacena el token y redirige según el rol.  
6. El token se envía en cada petición posterior.

---

### 6.2 Flujo general de venta
1. El Vendedor accede al módulo de Ventas.  
2. El Frontend consulta productos e inventario.  
3. El Vendedor registra productos y cantidades.  
4. Se envía `POST /ventas` al Backend.  
5. El Backend valida stock, registra la venta y actualiza inventario.  
6. Se devuelve confirmación y comprobante.

---

### 6.3 Flujo de gestión de inventario
1. El Encargado accede al módulo de Inventario.  
2. Consulta existencias actuales.  
3. Registra entradas, salidas o ajustes.  
4. El Backend valida y actualiza existencias.  
5. Se confirma la operación.

---

### 6.4 Flujo de consulta de reportes
1. Supervisor o Administrador accede a Reportes.  
2. El Frontend solicita datos agregados al Backend.  
3. El Backend procesa consultas y responde.  
4. El Frontend muestra tablas, gráficos y KPIs.

---

## 7. Consideraciones técnicas

### 7.1 Seguridad
- Autenticación y autorización por roles.  
- Uso de HTTPS en producción.  
- Almacenamiento seguro de contraseñas (hash + salt).

### 7.2 Rendimiento y escalabilidad
- Endpoints eficientes.  
- Paginación y filtros en listados.  
- Índices en tablas críticas.

### 7.3 Mantenibilidad
- Separación clara de responsabilidades.  
- Estructura organizada de carpetas y capas.  
- Uso de control de versiones con Git.

### 7.4 Calidad y pruebas
- QA deriva casos de prueba desde este documento.  
- Pruebas funcionales básicas en endpoints críticos.  
- Pruebas manuales por rol.

### 7.5 Despliegue y entornos
- **Desarrollo:** trabajo del equipo Dev.  
- **Pruebas:** validaciones de QA y revisión del PO.  

**Opciones de despliegue (conceptual):**
- Backend en servidor o PaaS Node.js.  
- Base de datos gestionada o dedicada.  
- Frontend como aplicación web estática.

---

Este documento proporciona una **visión global y coherente** del sistema Web de Control Administrativo, Ventas e Inventario, y queda listo para la **validación del Product Owner** y la **revisión de QA**.

---

*Documento de Arquitectura de Software — InventorySoft*  
*Versión 1.0 — Arquitectura Cliente–Servidor REST*
