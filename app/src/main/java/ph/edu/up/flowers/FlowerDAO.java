package ph.edu.up.flowers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FlowerDAO {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    public static final String TABLE_NAME = "FLOWERS";
    public static final String COLUMN_NAME = "ID";
    public static final String COLUMN_EASE = "FIRST_NAME";
    public static final String COLUMN_INSTRUCTIONS= "LAST_NAME";

    public FlowerDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertRecords(Flower flower) {
        /*database = this.getReadableDatabase();*/
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, flower.getName());
        contentValues.put(COLUMN_EASE, flower.getEase());
        contentValues.put(COLUMN_INSTRUCTIONS, flower.getInstructions());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public void updateRecords(Flower flower) {
        /*database = getReadableDatabase();*/
        database.execSQL("UPDATE " + TABLE_NAME + " SET " + COLUMN_EASE + " = '" + flower.getEase() + "', "
                + COLUMN_INSTRUCTIONS + " = '" + flower.getInstructions() + "' WHERE " + COLUMN_NAME + " = '" + flower.getName());
        database.close();
    }

    public void deleteRecords(Flower flower) {
        /*database = getReadableDatabase();*/
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + flower.getName() + "'");
        database.close();
    }

    public ArrayList<Flower> getAllRecords() {
        /*database = getReadableDatabase();*/
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Flower> flowerArrayList = new ArrayList<Flower>();
        Flower flower;

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                flower = new Flower();
                flower.setName(cursor.getString(0));
                flower.setEase(cursor.getString(1));
                flower.setInstructions(cursor.getString(2));
                flowerArrayList.add(flower);
            }
        }
        cursor.close();
        database.close();
        return flowerArrayList;
    }
}
