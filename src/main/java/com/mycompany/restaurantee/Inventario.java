package com.mycompany.restaurantee;

public class Inventario {

    private String ingrediente;
    private int unidades;
    private int minimo;
    private String estado;

    public Inventario(String ingrediente, int unidades, int minimo, String estado){
        this.ingrediente = ingrediente;
        this.unidades = unidades;
        this.minimo = minimo;
        this.estado = estado;
    }

    public String getIngrediente(){ return ingrediente; }
    public void setIngrediente(String ingrediente){ this.ingrediente = ingrediente; }

    public int getUnidades(){ return unidades; }
    public void setUnidades(int unidades){ this.unidades = unidades; }

    public int getMinimo(){ return minimo; }
    public void setMinimo(int minimo){ this.minimo = minimo; }

    public String getEstado(){ return estado; }
    public void setEstado(String estado){ this.estado = estado; }
}