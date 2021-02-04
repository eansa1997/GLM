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

// This dialog pops up in the AddItemByItemTypeActivity and AddItemByNameActivity. Upon pressing an Item, this dialog will appear
// with areas to enter the quantity and unit type. The object is then added to the list and ListActivity is started. if the item
// already exists in the list, it is not added again.
/*
author: Shmuel Halbfinger
 */
public class AddItemToListDialog extends AppCompatDialogFragment {
    private EditText addItemQuantityEditText;
    private EditText addItemUnitTypeEditText;
    private AddItemToListDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        int position = args.getInt("position");
        String itemToBeAdded = args.getString("Item Name");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.add_item_by_item_type_dialog, null);

        builder.setView(view).setTitle("Add " + itemToBeAdded + " to List").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Add Item to List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(addItemQuantityEditText.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    if (addItemQuantityEditText.getText().toString().equals(""))
                        Toast.makeText(getContext(), "Quantity is Empty! Default to 0.", Toast.LENGTH_SHORT).show();
                }
                String quantityUnit = addItemUnitTypeEditText.getText().toString();
                listener.AddItemToList(quantity, quantityUnit, position);
            }
        });

        addItemQuantityEditText = view.findViewById(R.id.addItemQuantityEditText);
        addItemUnitTypeEditText = view.findViewById(R.id.addItemUnitTypeEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddItemToListDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement AddItemToDatabaseDialogListener");
        }
    }

    public interface AddItemToListDialogListener {
        void AddItemToList(int quantity, String quantityUnit, int position);
    }
}
