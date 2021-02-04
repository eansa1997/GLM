package edu.qc.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// This activity allows the user to type the name of an item and add it to the list.
// It also allows includes the ability to go to the AddItemToDatabaseActivity if the user wants to add a new item that can be added to future lists.
/*
author: Esteban Ansaldo, Allan Gershon
 */
public class AddItemByNameActivity extends AppCompatActivity implements ItemAddToListAdapter.ItemAddToListClickListener, AddNewItemDialog.AddNewItemDialogListener, AddItemToListDialog.AddItemToListDialogListener {
    private ArrayList<Item> items;
    private DatabaseManager databaseManager;
    private Button searchItem;
    private Button addButton;
    private Button returnToListButton;
    private EditText text;
    private GroceryList list;
    private RecyclerView recycler;
    private ItemAddToListAdapter recyclerAdapter;
    private long listId;
    private String searchTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_by_name);

        Intent intent = getIntent();
        listId = intent.getLongExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, 0);

        //open db to search items
        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        list = databaseManager.getGroceryListById(listId);
        items = new ArrayList<>();

        searchItem = findViewById(R.id.searchItemByNameButton);
        searchItem.setBackgroundColor(Color.rgb(248,131,121));
        text = findViewById(R.id.searchItemByNameEditText);
        addButton = findViewById(R.id.addItemByNameCreateButton);
        addButton.setBackgroundColor(Color.rgb(248,131,121));
        returnToListButton = findViewById(R.id.addItemByNameReturnToListButton);
        returnToListButton.setBackgroundColor(Color.rgb(248,131,121));

        searchTerm = new String("");

        searchItem.setOnClickListener(new View.OnClickListener() {
            // Search for the items with the given searchTerm.
            //      If no items found, ask the user if they would like to add the item to the database
            //             If yes, go to AddItemToDatabaseActivity
            //             If no, stay in the activity
            //      If items found, display the items
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                searchTerm = new String(text.getText().toString());
                items = databaseManager.getAllItemsByName(searchTerm);
                hideKeyboard(v);

                if (items.size() == 0) {
                    AddNewItemDialog dialog = new AddNewItemDialog();
                    Bundle args = new Bundle();
                    args.putString("New Item", searchTerm);
                    args.putBoolean("No Items found", true);
                    dialog.setArguments(args);
                    dialog.show(getSupportFragmentManager(), "Add new Item");

                }

                try {
                    boolean success = addItemsToListView();
                    if (!success) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Error, please try again!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            // Button to go to AddItemToDatabaseActivity
            // If the search space is empty, go straight to the activity to add item from scratch
            // If the search space is not empty, ask the user if they would like to add the searchTerm as an item:
            //      If yes, go to AddItemToDatabaseActivity to choose an item type
            //      If no, go to AddItemToDatabaseActivity to add an item from scratch
            public void onClick(View v) {
                searchTerm = new String(text.getText().toString());
                Boolean exactMatchFound = false;
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getItemName().equalsIgnoreCase(searchTerm)) {
                        exactMatchFound = true;
                    }
                }
                if (!searchTerm.equals("") && !exactMatchFound) {
                    AddNewItemDialog dialog = new AddNewItemDialog();
                    Bundle args = new Bundle();
                    args.putString("New Item", searchTerm);
                    args.putBoolean("No Items found", false);
                    dialog.setArguments(args);
                    dialog.show(getSupportFragmentManager(), "Add new Item");
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), AddItemToDatabaseActivity.class);
                    intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, listId);
                    intent.putExtra("New Item Name", "");
                    startActivity(intent);
                }
            }
        });

        returnToListButton.setOnClickListener(new View.OnClickListener() {
            //Return to List Activity
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, listId);
                startActivity(intent);
            }
        });

        Toast.makeText(this, "Search for an item to add it to the list!", Toast.LENGTH_SHORT).show();
    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }

    private boolean addItemsToListView() {
        // Display the items based on the search term
        try {
            recycler = findViewById(R.id.AddItemByNameRecyclerView);
            recyclerAdapter = new ItemAddToListAdapter(items, this);

            recycler.setAdapter(recyclerAdapter);
            recycler.setLayoutManager(new LinearLayoutManager(this));

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void onItemAddToListClick(int position) {
        AddItemToListDialog dialog = new AddItemToListDialog();

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("Item Name", items.get(position).getItemName());

        dialog.setArguments(args);

        dialog.show(getSupportFragmentManager(), "Add Item to List");


        /*Item t = items.get(position);
        // temp replace with listID passed in by previous activity
        try {
            databaseManager.insertGroceryListItem(listId, t, 1, "");
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, listId);
            startActivity(intent);
        } catch (SQLiteConstraintException e) {
            Context context = getApplicationContext();
            CharSequence text = "Item already exists in list!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }*/
    }

    @Override
    public void addNewItem(String itemName) {
        Intent intent = new Intent(getApplicationContext(), AddItemToDatabaseActivity.class);
        intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, listId);
        intent.putExtra("New Item Name", itemName);
        startActivity(intent);
    }

    @Override
    public void goToAddItemToDatabaseActivity() {
        Intent intent = new Intent(getApplicationContext(), AddItemToDatabaseActivity.class);
        intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, listId);
        intent.putExtra("New Item Name", "");
        startActivity(intent);
    }

    @Override
    public void AddItemToList(int quantity, String quantityUnit, int position) {
        databaseManager.insertGroceryListItem(list.getGroceryListId(), items.get(position), quantity, quantityUnit);
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, list.getGroceryListId());

        startActivity(intent);
    }
}