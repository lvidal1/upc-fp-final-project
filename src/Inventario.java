import java.util.LinkedList;
import java.util.List;

public class Inventario {

    public static void main(String[] args) {

        String inventario[][] = {
                {
                    "C0001", "Laptop 1", "Laptop", "3", "Alto"
                }
        };

        listarEquipos(inventario);

    }

    public static void listarEquipos ( String inventario[][] ){

        System.out.printf("| %20s | %30s | %20s | %20s | %20s | \n", "CODIGO","NOMBRE","CATEGORIA","ANTIGUEDAD", "RENDIMIENTO");

        for (String[] equipo : inventario) {
            System.out.printf("| %20s | %30s | %20s | %20s | %20s |\n",
                    obtenerEquipoCodigo(equipo),
                    obtenerEquipoNombre(equipo),
                    obtenerEquipoCategoria(equipo),
                    obtenerEquipoCodigo(equipo),
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
        return equipo[4];
    }


}
