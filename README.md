# Diseño y Documentación de APIs para Sistema de Pagos

Como desarrollador backend avanzado en un sistema de pagos distribuido, tu tarea es diseñar y documentar las APIs que implementas utilizando al menos un lenguaje de descripción. El sistema procesa transacciones de pago en tiempo real con un throughput de 10 000 transacciones por segundo y un SLA de 99.9%. Los actores involucrados son el gateway de pagos, el core bancario, el sistema de liquidación y el motor antifraude. Debes asegurar que la documentación sea clara, concisa y siga los estándares de la industria.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Diseño y Documentación de APIs con Lenguajes de Descripción |
| **Nivel** | advanced-l3 |
| **Tipo** | practical |
| **Tiempo estimado** | 15 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición de Requisitos

**Objetivo:** Identificar y documentar los requisitos funcionales y no funcionales de las APIs.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Enumera los actores y las interacciones clave en el dominio de los pagos.
- Identifica las operaciones CRUD necesarias para el sistema.
- Especifica las propiedades operativas (throughput, latencia, disponibilidad) y los modos de falla relevantes.

**Entregable:** Documento de requisitos funcionales y no funcionales para las APIs.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las interacciones entre el gateway de pagos y el core bancario.
- Piensa en los edge cases, como transacciones duplicadas o fallos temporales del sistema de liquidación.

</details>

### Fase 2: Diseño de las APIs

**Objetivo:** Diseñar las APIs siguiendo los estándares de la industria y las mejores prácticas.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Define los endpoints, métodos HTTP y parámetros para cada operación.
- Especifica los códigos de estado HTTP y los mensajes de error.
- Aplica los principios de REST y los estándares de OpenAPI.

**Entregable:** Documentación técnica de las APIs siguiendo los estándares de OpenAPI.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza OpenAPI para describir los endpoints y los parámetros.
- Aplica los principios de REST para asegurar que las APIs sean idempotentes y stateless.

</details>

### Fase 3: Documentación de las APIs

**Objetivo:** Crear documentación clara y concisa para las APIs utilizando al menos un lenguaje de descripción.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Escribe la documentación en Markdown, siguiendo los estándares de OpenAPI.
- Asegura que la documentación sea clara, concisa y siga los estándares de la industria.
- Incluye ejemplos de solicitudes y respuestas para cada endpoint.

**Entregable:** Documentación completa de las APIs en Markdown, siguiendo los estándares de OpenAPI.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza Markdown para estructurar la documentación.
- Incluye ejemplos de solicitudes y respuestas para cada endpoint.

</details>

### Fase 4: Revisión y Mejora

**Objetivo:** Revisar y mejorar la documentación basada en el feedback de los stakeholders.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Recopila feedback de los stakeholders sobre la documentación.
- Identifica áreas de mejora y realiza los cambios necesarios.
- Asegura que la documentación final sea clara, concisa y siga los estándares de la industria.

**Entregable:** Documentación final de las APIs, revisada y mejorada basada en el feedback de los stakeholders.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el feedback de los stakeholders para identificar áreas de mejora.
- Asegura que los cambios realizados mejoren la claridad y concisión de la documentación.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las APIs y por qué son importantes en un sistema de pagos?
- **paraQueSirve**: ¿Para qué sirve la documentación de las APIs y cuáles son sus beneficios?
- **comoSeUsa**: ¿Cómo se aplican los estándares de OpenAPI y los principios de REST en el diseño y documentación de las APIs?
- **erroresComunes**: ¿Cuáles son los errores comunes en el diseño y documentación de las APIs y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica el diseño y documentación de las APIs y cómo se pueden justificar?

## Criterios de Evaluacion

- Identificar y documentar los requisitos funcionales y no funcionales de las APIs.
- Diseñar las APIs siguiendo los estándares de la industria y las mejores prácticas.
- Crear documentación clara y concisa para las APIs utilizando al menos un lenguaje de descripción.
- Revisar y mejorar la documentación basada en el feedback de los stakeholders.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
