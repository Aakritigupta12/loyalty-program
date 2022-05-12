# loyalty-program

A loyalty program for customers made with Springboot and in memory h2 database. We give them points every time they spend with us. Later, the customer is allowed to use the loyalty points by converting them into money.

1. How a customer can acquire new points:
Every euro spent on one transaction will give the customer

  • 1 pending point for every euro until 5000 INR value of transaction
  • 2 pending points for every euro from 5001 INR to 7500 INR value of transaction
  • 3 pending points from 7501 INR value of transaction
  
Example:
A.       A transaction of 4500 INR => 4500 points
B.       A transaction of 7800 INR -> 10900 points

 • 5000 points for first 5000 INR 
 • 2*2500 points for 5001 -> 7500 INR 
 • 3*300 points for 7501 -> 7800 INR 
 
New pending points become available points for use at the end of every week if:

•       The customer has spent at least 500 INR that week
•       At least one transaction exists for that customer on every day of the week

 
A user will lose all the points if no transaction was made in the last 5 weeks.
 
2. How a customer can use the available points:
User can use some or all available points. Every point is worth 1 INR.
 

3. A customer should be able to query for balance of pending and available points and history of loyalty points allocation & usage
