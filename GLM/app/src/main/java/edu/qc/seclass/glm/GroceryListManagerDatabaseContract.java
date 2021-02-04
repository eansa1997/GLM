package edu.qc.seclass.glm;

import android.provider.BaseColumns;

//Contract describing the different tables in our database. It can be called in the DatabaseHelper and DatabaseManager classes to get names of tables and columns
// This class ensures that all database methods will use the correct names in the embedded SQLite queries.
// It also contains a list of ItemTypes for initial populating of the database
/*
author: Shmuel Halbfinger
 */
public final class GroceryListManagerDatabaseContract {

    //Statuc classes that define the tables and columns for the database
    public static class GroceryList implements BaseColumns {
        public static final String GROCERY_LIST_TABLE = "GroceryList";
        public static final String GROCERY_LIST_ID = "GroceryListId";
        public static final String GROCERY_LIST_NAME = "GroceryListName";
    }

    public static class ItemType implements BaseColumns {
        public static final String ITEM_TYPE_TABLE = "ItemType";
        public static final String ITEM_TYPE_ID = "ItemTypeId";
        public static final String ITEM_TYPE_NAME = "ItemTypeName";
    }

    public static class Item implements BaseColumns {
        public static final String ITEM_TABLE = "Item";
        public static final String ITEM_ID = "ItemId";
        public static final String ITEM_TYPE_ID = "ItemTypeId";
        public static final String ITEM_NAME = "ItemName";
    }

    public static class GroceryListItem implements BaseColumns {
        public static final String GROCERY_LIST_ITEM_TABLE = "GroceryListItem";
        public static final String GROCERY_LIST_ID = "GroceryListId";
        public static final String ITEM_ID = "ItemId";
        public static final String QUANTITY = "Quantity";
        public static final String QUANTITY_UNIT = "QuantityUnit";
        public static final String IS_CHECKED = "IsChecked";
    }

    //Below are the queries for creating tables
    public static final String CREATE_TABLE_GROCERY_LIST = "CREATE TABLE " + GroceryList.GROCERY_LIST_TABLE + " (" + GroceryList.GROCERY_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GroceryList.GROCERY_LIST_NAME + " TEXT NOT NULL);";

    public static final String CREATE_TABLE_ITEM_TYPE = "CREATE TABLE " + ItemType.ITEM_TYPE_TABLE + " (" + ItemType.ITEM_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ItemType.ITEM_TYPE_NAME +
            " TEXT NOT NULL);";

    public static final String CREATE_TABLE_ITEM = "CREATE TABLE " + Item.ITEM_TABLE + " (" + Item.ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Item.ITEM_TYPE_ID +
            " INTEGER NOT NULL, " + Item.ITEM_NAME + " TEXT NOT NULL);";

    public static final String CREATE_TABLE_GROCERY_LIST_ITEM = "CREATE TABLE " + GroceryListItem.GROCERY_LIST_ITEM_TABLE +
            " (" + GroceryListItem.GROCERY_LIST_ID + " INTEGER NOT NULL, " + GroceryListItem.ITEM_ID + " INTEGER NOT NULL, " + GroceryListItem.QUANTITY +
            " INTEGER, " + GroceryListItem.QUANTITY_UNIT + " TEXT, " + GroceryListItem.IS_CHECKED + " INTEGER DEFAULT 0, PRIMARY KEY(" + GroceryListItem.GROCERY_LIST_ID
            + ", " + GroceryListItem.ITEM_ID + "));";

    //List of initial Item Types for the database
    public static final String[] ITEM_TYPES = new String[]
            {"Meat",
                    "Dairy",
                    "Fish/Seafood",
                    "Fruits",
                    "Vegetables",
                    "Bread/Pasta",
                    "Eggs",
                    "Grains/Nuts/Beans",
                    "Cereal/Breakfast",
                    "Snacks/Chocolate/Candy",
                    "Drinks/Alcoholic Beverages",
                    "Pastries",
                    "Spices/Condiments/Sauces",
                    "Other"};
}