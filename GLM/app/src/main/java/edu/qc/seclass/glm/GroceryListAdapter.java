package edu.qc.seclass.glm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// This class is the adapter which will populate the view in MainActivity with the Grocery Lists.
// It also specifies the layout of each Grocery List, populating the layout with the TextViews and Buttons
/*
author: Shmuel Halbfinger
 */
public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.ViewHolder> {

    //Takes the ArrayList of GroceryLists from the MainActivity
    private ArrayList<GroceryList> mGroceryLists;

    private GroceryListClickListener mGroceryListClickListener;

    //The ViewHolder class sets the view for each item within the Recycler View
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView groceryListNameTextView;
        public Button removeGroceryListButton;
        public Button renameGroceryListButton;

        // The constructor gets the views of each item for the GroceryList layout, and sets the OnClickListeners
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            groceryListNameTextView = itemView.findViewById(R.id.groceryListNameTextView);
            removeGroceryListButton = itemView.findViewById(R.id.removeGroceryListButton);
            renameGroceryListButton = itemView.findViewById(R.id.renameGroceryListButton);

            groceryListNameTextView.setOnClickListener(this);
            removeGroceryListButton.setOnClickListener(this);
            renameGroceryListButton.setOnClickListener(this);
        }

        // Calls the onClick method for the clicked View of the GroceryList
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (view == groceryListNameTextView) {
                mGroceryListClickListener.onGroceryListNameClick(position);
            } else if (view == removeGroceryListButton) {
                mGroceryListClickListener.onRemoveListClickListener(position);
            } else if (view == renameGroceryListButton) {
                mGroceryListClickListener.onRenameListClickListener(position);
            }
        }
    }

    public GroceryListAdapter(ArrayList<GroceryList> groceryLists, GroceryListClickListener listener) {
        mGroceryLists = groceryLists;
        this.mGroceryListClickListener = listener;
    }

    //This method gets the parent context, inflates the layout of the grocery_list view, which specifies the view for each Grocery List
    @NonNull
    @Override
    public GroceryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View groceryListView = inflater.inflate(R.layout.grocery_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(groceryListView);
        return viewHolder;
    }

    //This method adds names to the different Views within the viewHolder
    @Override
    public void onBindViewHolder(@NonNull GroceryListAdapter.ViewHolder holder, int position) {
        GroceryList groceryList = mGroceryLists.get(position);

        TextView groceryListNameTextView = holder.groceryListNameTextView;
        Button removeGroceryListButton = holder.removeGroceryListButton;
        Button renameGroceryListButton = holder.renameGroceryListButton;

        groceryListNameTextView.setText((position + 1) + ". " + groceryList.getGroceryListName());
        removeGroceryListButton.setText("Delete");
        removeGroceryListButton.setBackgroundColor(Color.rgb(248,131,121));
        renameGroceryListButton.setText("Rename");
        renameGroceryListButton.setBackgroundColor(Color.rgb(248,131,121));
    }

    @Override
    public int getItemCount() {
        return mGroceryLists.size();
    }

    // Custom interface for the ClickListener for the GroceryList. Each method
    // will be called when clicking on a different View.
    // These methods are defined in MainActivity
    public interface GroceryListClickListener {
        void onGroceryListNameClick(int position);

        void onRemoveListClickListener(int position);

        void onRenameListClickListener(int position);
    }
}
