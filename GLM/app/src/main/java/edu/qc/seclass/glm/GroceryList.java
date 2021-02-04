package edu.qc.seclass.glm;

public class GroceryList {
    private long groceryListId;
    private String groceryListName;

    public GroceryList() {

    }

    public GroceryList(long groceryListId, String groceryListName) {
        this.groceryListId = groceryListId;
        this.groceryListName = groceryListName;
    }

    public long getGroceryListId() {
        return groceryListId;
    }

    public void setGroceryListId(long groceryListId) {
        this.groceryListId = groceryListId;
    }

    public String getGroceryListName() {
        return groceryListName;
    }

    public void setGroceryListName(String groceryListName) {
        this.groceryListName = groceryListName;
    }
}
