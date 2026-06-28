package com.mobile.uph24si3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MahasiswaAdapter extends BaseAdapter {
    private Context context;
    private List<Mahasiswa> list;

    public MahasiswaAdapter(Context context, List<Mahasiswa> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_mahasiswa, parent, false);
        }

        Mahasiswa m = list.get(position);

        TextView tvNama = convertView.findViewById(R.id.tvItemNama);
        TextView tvNim = convertView.findViewById(R.id.tvItemNim);
        TextView tvId = convertView.findViewById(R.id.tvItemId);

        tvNama.setText(m.getNama());
        tvNim.setText("NIM: " + m.getNim());
        tvId.setText("ID: " + m.getId());

        return convertView;
    }
}
