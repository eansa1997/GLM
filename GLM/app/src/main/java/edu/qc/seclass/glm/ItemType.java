package edu.qc.seclass.glm;

public class ItemType {
    private long itemTypeId;
    private String itemTypeName;

    public ItemType() {
    }

    public ItemType(long itemTypeId, String itemTypeName) {
        this.itemTypeId = itemTypeId;
        this.itemTypeName = itemTypeName;
    }

    public long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }
}
