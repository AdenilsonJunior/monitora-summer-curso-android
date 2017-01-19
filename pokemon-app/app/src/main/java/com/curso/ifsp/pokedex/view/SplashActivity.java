package com.curso.ifsp.pokedex.view;

import android.content.Intent;
import android.os.Handler;
import android.renderscript.RenderScript;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.curso.ifsp.pokedex.R;
import com.curso.ifsp.pokedex.helper.LoginHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Método responsável por iniciar a Login activity. Neste método utilizaremos um objeto da
        //classe Handler que tem um método responsável por executar um comando depois de um tempo
        //determinado no parâmetro. Ele é executado em uma thread diferente da main.
        startLoginActivity();
    }

    private void startLoginActivity() {
        Handler handler = new Handler();

        //Cria um thread que inicia a LoginActivity depois de 3 segundos. (O parâmetro que recebe o
        //tempo é em milisegundos.
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Verifica no shared preferences se um usuário ja esta logado.
                if(LoginHelper.isLogged(SplashActivity.this)){
                    //Se usuário logado, a tela de lista de pokemons ja será exibida.
                    Intent intent = new Intent(SplashActivity.this, PokemonListActivity.class);
                    startActivity(intent);
                }else {

                    //É criada uma intenção de ir para a LoginActivity
                    //e a mesma será iniciada.
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                //Encerra esta activity para que o usuário nao consiga voltar nela.
                finish();
            }
        }, 3000);
    }
}
