Purpose — describes the service as an AI-driven code feedback tool for an interview prep platform.
Endpoints — documents both POST /api/feedback/generate (with query param and body details) and GET /api/feedback/{id}, each with example JSON responses.
Setup instructions — covers prerequisites (Java 17, Maven, PostgreSQL), DB creation, application.properties configuration, build/run command (./mvnw spring-boot:run on port 8083), and an example curl request.
Future work — LLM integration (OpenAI/HuggingFace), JWT auth, and async processing for long-running inference calls.
