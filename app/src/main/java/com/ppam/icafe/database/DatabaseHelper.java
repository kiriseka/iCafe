package com.ppam.icafe.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_icourse";
    public static final String TABLE_USER = "tb_user";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NAME = "name";


    public static final String TABLE_MENU = "tb_book";
    public static final String COL_ID_BOOK = "id_book";
    public static final String COL_MENU = "menu";
    public static final String COL_HARGA = "harga";
    public static final String COL_DESKRIPSI = "deskripsi";


    public static final String TABLE_COURSE = "tb_course";
    public static final String COL_KELAS_COURSE = "kelas_course";
    public static final String COL_JAM_COURSE = "jam_course";
    public static final String COL_HARGA_TOTAL = "harga_total";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table " + TABLE_USER + " (" + COL_USERNAME + " TEXT PRIMARY KEY, " + COL_PASSWORD +
                " TEXT, " + COL_NAME + " TEXT)");
        db.execSQL("create table " + TABLE_COURSE + " (" + COL_USERNAME + " TEXT, " + COL_ID_BOOK + " INTEGER, " +
                COL_KELAS_COURSE + " TEXT, " + COL_JAM_COURSE + " TEXT, " + COL_HARGA_TOTAL +
                " TEXT, FOREIGN KEY(" + COL_USERNAME + ") REFERENCES " + TABLE_USER
                + ", FOREIGN KEY(" + COL_ID_BOOK + ") REFERENCES " + TABLE_MENU + ")");
        db.execSQL("insert into " + TABLE_USER + " values ('tes@gmail.com','tes','Test Account');");


//        Memasukkan data Menu

//        db.execSQL("DROP TABLE " + TABLE_MENU);

        db.execSQL("create table " + TABLE_MENU + " (" + COL_ID_BOOK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MENU + " TEXT, " + COL_HARGA + " TEXT" + ", " + COL_DESKRIPSI + " TEXT)");


//        db.execSQL("insert into " + TABLE_BOOK + " values ('','Keju','Cobain','2','t','5');");
        db.execSQL("INSERT INTO TB_BOOK (menu, harga, deskripsi) VALUES ('" +
                "Nasi Goreng" + "','" +
                "1500" + "','" +
                "Nasi goreng adalah sebuah makanan berupa nasi yang digoreng dan diaduk dalam minyak goreng, margarin, atau mentega. Biasanya ditambah kecap manis, bawang merah, bawang putih, asam jawa, lada dan bumbu-bumbu lainnya; seperti telur, ayam, dan kerupuk." + "');");

        db.execSQL("INSERT INTO TB_BOOK (menu, harga, deskripsi) VALUES ('" +
                "Cheesecake" + "','" +
                "50000" + "','" +
                "Cheesecake (bahasa Indonesia: \"kue keju\") adalah kue yang biasanya dimakan sebagai hidangan penutup, dibuat dengan mencampurkan keju yang bertekstur lembut, telur, susu, dan gula." + "');");

        db.execSQL("INSERT INTO TB_BOOK (menu, harga, deskripsi) VALUES ('" +
                "Batagor" + "','" +
                "500000" + "','" +
                "Secara umum, batagor dibuat dari tahu yang dilembutkan dan diisi dengan adonan berbahan ikan tenggiri dan tepung tapioka lalu dibentuk menyerupai bola yang digoreng dalam minyak panas selama beberapa menit hingga matang" + "');");

        db.execSQL("INSERT INTO TB_BOOK (menu, harga, deskripsi) VALUES ('" +
                "Donat" + "','" +
                "80000" + "','" +
                "Donat (doughnut atau donut dalam bahasa Inggiriah) adolah makanan barupo roti nan dipanggang, adonan tepung terigu, gula, telur, dan mentega. Donat nan paliang umum adolah donat nan babantuak cincin nan balubang di tangah sarato donat nan babantuak bundar nan mamiliki isian manis, sarupo salai, jally, krim, tapuang gulo." + "');");

        db.execSQL("INSERT INTO TB_BOOK (menu, harga, deskripsi) VALUES ('" +
                "Black Salad" + "','" +
                "20000" + "','" +
                "Black salad merupakan salad berwarna hitam dilengkapi dengan charcoal yang memiliki fungsi yang baik untuk tubuh kita." + "');");


//        db.execSQL("DELETE FROM TB_BOOK WHERE id_book LIKE 1 LIMIT 1");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public boolean Register(String username, String password, String name) throws SQLException {

        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("INSERT INTO " + TABLE_USER + "(" + COL_USERNAME + ", " + COL_PASSWORD + ", " + COL_NAME + ") VALUES (?,?,?)", new String[]{username, password, name});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

    public boolean Login(String username, String password) throws SQLException {
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

}