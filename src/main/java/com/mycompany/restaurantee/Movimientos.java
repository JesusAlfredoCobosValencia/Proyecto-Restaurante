package com.mycompany.restaurantee;

public class Movimientos {
    private String ingrediente;
    private String accion;
    private int cantidad;
    private String fecha;

    public Movimientos(String ingrediente, String accion, int cantidad, String fecha){
        this.ingrediente = ingrediente;
        this.accion = accion;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getIngrediente(){ return ingrediente; }
    public void setIngrediente(String ingrediente){ this.ingrediente = ingrediente; }

    public String getAccion(){ return accion; }
    public void setAccion(String accion){ this.accion = accion; }

    public int getCantidad(){ return cantidad; }
    public void setCantidad(int cantidad){ this.cantidad = cantidad; }

    public String getFecha(){ return fecha; }
    public void setFecha(String fecha){ this.fecha = fecha; }
}