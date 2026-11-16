package com.example.reportesmicrocentro;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ImageView fotoPerfil = findViewById(R.id.foto_perfil);
        String urlFoto = "https://i.pravatar.cc/300";

        Glide.with(this)
                .load(urlFoto)
                .circleCrop()
                .into(fotoPerfil);
    }
}