package edu.qc.seclass.glm;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//This Activity allows the user to add an item to their list by selecting from a list of itemTypes, and then selecting from a list of items. Then, the user
//can enter a quantity and a quantityUnit, at which point the user will see his list with the new item added.

/*
author: Shmuel Halbfinger, Jeffrey Tom
 */
public class AddItemByItemTypeActivity extends AppCompatActivity implements ItemTypeAdapter.ItemTypeClickListener, ItemAddToListAdapter.ItemAddToListClickListener, AddItemToListDialog.AddItemToListDialogListener {

    private GroceryList groceryList;
    private ArrayList<ItemType> itemTypes;
    private ArrayList<Item> items;

    private DatabaseManager databaseManager;

    private RecyclerView rvItemTypes;
    private RecyclerView rvItems;

    private ItemTypeAdapter itemTypeAdapter;
    private ItemAddToListAdapter itemAddToListAdapter;

    private Button searchItemButton;
    private Button returnToItemTypesButton;
    private Button returnToListButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_by_item_type);

        Intent intent = getIntent();

        setTitle("Add Item by Item Type");

        databaseManager = new DatabaseManager(this);

        databaseManager.open();

        groceryList = databaseManager.getGroceryListById(intent.getLongExtra("groceryListId", 0));

        searchItemButton = findViewById(R.id.searchItemButton);

        searchItemButton.setText("Search for Item");
        searchItemButton.setBackgroundColor(Color.rgb(248,131,121));


        returnToItemTypesButton = findViewById(R.id.returnToItemTypesButton);
        returnToItemTypesButton.setText("Return to\nItem Types");
        returnToItemTypesButton.setVisibility(View.INVISIBLE);
        returnToItemTypesButton.setBackgroundColor(Color.rgb(248,131,121));

        searchItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to AddItemByNameActivity to search for items to add
                Intent intent = new Intent(getApplicationContext(), AddItemByNameActivity.class);
                intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, groceryList.getGroceryListId());
                startActivity(intent);
            }
        });

        returnToListButton = findViewById(R.id.addItemByItemTypeReturnToListButton);
        returnToListButton.setText("Return to\nList");
        returnToListButton.setBackgroundColor(Color.rgb(248,131,121));
        returnToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Return to the Grocery List
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, groceryList.getGroceryListId());

                startActivity(intent);
            }
        });

        itemTypes = databaseManager.getAllItemTypes();

        Toast.makeText(this, "Choose an item type to start adding items to your list!", Toast.LENGTH_SHORT).show();
        displayItemTypes();
    }

    private void displayItemTypes() {
        rvItemTypes = findViewById(R.id.addItemByItemTypeRecyclerView);

        itemTypeAdapter = new ItemTypeAdapter(itemTypes, this);

        rvItemTypes.setAdapter(itemTypeAdapter);

        rvItemTypes.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemTypeClick(int position) {
        //Upon pressing on an item type, display the items that fall under that item type
        setTitle(itemTypes.get(position).getItemTypeName());

        items = databaseManager.getAllItemsByItemTypeId(itemTypes.get(position).getItemTypeId());

        rvItems = findViewById(R.id.addItemByItemTypeRecyclerView);

        itemAddToListAdapter = new ItemAddToListAdapter(items, this);

        rvItems.setAdapter(itemAddToListAdapter);

        rvItems.setLayoutManager(new LinearLayoutManager(this));

        returnToItemTypesButton.setVisibility(View.VISIBLE);
        returnToItemTypesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go back to the list of item types
                returnToItemTypesButton.setVisibility(View.INVISIBLE);
                setTitle("Add Item By Item Type");
                displayItemTypes();
            }
        });
    }

    @Override
    public void onItemAddToListClick(int position) {
        openAddItemToListByItemTypeDialog(position);
    }

    private void openAddItemToListByItemTypeDialog(int position) {
        //Open the dialog to add a new item to the list
        AddItemToListDialog dialog = new AddItemToListDialog();

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("Item Name", items.get(position).getItemName());

        dialog.setArguments(args);

        dialog.show(getSupportFragmentManager(), "Add Item to List");
    }

    @Override
    public void AddItemToList(int quantity, String quantityUnit, int position) {
        //Insert the grocery list item into the list and return to ListActivity
        databaseManager.insertGroceryListItem(groceryList.getGroceryListId(), items.get(position), quantity, quantityUnit);
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, groceryList.getGroceryListId());

        startActivity(intent);
    }
}