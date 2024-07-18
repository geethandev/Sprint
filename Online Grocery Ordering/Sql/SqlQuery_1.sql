--As a Admin, I want to generate a report with List of First 50% logged in IDs.
CREATE TABLE Login (
    CustomerID NUMBER NOT NULL,
    LastLogin TIMESTAMP NOT NULL,
    LastLogout TIMESTAMP NOT NULL,
    UpdatedPassword VARCHAR2(255) NOT NULL,
    OldPassword VARCHAR2(255) NOT NULL,
    IsNowLoggedIn CHAR(1) NOT NULL
);
select * from login;
INSERT INTO Login (CustomerID, LastLogin, LastLogout, UpdatedPassword, OldPassword, IsNowLoggedIn) VALUES
(1, TIMESTAMP '2024-06-01 08:00:00', TIMESTAMP '2024-06-01 10:00:00', 'newPass1', 'oldPass1', 'N'),
(2, TIMESTAMP '2024-06-02 09:00:00', TIMESTAMP '2024-06-02 11:00:00', 'newPass2', 'oldPass2', 'N'),
(3, TIMESTAMP '2024-06-03 10:00:00', TIMESTAMP '2024-06-03 12:00:00', 'newPass3', 'oldPass3', 'Y'),
(4, TIMESTAMP '2024-06-04 11:00:00', TIMESTAMP '2024-06-04 13:00:00', 'newPass4', 'oldPass4', 'N'),
(5, TIMESTAMP '2024-06-05 12:00:00', TIMESTAMP '2024-06-05 14:00:00', 'newPass5', 'oldPass5', 'Y'),
(6, TIMESTAMP '2024-06-06 13:00:00', TIMESTAMP '2024-06-06 15:00:00', 'newPass6', 'oldPass6', 'N'),
(7, TIMESTAMP '2024-06-07 14:00:00', TIMESTAMP '2024-06-07 16:00:00', 'newPass7', 'oldPass7', 'Y'),
(8, TIMESTAMP '2024-06-08 15:00:00', TIMESTAMP '2024-06-08 17:00:00', 'newPass8', 'oldPass8', 'N');
SELECT CustomerID
FROM (
    SELECT CustomerID,
           ROW_NUMBER() OVER (ORDER BY LastLogin DESC) AS rn,
           COUNT(*) OVER () AS total_count
    FROM Login
    WHERE IsNowLoggedIn = 'Y'
)
WHERE rn <= total_count / 2;
--As an Admin, I want to generate a report of customers' updated name on the basis of there residency.
CREATE TABLE Registration (
    CustomerID INT PRIMARY KEY NOT NULL,
    CustomerName VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Address VARCHAR(255) NOT NULL,
    ContactNumber INT NOT NULL
);
select * from Registration;
INSERT INTO Registration (CustomerID, CustomerName, Email, Password, Address, ContactNumber) VALUES
(1, 'John Doe', 'john.doe@example.com', 'password1', '123 Elm Street', 1234567890),
(2, 'Jane Smith', 'jane.smith@example.com', 'password2', '456 Oak Avenue', 2345678901),
(3, 'Alice Johnson', 'alice.johnson@example.com', 'password3', '789 Pine Road', 3456789012),
(4, 'Bob Brown', 'bob.brown@example.com', 'password4', '101 Maple Lane', 4567890123),
(5, 'Charlie Davis', 'charlie.davis@example.com', 'password5', '202 Birch Blvd US', 5678901234),
(6, 'Diana Evans', 'diana.evans@example.com', 'password6', '303 Cedar Circle India', 6789012345),
(7, 'Ethan Harris', 'ethan.harris@example.com', 'password7', '404 Spruce Way US', 7890123456),
(8, 'Fiona Clark', 'fiona.clark@example.com', 'password8', '505 Redwood Drive India', 8901234567);
UPDATE Registration
SET CustomerName = 
    CASE 
        WHEN Address LIKE '%US%' THEN CONCAT('US_', CustomerName)
        WHEN Address LIKE '%India%' THEN CONCAT('IN_', CustomerName)
        ELSE CustomerName
    END
WHERE Address LIKE '%US%' OR Address LIKE '%India%';
--As an Admin, I want to generate 2 reports to show the distinct products in grocert store and customer-product mapping respectively. 
CREATE TABLE Product (
    ProductID INT PRIMARY KEY NOT NULL,
    ProductName VARCHAR(255) NOT NULL,
    Price INT NOT NULL,
    Quantity INT NOT NULL,
    Reserved VARCHAR(1) NOT NULL,
    CustomerID INT REFERENCES Registration(CustomerID) NOT NULL
);
select * from Product;
INSERT INTO Product (ProductID, ProductName, Price, Quantity, Reserved, CustomerID) VALUES
(1, 'Laptop', 1000, 10, 'Y', 1),
(2, 'Smartphone', 500, 20, 'N', 2),
(3, 'Tablet', 300, 15, 'Y', 3),
(4, 'Monitor', 150, 30, 'N', 4),
(5, 'Keyboard', 50, 50, 'Y', 5),
(6, 'Mouse', 25, 60, 'N', 6),
(7, 'Printer', 200, 5, 'Y', 7),
(8, 'Webcam', 75, 25, 'N', 8);
UPDATE Product
SET Quantity = Quantity + 1
WHERE ProductID = :deleted_product_id;
SELECT DISTINCT p.ProductID, p.ProductName, p.CustomerID, r.CustomerName
FROM Product p
JOIN Registration r ON p.CustomerID = r.CustomerID;
--As an Admin, I want to generate a report with list of all sorted transactions.
CREATE TABLE Transaction (
    TransactionID INT PRIMARY KEY NOT NULL,
    CustomerID INT REFERENCES Registration(CustomerID) NOT NULL,
    ProductID INT REFERENCES Product(ProductID) NOT NULL,
    TotalAmount INT NOT NULL,
    no_of_items INT NOT NULL
);
select * from Transaction;
INSERT INTO Transaction (TransactionID, no_of_items, TotalAmount, CustomerID, ProductID) VALUES
(1, 6, 1000, 1, 1),
(2, 7, 500, 2, 2),
(3, 5, 300, 3, 3),
(4, 9, 150, 4, 4),
(5, 7, 50, 5, 5),
(6, 3, 25, 6, 6),
(7, 7, 200, 7, 7),
(8, 8, 75, 8, 8);
SELECT
    :transaction_id,
    :customer_id,
    ProductID,
    (SELECT SUM(Price) FROM Product WHERE ProductID IN (:product_id_list)),
    COUNT(*)
FROM
    Product
WHERE
    ProductID IN (:product_id_list);
SELECT *
FROM (
    SELECT *, ROW_NUMBER() OVER (ORDER BY TotalAmount DESC) AS rn
    FROM Transaction
) AS T
WHERE rn = 2;
--As an Admin, I want to generate a report of all those customers whose order has been placed sucessfully.
CREATE TABLE Admin (
    AdminID INT PRIMARY KEY NOT NULL,
    AdminPassword VARCHAR(255) NOT NULL,
    CustomerID INT REFERENCES Registration(CustomerID) NOT NULL,
    ProductID INT REFERENCES Product(ProductID) NOT NULL,
    TransactionID INT REFERENCES Transaction(TransactionID) NOT NULL
);
select * from Admin;
INSERT INTO Admin (AdminID, AdminPassword, CustomerID, ProductID, TransactionID) VALUES
(1, 'adminPass1', 1, 1, 1),
(2, 'adminPass2', 2, 2, 2),
(3, 'adminPass3', 3, 3, 3),
(4, 'adminPass4', 4, 4, 4),
(5, 'adminPass5', 5, 5, 5),
(6, 'adminPass6', 6, 6, 6),
(7, 'adminPass7', 7, 7, 7),
(8, 'adminPass8', 8, 8, 8);

SELECT 
    r.CustomerID, 
    r.CustomerName, 
    t.TransactionID, 
    p.ProductName
FROM 
    Registration r
JOIN 
    Transaction t ON r.CustomerID = t.CustomerID
JOIN 
    Product p ON t.ProductID = p.ProductID;