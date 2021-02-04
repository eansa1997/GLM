package edu.qc.seclass.glm;

public class Item {
    private long itemId;
    private String itemName;
    private ItemType itemType;

    public Item(long itemId, String itemName, ItemType itemType) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public Item(long itemId, String itemName, long itemTypeId, String itemTypeName) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = new ItemType(itemTypeId, itemTypeName);
    }

    public Item() {

    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
