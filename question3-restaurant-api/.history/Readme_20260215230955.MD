# Restaurant Menu API

A specialized REST API for restaurant menu management, allowing staff to update dish availability and categorize food items effectively.

## Features

* Menu Overview: View the complete list of 8+ starter dishes, main courses, desserts, and beverages
* Category Navigation: Filter items by "Appetizer", "Main Course", "Dessert", or "Beverage"
* Availability Tracking: Filter to see only items currently in stock/available
* Instant Toggle: A dedicated endpoint to switch an item's status (e.g., when a dish is sold out)
* Search Functionality: Search for specific menu items by name

## Getting Started

### Prerequisites

* Java 17 or 21
* Maven
* Postman

### How to Run

1. Navigate to the project folder:
   ```bash
   cd question3-restaurant-api
   ```
2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
3. The API will be available at `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/menu` | Get the full menu |
| GET | `/api/menu/{id}` | Get details of a specific dish |
| GET | `/api/menu/category/{category}` | Filter by category (Path Variable) |
| GET | `/api/menu/available?available=true` | Filter by availability (Query Param) |
| GET | `/api/menu/search?name={name}` | Search by item name |
| POST | `/api/menu` | Add a new item to the menu |
| PUT | `/api/menu/{id}/availability` | Toggle if the item is available |
| DELETE | `/api/menu/{id}` | Remove an item from the menu |

## Sample Request & Response

### 1. Toggle Availability

**PUT** `http://localhost:8080/api/menu/4/availability`

Response (Before: available = false, After: available = true):
```json
{
    "id": 4,
    "name": "Pasta Carbonara",
    "description": "Creamy sauce",
    "price": 13.5,
    "category": "Main Course",
    "available": true
}
```

### 2. Filter by Category

**GET** `http://localhost:8080/api/menu/category/Beverage`

Expected Behavior: Returns a list containing "Iced Tea" and "Cappuccino".

## Project Structure

* **Model Package**: `com.library.question3restaurantapi.model`
* **Controller Package**: `com.library.question3restaurantapi.controller`

