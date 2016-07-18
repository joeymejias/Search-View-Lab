package ly.generalassemb.drewmahrt.shoppinglistwithsearch;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ShoppingSQLiteOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = ShoppingSQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String SHOPPING_LIST_TABLE_NAME = "SHOPPING_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_ITEM_PRICE = "PRICE";
    public static final String COL_ITEM_DESCRIPTION = "DESCRIPTION";
    public static final String COL_ITEM_TYPE = "TYPE";

    private static ShoppingSQLiteOpenHelper sInstance;


    public static final String[] SHOPPING_COLUMNS = {COL_ID,COL_ITEM_NAME,COL_ITEM_DESCRIPTION,COL_ITEM_PRICE,COL_ITEM_TYPE};

    private static final String CREATE_SHOPPING_LIST_TABLE =
            "CREATE TABLE IF NOT EXISTS" + SHOPPING_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ITEM_NAME + " TEXT, " +
                    COL_ITEM_DESCRIPTION + " TEXT, " +
                    COL_ITEM_PRICE + " REAL, " +
                    COL_ITEM_TYPE + " TEXT )";

    private ShoppingSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ShoppingSQLiteOpenHelper  getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ShoppingSQLiteOpenHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public GroceryListItem getGroceryItem(){
        GroceryListItem groceryListItem = new GroceryListItem();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + SHOPPING_LIST_TABLE_NAME +" WHERE "+ COL_ID +" = " + 1, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                groceryListItem.setId(c.getInt(0));
                groceryListItem.setName(c.getString(1));
                groceryListItem.setDescription(c.getString(2));
                groceryListItem.setPrice(c.getDouble(3));
                groceryListItem.setType(c.getString(4));
                c.moveToNext();
            }
        }
        c.close();
        db.close();

        return groceryListItem;
    }
    public List<GroceryListItem> fullGroceryList(){
        List<GroceryListItem> items = new ArrayList<GroceryListItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + SHOPPING_LIST_TABLE_NAME, null);

        if (c.moveToFirst()){
            while (!c.isAfterLast()) {
                GroceryListItem groceryListItem = new GroceryListItem();
                groceryListItem.setId(c.getInt(0));
                groceryListItem.setName(c.getString(1));
                groceryListItem.setDescription(c.getString(2));
                groceryListItem.setPrice(c.getDouble(3));
                groceryListItem.setType(c.getString(4));
                items.add(groceryListItem);
                c.moveToNext();
            }
        }
        db.close();
        c.close();

        return items;
    }

    public List<GroceryListItem> getNames(String query){
        List<GroceryListItem> items = new ArrayList<GroceryListItem>();
        Cursor c = searchNames(query);
        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                GroceryListItem groceryListItem = new GroceryListItem();
                groceryListItem.setId(c.getInt(0));
                groceryListItem.setName(c.getString(1));
                groceryListItem.setDescription(c.getString(2));
                groceryListItem.setPrice(c.getDouble(3));
                groceryListItem.setType(c.getString(4));
                items.add(groceryListItem);
                c.moveToNext();
            }
        }

        c.close();

        return items;
    }

    public Cursor searchNames(String query){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + SHOPPING_LIST_TABLE_NAME + " WHERE " + COL_ITEM_NAME + " LIKE " + "'%" + query + "%';",null);
        return c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SHOPPING_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SHOPPING_LIST_TABLE_NAME);
        this.onCreate(db);
    }
}
