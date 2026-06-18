# AMSListApi

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.6.7-6DB33F?logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT-black?logo=jsonwebtokens)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/Docs-Swagger_UI-85EA2D?logo=swagger&logoColor=black)
![License](https://img.shields.io/badge/license-MIT-green)

A RESTful API for managing personal watchlists and reading lists. Users track their **anime**, **manga**, and **TV series** with status labels (watching, reading, completed, dropped, plan-to-watch/read) and bookmark entries linking them to catalog titles.

---

## Built With

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 2.6.7 |
| Auth | Spring Security + JWT (jjwt) |
| ORM | Spring Data JPA (Hibernate) |
| Database | PostgreSQL (prod) / H2 (test) |
| Validation | Spring Validation (Bean Validation 2) |
| API Docs | springdoc-openapi (Swagger UI) |
| Build | Maven |
| Container | Docker |

---

## Architecture

```
src/main/java/com/gregory/AMSList/
├── config/
│   ├── SecurityConfig.java   # Spring Security filter chain
│   ├── DevConfig.java        # Dev profile data seeding
│   └── TestConfig.java       # Test profile data seeding
├── domain/                   # JPA entities
│   ├── Storys.java           # Abstract base (Anime/Manga/Serie inherit)
│   ├── Anime.java
│   ├── Manga.java
│   ├── Serie.java
│   ├── User.java
│   ├── BookMark.java
│   ├── dtos/                 # Data Transfer Objects
│   └── enums/
│       ├── Profile.java      # USER, ADMIN
│       └── Status.java       # WATCHING, READING, COMPLETED, DROPPED, PLAN_TO_*
├── repositories/             # Spring Data JPA interfaces
├── resources/                # REST controllers
│   └── exceptions/           # Global exception handler
├── security/                 # JWT filter, util, UserDetails impl
└── services/                 # Business logic
```

---

## Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL 13+ (for `dev` profile)
- Docker (optional)

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/GregoryLacerda/AMSListApi.git
cd AMSListApi
```

### 2. Configure the database

Create a PostgreSQL database:

```sql
CREATE DATABASE amslistapi_db;
```

Edit `src/main/resources/application-dev.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/amslistapi_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=create
```

### 3. Set the active profile

`src/main/resources/application.properties`:

```properties
spring.profiles.active=dev
```

> Use `test` to run with an in-memory H2 database — no setup required.

### 4. Build and run

```bash
mvn spring-boot:run
# Server starts on http://localhost:8080
```

### 5. Access Swagger UI

```
http://localhost:8080/swagger-ui.html
```

---

### Docker

```bash
# Build the JAR first
mvn clean package -DskipTests

# Build and run the image
docker build -t amslistapi .
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/amslistapi_db \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  amslistapi
```

---

## Environment Variables / Configuration

| Property | Default | Description |
|---|---|---|
| `spring.profiles.active` | `test` | Active profile (`dev` or `test`) |
| `spring.datasource.url` | H2 (test) | JDBC connection URL |
| `spring.datasource.username` | — | Database username |
| `spring.datasource.password` | — | Database password |
| `jwt.secret` | `AnimesMangasAndSeriesList` | JWT signing secret |
| `jwt.expiration` | `604800000` | Token TTL in milliseconds (7 days) |

---

## API Reference

All endpoints except `POST /login` require a `Bearer` JWT token.

**Roles:** `ADMIN` can manage users. Regular users manage their own content.

### Authentication

| Method | Route | Auth | Description |
|---|---|---|---|
| `POST` | `/login` | Public | Authenticate and receive a JWT token |

#### POST /login

```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username": "user@example.com", "password": "secret"}'
```

The JWT token is returned in the `Authorization` response header:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

### Users

| Method | Route | Auth | Role | Description |
|---|---|---|---|---|
| `GET` | `/users` | Required | Any | List all users |
| `GET` | `/users/:id` | Required | Any | Get user by ID |
| `GET` | `/users/:id/animes` | Required | Any | List a user's bookmarked anime |
| `GET` | `/users/:id/mangas` | Required | Any | List a user's bookmarked manga |
| `GET` | `/users/:id/series` | Required | Any | List a user's bookmarked series |
| `POST` | `/users` | Required | ADMIN | Create a user |
| `PUT` | `/users/:id` | Required | ADMIN | Update a user |
| `DELETE` | `/users/:id` | Required | ADMIN | Delete a user |

#### GET /users/:id/animes

```bash
curl http://localhost:8080/users/1/animes \
  -H "Authorization: Bearer <token>"
```

**Response `200 OK`**
```json
[
  {
    "id": 1,
    "status": 0,
    "storys": {
      "id": 1,
      "name": "Attack on Titan",
      "poster": "https://...",
      "totalSeason": 4.0,
      "totalEpisode": 87.0
    }
  }
]
```

---

### Anime

| Method | Route | Auth | Description |
|---|---|---|---|
| `GET` | `/animes` | Required | List all anime |
| `GET` | `/animes/:id` | Required | Get anime by ID |
| `POST` | `/animes` | Required | Create an anime entry |
| `PUT` | `/animes/:id` | Required | Update an anime entry |
| `DELETE` | `/animes/:id` | Required | Delete an anime entry |

#### POST /animes

```bash
curl -X POST http://localhost:8080/animes \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Attack on Titan",
    "poster": "https://cdn.myanimelist.net/images/anime/...",
    "site": "https://myanimelist.net/anime/16498",
    "description": "Humanity fights for survival inside walls.",
    "totalSeason": 4.0,
    "totalEpisode": 87.0
  }'
```

**Response `200 OK`**
```json
{
  "id": 1,
  "name": "Attack on Titan",
  "poster": "https://...",
  "site": "https://myanimelist.net/anime/16498",
  "description": "Humanity fights for survival inside walls.",
  "totalSeason": 4.0,
  "totalEpisode": 87.0
}
```

---

### Manga

| Method | Route | Auth | Description |
|---|---|---|---|
| `GET` | `/mangas` | Required | List all manga |
| `GET` | `/mangas/:id` | Required | Get manga by ID |
| `POST` | `/mangas` | Required | Create a manga entry |
| `PUT` | `/mangas/:id` | Required | Update a manga entry |
| `DELETE` | `/mangas/:id` | Required | Delete a manga entry |

Request/response fields are the same as Anime.

---

### Series

| Method | Route | Auth | Description |
|---|---|---|---|
| `GET` | `/series` | Required | List all series |
| `GET` | `/series/:id` | Required | Get series by ID |
| `POST` | `/series` | Required | Create a series entry |
| `PUT` | `/series/:id` | Required | Update a series entry |
| `DELETE` | `/series/:id` | Required | Delete a series entry |

Request/response fields are the same as Anime.

---

### Bookmarks

A *BookMark* links a user to a title (Anime, Manga, or Serie) with a status.

| Method | Route | Auth | Description |
|---|---|---|---|
| `GET` | `/bookmark` | Required | List all bookmarks |
| `GET` | `/bookmark/:id` | Required | Get bookmark by ID |
| `POST` | `/bookmark` | Required | Create a bookmark |
| `PUT` | `/bookmark/:id` | Required | Update a bookmark |
| `DELETE` | `/bookmark/:id` | Required | Delete a bookmark |

#### POST /bookmark

```bash
curl -X POST http://localhost:8080/bookmark \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "status": 0,
    "storys": { "id": 1 },
    "user": { "id": 1 }
  }'
```

**Response `201 Created`** — `Location` header points to the new resource.

---

### Status Codes

| Code | Value | Description |
|---|---|---|
| `0` | `WATCHING` | Currently watching |
| `1` | `READING` | Currently reading |
| `2` | `COMPLETED` | Finished |
| `3` | `PLAN_TO_READ` | Plan to read |
| `4` | `PLAN_TO_WATCH` | Plan to watch |
| `5` | `DROPPED` | Dropped |

---

## License

This project is licensed under the [MIT License](LICENSE).
