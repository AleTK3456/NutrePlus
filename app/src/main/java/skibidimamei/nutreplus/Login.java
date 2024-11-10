package skibidimamei.nutreplus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    // Credenciales de Login
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);

        // designaciones
        Button btnActualizar = findViewById(R.id.btnActualizar);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnCrear = findViewById(R.id.btnCrear);
        EditText User = findViewById(R.id.etUsername);
        EditText password = findViewById(R.id.etPassword);

        // Funcion de Boton de Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputUsername = User.getText().toString();
                String inputPassword = password.getText().toString();

                // Comprovasion de login
                if (inputUsername.equals(USERNAME) && inputPassword.equals(PASSWORD)){

                    //login exitoso
                    Toast.makeText(Login.this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();

                    // Abrir Main activity
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    
                }else {
                    // Error en login
                    Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Funcion de boton crear cuenta
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        //Funcion para ir a actualizar un usuario
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Actualizar_user.class);
                startActivity(intent);
            }
        });
    }
}