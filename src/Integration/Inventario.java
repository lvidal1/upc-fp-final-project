package Integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {
    public static void main(String[] args) {

        int opcion = menuAgregarEditarEquipo();
        if (opcion == 1){
            agregarNuevoEquipo();
        }else {
            Scanner ee = new Scanner(System.in);
            System.out.print("Ingrese codigo del equipo a editar: ");//C0006
            String codigoEquipo = ee.nextLine();
            editarEquipo(codigoEquipo);
        }
    }

    public static int menuAgregarEditarEquipo() {
        int opcion;

        Scanner mp = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("1. Agregar equipo (1)");
        System.out.println("2. Editar equipo (2)");
        System.out.print("Ingrese opcion para operacion: ");
        opcion = mp.nextInt();
        System.out.println("---------------------------------------------");

        boolean opcionValida = validarMenuAgregarEditar(opcion);

        while (opcionValida == false) {
            System.out.println("*********************************************");
            System.out.println("[Error][Ingresar las opciones valdias]: 1 | 2");
            System.out.println("*********************************************");
            System.out.print("Ingrese opcion para operacion: ");
            opcion = mp.nextInt();
            opcionValida = validarMenuAgregarEditar(opcion);
        }

        return opcion;
    }

    public static boolean validarMenuAgregarEditar(int opcion) {
        boolean resultado = false;

        if (opcion == 1 || opcion == 2) {
            resultado = true;
        } else {
            resultado =  false;
        }

        return resultado;
    }

    public static void agregarNuevoEquipo(){
        Scanner ae = new Scanner(System.in);
        String categoria;
        String estado;
        int antiguedad;
        boolean opcionValida = false;
        System.out.println("Ingresar los siguientes datos: ");
        System.out.print("Ingresar nuevo codigo: ");//C0006
        String codigoEquipo = ae.nextLine();

        do {
            categoriaEquipo();
            System.out.print("Categoria del equipo: ");
            categoria = ae.nextLine();
            opcionValida = validarCategoriaEquipo(categoria);

        }while (!opcionValida);

        System.out.print("Marca del equipo: ");
        String marca = ae.nextLine();

        System.out.print("Modelo del equipo: ");
        String modelo = ae.nextLine();

        System.out.print("Almacenamiento interno del equipo: ");
        String HDD = ae.nextLine();

        System.out.print("Capacidad de memoria RAM del equipo: ");
        String RAM = ae.nextLine();

        System.out.print("CPU del equipo: ");
        String CPU = ae.nextLine();

        System.out.print("GPU del equipo: ");
        String GPU = ae.nextLine();

        do {
            estadoEquipo();
            System.out.print("Estado actual del equipo: ");
            estado = ae.nextLine();
            opcionValida = validarEstadoEquipo(estado);

        }while (!opcionValida);

        System.out.print("Comentario del equipo: ");
        String comentario = ae.nextLine();

        do {
            antiguedadEquipo();
            System.out.print("Antiguedad del equipo: ");
            antiguedad = ae.nextInt();
            opcionValida = validarAntiguedadEquipo(antiguedad);

        }while (!opcionValida);


        List<nuevoEquipo> listNuevoEquipo = new ArrayList<nuevoEquipo>();
        listNuevoEquipo.add(new nuevoEquipo(codigoEquipo, categoria, marca, modelo, HDD, RAM, CPU, GPU, estado, antiguedad, comentario));
        System.out.println("=============================================");
        System.out.println("NUEVO EQUIPO AGREGADO");
        System.out.println("---------------------------------------------");
        resumenEquipo(listNuevoEquipo);
        System.out.println("---------------------------------------------");
        System.out.println("EXITO AL GUARDAR DATOS DEL NUEVO EQUIPO");
        System.out.println("=============================================");
    }

    public static void editarEquipo(String codigoEquipo){
        Scanner ae = new Scanner(System.in);
        String categoria;
        String estado;
        int antiguedad;
        boolean opcionValida = false;
        System.out.println("Ingresar los siguientes datos: ");

        do {
            categoriaEquipo();
            System.out.print("Categoria del equipo: ");
            categoria = ae.nextLine();
            opcionValida = validarCategoriaEquipo(categoria);

        }while (!opcionValida);

        System.out.print("Marca del equipo: ");
        String marca = ae.nextLine();

        System.out.print("Modelo del equipo: ");
        String modelo = ae.nextLine();

        System.out.print("Almacenamiento interno del equipo: ");
        String HDD = ae.nextLine();

        System.out.print("Capacidad de memoria RAM del equipo: ");
        String RAM = ae.nextLine();

        System.out.print("CPU del equipo: ");
        String CPU = ae.nextLine();

        System.out.print("GPU del equipo: ");
        String GPU = ae.nextLine();

        do {
            estadoEquipo();
            System.out.print("Estado actual del equipo: ");
            estado = ae.nextLine();
            opcionValida = validarEstadoEquipo(estado);

        }while (!opcionValida);

        System.out.print("Comentario del equipo: ");
        String comentario = ae.nextLine();

        do {
            antiguedadEquipo();
            System.out.print("Antiguedad del equipo: ");
            antiguedad = ae.nextInt();
            opcionValida = validarAntiguedadEquipo(antiguedad);

        }while (!opcionValida);

        resumenEquipoEditado(codigoEquipo, categoria, marca, modelo, HDD, RAM, CPU, GPU, estado, antiguedad, comentario);
    }
    public static void resumenEquipo(List<nuevoEquipo> listNuevoEquipo){

        System.out.println("Codigo de equipo: " + listNuevoEquipo.get(0).getCodigoEquipo());
        System.out.println("Categoria: " + listNuevoEquipo.get(0).getCategoria());
        System.out.println("Marca: " + listNuevoEquipo.get(0).getMarca());
        System.out.println("Modelo: " + listNuevoEquipo.get(0).getModelo());
        System.out.println("Almacenamiento: " + listNuevoEquipo.get(0).getHDD());
        System.out.println("Capacidad RAM: " + listNuevoEquipo.get(0).getRAM());
        System.out.println("CPU: " + listNuevoEquipo.get(0).getCPU());
        System.out.println("GPU: " + listNuevoEquipo.get(0).getGPU());
        System.out.println("Estado: " + listNuevoEquipo.get(0).getEstado());
        System.out.println("Antiguedad: " + listNuevoEquipo.get(0).getAntiguedad());
        System.out.println("Comentario: " + listNuevoEquipo.get(0).getComentario());

    }

    public static void resumenEquipoEditado(String codigoEquipo, String categoria, String marca, String modelo, String HDD, String RAM, String CPU,
                                            String GPU, String estado, int antiguedad, String comentario){
        System.out.println("=============================================");
        System.out.println("EDITANDO EQUIPO SELECCIONADO");
        System.out.println("---------------------------------------------");
        nuevoEquipo editar = new nuevoEquipo(codigoEquipo, categoria, marca, modelo, HDD, RAM, CPU,
                GPU, estado, antiguedad, comentario);
        List<nuevoEquipo> listNuevoEquipo = new ArrayList<nuevoEquipo>();
        editar.setCodigoEquipo(codigoEquipo);
        editar.setCategoria(categoria);
        editar.setMarca(marca);
        editar.setModelo(modelo);
        editar.setHDD(HDD);
        editar.setRAM(RAM);
        editar.setCPU(CPU);
        editar.setGPU(GPU);
        editar.setEstado(estado);
        editar.setAntiguedad(antiguedad);
        editar.setComentario(comentario);

        listNuevoEquipo.add(editar);

        resumenEquipo(listNuevoEquipo);
        System.out.println("---------------------------------------------");
        System.out.println("EXITO AL GUARDAR DATOS DEL EQUIPO EDITADO");
        System.out.println("=============================================");
    }

    public static void categoriaEquipo(){
        System.out.println("------------CATEGORIAS DE EQUIPOS------------");
        System.out.println("----Laptop | Desktop | Tablet | Impresora----");
    }

    public static void estadoEquipo(){
        System.out.println("-------------ESTADOS DEL EQUIPOS-------------");
        System.out.println("---------------Nuevo | Antiguo---------------");
    }

    public static void antiguedadEquipo(){
        System.out.println("--------EXPRESAR ANTIGUEDAD EN MESES---------");
        System.out.println("-------CONSIDERAR 0 COMO EQUIPO NUEVO--------");
    }

    public static boolean validarCategoriaEquipo(String opcion) {
        boolean resultado = false;

        if (opcion.equalsIgnoreCase("LAPTOP") ||
                opcion.equalsIgnoreCase("DESKTOP") ||
                opcion.equalsIgnoreCase("TABLET") ||
                opcion.equalsIgnoreCase("IMPRESORA")) {
            resultado = true;
        } else {
            resultado =  false;
        }

        return resultado;
    }

    public static boolean validarEstadoEquipo(String opcion) {
        boolean resultado = false;

        if (opcion.equalsIgnoreCase("NUEVO") ||
                opcion.equalsIgnoreCase("ANTIGUO")) {
            resultado = true;
        } else {
            resultado =  false;
        }

        return resultado;
    }

    public static boolean validarAntiguedadEquipo(int opcion) {
        boolean resultado = false;

        if (opcion >= 0) {
            resultado = true;
        } else {
            resultado =  false;
        }

        return resultado;
    }
}
