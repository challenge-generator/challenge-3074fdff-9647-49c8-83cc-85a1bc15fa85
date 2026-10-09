# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Diseño y Documentación de APIs para Sistema de Pagos**.

| | |
|---|---|
| Tema | Diseño y Documentación de APIs con Lenguajes de Descripción |
| Nivel | advanced-l3 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean con capas separadas (dominio, aplicación, infraestructura) |
| Tiempo estimado | 15 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springdoc:springdoc-openapi-starter-webmvc-ui:2.9.1 2.9.1
- io.github.resilience4j:resilience4j-spring-boot3:2.2.0 2.2.0
- org.postgresql:postgresql n/a
- org.projectlombok:lombok n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.junit.jupiter:junit-jupiter-api n/a
- org.mockito:mockito-junit-jupiter n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de Requisitos**: Documento de requisitos funcionales y no funcionales para las APIs.
- **Fase 2 — Diseño de las APIs**: Documentación técnica de las APIs siguiendo los estándares de OpenAPI.
- **Fase 3 — Documentación de las APIs**: Documentación completa de las APIs en Markdown, siguiendo los estándares de OpenAPI.
- **Fase 4 — Revisión y Mejora**: Documentación final de las APIs, revisada y mejorada basada en el feedback de los stakeholders.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (92)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.annotation.JsonProperty pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.annotation.JsonInclude pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/ErrorResponse.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/payments/application/service/TransactionService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/payments/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/payments/infrastructure/controller/TransactionControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.id`
      Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setId`
      Se invoca `setId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setAccountId`
      Se invoca `setAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.accountId`
      Se invoca `accountId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setAmount`
      Se invoca `setAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.amount`
      Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setCurrency`
      Se invoca `setCurrency` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.currency`
      Se invoca `currency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setType`
      Se invoca `setType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.type`
      Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setStatus`
      Se invoca `setStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.status`
      Se invoca `status` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setFraudCheckStatus`
      Se invoca `setFraudCheckStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.fraudCheckStatus`
      Se invoca `fraudCheckStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setMerchantId`
      Se invoca `setMerchantId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.merchantId`
      Se invoca `merchantId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setDescription`
      Se invoca `setDescription` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.description`
      Se invoca `description` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setReferenceNumber`
      Se invoca `setReferenceNumber` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.referenceNumber`
      Se invoca `referenceNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setCreatedAt`
      Se invoca `setCreatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.createdAt`
      Se invoca `createdAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setUpdatedAt`
      Se invoca `setUpdatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.updatedAt`
      Se invoca `updatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getId`
      Se invoca `getId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getAccountId`
      Se invoca `getAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getAmount`
      Se invoca `getAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getCurrency`
      Se invoca `getCurrency` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getType`
      Se invoca `getType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getStatus`
      Se invoca `getStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getFraudCheckStatus`
      Se invoca `getFraudCheckStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getMerchantId`
      Se invoca `getMerchantId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getDescription`
      Se invoca `getDescription` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getReferenceNumber`
      Se invoca `getReferenceNumber` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getCreatedAt`
      Se invoca `getCreatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getUpdatedAt`
      Se invoca `getUpdatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.accountId`
      Se invoca `accountId` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.amount`
      Se invoca `amount` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.currency`
      Se invoca `currency` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.type`
      Se invoca `type` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.merchantId`
      Se invoca `merchantId` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.description`
      Se invoca `description` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.referenceNumber`
      Se invoca `referenceNumber` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.callbackUrl`
      Se invoca `callbackUrl` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.metadata`
      Se invoca `metadata` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.id`
      Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.accountId`
      Se invoca `accountId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.amount`
      Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.currency`
      Se invoca `currency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.type`
      Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.status`
      Se invoca `status` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.fraudCheckStatus`
      Se invoca `fraudCheckStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.merchantId`
      Se invoca `merchantId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.description`
      Se invoca `description` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.referenceNumber`
      Se invoca `referenceNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.createdAt`
      Se invoca `createdAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.updatedAt`
      Se invoca `updatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/application/service/TransactionService.java` — `Transaction.id`
      Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/application/service/TransactionService.java` — `Transaction.status`
      Se invoca `status` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/application/service/TransactionService.java` — `Transaction.fraudCheckStatus`
      Se invoca `fraudCheckStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java` — `Transaction.id`
      Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java` — `Transaction.amount`
      Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java` — `Transaction.merchantId`
      Se invoca `merchantId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java` — `Transaction.description`
      Se invoca `description` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionRequest.toDomain`
      Se invoca `toDomain` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.getTransactionById`
      Se invoca `getTransactionById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.getTransactionsByFilters`
      Se invoca `getTransactionsByFilters` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.updateStatus`
      Se invoca `updateStatus` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.deleteTransaction`
      Se invoca `deleteTransaction` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionException.getMessage`
      Se invoca `getMessage` sobre `TransactionException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/payments/infrastructure/exception/TransactionException.java` — `ErrorCode.getCode`
      Se invoca `getCode` sobre `ErrorCode`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionResponse.accountId`
      Se invoca `accountId` sobre `TransactionResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionResponse.status`
      Se invoca `status` sobre `TransactionResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionResponse.fraudCheckStatus`
      Se invoca `fraudCheckStatus` sobre `TransactionResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionById`
      Se invoca `getTransactionById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionResponse.id`
      Se invoca `id` sobre `TransactionResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionsByAccountId`
      Se invoca `getTransactionsByAccountId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionsByMerchantId`
      Se invoca `getTransactionsByMerchantId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/payments/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionById`
      Se invoca `getTransactionById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/payments/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionsByAccountId`
      Se invoca `getTransactionsByAccountId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/payments/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionsByMerchantId`
      Se invoca `getTransactionsByMerchantId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (20)

- `pom.xml`
- `src/main/java/com/pragma/payments/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/payments/domain/model/Transaction.java`
- `src/main/java/com/pragma/payments/domain/port/TransactionRepository.java`
- `src/main/java/com/pragma/payments/domain/port/AntiFraudService.java`
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java`
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java`
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java`
- `src/main/java/com/pragma/payments/infrastructure/dto/ErrorResponse.java`
- `src/main/java/com/pragma/payments/application/service/TransactionService.java`
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java`
- `src/main/java/com/pragma/payments/infrastructure/config/OpenApiConfig.java`
- `src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java`
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java`
- `src/main/java/com/pragma/payments/infrastructure/exception/TransactionException.java`
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java`
- `src/main/resources/static/api-docs.md`
- `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java`
- `src/test/java/com/pragma/payments/infrastructure/controller/TransactionControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/payments`
- `src/main/java/com/pragma/payments/domain`
- `src/main/java/com/pragma/payments/domain/model`
- `src/main/java/com/pragma/payments/domain/port`
- `src/main/java/com/pragma/payments/application`
- `src/main/java/com/pragma/payments/application/service`
- `src/main/java/com/pragma/payments/infrastructure`
- `src/main/java/com/pragma/payments/infrastructure/adapter`
- `src/main/java/com/pragma/payments/infrastructure/config`
- `src/main/java/com/pragma/payments/infrastructure/controller`
- `src/main/java/com/pragma/payments/infrastructure/dto`
- `src/main/resources`
- `src/test/java/com/pragma/payments`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con capas separadas (dominio, aplicación, infraestructura)**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced
- Brecha que el reto ataca: Diseña documentación de las APIs que implementa utilizando al menos un lenguaje de descripción, como Swagger, OpenAPI, Markdown, entre otros. Brecha: Diseño y documentación de APIs con estándares de industria.
- Mision: Candidato Backend Advanced con 4+ años de experiencia en proyectos distribuidos, experto en Spring Boot y resiliencia, ahora enfocado en mejores prácticas de API design y documentación.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
