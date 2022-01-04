package com.ppam.icafe.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ppam.icafe.R;
import com.ppam.icafe.model.MainModel;
import com.ppam.icafe.adapter.MainAdapter;
import com.ppam.icafe.database.DatabaseHelper;
import com.ppam.icafe.session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    protected Cursor cursor;
    protected Cursor nama;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    SessionManager session;
    String id_book = "", menu, harga, deskripsi, kelas, jam, riwayat;
    String email;
    TextView tvNotFound;
    ImageButton btnLogout;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        tvNotFound = findViewById(R.id.noHistory);

        session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();

        email = user.get(SessionManager.KEY_EMAIL);


        refreshList();
        setupToolbar();

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Anda yakin ingin keluar ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                session.logoutUser();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .create();
                dialog.show();
            }
        });

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbHistory);
        toolbar.setTitle("Daftar Menu");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(null);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refreshList() {
        final ArrayList<MainModel> Menu = new ArrayList<>();
//        cursor = db.rawQuery("SELECT * FROM TB_BOOK, TB_COURSE WHERE TB_BOOK.id_book = TB_COURSE.id_book AND username='" + harga + "'", null);
        cursor = db.rawQuery("SELECT DISTINCT * FROM TB_BOOK GROUP BY id_book", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            id_book = cursor.getString(0);
            menu = cursor.getString(1);
            harga = cursor.getString(2);
            deskripsi = cursor.getString(3);

            switch (id_book){
                case "1" :
                    Menu.add(new MainModel(id_book, menu, harga, deskripsi, R.drawable.nasigoreng));
                    break;

                case "2" :
                    Menu.add(new MainModel(id_book, menu, harga, deskripsi, R.drawable.cheesecake));
                    break;

                case "3" :
                    Menu.add(new MainModel(id_book, menu, harga, deskripsi, R.drawable.batagor));
                    break;

                case "4" :
                    Menu.add(new MainModel(id_book, menu, harga, deskripsi, R.drawable.donut));
                    break;

                case "5" :
                    Menu.add(new MainModel(id_book, menu, harga, deskripsi, R.drawable.black_salad));
                    break;
            }


//            Menu.add(new MainModel(id_book, menu, harga, deskripsi, R.drawable.profile));
        }

        nama = db.rawQuery("SELECT * FROM TB_USER WHERE username = '" + email + "'", null);
        nama.moveToFirst();
        if (nama.getCount() > 0) {
            nama.moveToPosition(0);
            name = nama.getString(2);
        }

        TextView username = findViewById(R.id.txt_username);
        username.setText(name);



        ListView listBook = findViewById(R.id.list_booking);
        MainAdapter arrayAdapter = new MainAdapter(this, Menu);
        listBook.setAdapter(arrayAdapter);

        //delete data
        listBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String selection = Menu.get(i).getIdBook();
                final String nama = Menu.get(i).getJudul();
                final String detail_harga = Menu.get(i).getHarga();
                final String detail_deskripsi = Menu.get(i).getDeskripsi();
                final int detail_gambar = Menu.get(i).getImageResourceId();



                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("id_book", selection);
                intent.putExtra("nama", nama);
                intent.putExtra("harga", detail_harga);
                intent.putExtra("deskripsi", detail_deskripsi);
//                view.getContext().startActivity(intent);
                startActivity(intent);
            }
        });

//        if (id_book.equals("")) {
//            tvNotFound.setVisibility(View.VISIBLE);
//            listBook.setVisibility(View.GONE);
//        } else {
//            tvNotFound.setVisibility(View.GONE);
//            listBook.setVisibility(View.VISIBLE);
//        }

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Apakah Anda ingin keluar dari aplikasi?");
        builder.setCancelable(true);
        builder.setNegativeButton(getString(R.string.batal), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(getString(R.string.keluar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
