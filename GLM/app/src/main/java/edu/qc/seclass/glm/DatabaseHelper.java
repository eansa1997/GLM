package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Class to help with creation of our database. It contains the initial items we wish to add to the database.
// It also contains methods for creating the database upon installation and upgrading the database
/*
author: Shmuel Halbfinger
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GroceryListManager.DB";

    // List of Items to be initially loaded into the database upon installation.
    private final Item[] INITIAL_ITEMS = new Item[]{
            new Item(1, "Hot Dogs", 1, ""),
            new Item(1, "Hamburgers", 1, ""),
            new Item(1, "Bacon", 1, ""),
            new Item(1, "Chicken", 1, ""),
            new Item(1, "Turkey", 1, ""),
            new Item(1, "Duck", 1, ""),

            new Item(1, "Milk", 2, ""),
            new Item(1, "Cheese", 2, ""),
            new Item(1, "Yogurt", 2, ""),
            new Item(1, "Cream Cheese", 2, ""),
            new Item(1, "Butter", 2, ""),

            new Item(1, "Salmon", 3, ""),
            new Item(1, "Shrimp", 3, ""),
            new Item(1, "Tuna", 3, ""),
            new Item(1, "Halibut", 3, ""),

            new Item(1, "Apples", 4, ""),
            new Item(1, "Oranges", 4, ""),
            new Item(1, "Strawberries", 4, ""),
            new Item(1, "Grapes", 4, ""),
            new Item(1, "Watermelon", 4, ""),
            new Item(1, "Pineapple", 4, ""),
            new Item(1, "Kiwi", 4, ""),
            new Item(1, "Bananas", 4, ""),
            new Item(1, "Pears", 4, ""),
            new Item(1, "Mangos", 4, ""),

            new Item(1, "Carrots", 5, ""),
            new Item(1, "Spinach", 5, ""),
            new Item(1, "Kale", 5, ""),
            new Item(1, "Spinach", 5, ""),
            new Item(1, "Cucumbers", 5, ""),
            new Item(1, "Tomatoes", 5, ""),
            new Item(1, "Carrots", 5, ""),

            new Item(1, "Bread", 6, ""),
            new Item(1, "Bagels", 6, ""),
            new Item(1, "Spaghetti", 6, ""),
            new Item(1, "Rice", 6, ""),
            new Item(1, "Waffles", 6, ""),
            new Item(1, "Pancakes", 6, ""),

            new Item(1, "Eggs", 7, ""),

            new Item(1, "Peanuts", 8, ""),
            new Item(1, "Black Beans", 8, ""),
            new Item(1, "Chick Peas", 8, ""),
            new Item(1, "Almonds", 8, ""),

            new Item(1, "Cheerios", 9, ""),
            new Item(1, "Rice Krispies", 9, ""),
            new Item(1, "Honey Nut Cheerios", 9, ""),
            new Item(1, "Life", 9, ""),
            new Item(1, "Honey Bunches of Oats", 9, ""),
            new Item(1, "Oatmeal", 9, ""),
            new Item(1, "Pancake Mix", 9, ""),

            new Item(1, "Potato Chips", 10, ""),
            new Item(1, "Pretzels", 10, ""),
            new Item(1, "Pringles", 10, ""),
            new Item(1, "Tortilla Chips", 10, ""),
            new Item(1, "Hershey's Chocolate Bars", 10, ""),
            new Item(1, "Hershey Kisses", 10, ""),
            new Item(1, "Reese's Peanut Butter Cups", 10, ""),
            new Item(1, "Mike and Ikes", 10, ""),
            new Item(1, "Starburst", 10, ""),
            new Item(1, "Jelly Beans", 10, ""),

            new Item(1, "Coke", 11, ""),
            new Item(1, "Water", 11, ""),
            new Item(1, "Beer", 11, ""),
            new Item(1, "Coffee", 11, ""),
            new Item(1, "Wine", 11, ""),
            new Item(1, "Gatorade", 11, ""),
            new Item(1, "Tea", 11, ""),
            new Item(1, "Iced Tea", 11, ""),

            new Item(1, "Cake", 12, ""),
            new Item(1, "Cookies", 12, ""),
            new Item(1, "Donuts", 12, ""),
            new Item(1, "Croissants", 12, ""),
            new Item(1, "Challah", 12, ""),

            new Item(1, "Salt", 13, ""),
            new Item(1, "Mayonnaise", 13, ""),
            new Item(1, "BBQ Sauce", 13, ""),
            new Item(1, "Garlic Powder", 13, ""),
            new Item(1, "Onion Powder", 13, ""),
            new Item(1, "Rach Dressing", 13, ""),
            new Item(1, "Thousand Island Dressing", 13, ""),
            new Item(1, "Pepper", 13, ""),
            new Item(1, "Salsa", 13, ""),

            new Item(1, "Plates", 14, ""),
            new Item(1, "Napkins", 14, ""),
            new Item(1, "Masks", 14, ""),
            new Item(1, "Plates", 14, ""),
            new Item(1, "Napkins", 14, ""),
            new Item(1, "Knives", 14, ""),
            new Item(1, "Forks", 14, ""),
            new Item(1, "Spoons", 14, "")
    };

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //Method that specifies all actions to be taken when the Database is first created upon installation
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create the tables
        sqLiteDatabase.execSQL(GroceryListManagerDatabaseContract.CREATE_TABLE_GROCERY_LIST);
        sqLiteDatabase.execSQL(GroceryListManagerDatabaseContract.CREATE_TABLE_ITEM_TYPE);
        sqLiteDatabase.execSQL(GroceryListManagerDatabaseContract.CREATE_TABLE_ITEM);
        sqLiteDatabase.execSQL(GroceryListManagerDatabaseContract.CREATE_TABLE_GROCERY_LIST_ITEM);
        //Insert item types
        for (int i = 0; i < GroceryListManagerDatabaseContract.ITEM_TYPES.length; i++) {
            ContentValues values = new ContentValues();
            values.put(GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_NAME, GroceryListManagerDatabaseContract.ITEM_TYPES[i]);
            sqLiteDatabase.insert(GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_TABLE, null, values);
        }
        //Insert items with specific item types
        for (int i = 0; i < INITIAL_ITEMS.length; i++) {
            ContentValues values = new ContentValues();
            values.put("ItemTypeId", INITIAL_ITEMS[i].getItemType().getItemTypeId());
            values.put("ItemName", INITIAL_ITEMS[i].getItemName());
            sqLiteDatabase.insert(GroceryListManagerDatabaseContract.Item.ITEM_TABLE, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GroceryListManagerDatabaseContract.GroceryListItem.GROCERY_LIST_ITEM_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GroceryListManagerDatabaseContract.Item.ITEM_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GroceryListManagerDatabaseContract.ItemType.ITEM_TYPE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GroceryListManagerDatabaseContract.GroceryList.GROCERY_LIST_TABLE);

        onCreate(sqLiteDatabase);
    }
}
