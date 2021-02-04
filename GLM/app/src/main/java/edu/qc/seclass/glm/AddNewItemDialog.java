package edu.qc.seclass.glm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

// Dialog to add a new item to the database. If the user chooses an item type in AddItemToDatabaseActivity, and no item name
// had been passed from AddItemByNameActivity, this dialog will prompt the user to enter a name for their new item. If this item/item type combo
// is not already in the database, the item is added for future use
/*
author: Shmuel Halbfinger
 */
public class AddNewItemDialog extends AppCompatDialogFragment {
    private TextView addNewItemDialogTextView;
    private AddNewItemDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        String newItemName = args.getString("New Item");

        Boolean noItemsFound = args.getBoolean("No Items found");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.add_new_item_dialog, null);

        builder.setView(view).setTitle("Add " + newItemName + "?").setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!noItemsFound) {
                    listener.goToAddItemToDatabaseActivity();
                }
            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.addNewItem(newItemName);
            }
        });

        addNewItemDialogTextView = view.findViewById(R.id.addNewItemDialogTextView);

        if (noItemsFound) {
            addNewItemDialogTextView.setText(newItemName + " was not found. Would you like to add " + newItemName + " for future use?");
        }
        else {
            addNewItemDialogTextView.setText("'" + newItemName + "' gave some search results.\nPress 'Yes' to add " + newItemName + ".\nPress 'No' to add a new item from scratch.");
        }

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddNewItemDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement AddItemToDatabaseDialogListener");
        }
    }

    public interface AddNewItemDialogListener {
        void addNewItem(String itemName);
        void goToAddItemToDatabaseActivity();
    }
}
