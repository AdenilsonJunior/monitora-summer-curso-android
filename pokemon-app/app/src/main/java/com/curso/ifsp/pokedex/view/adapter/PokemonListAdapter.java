package com.curso.ifsp.pokedex.view.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.curso.ifsp.pokedex.R;
import com.curso.ifsp.pokedex.model.Pokemon;

import java.util.List;

/**
 * Created by adenilson on 09/10/16.
 */

public class PokemonListAdapter extends BaseAdapter {

    private List<Pokemon> pokemonList;

    public PokemonListAdapter(List<Pokemon> pokemonList){
        this.pokemonList = pokemonList;
    }

    //Quantidade de itens da sua lista
    // O adapter fará essa quantidade de views para a lista
    @Override
    public int getCount() {
        return pokemonList.size();
    }

    //Pega o item de posição i (para
    @Override
    public Pokemon getItem(int i) {
        return pokemonList.get(i);
    }

    //Pega o ID do item da lista, nesse caso será 0 pois nossos pokemons não terão ID
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //myViewHolder será responsavel por manipular a view a ser tratada e seus componentes, tornando eles
        //em objetos java. Isso é uma questão de encapsulamento
        MyViewHolder myViewHolder;

        //Pega a referencia do pokemon da position atual da lista
        Pokemon pokemon = pokemonList.get(position);

        //Verifica se a view não foi criada
        if(convertView == null){
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pokemon_list, viewGroup, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        }else{
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //Coloca as informações nos componentes através dos objetos java
        myViewHolder.imageViewPokemon.setImageResource(pokemon.getImage()[0]);
        myViewHolder.textViewNamePokemon.setText(pokemon.getName());
        myViewHolder.textViewLifePointsPokemon.setText(String.valueOf(pokemon.getLife()));

        return convertView;
    }

    private class MyViewHolder {
        ImageView imageViewPokemon;
        TextView textViewNamePokemon;
        TextView textViewLifePointsPokemon;

        public MyViewHolder(View view){
            imageViewPokemon = (ImageView) view.findViewById(R.id.image_view_pokemon);
            textViewNamePokemon = (TextView) view.findViewById(R.id.text_view_pokemon_name);
            textViewLifePointsPokemon = (TextView) view.findViewById(R.id.text_view_pokemon_life);
        }

    }

}
