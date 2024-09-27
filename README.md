# Technical Exam for API Developer 

This API allows you to manage a product inventory with basic CRUD (Create, Read, Update, Delete) operations.

Created by: Elijah Malazarte

## Requirements
- MySQL
- Java 23

## Prerequisites
1. Clone this repository using `git clone https://github.com/esmalazarte/tech-exam-api.git`
2. Modify the `src/main/resources/application.properties` file to use your own database configuration.
3. Run the included `setup.sql` in the root directory by running `source /<path_to_root>/setup.sql` inside a MySQL session.

## Usage
1. Run the server by running `src/main/java/com/example/tech_exam_api_java/TechExamApiJavaApplication.java` through an IDE like IntelliJ IDEA
2. Access the server through `http://localhost:8080/`

## API Endpoints

### Products

#### Create Product
- Endpoint: `POST /v1/product/`
- Request Body:
```json
{
    "name": "Product Name",
    "description": "Product Description",
    "type": "food",
    "quantity": 10,
    "unitPrice": 200
}
```
- Headers:
```json
{
    "Content-Type": "application/json"
}
```
- Response:
```json
{
    "id": "1",
    "name": "Product Name",
    "description": "Product Description",
    "type": "food",
    "quantity": 10,
    "unitPrice": 200,
    "requirements": null
}
```

#### Get All Products
- Endpoint: `GET /v1/product/`
- Response:
```json
[
    {
        "id": "1",
        "name": "Product Name",
        "description": "Product Description",
        "type": "food",
        "quantity": 10,
        "unitPrice": 200,
        "requirements": null
    },
    ...
]
```

#### Get Product By ID
- Endpoint: `GET /v1/product/:id`
- Response:
```json
{
    "id": "1",
    "name": "Product Name",
    "description": "Product Description",
    "type": "food",
    "quantity": 10,
    "unitPrice": 200,
    "requirements": null
}
```

#### Update Product
- Endpoint: `PUT /v1/product/:id`
- Request Body:
```json
{
    "name": "Updated Product Name",
    "description": "Updated Product Description",
    "type": "music",
    "quantity": 15,
    "unitPrice": 500
}
```
- Headers:
```json
{
    "Content-Type": "application/json"
}
```
- Response:
```json
{
    "id": "1",
    "name": "Updated Product Name",
    "description": "Updated Product Description",
    "type": "music",
    "quantity": 15,
    "unitPrice": 500,
    "requirements": null
}
```

#### Delete Product
- Endpoint: `DELETE /v1/product/:id`
- Response:
```json
{
    "message": "Product deleted successfully"
}
```