package org.example.models;

import org.bson.Document;

import javax.print.Doc;

public class Colaborador {
    private String nombre;
    private String rol;

    public Colaborador(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public Document toDocument(){
        return new Document("nombre", nombre)
                .append("rol", rol);
    }

    public static Colaborador fromDocument(Document document){
        String nombre = document.getString("nombre");
        String rol = document.getString("rol");
        return new Colaborador(nombre, rol);
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
