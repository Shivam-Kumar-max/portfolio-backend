# Portfolio Backend (Spring Boot)

REST API for a portfolio site with JWT authentication, an admin role, and
full CRUD for skills, projects, and contact messages.

## Stack
- Java 17, Spring Boot 4.1
- Spring Web MVC, Spring Data JPA, Spring Security (JWT, stateless)
- MySQL
- Lombok

## 1. Prerequisites
- JDK 17+
- Maven (or use the included `./mvnw`)
- MySQL running locally, with a database created:
  ```sql
  CREATE DATABASE portfolio;
  ```

## 2. Configure
All config in `src/main/resources/application.properties` can be overridden
with environment variables — nothing sensitive is hardcoded:

| Env var             | Default                                   | Purpose                       |
|----------------------|--------------------------------------------|--------------------------------|
| `DB_URL`             | `jdbc:mysql://127.0.0.1:3306/portfolio`     | MySQL connection URL           |
| `DB_USERNAME`        | `root`                                      | MySQL username                 |
| `DB_PASSWORD`        | `root`                                      | MySQL password                 |
| `JWT_SECRET`         | (placeholder — **override this!**)          | HMAC signing key for JWTs      |
| `JWT_EXPIRATION_MS`  | `86400000` (24h)                            | Token lifetime                 |
| `ADMIN_USERNAME`     | `admin`                                     | Seeded admin username          |
| `ADMIN_EMAIL`        | `admin@portfolio.local`                     | Seeded admin email             |
| `ADMIN_PASSWORD`     | `Admin@123`                                 | Seeded admin password          |

On first startup, `DataInitializer` seeds one admin account using the
`ADMIN_*` values above (skipped if that username already exists). **Log in
and change the password immediately** if you deploy this anywhere public,
and always override `JWT_SECRET` outside of local development.

## 3. Run
```bash
./mvnw spring-boot:run
```
The API starts on `http://localhost:8080`.

## 4. API Overview

### Auth (public)
| Method | Endpoint             | Body                                   |
|--------|-----------------------|-----------------------------------------|
| POST   | `/api/auth/register`  | `{ username, email, password }`         |
| POST   | `/api/auth/login`     | `{ username, password }`                |

Both return `{ token, username, role }`. Send the token as
`Authorization: Bearer <token>` on subsequent requests.
Publicly registered users get role `USER`; only the seeded/promoted account
is `ADMIN`.

### Skills — `/api/skills`
| Method | Path        | Access       |
|--------|-------------|--------------|
| GET    | `/`         | Public       |
| GET    | `/{id}`     | Public       |
| POST   | `/`         | Admin only   |
| PUT    | `/{id}`     | Admin only   |
| DELETE | `/{id}`     | Admin only   |

### Projects — `/api/projects`
Same shape as Skills (GET public, mutations admin-only).

### Contact messages — `/api/contacts`
| Method | Path      | Access                          |
|--------|-----------|-----------------------------------|
| POST   | `/`       | Public (visitors submit the form) |
| GET    | `/`       | Admin only                        |
| GET    | `/{id}`   | Admin only                        |
| DELETE | `/{id}`   | Admin only                        |

## 5. Promoting a user to ADMIN
Registration always creates a `USER`. To promote someone, update their row
directly for now:
```sql
UPDATE users SET role = 'ADMIN' WHERE username = 'someuser';
```

## Notes
- `spring.jpa.hibernate.ddl-auto=update` will create/update tables
  automatically on startup — fine for development, swap for migrations
  (Flyway/Liquibase) before production use.
- CORS is currently wide open (`*`) to make local frontend development easy;
  restrict `corsConfigurationSource()` in `SecurityConfig` to your real
  frontend origin before deploying.
