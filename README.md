# Car Application

Aplicación Spring Boot para gestión de usuarios y autenticación con JWT.

## Endpoints de Autenticación

### Registro de Usuario

**POST** `/api/v1/users/register`

**Body:**

```json
{
  "username": "usuario123",
  "password": "password123"
}
```

**Respuesta exitosa:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "message": "Usuario registrado exitosamente",
  "username": "usuario123"
}
```

### Login de Usuario

**POST** `/api/v1/users/login`

**Body:**

```json
{
  "username": "usuario123",
  "password": "password123"
}
```

**Respuesta exitosa:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "message": "Login exitoso",
  "username": "usuario123"
}
```

## Validaciones

- **Username**: Entre 4 y 12 caracteres, debe ser único
- **Password**: Entre 6 y 12 caracteres

## Tecnologías Utilizadas

- Spring Boot 3.5.5
- Spring Security
- Spring Data JPA
- JWT (JSON Web Tokens)
- SQL Server
- Maven

## Configuración

Asegúrate de tener configurada la base de datos en `application.properties` antes de ejecutar la aplicación.
