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

        String inventario[][] = {
                {"C0001", "Laptop 1", "Laptop", "7", "Area de direccion", "@jnelson", "2022-05-21", "Disponible"},
                {"C0002", "Laptop 2", "Laptop", "15", "Aula de innovacion", "@sbean", "2022-04-21", "Perdido"},
                {"C0003", "Impresora 2", "Impresora", "20" ,"Aula 1", "@dmcintosh", "2022-03-01", "Inactivo"},
                {"C0004", "Tablet 2", "Tablet", "1", "Aula 2", "@sbean", "2022-03-31","Disponible"},
                {"C0005", "PC 1", "Desktop", "4", "Aula 3", "@hcosta", "2022-05-10", "En uso"},
        };

        //listarEquipos(inventario);
        generarInformeUltimaUbicacion(inventario);

    }

    public static void listarEquipos ( String inventario[][] ){

        String tablaFormato = "| %-20s | %-20s | %-20s | %-20s | %-20s | \n";

        System.out.printf("\n" + tablaFormato, "CODIGO","NOMBRE","CATEGORIA","ANTIGUEDAD (meses)", "RENDIMIENTO");
        System.out.println(generarLineaHorizontal(116));

        for (String[] equipo : inventario) {

            System.out.printf(tablaFormato,
                obtenerEquipoCodigo(equipo),
                obtenerEquipoNombre(equipo),
                obtenerEquipoCategoria(equipo),
                obtenerEquipoAntiguedad(equipo),
                obtenerEquipoRendimiento(equipo)
            );

        }

    }

    public static void generarInformeUltimaUbicacion(String[][] inventario){

        int opcion;
        Scanner sc = new Scanner(System.in);

        System.out.println("- Revise el inventario e seleccione un codigo:");
        listarEquipos(inventario);

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
