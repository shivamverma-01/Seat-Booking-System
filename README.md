Seat Reservation Application
============================

This repository contains a Dockerized seat reservation application with a **React frontend**, **Spring Boot backend**, and **MySQL database**. You can run the entire application with a single command using Docker Compose, with images pulled directly from Docker Hub.

Prerequisites
-------------

*   **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
*   **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)

Getting Started
---------------

To run the application, follow these steps:

### 1\. Download the `docker-compose.yml` file

Download or copy the `docker-compose.yml` file from this repository, which configures the services and pulls images from Docker Hub.

### 2\. Run Docker Compose

Open a terminal in the same directory as `docker-compose.yml` and run:

    docker-compose up -d

This command will:

*   Pull the required images from Docker Hub:
    *   **MySQL**: `mysql:latest`
    *   **Backend**: `shivam058/seat-reservation-backend:latest`
    *   **Frontend**: `shivam058/seat-reservation-frontend:latest`
*   Start all services in the correct order, with the MySQL database initialized automatically.

### 3\. Access the Application

Once all services are running, you can access them as follows:

*   **Frontend (React)**: [http://localhost:3000](http://localhost:3000)
*   **Backend (Spring Boot)**: Accessible at [http://localhost:8080](http://localhost:8080) for API requests
*   **Database (MySQL)**: Available on port `3307`, with credentials specified in the `docker-compose.yml` file

**Note**: The MySQL database will be initialized with the schema from `db_schema.sql`, located in the `database` directory.

### 4\. Stopping the Application

To stop the containers, run:

    docker-compose down

This command will stop and remove the containers, but any data stored in the MySQL container will persist unless you explicitly remove the volumes.

Environment Variables
---------------------

The environment variables used in this setup are as follows:

*   **MySQL**
    *   `MYSQL_ROOT_PASSWORD`: Password for the MySQL root user (`root`)
    *   `MYSQL_DATABASE`: Name of the database (`reservation_db`)
*   **Backend**
    *   `SPRING_DATASOURCE_URL`: Database connection URL
    *   `SPRING_DATASOURCE_USERNAME`: Username for MySQL (`root`)
    *   `SPRING_DATASOURCE_PASSWORD`: Password for MySQL (`root`)

API Documentation
-----------------

To view and test the available API endpoints, please refer to the [GitHub repository](https://github.com/shivamverma-01/Seat-Booking-System/tree/main). This documentation provides details on each endpoint and the data required for making requests.

Troubleshooting
---------------

If the application does not start properly:

*   Check that Docker and Docker Compose are correctly installed.
*   Ensure that ports `3307`, `8080`, and `3000` are available on your system.
*   Check logs for any issues with `docker-compose logs`.

Contact
-------

For further questions, please feel free to contact **Shivam** at [verma.shivam2605@gmail.com](mailto:verma.shivam2605@gmail.com).
