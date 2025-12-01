import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AVL arbol = new AVL();
        Scanner scanner = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("1. Insertar nodo");
            System.out.println("2. Eliminar nodo");
            System.out.println("3. Buscar nodo");
            System.out.println("4. Mostrar en orden");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese valor: ");
                    arbol.insertar(scanner.nextInt());
                    break;

                case 2:
                    System.out.print("Ingrese valor: ");
                    arbol.eliminar(scanner.nextInt());
                    break;

                case 3:
                    System.out.print("Ingrese valor: ");
                    System.out.println(
                            arbol.buscar(scanner.nextInt()) != null
                                    ? "Encontrado"
                                    : "No encontrado"
                    );
                    break;

                case 4:
                    arbol.enOrden();
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Saliendo");
                    break;
            }

        } while (opcion != 5);

        scanner.close();
    }
}
