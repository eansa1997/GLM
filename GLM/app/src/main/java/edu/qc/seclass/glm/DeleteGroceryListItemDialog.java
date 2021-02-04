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

// This dialog appears upon pressing the "Delete Item" button for a given GroceryListItem in ListActivity.
// Upon pressing the "Delete Item" button, the item is deleted and the dialog closes.
/*
author: Xin Huang Liu, Luis Toro, Jeffrey Tom
 */
public class DeleteGroceryListItemDialog extends AppCompatDialogFragment {
    private TextView deleteGroceryListItemTextView;
    private DeleteGroceryListItemTextViewListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        int position = args.getInt("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.delete_grocery_list_item_dialog, null);

        builder.setView(view).setTitle("Delete Grocery List Item").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Delete Item", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.deleteGroceryListItem(position);
            }
        });

        deleteGroceryListItemTextView = view.findViewById(R.id.deleteGroceryListItemTextView);
        deleteGroceryListItemTextView.setText("Are you sure you want to delete this item?");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DeleteGroceryListItemTextViewListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement DeleteGroceryListItemTextViewListener");
        }
    }

    public interface DeleteGroceryListItemTextViewListener {
        void deleteGroceryListItem(int position);
    }
}
