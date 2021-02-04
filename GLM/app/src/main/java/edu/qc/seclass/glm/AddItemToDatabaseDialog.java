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

// This dialog pops up in the AddItemToDatabaseActivity. Upon pressing an ItemType, when there is no specific item name
// passed from AddItemByNameActivity, this dialog will appear with areas to enter the name of the new item.
// The new item is then added to the database and AddItemByNameActivity is started
/*
author: Shmuel Halbfinger
 */
public class AddItemToDatabaseDialog extends AppCompatDialogFragment {
    private EditText addItemToDatabaseEditText;
    private AddItemToDatabaseDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        int position = args.getInt("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.add_item_to_database_dialog, null);

        builder.setView(view).setTitle("Added Item to Database").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Add Item to Database", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String itemName = addItemToDatabaseEditText.getText().toString();
                if (itemName.equals("")) {
                    Toast.makeText(getActivity(), "No Item name was given, so the Item was not created", Toast.LENGTH_SHORT).show();
                }
                else {
                    listener.addItemToDatabase(itemName, position);
                }
            }
        });

        addItemToDatabaseEditText = view.findViewById(R.id.addItemToDatabaseEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddItemToDatabaseDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement AddItemToDatabaseDialogListener");
        }
    }

    public interface AddItemToDatabaseDialogListener {
        void addItemToDatabase(String itemName, int position);
    }
}
