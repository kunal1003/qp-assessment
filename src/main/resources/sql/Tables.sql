CREATE TABLE GroceryItem (
    id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    name VARCHAR2(255),
    price NUMBER(10, 2),
    stockQuantity NUMBER(10)
);

CREATE TABLE UserGrocery (
    id NUMBER(10) PRIMARY KEY,
    username VARCHAR2(255),
    password VARCHAR2(255),
    isAdmin NUMBER(1) -- Assuming 1 for true, 0 for false
);