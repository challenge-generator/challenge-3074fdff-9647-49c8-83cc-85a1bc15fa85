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