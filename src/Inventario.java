public class Inventario {

    // Constantes

    public static final String RENDIMIENTO_ALTO = "Alto";
    public static final String RENDIMIENTO_MEDIO = "Medio";
    public static final String RENDIMIENTO_BAJO = "Bajo";

    public static void main(String[] args) {

        String inventario[][] = {
                {"C0001", "Laptop 1", "Laptop", "7"},
                {"C0002", "Laptop 2", "Laptop", "15"},
                {"C0003", "Impresora 2", "Impresora", "9"},
                {"C0004", "Tablet 2", "Tablet", "1"},
                {"C0005", "PC 1", "Desktop", "4"},
        };

        listarEquipos(inventario);

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
