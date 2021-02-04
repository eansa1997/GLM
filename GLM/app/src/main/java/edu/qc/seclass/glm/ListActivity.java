package edu.qc.seclass.glm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// This activity lists the grocery list items for the selected grocery list. Each item contains the name, quantity, and unit type, along with a checkbox.
// Each item also contains buttons to edit and delete the items
// There are also buttons to group the items by their type, uncheck all the items, and add items by either name or type
// There is a back button on the top left to go back to the MainActivity
/*
author: Xin Huang Liu, Luis Toro, Jeffrey Tom
 */
public class ListActivity extends AppCompatActivity implements GroceryListItemAdapter.GroceryListItemClickListener,
        DeleteGroceryListItemDialog.DeleteGroceryListItemTextViewListener, ChangeGroceryListItemQuantityAndUnitDialog.ChangeGroceryListItemQuantityAndUnitDialogListener {

    private RecyclerView rvGroceryListItems;
    private GroceryListItemAdapter groceryListItemAdapter;
    private DatabaseManager databaseManager;
    private Intent selectedListIntent;

    private long groceryListId;
    private GroceryList groceryList;
    private ArrayList<GroceryListItem> items;


    private Button groupItemByType;
    private Button uncheckAllItemButton;
    private Button addItemByType;
    private Button addItemByName;

    private boolean groupedByType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        // Get the Intent that started this activity and extract the listId
        selectedListIntent = getIntent();
        groceryListId = selectedListIntent.getLongExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, 0);

        groupedByType = false;

        // Needs selectedList Name that is in the database
        databaseManager = new DatabaseManager(this);
        databaseManager.open();
        groceryList = databaseManager.getGroceryListById(groceryListId);
        setTitle(groceryList.getGroceryListName());
        items = databaseManager.getGroceryListItemByGroceryListId((int) groceryListId);


        addItemByName = findViewById(R.id.addItemByNameButton);
        addItemByName.setBackgroundColor(Color.rgb(248,131,121));
        addItemByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to AddItemByNameActivity
                Intent intent = new Intent(ListActivity.this, AddItemByNameActivity.class);
                intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, groceryListId);
                startActivity(intent);
            }
        });

        addItemByType = findViewById(R.id.addItemByTypeButton);
        addItemByType.setBackgroundColor(Color.rgb(248,131,121));
        addItemByType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to AddItemByItemTypeActivity
                Intent intent = new Intent(ListActivity.this, AddItemByItemTypeActivity.class);
                intent.putExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, groceryListId);
                startActivity(intent);
            }
        });

        uncheckAllItemButton = findViewById(R.id.uncheckAllItemButton);
        uncheckAllItemButton.setBackgroundColor(Color.rgb(248,131,121));
        uncheckAllItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uncheck all items in the list
                databaseManager.uncheckAllGroceryListItems(groceryListId);
                items = databaseManager.getGroceryListItemByGroceryListId((int) groceryListId);
                displayGroceryListItem(groupedByType);
            }
        });


        groupItemByType = findViewById(R.id.groupItemByTypeButton);
        groupItemByType.setBackgroundColor(Color.rgb(248,131,121));
        groupItemByType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display the same grocery list grouped by type
                items = databaseManager.getGroceryListItemsGroupedByItemType(groceryListId);
                groupedByType = !groupedByType;
                displayGroceryListItem(groupedByType);

            }
        });

        displayGroceryListItem(groupedByType);

        if (items.isEmpty()) {
            Toast.makeText(this, "Start adding items by pressing one of the 'Add Item' Buttons below!", Toast.LENGTH_LONG).show();
        }
    }

    public void displayGroceryListItem(boolean groupedByType) {
        rvGroceryListItems = findViewById(R.id.groceryItemRecyclerView);

        groceryListItemAdapter = new GroceryListItemAdapter(items, this, groupedByType);

        rvGroceryListItems.setAdapter(groceryListItemAdapter);

        rvGroceryListItems.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onChangeItemQuantityAndUnitButtonClickListener(int position) {
        openChangeItemQuantityAndUnitButton(position);
    }

    public void openChangeItemQuantityAndUnitButton(int position) {
        ChangeGroceryListItemQuantityAndUnitDialog dialog = new ChangeGroceryListItemQuantityAndUnitDialog();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("quantity", items.get(position).getQuantity());
        args.putString("quantityUnit", items.get(position).getQuantityUnit());
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "Change Quantity And Unit");
    }

    @Override
    public void changeGroceryListItemQuantityAndUnit(int position, int quantity, String unit) {

        databaseManager.updateGroceryListItem(items.get(position), quantity);
        databaseManager.updateGroceryListItem(items.get(position), unit);

        items = databaseManager.getGroceryListItemByGroceryListId((int) groceryListId);
        displayGroceryListItem(groupedByType);

    }


    @Override
    public void onDeleteItemButtonClickListener(int position) {
        openDeleteListDialog(position);
    }

    public void openDeleteListDialog(int position) {
        DeleteGroceryListItemDialog dialog = new DeleteGroceryListItemDialog();
        Bundle args = new Bundle();
        args.putInt("position", position);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "Delete Item");
    }

    @Override
    public void deleteGroceryListItem(int position) {
        databaseManager.deleteGroceryListItem(items.get(position));
        items = databaseManager.getGroceryListItemByGroceryListId((int) groceryListId);
        displayGroceryListItem(groupedByType);
    }


    @Override
    public void onIsCheckedCheckBoxClickListener(int position) {
        isCheckedItem(position);

    }

    public void isCheckedItem(int position) {
        if (items.get(position).isChecked())
            databaseManager.updateGroceryListItem(items.get(position), false);
        else databaseManager.updateGroceryListItem(items.get(position), true);

    }


}