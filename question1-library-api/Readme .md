# Library Book Management API

A simple RESTful API built with Spring Boot to manage a collection of library books. This project demonstrates basic CRUD (Create, Read, Update, Delete) operations and the use of proper HTTP status codes.

## Features

* Retrieve a full list of books
* Find a specific book by its unique ID
* Search for books by title using query parameters
* Add new books to the collection
* Remove books from the system

## Getting Started

### Prerequisites

* Java 17 or higher
* Maven 3.6+
* An API client like Postman or Insomnia

### How to Run

1. Clone the repository or download the source code
2. Navigate to the project root directory:
   ```bash
   cd question1-library-api
   ```
3. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. The server will start on `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description | Status Code (Success) |
|--------|----------|-------------|----------------------|
| GET | `/api/books` | Get all books | 200 OK |
| GET | `/api/books/{id}` | Get book by ID | 200 OK / 404 Not Found |
| GET | `/api/books/search?title={t}` | Search by title | 200 OK |
| POST | `/api/books` | Add a new book | 201 Created |
| DELETE | `/api/books/{id}` | Delete a book | 204 No Content |

## Sample Request & Response

### 1. Add a New Book

**POST** `/api/books`

Request Body:
```json
{
    "id": 4,
    "title": "Spring in Action",
    "author": "Craig Walls",
    "isbn": "978-1617294945",
    "publicationYear": 2018
}
```

Response:
```json
{
    "id": 4,
    "title": "Spring in Action",
    "author": "Craig Walls",
    "isbn": "978-1617294945",
    "publicationYear": 2018
}
```

### 2. Search for a Book

**GET** `/api/books/search?title=Clean`

Response:
```json
[
    {
        "id": 1,
        "title": "Clean Code",
        "author": "Robert Martin",
        "isbn": "978-0132350884",
        "publicationYear": 2008
    }
]
```

## Project Structure

* **Model**: Located in `com.library.question1libraryapi.model.library`
* **Controller**: Located in `com.library.question1libraryapi.controller.library`
* **Data Persistence**: Data is stored in-memory in an `ArrayList`. Restarting the application will reset the data to the 3 initial sample books.

## License

This project is open source and available under the [MIT License](LICENSE).