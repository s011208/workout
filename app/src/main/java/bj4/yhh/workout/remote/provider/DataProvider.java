package bj4.yhh.workout.remote.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import bj4.yhh.workout.data.ScheduleDate;
import bj4.yhh.workout.data.TrainData;

/**
 * Created by User on 2016/5/4.
 */
public class DataProvider extends ContentProvider {
    private SQLiteDatabase mDatabase;

    private static final String AUTHORITY = "bj4.yhh.workout.remote.provider.DataProvider";

    private static final String PATTERN_TRAIN_DATA = Database.TABLE_TRAIN_DATA;
    private static final String PATTERN_SCHEDULE_DATE = Database.TABLE_SCHEDULE_DATE;

    private static final int CODE_TRAIN_DATA = 1;
    private static final int CODE_SCHEDULE_DATE = 2;

    public static final Uri URI_TRAIN_DATA = Uri.parse("content://" + AUTHORITY + "/" + PATTERN_TRAIN_DATA);
    public static final Uri URI_SCHEDULE_DATE = Uri.parse("content://" + AUTHORITY + "/" + PATTERN_SCHEDULE_DATE);

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, PATTERN_TRAIN_DATA, CODE_TRAIN_DATA);
        sUriMatcher.addURI(AUTHORITY, PATTERN_SCHEDULE_DATE, CODE_SCHEDULE_DATE);
    }

    @Override
    public boolean onCreate() {
        mDatabase = new Database(getContext()).getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (sUriMatcher.match(uri)) {
            case CODE_TRAIN_DATA:
                return mDatabase.query(Database.TABLE_TRAIN_DATA, projection, selection, selectionArgs, sortOrder, null, null);
            case CODE_SCHEDULE_DATE:
                return mDatabase.query(Database.TABLE_SCHEDULE_DATE, projection, selection, selectionArgs, sortOrder, null, null);

        }
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
        switch (sUriMatcher.match(uri)) {
            case CODE_TRAIN_DATA:
                return ContentUris.withAppendedId(uri, mDatabase.insert(Database.TABLE_TRAIN_DATA, null, values));
            case CODE_SCHEDULE_DATE:
                return ContentUris.withAppendedId(uri, mDatabase.insert(Database.TABLE_SCHEDULE_DATE, null, values));

        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case CODE_TRAIN_DATA:
                return mDatabase.delete(Database.TABLE_TRAIN_DATA, selection, selectionArgs);
            case CODE_SCHEDULE_DATE:
                return mDatabase.delete(Database.TABLE_SCHEDULE_DATE, selection, selectionArgs);
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case CODE_TRAIN_DATA:
                return mDatabase.update(Database.TABLE_TRAIN_DATA, values, selection, selectionArgs);
            case CODE_SCHEDULE_DATE:
                return mDatabase.update(Database.TABLE_SCHEDULE_DATE, values, selection, selectionArgs);
        }
        return 0;
    }

    public static class Database extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "data.db";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_TRAIN_DATA = "train_data";
        private static final String TABLE_SCHEDULE_DATE = "schedule_date";

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
                    + TrainData.UNIT + " TEXT NOT NULL,"
                    + TrainData.TRAIN_IMAGE_SOURCE + " TEXT,"
                    + TrainData.TRAIN_TITLE + " TEXT NOT NULL,"
                    + TrainData.STATUS + " INTEGER)");
        }

        private static void createScheduleDateTable(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + TABLE_SCHEDULE_DATE + " ("
                    + ScheduleDate.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ScheduleDate.TRAIN_DATA_ID + " INTEGER,"
                    + ScheduleDate.YEAR + " INTEGER,"
                    + ScheduleDate.MONTH + " INTEGER,"
                    + ScheduleDate.DAY + " INTEGER)");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTrainDataTable(db);
            createScheduleDateTable(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
