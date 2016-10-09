package com.curso.ifsp.pokedex.model;

import java.io.Serializable;

/**
 * Created by adenilson on 09/10/16.
 */

public class Pokemon implements Serializable {

    private int[] image;
    private String name;
    private int life;
    private int attack;
    private int defense;
    private TypePokemon type;


    public Pokemon(int[] image, String name, int life, int attack, int defense, TypePokemon type) {
        this.image = image;
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.defense = defense;
        this.type = type;
    }


    public int[] getImage() {
        return image;
    }

    public void setImage(int[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getTypePokemon() {
        return type.getType();
    }

    public void setTypePokemon(TypePokemon type) {
        this.type = type;
    }


    public enum TypePokemon {
        AIR("Air"),
        ELETRICT("Elitrict"),
        FIRE("Fire"),
        WATER("Water"),
        GHOTS("Ghost"),
        DRAGON("Dragon"),
        PSICHO("Psicho");


        String type;

        TypePokemon(String type) {
            this.type = type;

        }

        public String getType() {
            return type;
        }
    }


}
