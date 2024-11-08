Seat Reservation Application
============================

* * *

This repository contains a Dockerized seat reservation application with a **React frontend**, **Spring Boot backend**, and **MySQL database**. You can run the entire application with a single command using Docker Compose, with images pulled directly from Docker Hub.

Prerequisites
-------------

*   **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
*   **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)

Project Setup
-------------

To set up the project locally, follow these steps:

1.  Clone the repository:
    
        git clone https://github.com/shivamverma-01/Seat-Booking-System.git
        cd Seat-Booking-System
    

Getting Started
---------------

To run the application, follow these steps:

### 1\. Start the application with Docker Compose

Open a terminal in the same directory as `docker-compose.yml` and run:

    docker-compose up --build

This command will:

*   Set up a MySQL container for the database.
*   Launch the Spring Boot backend service.
*   The application will be accessible at [http://localhost:8080](http://localhost:8080) for backend API requests.

### 2\. Access the Frontend (React)

Once the backend is running, the frontend will be available at:

*   **Frontend (React)**: [http://localhost:3000](http://localhost:3000)

### 3\. Verify API Endpoints

You can use Postman or curl to make requests to the available API endpoints. For example:

    curl http://localhost:8080/api/endpoints

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
