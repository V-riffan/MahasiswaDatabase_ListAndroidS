package com.mobile.uph24si3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvFruits;
    private List<Fruit> fruitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFruits = findViewById(R.id.lvFruits);
        
        initializeFruitList();

        FruitAdapter adapter = new FruitAdapter(this, fruitList);
        lvFruits.setAdapter(adapter);

        lvFruits.setOnItemClickListener((parent, view, position, id) -> {
            Fruit selectedFruit = fruitList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("FRUIT_DATA", selectedFruit);
            startActivity(intent);
        });
    }

    private void initializeFruitList() {
        fruitList = new ArrayList<>();

        fruitList.add(new Fruit("Nanas", "🍍", "Brasil & Paraguay", "Manis & Asam", 
                "Nanas adalah buah tropis yang lezat dan sehat, kaya akan vitamin C dan mangan.", 
                "- Meningkatkan kekebalan tubuh\n- Membantu pencernaan\n- Anti-inflamasi"));
        
        fruitList.add(new Fruit("Semangka", "🍉", "Afrika Selatan", "Manis & Segar", 
                "Semangka adalah buah yang mengandung banyak air, sangat menyegarkan di cuaca panas.", 
                "- Menjaga hidrasi\n- Baik untuk kesehatan jantung\n- Mengurangi nyeri otot"));

        fruitList.add(new Fruit("Melon", "🍈", "Persia & Afrika", "Manis & Lembut", 
                "Melon memiliki tekstur daging yang lembut dan rasa yang sangat manis.", 
                "- Baik untuk kesehatan mata\n- Menurunkan tekanan darah\n- Memperkuat tulang"));

        fruitList.add(new Fruit("Pepaya", "🥭", "Meksiko & Amerika Tengah", "Manis", 
                "Pepaya dikenal baik untuk pencernaan karena kandungan enzim papain-nya.", 
                "- Melancarkan pencernaan\n- Menyehatkan kulit\n- Sumber vitamin A"));

        fruitList.add(new Fruit("Durian", "🌳", "Asia Tenggara", "Manis & Legit", 
                "Durian dijuluki sebagai 'Raja Buah' karena aromanya yang kuat dan rasanya yang unik.", 
                "- Memberikan energi instan\n- Memperbaiki mood\n- Membantu tidur nyenyak"));

        fruitList.add(new Fruit("Rambutan", "🔴", "Malaysia & Indonesia", "Manis & Segar", 
                "Rambutan adalah buah tropis dari pohon Nephelium lappaceum. Namanya berasal dari kata rambut.", 
                "- Mendukung kesehatan tulang\n- Kaya zat besi mencegah anemia\n- Mengandung vitamin C"));

        fruitList.add(new Fruit("Leci", "🍒", "Tiongkok Selatan", "Manis & Wangi", 
                "Leci memiliki aroma harum yang khas dan daging buah yang transparan.", 
                "- Meningkatkan metabolisme\n- Menjaga tekanan darah\n- Antioksidan tinggi"));

        fruitList.add(new Fruit("Salak", "🟤", "Indonesia", "Manis & Sepat", 
                "Salak dikenal sebagai 'Snake Fruit' karena kulitnya yang bersisik seperti ular.", 
                "- Baik untuk kesehatan otak\n- Menjaga kesehatan mata\n- Membantu mengontrol gula darah"));

        fruitList.add(new Fruit("Jambu Biji", "🍏", "Amerika Tengah", "Manis & Harum", 
                "Jambu biji sangat kaya akan vitamin C, bahkan lebih tinggi dari jeruk.", 
                "- Meningkatkan imunitas\n- Baik untuk pencernaan\n- Menjaga kesehatan jantung"));

        fruitList.add(new Fruit("Kiwi", "🥝", "Tiongkok", "Manis & Sedikit Asam",
                "Kiwi adalah buah kecil yang padat nutrisi dengan biji hitam kecil yang bisa dimakan.", 
                "- Sangat tinggi vitamin C\n- Membantu kesehatan pernapasan\n- Menjaga kesehatan usus"));
    }
}
