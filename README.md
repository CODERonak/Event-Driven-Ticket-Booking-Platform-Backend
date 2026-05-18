# Event-Driven Ticket Booking Platform

A ticket booking platform using an Event-Driven, Modular Monolith architecture. It handles event management, seat reservations, payments, and notifications.

## Core Principles

*   **Modular Monolith:** A single deployable unit with strict internal boundaries between business domains (modules).
*   **Event-Driven:** Modules communicate asynchronously through domain events, ensuring loose coupling and scalability.
*   **Domain Isolation:** Each module owns its data and logic. No direct dependencies on other modules' internal components.

## High-Level Modules

*   `auth`: Authentication & Authorization
*   `users`: User Profiles
*   `events`: Event & Seat Management
*   `booking`: Core Seat Reservation Logic
*   `payment`: Transaction Processing
*   `notification`: Email/SMS Alerts
*   `ticket`: PDF Ticket Generation & Storage
*   `cache`: Redis-based Caching

## Technology Stack

*   **Backend:** Spring Boot
*   **Database:** PostgreSQL (Cloud SQL)
*   **Cache:** Redis
*   **Storage:** Google Cloud Storage (GCS)
*   **Hosting:** GKE / Cloud Run

## Deployment

The application is containerized using Docker and deployed to a cloud environment, connecting to managed database, cache, and storage services.

## Evolution Roadmap

1.  **Modular Monolith (Current):** Start with a single, well-structured application.
2.  **Introduce Caching/Locking:** Integrate Redis for performance and concurrency.
3.  **Externalize Events:** Migrate from internal Spring Events to a message broker like Kafka.