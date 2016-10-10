package com.curso.ifsp.pokedex.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.curso.ifsp.pokedex.R;
import com.curso.ifsp.pokedex.model.Pokemon;
import com.curso.ifsp.pokedex.view.adapter.PokemonListAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adenilson on 08/10/16.
 */

public class PokemonListActivity extends AppCompatActivity {

    private ListView listViewPokemon;
    private List<Pokemon> pokemonList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pokemon_list_activity);

        //Cria pokemons mockado
        createPokemons();
        //Transforma componente xml em objeto java e seta um adapter na lista
        bindListViewPokemon();

    }

    private void createPokemons() {
        int[] charmanderImages = {R.drawable.charmander, R.drawable.charmeleon, R.drawable.charizard};
        int[] squirtleImages = {R.drawable.squirtle, R.drawable.wartortle, R.drawable.blastoise};
        int[] pidgeyImages = {R.drawable.pidgey, R.drawable.pidgeot, R.drawable.pidgeotto};
        pokemonList = Arrays.asList(new Pokemon(charmanderImages, "Chamander", 20, 6, 4, Pokemon.TypePokemon.FIRE),
                new Pokemon(squirtleImages, "Squirtle", 10, 3, 5, Pokemon.TypePokemon.WATER),
                new Pokemon(pidgeyImages, "Pidgey", 6, 1, 8, Pokemon.TypePokemon.AIR));

    }

    private void bindListViewPokemon() {
        listViewPokemon = (ListView) findViewById(R.id.list_view_pokemon);

        //Cria um adapter para lista. Adapter é a classe responsável por fazer a view de cada item
        //da lista.
        PokemonListAdapter pokemonListAdapter = new PokemonListAdapter(pokemonList);
        //Seta o adapter na lista, apartir daqui será criada view por view a cada item da lista
        listViewPokemon.setAdapter(pokemonListAdapter);


        //Cria um listener que fica ouvindo o click em um item da lista
        listViewPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //Quando um evento de click é detectado o seguinte metódo é chamado >
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pokemon pokemon = (Pokemon) parent.getItemAtPosition(position);
                //Aqui criaremos a inteção de ir para a tela de detalhes
                Intent goToDetailsPokemonActivity = new Intent(PokemonListActivity.this, DetailsPokemonActivity.class);
                //adicionamos o pokemon selecionado em um bundle para ele ser passado pra proxima tela
                goToDetailsPokemonActivity.putExtra("POKEMON", pokemon);
                //Chamamos a proxima tela
                startActivity(goToDetailsPokemonActivity);
            }
        });
    }
}
