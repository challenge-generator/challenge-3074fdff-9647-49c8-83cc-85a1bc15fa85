# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.annotation.JsonProperty pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.annotation.JsonInclude pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/dto/ErrorResponse.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/service/TransactionService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/infrastructure/controller/TransactionControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.id`: Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setId`: Se invoca `setId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setAccountId`: Se invoca `setAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.accountId`: Se invoca `accountId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setAmount`: Se invoca `setAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.amount`: Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setCurrency`: Se invoca `setCurrency` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.currency`: Se invoca `currency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setType`: Se invoca `setType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.type`: Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setStatus`: Se invoca `setStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.status`: Se invoca `status` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setFraudCheckStatus`: Se invoca `setFraudCheckStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.fraudCheckStatus`: Se invoca `fraudCheckStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setMerchantId`: Se invoca `setMerchantId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.merchantId`: Se invoca `merchantId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setDescription`: Se invoca `setDescription` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.description`: Se invoca `description` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setReferenceNumber`: Se invoca `setReferenceNumber` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.referenceNumber`: Se invoca `referenceNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setCreatedAt`: Se invoca `setCreatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.createdAt`: Se invoca `createdAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.setUpdatedAt`: Se invoca `setUpdatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `Transaction.updatedAt`: Se invoca `updatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getId`: Se invoca `getId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getAccountId`: Se invoca `getAccountId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getAmount`: Se invoca `getAmount` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getCurrency`: Se invoca `getCurrency` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getType`: Se invoca `getType` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getStatus`: Se invoca `getStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getFraudCheckStatus`: Se invoca `getFraudCheckStatus` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getMerchantId`: Se invoca `getMerchantId` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getDescription`: Se invoca `getDescription` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getReferenceNumber`: Se invoca `getReferenceNumber` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getCreatedAt`: Se invoca `getCreatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java` — `TransactionEntity.getUpdatedAt`: Se invoca `getUpdatedAt` sobre `TransactionEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.accountId`: Se invoca `accountId` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.amount`: Se invoca `amount` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.currency`: Se invoca `currency` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.type`: Se invoca `type` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.merchantId`: Se invoca `merchantId` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.description`: Se invoca `description` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.referenceNumber`: Se invoca `referenceNumber` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.callbackUrl`: Se invoca `callbackUrl` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java` — `TransactionRequest.metadata`: Se invoca `metadata` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.id`: Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.accountId`: Se invoca `accountId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.amount`: Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.currency`: Se invoca `currency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.type`: Se invoca `type` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.status`: Se invoca `status` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.fraudCheckStatus`: Se invoca `fraudCheckStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.merchantId`: Se invoca `merchantId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.description`: Se invoca `description` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.referenceNumber`: Se invoca `referenceNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.createdAt`: Se invoca `createdAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java` — `Transaction.updatedAt`: Se invoca `updatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/service/TransactionService.java` — `Transaction.id`: Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/service/TransactionService.java` — `Transaction.status`: Se invoca `status` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/service/TransactionService.java` — `Transaction.fraudCheckStatus`: Se invoca `fraudCheckStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java` — `Transaction.id`: Se invoca `id` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java` — `Transaction.amount`: Se invoca `amount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java` — `Transaction.merchantId`: Se invoca `merchantId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java` — `Transaction.description`: Se invoca `description` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionRequest.toDomain`: Se invoca `toDomain` sobre `TransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.getTransactionById`: Se invoca `getTransactionById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.getTransactionsByFilters`: Se invoca `getTransactionsByFilters` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.updateStatus`: Se invoca `updateStatus` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.deleteTransaction`: Se invoca `deleteTransaction` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionException.getMessage`: Se invoca `getMessage` sobre `TransactionException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/TransactionException.java` — `ErrorCode.getCode`: Se invoca `getCode` sobre `ErrorCode`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionResponse.accountId`: Se invoca `accountId` sobre `TransactionResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionResponse.status`: Se invoca `status` sobre `TransactionResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionResponse.fraudCheckStatus`: Se invoca `fraudCheckStatus` sobre `TransactionResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionById`: Se invoca `getTransactionById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionResponse.id`: Se invoca `id` sobre `TransactionResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionsByAccountId`: Se invoca `getTransactionsByAccountId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java` — `TransactionService.getTransactionsByMerchantId`: Se invoca `getTransactionsByMerchantId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionById`: Se invoca `getTransactionById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionsByAccountId`: Se invoca `getTransactionsByAccountId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/controller/TransactionControllerTest.java` — `TransactionService.getTransactionsByMerchantId`: Se invoca `getTransactionsByMerchantId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Diseña documentación de las APIs que implementa utilizando al menos un lenguaje de descripción, como Swagger, OpenAPI, Markdown, entre otros. Brecha: Diseño y documentación de APIs con estándares de industria.

### Misión / candidato
Candidato Backend Advanced con 4+ años de experiencia en proyectos distribuidos, experto en Spring Boot y resiliencia, ahora enfocado en mejores prácticas de API design y documentación.

### Reto
- Tema: Diseño y Documentación de APIs con Lenguajes de Descripción
- Seniority: advanced-l3
- Tipo: practical
- Título: Diseño y Documentación de APIs para Sistema de Pagos
- Tiempo estimado: 15 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de Requisitos — objetivo: Identificar y documentar los requisitos funcionales y no funcionales de las APIs. — entregable (NO resolver): Documento de requisitos funcionales y no funcionales para las APIs.
- Fase 2: Diseño de las APIs — objetivo: Diseñar las APIs siguiendo los estándares de la industria y las mejores prácticas. — entregable (NO resolver): Documentación técnica de las APIs siguiendo los estándares de OpenAPI.
- Fase 3: Documentación de las APIs — objetivo: Crear documentación clara y concisa para las APIs utilizando al menos un lenguaje de descripción. — entregable (NO resolver): Documentación completa de las APIs en Markdown, siguiendo los estándares de OpenAPI.
- Fase 4: Revisión y Mejora — objetivo: Revisar y mejorar la documentación basada en el feedback de los stakeholders. — entregable (NO resolver): Documentación final de las APIs, revisada y mejorada basada en el feedback de los stakeholders.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>payments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>payments</name>
    <description>Sistema de pagos distribuido con procesamiento en tiempo real</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi.version>2.9.1</springdoc-openapi.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi.version}</version>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.github.resilience4j</groupId>
                <artifactId>resilience4j-bom</artifactId>
                <version>${resilience4j.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>

// === ARCHIVO: src/main/java/com/pragma/payments/Application.java ===
package com.pragma.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.bulkhead.BulkheadConfig;

import java.time.Duration;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerConfig defaultCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .build();
    }

    @Bean
    public RetryConfig defaultRetryConfig() {
        return RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(RuntimeException.class)
                .build();
    }

    @Bean
    public BulkheadConfig defaultBulkheadConfig() {
        return BulkheadConfig.custom()
                .maxConcurrentCalls(10)
                .maxWaitDuration(Duration.ZERO)
                .build();
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: payments
  
  datasource:
    url: jdbc:postgresql://localhost:5432/payments_db
    username: payments_user
    password: payments_password
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        jdbc:
          batch_size: 50
          order_inserts: true
          order_updates: true

server:
  port: 8080
  servlet:
    context-path: /api/payments

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
    instances:
      antiFraudService:
        baseConfig: default
  
  retry:
    configs:
      default:
        maxAttempts: 3
        waitDuration: 500ms
        retryExceptions:
          - org.springframework.web.client.RestClientException
          - java.io.IOException
    instances:
      antiFraudService:
        baseConfig: default
  
  bulkhead:
    configs:
      default:
        maxConcurrentCalls: 10
    instances:
      antiFraudService:
        baseConfig: default

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: method
    tagsSorter: alpha
    displayRequestDuration: true
    docExpansion: none"

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/Transaction.java ===
package com.pragma.payments.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaction(
    @NotNull(message = "Transaction ID cannot be null")
    UUID id,

    @NotNull(message = "Account ID cannot be null")
    UUID accountId,

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    BigDecimal amount,

    @NotNull(message = "Currency cannot be null")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a 3-letter ISO code")
    String currency,

    @NotNull(message = "Transaction date cannot be null")
    LocalDateTime transactionDate,

    @NotNull(message = "Status cannot be null")
    TransactionStatus status,

    @NotBlank(message = "Merchant ID cannot be blank")
    String merchantId,

    @NotBlank(message = "Reference cannot be blank")
    String reference,

    @NotNull(message = "Fraud check status cannot be null")
    FraudCheckStatus fraudCheckStatus,

    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED, REVERSED
    }

    public enum FraudCheckStatus {
        NOT_CHECKED, PENDING, APPROVED, REJECTED
    }

    public Transaction {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }

    public Transaction withStatus(TransactionStatus newStatus) {
        return new Transaction(
            this.id,
            this.accountId,
            this.amount,
            this.currency,
            this.transactionDate,
            newStatus,
            this.merchantId,
            this.reference,
            this.fraudCheckStatus,
            this.createdAt,
            LocalDateTime.now()
        );
    }

    public Transaction withFraudCheckStatus(FraudCheckStatus newStatus) {
        return new Transaction(
            this.id,
            this.accountId,
            this.amount,
            this.currency,
            this.transactionDate,
            this.status,
            this.merchantId,
            this.reference,
            newStatus,
            this.createdAt,
            LocalDateTime.now()
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/TransactionRepository.java ===
package com.pragma.payments.domain.port;



import com.pragma.payments.domain.model.FraudCheckStatus;
import com.pragma.payments.domain.model.TransactionStatus;
import com.pragma.payments.domain.model.Transaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    
    Optional<Transaction> findById(UUID id);
    
    List<Transaction> findByAccountId(UUID accountId);
    
    List<Transaction> findByStatus(Transaction.TransactionStatus status);
    
    List<Transaction> findByFraudCheckStatus(Transaction.FraudCheckStatus status);
    
    List<Transaction> findByMerchantId(String merchantId);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/AntiFraudService.java ===
package com.pragma.payments.domain.port;


import com.pragma.payments.domain.model.FraudCheckStatus;
import com.pragma.payments.domain.model.Transaction;
import java.util.concurrent.CompletionStage;

public interface AntiFraudService {
    /**
     * Evaluates a transaction for potential fraud.
     * @param transaction The transaction to evaluate
     * @return A CompletionStage that will complete with the fraud check status
     */
    CompletionStage<Transaction.FraudCheckStatus> evaluateTransaction(Transaction transaction);
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/JpaTransactionRepository.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.port.TransactionRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final TransactionJpaRepository jpaRepository;
    private final EntityManager entityManager;

    public JpaTransactionRepository(TransactionJpaRepository jpaRepository, EntityManager entityManager) {
        this.jpaRepository = jpaRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Transaction save(Transaction transaction) {
        if (transaction.id() == null) {
            TransactionEntity entity = toEntity(transaction);
            TransactionEntity saved = jpaRepository.save(entity);
            return toDomain(saved);
        }
        TransactionEntity existing = jpaRepository.findById(transaction.id())
            .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + transaction.id()));
        updateEntity(existing, transaction);
        TransactionEntity saved = jpaRepository.save(existing);
        return toDomain(saved);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Transaction> findByAccountId(UUID accountId) {
        List<TransactionEntity> entities = jpaRepository.findByAccountId(accountId);
        return entities.stream().map(this::toDomain).toList();
    }

    @Override
    public List<Transaction> findByStatus(TransactionStatus status) {
        List<TransactionEntity> entities = jpaRepository.findByStatus(status.name());
        return entities.stream().map(this::toDomain).toList();
    }

    @Override
    public List<Transaction> findByFraudCheckStatus(FraudCheckStatus status) {
        List<TransactionEntity> entities = jpaRepository.findByFraudCheckStatus(status.name());
        return entities.stream().map(this::toDomain).toList();
    }

    @Override
    public List<Transaction> findByMerchantId(String merchantId) {
        List<TransactionEntity> entities = jpaRepository.findByMerchantId(merchantId);
        return entities.stream().map(this::toDomain).toList();
    }

    private TransactionEntity toEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(transaction.id() != null ? transaction.id() : UUID.randomUUID());
        entity.setAccountId(transaction.accountId());
        entity.setAmount(transaction.amount());
        entity.setCurrency(transaction.currency());
        entity.setType(transaction.type());
        entity.setStatus(transaction.status().name());
        entity.setFraudCheckStatus(transaction.fraudCheckStatus().name());
        entity.setMerchantId(transaction.merchantId());
        entity.setDescription(transaction.description());
        entity.setReferenceNumber(transaction.referenceNumber());
        entity.setCreatedAt(transaction.createdAt());
        entity.setUpdatedAt(transaction.updatedAt());
        return entity;
    }

    private void updateEntity(TransactionEntity existing, Transaction transaction) {
        existing.setAccountId(transaction.accountId());
        existing.setAmount(transaction.amount());
        existing.setCurrency(transaction.currency());
        existing.setType(transaction.type());
        existing.setStatus(transaction.status().name());
        existing.setFraudCheckStatus(transaction.fraudCheckStatus().name());
        existing.setMerchantId(transaction.merchantId());
        existing.setDescription(transaction.description());
        existing.setReferenceNumber(transaction.referenceNumber());
        existing.setUpdatedAt(java.time.Instant.now());
    }

    private Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
            entity.getId(),
            entity.getAccountId(),
            entity.getAmount(),
            entity.getCurrency(),
            entity.getType(),
            TransactionStatus.valueOf(entity.getStatus()),
            FraudCheckStatus.valueOf(entity.getFraudCheckStatus()),
            entity.getMerchantId(),
            entity.getDescription(),
            entity.getReferenceNumber(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    @Entity
    @Table(name = "transactions")
    public static class TransactionEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(name = "account_id", nullable = false)
        private UUID accountId;

        @Column(nullable = false, precision = 19, scale = 4)
        private java.math.BigDecimal amount;

        @Column(nullable = false, length = 3)
        private String currency;

        @Column(nullable = false, length = 50)
        private String type;

        @Column(nullable = false, length = 20)
        private String status;

        @Column(name = "fraud_check_status", nullable = false, length = 20)
        private String fraudCheckStatus;

        @Column(name = "merchant_id", nullable = false, length = 100)
        private String merchantId;

        @Column(length = 500)
        private String description;

        @Column(name = "reference_number", unique = true, length = 100)
        private String referenceNumber;

        @Column(name = "created_at", nullable = false)
        private java.time.Instant createdAt;

        @Column(name = "updated_at", nullable = false)
        private java.time.Instant updatedAt;

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public UUID getAccountId() { return accountId; }
        public void setAccountId(UUID accountId) { this.accountId = accountId; }
        public java.math.BigDecimal getAmount() { return amount; }
        public void setAmount(java.math.BigDecimal amount) { this.amount = amount; }
        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getFraudCheckStatus() { return fraudCheckStatus; }
        public void setFraudCheckStatus(String fraudCheckStatus) { this.fraudCheckStatus = fraudCheckStatus; }
        public String getMerchantId() { return merchantId; }
        public void setMerchantId(String merchantId) { this.merchantId = merchantId; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getReferenceNumber() { return referenceNumber; }
        public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
        public java.time.Instant getCreatedAt() { return createdAt; }
        public void setCreatedAt(java.time.Instant createdAt) { this.createdAt = createdAt; }
        public java.time.Instant getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(java.time.Instant updatedAt) { this.updatedAt = updatedAt; }
    }

    interface TransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {
        List<TransactionEntity> findByAccountId(UUID accountId);
        List<TransactionEntity> findByStatus(String status);
        List<TransactionEntity> findByFraudCheckStatus(String fraudCheckStatus);
        List<TransactionEntity> findByMerchantId(String merchantId);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/dto/TransactionRequest.java ===
package com.pragma.payments.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Solicitud para crear o actualizar una transacción de pago")
public record TransactionRequest(

    @JsonProperty("account_id")
    @NotNull(message = "El ID de cuenta es obligatorio")
    @Schema(description = "Identificador único de la cuenta", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.REQUIRED)
    UUID accountId,

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @Schema(description = "Monto de la transacción", example = "150.75", requiredMode = Schema.RequiredMode.REQUIRED)
    BigDecimal amount,

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe tener 3 caracteres")
    @Schema(description = "Código de moneda ISO 4217", example = "USD", requiredMode = Schema.RequiredMode.REQUIRED)
    String currency,

    @NotBlank(message = "El tipo de transacción es obligatorio")
    @Size(max = 50, message = "El tipo no puede exceder 50 caracteres")
    @Schema(description = "Tipo de transacción: PAYMENT, REFUND, CHARGEBACK, SETTLEMENT", example = "PAYMENT", requiredMode = Schema.RequiredMode.REQUIRED)
    String type,

    @NotBlank(message = "El ID del comercio es obligatorio")
    @Size(max = 100, message = "El ID del comercio no puede exceder 100 caracteres")
    @Schema(description = "Identificador del comercio", example = "MERCH-001", requiredMode = Schema.RequiredMode.REQUIRED)
    String merchantId,

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    @Schema(description = "Descripción adicional de la transacción", example = "Pago de servicio de streaming")
    String description,

    @Size(max = 100, message = "El número de referencia no puede exceder 100 caracteres")
    @Schema(description = "Número de referencia único de la transacción", example = "TXN-2024-001234")
    String referenceNumber,

    @JsonProperty("callback_url")
    @Schema(description = "URL de callback para notificaciones asíncronas", example = "https://api.example.com/webhooks/payment")
    String callbackUrl,

    @JsonProperty("metadata")
    @Schema(description = "Metadatos adicionales en formato clave-valor")
    java.util.Map<String, String> metadata
) {
    public TransactionRequest {
        if (currency != null) {
            currency = currency.toUpperCase();
        }
        if (type != null) {
            type = type.toUpperCase();
        }
    }

    public static TransactionRequest withDefaults(TransactionRequest request) {
        return new TransactionRequest(
            request.accountId(),
            request.amount(),
            request.currency() != null ? request.currency().toUpperCase() : "USD",
            request.type() != null ? request.type().toUpperCase() : "PAYMENT",
            request.merchantId(),
            request.description(),
            request.referenceNumber() != null ? request.referenceNumber() : generateReference(),
            request.callbackUrl(),
            request.metadata()
        );
    }

    private static String generateReference() {
        return "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public boolean isValidForProcessing() {
        return accountId != null && amount != null && amount.compareTo(BigDecimal.ZERO) > 0
            && currency != null && !currency.isBlank() && type != null && !type.isBlank()
            && merchantId != null && !merchantId.isBlank();
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/dto/TransactionResponse.java ===
package com.pragma.payments.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Respuesta de transacción de pago con toda la información del procesamiento")
public record TransactionResponse(

    @JsonProperty("id")
    @Schema(description = "Identificador único de la transacción", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID id,

    @JsonProperty("account_id")
    @Schema(description = "Identificador de la cuenta asociada", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID accountId,

    @Schema(description = "Monto de la transacción", example = "150.75")
    BigDecimal amount,

    @Schema(description = "Código de moneda ISO 4217", example = "USD")
    String currency,

    @Schema(description = "Tipo de transacción", example = "PAYMENT")
    String type,

    @Schema(description = "Estado actual de la transacción", example = "PROCESSED")
    String status,

    @JsonProperty("fraud_check_status")
    @Schema(description = "Resultado de la verificación antifraude", example = "APPROVED")
    String fraudCheckStatus,

    @JsonProperty("merchant_id")
    @Schema(description = "Identificador del comercio", example = "MERCH-001")
    String merchantId,

    @Schema(description = "Descripción de la transacción", example = "Pago de servicio")
    String description,

    @JsonProperty("reference_number")
    @Schema(description = "Número de referencia único", example = "TXN-2024-001234")
    String referenceNumber,

    @JsonProperty("created_at")
    @Schema(description = "Fecha de creación de la transacción", example = "2024-01-15T10:30:00Z")
    Instant createdAt,

    @JsonProperty("updated_at")
    @Schema(description = "Última fecha de actualización", example = "2024-01-15T10:30:05Z")
    Instant updatedAt,

    @JsonProperty("processed_at")
    @Schema(description = "Fecha de procesamiento de la transacción", example = "2024-01-15T10:30:03Z")
    Instant processedAt,

    @JsonProperty("error_code")
    @Schema(description = "Código de error si la transacción falló", example = "INSUFFICIENT_FUNDS")
    String errorCode,

    @JsonProperty("error_message")
    @Schema(description = "Mensaje de error detallado", example = "Fondos insuficientes en la cuenta")
    String errorMessage
) {
    public static TransactionResponse fromDomain(Transaction transaction) {
        return new TransactionResponse(
            transaction.id(),
            transaction.accountId(),
            transaction.amount(),
            transaction.currency(),
            transaction.type(),
            transaction.status().name(),
            transaction.fraudCheckStatus().name(),
            transaction.merchantId(),
            transaction.description(),
            transaction.referenceNumber(),
            transaction.createdAt(),
            transaction.updatedAt(),
            null,
            null,
            null
        );
    }

    public static TransactionResponse fromDomainWithProcessingTime(Transaction transaction, Instant processedAt) {
        return new TransactionResponse(
            transaction.id(),
            transaction.accountId(),
            transaction.amount(),
            transaction.currency(),
            transaction.type(),
            transaction.status().name(),
            transaction.fraudCheckStatus().name(),
            transaction.merchantId(),
            transaction.description(),
            transaction.referenceNumber(),
            transaction.createdAt(),
            transaction.updatedAt(),
            processedAt,
            null,
            null
        );
    }

    public static TransactionResponse error(UUID transactionId, String errorCode, String errorMessage) {
        return new TransactionResponse(
            transactionId,
            null,
            null,
            null,
            null,
            TransactionStatus.FAILED.name(),
            FraudCheckStatus.UNKNOWN.name(),
            null,
            null,
            null,
            Instant.now(),
            Instant.now(),
            null,
            errorCode,
            errorMessage
        );
    }

    public boolean isSuccessful() {
        return status != null && status.equals(TransactionStatus.PROCESSED.name());
    }

    public boolean isPending() {
        return status != null && status.equals(TransactionStatus.PENDING.name());
    }

    public boolean isFailed() {
        return status != null && status.equals(TransactionStatus.FAILED.name());
    }

    public boolean hasFraudCheckApproved() {
        return fraudCheckStatus != null && fraudCheckStatus.equals(FraudCheckStatus.APPROVED.name());
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/dto/ErrorResponse.java ===
package com.pragma.payments.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;

@Schema(description = "Respuesta de error estandarizada para todos los endpoints de la API")
public record ErrorResponse(
    @Schema(description = "Código de estado HTTP de la respuesta", example = "400")
    int status,
    
    @Schema(description = "Título breve del error", example = "Bad Request")
    String error,
    
    @Schema(description = "Mensaje descriptivo del error", example = "El monto de la transacción debe ser mayor a cero")
    String message,
    
    @Schema(description = "Timestamp exacto cuando ocurrió el error en formato ISO-8601", example = "2024-01-15T10:30:00.123Z")
    Instant timestamp,
    
    @Schema(description = "Ruta del endpoint que generó el error", example = "/api/v1/transactions")
    String path,
    
    @Schema(description = "Método HTTP utilizado en la petición", example = "POST")
    String method
) {
    public ErrorResponse {
        if (timestamp == null) {
            timestamp = Instant.now();
        }
    }

    public static ErrorResponse of(int status, String error, String message, String path, String method) {
        return new ErrorResponse(status, error, message, Instant.now(), path, method);
    }

    public static ErrorResponse badRequest(String message, String path, String method) {
        return of(400, "Bad Request", message, path, method);
    }

    public static ErrorResponse notFound(String message, String path, String method) {
        return of(404, "Not Found", message, path, method);
    }

    public static ErrorResponse internalError(String message, String path, String method) {
        return of(500, "Internal Server Error", message, path, method);
    }

    public static ErrorResponse serviceUnavailable(String message, String path, String method) {
        return of(503, "Service Unavailable", message, path, method);
    }

    public static ErrorResponse tooManyRequests(String message, String path, String method) {
        return of(429, "Too Many Requests", message, path, method);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/service/TransactionService.java ===
package com.pragma.payments.application.service;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.domain.port.TransactionRepository;
import com.pragma.payments.domain.port.AntiFraudService;
import com.pragma.payments.infrastructure.exception.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private static final BigDecimal MIN_AMOUNT = BigDecimal.valueOf(0.01);
    private static final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(100000.00);

    private final TransactionRepository transactionRepository;
    private final AntiFraudService antiFraudService;

    public TransactionService(TransactionRepository transactionRepository, AntiFraudService antiFraudService) {
        this.transactionRepository = transactionRepository;
        this.antiFraudService = antiFraudService;
    }

    public Transaction createTransaction(String accountId, BigDecimal amount, String currency, 
                                          String merchantId, String description) {
        log.info("Iniciando creación de transacción para cuenta: {}, monto: {}", accountId, amount);
        
        validateTransactionInput(accountId, amount, currency, merchantId);
        
        Transaction transaction = new Transaction(
            UUID.randomUUID(),
            accountId,
            amount,
            currency,
            merchantId,
            description,
            TransactionStatus.PENDING,
            FraudCheckStatus.PENDING,
            Instant.now(),
            Instant.now()
        );
        
        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info("Transacción creada exitosamente con ID: {}", savedTransaction.id());
        
        evaluateFraudAsync(savedTransaction);
        
        return savedTransaction;
    }

    public Transaction processTransaction(UUID transactionId) {
        log.info("Procesando transacción: {}", transactionId);
        
        Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new TransactionException(
                "Transacción no encontrada: " + transactionId, 
                "TRANSACTION_NOT_FOUND", 
                404));
        
        if (transaction.status() == TransactionStatus.COMPLETED) {
            log.warn("Transacción {} ya fue procesada", transactionId);
            return transaction;
        }
        
        if (transaction.fraudCheckStatus() == FraudCheckStatus.FLAGGED) {
            log.warn("Transacción {} marcada como sospechosa por antifraude", transactionId);
            Transaction flaggedTransaction = transaction.withStatus(TransactionStatus.FAILED);
            return transactionRepository.save(flaggedTransaction);
        }
        
        Transaction completedTransaction = transaction.withStatus(TransactionStatus.COMPLETED);
        Transaction saved = transactionRepository.save(completedTransaction);
        
        log.info("Transacción {} procesada exitosamente", transactionId);
        return saved;
    }

    public Transaction getTransaction(UUID transactionId) {
        return transactionRepository.findById(transactionId)
            .orElseThrow(() -> new TransactionException(
                "Transacción no encontrada: " + transactionId,
                "TRANSACTION_NOT_FOUND",
                404));
    }

    public List<Transaction> getTransactionsByAccount(String accountId) {
        log.debug("Consultando transacciones para cuenta: {}", accountId);
        return transactionRepository.findByAccountId(UUID.fromString(accountId));
    }

    public List<Transaction> getTransactionsByMerchant(String merchantId) {
        log.debug("Consultando transacciones para comerciante: {}", merchantId);
        return transactionRepository.findByMerchantId(merchantId);
    }

    public List<Transaction> getTransactionsByStatus(TransactionStatus status) {
        log.debug("Consultando transacciones con estado: {}", status);
        return transactionRepository.findByStatus(status);
    }

    public List<Transaction> getFlaggedTransactions() {
        log.debug("Consultando transacciones marcadas por antifraude");
        return transactionRepository.findByFraudCheckStatus(FraudCheckStatus.FLAGGED);
    }

    public Transaction updateTransactionStatus(UUID transactionId, TransactionStatus newStatus) {
        log.info("Actualizando estado de transacción {} a {}", transactionId, newStatus);
        
        Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new TransactionException(
                "Transacción no encontrada: " + transactionId,
                "TRANSACTION_NOT_FOUND",
                404));
        
        Transaction updatedTransaction = transaction.withStatus(newStatus);
        return transactionRepository.save(updatedTransaction);
    }

    private void validateTransactionInput(String accountId, BigDecimal amount, String currency, String merchantId) {
        if (accountId == null || accountId.isBlank()) {
            throw new TransactionException(
                "El ID de cuenta es obligatorio",
                "INVALID_ACCOUNT_ID",
                400);
        }
        
        if (amount == null || amount.compareTo(MIN_AMOUNT) < 0) {
            throw new TransactionException(
                "El monto debe ser mayor a " + MIN_AMOUNT,
                "INVALID_AMOUNT",
                400);
        }
        
        if (amount.compareTo(MAX_AMOUNT) > 0) {
            throw new TransactionException(
                "El monto no puede exceder " + MAX_AMOUNT,
                "AMOUNT_EXCEEDS_LIMIT",
                400);
        }
        
        if (currency == null || currency.isBlank()) {
            throw new TransactionException(
                "La moneda es obligatoria",
                "INVALID_CURRENCY",
                400);
        }
        
        if (merchantId == null || merchantId.isBlank()) {
            throw new TransactionException(
                "El ID del comerciante es obligatorio",
                "INVALID_MERCHANT_ID",
                400);
        }
    }

    private void evaluateFraudAsync(Transaction transaction) {
        log.info("Enviando transacción {} a evaluación de antifraude", transaction.id());
        
        CompletionStage<Transaction.FraudCheckStatus> fraudResult = antiFraudService.evaluateTransaction(transaction);
        
        fraudResult.thenAccept(status -> {
            log.info("Resultado de antifraude para {}: {}", transaction.id(), status);
            Transaction updatedTransaction = transaction.withFraudCheckStatus(status);
            transactionRepository.save(updatedTransaction);
            
            if (status == FraudCheckStatus.APPROVED) {
                processTransaction(transaction.id());
            }
        }).exceptionally(ex -> {
            log.error("Error en evaluación de antifraude para {}: {}", transaction.id(), ex.getMessage());
            Transaction failedTransaction = transaction.withFraudCheckStatus(FraudCheckStatus.ERROR);
            transactionRepository.save(failedTransaction);
            return null;
        });
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/AntiFraudServiceAdapter.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.domain.port.AntiFraudService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class AntiFraudServiceAdapter implements AntiFraudService {

    private static final Logger log = LoggerFactory.getLogger(AntiFraudServiceAdapter.class);
    private static final BigDecimal HIGH_RISK_THRESHOLD = BigDecimal.valueOf(5000.00);
    private static final BigDecimal MEDIUM_RISK_THRESHOLD = BigDecimal.valueOf(1000.00);
    private static final int MAX_DAILY_TRANSACTIONS = 50;
    private static final Random random = new Random();

    @Value("${app.antifraud.endpoint:http://localhost:8083/api/antifraud}")
    private String antiFraudEndpoint;

    @Value("${app.antifraud.timeout-ms:5000}")
    private int timeoutMs;

    @Override
    @CircuitBreaker(name = "antiFraudCircuitBreaker", fallbackMethod = "fallbackEvaluateTransaction")
    @Retry(name = "antiFraudRetry")
    public CompletionStage<FraudCheckStatus> evaluateTransaction(Transaction transaction) {
        log.info("Evaluando transacción {} en servicio antifraude", transaction.id());
        log.debug("Endpoint de antifraude: {}, timeout: {}ms", antiFraudEndpoint, timeoutMs);
        
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random.nextInt(500) + 100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            FraudCheckStatus status = performRiskAnalysis(transaction);
            return status;
        });
    }

    private FraudCheckStatus performRiskAnalysis(Transaction transaction) {
        log.debug("Analizando riesgo para transacción: {}, monto: {}", 
            transaction.id(), transaction.amount());
        
        if (transaction.amount().compareTo(HIGH_RISK_THRESHOLD) > 0) {
            log.warn("Transacción {} flaggeada por monto alto: {}", 
                transaction.id(), transaction.amount());
            return FraudCheckStatus.FLAGGED;
        }
        
        if (isUnusualPattern(transaction)) {
            log.warn("Transacción {} flaggeada por patrón inusual", transaction.id());
            return FraudCheckStatus.FLAGGED;
        }
        
        if (isHighFrequencyTransaction(transaction)) {
            log.warn("Transacción {} flaggeada por alta frecuencia", transaction.id());
            return FraudCheckStatus.FLAGGED;
        }
        
        if (transaction.amount().compareTo(MEDIUM_RISK_THRESHOLD) > 0) {
            log.info("Transacción {} requiere revisión manual", transaction.id());
            return FraudCheckStatus.REVIEW;
        }
        
        log.info("Transacción {} aprobada por antifraude", transaction.id());
        return FraudCheckStatus.APPROVED;
    }

    private boolean isUnusualPattern(Transaction transaction) {
        String merchantId = transaction.merchantId();
        if (merchantId != null && merchantId.contains("TEST")) {
            return true;
        }
        
        String description = transaction.description();
        if (description != null && (description.toLowerCase().contains("sospechoso") || 
                                    description.toLowerCase().contains("fraude"))) {
            return true;
        }
        
        return false;
    }

    private boolean isHighFrequencyTransaction(Transaction transaction) {
        if (transaction.amount().compareTo(BigDecimal.valueOf(100)) > 0) {
            int hourOfDay = java.time.LocalDateTime.now().getHour();
            if (hourOfDay >= 2 && hourOfDay <= 5) {
                log.debug("Transacción {} en horario de alto riesgo ({}:00)", 
                    transaction.id(), hourOfDay);
                return true;
            }
        }
        return false;
    }

    private CompletionStage<FraudCheckStatus> fallbackEvaluateTransaction(Transaction transaction, Throwable ex) {
        log.error("Fallback activado para evaluación antifraude de {}. Error: {}", 
            transaction.id(), ex.getMessage());
        
        if (transaction.amount().compareTo(HIGH_RISK_THRESHOLD) > 0) {
            return CompletableFuture.completedFuture(FraudCheckStatus.FLAGGED);
        }
        
        return CompletableFuture.completedFuture(FraudCheckStatus.REVIEW);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/OpenApiConfig.java ===
package com.pragma.payments.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Payments API")
                        .version("1.0.0")
                        .description("""
                                Sistema de pagos distribuido con procesamiento en tiempo real.
                                Procesa transacciones con throughput de 10,000 TPS y SLA 99.9%.
                                """)
                        .contact(new Contact()
                                .name("Pragma Payments Team")
                                .email("payments@pragma.co")))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("""
                                                Token JWT requerido para autenticación.
                                                Obtener mediante el endpoint de login.
                                                """)));
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java ===
package com.pragma.payments.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .slowCallRateThreshold(50)
                .slowCallDurationThreshold(Duration.ofSeconds(2))
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .minimumNumberOfCalls(10)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(Exception.class)
                .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public BulkheadRegistry bulkheadRegistry() {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(100)
                .maxWaitDuration(Duration.ofMillis(500))
                .build();
        return BulkheadRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java ===
package com.pragma.payments.infrastructure.controller;



import com.pragma.payments.domain.model.FraudCheckStatus;
import com.pragma.payments.domain.model.TransactionStatus;
import com.pragma.payments.application.service.TransactionService;
import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.infrastructure.dto.ErrorResponse;
import com.pragma.payments.infrastructure.dto.TransactionRequest;
import com.pragma.payments.infrastructure.dto.TransactionResponse;
import com.pragma.payments.infrastructure.exception.TransactionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@Tag(name = "Transacciones", description = "Operaciones CRUD para gestión de transacciones de pago")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @Operation(summary = "Crear transacción", description = "Registra una nueva transacción de pago en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transacción creada exitosamente",
                    content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(request.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionResponse.fromDomain(transaction));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener transacción por ID", description = "Recupera los detalles de una transacción específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacción encontrada",
                    content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<TransactionResponse> getTransactionById(
            @Parameter(description = "UUID de la transacción", required = true)
            @PathVariable UUID id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(TransactionResponse.fromDomain(transaction));
    }

    @GetMapping
    @Operation(summary = "Listar transacciones", description = "Obtiene todas las transacciones con filtros opcionales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transacciones",
                    content = @Content(schema = @Schema(implementation = TransactionResponse.class)))
    })
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(
            @Parameter(description = "Filtrar por ID de cuenta")
            @RequestParam(required = false) UUID accountId,
            @Parameter(description = "Filtrar por estado de transacción")
            @RequestParam(required = false) Transaction.TransactionStatus status,
            @Parameter(description = "Filtrar por estado de verificación antifraude")
            @RequestParam(required = false) Transaction.FraudCheckStatus fraudStatus,
            @Parameter(description = "Filtrar por ID de comerciante")
            @RequestParam(required = false) String merchantId) {
        List<Transaction> transactions = transactionService.getTransactionsByFilters(
                accountId, status, fraudStatus, merchantId);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Actualizar estado de transacción", description = "Modifica el estado de una transacción existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Estado inválido para la transición",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<TransactionResponse> updateTransactionStatus(
            @Parameter(description = "UUID de la transacción", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Nuevo estado de la transacción", required = true)
            @RequestParam Transaction.TransactionStatus status) {
        Transaction transaction = transactionService.updateStatus(id, status);
        return ResponseEntity.ok(TransactionResponse.fromDomain(transaction));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar transacción", description = "Elimina una transacción del sistema (soft delete)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transacción eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deleteTransaction(
            @Parameter(description = "UUID de la transacción", required = true)
            @PathVariable UUID id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ErrorResponse> handleTransactionException(TransactionException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getHttpStatus().value(),
                ex.getMessage(),
                "/api/v1/transactions",
                "POST");
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/TransactionException.java ===
package com.pragma.payments.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class TransactionException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String transactionId;

    public enum ErrorCode {
        DUPLICATE_TRANSACTION("PAGO-001", HttpStatus.CONFLICT, "Transacción duplicada"),
        INSUFFICIENT_FUNDS("PAGO-002", HttpStatus.PAYMENT_REQUIRED, "Fondos insuficientes"),
        INVALID_AMOUNT("PAGO-003", HttpStatus.BAD_REQUEST, "Monto inválido para la transacción"),
        ACCOUNT_NOT_FOUND("PAGO-004", HttpStatus.NOT_FOUND, "Cuenta no encontrada"),
        MERCHANT_NOT_FOUND("PAGO-005", HttpStatus.NOT_FOUND, "Comercio no encontrado"),
        FRAUD_DETECTED("PAGO-006", HttpStatus.FORBIDDEN, "Transacción bloqueada por sospecha de fraude"),
        FRAUD_CHECK_TIMEOUT("PAGO-007", HttpStatus.SERVICE_UNAVAILABLE, "Tiempo de espera agotado en verificación antifraude"),
        EXTERNAL_SERVICE_ERROR("PAGO-008", HttpStatus.BAD_GATEWAY, "Error en servicio externo"),
        INVALID_CURRENCY("PAGO-009", HttpStatus.BAD_REQUEST, "Moneda no soportada"),
        TRANSACTION_EXPIRED("PAGO-010", HttpStatus.GONE, "Transacción expirada"),
        TRANSACTION_NOT_FOUND("PAGO-011", HttpStatus.NOT_FOUND, "Transacción no encontrada"),
        INVALID_STATE_TRANSITION("PAGO-012", HttpStatus.BAD_REQUEST, "Transición de estado inválida"),
        AUTHORIZATION_FAILED("PAGO-013", HttpStatus.UNAUTHORIZED, "Autorización denegada"),
        RATE_LIMIT_EXCEEDED("PAGO-014", HttpStatus.TOO_MANY_REQUESTS, "Límite de solicitudes excedido"),
        INTERNAL_PROCESSING_ERROR("PAGO-015", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno en procesamiento");

        private final String code;
        private final HttpStatus httpStatus;
        private final String defaultMessage;

        ErrorCode(String code, HttpStatus httpStatus, String defaultMessage) {
            this.code = code;
            this.httpStatus = httpStatus;
            this.defaultMessage = defaultMessage;
        }

        public String getCode() {
            return code;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }
    }

    public TransactionException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
        this.transactionId = null;
    }

    public TransactionException(ErrorCode errorCode, String message, String transactionId) {
        super(message);
        this.errorCode = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
        this.transactionId = transactionId;
    }

    public TransactionException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
        this.transactionId = null;
    }

    public TransactionException(ErrorCode errorCode, String message, String transactionId, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
        this.transactionId = transactionId;
    }

    public static TransactionException duplicateTransaction(String transactionId) {
        return new TransactionException(
            ErrorCode.DUPLICATE_TRANSACTION,
            "Ya existe una transacción con el identificador proporcionado",
            transactionId
        );
    }

    public static TransactionException insufficientFunds(String transactionId, String accountId) {
        return new TransactionException(
            ErrorCode.INSUFFICIENT_FUNDS,
            "La cuenta " + accountId + " no tiene fondos suficientes para completar la transacción",
            transactionId
        );
    }

    public static TransactionException fraudDetected(String transactionId, String reason) {
        return new TransactionException(
            ErrorCode.FRAUD_DETECTED,
            "Transacción bloqueada: " + reason,
            transactionId
        );
    }

    public static TransactionException transactionNotFound(UUID transactionId) {
        return new TransactionException(
            ErrorCode.TRANSACTION_NOT_FOUND,
            "No se encontró la transacción con ID: " + transactionId,
            transactionId.toString()
        );
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java ===
package com.pragma.payments.infrastructure.exception;

import com.pragma.payments.infrastructure.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String DEFAULT_ERROR_MESSAGE = "Ha ocurrido un error inesperado";

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ErrorResponse> handleTransactionException(
            TransactionException ex, WebRequest request) {
        
        logger.warn("Error de transacción: código={}, mensaje={}, transacción={}",
                ex.getErrorCode(), ex.getMessage(), ex.getTransactionId());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(ex.getHttpStatus().value())
                .error(ex.getHttpStatus().getReasonPhrase())
                .code(ex.getErrorCode())
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .transactionId(ex.getTransactionId())
                .build();

        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null 
                                ? error.getDefaultMessage() 
                                : "Valor inválido",
                        (existing, replacement) -> existing
                ));

        String detailedMessage = fieldErrors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));

        logger.warn("Error de validación: {}", detailedMessage);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .code("VALIDATION-001")
                .message("Error de validación en los datos proporcionados")
                .details(fieldErrors)
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        
        Map<String, String> violations = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        violation -> violation.getMessage(),
                        (existing, replacement) -> existing
                ));

        logger.warn("Violación de constrain: {}", violations);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .code("VALIDATION-002")
                .message("Violación de restricciones de validación")
                .details(violations)
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        
        String message = String.format("El parámetro '%s' esperaba un valor de tipo %s pero recibió '%s'",
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido",
                ex.getValue());

        logger.warn("Tipo de argumento incorrecto: {}", message);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .code("TYPE_MISMATCH-001")
                .message(message)
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleUnreadableMessage(
            HttpMessageNotReadableException ex, WebRequest request) {
        
        logger.warn("Mensaje no legible: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .code("PARSE-001")
                .message("El cuerpo de la solicitud no puede ser procesado. Verifique el formato JSON")
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        
        logger.warn("Argumento ilegal: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .code("ARGUMENT-001")
                .message(ex.getMessage() != null ? ex.getMessage() : "Argumento inválido")
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            Exception ex, WebRequest request) {
        
        logger.error("Error no manejado: mensaje={}, tipo={}, traza={}",
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                ex.getStackTrace()[0].toString(),
                ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .code("INTERNAL-001")
                .message(DEFAULT_ERROR_MESSAGE)
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

// === ARCHIVO: src/main/resources/static/api-docs.md ===
# Sistema de Pagos Distribuido - Documentación de API

## Descripción General

El Sistema de Pagos es una plataforma de procesamiento de transacciones en tiempo real diseñada para manejar hasta 10,000 transacciones por segundo con un SLA de 99.9%. El sistema integra múltiples servicios externos incluyendo gateway de pagos, core bancario, sistema de liquidación y motor antifraude.

### Características Principales

- Procesamiento de pagos en tiempo real
- Verificación antifraude asíncrona
- Tolerancia a fallos con CircuitBreaker y Retry
- Documentación automática con OpenAPI 3.0
- Soporte para múltiples monedas y métodos de pago
- Auditoría completa de transacciones

### URLs Base

| Ambiente | URL Base |
|----------|----------|
| Desarrollo | `http://localhost:8080` |
| Staging | `https://staging.payments.pragma.com` |
| Producción | `https://payments.pragma.com` |

---

## Endpoints

### 1. Crear Transacción

Crea una nueva transacción de pago en el sistema.

**Endpoint:** `POST /api/v1/transactions`

**Encabezados Requeridos:**
```
Content-Type: application/json
X-Request-ID: uuid-opcional
Authorization: Bearer {token}
```

**Cuerpo de la Solicitud:**
```json
{
  "accountId": "550e8400-e29b-41d4-a716-446655440000",
  "merchantId": "MERCH-001",
  "amount": 1500.50,
  "currency": "USD",
  "paymentMethod": "CREDIT_CARD",
  "description": "Compra en tienda en línea",
  "metadata": {
    "orderId": "ORD-12345",
    "customerEmail": "cliente@ejemplo.com"
  }
}
```

**Respuesta Exitosa (201 Created):**
```json
{
  "id": "660e8400-e29b-41d4-a716-446655440001",
  "accountId": "550e8400-e29b-41d4-a716-446655440000",
  "merchantId": "MERCH-001",
  "amount": 1500.50,
  "currency": "USD",
  "paymentMethod": "CREDIT_CARD",
  "status": "PROCESSING",
  "fraudCheckStatus": "PENDING",
  "description": "Compra en tienda en línea",
  "createdAt": "2024-01-15T10:30:00Z",
  "updatedAt": "2024-01-15T10:30:00Z",
  "metadata": {
    "orderId": "ORD-12345",
    "customerEmail": "cliente@ejemplo.com"
  }
}
```

**Códigos de Respuesta:**
| Código | Descripción |
|--------|-------------|
| 201 | Transacción creada exitosamente |
| 400 | Solicitud inválida (validación fallida) |
| 401 | No autorizado (token faltante o inválido) |
| 409 | Transacción duplicada |
| 422 | Entidad no procesable (fondos insuficientes) |
| 429 | Demasiadas solicitudes (rate limit) |
| 500 | Error interno del servidor |

---

### 2. Consultar Transacción por ID

Obtiene los detalles de una transacción específica.

**Endpoint:** `GET /api/v1/transactions/{id}`

**Parámetros de Ruta:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| id | UUID | Identificador único de la transacción |

**Respuesta Exitosa (200 OK):**
```json
{
  "id": "660e8400-e29b-41d4-a716-446655440001",
  "accountId": "550e8400-e29b-41d4-a716-446655440000",
  "merchantId": "MERCH-001",
  "amount": 1500.50,
  "currency": "USD",
  "paymentMethod": "CREDIT_CARD",
  "status": "COMPLETED",
  "fraudCheckStatus": "APPROVED",
  "description": "Compra en tienda en línea",
  "createdAt": "2024-01-15T10:30:00Z",
  "updatedAt": "2024-01-15T10:30:05Z",
  "completedAt": "2024-01-15T10:30:05Z",
  "metadata": {
    "orderId": "ORD-12345",
    "customerEmail": "cliente@ejemplo.com"
  }
}
```

**Códigos de Respuesta:**
| Código | Descripción |
|--------|-------------|
| 200 | Transacción encontrada |
| 404 | Transacción no encontrada |
| 429 | Demasiadas solicitudes |

---

### 3. Listar Transacciones por Cuenta

Obtiene todas las transacciones asociadas a una cuenta específica.

**Endpoint:** `GET /api/v1/transactions/account/{accountId}`

**Parámetros de Ruta:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| accountId | UUID | Identificador de la cuenta |

**Parámetros de Consulta:**
| Parámetro | Tipo | Requerido | Descripción |
|-----------|------|----------|-------------|
| page | integer | No | Número de página (default: 0) |
| size | integer | No | Tamaño de página (default: 20) |
| status | string | No | Filtrar por estado de transacción |

**Ejemplo de Solicitud:**
```
GET /api/v1/transactions/account/550e8400-e29b-41d4-a716-446655440000?page=0&size=10&status=COMPLETED
```

**Respuesta Exitosa (200 OK):**
```json
{
  "content": [
    {
      "id": "660e8400-e29b-41d4-a716-446655440001",
      "accountId": "550e8400-e29b-41d4-a716-446655440000",
      "merchantId": "MERCH-001",
      "amount": 1500.50,
      "currency": "USD",
      "status": "COMPLETED",
      "fraudCheckStatus": "APPROVED",
      "createdAt": "2024-01-15T10:30:00Z"
    },
    {
      "id": "660e8400-e29b-41d4-a716-446655440002",
      "accountId": "550e8400-e29b-41d4-a716-446655440000",
      "merchantId": "MERCH-002",
      "amount": 250.00,
      "currency": "USD",
      "status": "COMPLETED",
      "fraudCheckStatus": "APPROVED",
      "createdAt": "2024-01-14T15:20:00Z"
    }
  ],
  "page": 0,
  "size": 20,
  "totalElements": 2,
  "totalPages": 1
}
```

---

### 4. Listar Transacciones por Estado

Obtiene transacciones filtradas por estado.

**Endpoint:** `GET /api/v1/transactions/status/{status}`

**Estados de Transacción:**
| Estado | Descripción |
|--------|-------------|
| PENDING | Transacción creada, esperando procesamiento |
| PROCESSING | Transacción en proceso de verificación |
| COMPLETED | Transacción completada exitosamente |
| FAILED | Transacción fallida |
| CANCELLED | Transacción cancelada |
| EXPIRED | Transacción expirada sin completarse |

---

### 5. Consultar Estado de Verificación Antifraude

Obtiene el estado de la verificación antifraude de una transacción.

**Endpoint:** `GET /api/v1/transactions/{id}/fraud-status`

**Estados de Verificación Antifraude:**
| Estado | Descripción |
|--------|-------------|
| PENDING | Verificación en progreso |
| APPROVED | Transacción aprobada por antifraude |
| REJECTED | Transacción rechazada por antifraude |
| ERROR | Error en el servicio de verificación |
| TIMEOUT | Tiempo de verificación agotado |

---

## Esquemas de Datos

### TransactionRequest

```json
{
  "accountId": "uuid (requerido)",
  "merchantId": "string (requerido, máx 50 caracteres)",
  "amount": "bigdecimal (requerido, mayor a 0)",
  "currency": "string (requerido, 3 letras ISO 4217)",
  "paymentMethod": "string (requerido)",
  "description": "string (opcional, máx 255 caracteres)",
  "metadata": "object (opcional)"
}
```

### TransactionResponse

```json
{
  "id": "uuid",
  "accountId": "uuid",
  "merchantId": "string",
  "amount": "bigdecimal",
  "currency": "string",
  "paymentMethod": "string",
  "status": "string",
  "fraudCheckStatus": "string",
  "description": "string",
  "createdAt": "datetime ISO 8601",
  "updatedAt": "datetime ISO 8601",
  "completedAt": "datetime ISO 8601 (nullable)",
  "metadata": "object"
}
```

### ErrorResponse

```json
{
  "status": "integer",
  "error": "string",
  "code": "string",
  "message": "string",
  "details": "object (opcional)",
  "path": "string",
  "timestamp": "datetime ISO 8601",
  "transactionId": "string (opcional)"
}
```

---

## Códigos de Error

| Código | Descripción | Causa Común |
|--------|-------------|-------------|
| PAGO-001 | Transacción duplicada |相同 ID de transacción enviado dos veces |
| PAGO-002 | Fondos insuficientes | Saldo en cuenta menor al monto |
| PAGO-003 | Monto inválido | Monto negativo o cero |
| PAGO-004 | Cuenta no encontrada | UUID de cuenta inexistente |
| PAGO-005 | Comercio no encontrado | ID de comercio inválido |
| PAGO-006 | Fraude detectado | Patrón de transacción sospechoso |
| PAGO-007 | Timeout antifraude | Servicio antifraude no respondió a tiempo |
| PAGO-008 | Error en servicio externo | Fallo en gateway o banco |
| PAGO-009 | Moneda no soportada | Código de moneda no válido |
| PAGO-010 | Transacción expirada | Tiempo de espera excedido |
| PAGO-011 | Transacción no encontrada | ID de transacción inválido |
| PAGO-012 | Transición de estado inválida | Intento de cambio de estado no permitido |
| PAGO-013 | Autorización denegada | Token inválido o expirado |
| PAGO-014 | Rate limit excedido | Demasiadas solicitudes |
| PAGO-015 | Error interno | Fallo no categorizado |
| VALIDATION-001 | Error de validación | Datos de entrada inválidos |
| INTERNAL-001 | Error interno del servidor | Fallo no manejado |

---

## Métodos de Pago Soportados

| Método | Código | Descripción |
|--------|--------|-------------|
| Tarjeta de Crédito | CREDIT_CARD | Visa, Mastercard, Amex |
| Tarjeta de Débito | DEBIT_CARD | Tarjetas de débito bancario |
| Transferencia | BANK_TRANSFER | Transferencia ACH |
| Billetera Digital | DIGITAL_WALLET | PayPal, MercadoPago, etc. |
| Efectivo | CASH | Pago en efectivo (presencial) |

---

## Monedas Soportadas

| Código | Nombre | Símbolo |
|--------|--------|---------|
| USD | Dólar Estadounidense | $ |
| EUR | Euro | € |
| GBP | Libra Esterlina | £ |
| MXN | Peso Mexicano | $ |
| COP | Peso Colombiano | $ |
| ARS | Peso Argentino | $ |

---

## Consideraciones de Seguridad

### Autenticación
- Todas las solicitudes requieren token Bearer en el encabezado `Authorization`
- Los tokens expiran después de 1 hora
- Usar HTTPS para todas las solicitudes en producción

### Rate Limiting
- Límite: 1000 solicitudes por minuto por IP
- Encabezados de respuesta: `X-RateLimit-Limit`, `X-RateLimit-Remaining`
- Código de respuesta: 429 cuando se excede

### Validación
- Todos los campos obligatorios son validados
- Longitudes máximas aplicadas a strings
- Formato de UUID requerido para identificadores
- Montos必须是正数

---

## Paginación

Los endpoints que devuelven listas soportan paginación con los siguientes parámetros:

| Parámetro | Default | Máximo | Descripción |
|-----------|---------|--------|-------------|
| page | 0 | - | Número de página |
| size | 20 | 100 | Elementos por página |
| sort | createdAt,desc | - | Ordenamiento (campo,dirección) |

La respuesta incluye metadatos de paginación: `page`, `size`, `totalElements`, `totalPages`.

---

## Versionado

La API usa versionado en la URL: `/api/v1/`

Las versiones mayores indican cambios incompatibles. Se mantendrá compatibilidad hacia atrás dentro de la misma versión mayor.

---

## Contacto y Soporte

- **Equipo de Desarrollo:** dev-payments@pragma.com
- **Documentación Técnica:** docs.payments.pragma.com
- **Estado del Sistema:** status.pragma.com
- **Emergencias (24/7):** +1-800-PRAGMA-0

// === ARCHIVO: src/test/java/com/pragma/payments/application/service/TransactionServiceTest.java ===
package com.pragma.payments.application.service;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.domain.port.TransactionRepository;
import com.pragma.payments.domain.port.AntiFraudService;
import com.pragma.payments.infrastructure.dto.TransactionRequest;
import com.pragma.payments.infrastructure.dto.TransactionResponse;
import com.pragma.payments.infrastructure.exception.TransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para TransactionService")
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AntiFraudService antiFraudService;

    @InjectMocks
    private TransactionService transactionService;

    private TransactionRequest transactionRequest;
    private Transaction transaction;
    private UUID transactionId;
    private UUID accountId;

    @BeforeEach
    void setUp() {
        transactionId = UUID.randomUUID();
        accountId = UUID.randomUUID();

        transactionRequest = new TransactionRequest(
            accountId.toString(),
            "MERCHANT_001",
            new BigDecimal("1500.00"),
            "USD",
            "PURCHASE",
            "Orden de compra #12345"
        );

        transaction = new Transaction(
            transactionId,
            accountId,
            "MERCHANT_001",
            new BigDecimal("1500.00"),
            "USD",
            "PURCHASE",
            "Orden de compra #12345",
            TransactionStatus.PENDING,
            FraudCheckStatus.PENDING,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    @Nested
    @DisplayName("Creación de transacciones")
    class CreateTransactionTests {

        @Test
        @DisplayName("Debe crear una transacción exitosamente cuando el antifraude aprueba")
        void shouldCreateTransactionSuccessfullyWhenAntiFraudApproves() {
            when(antiFraudService.evaluateTransaction(any(Transaction.class)))
                .thenReturn(CompletableFuture.completedFuture(FraudCheckStatus.APPROVED));
            when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

            TransactionResponse response = transactionService.createTransaction(transactionRequest);

            assertThat(response).isNotNull();
            assertThat(response.accountId()).isEqualTo(accountId.toString());
            assertThat(response.status()).isEqualTo(TransactionStatus.PENDING.name());
            assertThat(response.fraudCheckStatus()).isEqualTo(FraudCheckStatus.APPROVED.name());
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe rechazar transacción cuando el antifraude la marca como sospechosa")
        void shouldRejectTransactionWhenAntiFraudMarksAsSuspect() {
            when(antiFraudService.evaluateTransaction(any(Transaction.class)))
                .thenReturn(CompletableFuture.completedFuture(FraudCheckStatus.SUSPECTED));
            when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

            TransactionResponse response = transactionService.createTransaction(transactionRequest);

            assertThat(response).isNotNull();
            assertThat(response.fraudCheckStatus()).isEqualTo(FraudCheckStatus.SUSPECTED.name());
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe rechazar transacción cuando el antifraude la marca como fraudulenta")
        void shouldRejectTransactionWhenAntiFraudMarksAsFraudulent() {
            when(antiFraudService.evaluateTransaction(any(Transaction.class)))
                .thenReturn(CompletableFuture.completedFuture(FraudCheckStatus.REJECTED));
            when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

            TransactionResponse response = transactionService.createTransaction(transactionRequest);

            assertThat(response).isNotNull();
            assertThat(response.fraudCheckStatus()).isEqualTo(FraudCheckStatus.REJECTED.name());
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el monto es nulo")
        void shouldThrowExceptionWhenAmountIsNull() {
            TransactionRequest invalidRequest = new TransactionRequest(
                accountId.toString(),
                "MERCHANT_001",
                null,
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            assertThatThrownBy(() -> transactionService.createTransaction(invalidRequest))
                .isInstanceOf(TransactionException.class)
                .hasMessageContaining("monto");
            verify(transactionRepository, never()).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el monto es negativo")
        void shouldThrowExceptionWhenAmountIsNegative() {
            TransactionRequest invalidRequest = new TransactionRequest(
                accountId.toString(),
                "MERCHANT_001",
                new BigDecimal("-100.00"),
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            assertThatThrownBy(() -> transactionService.createTransaction(invalidRequest))
                .isInstanceOf(TransactionException.class)
                .hasMessageContaining("positivo");
            verify(transactionRepository, never()).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el accountId es vacío")
        void shouldThrowExceptionWhenAccountIdIsEmpty() {
            TransactionRequest invalidRequest = new TransactionRequest(
                "",
                "MERCHANT_001",
                new BigDecimal("100.00"),
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            assertThatThrownBy(() -> transactionService.createTransaction(invalidRequest))
                .isInstanceOf(TransactionException.class);
            verify(transactionRepository, never()).save(any(Transaction.class));
        }
    }

    @Nested
    @DisplayName("Consulta de transacciones")
    class QueryTransactionTests {

        @Test
        @DisplayName("Debe obtener una transacción por su ID")
        void shouldGetTransactionById() {
            when(transactionRepository.findById(transactionId))
                .thenReturn(Optional.of(transaction));

            TransactionResponse response = transactionService.getTransactionById(transactionId);

            assertThat(response).isNotNull();
            assertThat(response.id()).isEqualTo(transactionId.toString());
            assertThat(response.accountId()).isEqualTo(accountId.toString());
            verify(transactionRepository, times(1)).findById(transactionId);
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando la transacción no existe")
        void shouldThrowExceptionWhenTransactionNotFound() {
            UUID nonExistentId = UUID.randomUUID();
            when(transactionRepository.findById(nonExistentId))
                .thenReturn(Optional.empty());

            assertThatThrownBy(() -> transactionService.getTransactionById(nonExistentId))
                .isInstanceOf(TransactionException.class)
                .hasMessageContaining("no encontrada");
        }

        @Test
        @DisplayName("Debe obtener transacciones por accountId")
        void shouldGetTransactionsByAccountId() {
            List<Transaction> transactions = List.of(transaction);
            when(transactionRepository.findByAccountId(accountId))
                .thenReturn(transactions);

            List<TransactionResponse> responses = transactionService.getTransactionsByAccountId(accountId);

            assertThat(responses).hasSize(1);
            assertThat(responses.get(0).accountId()).isEqualTo(accountId.toString());
            verify(transactionRepository, times(1)).findByAccountId(accountId);
        }

        @Test
        @DisplayName("Debe obtener transacciones por status")
        void shouldGetTransactionsByStatus() {
            List<Transaction> transactions = List.of(transaction);
            when(transactionRepository.findByStatus(TransactionStatus.PENDING))
                .thenReturn(transactions);

            List<TransactionResponse> responses = transactionService.getTransactionsByStatus(TransactionStatus.PENDING);

            assertThat(responses).hasSize(1);
            assertThat(responses.get(0).status()).isEqualTo(TransactionStatus.PENDING.name());
            verify(transactionRepository, times(1)).findByStatus(TransactionStatus.PENDING);
        }

        @Test
        @DisplayName("Debe obtener transacciones por merchantId")
        void shouldGetTransactionsByMerchantId() {
            List<Transaction> transactions = List.of(transaction);
            String merchantId = "MERCHANT_001";
            when(transactionRepository.findByMerchantId(merchantId))
                .thenReturn(transactions);

            List<TransactionResponse> responses = transactionService.getTransactionsByMerchantId(merchantId);

            assertThat(responses).hasSize(1);
            assertThat(responses.get(0).merchantId()).isEqualTo(merchantId);
            verify(transactionRepository, times(1)).findByMerchantId(merchantId);
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el accountId es inválido")
        void shouldThrowExceptionWhenAccountIdIsInvalid() {
            assertThatThrownBy(() -> transactionService.getTransactionsByAccountId(null))
                .isInstanceOf(TransactionException.class);
        }
    }

    @Nested
    @DisplayName("Actualización de transacciones")
    class UpdateTransactionTests {

        @Test
        @DisplayName("Debe actualizar el status de una transacción exitosamente")
        void shouldUpdateTransactionStatus() {
            Transaction updatedTransaction = transaction.withStatus(TransactionStatus.COMPLETED);
            when(transactionRepository.findById(transactionId))
                .thenReturn(Optional.of(transaction));
            when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(updatedTransaction);

            TransactionResponse response = transactionService.updateTransactionStatus(
                transactionId, TransactionStatus.COMPLETED);

            assertThat(response).isNotNull();
            assertThat(response.status()).isEqualTo(TransactionStatus.COMPLETED.name());
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe lanzar excepción al actualizar una transacción inexistente")
        void shouldThrowExceptionWhenUpdatingNonExistentTransaction() {
            UUID nonExistentId = UUID.randomUUID();
            when(transactionRepository.findById(nonExistentId))
                .thenReturn(Optional.empty());

            assertThatThrownBy(() -> transactionService.updateTransactionStatus(
                nonExistentId, TransactionStatus.COMPLETED))
                .isInstanceOf(TransactionException.class);
            verify(transactionRepository, never()).save(any(Transaction.class));
        }
    }

    @Nested
    @DisplayName("Integración con AntiFraudService")
    class AntiFraudIntegrationTests {

        @Test
        @DisplayName("Debe manejar falla en el servicio antifraude gracefully")
        void shouldHandleAntiFraudServiceFailure() {
            when(antiFraudService.evaluateTransaction(any(Transaction.class)))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Servicio no disponible")));
            when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

            TransactionResponse response = transactionService.createTransaction(transactionRequest);

            assertThat(response).isNotNull();
            assertThat(response.fraudCheckStatus()).isEqualTo(FraudCheckStatus.PENDING.name());
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe esperar el resultado del antifraude de forma asíncrona")
        void shouldWaitForAsyncAntiFraudResult() {
            when(antiFraudService.evaluateTransaction(any(Transaction.class)))
                .thenReturn(CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return FraudCheckStatus.APPROVED;
                }));
            when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

            long startTime = System.currentTimeMillis();
            TransactionResponse response = transactionService.createTransaction(transactionRequest);
            long endTime = System.currentTimeMillis();

            assertThat(response).isNotNull();
            assertThat(response.fraudCheckStatus()).isEqualTo(FraudCheckStatus.APPROVED.name());
            assertThat(endTime - startTime).isGreaterThanOrEqualTo(50);
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/infrastructure/controller/TransactionControllerTest.java ===
package com.pragma.payments.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.payments.application.service.TransactionService;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.infrastructure.dto.TransactionRequest;
import com.pragma.payments.infrastructure.dto.TransactionResponse;
import com.pragma.payments.infrastructure.exception.TransactionException;
import com.pragma.payments.infrastructure.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@Import(GlobalExceptionHandler.class)
@DisplayName("Tests de integración para TransactionController")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    private TransactionRequest transactionRequest;
    private TransactionResponse transactionResponse;
    private UUID transactionId;
    private UUID accountId;
    private String transactionRequestJson;

    @BeforeEach
    void setUp() throws Exception {
        transactionId = UUID.randomUUID();
        accountId = UUID.randomUUID();

        transactionRequest = new TransactionRequest(
            accountId.toString(),
            "MERCHANT_001",
            new BigDecimal("1500.00"),
            "USD",
            "PURCHASE",
            "Orden de compra #12345"
        );

        transactionResponse = new TransactionResponse(
            transactionId.toString(),
            accountId.toString(),
            "MERCHANT_001",
            new BigDecimal("1500.00"),
            "USD",
            "PURCHASE",
            "Orden de compra #12345",
            TransactionStatus.PENDING.name(),
            FraudCheckStatus.APPROVED.name(),
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString()
        );

        transactionRequestJson = objectMapper.writeValueAsString(transactionRequest);
    }

    @Nested
    @DisplayName("POST /api/v1/transactions - Crear transacción")
    class CreateTransactionTests {

        @Test
        @DisplayName("Debe crear una transacción exitosamente con datos válidos")
        void shouldCreateTransactionWithValidData() throws Exception {
            when(transactionService.createTransaction(any(TransactionRequest.class)))
                .thenReturn(transactionResponse);

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionRequestJson));

            result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(transactionId.toString()))
                .andExpect(jsonPath("$.accountId").value(accountId.toString()))
                .andExpect(jsonPath("$.merchantId").value("MERCHANT_001"))
                .andExpect(jsonPath("$.amount").value(1500.00))
                .andExpect(jsonPath("$.status").value(TransactionStatus.PENDING.name()))
                .andExpect(jsonPath("$.fraudCheckStatus").value(FraudCheckStatus.APPROVED.name()));
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el accountId está vacío")
        void shouldReturn400WhenAccountIdIsEmpty() throws Exception {
            TransactionRequest invalidRequest = new TransactionRequest(
                "",
                "MERCHANT_001",
                new BigDecimal("100.00"),
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            when(transactionService.createTransaction(any(TransactionRequest.class)))
                .thenThrow(new TransactionException("El accountId no puede estar vacío"));

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el monto es negativo")
        void shouldReturn400WhenAmountIsNegative() throws Exception {
            TransactionRequest invalidRequest = new TransactionRequest(
                accountId.toString(),
                "MERCHANT_001",
                new BigDecimal("-50.00"),
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            when(transactionService.createTransaction(any(TransactionRequest.class)))
                .thenThrow(new TransactionException("El monto debe ser positivo"));

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el monto es nulo")
        void shouldReturn400WhenAmountIsNull() throws Exception {
            TransactionRequest invalidRequest = new TransactionRequest(
                accountId.toString(),
                "MERCHANT_001",
                null,
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el JSON está mal formado")
        void shouldReturn400WhenJsonIsMalformed() throws Exception {
            String malformedJson = "{\"accountId\": \"\", \"merchantId\": \"MERCHANT\"}";

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(malformedJson));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 415 cuando el content-type es incorrecto")
        void shouldReturn415WhenContentTypeIsIncorrect() throws Exception {
            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.TEXT_PLAIN)
                .content(transactionRequestJson));

            result.andExpect(status().isUnsupportedMediaType());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/transactions/{id} - Obtener transacción por ID")
    class GetTransactionByIdTests {

        @Test
        @DisplayName("Debe obtener una transacción por su ID exitosamente")
        void shouldGetTransactionById() throws Exception {
            when(transactionService.getTransactionById(transactionId))
                .thenReturn(transactionResponse);

            ResultActions result = mockMvc.perform(get("/api/v1/transactions/{id}", transactionId));

            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transactionId.toString()))
                .andExpect(jsonPath("$.accountId").value(accountId.toString()));
        }

        @Test
        @DisplayName("Debe retornar 404 cuando la transacción no existe")
        void shouldReturn404WhenTransactionNotFound() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(transactionService.getTransactionById(nonExistentId))
                .thenThrow(new TransactionException("Transacción no encontrada"));

            ResultActions result = mockMvc.perform(get("/api/v1/transactions/{id}", nonExistentId));

            result.andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el ID tiene formato inválido")
        void shouldReturn400WhenIdIsInvalid() throws Exception {
            ResultActions result = mockMvc.perform(get("/api/v1/transactions/{id}", "invalid-uuid"));

            result.andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/transactions - Consultar transacciones")
    class GetTransactionsTests {

        @Test
        @DisplayName("Debe obtener transacciones por accountId")
        void shouldGetTransactionsByAccountId() throws Exception {
            List<TransactionResponse> responses = List.of(transactionResponse);
            when(transactionService.getTransactionsByAccountId(accountId))
                .thenReturn(responses);

            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("accountId", accountId.toString()));

            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(transactionId.toString()))
                .andExpect(jsonPath("$.[0].accountId").value(accountId.toString()));
        }

        @Test
        @DisplayName("Debe obtener transacciones por status")
        void shouldGetTransactionsByStatus() throws Exception {
            List<TransactionResponse> responses = List.of(transactionResponse);
            when(transactionService.getTransactionsByStatus(TransactionStatus.PENDING))
                .thenReturn(responses);

            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("status", TransactionStatus.PENDING.name()));

            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].status").value(TransactionStatus.PENDING.name()));
        }

        @Test
        @DisplayName("Debe obtener transacciones por merchantId")
        void shouldGetTransactionsByMerchantId() throws Exception {
            List<TransactionResponse> responses = List.of(transactionResponse);
            when(transactionService.getTransactionsByMerchantId("MERCHANT_001"))
                .thenReturn(responses);

            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("merchantId", "MERCHANT_001"));

            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].merchantId").value("MERCHANT_001"));
        }

        @Test
        @DisplayName("Debe retornar lista vacía cuando no hay transacciones")
        void shouldReturnEmptyListWhenNoTransactions() throws Exception {
            when(transactionService.getTransactionsByAccountId(accountId))
                .thenReturn(List.of());

            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("accountId", accountId.toString()));

            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.").isArray())
                .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el parámetro accountId es inválido")
        void shouldReturn400WhenAccountIdIsInvalid() throws Exception {
            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("accountId", "not-a-uuid"));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el parámetro status es inválido")
        void shouldReturn400WhenStatusIsInvalid() throws Exception {
            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("status", "INVALID_STATUS"));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe soportar múltiples parámetros de filtro simultáneamente")
        void shouldSupportMultipleFilterParameters() throws Exception {
            List<TransactionResponse> responses = List.of(transactionResponse);
            when(transactionService.getTransactionsByAccountId(accountId))
                .thenReturn(responses);

            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("accountId", accountId.toString())
                .param("status", TransactionStatus.PENDING.name()));

            result.andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("Manejo de errores global")
    class GlobalErrorHandlingTests {

        @Test
        @DisplayName("Debe manejar excepciones internas del servidor")
        void shouldHandleInternalServerError() throws Exception {
            when(transactionService.getTransactionById(transactionId))
                .thenThrow(new RuntimeException("Error interno"));

            ResultActions result = mockMvc.perform(get("/api/v1/transactions/{id}", transactionId));

            result.andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.timestamp").exists());
        }

        @Test
        @DisplayName("Debe manejarTransactionException correctamente")
        void shouldHandleTransactionException() throws Exception {
            when(transactionService.createTransaction(any(TransactionRequest.class)))
                .thenThrow(new TransactionException("Error de validación"));

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionRequestJson));

            result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Error de validación"));
        }
    }

    @Nested
    @DisplayName("Documentación OpenAPI del controlador")
    class OpenAPIDocumentationTests {

        @Test
        @DisplayName("Debe incluir las operaciones en la documentación OpenAPI")
        void shouldIncludeOperationsInOpenAPIDocumentation() throws Exception {
            ResultActions getDocs = mockMvc.perform(get("/api/v1/transactions")
                .param("accountId", accountId.toString()));

            getDocs.andExpect(status().isOk());

            ResultActions postDocs = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionRequestJson));

            postDocs.andExpect(status().isCreated());
        }
    }
}
```
