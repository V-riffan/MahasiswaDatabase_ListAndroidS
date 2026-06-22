package com.mobile.uph24si3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    public FruitAdapter(@NonNull Context context, @NonNull List<Fruit> fruits) {
        super(context, 0, fruits);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_fruit, parent, false);
        }

        Fruit fruit = getItem(position);

        TextView tvIcon = convertView.findViewById(R.id.tvFruitIcon);
        TextView tvName = convertView.findViewById(R.id.tvFruitName);

        if (fruit != null) {
            tvIcon.setText(fruit.getEmoji());
            tvName.setText(fruit.getName());
        }

        return convertView;
    }
}
