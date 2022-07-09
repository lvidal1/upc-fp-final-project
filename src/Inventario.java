import java.util.LinkedList;
import java.util.List;

public class Inventario {

    public static void main(String[] args) {

        String inventario[][] = {
                {"C0001", "Laptop 1", "Laptop", "7"},
        };

        listarEquipos(inventario);

    }

    public static void listarEquipos ( String inventario[][] ){

        String tablaFormato = "| %-20s | %-20s | %-20s | %-20s | %-20s | \n";

        System.out.println(generarLineaHorizontal(116));
        System.out.printf(tablaFormato, "CODIGO","NOMBRE","CATEGORIA","ANTIGUEDAD (meses)", "RENDIMIENTO");
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
            return "Alto";
        } else if (meses >= 6 && meses < 12) {
            return "Medio";
        } else {
            return "Bajo";
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
