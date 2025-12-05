# EnterPrice Todo App — Backend

A Spring Boot backend built as part of a school assignment at STI (Stockholm Technical Institute).  
This works towards a Frontend built in React. 

---

## Features

### Security & Authentication
- JWT-based authentication (HttpOnly cookie)
- Login, Register & Logout endpoints
- BCrypt password hashing
- Role & permission-based access (USER / ADMIN)
- Stateless sessions (JWT only)
- Input validation on DTOs
- Centralized exception handling

### Task Management
- Create, Read, Update, Delete tasks
- Users can only manage their own tasks

### Architecture & Deployment
- Built with Spring Boot & Java 
- Database connected via JPA/Hibernate
- Docker support (runs via `docker compose`)
- RabbitMQ integration prepared (event messaging)
- Uses Flyway for Database Migration

---

## Tech Stack

| Layer | Technology |
|------|------------|
| Backend | Spring Boot|
| Auth | JWT | CORS |
| Database | PostgreSQL |
| Messaging | RabbitMQ |
| Deployment | Docker & Docker Compose |

---

## API Overview

| Endpoint | Method | Description |
|---------|-------|-------------|
| `/api/auth/register` | POST | Register a new user |
| `/api/auth/login` | POST | Authenticate and set JWT cookie |
| `/api/auth/logout` | POST | Clear auth cookie |
| `/api/ducktasks` | GET | Fetch user tasks (requires auth) |
| `/api/ducktasks` | POST | Create task (requires auth) |
| `/api/ducktasks/update/{taskId}` | PUT | Update specific task |
| `/api/ducktasks/delete/{taskId}` | DELETE | Delete specific task |

> All `/api/ducktasks/**` endpoints require a valid JWT cookie.

---

### Run with Docker 

```bash
docker compose up --build
