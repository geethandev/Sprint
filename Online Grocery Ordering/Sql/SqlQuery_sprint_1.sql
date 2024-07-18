SELECT
    c.CustomerID,
    c.CustomerName,
    COUNT(p.ProductID) AS orders_placed
FROM
    Customer c
LEFT JOIN
    Product p ON c.CustomerID = p.CustomerID
GROUP BY
    c.CustomerID, c.CustomerName
ORDER BY
    c.CustomerID ASC;



SELECT *
FROM Product
WHERE Price > 1000;



SELECT
    c.CustomerID,
    c.CustomerName,
    COUNT(p.ProductID) AS total_products_ordered
FROM
    Customer c
JOIN
    Product p ON c.CustomerID = p.CustomerID
GROUP BY
    c.CustomerID, c.CustomerName
HAVING
    COUNT(p.ProductID) > 5;
