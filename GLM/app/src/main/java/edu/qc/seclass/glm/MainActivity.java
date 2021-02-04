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

// This activity is the launcher activity. It lists the user's grocery lists. Each list can be selected, renamed, and deleted.
// There is also the option to create a new list
// Upon selecting a Grocery List, ListActivity is launched

/*
author: Shmuel Halbfinger
 */
public class MainActivity extends AppCompatActivity implements GroceryListAdapter.GroceryListClickListener,
        RenameGroceryListDialog.RenameGroceryListDialogListener,
        RemoveGroceryListDialog.RemoveGroceryListDialogListener,
        CreateGroceryListDialog.CreateListDialogListener {

    private ArrayList<GroceryList> groceryLists;
    private DatabaseManager databaseManager;
    private RecyclerView rvGroceryLists;

    private GroceryListAdapter groceryListAdapter;
    private Button createGroceryListButton;

    //This constant can be used by other classes to make sure that each intent passes in the right key for the groceryListId of the selected list
    public static final String GROCERY_LIST_ID_CONSTANT = "groceryListId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set page title
        setTitle("Grocery List Manager");

        // Add a database manager to allow access to database functions
        databaseManager = new DatabaseManager(this);

        //Open a connection to the database
        databaseManager.open();

        //Populate the ArrayList with GroceryList objects from the database
        groceryLists = databaseManager.getAllGroceryList();

        // Get the Create List button from the view and set a listener to open the dialog used to create a list
        createGroceryListButton = findViewById(R.id.createGroceryListButton);
        createGroceryListButton.setBackgroundColor(Color.rgb(248,131,121));
        createGroceryListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateListDialog();
            }
        });

        if (groceryLists.size() == 0) {
            Toast.makeText(this, "Welcome to the Grocery List Manager! Press the 'Create Grocery List' button below to begin", Toast.LENGTH_SHORT).show();
        }
        //Call the method that will populate the activity with grocery lists
        displayGroceryLists();
    }

    // Open the Dialog used to create lists, allowing the user to enter the name and add it to the database
    private void openCreateListDialog() {
        //Create the CreateGroceryList Dialog
        CreateGroceryListDialog dialog = new CreateGroceryListDialog();
        //Set the title and show the dialog
        dialog.show(getSupportFragmentManager(), "Create Grocery List");
    }

    // Gets the recycler view and populates it with the Grocery Lists from the ArrayList
    // Set the RecyclerView, set its Adapter, and set the Layout Manager
    public void displayGroceryLists() {
        rvGroceryLists = findViewById(R.id.groceryListRecyclerView);

        groceryListAdapter = new GroceryListAdapter(groceryLists, this);

        rvGroceryLists.setAdapter(groceryListAdapter);

        rvGroceryLists.setLayoutManager(new LinearLayoutManager(this));
    }

    // This method is overridden from the GroceryListAdapter class. Calls the method to select a GroceryList
    @Override
    public void onGroceryListNameClick(int position) {
        selectGroceryList(position);
    }

    //Creates an intent to pass along the GroceryListId from the selected GroceryList and to start the ListActivity
    public void selectGroceryList(int position) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(GROCERY_LIST_ID_CONSTANT, groceryLists.get(position).getGroceryListId());
        startActivity(intent);
    }

    // Overridden from the GroceryListAdapter Class. When the Remove List button is clicked for a list, a dialog will pop up
    @Override
    public void onRemoveListClickListener(int position) {
        openDeleteListDialog(position);
    }

    // Opens the DeleteList dialog, allowing the user to delete the List
    private void openDeleteListDialog(int position) {
        RemoveGroceryListDialog dialog = new RemoveGroceryListDialog();
        Bundle args = new Bundle();
        //Pass in the position of the List in the ArrayList that is to be deleted.
        args.putInt("position", position);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "Delete Grocery List");
    }

    // Overridden from the GroceryListAdapter Class. When the Rename List button is clicked for a list, a dialog will pop up
    @Override
    public void onRenameListClickListener(int position) {
        openEditNameDialog(position);
    }

    // Opens the RenameGroceryList dialog, allowing the user to delete the List
    private void openEditNameDialog(int position) {
        RenameGroceryListDialog dialog = new RenameGroceryListDialog();
        Bundle args = new Bundle();
        //Pass in the position of the List in the ArrayList that is to be renamed.
        args.putInt("position", position);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "Rename Grocery List");
    }

    //Overridden from the RenameGroceryListDialog Class, renames the list and redisplays the Lists
    @Override
    public void renameGroceryList(String groceryListName, int position) {
        if (groceryListName.equals("")) {
            Toast.makeText(this, "No name was specified. The list could not be renamed.", Toast.LENGTH_SHORT).show();
        }
        else {
            databaseManager.updateGroceryListNameById(groceryLists.get(position).getGroceryListId(), groceryListName);
            groceryLists = databaseManager.getAllGroceryList();
            displayGroceryLists();
        }
    }

    //Overridden from the RemoveGroceryListDialog Class, deletes the list and it's groceryListItems from the database and redisplays the Lists
    @Override
    public void deleteGroceryList(int position) {
        databaseManager.deleteAllGroceryListItemFromGroceryList(groceryLists.get(position).getGroceryListId());
        databaseManager.deleteGroceryListById(groceryLists.get(position).getGroceryListId());
        groceryLists = databaseManager.getAllGroceryList();
        displayGroceryLists();
    }

    //Overridden from the CreateGroceryList Class, creates a list and redisplays the lists with the new list
    @Override
    public void createList(String groceryListName) {
        if (groceryListName.equals("")) {
            Toast.makeText(this, "No name was specified. The list could not be created.", Toast.LENGTH_SHORT).show();
        }
        else {
            databaseManager.insertGroceryList(groceryListName);
            groceryLists = databaseManager.getAllGroceryList();
            if (groceryLists.size() == 1) {
                Toast.makeText(this, "Click on the name of your Grocery List to get started!", Toast.LENGTH_SHORT).show();
            }
            displayGroceryLists();
        }
    }
}