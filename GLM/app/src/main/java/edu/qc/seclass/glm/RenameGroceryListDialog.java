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

// This dialog appears upon pressing the "Rename List" button for a given list in MainActivity
// Upon pressing the "Rename List" button, the list is renamed and the dialog is closed
/*
author: Shmuel Halbfinger
 */
public class RenameGroceryListDialog extends AppCompatDialogFragment {
    private EditText renameGroceryListEditText;
    private RenameGroceryListDialogListener listener;

    // Method for creating the RenameListDialog
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        // The position of the list in the recycler View is acquired from the arguments Bundle
        int position = args.getInt("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        //The view for the dialog is inflated
        View view = inflater.inflate(R.layout.edit_grocerylist_dialog, null);

        //The title is set, and negative and positive buttons are set with onClickListeners
        builder.setView(view).setTitle("Rename Grocery List").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Rename List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (renameGroceryListEditText.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "No new name was entered, so the list was not renamed", Toast.LENGTH_SHORT).show();
                }
                else {
                    String groceryListName = renameGroceryListEditText.getText().toString();
                    listener.renameGroceryList(groceryListName, position);
                }
            }
        });

        renameGroceryListEditText = view.findViewById(R.id.renameGroceryListEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (RenameGroceryListDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement RenameGroceryListDialogListener");
        }
    }

    //Custom listener whose logic is defined in MainActivity, will rename the list to the given name
    public interface RenameGroceryListDialogListener {
        void renameGroceryList(String groceryListName, int position);
    }
}