package com.ppam.icafe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppam.icafe.R;

import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;

public class DetailActivity extends AppCompatActivity {

    String id;
    String judul, harga, deskripsi;
    int resourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        id = intent.getExtras().getString("id_book");
        judul = intent.getExtras().getString("nama");
        harga = intent.getExtras().getString("harga");
        deskripsi = intent.getExtras().getString("deskripsi");
        resourceId = intent.getExtras().getInt("detail_gambar");

        TextView Judul = findViewById(R.id.detail_judul);
        TextView Harga = findViewById(R.id.detail_harga);
        TextView Deskripsi = findViewById(R.id.detail_deskripsi);
        ImageView Gambar = findViewById(R.id.detail_gambar);


        Judul.setText(judul);
        Harga.setText("Rp. " + harga);
        Deskripsi.setText(deskripsi);


        switch (id){
            case "1":
                Gambar.setImageResource(R.drawable.nasigoreng);
                break;

            case "2" :
                Gambar.setImageResource(R.drawable.cheesecake);
                break;

            case "3":
                Gambar.setImageResource(R.drawable.batagor);
                break;

            case "4":
                Gambar.setImageResource(R.drawable.donut);
                break;

            case "5":
                Gambar.setImageResource(R.drawable.black_salad);
                break;

        }


    }
}