# CS 370 Team 1 Design Discussion



## Individual Designs

1. Design 1- Allan Gershon
   1. Design
   
      <img src="Individual Design Photos\Allan Gershon Design.png"/>
   
   2. Pros
      1. separating item and item type
      2. Class relations/dependencies
      3. ItemType good to have separately from Item
   
   3. Cons
      1. Item class should have cost/price value?
      2. Make another class to see whether Item object is checked off instead of having it in field in Item class?
      3. Quantity field for items
      4. Do we need Grocery Store class? It’s an abstraction of our data/database
      5. ItemType shouldn't be a variable in Item class, it should be its own class
      6. Add getters and setters later, as those are not design-specific
      7. Relationship between Item List and Item - CheckOffItem class
2. Design 2- Jeffrey Tom
   1. Design
   
      <img src="Individual Design Photos\Jeffrey Tom Design.png"/>
   
   2. Pros
      1. Included a lot of the right methods
      2. User id good for multiple users
   
   3. Cons
      1. Forgot to add public and private methods and variables
      2. Item Type should be separated and having separate class
      3. Database class doesn’t need to specify the types in the diagram
      4. No way to represent multiple grocery lists
      5. A lot of right methods, but in wrong class
      6. saveNewItem() is ambiguous and better naming convention could make things clearer
      7. Quantity variable to keep track of quantity
      8. Missing cost of item attribute
3. Design 3- Esteban Ansaldo
   1. Design
   
      <img src="Individual Design Photos\Esteban Ansaldo Design.png"/>
   
   2. Pros
   
      1. Covers all the requirements
      2. Item class has all properties needed for item type separation
   
   3. Cons
   
      1. Cost variable in Item class not part of specs
      2. Item UI state (checked or unchecked, quantity) can be separated from the Item class itself
      3. ItemDB methods can be included in different classes, ItemDB class may not be needed
4. Design 4- Xin Huang Liu
   1. Design
   
      <img src="Individual Design Photos\Xin Huang Liu Design.png"/>
   
   2. Pros
   
      1. autosave operation is a good idea to auto save	the list, but it might be handled by the database automatically
      2. intend operation will be a good idea to ask the user if they want to add a new item
   
   3. Cons
   
      1. itemtype have a separate class
      2. checkoff, quantity, attributes and thier related operations should be in items class
      3. the visibility signs should be included
      4. unit attributes might be removed to keep simple for now
5. Design 5- Luis Toro
   1. Design
   
      <img src="Individual Design Photos\Luis Toro Design.png"/>
   
   2. Pros
      1. Relationship and multiplicity is well represented.
      2. Creating a separate class for Checked off items.
      3. ID are useful in classes so that we can access instances more easily.
   
   3. Cons
      1. In user class, we do not need to include  the create or delete user method in the design.
      2. In CheckedOffItem, there should be an attribute that indicates if the item is checkedOff.
      3. Needs a method that allows the user to create and delete a list.
      4. In ItemType, createType and delete type are unnecessary. 
      5. There should an associative relationship between CheckedOffItem and Item.
6. Design 6- Shmuel Halbfinger
   1. Design
   
      <img src="Individual Design Photos\Shmuel Halbfinger Design.png"/>
   
   2. Pros
      1. Nicely Designed
      2. Very thorough in naming of attributes and methods
      3. Good job on designation of public and private methods and attributes
   
   3. Cons
      1. The diamond in the relationship between Item and ItemType should be filled in, as the ItemType is necessary for the Item to exist.
      2. There should be a diamond to represent the multiplicity between the GroceryList class and Item class, as a GroceryList has many items.
      3. GroceryShopper doesn't need fields such as password or username, as the app will be local to each machine.
      4. The methods in the ItemType class aren't necessary and should be removed.

## Team Design

<img src="Individual Design Photos\Team Design.png" alt="Team Design"/>

##### Main Design Decisions by Class

Here, we will specify the justification for the decisions behind our classes, along with what requirements they fulfill:

1. *User*
   2. The methods *addList*, *renameList*, *selectList*, and *deleteList* fulfill requirement 10, allowing for multiple lists at a time which the user can add to, delete, rename, or select.
   3. The *addNewItem* method fulfills the end of requirement 4, allowing users to add items to the database under a certain ItemType.
2. *GroceryList*
   1. The GroceryList class fulfills part of requirement 1. It is the class that will signify the lists that the User has.
   2. The *groceryListId* and *groceryListName* attributes help fulfill requirement 10, which requires functionality for multiple lists. These attributes help differentiate between lists in the database and will allow for selecting lists. These attributes also help fulfill requirement 5. The groceryListId can be used to save data in the database, allowing for list persistance.
   4. The methods *addItemToList*, *deleteItemFromList*, and *changeItemQuantity* help fulfill part of requirement 1 which calls for the ability to add items to, delete items from, and change the quantity of items from the list.
   5. The relationship between *User* and *Grocery List* is a composition relationship and helps fulfill requirement 10. A User has multiple GroceryLists, and if the User is deleted, the Lists also are deleted.
   6. The *addItemByItemType* method fulfills requirement 3, which requires the User to be able to pick items by item type and then item. This method is private and can be called by the *addItemToList* method depending on how the user wants to choose an item.
   7. The *addItemByItemName* method fulfills the first part of requirement 4. Like the *addItemByItemType* method, it is private and can be called by the *addItemToList* method.
   8. The *checkOrUncheckItem* method fulfills requirement 6 by allowing the User to check off items without deleting them.
   9. The *uncheckAllItems* method fulfills requirement 7 by allowing the user to uncheck all items in the list.
3. *Item*
   1. The Item class fulfills part of requirement 1, as the items will populate the list.
   2. The *itemId* and *itemName* attributes help with persistence, as items can be selected from the database.
   3. The relationship between *GroceryList* and *Item* is a multiplicity relationship. A list has multiple items, but if a GroceryList is deleted the Items persist. This helps fulfill requirement 1.
4. *GroceryListItem*
   1. The GroceryListItem class associates the GroceryList class and the Item class and contains attributes that relate to the Items in a list without affecting the items as they exist in the database. This will allow Items to remain statically in the database and be changed dynamically during the implementation.
   2. The *quantity* attribute helps fulfill requirement 1, since the *changeItemQuantity* method can simply check the quantity of the item. It also helps with the persistence of the list, denoted in requirement 5.
   3. The *quantityUnit* attribute also helps fulfill requirement 1, but setting the type of quantity for an item (examples include: pounds, dozens, ounces, or individual). It is part of this class because a single item can have many different quantity types, but an item as it pertains to a list can only have one quantity type.
   4. The *checked* boolean attribute helps with requirements 6 and 7, by allowing the User to simply reverse its value to either check or uncheck the item. It also helps with requirement 8, since the presence of this value allow the check marks to be persistent.
5. *ItemType*
   1. The ItemType class helps with requirement 2, as each item will have a corresponding item type.
   2. The composition relationship between Item and ItemType helps with requirement 2, as without an ItemType, an Item can't exist. It also helps with requirement 9 in the implementation, as it allows for easier grouping of Items by their ItemType.

Requirements 9 and 11 are requirements that are mainly implementation based and therefore didn't factor as much into the design.

##### Comparisons between Team Design and Individual Design

1. Comparison to Design 1- Allan Gershon
   1. Similarities
      1. The way I represented relationships (dependencies, composition, etc.) is similar to the final design.
         We showed this by thinking about how whether certain entities could exist without each other.
      2. Our methods in respective classes were similar for the most part, except for some having different names that still planned to have the same functionality.
      3. Both my design and the final one did not have methods in the Item and ItemType classes because we could not think of any operations directly linked to them.
      4. Our multiplicities represented with "*" and numbers (0, 1, etc.) were also the same between classes.
   2. Differences (with Justifications)
      1. I identified each individual grocery list by its name, while the final design uses an integer ID.
         An integer ID would be better because it helps distinguish uniqueness among lists better as opposed to string names.
      2. I specified the items being placed in a list data structure while the team design didn't.
         It may be better not to because right now we're focusing on an abstract view of our design and the GroceryList class being represented by a list is already self-explanatory.
      3. I included a GroceryStore class to contain a database's Items and ItemTypes that other classes could get data from.
         Our final design did not do this because we assumed the GroceryStore would possibly be represented in a database.
         This would be better because having Items and ItemTypes hardcoded in a GroceryStore class in a linear manner would be impractical.
      4. The final design included a ternary relationship between GroceryListItem, Item, and GroceryList.
         I only had a relationship between Item and ItemList.
         It's better this way because the GroceryListItem class includes a boolean variable to distinguish if an item was checked off from the list.
         It also includes an integer variable to see how much of a certain item a user may want. Separating Item and GroceryListItem is better this way because an Item object
         cannot be checked off or have its quantity changed until it's placed into a list.
      5. In my User class, I included a field to keep track of all lists the user would have. This is not in the final design because a user's lists would perhaps be represented better not in actual code.
      6. The final design included a method in the User class to add new items perhaps to add items to a GroceryStore's database while I did this by my saveNewItem method in my GroceryStore class.
         This is better because a user adding the new item after searching for it would be more accurately represented by this. Also, the final design does not have a GroceryStore class.
      7. The final design tried to enforce uniqueness of instances in entities with the GroceryList, Item, and ItemType entities having ID fields, whether they were integers or strings.
         I tried something similar, except I used mostly only strings to help maintain unique identifiers.
      8. I did not use plus (+) or minus (-) signs to indicate whether a field or method is public or private, respectively. The final design did this to help further plan our code in the longterm by envisioning how fields and methods interact with one another in classes.
      9. Added a *quantityUnit* attribute in order to know what type of quantity is in the GroceryList
2. Comparison to Design 2- Jeffrey Tom
   1. Similarities
      1. Aggregation from item to GroceryList because a GroceryList can have have 0 or more items and can still exist even if there are no items in the GroceryList
      2. One to many relationship between the user and a grocerylist because a user can have 0 or many grocerylist
      3. GroceryList has two attributes in Listid and listname to identify the list of the user because a user can have more than one GroceryList
   2. Differences (with Justifications)
      1. Includes an ItemType class, instead of adding just the attribute of itemtype to make it easier to search the item within the database
      2. User does not include UserId or UserName, to simply the the design of the application
      3. Remove DB class that keeps track of the item by id and item ItemType because there should be a database already built in, therefore we wouldn't require this class
      4. Added quantity attribute to keep track of the quantity of the item in which a user adds to their list
      5. User should have methods addList(), renameList(), selectList(), deleteList(), addNewItem(), which allows the user to create new lists, rename list, select list, delete list, and adding new item to a database and are the behaviors of the user
      6. GroceryList class has methods addItemToList(), deleteItemFromList(), changeItemQuantity(), addItemByItemType(), addItemByItemName(), checkOrUncheckItem(), uncheckAllItems() because these are the behaviors of the GroceryList
      7. Added GroceryListItem class to simplify design in which it contains the quantity and if item is checked attributes
      8. Added a *quantityUnit* attribute in order to know what type of quantity is in the GroceryList
3. Comparison to Design 3- Esteban Ansaldo
   1. Similarities
      1. Covers all the requirements
      2. Item class has all properties needed for item type separation
      3. List class that has a 1 to many item relation.
   2. Differences (with Justifications)
      1. The biggest difference is the Item class. In the team design, all the properties of my Item class design are split into 3 classes, Item, ItemType, and GroceryListItem
      2. The DB is modeled in the team design with ItemType and Item, whereas my design abstracts the DB into a class that will 
         use my Item class for the data model.
      3. Some methods are similar but in different classes.
      4. Private or public attribute is specified.
      5. Added a *quantityUnit* attribute in order to know what type of quantity is in the GroceryList
4. Comparison to Design 4- Xin Huang Liu
   1. Similarities
      1. have same classes: User (user), Item (items) and GroceryList (groceryList)
      2. attributes and operations are similar
      3. Kept the *unit* attribute and changed it to *quantityUnit*
   2. Differences (with Justifications)
      1. team design UML diagram combined my listMenu class into User class
      2. types attribute under my groceryList class has seperated into a new class which named ItemType
      3. A sub class, GroceryListItem, has added between class GroceryList and Item
      4. redefined relationships between classes
      5. User class: no attributes remain, which means will not require the user to have an account to use the app, and has the operations to interact with the lists
      6. GroceryList class: contains attribute groceryListId and groceryListName, which will have a database to save the information of the lists, and has operations to modify the items choices in the list
      7. Item class: contains the attribute itemId and ItemName, which will have a database to store these item information
      8. GroceryListItem association class: has the attribute quantity and checked, to support the GroceryList class to determine the item status
      9. ItemType class: contains the attribute itemTypeId and itemTypeName, which might have a database to store the information about the item types, for requirement 2 and 4
5. Comparison to Design 5- Luis Toro
   1. Similarities
      1. The relationship between the User and the GroceryList is that for each User, a User can have 0 to many Grocery List.
      2. Composition between User and GroceryList, and ItemType and Item because the Grocery List is unique to the User and can not exist without it.
   
      3. Composition between ItemType and Item because as stated in requirement 4 a new item must always be saved with a corresponding Type meaning the item can not exist without an ItemType.
   
      4. Item and ItemType contains the similar attribute as in the original design. 
   
      5. Item from grocery list are represented by separate class because it is a separate entity that depend on the grocery list. 
   2. Differences (with Justifications)
      1. User class has been updated to contain the addList(), deleteList(), and selectList() method; The original designed lacked these functionalities and must be included to uphold to requirement 10. 
      2. Method renameList() has been moved from GroceryList class to User class because this a functionality that a user can have to manipulate the Grocery List.
      3. Ability to add an item to the database has been moved from the item class to the User class because it is the user who will be updating the database.
      4. Removed quantity and item_id attribute from GroceryList since it is redundant and already defined in the GroceryListItem and Item class, respectively. 
      5. Method for adding checks and removing all checks have been moved to the GroceryList class because it is unique to each  Grocery List and can be toggled there.
      6. CheckOffItems renamed to GroceryListItem now includes an attribute for tracking the status of the checks and methods have been removed because they are now satisfied by the methods in GroceryList Class.
      7. Methods from item and itemtype class has been removed because they can be satisfied by the methods in User and GroceryList, respectively, or are not needed in the design.
      8. Added a *quantityUnit* attribute in order to know what type of quantity is in the GroceryList
6. Comparison to Design 6- Shmuel Halbfinger
   1. Similarities
      1. This individual design was the basis for the team design.
      2. All of the classes were kept in the design in relatively similar format to the individual design.
   2. Differences (with Justifications)
      1. The GroceryShopper class was changed to the User class. Since each device will have a local version of the app, it made more sense to call this the User class.
      2. All attributes were removed from the User class, since our database wouldn't have multiple users as everything would be handled locally
      3. Instead of having separate methods to check and uncheck items in the list, there would be one method that would check or uncheck the item. This was done because we have the checked boolean value, which can simply be reversed.
      4. A multiplicity relationship was established between the Item class and the GroceryList class. This was done because every GroceryList has multiple items, and we wanted to represent that.
      5. The methods of the ItemType class were removed. This was because they were not necessary in terms of the class design as it pertains to the requirements of the project.
      6. Added a *quantityUnit* attribute in order to know what type of quantity is in the GroceryList

## Summary

- The meeting to discuss our team design went flawlessly. We all were respectful of each other's opinions and gave constructive criticisms of each others' designs. At the end, we took the design we thought was most complete and elaborated on it.
- There was a bevy of ideas connected to each design, and we worked together to take them into account. One thing we saw was that a lot of things were going to be implementation based and didn't need to be included in the design. This served to familiarize us with the implementation and to keep our design concise.
- Each member was individually tasked with taking down comments on the pros and cons of their design, which were then inserted in the <strong>Individual Design</strong> part of the document. The project manager compiled these contributions.
- After discussing the team design together, we built the team design diagram collectively. Each member was then individually tasked with noting the similarities, differences, and justifications for differences between their individual design as compared to the team design. These contributions were placed in the <strong>Team Design</strong> section of the document. The project manager compiled these as well.
- We took this more asynchronous approach for a few reasons:
  1. Everybody knows their own design best and is therefore able to look at the differences and similarities to the team design more clearly
  2. We all have different schedules and workloads, and therefore we found that we could finish this deliverable more efficiently with a more asynchronous approach.
- We focused our attention on collectively coming up with a design by going over the requirements again in depth, so we felt like this design encompassed all of our opinions on the project. Therefore we felt comfortable with the design and with working asynchronously.
- As we go forward with the project, we look to keep the same level of professionalism and respect that we had during this meeting.