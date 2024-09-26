Seat Booking System
===================

The Seat Booking System is a Spring Boot application designed to allow users to reserve seats on a bus. The system allows users to check seat availability, reserve seats, lock seats for a period, and fetch details on all seats (whether reserved, locked, or available). It also handles user bookings and stores customer information.

Features
--------

*   **Bus Management:** View all buses and their seat details.
*   **Seat Management:**
    *   Fetch available seats for a specific bus.
    *   Reserve and lock seats.
    *   Retrieve seat status (reserved, locked, or available).
*   **Customer Booking:** Book seats with customer details.
*   **Seat Locking:** Temporarily lock seats when a booking attempt fails to avoid concurrent booking.
*   **Error Handling:** Proper validation for customer booking data and seat status (reserved or locked).

Endpoints
---------

### Bus Endpoints

**Get All Buses:**

    GET /api/buses
    Retrieves all buses with seat details.

### Seat Endpoints

**Get Available Seats:**

    GET /api/seats/available/{busNo}
    Retrieves all available seats for the bus identified by {busNo}.

**Get All Seats with Status:**

    GET /api/seats/status/{busNo}
    Retrieves all seats for the bus with {busNo}, showing their reservation and lock status.

**Lock Seat:**

    POST /api/seats/lock?busNo={busNo}&seatNo={seatNo}
    Locks the seat for 1 minute if an invalid booking attempt is made.

### Booking Endpoints

**Confirm Booking:**

    POST /api/bookings/confirm
    Accepts a list of booking requests (up to 5 seats) and reserves the seats if the booking is valid. Invalid requests will lock the seat for a short period to prevent concurrent booking attempts.

Sample request body for booking:

    [
        {
            "firstName": "John",
            "lastName": "Doe",
            "email": "john.doe@example.com",
            "contactNumber": "1234567890",
            "address": "123 Main St",
            "busNo": "BUS123",
            "seatNo": 1
        },
        {
            "firstName": "Jane",
            "lastName": "Smith",
            "email": "jane.smith@example.com",
            "contactNumber": "9876543210",
            "address": "456 Elm St",
            "busNo": "BUS123",
            "seatNo": 2
        }
    ]

Database Schema
---------------

### Bus Table (buses)

*   id (Primary Key)
*   busNo: Unique identifier for the bus.
*   totalSeats: Total number of seats on the bus.

### Customer Table (customers)

*   id (Primary Key)
*   firstName, middleName, lastName, email, contactNumber, address
*   busNo: Bus number for the reservation.
*   seatNo: Seat number that the customer has booked.

### Seat Table (seats)

*   id (Primary Key)
*   busNo: Bus number for the seat.
*   seatNo: Seat number on the bus.
*   isReserved: Boolean indicating whether the seat is reserved.
*   isLocked: Boolean indicating whether the seat is temporarily locked.

Project Structure
-----------------

    seat-booking-system/
    ├── backend/                  # Spring Boot backend
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/
    │   │   │   │       └── example/
    │   │   │   │           └── seatbooking/
    │   │   │   │               ├── SeatBookingApplication.java  # Main application class
    │   │   │   │               ├── controller/                  # REST controllers
    │   │   │   │               ├── model/                       # Entity classes
    │   │   │   │               ├── repository/                  # JPA repositories
    │   │   │   │               ├── service/                     # Service classes
    │   │   │   │               └── security/                    # Security configuration
    │   │   │   └── resources/
    │   │   │       └── application.properties  # Configuration file
    │   │   └── test/                             # Unit tests
    │   └── pom.xml                               # Maven dependencies
    └── frontend/                 # React frontend
        ├── public/               # Public assets
        ├── src/
        │   ├── components/       # React components
        │   ├── App.js            # Main application component
        │   ├── index.js          # Entry point of the React app
        │   └── styles.css        # CSS styles
        └── package.json          # Node.js dependencies
    

Project Setup
-------------

### Prerequisites

*   **Java:** Make sure Java 11 or above is installed.
*   **Maven:** Apache Maven is required to build the project.
*   **MySQL:** The system uses MySQL for data persistence.
*   **Node.js and npm:** Required for building and running the React frontend.

### Installation

1.  Clone the repository:

        git clone https://github.com/shivamverma-01/seat-booking-system.git

3.  Navigate into the project directory:

        cd seat-booking-system

5.  Configure the MySQL database in the application.properties file:

        spring.datasource.url=jdbc:mysql://localhost:3306/seat_booking_db
        spring.datasource.username=root
        spring.datasource.password=yourpassword
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true

7.  Create a MySQL database named seat\_booking\_db:

        CREATE DATABASE seat_booking_db;

9.  Run the Spring Boot backend:

        cd backend
        mvn spring-boot:run

11.  Navigate to the frontend directory and install dependencies:

    cd ../frontend
    npm install

13.  Start the React frontend:

    npm start

API Testing
-----------

You can test the APIs using Postman or cURL.

### Example Postman Request to Confirm Booking

**URL:** http://localhost:8080/api/bookings/confirm

**Method:** POST

**Body:** (JSON format)

    [
        {
            "firstName": "John",
            "lastName": "Doe",
            "email": "john.doe@example.com",
            "contactNumber": "1234567890",
            "address": "123 Main St",
            "busNo": "BUS123",
            "seatNo": 1
        }
    ]

Conclusion
----------

The Seat Booking System provides a streamlined interface for users to book seats efficiently while ensuring data integrity and preventing concurrent booking issues.