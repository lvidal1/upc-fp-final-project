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

    public static final int ANCHO_CONTENEDOR_PAGINA = 160;
    public static final int ANCHO_CONTENEDOR_MODULO = 80;

    public static void main(String[] args) {

        List<Equipo> inventario = cargarInventarioInicial();

        int opcion;

        generarEncabezado("SISTEMA DE INVENTARIO 2022", ANCHO_CONTENEDOR_PAGINA);

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
                        generarInformeGeneral(inventario);
                        break;
                    case 5:
                        generarInformeUltimaUbicacion(inventario);
                        break;
                }

            }else{
                System.out.println("Opcion incorrecta. Por favor ingrese una opcion valida. ");
            }

        } while (opcion!=6);

        generarEncabezado("Saliendo del sistema. Tenga un buen dia.", ANCHO_CONTENEDOR_PAGINA);

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

        System.out.println();
        generarEncabezado("MENU PRINCIPAL", ANCHO_CONTENEDOR_MODULO);
        System.out.println();
        System.out.println("1. Agregar equipo");
        System.out.println("2. Editar equipo");
        System.out.println("3. Listar equipos");
        System.out.println("4. Mostrar informe general de estados");
        System.out.println("5. Mostrar informe de ultima ubicacion");
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

        System.out.println("\n"+generarLineaHorizontal(ANCHO_CONTENEDOR_MODULO));
        generarSubEncabezado("EXITO AL GUARDAR DATOS DEL NUEVO EQUIPO",ANCHO_CONTENEDOR_MODULO);

    }

    public static void editarEquipo(List<Equipo> inventario){

        listarEquipos(inventario);

        int equipoIndex = obtenerEquipoPorTeclado(inventario);

        Equipo equipo = leerFormularioEquipo(inventario.get(equipoIndex));

        inventario.set(equipoIndex, equipo);

        System.out.println("\n"+generarLineaHorizontal(ANCHO_CONTENEDOR_MODULO));
        generarSubEncabezado("EXITO AL EDITAR DATOS DEL EQUIPO",ANCHO_CONTENEDOR_MODULO);

    }

    public static Equipo leerFormularioEquipo( Equipo... equipo ){

        Scanner ae = new Scanner(System.in);

        boolean esEditarForm = (equipo != null);
        boolean opcionValida = false;
        int antiguedad;

        String categoria;
        String estado;
        String codigoEquipo;

        if(!esEditarForm){
            generarEncabezado("AGREGAR NUEVO EQUIPO", ANCHO_CONTENEDOR_MODULO);

            System.out.print("\n- Ingresar nuevo codigo: ");//C0006
            codigoEquipo = ae.nextLine();
        }else{
            codigoEquipo = equipo[0].getCodigo();
            generarEncabezado("EDITAR EQUIPO: " + codigoEquipo, ANCHO_CONTENEDOR_MODULO);
        }

        do {
            categoriaEquipo();
            System.out.printf("\n- Categoria del equipo %s :", (esEditarForm ?  "("+equipo[0].getCategoria()+")" : ""));
            categoria = ae.nextLine();
            opcionValida = validarCategoriaEquipo(categoria);

        } while (!opcionValida);

        System.out.printf("- Marca del equipo %s :", (esEditarForm ?  "("+equipo[0].getMarca()+")" : ""));
        String marca = ae.nextLine();

        System.out.printf("- Modelo del equipo %s :", (esEditarForm ?  "("+equipo[0].getModelo()+")" : ""));
        String modelo = ae.nextLine();

        System.out.printf("- Almacenamiento interno del equipo %s :", (esEditarForm ?  "("+equipo[0].getHDD()+")" : ""));
        String HDD = ae.nextLine();

        System.out.printf("- Capacidad de memoria RAM del equipo %s :", (esEditarForm ?  "("+equipo[0].getRAM()+")" : ""));
        String RAM = ae.nextLine();

        System.out.printf("- CPU del equipo %s :", (esEditarForm ?  "("+equipo[0].getCPU()+")" : ""));
        String CPU = ae.nextLine();

        System.out.printf("- GPU del equipo %s :", (esEditarForm ?  "("+equipo[0].getGPU()+")" : ""));
        String GPU = ae.nextLine();

        do {
            estadoEquipo();
            System.out.printf("\n- Estado actual del equipo %s :", (esEditarForm ?  "("+equipo[0].getEstado()+")" : ""));
            estado = ae.nextLine();
            opcionValida = validarEstadoEquipo(estado);

        }while (!opcionValida);

        System.out.printf("\n- Comentario del equipo:");
        String comentario = ae.nextLine();

        do {
            antiguedadEquipo();
            System.out.printf("\n- Antiguedad del equipo (meses) %s :", (esEditarForm ?  "("+equipo[0].getAntiguedad()+")" : ""));
            antiguedad = ae.nextInt();
            opcionValida = validarAntiguedadEquipo(antiguedad);

        }while (!opcionValida);

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
        generarSubEncabezado("CATEGORIAS DE EQUIPOS", ANCHO_CONTENEDOR_MODULO);
        generarSubEncabezado(String.join(" | ", obtenerCategorias()), ANCHO_CONTENEDOR_MODULO);
    }

    public static void estadoEquipo(){
        generarSubEncabezado("ESTADOS DEL EQUIPOS", ANCHO_CONTENEDOR_MODULO);
        generarSubEncabezado(String.join(" | ", obtenerEstados()), ANCHO_CONTENEDOR_MODULO);
    }

    public static void antiguedadEquipo(){
        generarSubEncabezado("EXPRESAR ANTIGUEDAD EN MESES", ANCHO_CONTENEDOR_MODULO);
        generarSubEncabezado("CONSIDERAR 0 COMO EQUIPO NUEVO", ANCHO_CONTENEDOR_MODULO);
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

        generarEncabezado("LISTADO DE EQUIPOS EN EL INVENTARIO", ANCHO_CONTENEDOR_MODULO);

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

    public static void generarInformeGeneral( List<Equipo> inventario){

        generarEncabezado("INFORME GENERAL POR ESTADO", ANCHO_CONTENEDOR_MODULO);
        System.out.println("");

        List<Equipo> equiposDisponible = new ArrayList<>();
        List<Equipo> equiposPerdido = new ArrayList<>();
        List<Equipo> equiposInactivo = new ArrayList<>();
        List<Equipo> equiposEnUso = new ArrayList<>();

        // Agrupar datos por estado
        for (Equipo equipo : inventario) {
            if (equipo.getEstado().equals(ESTADO_DISPONIBLE)) {
                equiposDisponible.add(equipo);
            }
            if (equipo.getEstado().equals(ESTADO_ENUSO)) {
                equiposEnUso.add(equipo);
            }
            if (equipo.getEstado().equals(ESTADO_INACTIVO)) {
                equiposInactivo.add(equipo);
            }
            if (equipo.getEstado().equals(ESTADO_PERDIDO)) {
                equiposPerdido.add(equipo);
            }
        }


        // Mostrar informe de estado
        mostrarTablaDeEstados(equiposDisponible, ESTADO_DISPONIBLE);
        mostrarTablaDeEstados(equiposEnUso, ESTADO_ENUSO);
        mostrarTablaDeEstados(equiposInactivo, ESTADO_INACTIVO);
        mostrarTablaDeEstados(equiposPerdido, ESTADO_PERDIDO);

    }

    public static void mostrarTablaDeEstados(List<Equipo> equipos, String estado){

        generarSubEncabezado("EQUIPOS CON ESTADO: '"+estado+"' | TOTAL: " + equipos.size(), ANCHO_CONTENEDOR_MODULO);

        String tablaFormato = "| %-20s | %-20s | %-20s | %-20s | %-20s | \n";

        System.out.printf("\n" + tablaFormato, "CODIGO", "CATEGORIA", "MARCA", "ANTIGUEDAD (meses)", "RENDIMIENTO");
        System.out.println(generarLineaHorizontal(116));

        for (Equipo equipo : equipos) {

            System.out.printf(tablaFormato,
                    equipo.getCodigo(),
                    equipo.getCategoria(),
                    equipo.getMarca(),
                    equipo.getAntiguedad(),
                    equipo.getRendimiento()
            );

        }

        System.out.print("\n\n");

    }

    public static void generarInformeUltimaUbicacion(List<Equipo> inventario){

        generarEncabezado("INFORME DE ULTIMA UBICACION", ANCHO_CONTENEDOR_MODULO);

        int opcion;
        Scanner sc = new Scanner(System.in);

        System.out.println("\n" +"- Revise el inventario e seleccione un codigo:");
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

            System.out.println("\n- Ingrese codigo a buscar:");
            codigoBuscar = lector.next();

            equipoIndex = buscarEquipo(codigoBuscar, inventario);

            if(equipoIndex == ID_NO_ENCONTRADO){
                System.out.printf("No encontrado equipo '%s'. Revise el codigo e intente nuevamente.", codigoBuscar);
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
        System.out.println("");
        generarEncabezado(equipo.getCodigo()+" | INFORME DE ULTIMA UBICACION - Estado: " + equipo.getEstado(), ANCHO_CONTENEDOR_MODULO);
        // Mostrar datos del equipo
        System.out.printf(tablaFormato, "Marca", equipo.getMarca());
        System.out.printf(tablaFormato, "Categoria", equipo.getCategoria());
        System.out.println(generarLineaHorizontal(ANCHO_CONTENEDOR_MODULO));
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

    public static void generarEncabezado(String titulo, int anchoTitulo){

        int espacios = (anchoTitulo - titulo.length())/2;
        String tituloCentrado;
        if(espacios >= 0){
            tituloCentrado = String.format("%" + espacios + "s" + titulo +  "%" + espacios + "s", "","");
        }else{
            tituloCentrado = titulo;
        }

        System.out.println(generarLineaHorizontal(anchoTitulo));
        System.out.println(tituloCentrado);
        System.out.println(generarLineaHorizontal(anchoTitulo));
    }

    public static void generarSubEncabezado(String titulo, int anchoTitulo){

        int espacios = (anchoTitulo - titulo.length()- 4 )/2;
        String tituloCentrado;
        if(espacios >= 0){
            tituloCentrado = String.format("|" + "-".repeat(espacios) + " " + titulo +  " " + "-".repeat(espacios) + "|");
        }else{
            tituloCentrado = titulo;
        }


        System.out.println(tituloCentrado);

    }

    public static String generarLineaHorizontal(int cantidadSimbolos){
        String linea = "";
        for (int i = 0; i < cantidadSimbolos; i++) {
            linea += "=";
        }
        return linea;
    }
}
