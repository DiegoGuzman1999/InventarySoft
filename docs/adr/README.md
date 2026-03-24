# Registro de Decisiones de Arquitectura (ADR)

Este directorio contiene los **Architecture Decision Records (ADR)** del ecosistema InventarySoft. Cada registro deja constancia de **por qué** se tomó una decisión técnica relevante y **qué implica** mantenerla.

---

## Qué debe responder cada ADR

Todo ADR debe contestar, de forma explícita:

1. **Cuál era el problema** (o la necesidad de negocio/técnica que obligaba a decidir).
2. **Qué opciones existían** (al menos dos alternativas razonables).
3. **Cuál opción se eligió** (la decisión concreta).
4. **Qué ganamos** (beneficios esperados o ya observados).
5. **Qué costo aceptamos** (trade-offs, deuda, complejidad, riesgos asumidos).

Opcional pero recomendable: estado (borrador / aceptado / deprecado), fecha y referencias a otros documentos o repos.

---

## Cuándo escribir un ADR

Crear un ADR cuando, entre otros casos:

- Se **cambia la estructura del código** o el estilo arquitectónico del sistema.
- Se **decide separar un módulo** a **otro repositorio** (o fusionar repos).
- Se introduce **API Gateway**, **orquestación** (por ejemplo Kubernetes), **nuevas políticas por entorno** (Main / QA / Dev) o cambios de seguridad en el borde.
- Se adopta o abandona un patrón transversal (microservicios, eventos, CQRS, etc.).

No hace falta un ADR por cada commit pequeño; sí cuando la decisión **afecta a varios equipos**, al **despliegue** o al **contrato** entre componentes.

---

## Índice

| ID | Título |
|----|--------|
| [ADR-001](001-microservicios.md) | Adoptar arquitectura de microservicios |
| [ADR-002](002-api-gateway.md) | Usar API Gateway como punto de entrada único |
| [ADR-003](003-repositorios-por-dominio.md) | Separar repositorios por dominio / microservicio |

---

Mantener la numeración correlativa para nuevas decisiones (ADR-004, ADR-005, …).
