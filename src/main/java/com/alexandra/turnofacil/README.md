# CareFlow 🏥

CareFlow is a staff management backend system built with **Spring Boot 3**. It handles employee shifts and schedules for healthcare environments with a focus on data integrity and clean architecture.

## 🚀 Key Features

* **Shift Management**: Full CRUD operations for work schedules.
* **Business Logic**: Prevents overlapping shifts and validates work hours.
* **Database Persistence**: Integrated with **PostgreSQL** for reliable data storage.
* **Security**: Separation of sensitive data using the Repository pattern.

## 🛠️ Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.x
* **Database:** PostgreSQL
* **Tools:** JPA/Hibernate, Maven, Lombok, WSL

---

## 🔧 Setup & Installation

### 1. Database Configuration
The project uses PostgreSQL. You need to create a file named `application.properties` in `src/main/resources/`.

You can use the provided template:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties

### 2. Environment Variables
Make sure to update your `application.properties` with your local database credentials:

* **URL:** `spring.datasource.url`
* **Username:** `spring.datasource.username`
* **Password:** `spring.datasource.password`

### 3. Build and Run
```bash
mvn clean install
mvn spring-boot:run
📂 API Reference
Get all shifts
GET /api/shifts

Create a shift
POST /api/shifts

Request Body Example:

JSON
{
  "startTime": "2026-02-20T08:00:00",
  "endTime": "2026-02-20T16:00:00",
  "employee": {
    "id": 1
  }
}
🏗️ Architecture
The project follows the standard Controller-Service-Repository pattern to ensure scalability and easy maintenance.