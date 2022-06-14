package com.valcon.lskeljo.abnamro.model;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected IngredientEntity() {}

    public IngredientEntity(String name) {
        this.name = name;
    }

    public IngredientEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String ingredient) {
        this.name = ingredient;
    }

    @Override
    public String toString() {
        return String.format("Ingredient[id=%d, name='%s']", id, name);
    }
}
