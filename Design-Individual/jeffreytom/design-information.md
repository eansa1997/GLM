# GroceryList UML Class Information:

## User
* UserId - keeps track of users so that each user has a particular id associated with them
* addItem() - allows for the user to be able to add an item to the lists
* deleteItem() - allows for the user to be able to delete an item from the lists
* changeQuantity() - allows for the user to be able to change the quantity of the item to add to the lists
* checkOffItemOnList() - allows checks off the one item on a lists
* clearAllCheckMarksOnList() - allows to clear all the check marks on the list
* A user can have 0 or more lists

## GroceryList
* ListId - keeps track of the list id, because a user can have multiple lists
* ListName - stores the name of the list
* createList() - creates a list based on the name that is given by the user
* renameList() - allows for the user to rename the list
* selectList() - allows the user to select the list based on the name or Id
* deleteList() - allows for the user to delete the list
* saveList() - allows for the user to save the list
* groupList() - allows for the user to group the list to be able to choose from the hierarchical list
* A GroceryList can have 0 or more items in the the list

## Item
* ItemType - is of type string and each item has an item type
* ItemName - is of type string and each item has a name
* ItemFound - is of type boolean and determines whether an item is found according to the database
* specifyItem() - allows the user to specify an item
* saveNewItem() - saves the new item that was not listed in the database
* Can have 0 or more items in a database

## Database
* ItemId: is of type int and stores the id of the Item to look up
* ItemType: is of type String and stores the type of the item
