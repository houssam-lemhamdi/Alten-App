# Alten-App
This is a Spring Boot application that handles product management and supports both SQL (H2 database), NoSQL (MongoDB), and a JSON file (products.json) for data storage.

# Overview
The Alten-App is designed to provide a robust back-end solution for managing products. It supports various operations on products through RESTful API endpoints and allows seamless interaction with multiple data sources. The application is built using Java 17 and Spring Boot 3.x, ensuring scalability and maintainability.
# Product Trial Master
This project involves developing a back-end for managing products using Java/Spring Boot. The back-end enables CRUD operations on products via API endpoints, allowing the system to interface with either SQL, NoSQL, or a JSON file.

# Features
Java 17
Spring Boot 3.3.5
Swagger UI for API documentation
H2 in-memory database for SQL-based data storage
MongoDB for NoSQL-based data storage
JSON file support for product data storage (products.json)
JUnit for unit testing
Swagger UI for API documentation
H2 Console for SQL database management

# API Endpoints
| Resource           | POST                  | GET                            | PATCH                                    | PUT | DELETE           |
| ------------------ | --------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
| **/products**      | Create a new product  | Retrieve all products          | X                                        | X   |     X            |
| **/products/:id**  | X                     | Retrieve details for product 1 | Update details of product 1 if it exists | X   | Remove product 1 |
# Product Structure
A product contains the following attributes:

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
# Setup and Run
# Running Locally
Clone the repository
    git clone https://github.com/houssam-lemhamdi/Alten-App.git
    cd Alten-App
# Build the project
    ./mvnw clean install
# Run the application
    ./mvnw spring-boot:run
# Access the application
    Swagger UI: [http://localhost:8080/swagger-ui/index.html]
    H2 Console: [http://localhost:8080/h2-console]
    Username: admin
    Password: 12345
# Database Configuration
    The application supports three types of data sources: 
    SQL (H2), 
    NoSQL (MongoDB), 
    and JSON files. 
    You can control which data source to use via the app.datasource.type property in the application.properties file.

# Available Data Sources
    sql: Uses H2 database (in-memory).
    nosql: Uses MongoDB.
    json: Uses a JSON file (products.json) for product data.
# Configuring Data Source
    In the application.properties file, set the app.datasource.type property to specify the data source:
    app.datasource.type=nosql  # Options: sql, nosql, json
# MongoDB Configuration
    When using MongoDB as the data source (app.datasource.type=nosql), the application connects to a MongoDB database named product-management-db. 
    Ensure that MongoDB is running and accessible before setting the application to use MongoDB.
- Database: product-management-db 
- Collection: products
# Setting Up mongoimport Tool for Data Initialization
- download mongoimport tool from this url https://fastdl.mongodb.org/tools/db/mongodb-database-tools-windows-x86_64-100.10.0.zip
- add the bin of mongoimport tool installed to your system path variable
- run mongoimport --version in the terminal of your ide where project is opened,if it is not recognized then restart your machine.
- now if you run the app ,the mongo db will be initialized by data inside mongo.json file

# SQL (H2 Database): Automatically loads initial data from the data.sql script if app.datasource.type=sql.
# JSON File: If app.datasource.type=json, the application reads product data from products.json, which should be located in the src/main/resources folder.
Example products.json Structure:
[
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
]
# Running Tests
    You can run the tests directly from your IDE or use Maven to run them.
# Test Classes: 
    Located in src/test/java/ as ProductControllerTest and ProductServiceTest.
# Run Tests in the IDE: 
    Right-click on the test class or test method and select "Run".
# Postman Tests for Product Management API
# Create Product
Method: POST
URL: http://localhost:8080/products
Body:
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
# Get Product by ID
Method: GET
URL: http://localhost:8080/products/{id}
Replace {id} with the actual product ID from SQL (e.g., 1) or the MongoDB document ID (e.g., 6727b32d0580854c3d1d906a).
# Get All Products
Method: GET
URL: http://localhost:8080/products
# Delete Product
Method: DELETE
URL: http://localhost:8080/products/{id}
# Audit Fields
Both in SQL and NoSQL, the createdAt and updatedAt fields are handled automatically:
createdAt: The timestamp when the product was created.
updatedAt: The timestamp when the product was last updated.
# Contact
For any questions or inquiries, please contact me at:
Email: lemhamdihoussam15@gmail.com

# Notes
Make sure that MongoDB is installed and running, and that the product-management-db database is accessible before using the nosql option for your data source.
The H2 Console provides a graphical interface to manage the H2 database. You can access it at http://localhost:8080/h2-console and use the provided credentials.
Ensure that the products.json file is properly formatted when you switch to the json data source option.