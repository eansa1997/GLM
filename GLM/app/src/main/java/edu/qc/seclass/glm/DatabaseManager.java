package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

// This class manages the functionality of the database throughout the application
// There are methods to open and close the database, as well as custom methods based on SQLite queries that can be used in the activities
/*
author: Shmuel Halbfinger
 */
public class DatabaseManager {

    private DatabaseHelper databaseHelper;

    private Context context;

    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    //Open the Database for use in the application
    public DatabaseManager open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    // close the database
    public void close() {
        databaseHelper.close();
    }

    //Add a new Grocery List with a given name
    public void insertGroceryList(String groceryListName) {
        ContentValues groceryList = new ContentValues();
        groceryList.put(GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_NAME, groceryListName);
        database.insert(GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_TABLE, null, groceryList);
    }

    // Update the Name of a grocery list by the ID
    public int updateGroceryListNameById(long groceryListId, String groceryListName) {
        ContentValues groceryList = new ContentValues();
        groceryList.put(GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_NAME, groceryListName);
        return database.update(GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_TABLE, groceryList, GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_ID + " = " + groceryListId, null);
    }

    // Delete a GroceryList with a certain ID
    public void deleteGroceryListById(long groceryListId) {
        database.delete(GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_TABLE,
                GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_ID + " = " + groceryListId, null);
    }

    //Get a list of all Grocery Lists
    public ArrayList getAllGroceryList() {
        String[] columns = new String[]{GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_ID, GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_NAME};
        Cursor cursor = database.query(GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_TABLE, columns, null, null, null, null, GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_ID);
        ArrayList<GroceryList> groceryLists = new ArrayList<>();
        while (cursor.moveToNext()) {
            GroceryList groceryList = new GroceryList();
            groceryList.setGroceryListId(Long.parseLong(cursor.getString(0)));
            groceryList.setGroceryListName(cursor.getString(1));
            groceryLists.add(groceryList);
        }
        return groceryLists;
    }

    // Get a Grocery List given the ID
    public GroceryList getGroceryListById(long groceryListId) {
        String[] columns = new String[]{GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_ID, GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_NAME};
        Cursor cursor = database.query(GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_TABLE, columns, GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_ID + "=" + groceryListId, null, null, null, null);
        GroceryList groceryList = new GroceryList();
        cursor.moveToFirst();
        groceryList.setGroceryListId(Long.parseLong(cursor.getString(0)));
        groceryList.setGroceryListName(cursor.getString(1));

        return groceryList;
    }

    // Get an Item Type by its ID
    public ItemType getItemTypeByItemTypeId(long itemTypeId) {
        String[] columns = new String[]{GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_ID, GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_NAME};
        Cursor cursor = database.query(GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_TABLE, columns, GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_ID + " = " + itemTypeId, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        ItemType itemType = new ItemType();
        itemType.setItemTypeId(Long.parseLong(cursor.getString(0)));
        itemType.setItemTypeName(cursor.getString(1));

        return itemType;
    }

    // Get an Item by its ID
    public Item getItemByItemId(long itemId) {
        String[] columns = new String[]{GroceryListManagerDatabaseContract.Item.ITEM_ID, GroceryListManagerDatabaseContract.Item.ITEM_TYPE_ID, GroceryListManagerDatabaseContract.Item.ITEM_NAME};
        Cursor cursor = database.query(GroceryListManagerDatabaseContract.Item.ITEM_TABLE, columns, GroceryListManagerDatabaseContract.Item.ITEM_ID + " = " + itemId, null, null, null, null);
        Item item = new Item();
        cursor.moveToFirst();
        item.setItemId(Long.parseLong(cursor.getString(0)));
        item.setItemName(cursor.getString(2));
        item.setItemType(getItemTypeByItemTypeId(Long.parseLong(cursor.getString(1))));

        return item;
    }

    //Get all the Grocery List Items by Grocery List ID. This will also get the Items and Item Types that correspond to the Grocery List Items
    public ArrayList getGroceryListItemByGroceryListId(int groceryListId) {
        String[] columns = new String[]{GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ID,
                GroceryListManagerDatabaseContract.GroceryListItem.ITEM_ID, GroceryListManagerDatabaseContract.GroceryListItem.QUANTITY,
                GroceryListManagerDatabaseContract.GroceryListItem.QUANTITY_UNIT, GroceryListManagerDatabaseContract.GroceryListItem.IS_CHECKED};
        Cursor cursor = database.query(GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ITEM_TABLE, columns, GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListId, null, null, null, null);
        ArrayList<GroceryListItem> groceryListItems = new ArrayList<>();
        while (cursor != null && cursor.moveToNext()) {
            GroceryListItem groceryListItem = new GroceryListItem();
            groceryListItem.setGroceryListId(Long.parseLong(cursor.getString(0)));
            groceryListItem.setItem(getItemByItemId(Long.parseLong(cursor.getString(1))));
            groceryListItem.setQuantity(Integer.parseInt(cursor.getString(2)));
            groceryListItem.setQuantityUnit(cursor.getString(3));
            groceryListItem.setChecked(Integer.parseInt(cursor.getString(4)) == 1 ? true : false);
            groceryListItems.add(groceryListItem);
        }
        return groceryListItems;
    }

    //Get the Grocery List Items for a certain Grocery List grouped and ordered by Item Type. Uses a custom raw Query.
    public ArrayList getGroceryListItemsGroupedByItemType(long groceryListId) {
        Cursor cursor = database.rawQuery("SELECT GroceryListItem.GroceryListId, GroceryListItem.ItemId, GroceryListItem.Quantity, GroceryListItem.QuantityUnit, GroceryListItem.IsChecked FROM GroceryListItem INNER JOIN Item ON GroceryListItem.ItemId = Item.ItemId WHERE groceryListId = " +
                groceryListId + " ORDER BY Item.ItemTypeId", null);
        ArrayList<GroceryListItem> groceryListItems = new ArrayList<>();
        while (cursor.moveToNext()) {
            GroceryListItem groceryListItem = new GroceryListItem();
            groceryListItem.setGroceryListId(Long.parseLong(cursor.getString(0)));
            groceryListItem.setItem(getItemByItemId(Long.parseLong(cursor.getString(1))));
            groceryListItem.setQuantity(Integer.parseInt(cursor.getString(2)));
            groceryListItem.setQuantityUnit(cursor.getString(3));
            groceryListItem.setChecked(Integer.parseInt(cursor.getString(4)) == 1 ? true : false);
            groceryListItems.add(groceryListItem);
        }
        return groceryListItems;
    }

    //Update the Quantity of a Grocery List Item
    public int updateGroceryListItem(GroceryListItem groceryListItem, int newQuantity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GroceryListManagerDatabaseContract.GroceryListItem.QUANTITY, newQuantity);
        return database.update(GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ITEM_TABLE,
                contentValues, GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListItem.getGroceryListId() +
                        " AND " + GroceryListManagerDatabaseContract.GroceryListItem.ITEM_ID + " = " + groceryListItem.getItem().getItemId(), null);
    }

    //Update the Unit Type of a Grocery List Item
    public int updateGroceryListItem(GroceryListItem groceryListItem, String newQuantityUnit) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GroceryListManagerDatabaseContract.GroceryListItem.QUANTITY_UNIT, newQuantityUnit);
        return database.update(GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ITEM_TABLE,
                contentValues, GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListItem.getGroceryListId() +
                        " AND " + GroceryListManagerDatabaseContract.GroceryListItem.ITEM_ID + " = " + groceryListItem.getItem().getItemId(), null);
    }

    //Update the checked status of a Grocery List Item
    public int updateGroceryListItem(GroceryListItem groceryListItem, boolean switchCheck) {
        ContentValues contentValues = new ContentValues();
        int switchCheckInt = (switchCheck ? 1 : 0);
        contentValues.put(GroceryListManagerDatabaseContract.GroceryListItem.IS_CHECKED, switchCheckInt);
        return database.update(GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ITEM_TABLE,
                contentValues, GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListItem.getGroceryListId() +
                        " AND " + GroceryListManagerDatabaseContract.GroceryListItem.ITEM_ID + " = " + groceryListItem.getItem().getItemId(), null);
    }

    //Delete a Grocery List Item
    public void deleteGroceryListItem(GroceryListItem groceryListItem) {
        database.delete(GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ITEM_TABLE,
                GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListItem.getGroceryListId() +
                        " AND " + GroceryListManagerDatabaseContract.GroceryListItem.ITEM_ID + " = " + groceryListItem.getItem().getItemId(), null);
    }

    //Uncheck all Grocery List Items
    public int uncheckAllGroceryListItems(long groceryListId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GroceryListManagerDatabaseContract.GroceryListItem.IS_CHECKED, false);
        return database.update(GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ITEM_TABLE, contentValues,
                GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListId, null);
    }

    //Get a list of all Item Types
    public ArrayList getAllItemTypes() {
        String[] columns = new String[]{GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_ID, GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_NAME};
        Cursor cursor = database.query(GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_TABLE, columns, null, null, null, null, null);
        ArrayList<ItemType> itemTypes = new ArrayList<>();

        while (cursor.moveToNext()) {
            ItemType itemType = new ItemType();
            itemType.setItemTypeId(Long.parseLong(cursor.getString(0)));
            itemType.setItemTypeName(cursor.getString(1));
            itemTypes.add(itemType);
        }

        return itemTypes;
    }

    /*public long getItemTypeIdByItemTypeName(String itemTypeName) {
        String[] columns = new String[]{GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_ID};
        Cursor cursor = database.query(GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_TABLE, columns, GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_NAME + "=" + itemTypeName, null, null, null, null);

        cursor.moveToFirst();
        return Long.parseLong(cursor.getString(0));
    }*/

    //Get all of the Items for a given Item Type ID
    public ArrayList getAllItemsByItemTypeId(long itemTypeId) {
        ItemType itemType = getItemTypeByItemTypeId(itemTypeId);
        String[] columns = new String[]{GroceryListManagerDatabaseContract.Item.ITEM_ID, GroceryListManagerDatabaseContract.Item.ITEM_NAME};
        Cursor cursor = database.query(GroceryListManagerDatabaseContract.Item.ITEM_TABLE, columns,
                GroceryListManagerDatabaseContract.Item.ITEM_TYPE_ID + " = " + itemTypeId, null, null, null, null);

        ArrayList<Item> items = new ArrayList<>();

        while (cursor.moveToNext()) {
            Item item = new Item();
            item.setItemType(itemType);
            item.setItemId(Long.parseLong(cursor.getString(0)));
            item.setItemName(cursor.getString(1));
            items.add(item);
        }

        return items;
    }

    //Get all items that are similar to a given search query name
    public ArrayList<Item> getAllItemsByName(String name) {
        name = "'%" + name + "%'";
        String[] columns = new String[]{GroceryListManagerDatabaseContract.Item.ITEM_ID, GroceryListManagerDatabaseContract.Item.ITEM_NAME, GroceryListManagerDatabaseContract.Item.ITEM_TYPE_ID};
        Cursor cursor = database.query(GroceryListManagerDatabaseContract.Item.ITEM_TABLE, columns,
                GroceryListManagerDatabaseContract.Item.ITEM_NAME + " LIKE " + name, null, null, null, null);

        ArrayList<Item> items = new ArrayList<>();

        while (cursor.moveToNext()) {
            Item item = new Item();
            ItemType type = getItemTypeByItemTypeId(Long.parseLong(cursor.getString(2)));
            item.setItemType(type);
            item.setItemId(Long.parseLong(cursor.getString(0)));
            item.setItemName(cursor.getString(1));
            items.add(item);
        }

        return items;
    }

    //Insert a Grocery List Item into a Grocery list
    public void insertGroceryListItem(long groceryListId, Item item, int quantity, String quantityType) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ID, groceryListId);
        contentValues.put(GroceryListManagerDatabaseContract.GroceryListItem.ITEM_ID, item.getItemId());
        contentValues.put(GroceryListManagerDatabaseContract.GroceryListItem.QUANTITY, quantity);
        contentValues.put(GroceryListManagerDatabaseContract.GroceryListItem.QUANTITY_UNIT, quantityType);
        contentValues.put(GroceryListManagerDatabaseContract.GroceryListItem.IS_CHECKED, 0);

        database.insert(GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ITEM_TABLE, null, contentValues);
    }

    //Insert a new Item
    public void insertItem(ItemType itemType, String itemName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GroceryListManagerDatabaseContract.Item.ITEM_TYPE_ID, itemType.getItemTypeId());
        contentValues.put(GroceryListManagerDatabaseContract.Item.ITEM_NAME, itemName);

        database.insert(GroceryListManagerDatabaseContract.Item.ITEM_TABLE, null, contentValues);
    }

    /*public void insertItemType(String itemTypeName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_NAME, itemTypeName);
        database.insert(GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_TABLE, null, contentValues);
    }*/

    public void deleteAllGroceryLists() {
        database.execSQL("DELETE FROM " + GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ITEM_TABLE);
        database.execSQL("DELETE FROM " + GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_TABLE);
    }

    //Delete all Grocery List Items for a given Grocery List
    public void deleteAllGroceryListItemFromGroceryList(long groceryListId) {
        database.delete(GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ITEM_TABLE, GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ID + "=" + groceryListId, null);
    }
}
