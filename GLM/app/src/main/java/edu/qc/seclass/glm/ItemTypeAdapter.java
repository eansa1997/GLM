package edu.qc.seclass.glm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// This class is the adapter which will populate the view in the Activity in which it is called with the Item Types.
// It also specifies the layout of each Item Type, populating the layout with the TextViews and Buttons
/*
author: Shmuel Halbfinger
 */
public class ItemTypeAdapter extends RecyclerView.Adapter<ItemTypeAdapter.ViewHolder> {
    private ArrayList<ItemType> itemTypes;

    private ItemTypeClickListener mItemTypeClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemTypeNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTypeNameTextView = itemView.findViewById(R.id.itemTypeNameTextView);

            itemTypeNameTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mItemTypeClickListener.onItemTypeClick(position);
        }
    }

    public ItemTypeAdapter(ArrayList<ItemType> itemTypes, ItemTypeClickListener listener) {
        this.itemTypes = itemTypes;
        mItemTypeClickListener = listener;
    }

    @NonNull
    @Override
    public ItemTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemTypeView = inflater.inflate(R.layout.item_type, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemTypeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemType itemType = itemTypes.get(position);

        TextView itemTypeNameTextView = holder.itemTypeNameTextView;

        itemTypeNameTextView.setText(itemType.getItemTypeId() + "-" + itemType.getItemTypeName());

    }

    @Override
    public int getItemCount() {
        return itemTypes.size();
    }

    public interface ItemTypeClickListener {
        void onItemTypeClick(int position);
    }
}
