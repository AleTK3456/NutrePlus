package skibidimamei.nutreplus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    private EditText edCorreo, edUser, edPass;
    private Button btCrearcuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Poner @gmail cuando se toca la linea de ingreso de correo
        EditText etcorreo = findViewById(R.id.etEmail);

        etcorreo.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                String currentText = etcorreo.getText().toString();

                // Si el texto no contiene "@gmail.com" ni otros dominios, añadirlo automáticamente
                if (!currentText.contains("@gmail.com")) {
                    etcorreo.setText(currentText + "@gmail.com");

                    // Mueve el cursor al principio para que el usuario escriba su nombre de usuario
                    etcorreo.setSelection(currentText.length());
                }
            }
        });

        edCorreo = findViewById(R.id.etEmail);
        edUser = findViewById(R.id.etUser);
        edPass = findViewById(R.id.etPass);

        btCrearcuenta = findViewById(R.id.btnCrearCuenta);

        btCrearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearCuentaNueva();
            }
        });
    }

    public void CrearCuentaNueva(){
        try {
            String gmail = edCorreo.getText().toString();
            String usuario = edUser.getText().toString();
            String contraseña = edPass.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_USUARIOS", Context.MODE_PRIVATE, null);
            // Abre o crea la base de datos llamada "BD_USUARIOS" en modo privado.

            db.execSQL("CREATE TABLE IF NOT EXISTS Usuarios(id INTEGER PRIMARY KEY AUTOINCREMENT, gmail VARCHAR, usuario VARCHAR, contraseña VARCHAR)");
            // crea la tabla usuarios

            String sql = "insert into usuarios(gmail, usuario, contraseña) values(?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            // Prepara una instrucción SQL para insertar los valores de gmail, usuario y contraseña en la tabla "usuarios"

            statement.bindString(1, gmail);
            statement.bindString(2, usuario);
            statement.bindString(3, contraseña);
            // Vincula los valores ingresados por el usuario a los parámetros de la instrucción SQL

            statement.execute();
            // Ejecuta la instrucción SQL para insertar el registro en la base de datos

            Toast.makeText(this,"Usuario agregado correctamente", Toast.LENGTH_LONG).show();
            // Muestra un mensaje al usuario indicando que los datos fueron agregados correctamente

            edCorreo.setText("");
            edUser.setText("");
            edPass.setText("");
            edCorreo.requestFocus();
            finish();
            // Limpia los campos de entrada de texto y coloca el cursor en el campo de correo
        }catch (Exception ex){
            Toast.makeText(this,"Error al ingresar usuario", Toast.LENGTH_LONG).show();
            // Captura cualquier excepción y muestra un mensaje de error
        }
    }
}