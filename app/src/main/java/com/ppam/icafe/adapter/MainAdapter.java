package com.ppam.icafe.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppam.icafe.R;
import com.ppam.icafe.model.MainModel;

import java.util.ArrayList;

public class MainAdapter extends ArrayAdapter<MainModel> {

    public MainAdapter(Activity context, ArrayList<MainModel> notification) {
        super(context, 0, notification);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_booking, parent, false);
        }

        MainModel current = getItem(position);

        TextView idBook = listItemView.findViewById(R.id.id_booking);
        idBook.setText("ID Menu : " + current.getIdBook());

        TextView judul = listItemView.findViewById(R.id.judul_list);
        judul.setText(current.getJudul());

        TextView riwayat = listItemView.findViewById(R.id.txt_harga);
        riwayat.setText("Rp. " + current.getHarga());

        ImageView imageIcon = listItemView.findViewById(R.id.image);

        if (current.hasImage()) {
            imageIcon.setImageResource(current.getImageResourceId());
            imageIcon.setVisibility(View.VISIBLE);
        } else {
            imageIcon.setVisibility(View.GONE);
        }

        return listItemView;
    }
}