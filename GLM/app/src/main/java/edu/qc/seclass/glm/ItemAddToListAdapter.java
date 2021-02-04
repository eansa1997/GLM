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

// This class is the adapter which will populate the view in the Activity in which it is called with the Items to add to the list.
// It also specifies the layout of each Item, populating the layout with the TextViews and Buttons
/*
author: Shmuel Halbfinger
 */
public class ItemAddToListAdapter extends RecyclerView.Adapter<ItemAddToListAdapter.ViewHolder> {
    private ArrayList<Item> items;

    private ItemAddToListClickListener mItemAddToListClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemAddToListTextView;
        public Button itemAddToListButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemAddToListTextView = itemView.findViewById(R.id.itemAddToListTextview);

            itemAddToListButton = itemView.findViewById(R.id.itemAddToListButton);
            itemAddToListButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mItemAddToListClickListener.onItemAddToListClick(position);
        }
    }

    public ItemAddToListAdapter(ArrayList<Item> items, ItemAddToListAdapter.ItemAddToListClickListener listener) {
        this.items = items;
        mItemAddToListClickListener = listener;
    }

    @NonNull
    @Override
    public ItemAddToListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemAddToListView = inflater.inflate(R.layout.item_add_to_list, parent, false);

        ItemAddToListAdapter.ViewHolder viewHolder = new ItemAddToListAdapter.ViewHolder(itemAddToListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAddToListAdapter.ViewHolder holder, int position) {
        Item item = items.get(position);

        TextView itemAddToListTextView = holder.itemAddToListTextView;

        itemAddToListTextView.setText((position + 1) + ". " + item.getItemName());

        Button itemAddToListButton = holder.itemAddToListButton;

        itemAddToListButton.setText("Add item to List");
        itemAddToListButton.setBackgroundColor(Color.rgb(248,131,121));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ItemAddToListClickListener {
        void onItemAddToListClick(int position);
    }
}
