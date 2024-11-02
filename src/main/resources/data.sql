INSERT INTO PRODUCT (code, name, description, image, category, price, quantity, internal_reference, shell_id, inventory_status, rating, created_At, updated_At)
VALUES
    ('P001', 'Product 1', 'Description of product 1', 'image1.jpg', 'Category 1', 19.99, 50, 'REF001', 101, 'INSTOCK', 4, CURRENT_TIMESTAMP, NULL),
    ('P002', 'Product 2', 'Description of product 2', 'image2.jpg', 'Category 2', 29.99, 30, 'REF002', 102, 'LOWSTOCK', 5, CURRENT_TIMESTAMP, NULL),
    ('P003', 'Product 3', 'Description of product 3', 'image3.jpg', 'Category 1', 39.99, 0, 'REF003', 103, 'OUTOFSTOCK', 3, CURRENT_TIMESTAMP, NULL),
    ('P004', 'Product 4', 'Description of product 4', 'image4.jpg', 'Category 3', 49.99, 25, 'REF004', 104, 'LOWSTOCK', 4, CURRENT_TIMESTAMP, NULL),
    ('P005', 'Product 5', 'Description of product 5', 'image5.jpg', 'Category 2', 15.99, 60, 'REF005', 105, 'INSTOCK', 2, CURRENT_TIMESTAMP, NULL),
    ('P006', 'Product 6', 'Description of product 6', 'image6.jpg', 'Category 3', 55.99, 0, 'REF006', 106, 'OUTOFSTOCK', 5, CURRENT_TIMESTAMP, NULL),
    ('P007', 'Product 7', 'Description of product 7', 'image7.jpg', 'Category 1', 22.99, 40, 'REF007', 107, 'INSTOCK', 4, CURRENT_TIMESTAMP, NULL),
    ('P008', 'Product 8', 'Description of product 8', 'image8.jpg', 'Category 2', 33.99, 20, 'REF008', 108, 'LOWSTOCK', 3, CURRENT_TIMESTAMP, NULL),
    ('P009', 'Product 9', 'Description of product 9', 'image9.jpg', 'Category 3', 44.99, 70, 'REF009', 109, 'INSTOCK', 5, CURRENT_TIMESTAMP, NULL),
    ('P010', 'Product 10', 'Description of product 10', 'image10.jpg', 'Category 1', 24.99, 0, 'REF010', 110, 'OUTOFSTOCK', 2, CURRENT_TIMESTAMP, NULL);