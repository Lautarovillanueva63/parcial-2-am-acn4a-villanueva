package com.example.reportesmicrocentro;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private LinearLayout listaReportes;
    private Button botonReportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            auth.signInAnonymously()
                    .addOnSuccessListener(result ->
                            android.util.Log.d("FirebaseAuth", "Usuario autenticado: " + result.getUser().getUid()))
                    .addOnFailureListener(e ->
                            android.util.Log.e("FirebaseAuth", "Error al autenticar", e));}

        listaReportes = findViewById(R.id.lista_reportes);
        botonReportar = findViewById(R.id.boton_reportar);
        botonReportar.setOnClickListener(v -> agregarReporte());

        ImageView botonPerfil = findViewById(R.id.boton_perfil);
        botonPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        LinearLayout tarjetaControlFija = findViewById(R.id.tarjeta_control_fija);
        tarjetaControlFija.setOnClickListener(v ->
                abrirDetalle(true, getString(R.string.calle_1)));

        LinearLayout tarjetaLibreFija = findViewById(R.id.tarjeta_libre_fija);
        tarjetaLibreFija.setOnClickListener(v ->
                abrirDetalle(false, getString(R.string.calle_2)));
    }

    private void abrirDetalle(boolean esControl, String calle) {
        Intent intent = new Intent(MainActivity.this, DetalleReporteActivity.class);
        intent.putExtra("tipo", esControl ? getString(R.string.tipo_control) : getString(R.string.tipo_libre));
        intent.putExtra("calle", calle);
        intent.putExtra("esControl", esControl);
        startActivity(intent);
    }

    private void agregarReporte() {
        String[] opciones = {"Control policial", "Lugar libre"};
        new AlertDialog.Builder(this)
                .setTitle("¿Qué querés reportar?")
                .setItems(opciones, (dialog, which) -> {
                    boolean esControl = which == 0;
                    mostrarSelectorCalle(esControl);
                }).show();
    }

    private void mostrarSelectorCalle(boolean esControl) {
        String[] calles = esControl
                ? new String[]{ getString(R.string.calle_1), getString(R.string.calle_3) }
                : new String[]{ getString(R.string.calle_2), getString(R.string.calle_4) };

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_seleccionar_calle, null);
        Spinner spinner = vista.findViewById(R.id.spinner_calles);
        spinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, calles));

        new AlertDialog.Builder(this)
                .setTitle("¿En qué calle?")
                .setView(vista)
                .setPositiveButton("Reportar", (dialog, which) -> {
                    String calle = (String) spinner.getSelectedItem();
                    crearTarjeta(esControl, calle);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void crearTarjeta(boolean esControl, String calle) {
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

        TextView calleView = new TextView(this);
        calleView.setText(calle);
        calleView.setTextColor(getColor(R.color.white));

        textos.addView(tipo);
        textos.addView(calleView);

        tarjeta.addView(icono);
        tarjeta.addView(textos);

        tarjeta.setOnClickListener(v -> abrirDetalle(esControl, calle));

        listaReportes.addView(tarjeta);
    }
}