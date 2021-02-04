package edu.qc.seclass.glm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

// This dialog appears upon pressing the "Create Grocery List" button in MainActivity. It contains a text area to enter the name of the new list
// Upon pressing the "Create List" button, the list is added and the dialog closes.
/*
author: Shmuel Halbfinger
 */
public class CreateGroceryListDialog extends AppCompatDialogFragment {
    private EditText createGroceryListEditText;
    private CreateListDialogListener listener;

    // Method for creating the CreateListDialog
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        //The view for the dialog is inflated
        View view = inflater.inflate(R.layout.create_grocerylist_dialog, null);

        //The title is set, and negative and positive buttons are set with onClickListeners
        builder.setView(view).setTitle("Create GroceryList").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Create List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (createGroceryListEditText.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "No name was given, so the list is not created", Toast.LENGTH_SHORT).show();
                }
                else {
                    String groceryListName = createGroceryListEditText.getText().toString();
                    listener.createList(groceryListName);
                }
            }
        });

        createGroceryListEditText = view.findViewById(R.id.createGroceryListEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            // Add the listener
            listener = (CreateListDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement CreateGroceryListDialogListener");
        }
    }

    //Custom listener whose logic is defined in MainActivity, will create the list with the given name
    public interface CreateListDialogListener {
        void createList(String groceryListName);
    }
}

