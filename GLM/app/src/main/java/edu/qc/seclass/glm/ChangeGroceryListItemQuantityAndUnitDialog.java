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
import androidx.appcompat.app.AppCompatDialogFragment;

// This dialog pops up in the ListActivity. Upon pressing an the "Edit Item" button of a certain GroceryListItem, this dialog will appear
// with areas to edit the quantity and unit type of that item. The item is then added to the list and the list is updated.
/*
author: Xin Huang Liu, Luis Toro, Jeffrey Tom
 */
public class ChangeGroceryListItemQuantityAndUnitDialog extends AppCompatDialogFragment {

    private EditText changeGroceryListItemQuantityEditText;
    private EditText changeGroceryListItemUnitEditText;
    private ChangeGroceryListItemQuantityAndUnitDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();

        int position = args.getInt("position");
        String quantity = Integer.toString(args.getInt("quantity"));
        String quantityUnit = args.getString("quantityUnit");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.change_item_quantity_and_unit_dialog, null);

        builder.setView(view).setTitle("Change Item Quantity and Unit").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int quantity = 0;
                        try {
                            quantity = Integer.parseInt(changeGroceryListItemQuantityEditText.getText().toString());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Quantity is Empty! Default to 0.", Toast.LENGTH_SHORT).show();
                        }

                        String unit = changeGroceryListItemUnitEditText.getText().toString();

                        listener.changeGroceryListItemQuantityAndUnit(position, quantity, unit);
                    }
                });
        changeGroceryListItemQuantityEditText = view.findViewById(R.id.changeItemQuantityEditText);
        changeGroceryListItemQuantityEditText.setText(quantity);
        changeGroceryListItemUnitEditText = view.findViewById(R.id.changeItemUnitEditText);
        changeGroceryListItemUnitEditText.setText(quantityUnit);

        changeGroceryListItemUnitEditText = view.findViewById(R.id.changeItemUnitEditText);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ChangeGroceryListItemQuantityAndUnitDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ChangeGroceryListItemQuantityAndUnitDialogListener");
        }
    }

    public interface ChangeGroceryListItemQuantityAndUnitDialogListener {

        void changeGroceryListItemQuantityAndUnit(int position, int quantity, String unit);

    }
}