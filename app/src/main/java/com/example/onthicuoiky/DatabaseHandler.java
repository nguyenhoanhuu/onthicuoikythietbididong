package com.example.onthicuoiky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quanlycongviec";
    private static final String TABLE_DANHSACHCONGVIEC = "danhSachCongViec";
    private static final String KEY_ID = "id";
    private static final String KEY_TEN_CONG_VIEC = "tenCongViec";
    private static final String KEY_MUC_DO = "mucDo";
    private static final String KEY_NGAY = "ngay";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DANHSACHCONGVIEC + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TEN_CONG_VIEC + " TEXT,"
                + KEY_MUC_DO + " TEXT," + KEY_NGAY + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DANHSACHCONGVIEC);

        onCreate(db);
    }

    void addCongViec(CongViec congViec) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEN_CONG_VIEC, congViec.getTenCongViec());
        values.put(KEY_MUC_DO, congViec.getMucDo());
        values.put(KEY_NGAY, new Date().toString());


        db.insert(TABLE_DANHSACHCONGVIEC, null, values);
        db.close();
    }

    public List<CongViec> getAllDanhsachCongViec() {
        List<CongViec> danhSachCongViec = new ArrayList<CongViec>();
        String selectQuery = "SELECT  * FROM " + TABLE_DANHSACHCONGVIEC;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CongViec congViec = new CongViec();
                congViec.set_id(Integer.parseInt(cursor.getString(0)));
                congViec.setTenCongViec(cursor.getString(1));
                congViec.setMucDo(cursor.getString(2));
                congViec.setNgay(new Date());
                danhSachCongViec.add(congViec);
            } while (cursor.moveToNext());
        }

        return danhSachCongViec;
    }
}
