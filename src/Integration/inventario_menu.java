package Integration;

import java.util.Scanner;
public class inventario_menu {
    public static void main(String[] args) {

        int choice;
        do {
            System.out.println("----------------MENU---------------");
            System.out.println();
            System.out.println("1. Agregar nuevo equipo");
            System.out.println("2. Editar equipo");
            System.out.println("3. Listar equipos");
            System.out.println("4. Mostrar informe general");
            System.out.println("5. Mostrar informe de ubicaciones");
            System.out.println("6. Salir");

            Scanner scanchoice = new Scanner(System.in);
            choice = scanchoice.nextInt();

            if (!esOpcionValida(choice)) {
                System.out.println("Opcion incorrecta, por favor ingrese una opcion valida: ");
                choice = scanchoice.nextInt();
            }else {
                if (choice != 6) {
                    System.out.println("Si desea leer el menu anterior, presione cualquier numero");
                    choice = scanchoice.nextInt();
                }
            }
        } while (choice!=6);



    }
    public static boolean esOpcionValida(int opcion) {

        if ((opcion == 1 || opcion == 2 || opcion == 3
                || opcion == 4 || opcion == 5 || opcion == 6 || opcion < 0)) {
            return true;
        }else{
            return false;
        }
    }
}
