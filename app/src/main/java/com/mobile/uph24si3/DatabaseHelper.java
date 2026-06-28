package com.mobile.uph24si3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MahasiswaDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "mahasiswa";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NIM = "nim";
    private static final String COLUMN_NAMA = "nama";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NIM + " TEXT, " +
                COLUMN_NAMA + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addMahasiswa(String nim, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NIM, nim);
        values.put(COLUMN_NAMA, nama);
        return db.insert(TABLE_NAME, null, values);
    }

    public int updateMahasiswa(int id, String nim, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NIM, nim);
        values.put(COLUMN_NAMA, nama);
        return db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public int deleteMahasiswa(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public List<Mahasiswa> getAllMahasiswa(String searchQuery, String sortOrder) {
        List<Mahasiswa> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            selectQuery += " WHERE " + COLUMN_NAMA + " LIKE '%" + searchQuery + "%' OR " +
                    COLUMN_NIM + " LIKE '%" + searchQuery + "%'";
        }
        
        if (sortOrder != null) {
            selectQuery += " ORDER BY " + COLUMN_NAMA + " " + sortOrder;
        }

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Mahasiswa m = new Mahasiswa();
                m.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                m.setNim(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIM)));
                m.setNama(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)));
                list.add(m);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
