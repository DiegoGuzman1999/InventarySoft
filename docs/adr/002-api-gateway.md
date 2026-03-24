# ADR-002: Usar API Gateway como punto de entrada único

## Estado

Aceptado

---

## Qué debe responder este ADR

Definir cómo los clientes (por ejemplo el frontend) acceden a los microservicios **sin acoplarse** a la topología interna ni a múltiples URLs base.

---

## 1. Cuál era el problema

- Varios **microservicios** exponen APIs en **puertos y rutas distintos**; el cliente tendría que conocer cada uno.
- Cambios de despliegue (IP, puerto, balanceo) **romperían** configuraciones del frontend si están hardcodeadas por servicio.
- Políticas transversales (entrada única, enrutamiento, límites por entorno Main / QA / Dev) **se dispersan** si cada cliente habla directo con cada backend.

---

## 2. Qué opciones existían

| Opción | Descripción breve |
|--------|-------------------|
| **A. Llamadas directas** | El frontend invoca cada microservicio por su URL/puerto propio. |
| **B. BFF (Backend for Frontend)** | Un backend específico por tipo de cliente que agrega datos y simplifica el contrato. |
| **C. API Gateway** | Un componente que recibe todo el tráfico del cliente y **enruta** a los microservicios internos. |

---

## 3. Cuál opción se eligió

**Opción C: API Gateway** como **punto de entrada único** para las solicitudes HTTP desde los clientes hacia los microservicios de dominio (Inventario, Ventas, Autenticación).

---

## 4. Qué ganamos

- **Desacoplamiento**: el cliente conoce principalmente la URL del gateway, no la matriz interna de servicios.
- **Enrutamiento centralizado** y gobernanza de rutas (prefijos, versiones, reglas por entorno).
- Base para **políticas en el borde** (por ejemplo rate limiting, autenticación en entrada, según diseño).
- **Menor fricción** al cambiar ubicación interna de un servicio si el contrato público se mantiene en el gateway.

---

## 5. Qué costo aceptamos

- El gateway puede ser un **punto crítico** si no hay alta disponibilidad o redundancia.
- **Latencia adicional** por un salto más en la cadena (normalmente asumible).
- **Operación y configuración** del gateway (rutas, timeouts, reintentos) deben mantenerse al día con los servicios.
- Riesgo de convertir el gateway en un **“monolito de orquestación”** si se mete demasiada lógica de negocio ahí (debe limitarse a tránsito y políticas transversales).

---

## Referencias

- `README.md` (tabla de puertos y flujo de solicitudes)
- `Docs/explanation/ddd-inventarysoft.md`
