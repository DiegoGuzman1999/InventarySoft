# ADR-001: Adoptar arquitectura de microservicios

## Estado

Aceptado

---

## Qué debe responder este ADR

Documentar por qué InventarySoft deja de concentrar la lógica en un único despliegue monolítico y pasa a **servicios independientes alineados con dominio (DDD)**.

---

## 1. Cuál era el problema

- Un **monolito único** dificultaba que inventario, ventas y autenticación evolucionaran **en paralelo** sin pisarse.
- Los **ciclos de despliegue** estaban acoplados: un cambio en un área implicaba redeploy de todo.
- La **escalabilidad** y el aislamiento de fallos por capacidad de negocio eran limitados.
- El modelo de dominio mezclado en un solo código base dificultaba mantener **límites claros** entre bounded contexts.

---

## 2. Qué opciones existían

| Opción | Descripción breve |
|--------|-------------------|
| **A. Monolito modular** | Un solo repositorio y un solo despliegue, con paquetes o módulos internos por dominio. |
| **B. Microservicios** | Varios servicios desplegables por separado, cada uno acotado a un contexto (Inventario, Ventas, Autenticación). |
| **C. Macroservicios / “few services”** | Pocos servicios grandes (por ejemplo, “backoffice” vs “público”), menos granularidad que B. |

---

## 3. Cuál opción se eligió

**Opción B: arquitectura basada en microservicios**, donde cada servicio principal representa un **bounded context** (Inventario, Ventas, Autenticación / Seguridad), con ciclo de vida y despliegue propios.

---

## 4. Qué ganamos

- **Despliegue y escalado independiente** por dominio.
- **Equipos** pueden avanzar con más autonomía sobre su servicio.
- **Alineación con DDD**: un contexto delimitado por servicio facilita lenguaje ubicuo y modelos más cohesionados.
- **Aislamiento de fallos** mejorado respecto a un monolito único (si se opera y diseña bien).

---

## 5. Qué costo aceptamos

- **Complejidad operativa**: más procesos, más endpoints, más piezas que monitorear y versionar.
- **Consistencia distribuida**: transacciones entre contextos requieren diseño explícito (sagas, idempotencia, compensación, etc., según el caso).
- **Contratos entre servicios**: obliga a **APIs estables**, documentación y comunicación entre equipos.
- **Latencia y red**: más saltos de red entre componentes frente a llamadas in-process en un monolito.

---

## Referencias

- `Docs/explanation/ddd-inventarysoft.md`
