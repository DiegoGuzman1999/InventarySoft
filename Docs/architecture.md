# Documento de Arquitectura de Software — InventorySoft

---

## 1. Introducción

InventorySoft es un sistema administrativo web modular orientado a la gestión de inventarios en contextos empresariales. El presente documento describe la arquitectura del sistema, sus componentes, interacciones y decisiones técnicas que sustentan su diseño.

El sistema se concibe como una solución distribuida compuesta por múltiples repositorios independientes que colaboran bajo una arquitectura Cliente–Servidor basada en principios REST. El desarrollo se realiza siguiendo metodología ágil Scrum, lo que permite iteraciones incrementales y entrega de valor continuo.

El alcance de este documento abarca la visión general de la arquitectura, el estilo arquitectónico adoptado, la descripción de cada componente, el flujo de datos, los protocolos de comunicación, la seguridad, la escalabilidad y las ventajas que esta arquitectura aporta al proyecto.

---

## 2. Visión General de la Arquitectura

InventorySoft se organiza en cuatro unidades lógicas principales: el **Portal Web Administrativo** (frontend), la **Aplicación Cliente**, la **Backend API** y la **Base de Datos**. La comunicación entre cliente y servidor se realiza mediante HTTP/HTTPS, con intercambio de información en formato JSON.

La separación en repositorios independientes permite equipos paralelos, despliegues aislados y evolución tecnológica por componente sin acoplar el resto del sistema. La API actúa como único punto de acceso a la lógica de negocio y a los datos, garantizando consistencia y trazabilidad.

A continuación se presenta un diagrama de arquitectura en formato ASCII que ilustra la disposición de los componentes y sus relaciones.

```
┌──────────────────────────────────────────────────────────────┐
│                       CAPA DE CLIENTES                       │
└──────────────────────────────────────────────────────────────┘
            │                                   │
┌───────────▼───────────┐           ┌──────────▼───────────┐
│ PORTAL WEB ADMIN      │           │ APLICACIÓN CLIENTE   │
│ (Frontend Web)        │           │ (Cliente Final)      │
│ • Administración      │           │ • Consultas          │
│ • Gestión inventario  │           │ • Operaciones básicas│
│ • Reportes            │           │ • UI simplificada    │
│ • Configuración       │           │ • Consumo de API     │
└───────────┬───────────┘           └──────────┬───────────┘
            │                                  │
            └────────── HTTP / HTTPS (JSON) ────┘
                            │
                    ┌───────▼───────┐
                    │   RED /       │
                    │   INTERNET    │
                    └───────┬───────┘
                            │
┌──────────────────────────────────────────────────────────────┐
│                       CAPA DE SERVIDOR                       │
└──────────────────────────────────────────────────────────────┘
                            │
┌───────────────────────────▼──────────────────────────────────┐
│                     BACKEND API (REST)                       │
│ • Autenticación y autorización                               │
│ • Lógica de negocio                                          │
│ • Validación de datos                                        │
│ • Exposición de servicios REST                               │
│ • Orquestación de procesos                                   │
└───────────────────────────┬──────────────────────────────────┘
                            │
┌───────────────────────────▼──────────────────────────────────┐
│                        BASE DE DATOS                         │
│ • Persistencia de datos                                      │
│ • Integridad referencial                                     │
│ • Transacciones                                              │
│ • Entidades del sistema                                      │
└──────────────────────────────────────────────────────────────┘
```

---

## 3. Estilo y Patrón Arquitectónico

### 3.1 Estilo arquitectónico

El sistema adopta una **arquitectura en capas** combinada con el estilo **Cliente–Servidor** y el paradigma **REST** para la API. Esta combinación permite separar responsabilidades entre presentación, lógica de negocio y persistencia.

- **Cliente–Servidor:** Los clientes (Portal y Aplicación Cliente) solicitan recursos y operaciones al servidor (Backend API), que centraliza la lógica y el acceso a datos.
- **REST (Representational State Transfer):** La API expone recursos identificables por URI, operados mediante verbos HTTP estándar (GET, POST, PUT, PATCH, DELETE), sin estado en el servidor entre peticiones.
- **Capas:** En el servidor se distinguen capa de presentación (endpoints), capa de aplicación (casos de uso y orquestación), y capa de acceso a datos.

### 3.2 Justificación

La elección de una API REST sobre HTTP/HTTPS facilita la interoperabilidad, el uso de herramientas estándar (proxies, cachés, balanceadores) y la integración con distintos tipos de clientes (web, móvil, terceros). La separación en repositorios y la ausencia de estado en el servidor favorecen la escalabilidad horizontal y la mantenibilidad a largo plazo.

---

## 4. Componentes del Sistema

### 4.1 Frontend — Portal Web Administrativo

**Responsabilidades:**

- Ofrecer la interfaz de usuario para administradores y operadores del sistema.
- Presentar pantallas de gestión de inventario, reportes, configuración y administración de usuarios o parámetros.
- Consumir la Backend API mediante peticiones HTTP para obtener y enviar datos en JSON.
- Gestionar sesión y credenciales en el cliente (por ejemplo, tokens) y enviarlas en las peticiones autorizadas.
- Validar datos en el cliente para mejorar la experiencia de usuario, sin sustituir la validación del servidor.

**Interacción:** El Portal se ejecuta en el navegador, se comunica exclusivamente con la Backend API y no accede directamente a la base de datos. Toda la lógica de negocio y persistencia reside en el servidor.

### 4.2 Aplicación Cliente

**Responsabilidades:**

- Atender a usuarios finales (por ejemplo, consulta de stock, registro de movimientos o solicitudes).
- Proporcionar una interfaz adaptada a las necesidades del cliente final, posiblemente más acotada que la del Portal administrativo.
- Consumir los mismos o un subconjunto de recursos expuestos por la Backend API.
- Mantener una experiencia de uso coherente con las políticas de seguridad y flujos definidos por la API.

**Interacción:** Al igual que el Portal, la Aplicación Cliente se comunica solo con la Backend API mediante HTTP/HTTPS y JSON, garantizando un único punto de verdad para reglas de negocio y datos.

### 4.3 Backend API

**Responsabilidades:**

- Exponer recursos y operaciones mediante una API REST sobre HTTP/HTTPS.
- Implementar la lógica de negocio, reglas de validación y orquestación de operaciones.
- Gestionar autenticación (identificación de usuarios/sistemas) y autorización (permisos por recurso y acción).
- Realizar el acceso a la base de datos (lecturas, escrituras, transacciones) y ocultar detalles de persistencia a los clientes.
- Garantizar respuestas coherentes (códigos HTTP, formato JSON) y manejo de errores estandarizado.

**Interacción:** Recibe peticiones del Portal y de la Aplicación Cliente, valida y procesa las solicitudes, y utiliza la Base de Datos como único almacén persistente. No expone detalles internos de implementación ni esquema de base de datos.

### 4.4 Base de Datos

**Responsabilidades:**

- Almacenar de forma persistente las entidades del dominio (productos, movimientos, usuarios, configuración, etc.).
- Asegurar integridad referencial, consistencia transaccional y disponibilidad dentro del entorno de despliegue.
- Soportar consultas y actualizaciones demandadas por la Backend API con rendimiento adecuado.

**Interacción:** Es accedida exclusivamente por la Backend API. Los clientes no tienen conexión directa a la base de datos; todo el acceso se realiza a través de la API.

---

## 5. Descripción del Flujo de Datos

1. **Inicio de operación:** El usuario interactúa con el Portal Administrativo o con la Aplicación Cliente desde el navegador o dispositivo.
2. **Petición:** El cliente construye una petición HTTP (por ejemplo, GET para consulta, POST para creación) hacia la URL del recurso en la Backend API, incluyendo cabeceras (autorización, contenido) y cuerpo en JSON cuando aplique.
3. **Recepción y validación:** La API recibe la petición, valida autenticación y autorización, y comprueba formato y reglas de negocio.
4. **Procesamiento:** Si la petición es válida, la API ejecuta la lógica de negocio y, si corresponde, realiza lecturas o escrituras en la Base de Datos.
5. **Respuesta:** La API devuelve una respuesta HTTP con código de estado apropiado (200, 201, 400, 401, 404, 500, etc.) y un cuerpo en JSON con datos o mensajes de error normalizados.
6. **Actualización de la interfaz:** El cliente (Portal o Aplicación Cliente) interpreta la respuesta y actualiza la interfaz o notifica al usuario.

Los flujos de datos son unidireccionales en cada petición: cliente → API → Base de Datos (en lecturas/escrituras) y Base de Datos → API → cliente (en respuestas). No existe flujo directo cliente–base de datos.

---

## 6. Protocolos de Comunicación e Intercambio de Información

- **Protocolo de transporte:** HTTP/HTTPS. Se recomienda HTTPS en entornos de producción para confidencialidad e integridad de los datos en tránsito.
- **Formato de intercambio:** JSON (JavaScript Object Notation) para el cuerpo de las peticiones y respuestas. JSON es estándar, legible y ampliamente soportado por clientes y servidores.
- **Semántica REST:** Uso de métodos HTTP (GET, POST, PUT, PATCH, DELETE) según la acción sobre el recurso; uso de códigos de estado HTTP para indicar resultado; recursos identificados por URIs estables.
- **Cabeceras relevantes:** Content-Type (application/json), Authorization (por ejemplo, Bearer token), y cabeceras estándar de caché y control de solicitudes cuando se requiera.

Esta elección de protocolos y formato favorece la interoperabilidad, la integración con otros sistemas y la claridad del contrato entre clientes y API.

---

## 7. Consideraciones de Seguridad

- **Confidencialidad:** Uso de HTTPS para cifrar el tráfico entre clientes y Backend API.
- **Autenticación:** Mecanismo centralizado en la API (por ejemplo, tokens JWT o sesiones) para identificar a usuarios o aplicaciones.
- **Autorización:** Comprobación de permisos por recurso y acción en cada petición; la API no confía en que el cliente haya ocultado opciones no permitidas.
- **Validación:** Toda entrada se valida y sanitiza en el servidor; la validación en el cliente es complementaria y no sustituye la del backend.
- **Exposición de datos:** La API expone solo los recursos y atributos necesarios; no se exponen detalles internos de la base de datos ni información sensible innecesaria.
- **Gestión de secretos:** Credenciales, claves y secretos se gestionan mediante variables de entorno o sistemas de gestión de secretos, sin almacenarlos en el código ni en el control de versiones.

Estas medidas contribuyen a proteger la confidencialidad, integridad y disponibilidad del sistema dentro del modelo Cliente–Servidor y REST adoptado.

---

## 8. Escalabilidad y Extensibilidad

**Escalabilidad:**

- **Horizontal:** La ausencia de estado en el servidor (REST) permite replicar instancias de la Backend API detrás de un balanceador de carga. La Base de Datos puede escalarse según las capacidades del motor elegido (réplicas de lectura, particionamiento, etc.).
- **Vertical:** Cada componente puede dimensionarse de forma independiente (más CPU/memoria en API o en base de datos) según cuellos de botella observados.

**Extensibilidad:**

- **Nuevos clientes:** Cualquier nuevo cliente (por ejemplo, aplicación móvil nativa o integración con un ERP) puede consumir la misma API REST sin modificar la lógica ya desplegada, siempre que respete el contrato de la API.
- **Nuevos recursos o operaciones:** Se pueden añadir nuevos endpoints o versionar la API (por ejemplo, prefijo /v1/, /v2/) para introducir funcionalidad sin romper clientes existentes.
- **Repositorios independientes:** La separación en repositorios (Portal, Aplicación Cliente, Backend API, esquema o scripts de Base de Datos) permite evolucionar cada parte con ciclos de vida propios y equipos dedicados, facilitando la extensión por módulos.

La arquitectura descrita está preparada para crecer en carga y en funcionalidad sin requerir rediseños radicales.

---

## 9. Ventajas Arquitectónicas

- **Separación de responsabilidades:** Cada componente tiene un rol bien definido (presentación, lógica, persistencia), lo que facilita el mantenimiento y las pruebas.
- **Mantenibilidad:** Cambios en la interfaz (Portal o Aplicación Cliente) no obligan a tocar la API ni la base de datos; cambios en reglas de negocio se concentran en la API.
- **Reutilización:** Una única API sirve al Portal y a la Aplicación Cliente (y a futuros consumidores), evitando duplicación de lógica.
- **Escalabilidad:** Posibilidad de escalar la API y la base de datos de forma independiente y, en el caso de la API, de forma horizontal.
- **Interoperabilidad:** REST y JSON permiten integrar con otros sistemas y herramientas estándar de red y seguridad.
- **Alineación con metodología ágil:** Los módulos y repositorios independientes se adaptan a equipos Scrum y a entregas incrementales por componente.
- **Portabilidad:** Los componentes pueden desplegarse en distintos entornos (on-premise, nube) siempre que se respeten los protocolos y contratos definidos.

---

## 10. Conclusión

InventorySoft se sustenta en una arquitectura Cliente–Servidor con API REST sobre HTTP/HTTPS e intercambio JSON, organizada en cuatro componentes principales: Portal Web Administrativo, Aplicación Cliente, Backend API y Base de Datos. La distribución en múltiples repositorios y la estricta separación de responsabilidades favorecen la mantenibilidad, la escalabilidad y la extensión del sistema sin comprometer la consistencia ni la seguridad.

Este documento describe la visión arquitectónica del proyecto y sirve como referencia para presentaciones académicas, portafolio profesional y revisión técnica, proporcionando una base clara para el desarrollo, el despliegue y la evolución futura de InventorySoft.

---

*Documento de Arquitectura de Software — InventorySoft*  
*Versión 1.0 — Arquitectura Cliente–Servidor REST*
