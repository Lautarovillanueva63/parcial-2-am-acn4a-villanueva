package com.example.reportesmicrocentro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleReporteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reporte);

        String tipo = getIntent().getStringExtra("tipo");
        String calle = getIntent().getStringExtra("calle");
        boolean esControl = getIntent().getBooleanExtra("esControl", true);

        ImageView icono = findViewById(R.id.icono_detalle);
        TextView tipoDetalle = findViewById(R.id.tipo_detalle);
        TextView calleDetalle = findViewById(R.id.calle_detalle);
        Button botonConfirmar = findViewById(R.id.boton_confirmar_vigencia);

        icono.setImageResource(esControl ? R.drawable.ic_control : R.drawable.ic_libre);
        icono.setColorFilter(getColor(esControl ? R.color.color_control : R.color.color_libre));
        tipoDetalle.setText(tipo);
        calleDetalle.setText(calle);

        botonConfirmar.setOnClickListener(v ->
                Toast.makeText(this, "Gracias por confirmar que " + tipo + " sigue vigente", Toast.LENGTH_SHORT).show());
    }
}