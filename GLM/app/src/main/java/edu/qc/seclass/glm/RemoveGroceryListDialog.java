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

// This dialog appears upon pressing the "Delete List" button for a given list in MainActivity
// Upon pressing the "Delete List" button, the list is deleted, along with all items inside, and the dialog is closed
/*
author: Shmuel Halbfinger
 */
public class RemoveGroceryListDialog extends AppCompatDialogFragment {
    private TextView removeGroceryListTextView;
    private RemoveGroceryListDialogListener listener;

    // Method for creating the RemoveListDialog
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        // The position of the list in the recycler View is acquired from the arguments Bundle
        int position = args.getInt("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        //The view for the dialog is inflated
        View view = inflater.inflate(R.layout.delete_grocerylist_dialog, null);

        //The title is set, and negative and positive buttons are set with onClickListeners
        builder.setView(view).setTitle("Delete Grocery List").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Delete List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.deleteGroceryList(position);
            }
        });

        removeGroceryListTextView = view.findViewById(R.id.removeGroceryListTextView);
        removeGroceryListTextView.setText("Are you sure you want to delete this list?");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (RemoveGroceryListDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement RemoveGroceryListDialogListener");
        }
    }

    //Custom listener whose logic is defined in MainActivity, will rename the list to the given name
    public interface RemoveGroceryListDialogListener {
        void deleteGroceryList(int position);
    }
}
