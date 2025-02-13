/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.mssql;

public class AdmClientes {
   private int id_cliente;
   private String nombre;
   private String razon_social;
   private int cfdi;        

   public AdmClientes() {
    
}
    public AdmClientes(int id_cliente, String nombre, String razon_social, int cfdi) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.razon_social = razon_social;
        this.cfdi = cfdi;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public int getCfdi() {
        return cfdi;
    }

    public void setCfdi(int cfdi) {
        this.cfdi = cfdi;
    }
   
    
    
}
