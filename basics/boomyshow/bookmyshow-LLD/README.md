# BookMyShow LLD

## Overview
This project is a low-level design (LLD) for a ticket booking system similar to BookMyShow. It allows users to book tickets for movies, view their bookings, and cancel them.

## Project Structure
```
bookmyshow-LLD
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── bookmyshow
│   │   │   │       ├── controllers
│   │   │   │       │   └── BookingController.java
│   │   │   │       ├── models
│   │   │   │       │   ├── Booking.java
│   │   │   │       │   ├── Movie.java
│   │   │   │       │   ├── Show.java
│   │   │   │       │   └── User.java
│   │   │   │       ├── repositories
│   │   │   │       │   └── BookingRepository.java
│   │   │   │       ├── services
│   │   │   │       │   └── BookingService.java
│   │   │   │       └── utils
│   │   │   │           └── DateUtils.java
│   │   │   └── resources
│   │   │       └── application.properties
│   │   └── resources
│   │       └── static
│   └── test
│       └── java
│           └── com
│               └── bookmyshow
│                   └── BookingControllerTest.java
├── pom.xml
└── README.md
```

## Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd bookmyshow-LLD
   ```
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Run the application:
   ```
   mvn spring-boot:run
   ```

## Usage Guidelines
- **Booking a Show**: Use the `createBooking()` method in `BookingController` to book a show.
- **Viewing Bookings**: Use the `getBooking()` method to retrieve booking details.
- **Cancelling a Booking**: Use the `cancelBooking()` method to cancel an existing booking.

## Dependencies
This project uses Maven for dependency management. Please refer to the `pom.xml` file for the list of dependencies.

## Testing
Unit tests for the `BookingController` are located in `src/test/java/com/bookmyshow/BookingControllerTest.java`. Run the tests using:
```
mvn test
```

## License
This project is licensed under the MIT License.