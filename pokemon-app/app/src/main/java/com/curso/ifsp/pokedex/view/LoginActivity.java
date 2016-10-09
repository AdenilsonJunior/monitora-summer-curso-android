package com.curso.ifsp.pokedex.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.curso.ifsp.pokedex.R;
import com.curso.ifsp.pokedex.helper.LoginHelper;

/**
 * Created by adenilson on 08/10/16.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindEditTextUsername();
        bindEditTextPassword();
        bindButtonLogin();


    }

    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Método que pega o texto digitado no componente. Isso foi possível porque transformamos
                //o componente xml desejado em objeto java, sendo assim, podendo manipulá-lo em tempo de execução
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                //Simulação de uma validação de login
                if (LoginHelper.validaLogin(username, password)) {
                    //Se o login for válido, criaremos uma intenção de ir para a PokemonListActivity
                    //e a mesma será iniciada.
                    startPokemonListActivity();
                } else {
                    //Mostra uma mensagem Toast de aviso
                    Toast.makeText(LoginActivity.this, R.string.message_invalid_login, Toast.LENGTH_SHORT).show();

                    //Limpa os campos dos EditText's
                    //Pode ser usado também edittext.setText("");
                    editTextUsername.getText().clear();
                    editTextPassword.getText().clear();
                }

            }
        });
    }

    private void startPokemonListActivity() {
        Intent intent = new Intent(LoginActivity.this, PokemonListActivity.class);
        startActivity(intent);
    }

    private void bindEditTextPassword() {
        //Através do id no xml, encontra e transforma o componente em um objeto no java para poder manipular
        editTextPassword = (EditText) findViewById(R.id.edit_text_password);
    }

    private void bindEditTextUsername() {
        //Através do id no xml, encontra e transforma o componente em um objeto no java para poder manipular
        editTextUsername = (EditText) findViewById(R.id.edit_text_user_name);
    }
}
