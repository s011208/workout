package bj4.yhh.workout.remote.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import bj4.yhh.workout.data.TrainData;

/**
 * Created by User on 2016/5/4.
 */
public class DataProvider extends ContentProvider {
    private SQLiteDatabase mDatabase;

    @Override
    public boolean onCreate() {
        mDatabase = new Database(getContext()).getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    public static class Database extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "data.db";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_TRAIN_DATA = "train_data";

        public Database(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        private static void createTrainDataTable(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + TABLE_TRAIN_DATA + " ("
                    + TrainData.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TrainData.INTENSITY_DATA + " TEXT NOT NULL,"
                    + TrainData.LAP_TIME + " TEXT,"
                    + TrainData.TOTAL_TIME + " TEXT,"
                    + TrainData.TRAIN_IMAGE_SOURCE + " TEXT,"
                    + TrainData.TRAIN_TITLE + " TEXT NOT NULL,"
                    + TrainData.STATUS + " INTEGER)");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTrainDataTable(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
