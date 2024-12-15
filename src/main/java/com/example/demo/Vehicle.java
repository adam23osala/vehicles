package com.example.demo;

import java.io.Serializable;

public class Vehicle implements Serializable {

    private long id;
    private String matricula;
    private String marca;

    public Vehicle() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vehicle(long id, String matricula, String marca) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Vehicle{matricula='" + matricula + "', marca='" + marca + "'}";
    }
}
