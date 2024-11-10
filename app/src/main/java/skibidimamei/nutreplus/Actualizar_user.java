package skibidimamei.nutreplus;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Actualizar_user extends AppCompatActivity {

    private EditText ed_usuario, ed_contraseña;
    private Button bt_Actualizar, bt_Eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actualizar_user);

        //define el valor de cada edit text y botones con los del xml
        ed_usuario = findViewById(R.id.UpUser);
        ed_contraseña = findViewById(R.id.UpPass);
        bt_Actualizar = findViewById(R.id.update);
        bt_Eliminar = findViewById(R.id.Delete);

        Intent i = getIntent();

        // Extrae los datos pasados en el Intent (usuario, contraseña) y los convierte a String.
        String Up_user = i.getStringExtra("usuario");
        String Up_pass = i.getStringExtra("contraseña");

        // Establece los valores extraídos del Intent en los campos de texto correspondientes.
        ed_usuario.setText(Up_user);
        ed_contraseña.setText(Up_pass);

        // Boton de actualizar
        bt_Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            editar();
            }
        });

        // boton de eliminar
        bt_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });
    }
    // Funcion del boton editar
    public void editar(){
        try {
            // obtiene los valores de los campos de texto
            String usuario =ed_usuario.getText().toString();
            String contraseña = ed_contraseña.getText().toString();

            SQLiteDatabase db =openOrCreateDatabase("BD_USUARIOS", Context.MODE_PRIVATE, null);

            // prepara la sentencia sql para actualizar
            String sql = "update Usuarios set contraseña =? where usuario =?";
            SQLiteStatement statement = db.compileStatement(sql);

            // vincula los valores a la sentencia sql
            statement.bindString(1,usuario);
            statement.bindString(2,contraseña);

            // Envia mensaje de actualizacion correcta
            Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_LONG).show();

            ed_usuario.setText("");
            ed_contraseña.setText("");
            // limpia los campos usados
        }catch (Exception e){
            Toast.makeText(this,"Error al editar la contraseña del usuario o usuario no encontrado", Toast.LENGTH_LONG).show();
        }
    }
    // Funcion del boton eliminar
    public void Eliminar(){
        try {
            String usuario = ed_usuario.getText().toString();

            SQLiteDatabase db =openOrCreateDatabase( "BD_USUARIOS",Context.MODE_PRIVATE,null);

            String sql = "delete from Usuarios where usuario =?";
            SQLiteStatement statement =db.compileStatement(sql);

            statement.bindString(1,usuario);
            statement.execute();

            Toast.makeText(this,"Usuario eliminado correctamente", Toast.LENGTH_LONG).show();

            ed_usuario.setText("");
            ed_contraseña.setText("");
            ed_usuario.requestFocus();
        }catch (Exception e){
            Toast.makeText(this,"Error al eliminar usuario o usuario no encontrado", Toast.LENGTH_LONG).show();
        }
    }
}