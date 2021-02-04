package edu.qc.seclass.glm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// This class is the adapter which will populate the view in ListActivity with the Grocery List Items.
// It also specifies the layout of each Grocery List Item, populating the layout with the TextViews and Buttons
/*
author: Xin Huang Liu, Luis Toro, Jeffrey Tom
 */
public class GroceryListItemAdapter extends RecyclerView.Adapter<GroceryListItemAdapter.ViewHolder> {
    private boolean groupedByType;
    private ArrayList<GroceryListItem> mListItem;

    private GroceryListItemClickListener mListItemClickListener;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView groceryItemNameTextView;
        public TextView groceryItemQuantityTextView;
        public TextView groceryItemUnitTextView;
        public Button changeItemQuantityAndUnitButton;
        public Button deleteItemButton;
        public CheckBox isCheckedCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            groceryItemNameTextView = itemView.findViewById(R.id.groceryItemNameTextView);
            groceryItemQuantityTextView = itemView.findViewById(R.id.groceryItemQuantityTextView);
            groceryItemUnitTextView = itemView.findViewById(R.id.groceryItemUnitTextView);
            changeItemQuantityAndUnitButton = itemView.findViewById(R.id.changeItemQuantityAndUnitButton);
            deleteItemButton = itemView.findViewById(R.id.deleteItemButton);
            isCheckedCheckBox = itemView.findViewById(R.id.isCheckedCheckBox);

            groceryItemNameTextView.setOnClickListener(this);
            groceryItemQuantityTextView.setOnClickListener(this);
            groceryItemUnitTextView.setOnClickListener(this);
            changeItemQuantityAndUnitButton.setOnClickListener(this);
            deleteItemButton.setOnClickListener(this);
            isCheckedCheckBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (view == deleteItemButton) {
                mListItemClickListener.onDeleteItemButtonClickListener(position);
            } else if (view == isCheckedCheckBox) {
                mListItemClickListener.onIsCheckedCheckBoxClickListener(position);
            } else if (view == changeItemQuantityAndUnitButton) {
                mListItemClickListener.onChangeItemQuantityAndUnitButtonClickListener(position);
            }
        }
    }

    public GroceryListItemAdapter(ArrayList<GroceryListItem> listItems, GroceryListItemAdapter.GroceryListItemClickListener listener, boolean groupedByType) {
        mListItem = listItems;
        this.mListItemClickListener = listener;
        this.groupedByType = groupedByType;
    }

    @NonNull
    @Override
    public GroceryListItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View listItemListView = inflater.inflate(R.layout.grocery_list_item, parent, false);

        ViewHolder viewHolder = new GroceryListItemAdapter.ViewHolder(listItemListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryListItemAdapter.ViewHolder holder, int position) {
        GroceryListItem groceryListItem = mListItem.get(position);


        TextView groceryItemNameTextView = holder.groceryItemNameTextView;
        TextView groceryItemQuantityTextView = holder.groceryItemQuantityTextView;
        TextView groceryItemUnitTextView = holder.groceryItemUnitTextView;
        Button deleteItemButton = holder.deleteItemButton;
        Button changeItemQuantityAndUnitButton = holder.changeItemQuantityAndUnitButton;

        CheckBox isCheckedCheckBox = holder.isCheckedCheckBox;
        if (!groupedByType) {
            groceryItemNameTextView.setText((position + 1) + ". " + groceryListItem.getItem().getItemName());
        }
        else {
            groceryItemNameTextView.setText(groceryListItem.getItem().getItemType().getItemTypeName() + ":" + groceryListItem.getItem().getItemName());
        }

        if (groceryListItem.getQuantity() != 0) {
            groceryItemQuantityTextView.setText("QTY: " + Integer.toString(groceryListItem.getQuantity()));
            groceryItemUnitTextView.setText(groceryListItem.getQuantityUnit());
        } else {
            groceryItemQuantityTextView.setText("");
            groceryItemUnitTextView.setText("");
        }

        deleteItemButton.setText("Delete");
        deleteItemButton.setBackgroundColor(Color.rgb(248,131,121));
        changeItemQuantityAndUnitButton.setText("Edit");
        changeItemQuantityAndUnitButton.setBackgroundColor(Color.rgb(248,131,121));

        isCheckedCheckBox.setBackgroundColor(Color.BLACK);
        isCheckedCheckBox.setChecked(mListItem.get(position).isChecked());


    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public interface GroceryListItemClickListener {


        void onDeleteItemButtonClickListener(int position);

        void onIsCheckedCheckBoxClickListener(int position);

        void onChangeItemQuantityAndUnitButtonClickListener(int position);
    }

}


