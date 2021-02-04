1.	A grocery list consist of items, which will allow user to add, delete, or change the quantity of an item. To realize this requirement, I added to the design a groceryList class consisting of the following attributes: item_Id and quantity. The operation of this class are as follows: addItem(), removeItem(), and changeQuantitiy(). groceryList can have one or more items.

2.	The application must contain a database of item and corresponding item type. To realize this requirement,  I added to the design an item class consisting of the following attributes: item_Id and itemName. The operation of this class are addItem() and deleteItem(). An item can be in 0 to many groceryList.

3.	Users may be able to add item to the list and must provide the quantity of said item. To realize this requirement, we need to create a user class which consist of a name and user_id attributes. The operation of this class should be createUser() and deleteUser(). User can add item by using the operation that is defined in the groceryList.

4.	User may be able to specify item by typing its name, the application will search the database. Item will be added to list if they are found, otherwise will be prompted to select a type, and add the item to the database. This does not refer to the design of the class but how a user interacts with the database.

5.	List must be saved automatically and immediately after modification. This is a feature was realized through the operations of the groceryList class.

6.	User must be able to check off item from a list without deleting it. This requirement can be realized by creating a CheckOffItems class consisting of the following attributes: item_id and quantity. The operation addCheck() must be included. CheckOffItems can have 0 to many items checked off.
7.	User must be able to clear all the check marks from the list at once. To realize this requirement, we must include the operation clearCheck().

8.	Check-off mark are persistent and must be saved immediately. This requirement is fulfilled through the operation of addCheck() in CheckOffItems class.

9.	Item are presented in a list grouped by item type so that user can shop for a specific type at a time. To realize this requirement, I added to the design an itemType class which consist of the following attributes: type_id and typeName. The operation of this class are createType(), deleteType(), addItem(), and removeItem(). The itemType may have 1 to many items.

10.	Application must support multiple list and ability to create, delete, and rename list. To realize this requirement, we must add a list_id attribute to groceryList and CheckOffItems class. In addition, we must add a listName attribute to groceryList and the operation renameList(). Each user may have 0 to many groceryList, while each groceryList can only have one user. 

11.	The User Interface must be intuitive and responsive. This is not considered in the design refer to how the user interaction and experience with the application. 
