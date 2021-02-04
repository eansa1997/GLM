Allan Gershon - Assignment 5 - CSCI 370

How Requirements Were Dealt With

1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).

I made an Item List class that contains Item objects in a list. To allow users to edit their list, I added methods so that they can add, delete, or change quantities in it.

2. The application must contain a database (DB) of ​items​ and corresponding ​item types​.

At this point I cannot create a database so the best I could do was include two separate lists in the Grocery Store class, one for item types, and the other for items. Each object of the Item class also has an typeName variable from the Item Type interface to distinguish its item type.
    
3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

Users can add items to their list with the methods described previously. Also, the Item List class has a method to change quantities of a given item. The hierarchical structure of items and their type is shown in Item Type being an interface and Item implementing it.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.

A method to search items was included in the GroceryStore class so that it can be called to search that class' Items list, instead of using an actual DB. Found matches are added to a list that are then shown to the user. If no matches are found, the item and its type are saved into the lists with the saveNewItem() method.

5. Lists must be saved automatically and immediately after they are modified.

Lists are saved immediately after modification by merely changing the contents of specific instances of Item Lists.

6. Users must be able to check off items in a list (without deleting them).

The Item List class contains a method to check or uncheck items in their list.

7. Users must also be able to clear all the check-off marks in a list at once.

The Item List includes a method to clear all checks in the list

8. Check-off marks for a list are persistent and must also be saved immediately.

No database is available at this point, but the certain desired instance of a list is edited to save changes to which items are checked or unchecked in an item list.

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).

The method that adds items to a list in the Item List class inserts them based on its type. It can do this perhaps by sorting items by their type or hashing different items into different item type keys.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.

The User class allows for each user to have multiple lists with their own names. It also contains methods for them to create, delete, and rename lists.

11. The User Interface (UI) must be intuitive and responsive.

There aren't too many moving parts to my UML so its simplicity should make it easy to understand.
