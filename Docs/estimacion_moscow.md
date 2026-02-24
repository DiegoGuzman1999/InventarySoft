# Estimación de Prioridades con Técnica MoSCoW  
### Sistema Web de Control Administrativo, Ventas e Inventario

---

## 1. Introducción a la técnica MoSCoW

La técnica MoSCoW es un método de priorización de requerimientos ampliamente utilizado en proyectos ágiles, especialmente en contextos donde existen limitaciones de tiempo, presupuesto y recursos. Su propósito es clasificar las funcionalidades de un sistema en diferentes niveles de importancia, permitiendo al equipo de desarrollo y al Product Owner (PO) decidir qué debe incluirse en las primeras entregas y qué puede posponerse.

El acrónimo MoSCoW se compone de las siguientes categorías:

- **Must Have**: Requerimientos imprescindibles, sin los cuales el sistema no cumple su propósito mínimo.
- **Should Have**: Requerimientos importantes que aportan gran valor, pero cuya ausencia no imposibilita el funcionamiento básico del sistema.
- **Could Have**: Requerimientos deseables que mejoran la experiencia de uso o aportan valor añadido, pero no son esenciales.
- **Won’t Have (por ahora)**: Requerimientos que deliberadamente se dejan fuera del alcance actual, ya sea por tiempo, costo o estrategia, pudiendo considerarse en futuras fases.

Aplicar MoSCoW en el Sistema Web de Control Administrativo, Ventas e Inventario permite al equipo priorizar de forma transparente y consensuada, alineando las expectativas del PO con las capacidades del equipo de desarrollo bajo la metodología Scrum.

---

## 2. Objetivo de la estimación MoSCoW

El objetivo de este documento es:

- **Definir y priorizar los requerimientos principales** del Sistema Web de Control Administrativo, Ventas e Inventario utilizando la técnica MoSCoW.
- **Establecer una base clara de alcance** para los próximos sprints, enfocando el esfuerzo en las funcionalidades de mayor valor para el negocio.
- **Facilitar la comunicación entre PO, equipo de desarrollo y QA**, asegurando que todos compartan una comprensión común de qué se debe entregar primero y qué puede diferirse.
- **Servir como insumo formal de planificación** para el Product Backlog, la planificación de releases y la definición de criterios de aceptación a nivel de calidad.

Este documento está orientado a su uso en el contexto académico universitario, pero siguiendo prácticas profesionales de ingeniería de software y gestión de requerimientos.

---

## 3. Criterios de priorización

Para clasificar los requerimientos en Must, Should, Could y Won’t, se han considerado los siguientes criterios:

- **Valor de negocio**  
  - **Alto**: Impacta directamente en la operación principal (registro de ventas, control de inventario, emisión de comprobantes, control administrativo básico).  
  - **Medio**: Mejora la eficiencia operativa, reduce errores o aumenta la trazabilidad.  
  - **Bajo**: Aporta comodidad, estética o funcionalidades complementarias no críticas.

- **Impacto en los procesos clave del sistema**  
  - Generación y registro de ventas.  
  - Actualización y control de inventario.  
  - Gestión básica administrativa (clientes, proveedores, usuarios, roles).  
  - Reportes esenciales para la toma de decisiones.

- **Riesgo y complejidad técnica**  
  - Requerimientos de alta complejidad podrían posponerse si no son esenciales para el MVP.  
  - Se prioriza primero la base funcional estable y segura.

- **Dependencias entre funcionalidades**  
  - Funcionalidades que sirven como base de otras (por ejemplo, gestión de productos antes de ventas) se consideran con mayor prioridad.  
  - Requerimientos que dependen de otros se ubican en categorías posteriores si su predecesor aún no forma parte del alcance inmediato.

- **Restricciones de tiempo y recursos (contexto académico)**  
  - Dado el carácter académico, se prioriza la demostración funcional de los procesos centrales sobre características avanzadas o de integración externa.  
  - Se busca un alcance realista para ser implementado y probado dentro de los tiempos del semestre.

Bajo estos criterios, se definen las categorías MoSCoW para el sistema descrito a continuación.

---

## 4. Lista de requerimientos clasificados según MoSCoW

### 4.1. Must Have (Obligatorios)

Funcionalidades sin las cuales el sistema no cumpliría con el objetivo mínimo de control administrativo, ventas e inventario.

| ID   | Requerimiento                                                                                           | Descripción breve                                                                                     |
|------|---------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| M-01 | Autenticación básica de usuarios                                                                       | Permitir inicio de sesión con usuario y contraseña para acceso controlado al sistema.                 |
| M-02 | Gestión de productos                                                                                   | Crear, editar, eliminar y consultar productos (nombre, código, categoría, precio, stock, estado).     |
| M-03 | Registro de ventas                                                                                     | Registrar ventas asociando productos, cantidades, precios y totales, con fecha y usuario vendedor.    |
| M-04 | Actualización automática de inventario                                                                 | Descontar existencias de inventario al registrar una venta.                                           |
| M-05 | Gestión de clientes básicos                                                                            | Registrar y consultar datos básicos de clientes (nombre, identificación, contacto).                  |
| M-06 | Gestión de proveedores básicos                                                                         | Registrar y consultar datos básicos de proveedores para control de compras e inventario.             |
| M-07 | Consulta de existencias en tiempo casi real                                                             | Visualizar el stock actual de cada producto (disponible, mínimo, en alerta).                         |
| M-08 | Reporte básico de ventas por rango de fechas                                                           | Generar un listado de ventas en un período determinado con montos totales.                           |
| M-09 | Control de acceso según rol mínimo (admin / vendedor)                                                 | Distinguir permisos básicos: administrador (gestión completa) y vendedor (registro de ventas, consulta). |
| M-10 | Registro de compras o ingreso de mercancía                                                             | Registrar compras o ingresos de productos para incrementar el inventario.                            |
| M-11 | Validación de datos obligatorios en formularios críticos                                               | Prevenir registros incompletos en ventas, productos y usuarios.                                      |

---

### 4.2. Should Have (Importantes)

Funcionalidades muy valiosas que mejoran significativamente la operación, pero que pueden ser implementadas en una iteración posterior al núcleo mínimo.

| ID   | Requerimiento                                                                                           | Descripción breve                                                                                     |
|------|---------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| S-01 | Gestión avanzada de roles y permisos                                                                   | Definir diferentes roles (ej. administrador, supervisor, vendedor) con permisos más granulares.       |
| S-02 | Reportes detallados de inventario (stock mínimo, productos en alerta, rotación)                       | Reportes específicos para la toma de decisiones de compra y reposición.                               |
| S-03 | Reporte de ventas por cliente                                                                          | Visualizar el historial de compras y volumen por cliente.                                             |
| S-04 | Reporte de ventas por producto                                                                         | Identificar productos más vendidos y aquellos con baja rotación.                                      |
| S-05 | Registro de devoluciones y ajustes de inventario                                                       | Controlar devoluciones de clientes o ajustes por pérdida/merma.                                       |
| S-06 | Bitácora básica de actividades (log de operaciones clave)                                              | Registrar acciones críticas (creación/eliminación de productos, ventas, ajustes de inventario).       |
| S-07 | Búsqueda avanzada en catálogos (productos, clientes, proveedores)                                     | Filtrar por múltiples criterios (categoría, rango de precios, estado, etc.).                          |
| S-08 | Exportación básica de reportes a PDF o Excel                                                           | Permitir descarga de reportes para revisión externa o respaldo.                                       |

---

### 4.3. Could Have (Opcionales / Deseables)

Funcionalidades que aumentan la usabilidad, la eficiencia o el valor añadido del sistema, pero que no son imprescindibles para el funcionamiento básico.

| ID   | Requerimiento                                                                                           | Descripción breve                                                                                     |
|------|---------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| C-01 | Panel de control (dashboard) con indicadores clave (KPI)                                               | Visualizar métricas de ventas, inventario y alertas en una sola vista.                                |
| C-02 | Notificaciones visuales de stock bajo                                                                  | Alertas en pantalla cuando un producto llegue a su nivel mínimo definido.                             |
| C-03 | Gestión de categorías y subcategorías de productos más detallada                                      | Organización jerárquica avanzada de productos.                                                         |
| C-04 | Impresión y/o generación de comprobantes de venta en formato ticket/factura                            | Generar comprobantes formales para el cliente.                                                         |
| C-05 | Soporte multimoneda básico (visualización de totales en otra moneda)                                  | Conversión referencial con tipo de cambio configurable.                                                |
| C-06 | Autocompletado de campos frecuentes (búsqueda rápida de clientes, productos)                           | Mejora la velocidad de registro de ventas y operaciones.                                               |
| C-07 | Personalización básica de la interfaz (logo institucional, colores)                                   | Adaptar la apariencia del sistema a la identidad de la organización/académica.                        |
| C-08 | Ayuda en línea o sección de preguntas frecuentes                                                       | Guía básica de uso para usuarios finales.                                                              |

---

### 4.4. Won’t Have (por ahora)

Requerimientos que, por alcance, tiempo, complejidad o baja prioridad en el contexto académico, se han decidido explícitamente dejar fuera de la versión actual del proyecto.

| ID   | Requerimiento                                                                                           | Descripción breve                                                                                     |
|------|---------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| W-01 | Integración con sistemas contables externos                                                             | Conectar el sistema con plataformas contables de terceros.                                            |
| W-02 | Integración con pasarelas de pago en línea                                                              | Pagos con tarjetas o plataformas de pago en tiempo real.                                              |
| W-03 | Aplicación móvil nativa (Android / iOS)                                                                 | Cliente móvil específico más allá del acceso web responsive.                                          |
| W-04 | Módulo avanzado de CRM (gestión completa del ciclo de vida del cliente)                                | Funcionalidades complejas de marketing, seguimiento de oportunidades, campañas, etc.                  |
| W-05 | Inteligencia de negocios avanzada (BI) con dashboards interactivos externos                             | Integración con herramientas de BI externas y analítica avanzada.                                     |
| W-06 | Multisucursal con sincronización en tiempo real entre diferentes ubicaciones físicas                    | Manejo de inventarios distribuidos con replicación avanzada.                                          |
| W-07 | Sistema de facturación electrónica plenamente integrado con entes tributarios                           | Emisión y validación directa de comprobantes fiscales electrónicos.                                   |

---

## 5. Justificación general de la priorización

La priorización presentada responde a la necesidad de asegurar primero un **núcleo funcional sólido** que permita demostrar, en el contexto académico, la correcta implementación de un sistema de control administrativo, ventas e inventario:

- **Must Have**: Incluyen las funcionalidades esenciales para que el sistema cumpla su propósito mínimo: registrar ventas, gestionar productos, controlar inventario, manejar clientes y proveedores, y contar con un control de acceso básico. Sin estos elementos, el sistema no podría ser utilizado de manera efectiva ni justificaría su objetivo académico de modelar un entorno realista de gestión comercial.

- **Should Have**: Se han clasificado aquí las funcionalidades que **incrementan significativamente el valor de la solución**, principalmente a través de reportes más específicos, gestión avanzada de roles, trazabilidad mediante bitácoras y herramientas de análisis operativo. Aunque no son estrictamente necesarias para la operación mínima, sí marcan una diferencia importante en términos de profesionalismo y capacidad analítica del sistema.

- **Could Have**: En este grupo se ubican características que **mejoran la experiencia de usuario, la eficiencia y la presentación** (como dashboards, notificaciones visuales, personalización de interfaz, impresión de comprobantes y ayudas en línea). Estas funcionalidades aportan madurez y completitud, pero pueden ser diferidas para fases posteriores sin comprometer el objetivo académico principal.

- **Won’t Have (por ahora)**: Se identifican requerimientos que, aunque relevantes en un entorno empresarial real, **su complejidad técnica y de integración excede razonablemente el alcance de un proyecto académico** en términos de tiempo y recursos (por ejemplo, integraciones con pasarelas de pago, sistemas contables, facturación electrónica y soluciones de BI avanzadas). Su exclusión actual es una decisión consciente de gestión de alcance y riesgos.

La clasificación MoSCoW permite así **alinear expectativas** entre el Product Owner y el equipo de desarrollo, además de proporcionar al equipo de QA una referencia clara de qué funcionalidades deben considerarse obligatorias en las pruebas de aceptación de cada iteración.

---

## 6. Conclusiones

La aplicación de la técnica MoSCoW al Sistema Web de Control Administrativo, Ventas e Inventario ha permitido **definir de manera estructurada y transparente las prioridades del proyecto**, diferenciando entre requerimientos esenciales, importantes, opcionales y aplazados. Esta priorización es coherente con el contexto académico y con las limitaciones habituales de tiempo y recursos.

El conjunto de requerimientos clasificados como **Must Have** conforma un **Producto Mínimo Viable (MVP)** capaz de demostrar el flujo básico de negocio: gestión de productos, registro de ventas, control de inventarios y generación de reportes fundamentales. Los **Should Have** y **Could Have** proporcionan un camino natural de evolución del sistema, facilitando la planificación de sprints futuros y ampliando progresivamente el alcance funcional.

Finalmente, la identificación explícita de los requerimientos **Won’t Have (por ahora)** ayuda a evitar expectativas irreales y a **gestionar adecuadamente el alcance del proyecto**, dejando claramente documentado qué elementos quedan fuera de la versión actual. Este documento queda listo para su **validación por parte del Product Owner** y para su uso por el equipo de QA como insumo en la definición de casos de prueba y criterios de aceptación, alineados con la prioridad y el valor de cada requerimiento.
