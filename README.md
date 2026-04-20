# AI Feedback Service

## Purpose

AI Feedback Service provides AI-driven feedback on code submissions. It is part of an interview preparation platform that evaluates submitted code and returns structured feedback covering correctness, optimization, and style.

## API Endpoints

### Generate Feedback

**POST** `/api/feedback/generate`

Submits code for AI-driven analysis and persists the resulting feedback.

**Query Parameters**

| Parameter      | Type   | Description                          |
|----------------|--------|--------------------------------------|
| `submissionId` | String | Identifier of the code submission    |

**Request Body**

Raw source code (plain text).

**Response**

```json
{
  "id": 1,
  "submissionId": "sub-001",
  "correctness": "Pending",
  "optimization": "Pending",
  "style": "Pending",
  "comments": "AI feedback not yet implemented"
}
```

---

### Get Feedback by ID

**GET** `/api/feedback/{id}`

Retrieves previously generated feedback by its database ID.

**Path Parameters**

| Parameter | Type | Description             |
|-----------|------|-------------------------|
| `id`      | Long | ID of the feedback entry |

**Response**

```json
{
  "id": 1,
  "submissionId": "sub-001",
  "correctness": "Pending",
  "optimization": "Pending",
  "style": "Pending",
  "comments": "AI feedback not yet implemented"
}
```

---

## Setup Instructions

### Prerequisites

- Java 17
- Maven 3.8+
- PostgreSQL (running locally or via Docker)

### 1. Configure the Database

Create a PostgreSQL database:

```sql
CREATE DATABASE aifeedbackdb;
```

Update `src/main/resources/application.properties` with your credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/aifeedbackdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```

### 2. Build and Run

```bash
./mvnw spring-boot:run
```

The service starts on port **8083** by default.

### 3. Example Request

```bash
curl -X POST "http://localhost:8083/api/feedback/generate?submissionId=sub-001" \
     -H "Content-Type: text/plain" \
     -d "public int add(int a, int b) { return a + b; }"
```

---

## Future Work

- **LLM Integration**: Connect to an LLM provider (OpenAI GPT or HuggingFace models) to replace the placeholder `"Pending"` feedback with real AI-generated analysis covering:
  - Code correctness
  - Optimization suggestions
  - Style and best-practice recommendations
- **Authentication**: Secure endpoints with JWT-based auth.
- **Async Processing**: Queue feedback generation jobs for long-running LLM calls.
