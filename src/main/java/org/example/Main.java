package org.example;

import org.example.dao.ProgramaDAOMongoDB;
import org.example.models.Audiencia;
import org.example.models.Colaborador;
import org.example.models.Horario;
import org.example.models.Programa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ProgramaDAOMongoDB pDao = new ProgramaDAOMongoDB();

        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        while(!salir){
            System.out.println("\n1. Crear nuevo programa\n" +
                    "2. Listar todos los programas\n" +
                    "3. Consultar programa por nombre\n" +
                    "4. Actualizar un programa\n" +
                    "5. Eliminar un programa\n" +
                    "6. Devolver programas según una categoría\n" +
                    "7. Obtener programa con mayor audiencia en una fecha\n" +
                    "8. Calcular la audiencia media de un programa en un rango de fechas\n" +
                    "9. Salir");
            System.out.println("Elige una opción: ");
            int opcion = sc.nextInt();
            switch(opcion){

                case 1:
                    System.out.println("Nombre:");
                    String nombre = sc.next();
                    System.out.println("id:");
                    String id = sc.next();
                    System.out.println("Categoria: ");
                    String categoria = sc.next();
                    List<Horario> horarios = new ArrayList<>();
                    System.out.println("Cuantos horarios quieres meter?");
                    int numH = sc.nextInt();
                    for(int i = 0; i<numH; i++){
                        System.out.println("Dia");
                        String dia = sc.next();
                        System.out.println("Hora");
                        String hora = sc.next();
                        horarios.add(new Horario(dia, hora));
                    }
                    List<Audiencia> audiencias = new ArrayList<>();
                    System.out.println("Cuantas audiencias vas a insertar?");
                    int numA = sc.nextInt();
                    for(int i = 0; i<numA; i++){
                        System.out.println("Fecha");
                        String fecha = sc.next();
                        System.out.println("espectadores");
                        int espectadores = sc.nextInt();
                        audiencias.add(new Audiencia(fecha, espectadores));
                    }
                    List<Colaborador> colaboradores = new ArrayList<>();
                    System.out.println("Cuantos colaboradores quieres añadir?");
                    int numC = sc.nextInt();
                    for(int i = 0; i<numC; i++){
                        System.out.println("nombre");
                        String nombreC = sc.next();
                        System.out.println("Rol");
                        String rol = sc.next();
                        colaboradores.add(new Colaborador(nombreC, rol));
                    }
                    pDao.crearNuevoPrograma(new Programa(id, nombre, categoria, horarios, audiencias, colaboradores));
                break;

                case 2:
                    System.out.println(pDao.listarProgramas());
                    break;

                case 3:
                    System.out.println("Nombre: ");
                    nombre = sc.next();
                    System.out.println(pDao.getPrograbaByNombre(nombre));
                    break;

                case 4:
                    System.out.println("id del programa a actualizar: ");
                    id = sc.next();
                    System.out.println("Nombre:");
                    nombre = sc.next();
                    System.out.println("Categoria: ");
                    categoria = sc.next();
                    horarios = new ArrayList<>();
                    System.out.println("Cuantos horarios quieres meter?");
                    numH = sc.nextInt();
                    for(int i = 0; i<numH; i++){
                        System.out.println("Dia");
                        String dia = sc.next();
                        System.out.println("Hora");
                        String hora = sc.next();
                        horarios.add(new Horario(dia, hora));
                    }
                    audiencias = new ArrayList<>();
                    System.out.println("Cuantas audiencias vas a insertar?");
                    numA = sc.nextInt();
                    for(int i = 0; i<numA; i++){
                        System.out.println("Fecha");
                        String fecha = sc.next();
                        System.out.println("espectadores");
                        int espectadores = sc.nextInt();
                        audiencias.add(new Audiencia(fecha, espectadores));
                    }
                    colaboradores = new ArrayList<>();
                    System.out.println("Cuantos colaboradores quieres añadir?");
                    numC = sc.nextInt();
                    for(int i = 0; i<numC; i++){
                        System.out.println("nombre");
                        String nombreC = sc.next();
                        System.out.println("Rol");
                        String rol = sc.next();
                        colaboradores.add(new Colaborador(nombreC, rol));
                    }

                    pDao.updatePrograma(new Programa(id, nombre, categoria, horarios, audiencias, colaboradores));
                    break;

                case 5:
                    System.out.println("id del programa a eliminar: ");
                    id = sc.next();
                    pDao.deletePrograma(id);
                    break;

                case 6:
                    System.out.println("Categoria de los programas a buscar: ");
                    categoria = sc.next();
                    System.out.println(pDao.getProgramasByCategoria(categoria));
                    break;

                case 7:
                    System.out.println("Introduce la fecha: ");
                    String fecha = sc.next();
                    System.out.println(pDao.getProgramaConMayorAudiencia(fecha));
                    break;

                case 8:
                    System.out.println("Introduce el nombre del programa:");
                    nombre = sc.next();
                    System.out.println("Introduce la fecha 1:");
                    String fecha1 = sc.next();
                    System.out.println("Introduce la fecha 2:");
                    String fecha2 = sc.next();
                    System.out.println(pDao.getAudienciaMediaEntreFechas(nombre, fecha1, fecha2));
                    break;

                case 9:
                    salir = true;

                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }

    }
}