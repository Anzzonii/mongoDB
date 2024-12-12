package org.example.dao;

import org.example.models.Programa;

import java.util.List;

public interface ProgramaDAO {

    void crearNuevoPrograma(Programa programa);
    List<Programa> listarProgramas();
    Programa getPrograbaByNombre(String nombre);
    void updatePrograma(Programa programa);
    void deletePrograma(String id);


}
