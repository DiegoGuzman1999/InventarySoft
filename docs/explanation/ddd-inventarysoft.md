# DDD — Visión general de InventarySoft

Este documento describe la organización del dominio de **InventarySoft** bajo **Domain-Driven Design (DDD)** a nivel de **contextos delimitados (bounded contexts)**. El objetivo es separar responsabilidades, reducir acoplamiento y alinear el modelo de negocio con la arquitectura distribuida del sistema.

---

## 1. Contexto general

InventarySoft es una plataforma orientada a la gestión comercial y de inventario. El dominio se modela como un **sistema distribuido** compuesto por varios **bounded contexts** que colaboran mediante contratos explícitos (APIs REST) y, cuando aplica, integraciones coordinadas por el **API Gateway**.

Cada contexto tiene:

- **Lenguaje ubicuo** propio (términos y reglas coherentes dentro del límite).
- **Modelo de dominio** independiente en su implementación.
- **Interfaces de integración** hacia otros contextos (no exposición directa del modelo interno).

---

## 2. Bounded contexts

### 2.1 Inventario

**Propósito:** Gestionar el catálogo de productos, existencias, movimientos y políticas que afectan al stock disponible.

**Responsabilidades típicas:**

- Alta, baja y modificación de productos (según reglas de negocio).
- Consulta de stock y disponibilidad.
- Registro de movimientos de inventario (entradas, salidas, ajustes).

**Relación con otros contextos:**

- **Ventas** consume información de disponibilidad y puede disparar descuentos de stock al confirmar una venta.
- **Autenticación / Seguridad** asegura que solo operadores autorizados ejecuten operaciones sensibles.

**Microservicio asociado (referencia):** `inventory-service` (repositorio dedicado).

---

### 2.2 Ventas

**Propósito:** Gestionar el ciclo de ventas: transacciones, ítems de venta, totales y reglas comerciales asociadas a la operación.

**Responsabilidades típicas:**

- Creación y gestión de ventas (pedidos, transacciones).
- Cálculo de totales, impuestos o descuentos según políticas definidas.
- Coordinación con inventario para **validar** y **actualizar** stock al concretar ventas.

**Relación con otros contextos:**

- **Inventario** es consultado para validar existencias antes de confirmar.
- **Autenticación / Seguridad** identifica al vendedor o rol que registra la venta.

**Microservicio asociado (referencia):** `sales-service` (repositorio dedicado).

---

### 2.3 Autenticación y seguridad

**Propósito:** Gestionar identidad, autenticación, autorización y políticas de acceso a recursos del sistema.

**Responsabilidades típicas:**

- Registro e inicio de sesión (según diseño).
- Emisión y validación de credenciales (por ejemplo, tokens).
- Autorización por roles o permisos sobre operaciones de inventario y ventas.

**Relación con otros contextos:**

- **Inventario** y **Ventas** confían en este contexto para **validar** quién puede ejecutar cada operación.

**Microservicio asociado (referencia):** `auth-service` (repositorio dedicado).

---

## 3. Integración entre contextos

La comunicación entre bounded contexts **no debe acoplar modelos internos**. Se recomienda:

- **Contratos API** versionados y estables.
- **DTOs** en los límites del servicio.
- **API Gateway** como punto de entrada único hacia los clientes (ver ADR-002).

Flujo lógico de alto nivel:

`Cliente → Frontend → API Gateway → Microservicio de dominio → PostgreSQL`

---

## 4. Relación con otros documentos

- Decisiones de arquitectura: carpeta `Docs/adr/`.
- Arquitectura general del sistema: `Docs/explanation/architecture.md` (si aplica).

---

*Documento de consulta — DDD y bounded contexts — InventarySoft*
