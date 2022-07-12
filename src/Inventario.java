import Integration.nuevoEquipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {

    // Constantes

    public static final String RENDIMIENTO_ALTO = "Alto";
    public static final String RENDIMIENTO_MEDIO = "Medio";
    public static final String RENDIMIENTO_BAJO = "Bajo";

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
                        //
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        listarEquipos(inventario);
                        break;
                    case 4:
                        //
                        break;
                    case 5:
                        //generarInformeUltimaUbicacion(inventario);
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

    public static void generarInformeUltimaUbicacion(String[][] inventario){

        int opcion;
        Scanner sc = new Scanner(System.in);

        System.out.println("- Revise el inventario e seleccione un codigo:");
        //listarEquipos(inventario);

        do{

            String[] equipo = obtenerEquipoPorTeclado(inventario);
            mostrarInformeUltimaUbicacion(equipo);

            System.out.println("\n- Desea ver otro informe?");
            System.out.println("1. Aceptar");
            System.out.println("2. Cancelar");
            opcion = sc.nextInt();

        } while (opcion == 1);

    }

    public static String[] obtenerEquipoPorTeclado(String[][] inventario){

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

        return inventario[equipoIndex];
    }

    public static int buscarEquipo(String codigo, String[][] inventario){

        int equipoIndex = ID_NO_ENCONTRADO;

        for (int i = 0; i < inventario.length; i++) {
            if(obtenerEquipoCodigo(inventario[i]).equals(codigo)){
                equipoIndex = i;
                break;
            }
        }

        return equipoIndex;
    }

    public static void mostrarInformeUltimaUbicacion(String[] equipo){
        String tablaFormato = "%-20s: %-10s \n";
        // Mostrar encabezado del informe
        System.out.println(generarLineaHorizontal(40));
        System.out.println("ULTIMA UBICACION - Estado: " + obtenerEquipoEstado(equipo));
        System.out.println(generarLineaHorizontal(40));
        // Mostrar datos del equipo
        System.out.printf(tablaFormato, "Nombre", obtenerEquipoNombre(equipo));
        System.out.printf(tablaFormato, "Categoria", obtenerEquipoCategoria(equipo));
        System.out.println(generarLineaHorizontal(40));
        // Mostrar datos de la ubicación
        System.out.printf(tablaFormato, "Ubicacion", obtenerEquipoUltimaUbicacion(equipo));
        System.out.printf(tablaFormato, "Usuario", obtenerEquipoUsuarioAsignado(equipo));
        System.out.printf(tablaFormato, "F. Asignacion", obtenerEquipoFechaAsignado(equipo));
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

    public static String obtenerEquipoCodigo(String[] equipo){
        return equipo[0];
    }

    public static String obtenerEquipoNombre(String[] equipo){
        return equipo[1];
    }

    public static String obtenerEquipoCategoria(String[] equipo){
        return equipo[2];
    }

    public static String obtenerEquipoAntiguedad(String[] equipo){
        return equipo[3];
    }

    public static String obtenerEquipoUltimaUbicacion(String[] equipo){
        return equipo[4];
    }

    public static String obtenerEquipoUsuarioAsignado(String[] equipo){
        return equipo[5];
    }

    public static String obtenerEquipoFechaAsignado(String[] equipo){
        return equipo[6];
    }

    public static String obtenerEquipoEstado(String[] equipo){
        return equipo[7];
    }

    public static String obtenerEquipoRendimiento(String[] equipo){
        int meses = Integer.parseInt(obtenerEquipoAntiguedad(equipo));
        if(meses >= 0 && meses < 6 ){
            return RENDIMIENTO_ALTO;
        } else if (meses >= 6 && meses < 12) {
            return RENDIMIENTO_MEDIO;
        } else {
            return RENDIMIENTO_BAJO;
        }
    }

    public static String generarLineaHorizontal(int cantidadSimbolos){
        String linea = "";
        for (int i = 0; i < cantidadSimbolos; i++) {
            linea += "=";
        }
        return linea;
    }
}
