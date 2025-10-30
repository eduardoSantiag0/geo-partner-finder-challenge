# 🧭🍻 Geo Partner Finder

A backend service developed in **Java Spring Boot** to manage beverage delivery partners, using **PostgreSQL** + **PostGIS** for spatial queries, and exposing a REST API to create, fetch, and find the nearest partner.

Built using Ports and Adapters (Hexagonal) architecture for maintainability, testability, and separation of concerns.

## 🗺️ Challenge Description

The goal of the challenge is to implement an application that can:

- Create partners with GeoJSON data (``coverageArea`` as MultiPolygon and ``address`` as Point).
- Retrieve a partner by ``id``.
- Find the nearest partner given a pair of coordinates (``lat`` / ``lon``) that lies within the partner’s coverage area.


````json
{
  "id": 1,
  "tradingName": "Adega da Cerveja - Pinheiros",
  "ownerName": "Zé da Silva",
  "document": "1432132123891/0001",
  "coverageArea": { 
    "type": "MultiPolygon", 
    "coordinates": [
      [[[30, 20], [45, 40], [10, 40], [30, 20]]], 
      [[[15, 5], [40, 10], [10, 20], [5, 10], [15, 5]]]
    ]
  },
  "address": { 
    "type": "Point",
    "coordinates": [-46.57421, -21.785741]
  }
}
````

## 🌐 Technologies Used
- Java 17
- Spring Boot 3.5.6
- Spring Data JPA
- PostgreSQL 16 + PostGIS
- Hibernate Spatial & JTS (Java Topology Suite)
- Docker & Docker Compose
- Maven
- JUnit

## 🌍️ Workflow
1. A partner is created via POST /api/v1/partners.
2. Partner data is persisted in PostgreSQL + PostGIS.
3. Partners can be queried via:
   - **GET** ``/api/v1/partners/{id}`` → fetch by ID
   - **GET** ``/api/v1/partners/{lat}/{lon}`` → fetch nearest partner that covers the location

**Error handling:**
- ``PartnerNotFoundException`` → ``404 NOT FOUND``
- ``InvalidGeoJsonException`` → ``400 BAD REQUEST``
- ``NoPartnerCloseEnoughException`` → ``404 NOT FOUND``
- ``DuplicateIdException`` / ``DuplicateDocumentException`` → ``400 BAD REQUEST``

## 🌎 API Endpoints
Base URL: http://localhost:8080/api/v1/partners

| Method | Endpoint       | Description                               | Example                  |
| ------ | -------------- | ----------------------------------------- | ------------------------ |
| `POST` | `/`            | Create a new partner                      | `/`                      |
| `GET`  | `/{id}`        | Get partner by ID                         | `/1`                     |
| `GET`  | `/{lat}/{lon}` | Find nearest partner covering coordinates | `/ -21.785741/-46.57421` |


## 🌏 Architecture

This project follows the Ports and Adapters (Hexagonal) architecture:

- **Application Layer**: Contains ``PartnerUseCase`` interface and ``PartnerService`` implementation, exceptions and business rules.

- **Inbound Adapter**: REST controllers (``PartnerController``) handling HTTP requests.

- **Outbound Adapter**: Database repositories using Spring Data JPA with PostGIS support.


## 🛫 How to run: 

```bash 
  docker compose up --build
```

