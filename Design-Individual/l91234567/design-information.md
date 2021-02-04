#Requirements

##1.A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).

	To realize this requirement, I added to the design a class user. Class user will use groceryList, items and listMenu classes. 
	Also, I added to the design a class groceryList with attributes items, quantity and units, and operations addItems(itemName), deleteItems(itemName), changeQuantity(itemName, quantity). Class user will use groceryList class. Class items is parts of groceryList class.

	According to requirement 3, the attribute items changed into itemName and types.

		-itemName: the name of the item, with data type String.

		-types: the types of the item, with data type String.

		-quantity: the quantity of the item, with data type integer. 

		-unit: the unit of the quantity, with data type String.

		-addItems(itemName): add item to a list.

		-deleteItems(itemName): delete item from a list.

		-changeQuantity(itemName, quantity<Integer>, unit): change the quantity of items in the list.




##2. The application must contain a database (DB) of items and corresponding item types.

	To realize this requirement, I added to the design a class items with attributes itemName and types. Class user will use items class. Class groceryList will contain items class.

		-itemName: the name of the item, with data type String.

		-types: the types of the item, with data type String.


##3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

		To realize this requirement, I change the attribute items in class groceryList into itemName and types. But not consider input new operations for picking items from a hierarchical list in this design because this requirement is more involved in GUI design not the system, operation addItems(itemName) is enough for now.



##4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.

	To realize this requirement, I add operations to class items they are: specify(itemName): return itemName, intend(itemName, yes<Boolean>) and addNew(itemName, types). 

		-specify(itemName): return itemName: specify an item by typing its name and return the similar name of the item. And do operation intend(yes<Boolean>) next.

		-intend(yes<Boolean>): ask the users, for each of them, whether that is the item they intended to add, if yes<Boolean> is true, add the item to the database, otherwise do operation addNew(item, types).

		-addNew(itemName, types): ask the user to select a type for the item and then save the new item, together with its type, in its database.


##5. Lists must be saved automatically and immediately after they are modified.

	To realize this requirement, I add an operation autosave() to class groceryList, which executes after any operation execution in the class groceryList.

		-autosave(): save the current list 


##6. Users must be able to check off items in a list (without deleting them).

		To realize this requirement, I add an attribute and an operation to the class groceryList.

		-attribute: checkoff (determine whether the item is check off or not, with data type Boolean. Might be represented as a toggle button in GUI).

		-operation: checkoff (itemName, yes<Boolean>):  to give an item check-off mark by its itemName and determine whether it is check off or not by yes<Boolean>, if yes<Boolean> is true that means the item is check off, otherwise it is not.



##7. Users must also be able to clear all the check-off marks in a list at once.

	To realize this requirement, I add an operation to class groceryList, that is clearAll().

		-clearAll(): to clear all check-off marks


##8. Check-off marks for a list are persistent and must also be saved immediately.

	Not sure what persistent means here, from my understanding, this requirement will be not considered, because it is more likely a GUI design, which the persistence of the check-off marks might be represented by a toggle button to determine whether the item is check off or not. Also, according to requirement 5, the requirement to save immediately is already resolved.


##9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).

	Not considered because it does not affect the design directly. This will be part of the GUI design, which can group the items by the types attribute, or in other arrangements. 


##10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.

	To realize this requirement, I add to the design a class listMenu with attribute listName and operations createList(listName), nameList(listName), selectList(listName) and deleteList(listName). Class user will use listMenu class. Class groceryList is a subtype of listMenu class.

		-listName: name of the list, with data type String.

		-createList(listName): create a new list.

		-nameList(listName): name or rename a list.

		-selectList(listName): select a list.

		-deleteList(listName): delete a list.


##11. The User Interface (UI) must be intuitive and responsive.

	Not considered because it does not affect the design directly. More rely on the GUI design.


###Notes: for classes items and listMenu are consider as database, all data under the attributes itemName or listName will have their unique primary key. Therefore, to be more accurately, the operations that takes itemName or listName as parameters can be considered as using their primary key.
