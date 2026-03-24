# ADR-003: Separar repositorios por dominio / microservicio

## Estado

Aceptado

---

## Qué debe responder este ADR

Fijar cómo se **organiza el código** en Git: un repositorio por servicio/dominio frente a un monorepo único, y el papel del repositorio de **documentación central**.

---

## 1. Cuál era el problema

- Un **solo repositorio** con frontend, gateway, varios backends y scripts mezclados generaba **conflictos de merge** frecuentes y responsabilidades poco claras.
- Los **pipelines y permisos** no podían alinearse fácilmente con “solo lo que cambió el equipo de ventas” vs “solo inventario”.
- Los **ciclos de release** quedaban acoplados: un cambio documental o de un servicio arrastraba revisión de todo el árbol.

---

## 2. Qué opciones existían

| Opción | Descripción breve |
|--------|-------------------|
| **A. Monorepo** | Todo el código del ecosistema en un repositorio, con herramientas internas para límites (carpetas, Bazel/Turborepo, etc.). |
| **B. Multirepo por servicio** | Un repositorio por microservicio, frontend y gateway; documentación y ADR en un repo aparte o en uno “docs”. |
| **C. Monorepo + submódulos / subtrees** | Un repo “contenedor” que referencia otros; más complejidad de flujo Git. |

---

## 3. Cuál opción se eligió

**Opción B (Multirepo por dominio / microservicio)**:

- Repositorios dedicados, por ejemplo: `inventory-service`, `sales-service`, `auth-service`, `gateway-service`, `frontend`, `inventorysoft-database`.
- Un repositorio principal (este) para **documentación**, **diagramas**, **prototipos**, **retrospectivas** y **ADR**, como **punto de referencia** del ecosistema.

---

## 4. Qué ganamos

- **Propiedad clara**: cada equipo o línea de trabajo tiene un límite natural en su repo.
- **CI/CD y permisos** más simples por componente.
- **Versionado y releases** independientes por servicio.
- **Menos ruido** en el historial: los commits de un servicio no mezclan cambios ajenos.

---

## 5. Qué costo aceptamos

- **Coordinación entre repos**: contratos de API, versiones y compatibilidad deben vigilarse explícitamente.
- **Documentación transversal** debe mantenerse a mano (README del ecosistema, enlaces, ADR); no queda “obvia” solo por estar en el mismo árbol de código.
- **Clonación y setup local** implica varios `git clone` o scripts de bootstrap (si no se automatizan).
- Riesgo de **drift** entre documentación y código si no se actualizan los enlaces tras renombrar repos.

---

## Referencias

- `README.md` (sección de repositorios del ecosistema)
- `Docs/explanation/ddd-inventarysoft.md`
