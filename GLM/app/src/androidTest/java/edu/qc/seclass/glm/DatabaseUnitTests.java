package edu.qc.seclass.glm;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

/**
 * Instrumented test, which will execute on an Android device.
 * */
@RunWith(AndroidJUnit4.class)
public class DatabaseUnitTests {

    DatabaseManager DBListEditor;

    @Before //set up DatabaseManager
    public void createDb() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBListEditor = new DatabaseManager(appContext);
        DBListEditor.open();
    }

    @After //close DatabaseManager
    public void closeDb() {
        DBListEditor.close();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("edu.qc.seclass.glm", appContext.getPackageName());
    }

    @Test //Test if no lists exists when DatabaseManager first instantiated and opened
    public void noListTest(){
        DBListEditor.insertGroceryList("list1");
        DBListEditor.deleteAllGroceryLists();
        ArrayList emptyList = DBListEditor.getAllGroceryList();
        assertEquals(0, emptyList.size());
    }

    @Test //test whether list is created when empty list name is given
    public void emptyListNameTest(){
        DBListEditor.deleteAllGroceryLists();
        DBListEditor.insertGroceryList(null);
        assertEquals(0, DBListEditor.getAllGroceryList().size());
    }

    @Test //Test if more than one list can be stored in memory
    public void createMultipleListTest(){
        DBListEditor.insertGroceryList("list 1");
        DBListEditor.insertGroceryList("list 2");
        ArrayList fewLists = DBListEditor.getAllGroceryList();
        GroceryList list1 = (GroceryList) fewLists.get(0);
        GroceryList list2 = (GroceryList) fewLists.get(1);
        assertEquals(2, fewLists.size());
        assertEquals("list 1", ((GroceryList) fewLists.get(0)).getGroceryListName());
        assertEquals("list 2", ((GroceryList) fewLists.get(1)).getGroceryListName());
        DBListEditor.deleteAllGroceryLists();
    }

    @Test //Test to see if renaming works
    public void renameListTest(){
        DBListEditor.insertGroceryList("will be renamed");
        GroceryList rename = (GroceryList) DBListEditor.getAllGroceryList().get(0);
        assertEquals("will be renamed", rename.getGroceryListName());
        rename.setGroceryListName("new current name");
        assertEquals("new current name", rename.getGroceryListName());
        DBListEditor.deleteAllGroceryLists();
    }

    @Test //Test to see if one list out of a few are deleted
    public void deleteListTest(){
        DBListEditor.insertGroceryList("first list");
        DBListEditor.insertGroceryList("second list");
        DBListEditor.insertGroceryList("third list");
        GroceryList secondList = (GroceryList) DBListEditor.getAllGroceryList().get(1);
        long listID = secondList.getGroceryListId();
        DBListEditor.deleteGroceryListById(listID);
        ArrayList DBList = DBListEditor.getAllGroceryList();
        boolean foundDeletedID = false;
        for(int i = 0; i < DBList.size(); i++){
            GroceryList current = (GroceryList) DBList.get(i);
            long currID = current.getGroceryListId();
            if(currID == listID) foundDeletedID = true;
        }
        assertFalse(foundDeletedID);
        DBListEditor.deleteAllGroceryLists();
    }

    @Test //test if item exists in list after being added to it
    public void addItemToListTest(){
        DBListEditor.insertGroceryList("List1");
        GroceryList currList = (GroceryList) DBListEditor.getAllGroceryList().get(0);
        long currListID = currList.getGroceryListId();
        Item wonderBread = new Item(100, "Wonder Bread", 200, "Bread");
        GroceryListItem breadInList = new GroceryListItem(currListID, wonderBread, 1, "loaf", false);
        //DBListEditor.getItemByItemId()
        DBListEditor.insertGroceryListItem(currListID, wonderBread, 1, "1");
        //Check if list ID obtained from GroceryListItem is same as list made in first place.
        assertEquals(currListID, breadInList.getGroceryListId());
        //Check if Item object present in list is same as previously hardcoded Item object
        assertEquals(wonderBread, breadInList.getItem());
        DBListEditor.deleteAllGroceryLists();
    }

    @Test //test if item is no longer in list after being deleted
    public void deleteItemFromListTest(){
        DBListEditor.insertGroceryList("delete single item list");
        GroceryList currList = (GroceryList) DBListEditor.getAllGroceryList().get(0);
        long currListID = currList.getGroceryListId();
        Item wonderBread = new Item(100, "Wonder Bread", 200, "Bread");
        GroceryListItem breadInList = new GroceryListItem(currListID, wonderBread, 1, "loaf", false);
        DBListEditor.insertGroceryListItem(currListID, wonderBread, 1, "loaf");
        DBListEditor.deleteGroceryListItem(breadInList);
        ArrayList emptyList = DBListEditor.getGroceryListItemByGroceryListId((int) currListID);
        assertEquals(0, emptyList.size());
        DBListEditor.deleteAllGroceryLists();
    }

    @Test //Test if all items in a list can be deleted
    public void deleteAllItemsFromListTest(){
        DBListEditor.insertGroceryList("List1");
        GroceryList currList = (GroceryList) DBListEditor.getAllGroceryList().get(0);
        long currListID = currList.getGroceryListId();
        Item wonderBread = new Item(100, "Wonder Bread", 200, "Bread");
        Item onion = new Item(101, "Onion", 201, "Vegetable");
        DBListEditor.insertGroceryListItem(currListID, wonderBread, 1, "1");
        DBListEditor.insertGroceryListItem(currListID, onion, 2, "2");
        DBListEditor.deleteAllGroceryListItemFromGroceryList(currListID);
        ArrayList emptyList = DBListEditor.getGroceryListItemByGroceryListId((int) currListID);
        assertEquals(0, emptyList.size());
        DBListEditor.deleteAllGroceryLists();
    }

    @Test //Test if item in list can be edited
    public void editItemInListTest(){
        DBListEditor.insertGroceryList("first list");
        DBListEditor.insertGroceryList("second list");
        Item wonderBread = new Item(100, "Wonder Bread", 200, "Bread");
        Item onion = new Item(101, "Onion", 201, "Vegetable");
        GroceryList firstList  = (GroceryList) DBListEditor.getAllGroceryList().get(0);
        GroceryList secondList = (GroceryList) DBListEditor.getAllGroceryList().get(1);
        long firstID  = firstList.getGroceryListId();
        long secondID = secondList.getGroceryListId();
        GroceryListItem breadInList = new GroceryListItem(firstID, wonderBread, 1, "loaf", false);
        //Check values before setting new values to breadInList's fields
        assertEquals(wonderBread, breadInList.getItem());
        assertEquals(1, breadInList.getQuantity());
        assertEquals("loaf", breadInList.getQuantityUnit());
        assertFalse(breadInList.isChecked());
        //Change values of fields
        breadInList.setQuantity(2);
        breadInList.setChecked(true);
        breadInList.setQuantityUnit("bag");
        breadInList.setItem(onion);
        breadInList.setGroceryListId(secondID);
        //Check values of fields
        assertEquals(onion, breadInList.getItem());
        assertEquals(2, breadInList.getQuantity());
        assertEquals("bag", breadInList.getQuantityUnit());
        assertEquals(secondID, breadInList.getGroceryListId());
        assertTrue(breadInList.isChecked());
        DBListEditor.deleteAllGroceryLists();
    }
}