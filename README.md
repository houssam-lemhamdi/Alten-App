# Alten-App
This is a spring boot application that handle products in both data base SQL or NoSql

# Overview
The Alten-App is designed to provide a robust back-end solution for product management. 
It supports various operations on products through RESTful API endpoints, allowing seamless interaction with both SQL and NoSQL databases. 
The application is built using Java and Spring Boot, ensuring scalability and maintainability.

# Product Trial Master
This project involves developing a back-end for managing products using Java/Spring Boot.

# Description
The back-end enables the management of products through multiple API endpoints for creating, retrieving, updating, and deleting products.

# API Endpoints

| Resource           | POST                  | GET                            | PATCH                                    | PUT | DELETE           |
| ------------------ | --------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
| **/products**      | Create a new product  | Retrieve all products          | X                                        | X   |     X            |
| **/products/:id**  | X                     | Retrieve details for product 1 | Update details of product 1 if it exists | X   | Remove product 1 |


# Product Structure
A product contains the following attributes:

``` typescript
class Product {
  id: number;
  code: string;
  name: string;
  description: string;
  image: string;
  category: string;
  price: number;
  quantity: number;
  internalReference: string;
  shellId: number;
  inventoryStatus: "INSTOCK" | "LOWSTOCK" | "OUTOFSTOCK";
  rating: number;
  createdAt: number;
  updatedAt: number;
}
```
# Features 
- Java 17
- Spring boot 3.3.5
- Swagger UI for API documentation
- H2 in-memory database
- H2 console for database management
- SQL scripts for data initialization
- MongoDb for handling noSql data
- JUnit

# Prérequis
- Java 17
- MongoDb data base
- Maven

## Setup and Run
### Running Locally

1. **Clone the repository**
    ```bash
    git clone https://github.com/houssam-lemhamdi/Alten-App.git
    cd Alten-App
    ```
2. **Build the project**
    ```bash
    ./mvnw clean install
    ```
3. **Run the application**
    ```bash
    ./mvnw spring-boot:run
    ```
4. **Access the application**
   - Swagger UI: [http://localhost:8080/swagger-ui/index.html]
   - H2 Console: [http://localhost:8080/h2-console]
      - Username: `admin`
      - Password: `12345`

## Database

This application can switch between H2 in-memory database and MongoDB based on the property app.datasource.type defined in application.properties, which accepts two values: sql or nosql. 
The database schema is created automatically, and the H2 database is initially populated by a script:

- data.sql: Populates the database with initial data.

# Postman Tests for Product Management API
   # create product
      - Method: POST
      - URL: http://localhost:8080/products
      - Body
      {
        "code": "P001",
        "name": "Sample Product",
        "description": "This is a sample product.",
        "image": "http://example.com/image.png",
        "category": "Electronics",
        "price": 99.99,
        "quantity": 100,
        "internalReference": "IR-001",
        "shellId": 1,
        "inventoryStatus": "INSTOCK",
        "rating": 5
        }
   # get product by id
      - Method: GET
      - URL: http://localhost:8080/products/id
         for sql replace id with a number 1,2...
         for NoSql after you create a product check it's id on db it will show somthing like 6727b32d0580854c3d1d906a
   # get all products
      - Method: GET
      - URL: http://localhost:8080/products
# Audit
    createdAt and updatedAt field are created automatically in both scenario sql and nosql
# Running Tests in the IDE
You can also run the tests directly from your Eclipse IDE:

Locate the test classes in the src/test/java directory: ProductControllerTest / ProductServiceTest.
Right-click on the test class or test method and select "Run."

## Contact
For any questions or inquiries, please contact me at lemhamdihoussam15@gmail.com.
