# Spring API with RabbitMQ, Redis, and Custom Banner

A Spring Boot API demonstrating **customized banner setup**, **RabbitMQ integration** (publishers/listeners with multiple queues/exchanges), and **Redis caching**.

---

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [RabbitMQ Setup](#rabbitmq-configuration)
- [Redis Caching](#redis-caching)
- [API Endpoints](#api-endpoints)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Monitoring](#monitoring)
- [Troubleshooting](#troubleshooting)
- [Future Enhancements](#future-enhancements)
- [License](#license)

---

## Features
1. **Custom Banner**: Custom startup banner for Spring Boot.
2. **RabbitMQ Integration**:
   - Multiple exchanges (Direct, Topic, Fanout).
   - Multiple queues bound to exchanges with routing keys.
   - Publisher for sending messages.
   - Listener for consuming messages.
3. **Redis Caching**: Cache frequently accessed data using Redis.
4. **Spring Boot REST API**: CRUD operations with caching and async messaging.

---

## Prerequisites
- Java 17+
- Maven/Gradle
- Docker (for RabbitMQ & Redis)
- IDE (IntelliJ, Eclipse)
- Postman/curl (for API testing)

---

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/spring-rabbitmq-redis.git
   cd spring-rabbitmq-redis
