# Test Plan

**Author**: Esteban Ansaldo, Allan Gershon

## 1 Testing Strategy

### 1.1 Overall strategy

Since this application is a very simple system, we decided to keep the testing strategy simple as well. We decided that doing unit tests on the functionality of our implemented software was a good idea to make sure every method works up to specification. Testing will mainly be done manually, with technologies like JUnit being utilized should the need arise. The reason for this is because our system was simple and could be tested manually in an efficient manner.

Unit testing will be done to test the functionality of each component of every Activity. We will do integration tests as needed to make sure that different Activities are connected as necessary and that there are no connectivity issues.

If we write coded unit tests, we will test the class methods using JUnit as Android studio has support for this, at least in the case where the tested unit does not have android platform dependencies. Test will be automated using gradle, as it is the default build tool of Android Studio. 

As software was developed and implemented, we ran regression tests manually using the Android Studio emulator functionality. This allowed us to test whether our functions led to further issues and allowed us to find and fix bugs in an iterative fashion. Lastly, manual system tests were done by the project manager, testing all of the functionality.

### 1.2 Test Selection

We are going to test every class method for object oriented classes. This will be black box testing as we do not care about the specific implementation of the method, just that it produces the desired output. We will build custom objects that would be present in a user's app and then test all the methods available.

Then, we will run system tests on either an Android Studio emulator or on an android device, by installing the device and testing its overall functionality. The system testing is mainly white box, as it is to check whether or not the code, as it is written, creates the functionality that we are looking for.

Regression testing was done as code was written by every user to check that the code of one component is not damaged by changes to other parts of the code. Each member was responsible to do these checks and note any bugs that came about.

### 1.3 Adequacy Criterion

Since we are creating custom class objects and supplying an input to the methods testing, we will know exactly what the output should be. We will create mainly 2 sets of tests, one that tests the validity of data at object creation/modification. The other set will supply bad data types to all the methods/constructors to assess our error catching.

### 1.4 Bug Tracking


As unit tests were completed, they were marked down in the table below as either passed or failed. The developers could look at that table to determine the functionality that needed to be fixed or modified. When we see a failed test at build time, we will note which unit failed and create a task for the team in our Trello board.

****

### 1.5 Technology


Unit tests were mainly run manually, with JUnit being utilized if needed.

Regression testing will always be utilized, as we will run tests every time we modify a class. If an old test fails, we will know which change affected that unit and fix the error accordingly.

## 2 Test Cases

### Unit/Integration Tests

These tests were meant to test the individual functionality of the components (in this case the functions within the activities) as well as their connectivity to each other. We tested each module and then tested to make sure every necessary module could be accessed from a given module, passing along the necessary information.

For each test, we describe the following:

1. Test Name
2. Activity in which it takes place
3. Purpose of the test
4. Steps to completing the test
5. Input required for the test
6. Expected Results
7. Result of the test
8. Whether the test passed or failed
9. Additional Information, if any


|Test Name |Activity |Purpose |Steps|Input Required  (if necessary)|Expected Result|Result |Pass/Fail |Additional Info|
|---|---|---|---|---|---|---|---|---|
|Start application |<none\> |Test that the application runs correctly |Install and Open the application|None|The application runs successfully and opens MainActivity|App opened without error on fresh install.|Pass|Previous installation DB was deleted properly.|
|Create List (Name entered)|MainActivity|Testing the creation of a new list and appending it to all the existing list.| Click the "Create new List" button, type in a list name, create the list. | App started without any issues, User is in MainActivity | A new list is created and can be selected| New list was created and is selectable.|Pass||
|Create List (Name not entered)|MainActivity|Test that, if a list is attempted to be created without a name, the list is not created| Click the "Create new List" button, don't type in a list name, attempt to create the list. | App started without any issues, User is in MainActivity |The user is notified that their list was not created.|Empty name did not result in a list being created.|Pass | |
|Rename List (name entered)|MainActivity|Test the renaming method of the User class.| Click the "Rename List" button, enter a new name for the list | App started without any issues, User is in MainActivity, and there is at least 1 created list |The Grocery List is renamed and saved|Rename button resulted in changed name.|Pass|   |
|Rename list (name not entered)|MainActivity|Test that if a name is not entered for a list, the user will be notified that a list was not renamed.| Click the "Rename List Button" | App started without any issues, User is in MainActivity, and there is at least 1 created list |The List is not renamed, user is notified that their attempt didn't work |Empty name resulted in the list not being altered. |Pass | |
|Delete List|MainActivity|Test the deletion of an existing list belonging to a user|Delete selected list from set of current lists. Try to get the deleted list by its original name.|App started without any issues, User is in MainActivity, and there is at least 1 created list|The deleted list should not exist or be accessible.|Test list was removed.|Pass| App was restarted to ensure data persistence. |
|Select List|MainActivity| Test the selection of a list by the user | Click on the name of a list | App started without any issues, User is in MainActivity, and there is at least 1 created list | The ListActivity should open for the selected list. | Test list was opened showing its contents (ListActivity)|Pass|   |
|Delete GroceryListItem from GroceryList|ListActivity|Test if deleted item is no longer in list|Create test list with arbitrary Items it it. Remove one and then check whether if item still exists in list.||The item should not be found in list|Test item was deleted from list.|Pass||
|Edit GroceryListItem (Quantity and/or Unit Type not entered)|ListActivity| Test that when a new quantity and/or unit type is entered for a GroceryListItem, the item is edited and updated |Choose item in test list, and set its quantity field to be equal to the desired quantity passed into the method. Then check whether Item instance's quantity field equals to the passed in quantity value.||Item's current quantity should be equal to passed in quantity value|Item reflects change in both quantity and unit.|Pass|   |
|Edit GroceryListItem (Quantity and/or Unit Type entered)|ListActivity|Test that when a new a GroceryListItem is edited and updated without a quantity and/or unit type, the item is displayed without a quantity or unit type| Press edit on existing item. Delete existing data and submit.||Item should have no unit & quantity.|Item no longer had a unit & quantity.|Pass| |
| Click Add Item by Item Type Button                           | ListActivity              | Test that when this button is clicked, the AddItemByItemTypeActivity opens | In an existing list, click Add Item By Type button.||New activity should open with all items separated by type.| New activity was opened.|Pass| |
| Click Add Item by Name Button                                | ListActivity              | Test that when this button is clicked, the AddItemByNameActivity opens | In an existing list, click Add Item By Name button.  | | AddItemByName activity should be opened. | New activity was opened.| Pass |   |
|Check/Uncheck item|ListActivity|See if items can be properly checked or unchecked through the GroceryListItem value.|First, instantiate a grocery list Item object and set its isChecked field to false. Use the checkorUncheck method to change that instance's check value to true. Then check if the item is checked (true). Then uncheck the item and see if the Item instance's isChecked field is unchecked (false)||Both comparisons of checking whether the current isChecked value should be true.| Item is checked/unchecked on press.|Pass |   |
|Uncheck all items|ListActivity|Test if all items can be unchecked in a list simultaneously by pressing the "Uncheck all items" button.|First, instantiate a new grocery list and add a few items to them. Next, set some, but not all of the isChecked fields to the list's items to true. Next, call the method to uncheck all items in the list. Then check the isChecked field of all items in the list.||All items in the list should be unchecked (i.e. their isChecked fields are all false)| All test items had their checkboxes turned false.|Pass|   |
|Select Item Type|AddItemByItemTypeActivity|Test that when an item type is selected, its items are shown|Tap "Add Item by Type" to show display of all Item types. Then tap each item type one at a time to gain access to each type's list of items. Tap "Return to Item Types" to get back to type list more quickly.|None|Each list of items will be displayed based on their type.|All items were shown for each type without issue|Pass||
|Add Item in AddItemByItemTypeActivity (Quantity and/or Unit Type not entered)|AddItemByItemTypeActivity|Test when an item is chosen after choosing an item type, and no quantity or unit type is entered, that the item is created in the list without a quantity or unit type|First, tap "Add Item by Type". Next, tap any food type, and choose any specific item that appears. Then, tap "Add item to List" and tap "Add item to List in window without entering any values for quantity or unit type.|None|Item will be added to list with default quantity of 0 and no text for unit type text field.|Item successfully added to list with proper quantity amount and no unit field.|Pass|Toast displayed to notify user of automatically input values|
|Add Item in AddItemByItemTypeActivity (Quantity and/or Unit Type entered)|AddItemByItemTypeActivity|Test when an item is chosen after choosing an item type, and a quantity and unit type are entered, that the item is created in the list with its quantity or unit type|On AddItemByItemActivity, select an item type. Upon picking a type, all of its item will appear. Click on the "Add Item to List" button that is to the right of the desired item. Type a quantity, a unit type and, click the "Add To List" button in the dialogue box|An item that has not yet been added to list, a quantity and a unit type|Redirected to list and item will now appear with the entered quantity and unit type|Redirected to ListActivity and new item with correct quantity and unit type is displayed|Pass| |
|Add Item in AddItemByItemTypeActivity (Item already exists in the List)|AddItemByItemTypeActivity|Test that if an item is being added that already exists in a list, that the item is not added again and that the original item remains in the list|On AddItemByItemTypeActivity, select an item type and item that combination that already exist in the list. Click the "Add Item to List" button. Enter a quantity number and unit type and click the "Add Item to List" button in the Add item to list dialogue box.|Item and item type combination that already exist in the list, quantity, and unit type|Redirect to the ListActivity and list remains unchanged|Redirected to to ListActivity and list remained unchanged, as the item's quantity and unit type was not altered|Pass||
|Return to ListActivity from AddItemByItemTypeActivity|AddItemByItemTypeActivity|Test that if the "Return to List" button is selected, that ListActivity opens|On AddItemByItemType Activity click on the "Return to List" button or select an item type then click the "Return to List" button|None|Will be redirected to ListActivity|Redirected to ListActivity|Pass|Content of ListActivity remained the same|
|Return to List of Item Types|AddItemByItemTypeActivity|Test that if the "Return to Item Types" button is pressed, that the item types reappear|On AddItemByItemByActivity, select any item type. Items of the selected item type from the database will appear, click on the "Return to Item Types" button|None|Will be redirected to AddItemByItemTypeActivity and item types reappear|Redirected to AddItemByItemTypeActivity and item type are once again listed for selection|Pass| |
|Click Search for Item Button|AddItemByItemTypeActivity|Test that if the "Search For Item" button is pressed, the user goes to AddItemByNameActivity|On AddItemByItemTypeActivity, user is presented with item types and buttons. Click the "Search For Item" button|None|Redirected to AddItemByNameActivity|Redirected to AddItemByNameActivity|Pass| |
|Click "Search" Button and search bar is empty|AddItemByNameActivity|Test that, if this button is pressed and the search bar is empty, all items will appear|Click "Search" button|None|All items in the database will appear|All items in the database are appeared |Pass |order in the time the items are added |
|Click "Search" Button and search bar is not empty with the item name/partial item name that is already in the database|AddItemByNameActivity|Test that, if this button is pressed and the search bar is filled with item name/partial item name in that's already in the database, will show the item name that contains the input value|First, input the item name/ partial item name in the search bar. Then, click "Search" button|Item name/partial item name|All items that contain the item name/partial item name will appear|All items that contain the item name/partial item name are appeared|Pass ||
|Click "Search" Button and search bar is not empty with the item name/partial item name that is not in the database|AddItemByNameActivity|Test that, if this button is pressed and the search bar is filled with item name/parital item name in that's not in the database, a dialog will appear ask the user if he wants to add the entered item name to the database|First, input the item name/partial item name in the search bar. Then,click "Search" button|Item name/partial item name|A dialog will appear ask the user if he wants to add the entered item name to the database|A dialog is appeared ask the user if he wants to add the entered item name to the database|Pass ||
|Click "Create New Item" Button (No item searched for)|AddItemByNameActivity| Test that, if the user presses this button and no item was searched for, that the user is taken straight to the AddItemToDatabaseActivity |click on "Add Item By Name" button on List Activity|None |Go to AddItemTo DatabaseActivity|Test 1- App crashed, bug was then fixed, Test 2- App went to AddItemToDatabaseActivity |Test 1-Fail, Test 2- Pass | |
|Click "Create New Item" Button (Item was searched for and matches some result(s) in database)|AddItemByNameActivity|Test that, if this button is pressed and an item was searched for but did not exactly match any of the search results, that the user will be prompted to choose if they would like to add that item|Click "Create New Item" button|None|A dialog appear ask the user if the user wants to add the entered item name to database| A dialog is appeared ask the user if the user wants to add the entered item name to database|Pass | |
|Click "Create New Item" Button (Item was searched for and it matches exactly with an item that is already in the database)|AddItemByNameActivity|Test that, if this button is pressed and an item was searched for and found with the exact spelling, the user will be taken straight to the AddItemToDatabase Activity |Click "Create New Item" button with an item searched for that matches the exact spelling of an already existing item|None|A toast appears and the AddItemToDatabaseActivity Starts|Takes user to AddItemToDatabaseActivity Page, shows the toast|Pass ||
|Click "YES" Button on the dialog where ask the user if the user wants to add the entered item name to data base|AddItemByNameActivity|Test that, if this button is pressed take the user to select item type page|Click "YES" button|None|Take the user to select item type page|User has been taken to the select item type page|Pass ||
|Click "NO" Button on the dialog where ask the user to add the entered item name to data base|AddItemByNameActivity|Test that, if this button is pressed the dialog is closed and nothing happens|Click "NO" button|None|Close the dialog and nothing happens|The dialog is closed and nothing happened|Pass ||
|Add Item in AddItemByNameActivity (Quantity and Unit Type not entered)|AddItemByNameActivity|Test when an item is chosen after searching for an item, and no quantity or unit type is entered, that the item is created in the list without a quantity or unit type|First, click "Add Item to List" at the right side of selected item. Then, Click "ADD ITEM TO LIST" button in the dialog|None|The item will appear in the list with empty quantity and unit|The item is appeared in the list with empty quantity and unit type |Pass | |
|Add Item in AddItemByNameActivity (Quantity or Unit Type not entered)|AddItemByNameActivity|Test when an item is chosen after searching for an item, and quantity or unit type is entered, that the item is created in the list with a quantity or without a quantity and unit type|First, click "Add Item to List" at the right side of selected item. Then, input quantity or unit type. Last, click "ADD ITEM TO LIST" button in the dialog|Quantity or unit type|The item will appear in the list with empty quantity and unit or only the quantity|The item is appeared in the list with empty quantity and unit or only the quantity |Pass |If the quantity is empty but unit type has a value, both quantity and unit will be empty. If the quantity has a value but unit type is empty, only quantity will appear and unit type will be empty |
|Click "CANCEL" Button on the dialog where ask the user to enter the quantity and unit type for the item|AddItemByNameActivity|Test to see if the "NO" button is working|First, click "Add Item to List" at the right side of selected item. Then, input quantity or unit type or none. Last, click "CANCEL" button in the dialog|Quantity or unit type or None|The dialog will close and nothing happens|The dialog is closed and nothing happened |Pass |If have focus on unit type input box, and click cancel. it will automatically have focus (keyboard appeared) on the search bar and the UI is change a little until hide the keyboard on the search bar|
|Add Item in AddItemByNameActivity (Quantity and Unit Type entered)|AddItemByNameActivity|Test when an item is chosen after searching for an item, and a quantity and unit type are entered, that the item is created in the list with its quantity or unit type|First, click "Add Item to List" at the right side of selected item. Then, input quantity or unit type. Last, click "ADD ITEM TO LIST" button in the dialog|Quantity and unit type|The item will appear in the list with its quantity and unit type| The item is appeared in the list with its quantity and unit type|Pass | |
|Add Item in AddItemByNameActivity (Item already exists in the List)|AddItemByNameActivity|Test that if an item is being added that already exists in a list, that the item is not added again and that the original item remains in the list| First, click "Add Item to List" at the right side of selected item (already in the list). Then, input quantity or unit type. Last, click "ADD ITEM TO LIST" button in the dialog|Quantity and unit type|No duplicate item will appear in the list| No duplicate item is appeared in the list|Pass |The item's quantity and unity type will not be updated. It stays as previous values |
|Return to ListActivity from AddItemByNameActivity|AddItemByNameActivity|Test that if the "Return to List" button is selected, that ListActivity opens|Click "Return to List" button|None|It will take user back to the ListActivity | It took user back to the ListActivity| Pass| |
|Select an Item Type for a specific item|AddItemToDatabaseActivity|Test that, if a specific item is to be added to the database, that when the user chooses an item type, the item is added to the database and the user is taken back to AddItemByNameActivity|On AddItemByNameActivity, search for a specific item that has not yet been added to database. User will be prompted if they would like to add this item, click "Yes". User will then be asked to select a type for the new item|An item that has not yet been added to database|User will be redirected to AddItemByNameActivity and a toast will appear|Upon selecting a type User was redirected to AddItemByNameActivity and a toast will notify the user that the item is searchable and has been added to database|Pass| To confirm the item has been added, user can now search for the item|
|Select an Item type, then add a new item by typing it's name|AddItemToDatabaseActivity|Test that, if no item name was chosen to be added, that the user can choose an item type, enter an item name, and that item is added to the database|On AddItemToDatabase Activity, user will be asked to select an item type, upon clicking a type a name for the item should be provided. Afterward click "Add item to Database" button|A item that has not yet been added to the database|User is redirected to AddItemByNameActivity, item is added to database and toast will appear|user was redirected to AddItemByNameActivity and a toast will notify the user that the item is searchable and has been added to database|Pass| |
|Try to add an item without typing the name|AddItemToDatabaseActivity|Test that, if an item type is selected and no name is typed in, that the database doesn't add a new item and the user is notified|On AddItemToDatabase Activity, user will be asked to select an item type, upon clicking a type user will be asked to give a name. No name shall be provided and click "Add item to Database" button"|None| Toast saying no item name was given and no item was created|Toast notifying user that no item name was provide and item was not created, remains on AddItemToDatabaseActivity|Pass||
|Try to add an item with an item type that already exists|AddItemToDatabaseActivity|Test that, if the user tries to add an item/item type combination that already exists, that this duplicate is not stored and the user is notified|On AddItemToDatabase Activity, user will be asked to select an item type, upon clicking a type user will be asked to give a name. Type in an item name that already exist with the selected type and click "Add item to Database" button"|An item type and item name combination that exist in the database|Redirected to AddItemByNameActivity and a toast appearing saying item already exist with given name|Redirected to AddItemByNameActivity and a toast notifying user that item already exist with given name|Pass||
|Return to ListActivity from AddItemToDatabaseActivity|AddItemToDatabaseActivity|Test that, when the "Return to List" button is clicked, the user returns to ListActivity|On AddItemToDatabaseActivity, user will be user will be asked to select an item type or return to list. Click "Return To List"|None|Will be redirected to to the ListActivity and no new item was added to database|User was redirected to ListActivity and no new item was added to the database |Pass||

### System Tests

Along with the Unit tests run by the QA Testers, a system test was run by the Project Manager to ensure that the System ran smoothly. The project manager will write a summary of the system test. In addition, the app was given to a family member of the project manager to ensure that the UI is intuitive and easy to use for users who weren't involved in development.

Along with these official system tests, the individual components of the system were covered by the QA testers during Unit/Integration Testing

The System Test involved the following actions:

1. Open the Application
2. Creating 2 lists
   1. Also attempt to create a list with no name
3. Deleting one of the lists
4. Renaming one of the lists
   1. Also attempt to rename with no name
5. Selecting a list
6. Adding 2 items by choosing the item type and 2 items by searching for the item name
   1. Doing this by specifying quantity and unit type
   2. Doing this without specifying quantity or unit type.
7. Check and uncheck all of the items individually, then check them and uncheck them all using the "Uncheck All Items" button
8. Check List/Grocery List Item persistence
9. Edit a Grocery List Item
   1. Change the quantity/unit type
   2. Remove the quantity/unit type
   3. Add a quantity/unit type to an item which previously had no quantity or unit type
10. Delete a Grocery List Item
11. Group the items by type
12. Search for a non-existing item and add it to the database
13. Search for an item that returns search results, but is not found exactly, and choose an item type for it.
14. Adding a new item to the database from scratch
15. Various edge cases
    1. Trying to add the same item to a list multiple times
    2. Trying to add the same item/item type combination multiple times
16. Open and close application to check for data persistence



##### System Test Summary

**This summary has the name of the test as an ordered list, followed by sublists describing the actions that led to completion of the test**

1. Open the Application
   1. App opens without any errors
2. Create 2 Grocery Lists
   1. Created a list with the name "First Grocery List", list shows up on the screen
   2. Created a list with the name "Second Grocery List", list shows up on the screen
   3. Tried to create a Grocery List with no name, List was not created
3. Delete a Grocery List
   1. Deleted "First Grocery List", list is no longer there
4. Rename a Grocery List
   1. Renamed "Second Grocery List" to "First Grocery List", name changed
   2. Tried to rename "First Grocery List" to a list with no name, List was not renamed
5. Select a List
   1. Selected "First Grocery List", new Activity started
6. Add 2 item by choosing an item type and then choosing an item, and add 2 items by searching for the item name
   1. Add Item By Type
      1. Pressed "Add Item By Type Button", list of item types shown.
      2. Chose "Bread/Pasta" Item Type and added "Spaghetti" with quantity of 2 boxes
      3. Chose "Meat" Item Type and added "Hamburgers" with no quantity or unit type
   2. Add Item By Name
      1. Searched for "Bacon" and added "Bacon" with quantity of 3 packs
      2. Searched for "Cheerios" and added "Honey Nut Cheerios" with no quantity or unit type
7. Check/Uncheck items individually
   1. Checked and unchecked each item, and the items were subsequently checked/unchecked
   2. Checked each item again, pressed the "Uncheck All Items" button, all items became unchecked
8. Check persistence
   1. Checked all the items, went back to home page of Grocery Lists, reselected "First Grocery Lists", everything persisted
9. Edit Grocery List Items
   1. Changed "Bacon" to quantity of 4 boxes
   2. Added a quantity of 2 boxes to "Honey Nut Cheerios"
   3. Removed the quantity/unit type for "Spaghetti"
   4. Added just a quantity of 2 to Hamburgers, without specifying the Unit type
   5. All items were sufficiently changed
10. Delete a Grocery List Item
    1. Deleted "Honey Nut Cheerios", item was no longer in the list
11. Group Items by Type
    1. Pressed the "Group Items By Type" button, all the items now showed their item type, and the items were grouped by their type in order in the list
    2. Pressing the button again gives us our original list format, as expected
12. Search for item that is not in the database, choose item type for that item and add it to the database, search again for that item. Then, search for another item that is not in the database, but don't accept the prompt to add the 
    1. Searched for "Sausage", was prompted to add "Sausage"
    2. Was then prompted to choose an Item Type for "Sausage", chose "Meat"
    3. Searched for Sausage, "Sausage" came up as a search term
    4. Searched for "Macaroni", was prompted to add it for future use, chose "NO", remained on the screen to search for items
13. Search for an item that is not found exactly in the database, and choose an item type for it by pressing the "Create New Item" button
    1. Searched for "Honeycom", got a search result of "Honeycomb". Since this isn't an exact match, when I pressed the "Create New Item" button, I was asked if I wanted to add "Honeycom" to the database, as expected.
    2. Searched for "Honeycomb". Since it was in the database exactly as spelled, when I pressed the "Create New Item Button", I was not asked it I wanted to add it to the database again.
14. Add a new item to the database from scratch
    1. From the list, pressed "Add Item By Name" button. Then, pressed the "Create New Item" Button
    2. Chose Item Type "Cereal", typed the name "Honeycomb"
    3. Searched and found Item "Honeycomb"
15. Various Edge cases
    1. Tried adding "Hamburgers" again to the list again, a duplicate item was not added and the original item remained unchanged.
    2. Tried adding Honeycomb again under "Cereal" Item Type, was not able to add again
    3. Tried typing "Honeycomb" again into the search bar of AddItemByName Activity, was not prompted to re-add the item as expected.
16. Close and reopen app to check for persistence
    1. Data was persistent upon reopening the app
17. Test all of the "Return Buttons"
    1. The back-arrow button for the ListActivity takes us back to the home screen with our Grocery Lists
    2. Every "Return to List" button returns us to our list
    3. The "Return to Item Types" button in AddItemByItemType Activity takes us back to the item types, allowing us to choose a new item type.
    4. The back buttons on the bottom of the screen take us to the previously shown activity.

**Conclusion:** The system test passed completely. Anything that was supposed to happen happened, and anything that was not supposed to happen failed to occur.

### Database Testing

At first, unit tests were attempted using activity classes. However, we were not able to do this because the functionality of the activity classes were hidden as private DatabaseManager fields in them. So, we decided to use an Instrument testing class that has a DatabaseManager object as a field. With this field in the test class, we were able to create GroceryList and Item objects as a part of this field. Testing the functionality of DatabaseManager's methods this way is very similar to testing the functionality of activity classes because the activity class' methods use the DatabaseManager object within them. Also, we only needed one Test class since the DatabaseManager class on its own helped in the functionality of a few of the activity classes. We checked whether unit tests passed by instantiating Item, ItemType, and GroceryList objects and compared their corresponding field values (e.g. ItemID, listID, ItemName) to their expected values.

Although at first we planned to test several aspects of the app with unit testing, we mostly only were able to test the ability to add and delete lists and items from lists. Other functionalities such as grouping items by item type and adding items to the database by name or type were tested during manual testing.

**Note:  It is recommended that the Database Instrumented Unit Tests be run on a separate emulator. The nature of the DatabaseManager class upon which the tests were done makes it that there might be some persistence issues with the Grocery Lists and Grocery List Items if run on the same emulator as the app itself.**