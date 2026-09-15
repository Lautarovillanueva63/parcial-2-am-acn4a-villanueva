package com.example.reportesmicrocentro;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.graphics.Typeface;
import androidx.appcompat.app.AlertDialog;
import android.widget.ImageView;
import android.view.Gravity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout listaReportes;
    private Button botonReportar;
    private int contadorReportes = 3; // ya tenemos calle_1 y calle_2 en el XML

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listaReportes = findViewById(R.id.lista_reportes);
        botonReportar = findViewById(R.id.boton_reportar);
        botonReportar.setOnClickListener(v -> agregarReporte());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void agregarReporte() {
        String[] opciones = {"Control policial", "Lugar libre"};

        new AlertDialog.Builder(this)
                .setTitle("¿Qué querés reportar?")
                .setItems(opciones, (dialog, which) -> {
                    boolean esControl = which == 0;
                    crearTarjeta(esControl);
                })
                .show();
    }

    private void crearTarjeta(boolean esControl) {
        contadorReportes++;

        String[] callesControl = {
                getString(R.string.calle_1),
                getString(R.string.calle_3)
        };
        String[] callesLibre = {
                getString(R.string.calle_2),
                getString(R.string.calle_4)
        };

        String calleElegida = esControl
                ? callesControl[contadorReportes % callesControl.length]
                : callesLibre[contadorReportes % callesLibre.length];

        LinearLayout tarjeta = new LinearLayout(this);
        tarjeta.setOrientation(LinearLayout.HORIZONTAL);
        tarjeta.setBackgroundColor(getColor(esControl ? R.color.color_control : R.color.color_libre));
        tarjeta.setPadding(32, 24, 32, 24);
        tarjeta.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams paramsTarjeta = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsTarjeta.setMargins(0, 0, 0, 16);
        tarjeta.setLayoutParams(paramsTarjeta);

        ImageView icono = new ImageView(this);
        icono.setImageResource(esControl ? R.drawable.ic_control : R.drawable.ic_libre);
        icono.setColorFilter(getColor(R.color.white));
        LinearLayout.LayoutParams paramsIcono = new LinearLayout.LayoutParams(48, 48);
        paramsIcono.setMarginEnd(24);
        icono.setLayoutParams(paramsIcono);

        LinearLayout textos = new LinearLayout(this);
        textos.setOrientation(LinearLayout.VERTICAL);

        TextView tipo = new TextView(this);
        tipo.setText(esControl ? getString(R.string.tipo_control) : getString(R.string.tipo_libre));
        tipo.setTextColor(getColor(R.color.white));
        tipo.setTypeface(null, Typeface.BOLD);

        TextView calle = new TextView(this);
        calle.setText(calleElegida);
        calle.setTextColor(getColor(R.color.white));

        textos.addView(tipo);
        textos.addView(calle);

        tarjeta.addView(icono);
        tarjeta.addView(textos);
        listaReportes.addView(tarjeta);
    }
}