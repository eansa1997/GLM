package edu.qc.seclass.glm;

public class GroceryListItem {
    private long groceryListId;
    private Item item;
    private int quantity;
    private String quantityUnit;
    private boolean isChecked;

    public GroceryListItem() {

    }

    public GroceryListItem(long groceryListId, Item item, int quantity, String quantityUnit, boolean isChecked) {
        this.groceryListId = groceryListId;
        this.item = item;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.isChecked = isChecked;
    }

    public long getGroceryListId() {
        return groceryListId;
    }

    public void setGroceryListId(long groceryListId) {
        this.groceryListId = groceryListId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
