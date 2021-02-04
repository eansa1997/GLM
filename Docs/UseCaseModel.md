# Use Case Model

**Author**: Shmuel Halbfinger, Luis Toro

## 1 Use Case Diagram

*This section should contain a use case diagram with all the actors and use cases for the system, suitably connected.*

Use cases are color coded by the use cases they extend. This way, we are able to streamline the process of deciding which use cases belong in which activities of our code.

![UseCaseDiagram](Images\UseCaseDiagram.png)

## 2 Use Case Descriptions

For each use case in the use case diagram, we included the following elements:

- Requirements: High-level description of what the use case must allow the user to do.
- Pre-conditions: conditions that must be true before the use case is run.
- Post-conditions conditions that must be true once the use case is run.
- Scenarios: Sequence of events that characterize the use case. This part may include multiple Scenarios, for normal, alternate, and exceptional event sequences. These Scenarios may be expressed as a list of steps in natural language or as sequence diagrams.

**Note:** Some use cases shown in the diagram above are combined if the use case is similar to another use case.

- #### Use Case 1

  - Requirement: User can create a new list.
  - Pre-conditions: The application has been downloaded and runs without any errors.
  - Post-conditions: A new list has been created and is listed along with the other Grocery Lists.
  - Scenarios: A user starts the app, and they see a page listing all grocery lists. They will select the "Create List" action and type in the name of the grocery list. After a name is typed, a new Grocery list will be added to the database and will appear on the page of grocery lists. If no name is typed, an error message will direct the user to fill in a name for the list.

- #### Use Case 2

  - Requirement: User can select a list.
  - Pre-conditions: The application runs without any errors and there is at least one created list.
  - Post-conditions: A page showing the name of the selected list and all items in that list (should there be items in the list, it can be empty) will be shown.
  - Scenarios: There will be a page with a list of grocery lists. A user selects one of these lists, triggering the activity that will load all items that are present in the selected list. If there are no items in the selected list, there will be a page with the list name only.

- #### Use Case 3

  - Requirement: User can add an item to a list.

  - Pre-conditions: The application runs without any errors, there is at least one created list, and one list is selected.

  - Post-conditions: The selected list will now display the item chosen by the user.

  - Scenarios: A user will select the "Add item" activity. This will trigger a new screen with a list of item types from which an item will be chosen from. The user, depending on their preference for choosing an item, can access Use case 3a or 3b.

    ##### Use Case 3a

    - Requirement: User can add an item from a hierarchical list.
    - Pre-conditions: The application runs without errors, there is at least one created list, one list is selected, and the "Add item" activity is triggered by the user.
    - Post-conditions: A new item is added to the list.
    - Scenarios: After choosing the "Add item" activity, the user will be directed to a screen with a list if item types. The user can then choose an item type, which will trigger a list of items under that item type to appear. The user can choose an item. The item will then be added to the list and the list will reappear.

    ##### Use Case 3b

    - Requirement: User can search for an item and add it to a the list.
    - Pre-conditions: The application runs without errors, there is at least one created list, one list is selected, and the "Add item" activity is triggered by the user.
    - Post-conditions: A new item will be added to either the list or the database.
    - Scenarios
      - On the screen brought up in Use case 3a, there will be a search button triggering a search activity. The user can type the name of an item. The database will then return all items of that name, the user can then select an item to be added to the list.
      - If no item matches the search parameter, an option to add a new item to the database will appear. If the user chooses to add this item, they will be directed to Use case 4 through the "Add new item" activity.

- #### Use Case 4

  - Requirement: User can add a new item to the database.
  - Pre-conditions:
    - The application runs without errors, there is at least one created list, one list is selected, the "Add item" activity is triggered by the user, the search option is utilized, and no item matches the search parameter.
    - Developers may choose to add this Use case to the activity that shows the list, but this isn't a requirement as specified.
  - Post-conditions: A new item will be added to the database with a specified item type.
  - Scenarios:
    - When the user chooses the "Add new item" activity from Use case 3b, they will be redirected to a list of item types. They will choose an item type and be redirected to a screen to choose the name of the item. Upon entry of the name, the item will be added to the database, and the user will be redirected to the selected list screen.
    - If no name is typed, an error message will be displayed prompting the user to enter a valid item name.

- #### Use Case 5

  - Requirement: User can edit an item in a list
    - This requirement includes functionality to delete an item, edit its quantity, or change the unit type.
  - Pre-conditions: The application runs without errors, there is at least one created list, one list is selected, and there is at least one item in the list.
  - Post-conditions: The selected item will be edited in one of the following ways: item deletion from list, item quantity or unit type updated.
  - Scenarios
    - A user can select an item, and a screen will appear prompting the user to choose an edit activity.
    - If the user selects to delete the item, the item will be deleted from the list and no longer appear there.
    - If the user selects to edit the item quantity, the user will be prompted to type a new quantity amount. 
    - If the user selects to edit the unit type, the user will be prompted to type a new unit type.
    - The list will then reflect these changes and they will be saved in the database.

- #### Use Case 6

  - Requirement: User can check or uncheck items in a list.
  - Pre-conditions: The application runs without errors, there is at least one created list, one list is selected, and there is at least one item in the list.
  - Post-conditions: An item in the list will either be newly checked or unchecked, depending on the user's actions.
  - Scenarios: Each item will have a check mark box next to it. It will either be checked or unchecked. Upon pressing this check mark box, the checkmark will display the opposite of the state it was before it was checked and this state will be saved in the database for persistence.

- #### Use Case 7

  - Requirement: User can uncheck all items in a list.
  - Pre-conditions: The application runs without errors, there is at least one created list, and one list is selected.
  - Post-conditions: All items in the list will be unchecked.
  - Scenarios: A button denoting the "Uncheck all items" activity can be pressed by the user, and all items that are checked will become unchecked, while all items that are unchecked will remain unchecked.
    - If there are no items in the list, or all items are unchecked, the activity will simply change nothing (a notification to the user may be triggered).

- #### Use Case 8

  - Requirement: User can group and display all items by item type.
  - Pre-conditions:  The application runs without errors, there is at least one created list, and one list is selected.
  - Post-conditions: The list will be displayed with the items grouped by item type.
  - Scenarios:
    - When the user selects a list, the selected list will be retrieved with the items grouped by item type.
    - If the the list is empty no content other than list name will be displayed. 

- #### Use Case 9

  - Requirement: User can rename a list.
  - Pre-conditions: The application runs without errors and there is at least one created list.
  - Post-conditions: The selected list will be renamed, but will keep all other information.
  - Scenarios: From the screen to select a list, the user can choose the "Rename list" activity. The user can then click on a list and will be prompted to change the name. The new name will be stored in the database and will persist. All items and states in the list will remain unchanged.

- #### Use Case 10

  - Requirement: User can delete a list.
  - Pre-conditions: The application runs without errors and there is at least one created list.
  - Post-conditions: The selected list will be deleted, along with all information inside the list.
  - Scenarios: From the screen to select a list, the user can choose the "Delete list" activity. The user then will select a list, and it will be automatically deleted from the database. All items associated with it will also be deleted from the database (by items, we mean items as they relate to the list denoted by the *GroceryListItem* class, not the actual items themselves).