package com.curso.ifsp.pokedex.view;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.curso.ifsp.pokedex.R;
import com.curso.ifsp.pokedex.model.Pokemon;
import com.curso.ifsp.pokedex.view.adapter.PokemonListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adenilson on 08/10/16.
 */

public class PokemonListActivity extends AppCompatActivity {

    private ListView listViewPokemon;
    private List<Pokemon> pokemonList;
    private PokemonListAdapter pokemonListAdapter;

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
        int[] abraImages = {R.drawable.abra, R.drawable.kadabra, R.drawable.alakazam};
        int[] bulbasaurImages = {R.drawable.bulbasaur, R.drawable.ivysaur, R.drawable.venusaur};
        int[] dragonairImages = {R.drawable.dragonair, R.drawable.dratini, R.drawable.dragonite};
        int[] gastlyImages = {R.drawable.gastly, R.drawable.gengar, R.drawable.haunter};
        int[] pichuImages = {R.drawable.pichu, R.drawable.pikachu, R.drawable.raichu};
        int[] squirtleImages = {R.drawable.squirtle, R.drawable.wartortle, R.drawable.blastoise};
        int[] pidgeyImages = {R.drawable.pidgey, R.drawable.pidgeot, R.drawable.pidgeotto};

        pokemonList = new ArrayList<>(Arrays.asList(
                new Pokemon(charmanderImages, "Chamander", 80, 6, 4, Pokemon.TypePokemon.FIRE),
                new Pokemon(squirtleImages, "Squirtle", 70, 3, 5, Pokemon.TypePokemon.WATER),
                new Pokemon(pidgeyImages, "Pidgey", 86, 7, 4, Pokemon.TypePokemon.AIR),
                new Pokemon(abraImages, "Abra", 95, 7, 8, Pokemon.TypePokemon.PSICHO),
                new Pokemon(bulbasaurImages, "Bulbasaur", 45, 3, 8, Pokemon.TypePokemon.GRASS),
                new Pokemon(dragonairImages, "Dragonair", 68, 4, 6, Pokemon.TypePokemon.AIR),
                new Pokemon(gastlyImages, "Gastly", 78, 4, 6, Pokemon.TypePokemon.GHOST),
                new Pokemon(pichuImages, "Pichu", 55, 6, 4, Pokemon.TypePokemon.ELETRICT)
        ));
    }

    private void bindListViewPokemon() {
        listViewPokemon = (ListView) findViewById(R.id.list_view_pokemon);

        //Cria um drawable de cor
        ColorDrawable myColor = new ColorDrawable(ContextCompat.getColor(this, R.color.divider));
        //seta essa cor como um divisor da lista
        listViewPokemon.setDivider(myColor);
        //seta a espessura da lista
        listViewPokemon.setDividerHeight(1);

        //Cria um adapter para lista. Adapter é a classe responsável por fazer a view de cada item
        //da lista.
        pokemonListAdapter = new PokemonListAdapter(pokemonList);
        //registra a lista para o menu de contexto
        registerForContextMenu(listViewPokemon);
        //Seta o adapter na lista, apartir daqui será criada view por view a cada item da lista
        listViewPokemon.setAdapter(pokemonListAdapter);


        //Cria um listener que fica ouvindo o click em um item da lista
        listViewPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //Quando um evento de click é detectado o seguinte metódo é chamado >
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Pegamos o pokemon na posição clicada
                Pokemon pokemon = (Pokemon) parent.getItemAtPosition(position);
                //chamamos o metodo passando o pokemon por parametro
                goToPokemonDetails(pokemon);

            }
        });
    }

    private void goToPokemonDetails(Pokemon pokemon) {
        //Aqui criaremos a inteção de ir para a tela de detalhes
        Intent goToDetailsPokemonActivity = new Intent(PokemonListActivity.this, DetailsPokemonActivity.class);
        //adicionamos o pokemon selecionado em um bundle para ele ser passado pra proxima tela
        goToDetailsPokemonActivity.putExtra("POKEMON", pokemon);
        //Chamamos a proxima tela
        startActivity(goToDetailsPokemonActivity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //cria a instancia de um inflador de menus
        MenuInflater inflater = getMenuInflater();
        //infla o nosso menu
        inflater.inflate(R.menu.list_context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //pega as informações do menu (onde poderemos obter a posição do click
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            //caso o id do item clicado foi delete
            case R.id.delete:
                //pega o pokemon de acordo com o click
                Pokemon pokemon = pokemonListAdapter.getItem(info.position);
                Log.d("PokemonListActivity", "removing item pos=" + pokemon.getName());
                //chama o método para deletar o pokemon da lista
                removePokemon(pokemon);
                return true;
            //caso o id do item clicado foi view
            case R.id.view:
                //chama o método para mostrar as informações do pokemon, passando o pokemon selecionado
                goToPokemonDetails(pokemonListAdapter.getItem(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void removePokemon(Pokemon pokemon) {
        //deleta o pokemon selecionado da lista
        pokemonList.remove(pokemon);
        //notifica o adaptador que houve uma mudança na lista
        pokemonListAdapter.notifyDataSetChanged();
    }

}
