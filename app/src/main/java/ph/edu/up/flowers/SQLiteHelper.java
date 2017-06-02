package ph.edu.up.flowers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Flowers.db";
    private SQLiteDatabase database;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_NAME = "FLOWERS";
    public static final String COLUMN_NAME = "ID";
    public static final String COLUMN_EASE = "FIRST_NAME";
    public static final String COLUMN_INSTRUCTIONS= "LAST_NAME";
    /*public static final String DB_LOCATION = "/data/data/ph.edu.up.flowers/databases";*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_EASE + " VARCHAR, " + COLUMN_INSTRUCTIONS + " VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecords(Flower flower) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, flower.getName());
        contentValues.put(COLUMN_EASE, flower.getEase());
        contentValues.put(COLUMN_INSTRUCTIONS, flower.getInstructions());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public void updateRecords(Flower flower) {
        database = getReadableDatabase();
        database.execSQL("UPDATE " + TABLE_NAME + " SET " + COLUMN_EASE + " = '" + flower.getEase() + "', "
                + COLUMN_INSTRUCTIONS + " = '" + flower.getInstructions() + "' WHERE " + COLUMN_NAME + " = '" + flower.getName());
        database.close();
    }

    public void deleteRecords(Flower flower) {
        database = getReadableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + flower.getName() + "'");
        database.close();
    }

    public ArrayList<Flower> getAllRecords() {
        database = getReadableDatabase();
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
