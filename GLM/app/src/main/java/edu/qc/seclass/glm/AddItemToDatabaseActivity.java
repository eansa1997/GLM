package edu.qc.seclass.glm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

// This activity allows the user to add a new item to the database that can be added to future lists.
// If an item had bee searched for in AddItemByNameActivity and had not been found, this activity allows the user to choose an item type for that item.
// The User can also enter this Activity manually and add an Item without first searching for it
// After adding an item to the database, the user will be redirected back to the AddItemByNameActivity
// The user can also return to their list from this activity
/*
author: Shmuel Halbfinger
 */
public class AddItemToDatabaseActivity extends AppCompatActivity implements ItemTypeAdapter.ItemTypeClickListener, AddItemToDatabaseDialog.AddItemToDatabaseDialogListener {

    private GroceryList groceryList;
    private DatabaseManager databaseManager;
    private ArrayList<ItemType> itemTypes;

    private RecyclerView rvItemTypes;

    private ItemTypeAdapter itemTypeAdapter;

    private Button returnToListButton;
    private TextView addItemToDatabaseTextview;

    private String newItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_to_database);

        databaseManager = new DatabaseManager(this);

        databaseManager.open();

        Intent intent = getIntent();

        groceryList = databaseManager.getGroceryListById(intent.getLongExtra("groceryListId", 0));

        newItemName = intent.getStringExtra("New Item Name");

        addItemToDatabaseTextview = findViewById(R.id.addItemToDatabaseTextview);
        addItemToDatabaseTextview.setText("Choose an Item Type for " + newItemName);

        //If no new Item Name is passed along from AddItemByNameActivity, the activity allows the user to add an item from scratch
        if (newItemName.equals("")) {
            setTitle("Add Item to Database");
            addItemToDatabaseTextview.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Choose an item type and then enter the name of an item to add it for future use!", Toast.LENGTH_SHORT).show();
        }
        //If a new item name is passed from AddItemByNameActivity, choose an item type for that item and it will be added to the database
        else {
            setTitle("Add " + newItemName + " to Database");
            Toast.makeText(this, "Choose an item type for " + newItemName + "!", Toast.LENGTH_SHORT).show();
            addItemToDatabaseTextview.setVisibility(View.VISIBLE);
        }

        itemTypes = databaseManager.getAllItemTypes();

        displayItemTypes();

        returnToListButton = findViewById(R.id.addItemToDatabaseReturnToListButton);
        returnToListButton.setBackgroundColor(Color.rgb(248,131,121));

        //Return to ListActivity
        returnToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGroceryList(groceryList.getGroceryListId());
            }
        });
    }

    private void displayItemTypes() {
        rvItemTypes = findViewById(R.id.itemTypeRecyclerView);

        itemTypeAdapter = new ItemTypeAdapter(itemTypes, this);

        rvItemTypes.setAdapter(itemTypeAdapter);

        rvItemTypes.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemTypeClick(int position) {
        if (newItemName.equals("")) {
            //If no specific item, open dialog for user to enter name of new item
            openAddItemToDatabaseDialog(position);
        }
        else {
            //if there is a specific item, add that item with the chosen item type
            addItemToDatabase(newItemName, position);
        }
    }

    private void openAddItemToDatabaseDialog(int position) {
        AddItemToDatabaseDialog dialog = new AddItemToDatabaseDialog();

        Bundle args = new Bundle();
        args.putInt("position", position);

        dialog.setArguments(args);

        dialog.show(getSupportFragmentManager(), "Add Item to Database");
    }

    @Override
    public void addItemToDatabase(String itemName, int position) {
        //Check to make sure the chosen item/item type combination isn't already inserted into the database
        //  If it is, go to AddItemByNameActivity and inform the user that they already have this combination
        //  If it is not, add the item to the database and go to AddItemByNameActivity
        ArrayList<Item> items = new ArrayList<>();
        items = databaseManager.getAllItemsByItemTypeId(itemTypes.get(position).getItemTypeId());
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                Intent intent = new Intent(getApplicationContext(), AddItemByNameActivity.class);
                intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, groceryList.getGroceryListId());
                Toast.makeText(this, itemName + " already exists with type " + itemTypes.get(position).getItemTypeName(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return;
            }
        }

        databaseManager.insertItem(itemTypes.get(position), itemName);

        goToAddItemByNameActivity(itemName, groceryList.getGroceryListId());
    }

    private void goToGroceryList(long groceryListId) {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, groceryList.getGroceryListId());
        startActivity(intent);
    }

    private void goToAddItemByNameActivity(String itemName, long groceryListId) {
        Intent intent = new Intent(getApplicationContext(), AddItemByNameActivity.class);
        intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, groceryList.getGroceryListId());
        Toast.makeText(this, itemName + " can now be searched for and added to a list!!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}