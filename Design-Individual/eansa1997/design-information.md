# Requirements

**1.** A grocery list consists of items the users want to buy at a grocery store. The application
must allow users to add items to a list, delete items from a list, and change the quantity
of items in the list (e.g., change from one to two pounds of apples).

I designed a GroceryList class where each instance is a different list. Internally all the items are stored inside a List (java). The GroceryList class implements the operations addItem, and deleteItem. The quantity of each item is a property of the item and is implemented in an Item class.

**2.** The application must contain a database (DB) of ​items​ and corresponding ​item types​.

The class ItemDB provides an instance of the DB (local or remote). This class has operations that then use the DB. The design of the DB in this case is trivial and not needed. Each operation is assumed to use the correct query. The operations include searchForItem, getAllItems, getItemsWithType, and addNewItem.

**3.** Users must be able to add items to a list by picking them from a hierarchical list, where
the first level is the item type (e.g., cereal), and the second level is the name of the
actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
quantity for that item.

Each item inside a instance of GroceryList has an attribute 'itemType'. The UI logic can handle the separation of all the items into respective types. The UI must have a editable quantity property that then calls the updateQuantity method of the Item class. 
**4.** Users must also be able to specify an item by typing its name. In this case, the
application must look in its DB for items with similar names and ask the users, for each
of them, whether that is the item they intended to add. If a match cannot be found, the
application must ask the user to select a type for the item and then save the new item,
together with its type, in its DB.

The ItemDB class has a method to search for items. It also has a class to insert a new item into the DB. The UI is in charge of presenting the search results properly.

**5.** Lists must be saved automatically and immediately after they are modified.

The implementation of this can vary wildly in design choices made by the lead developer as this pertains to state management. Ideally the app should have the proper architecture so that each time an attribute is changed, they are written to a persistent storage method. Since the requirements specify multiple grocery lists, there is a UserLists class that stores a list of GroceryList instances. This UserLists class has a method saveLists that should implement this permanent storage.

**6.** Users must be able to check off items in a list (without deleting them).

The Item class has an boolean attribute called 'checked'. This attribute can be shown and updated through the check method inside Item. 

**7.** Users must also be able to clear all the check-off marks in a list at once.

The GroceryList class has the method clearChecks which will iterate through all the items and call the check method ( req. 6).

**8.** Check-off marks for a list are persistent and must also be saved immediately.

Refer to requirement 5.

**9.** The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of products at once (i.e., without having to go back and forth
between aisles).

This requirement is synonymous with #3. 

**10.** The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
farmer’s market list”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete lists.

The UserLists is a class that stores a list of GroceryList instances. The rename list is modifying the listName attribute of the GroceryList class and is implemented in the method updateName. Thu UserLists class has a method's to create, delete, and select lists.

**11.** The User Interface (UI) must be intuitive and responsive.

Not considered because it does not affect the design directly.