package com.mobile.uph24si3;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView btnBack = findViewById(R.id.btnBack);
        TextView tvFruitEmoji = findViewById(R.id.tvDetailFruitEmoji);
        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvFruitName = findViewById(R.id.tvDetailFruitName);
        TextView tvOrigin = findViewById(R.id.tvDetailOrigin);
        TextView tvTaste = findViewById(R.id.tvDetailTaste);
        TextView tvDescription = findViewById(R.id.tvDetailDescription);
        TextView tvBenefits = findViewById(R.id.tvDetailBenefits);

        Fruit fruit = (Fruit) getIntent().getSerializableExtra("FRUIT_DATA");

        if (fruit != null) {
            tvDetailTitle.setText("Detail: " + fruit.getName());
            tvFruitName.setText(fruit.getName());
            tvFruitEmoji.setText(fruit.getEmoji());
            tvOrigin.setText(fruit.getOrigin());
            tvTaste.setText(fruit.getTaste());
            tvDescription.setText(fruit.getDescription());
            tvBenefits.setText(fruit.getBenefits());
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
