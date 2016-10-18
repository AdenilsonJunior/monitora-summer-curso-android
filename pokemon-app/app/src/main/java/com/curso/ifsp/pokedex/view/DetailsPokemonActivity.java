package com.curso.ifsp.pokedex.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.curso.ifsp.pokedex.R;
import com.curso.ifsp.pokedex.model.Pokemon;
import com.curso.ifsp.pokedex.utils.Utils;

/**
 * Created by Estevao on 09/10/2016.
 */
public class DetailsPokemonActivity extends AppCompatActivity {
    private ProgressBar progressBarLife;
    private ProgressBar progressBarDefense;
    private ProgressBar progressBarAttack;
    private Pokemon pokemon;
    private TextView txtViewPokemonName;
    private ImageView imageViewPokemonImage;
    private ImageView imageViewPokemonImageEv1;
    private ImageView imageViewPokemonImageEv2;
    private TextView txtViewpokemonType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        //Pegamos o pokemon passado por bundle da intent anterior
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //se bundle não for nulo, buscamos o pokemon, com o nome que demos na outra activity
            pokemon = (Pokemon) bundle.getSerializable("POKEMON");
        }

        bindPokemonName();
        bindPokemonImages();
        bindPokemonType();
        bindLifeProgress();
        bindAttackProgress();
        bindDefenseProgress();
    }

    private void bindPokemonType() {
        txtViewpokemonType = (TextView) findViewById(R.id.txt_view_pokemon_type);
        txtViewpokemonType.setText(pokemon.getTypePokemon());
    }

    private void bindPokemonImages() {
        //Através do id no xml, encontra e transforma o componente em um objeto no java para poder manipular
        imageViewPokemonImage = (ImageView) findViewById(R.id.img_view_pokemon);
        //seta a imagem do pokemon no imageView
        imageViewPokemonImage.setImageDrawable(Utils.getDrawableByIdResource(this, pokemon.getImage()[0]));

        imageViewPokemonImageEv1 = (ImageView) findViewById(R.id.img_view_pokemon_ev1);
        //seta a imagem do pokemon no imageView
        imageViewPokemonImageEv1.setImageDrawable(Utils.getDrawableByIdResource(this, pokemon.getImage()[1]));

        imageViewPokemonImageEv2 = (ImageView) findViewById(R.id.img_view_pokemon_ev2);
        //seta a imagem do pokemon no imageView
        imageViewPokemonImageEv2.setImageDrawable(Utils.getDrawableByIdResource(this, pokemon.getImage()[2]));

    }

    private void bindPokemonName() {
        //Através do id no xml, encontra e transforma o componente em um objeto no java para poder manipular
        txtViewPokemonName = (TextView) findViewById(R.id.txt_view_pokemon_name);

        //seta o nome do pokemon recebido no textView
        txtViewPokemonName.setText(pokemon.getName());
    }

    private void bindDefenseProgress() {
        //Através do id no xml, encontra e transforma o componente em um objeto no java para poder manipular
        progressBarDefense = (ProgressBar) findViewById(R.id.progressbar_defense);
        progressBarDefense.setProgress(pokemon.getDefense());
    }

    private void bindAttackProgress() {
        //Através do id no xml, encontra e transforma o componente em um objeto no java para poder manipular
        progressBarAttack = (ProgressBar) findViewById(R.id.progressbar_attack);
        progressBarAttack.setProgress(pokemon.getAttack());
    }

    private void bindLifeProgress() {
        //Através do id no xml, encontra e transforma o componente em um objeto no java para poder manipular
        progressBarLife = (ProgressBar) findViewById(R.id.progressbar_life);
        progressBarLife.setMax(100);
        progressBarLife.setProgress(pokemon.getLife());
    }
}
