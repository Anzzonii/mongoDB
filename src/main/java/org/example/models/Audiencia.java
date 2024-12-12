package org.example.models;

import org.bson.Document;



public class Audiencia {
    private String fecha;
    private int espectadores;

    public Audiencia(String fecha, int espectadores) {
        this.fecha = fecha;
        this.espectadores = espectadores;
    }

    public Document toDocument(){
        return new Document("fecha", fecha)
                .append("espectadores", espectadores);
    }

    public static Audiencia fromDocument(Document document){
        String fecha = document.getString("fecha");
        int espectadores = document.getInteger("espectadores");
        return new Audiencia(fecha, espectadores);
    }


    @Override
    public String toString() {
        return "Audiencia{" +
                "fecha='" + fecha + '\'' +
                ", espectadores=" + espectadores +
                '}';
    }
}
