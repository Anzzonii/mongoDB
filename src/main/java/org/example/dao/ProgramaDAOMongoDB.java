package org.example.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.models.Programa;
import org.example.utils.MongoDBConnection;

import javax.print.Doc;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProgramaDAOMongoDB implements ProgramaDAO{
    private final MongoCollection<Document> collection;

    public ProgramaDAOMongoDB() {
        this.collection = MongoDBConnection.getDatabase();
    }

    @Override
    public void crearNuevoPrograma(Programa programa) {
        Document documento = programa.toDocument();
        collection.insertOne(documento);
        System.out.println("Programa insertado");
    }

    @Override
    public List<Programa> listarProgramas() {
        List<Programa> programas = new ArrayList<>();
        for(Document document : collection.find()){
            programas.add(Programa.fromDocument(document));
        }
        return programas;

    }

    @Override
    public Programa getPrograbaByNombre(String nombre) {
        try{
            Document programa = new Document(collection.find(new Document("nombre", nombre)).first());

            Programa p = Programa.fromDocument(programa);
            return p;
        } catch (Exception e) {
            System.out.println("No se ha encontrado el programa");
            return null;
        }

    }

    @Override
    public void updatePrograma(Programa programa) {
        try {
            Document documento = programa.toDocument();
            Document filtro = new Document("id", programa.getId());
            Document actualizacion = new Document("$set", documento);
            collection.updateOne(filtro, actualizacion);
            System.out.println("Programa actualizado correctamente");
        } catch (Exception e) {
            System.out.println("No existe la id del programa");
        }
    }

    @Override
    public void deletePrograma(String id) {
        collection.deleteOne(new Document("id", id));
        System.out.println("Programa borrado correctamente");
    }

    public List<Programa> getProgramasByCategoria(String categoria){
        try{
            Document filtro = new Document("categoria", categoria);

            FindIterable<Document> programasEnDocument = collection.find(filtro);
            List<Programa> programas = new ArrayList<>();

            for(Document doc : programasEnDocument){
                Programa p = Programa.fromDocument(doc);
                programas.add(p);
            }

            return programas;

        }catch (Exception e){
            System.out.println("Error al buscar programas");
            return Collections.emptyList();
        }
    }

    public Programa getProgramaConMayorAudiencia(String fecha){
        try {
            Document filtro = new Document("audiencia.fecha", fecha);

            FindIterable<Document> programasOrdenados = collection.find(filtro).sort(new Document("audiencia.espectadores", -1));

            Document programa = programasOrdenados.first();

            return Programa.fromDocument(programa);

        } catch (Exception e) {
            System.out.println("Programa no encontrado");
            return null;
        }
    }

    public double getAudienciaMediaEntreFechas(String nombre, String fecha1, String fecha2){
        Document filtroNombre = new Document("nombre", nombre);

        FindIterable<Document> programas = collection.find(filtroNombre);
        double sumaAudiencia = 0;
        int contAudiencia = 0;

        for(Document programa : programas){
            List<Document> audienciaList = (List<Document>) programa.get("audiencia");

            for(Document audiencia : audienciaList){
                String fecha = audiencia.getString("fecha");
                int espectadores = audiencia.getInteger("espectadores");

                if(fecha.compareTo(fecha1) >= 0 && fecha.compareTo(fecha2) <= 0){
                    sumaAudiencia+=espectadores;
                    contAudiencia++;
                }
            }
        }

        return sumaAudiencia/contAudiencia;
    }

}
