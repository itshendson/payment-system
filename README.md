# Documentation
Payment System is a simple program that allows customers to select the product they would like to order and deposit 
bills or coins (one at a time) into the program. Customer can then execute the buy order whereby the 
Payment System checks whether the purchase can be made (such as whether customer's payment is sufficient and 
whether the Payment System has sufficient change to give back customer). If the purchase is successful, it will
return a Receipt to the customer containing the purchased product as well as any change. 

A major part of the program is its ability to calculate the change necessary to give to the customer.

## Requirements
- Customers can select product want to order
- Customers can pay in (cash) bills or coins
- Customers can cancel the order and refund
- Customer makes a successful transaction it should get product and change back
- Payment system can receive tender
- Payment system will know if customer overpaid
- Payment system will know if customer underpaid
- Payment system will know if it does not have enough tender to give change to customers
- Inventory of products (finite)
- Inventory of tender (bills and coins)

## Design (Classes)
- PaymentSystem interface
- PaymentSystemImpl
- Inventory (for tender and product)
- Product (enum)
- Tender (enum)
- Receipt (contains purchased product and change)
- Cmd interface (to interact with if have time)