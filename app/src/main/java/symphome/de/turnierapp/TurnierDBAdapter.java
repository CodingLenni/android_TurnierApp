package symphome.de.turnierapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TurnierDBAdapter {

    public static final String TABLE_TURNIER = "turnier";

    public static final String KEY = "_id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE_URI = "image_uri";

    private static final String DATABASE_NAME = "turniere.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TURNIER_CREATE =
            "create table " + TABLE_TURNIER +
                    "(" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_DESCRIPTION + " text not null," +
                    COLUMN_IMAGE_URI + " text);";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_TURNIER_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newverison) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TURNIER);
            onCreate(db);
        }
    }

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public TurnierDBAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public TurnierDBAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor query(
            String[] projection, String selection,
            String[] selectionArgs, String groupBy,
            String having, String orderBy) {

        return db.query(TABLE_TURNIER, projection, selection, selectionArgs, groupBy, having, orderBy);
    }

    public long insert(String description, String imageUri) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_IMAGE_URI, imageUri);

        return db.insert(TABLE_TURNIER, null, values);
    }

    public boolean update(String selection, String[] selectionArgs, ContentValues values) {
        return db.update(TABLE_TURNIER, values, selection, selectionArgs) > 0;
    }

    public boolean delete(String selection, String[] selectionArgs) {
        return db.delete(TABLE_TURNIER, selection, selectionArgs) > 0;
    }


}
