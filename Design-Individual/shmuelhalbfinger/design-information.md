

<h1>
    <i>GroceryListManager</i> System Design
</h1>

<center><h4>Shmuel Halbfinger</h4></center>

<ul>
    <li>Requirement 1
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>A grocery list consists of items the users want to buy at a grocery store. The application
must allow users to add items to a list, delete items from a list, and change the quantity
                        of items in the list (e.g., change from one to two pounds of apples).</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>There will be a <i>GroceryList class</i> to represent the lists for each user. This class will have methods to add items, delete items, and change the quantity of an item
                        <ul>
                            <li>Class: <i>GroceryList</i></li>
                            <li>Attributes:
                                <ol>
                                    <li>listName:String</li>
                                    <li>listId:Integer</li>
                                </ol>
                            </li>
                            <li>Operations:
                                <ol>
                                    <li>addItemToList()</li>
                                    <li>deleteItemFromList()</li>
                                    <li>changeItemQuantity()</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                    <li>There will be an <i>Item</i> class, representing the items that can be added to a GroceryList
                        <ul>
                            <li>Class: <i>Item</i></li>
                            <li>Attributes:
                                <ol>
                                    <li>itemName:String</li>
                                    <li>itemId:Integer</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                    <li>There will be an association class called <i>GroceryListItem</i> that will represent the ability of an item within a GroceryList to have a quantity while not directly having an Item itself require a quantity
                        <ul>
                            <li>Class: <i>GroceryListItem</i></li>
                            <li>Attributes:
                                <ol>
                                    <li>quantity:Integer</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                    <li>There will be a <i>GroceryShopper</i> class to represent the users who build the lists
                        <ul>
                            <li>Class: <i>GroceryShopper</i></li>
                            <li>Attributes:
                                <ol>
                                    <li>shopperName:String</li>
                                    <li>shopperEmail:String</li>
                                    <li>shopperPassword:String</li>
                                    <li>shopperId:Integer</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                    <li>Some of the relationships from this requirement include:
                        <ul>
                            <li>A GroceryList has many Items, and each Item can be in many GroceryLists.</li>
                            <li><i>GroceryListItem</i> represents part of the association between the total list of Items and the GroceryList</li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </li>
    <li>Requirement 2
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>The application must contain a database (DB) of items and corresponding item types.</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>There will be an <i>ItemType</i> class which represents the types of the items that can be added to the list
                        <ul>
                            <li>Class: <i>ItemType</i></li>
                            <li>Attributes:
                                <ol>
                                    <li>ItemTypeName:String</li>
                                    <li>ItemTypeId:Integer</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                    <li>Some of the relationships from this requirement include:
                        <ul>
                            <li>An ItemType has multiple Items, and each Item has an ItemType. This is an aggregation relationship, as the <i>ItemType</i> class contains many items, but the items will still exist without the ItemType that they fall under.</li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </li>
    <li>Requirement 3
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>Users must be able to add items to a list by picking them from a hierarchical list, where
the first level is the item type (e.g., cereal), and the second level is the name of the
actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
quantity for that item.</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>This requirement will most likely be recognized in the UI, as the output of the elements is an implementation matter. However, we will include a method in the GroceryList class, <i>addItemByItemType()</i>, which can be called by the <i>addItemToList()</i> method to display the Items a certain way. This method will be private
                        <ul>
                            <li>Class: GroceryList</li>
                            <li>Operations
                                <ol>
                                    <li>addItemsByItemType()</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </li>
    <li>Requirement 4
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>Users must also be able to specify an item by typing its name. In this case, the
application must look in its DB for items with similar names and ask the users, for each
of them, whether that is the item they intended to add. If a match cannot be found, the
application must ask the user to select a type for the item and then save the new item,
together with its type, in its DB.
</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>Like requirement 3, this requirement will most likely be recognized in the UI, as the output of the elements is an implementation matter. However, we will include a method in the GroceryList class, <i>addItemByItemName()</i>, which can be called by the <i>addItemToList()</i> method to display the Items a certain way. This method will be private
                        <ul>
                            <li>Class: GroceryList</li>
                            <li>Operations
                                <ol>
                                    <li>addItemsByItemName()</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                    <li>We will also add a method to the <i>GroceryShopper</i> class called <i>addNewItem()</i> which will allow the user to add a new Item under a specific ItemType to the items database
                        <ul>
                            <li>Class: GroceryShopper</li>
                            <li>Operations:
                                <ol>
                                    <li>addNewItem()</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </li>
    <li>Requirement 5
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>Lists must be saved automatically and immediately after they are modified.</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>This is accomplished by implementing the <i>GroceryList</i> class and implementing CRUD operations in the <i>GroceryShopper</i> class, which we will do in Requirement 10</li>
                </ul>
            </li>
        </ul>
    </li>
    <li>Requirement 6
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>Users must be able to check off items in a list (without deleting them)</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>We will have an attribute in the <i>GroceryListItem</i> class called <i>checked</i> which will represent whether or not a GroceryListItem is checked off or not without affecting the status of the Item itself as an entity. This attribute will be set as <u>false</u> at first
                        <ul>
                            <li>Class: GroceryListItem</li>
                            <li>Attributes
                                <ol>
                                    <li>checked:Boolean=true</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                    <li>We will have methods in the <i>GroceryList</i> class to check and uncheck the items, reversing the value of the <i>checked</i> attribute
                        <ul>
                            <li>Class: GroceryList</li>
                            <li>Operations
                                <ol>
                                    <li>checkItem()</li>
                                    <li>uncheckItem()</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </li>
    <li>Requirement 7
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>Users must also be able to clear all the check-off marks in a list at once.</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>We will have a method in the <i>GroceryList</i> class to uncheck all items
                        <ul>
                            <li>Class: GroceryList</li>
                            <li>Operations
                                <ol>
                                    <li>uncheckAllItems()</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </li>
    <li>Requirement 8
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>Check-off marks for a list are persistent and must also be saved immediately.</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>This is accomplished by implementing the <i>GroceryListItem</i> class and implementing CRUD operations in the <i>GroceryList</i> class that relate to the <i>checked</i>, which were outlined in Requirements 6 and 7</li>
                </ul>
            </li>
        </ul>
    </li>
    <li>Requirement 9
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of products at once (i.e., without having to go back and forth between aisles).</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>In the <i>ItemType</i> class, we will include operations to add, remove, and update Items by ItemType for further possible functionality to be implemented.
                        <ul>
                            <li>Class: <i>ItemType</i></li>
                            <li>Operations:
                                <ol>
                                    <li>addItem()</li>
                                    <li>deleteItem()</li>
                                    <li>updateItem()</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                    <li>A relationship recognized in this requirement is that each ItemType has many Items, and each Item has one ItemType</li>
                </ul>
            </li>
        </ul>
    </li>
    <li>Requirement 10
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>In the <i>GroceryShopper</i> class, we will have operations to create, rename, delete and select lists.
                        <ul>
                            <li>Class: <i>GroceryShopper</i></li>
                            <li>Operations:
                                <ol>
                                    <li>addList()</li>
                                    <li>renameList()</li>
                                    <li>deleteList()</li>
                                    <li>selectList()</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                    <li>We will add an attribute to the GroceryList class that represents whether the list has been selected and is therefore active. The <i>selectList()</i> operation will select a non-active list and unselect an active-list. On list creation, this attribute will be set to false
                        <ul>
                            <li>Class: <i>GroceryList</i></li>
                            <li>Attributes
                                <ol>
                                    <li>selected:Boolean=false</li>
                                </ol>
                            </li>
                        </ul>
                    </li>
                    <li>A relationship realized from this requirement is that a GroceryShopper has many GroceryLists, and a GroceryList belongs to one GroceryShopper. This relationship is a composition relationship, as without the <i>GroceryShopper</i> class, the <i>GroceryList</i> objects wouldn't exist.</li>
                </ul>
            </li>
        </ul>
    </li>
    <li>Requirement 11
        <ul>
            <li>Requirement
                <ul>
                    <li><strong>The User Interface (UI) must be intuitive and responsive.</strong></li>
                </ul>
            </li>
            <li>Realization
                <ul>
                    <li>This requirement is related to the system design and is therefore not directly considered</li>
                </ul>
            </li>
        </ul>
    </li>
</ul>

