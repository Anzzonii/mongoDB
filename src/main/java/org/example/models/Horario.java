package org.example.models;

import org.bson.Document;

public class Horario {

    private String dia;
    private String hora;

    public Horario(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public Document toDocument(){
        return new Document("dia", dia)
                .append("hora", hora);
    }

    public static Horario fromDocument(Document document){
        String dia = document.getString("dia");
        String hora = document.getString("hora");
        return new Horario(dia, hora);
    }


    @Override
    public String toString() {
        return "Horario{" +
                "dia='" + dia + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
