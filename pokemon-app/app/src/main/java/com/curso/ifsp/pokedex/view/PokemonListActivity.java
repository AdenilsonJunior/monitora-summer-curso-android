package com.curso.ifsp.pokedex.view;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.curso.ifsp.pokedex.R;
import com.curso.ifsp.pokedex.helper.LoginHelper;
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
        int[] abraImages = {R.drawable.abra, R.drawable.kadabra, R.drawable.alakazam};
        int[] bulbasaurImages = {R.drawable.bulbasaur, R.drawable.ivysaur, R.drawable.venusaur};
        int[] dragonairImages = {R.drawable.dragonair, R.drawable.dratini, R.drawable.dragonite};
        int[] gastlyImages = {R.drawable.gastly, R.drawable.gengar, R.drawable.haunter};
        int[] pichuImages = {R.drawable.pichu, R.drawable.pikachu, R.drawable.raichu};
        int[] squirtleImages = {R.drawable.squirtle, R.drawable.wartortle, R.drawable.blastoise};
        int[] pidgeyImages = {R.drawable.pidgey, R.drawable.pidgeot, R.drawable.pidgeotto};

        pokemonList = Arrays.asList(
                new Pokemon(charmanderImages, "Chamander", 80, 6, 4, Pokemon.TypePokemon.FIRE),
                new Pokemon(squirtleImages, "Squirtle", 70, 3, 5, Pokemon.TypePokemon.WATER),
                new Pokemon(pidgeyImages, "Pidgey", 86, 7, 4, Pokemon.TypePokemon.AIR),
                new Pokemon(abraImages, "Abra", 95, 7, 8, Pokemon.TypePokemon.PSICHO),
                new Pokemon(bulbasaurImages, "Bulbasaur", 45, 3, 8, Pokemon.TypePokemon.GRASS),
                new Pokemon(dragonairImages, "Dragonair", 68, 4, 6, Pokemon.TypePokemon.AIR),
                new Pokemon(gastlyImages, "Gastly", 78, 4, 6, Pokemon.TypePokemon.GHOST),
                new Pokemon(pichuImages, "Pichu", 55, 6, 4, Pokemon.TypePokemon.ELETRICT)
        );
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Método responsável por inflar o menu na toolbar através do menuinflater.
        getMenuInflater().inflate(R.menu.menu_list_pokemon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Verificação de qual item do menu foi selecionado.
        switch (item.getItemId()){
            case R.id.menu_logout:
                logout();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);

    }

    private void logout() {
        //Cria uma intenção de trocar de activity.
        LoginHelper.setLogged(PokemonListActivity.this, false);
        Intent intent = new Intent(PokemonListActivity.this, LoginActivity.class);
        //Executa a intenção.
        startActivity(intent);
    }
}
