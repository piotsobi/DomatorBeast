package com.demon.domatorbeast.data;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Piotr on 2016-01-06.
 */
public class Exercise extends RealmObject {
    //@Required
    private int id;
    private String opis;
    private String level;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int steps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
