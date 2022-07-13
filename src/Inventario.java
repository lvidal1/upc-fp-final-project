import Integration.nuevoEquipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {

    // Constantes

    public static final String CATEGORIA_DESKTOP = "Desktop";
    public static final String CATEGORIA_IMPRESORA = "Impresora";
    public static final String CATEGORIA_LAPTOP = "Laptop";
    public static final String CATEGORIA_TABLET = "Tablet";

    public static final String ESTADO_DISPONIBLE = "Disponible";
    public static final String ESTADO_ENUSO = "En uso";
    public static final String ESTADO_INACTIVO = "Inactivo";
    public static final String ESTADO_PERDIDO = "Perdido";

    public static final int ID_NO_ENCONTRADO = -1;

    public static void main(String[] args) {

        List<Equipo> inventario = cargarInventarioInicial();

        int opcion;

        System.out.println(generarLineaHorizontal(50) );
        System.out.println("            SISTEMA DE INVENTARIO 2022            ");
        System.out.println(generarLineaHorizontal(50) );

        do {
            opcion = leerOpcionEnMenuPrincipal();

            if (esOpcionValidaEnMenu(opcion)) {

                switch (opcion){
                    case 1:
                        agregarEquipo(inventario);
                        break;
                    case 2:
                        editarEquipo(inventario);
                        break;
                    case 3:
                        listarEquipos(inventario);
                        break;
                    case 4:
                        //
                        break;
                    case 5:
                        generarInformeUltimaUbicacion(inventario);
                        break;
                }

            }else{
                System.out.println("Opcion incorrecta. Por favor ingrese una opcion valida. ");
            }

        } while (opcion!=6);

    }

    public static List<Equipo> cargarInventarioInicial() {

        List<Equipo> inventario = new ArrayList<>();

        String data[][] = {
                {"C0001", "Laptop", "HP", "MODEL-1",  "1TB", "16GB", "Ryzen 5", "-", "Perdido", "15", "2021-05-21",  "Area de direccion", "@jnelson", "2022-05-21", "-"},
                {"C0002", "Impresora", "Epson", "MODEL-1", "-", "-", "-", "-", "Inactivo", "20", "2021-03-21",  "Area de direccion", "@jnelson", "2022-05-21", "-"},
                {"C0003", "Tablet", "HP", "MODEL-1", "256GB", "2GB", "Ryzen 5", "-", "Disponible", "7", "2020-05-29",  "Area de direccion", "@jnelson", "2022-05-21", "-"},
                {"C0004", "Laptop", "Dell", "MODEL-1", "1TB", "16GB", "Ryzen 7", "-", "En uso", "2", "2020-02-21",  "Area de direccion", "@jnelson", "2022-05-21", "-"},
                {"C0005", "Desktop", "HP", "MODEL-1", "2TB", "8GB", "Ryzen 3", "-", "Disponible", "3", "2021-01-01",  "Area de direccion", "@jnelson", "2022-05-21", "-"},
        };

        for (String[] equipo : data) {
            inventario.add(new Equipo(
                    equipo[0],
                    equipo[1],
                    equipo[2],
                    equipo[3],
                    equipo[4],
                    equipo[5],
                    equipo[6],
                    equipo[7],
                    equipo[8],
                    Integer.parseInt(equipo[9]),
                    equipo[10],
                    equipo[11],
                    equipo[12],
                    equipo[13]));
        }

        return inventario;
    }

    public static int leerOpcionEnMenuPrincipal(){

        System.out.println("\n---------------- MENU PRINCIPAL ---------------");
        System.out.println();
        System.out.println("1. Agregar nuevo equipo");
        System.out.println("2. Editar equipo");
        System.out.println("3. Listar equipos");
        System.out.println("4. Mostrar informe general");
        System.out.println("5. Mostrar informe de Última ubicación");
        System.out.println("6. Salir");

        Scanner scanchoice = new Scanner(System.in);
        System.out.println("- Ingrese opcion:");

        return scanchoice.nextInt();
    }

    public static boolean esOpcionValidaEnMenu(int opcion) {
        return (opcion == 1 || opcion == 2 || opcion == 3 || opcion == 4 || opcion == 5 || opcion == 6);
    }

    public static void agregarEquipo(List<Equipo> inventario){

        Equipo equipo = leerFormularioEquipo(null);

        inventario.add(equipo);

        System.out.println("---------------------------------------------");
        System.out.println("EXITO AL GUARDAR DATOS DEL NUEVO EQUIPO");
        System.out.println("=============================================");
    }

    public static void editarEquipo(List<Equipo> inventario){

        listarEquipos(inventario);

        int equipoIndex = obtenerEquipoPorTeclado(inventario);

        Equipo equipo = leerFormularioEquipo(inventario.get(equipoIndex));

        inventario.set(equipoIndex, equipo);

        System.out.println("---------------------------------------------");
        System.out.println("EXITO AL EDITAR DATOS DEL NUEVO EQUIPO");
        System.out.println("=============================================");
    }

    public static Equipo leerFormularioEquipo( Equipo... equipo ){

        Scanner ae = new Scanner(System.in);

        boolean esEditarForm = (equipo.length > 0);
        boolean opcionValida = false;
        int antiguedad;

        String categoria;
        String estado;
        String codigoEquipo;

        if(!esEditarForm){
            System.out.println("Nuevo equipo: ");
            System.out.print("Ingresar nuevo codigo: ");//C0006
            codigoEquipo = ae.nextLine();
        }else{
            codigoEquipo = equipo[0].getCodigo();
            System.out.println("Editando equipo: "+ codigoEquipo);
        }

        do {
            categoriaEquipo();
            System.out.printf("Categoria del equipo %s :", (esEditarForm ?  "("+equipo[0].getCategoria()+")" : ""));
            categoria = ae.nextLine();
            opcionValida = validarCategoriaEquipo(categoria);

        } while (!opcionValida);

        System.out.printf("Marca del equipo %s :", (esEditarForm ?  "("+equipo[0].getMarca()+")" : ""));
        String marca = ae.nextLine();

        System.out.printf("Modelo del equipo %s :", (esEditarForm ?  "("+equipo[0].getModelo()+")" : ""));
        String modelo = ae.nextLine();

        System.out.printf("Almacenamiento interno del equipo %s :", (esEditarForm ?  "("+equipo[0].getHDD()+")" : ""));
        String HDD = ae.nextLine();

        System.out.printf("Capacidad de memoria RAM del equipo %s :", (esEditarForm ?  "("+equipo[0].getRAM()+")" : ""));
        String RAM = ae.nextLine();

        System.out.printf("CPU del equipo %s :", (esEditarForm ?  "("+equipo[0].getCPU()+")" : ""));
        String CPU = ae.nextLine();

        System.out.printf("GPU del equipo %s :", (esEditarForm ?  "("+equipo[0].getGPU()+")" : ""));
        String GPU = ae.nextLine();

        do {
            estadoEquipo();
            System.out.printf("Estado actual del equipo %s :", (esEditarForm ?  "("+equipo[0].getEstado()+")" : ""));
            estado = ae.nextLine();
            opcionValida = validarEstadoEquipo(estado);

        }while (!opcionValida);

        do {
            antiguedadEquipo();
            System.out.printf("Antiguedad del equipo (meses) %s :", (esEditarForm ?  "("+equipo[0].getAntiguedad()+")" : ""));
            antiguedad = ae.nextInt();
            opcionValida = validarAntiguedadEquipo(antiguedad);

        }while (!opcionValida);

        System.out.printf("Comentario del equipo:");
        String comentario = ae.nextLine();

        return new Equipo(
                codigoEquipo,
                categoria,
                marca,
                modelo,
                HDD,
                RAM,
                CPU,
                GPU,
                estado,
                antiguedad,
                comentario , "-" , "-", "-");
    }

    public static void categoriaEquipo(){
        System.out.println("------------CATEGORIAS DE EQUIPOS------------");
        System.out.println("---- " + String.join(" | ", obtenerCategorias()) + " ----");
    }

    public static void estadoEquipo(){
        System.out.println("-------------ESTADOS DEL EQUIPOS-------------");
        System.out.println("--------------- " + String.join(" | ", obtenerEstados()) + " ---------------");
    }

    public static void antiguedadEquipo(){
        System.out.println("--------EXPRESAR ANTIGUEDAD EN MESES---------");
        System.out.println("-------CONSIDERAR 0 COMO EQUIPO NUEVO--------");
    }

    public static boolean validarCategoriaEquipo(String opcion) {
        for (String categoria : obtenerCategorias()) {
            if (opcion.equalsIgnoreCase(categoria)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validarEstadoEquipo(String opcion) {
        for (String estado : obtenerEstados()) {
            if (opcion.equalsIgnoreCase(estado)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validarAntiguedadEquipo(int opcion) {
        return opcion >= 0;
    }

    public static void listarEquipos ( List<Equipo> inventario ){

        String tablaFormato = "| %-20s | %-20s | %-20s | %-20s | %-20s | \n";

        System.out.printf("\n" + tablaFormato, "CODIGO", "CATEGORIA", "MARCA", "ANTIGUEDAD (meses)", "RENDIMIENTO");
        System.out.println(generarLineaHorizontal(116));

        for (Equipo equipo : inventario) {

            System.out.printf(tablaFormato,
                    equipo.getCodigo(),
                    equipo.getCategoria(),
                    equipo.getMarca(),
                    equipo.getAntiguedad(),
                    equipo.getRendimiento()
            );

        }

    }

    public static void generarInformeUltimaUbicacion(List<Equipo> inventario){

        int opcion;
        Scanner sc = new Scanner(System.in);

        System.out.println("- Revise el inventario e seleccione un codigo:");
        listarEquipos(inventario);

        do{

            int equipoIndex = obtenerEquipoPorTeclado(inventario);
            mostrarInformeUltimaUbicacion(inventario.get(equipoIndex));

            System.out.println("\n- Desea ver otro informe?");
            System.out.println("1. Aceptar");
            System.out.println("2. Cancelar");
            opcion = sc.nextInt();

        } while (opcion == 1);

    }

    public static int obtenerEquipoPorTeclado(List<Equipo> inventario){

        int equipoIndex;
        String codigoBuscar;
        Scanner lector = new Scanner(System.in);

        do{

            System.out.println("\n- Ingrese código:");
            codigoBuscar = lector.next();

            equipoIndex = buscarEquipo(codigoBuscar, inventario);

            if(equipoIndex == ID_NO_ENCONTRADO){
                System.out.printf("No encontrado equipo '%s'. Revise el código e intente nuevamente.", codigoBuscar);
            }

        } while(equipoIndex == ID_NO_ENCONTRADO);

        return equipoIndex;
    }

    public static int buscarEquipo(String codigo, List<Equipo> inventario){

        int equipoIndex = ID_NO_ENCONTRADO;

        for (int i = 0; i < inventario.size(); i++) {
            if(inventario.get(i).getCodigo().equals(codigo)){
                equipoIndex = i;
                break;
            }
        }

        return equipoIndex;
    }

    public static void mostrarInformeUltimaUbicacion(Equipo equipo){
        String tablaFormato = "%-20s: %-10s \n";
        // Mostrar encabezado del informe
        System.out.println(generarLineaHorizontal(40));
        System.out.println("ULTIMA UBICACION - Estado: " + equipo.getEstado());
        System.out.println(generarLineaHorizontal(40));
        // Mostrar datos del equipo
        System.out.printf(tablaFormato, "Marca", equipo.getMarca());
        System.out.printf(tablaFormato, "Categoria", equipo.getCategoria());
        System.out.println(generarLineaHorizontal(40));
        // Mostrar datos de la ubicación
        System.out.printf(tablaFormato, "Ubicacion", equipo.getUltimaUbicacion());
        System.out.printf(tablaFormato, "Usuario", equipo.getUsuarioAsignado());
        System.out.printf(tablaFormato, "F. Asignacion", equipo.getFechaAsignado());
    }

    public static String[] obtenerCategorias(){
        String [] categorias = {
                CATEGORIA_DESKTOP,
                CATEGORIA_IMPRESORA,
                CATEGORIA_LAPTOP,
                CATEGORIA_TABLET
        };
        return categorias;
    }

    public static String[] obtenerEstados(){
        String [] categorias = {
                ESTADO_DISPONIBLE,
                ESTADO_ENUSO,
                ESTADO_INACTIVO,
                ESTADO_PERDIDO
        };
        return categorias;
    }

    public static String generarLineaHorizontal(int cantidadSimbolos){
        String linea = "";
        for (int i = 0; i < cantidadSimbolos; i++) {
            linea += "=";
        }
        return linea;
    }
}
