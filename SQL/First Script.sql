/*
*/
CREATE TABLE groceries (
	id INTEGER PRIMARY KEY,
    name TEXT,
    quantity  INTEGER,
    cost LONG,
	aisle INTEGER
);



INSERT INTO groceries VALUES (1, "Bananas", 4,1,7); # adds data to the table specified with the values specified
INSERT INTO groceries VALUES (2, "Peanut Butter", 4,1,2);
INSERT INTO groceries VALUES (3, "Dark Chocolate Bars", 2,1, 2);
SELECT name FROM groceries; # The select command returns the values of rows in the columns specified from the table specified
SELECT * FROM groceries ORDER BY aisle; # Use the asterisk to mean "all columns". Order by decides what order it should be in
SELECT name,quantity FROM groceries WHERE aisle > 5; #S eparate column names with commas after the select. Use where to determine which rows to select (Kinda like the which statement in R)

# Aggregate functions
SELECT SUM(quantity) FROM groceries; # Selects sum of the quantity column
SELECT SUM(quantity) FROM groceries GROUP BY aisle; # Groups the table by aisle, then finds the sum of quantity for each group
SELECT aisle,SUM(quantity) FROM groceries GROUP BY aisle; # Also lists the first Aisle value for each aisle group in the aisle column of results
SELECT MAX(quantity) FROM groceries; # finds max value of quantity column




DROP TABLE groceries; # Deletes a table that's specified
