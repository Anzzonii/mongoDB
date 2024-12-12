package org.example.models;

import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Programa {
    private String id;
    private String nombre;
    private String categoria;
    private List<Horario> horario;
    private List<Audiencia> audiencia;
    private List<Colaborador> colaboradores;

    public Programa(String id, String nombre, String categoria, List<Horario> horario, List<Audiencia> audiencia, List<Colaborador> colaboradores) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.horario = horario;
        this.audiencia = audiencia;
        this.colaboradores = colaboradores;
    }

    public Document toDocument(){
        List<Document> horariosDoc = new ArrayList<>();
        for(Horario h : horario){
            horariosDoc.add(h.toDocument());
        }
        List<Document> audienciasDoc = new ArrayList<>();
        for(Audiencia a : audiencia){
            audienciasDoc.add(a.toDocument());
        }
        List<Document> colaboradoresDoc = new ArrayList<>();
        for(Colaborador c : colaboradores){
            colaboradoresDoc.add(c.toDocument());
        }

        return new Document("id", id)
                .append("nombre", nombre)
                .append("categoria", categoria)
                .append("horario", horariosDoc)
                .append("audiencia", audienciasDoc)
                .append("colaboradores", colaboradoresDoc);
    }

    public static Programa fromDocument(Document document){

        List<Audiencia> audiencia = ((List<Document>) document.get("audiencia")).stream().map(Audiencia::fromDocument).collect(toList());

        List<Colaborador> colaboradores = ((List<Document>) document.get("colaboradores")).stream().map(Colaborador::fromDocument).collect(toList());

        List<Horario> horarios = ((List<Document>) document.get("horario")).stream().map(Horario::fromDocument).collect(toList());

        String id = document.getString("id");
        String nombre = document.getString("nombre");
        String categoria = document.getString("categoria");

        return new Programa(id, nombre, categoria,horarios, audiencia, colaboradores);
    }

    @Override
    public String toString() {
        return "\nPrograma{" +
                "id='" + id + '\'' +
                "nombre='" + nombre + '\'' +
                "categoria='" + categoria + "\n" +
                "horario=" + horario + "\n" +
                "audiencia=" + audiencia + "\n" +
                "colaboradores=" + colaboradores +
                "}\n";
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }
}
