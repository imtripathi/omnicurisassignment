     				OmniCuris Assignment
      Steps to Run the project
1)	Download the project from git url- https://github.com/imtripathi/omnicurisassignment.git
2)	Install Mysql  by following the link- https://dev.mysql.com/doc/refman/8.0/en/windows-installation.html
3)	Once Mysql is installed create the db name assignment.
4)	Open the code in eclipse  and change the username ,password,url as per your own credentials of mysql details in application.properties file.
5)	Run the springboot application .Entity will be created while starting  the application .
6)	Open the swagger Url in your browser- http://localhost:8080/assignment/swagger-ui.htm
7)	Once the swagger is open we can see all the api’s listing .
8)	We can Hit all the api’s from swagger and check.


Software and tools Used
1)Mysql
2)Java 1.8
3)springboot
4)JPA for database interaction.
5)swagger implementation is done for listing the api’s and hitting the api easily from browser itself.




APIs and its Description

1)Order

a) PUT /orders/createorder

This is use to create the new order either single or multiple.



Request-



[
  {
    "emailId": "string",
    "itemId": 0,
    "quantity": 0
  }
]


Response-


{
“message”:”String”,
“status”:”String”
}



b) GET /orders/getallorders


It gives all the orders done .


Response-


[{
  "emailid": "string",
  "itemid": 0,
  "orderid": 0,
  "quantity": 0,
  "totalamount": 0)
}]


2)Items

a) PUT /items/createitem


This service is used to insert the item in Db


Request-


{
  "brand": "string",
  "itemname": "string",
  "price": 0,
  "stock": 0
}


Response-


{
  "brand": "string",
  "itemid": 0,
  "itemname": "string",
  "price": 0,
  "stock": 0
}

b) GET /items/getitem/{id}


This service is used to get the items by there ItemId


Request-

Id


Response-


{
  "brand": "string",
  "itemid": 0,
  "itemname": "string",
  "price": 0,
  "stock": 0
}



c) GET /items/getallitem

This service is used to get All the item present


Response-



[{
  "brand": "string",
  "itemid": 0,
  "itemname": "string",
  "price": 0,
  "stock": 0
}]




d) POST /items/updateitem


This Service is used to update the Item details.


Request-


{
  "brand": "string",
  "itemid": 0,
  "itemname": "string",
  "price": 0,
  "stock": 0
}


Response-

{
  "brand": "string",
  "itemid": 0,
  "itemname": "string",
  "price": 0,
  "stock": 0
}


e) DELETE /items/deleteitem/{id}



This service is used to delete the item from db by its id



Request-

Id



Response-


{
“message”:”String”,
“status”:”String”
}



Database Details



Database name-assignment



Entity-


a)item


Column name-


i)itemid-autogenerated
ii)brand
iii)itemname
iv)price
v)stock




b)order_details


Column name-

i)ordered-autogenerated
ii)emailed
iii)itemid
iv)quantity
v)totalamount
