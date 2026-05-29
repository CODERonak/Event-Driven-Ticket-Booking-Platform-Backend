# Event-Driven Ticket Booking Platform

A ticket booking platform using an Event-Driven, Modular Monolith architecture. It handles event management, seat bookings.

## Live Demo & Testing

The application is deployed to a **Google Kubernetes Engine (GKE)** cluster.

You can access the Swagger UI for live testing and API documentation at the following IP address, which is running in the Kubernetes cluster:

**Swagger UI:** [http://34.9.189.202/swagger-ui/index.html#/](http://34.9.189.202/swagger-ui/index.html#/)

## Core Principles

*   **Modular Monolith:** A single deployable unit with strict internal boundaries between business domains (modules).
*   **Event-Driven:** Modules communicate asynchronously through domain events, ensuring loose coupling and scalability.
*   **Domain Isolation:** Each module owns its data and logic. No direct dependencies on other modules' internal components.

## High-Level Modules

*   `auth`: Authentication & Authorization
*   `common`: core
*   `users`: User Profiles
*   `events`: Event & Seat Management
*   `booking`: Core Seat Reservation Logic

## Technology Stack

*   **Backend:** Spring Boot
*   **Database:** PostgreSQL (Cloud SQL)
*   **Storage:** Google Cloud Storage (GCS)
*   **Hosting:** GKE / Cloud Run

## Deployment

The application is containerized using Docker and deployed to a cloud environment, connecting to managed database, cache, and storage services.

## API Authentication

This API uses JWT (JSON Web Tokens) for authentication. After a successful login, you will receive a JWT token. This token must be included in the `Authorization` header of your requests as `Bearer {token}` for all protected endpoints.

### Endpoints Requiring JWT Authentication:

**User Profile Controller (`/user-profile`)**

*   `POST /user-profile/create`: Creates a new user profile.
*   `PUT /user-profile/update`: Updates an existing user profile.
*   `GET /user-profile/my-profile`: Retrieves the profile of the currently logged-in user.

**Events Controller (`/events`)**

*   `POST /events/create`: Creates a new event. (Requires **admin** role).

**Booking Controller (`/bookings`)**

*   `POST /bookings`: Creates a new booking.
*   `GET /bookings/{bookingId}`: Retrieves a specific booking by its ID.
*   `GET /bookings`: Retrieves all bookings for the current user.
*   `POST /bookings/{bookingId}/confirm`: Confirms a booking.
*   `POST /bookings/{bookingId}/cancel`: Cancels a booking.

### Publicly Accessible Endpoints (No JWT Required):

The following endpoints are public and do not require authentication:

**Authentication Controller (`/auth`)**

*   `POST /auth/register`
*   `POST /auth/login`

**Events Controller (`/events`)**

*   `GET /events`
*   `GET /events/{eventId}`
*   `GET /events/{eventId}/available-seats`
