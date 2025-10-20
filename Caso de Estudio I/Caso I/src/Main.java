 void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int opcion;

    do {
        System.out.println("\nIndique la operación que desea llevar a cabo");
        System.out.println("1. Validar una expresión aritmética");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
        opcion = sc.nextInt();
        sc.nextLine(); // limpiar el sacnner

        switch (opcion) {
            case 1:
                System.out.println("Ingrese la expresión aritmética:");
                String expresion = sc.nextLine();
                Validaciones.validarExpresion(expresion);
                break;
            case 2:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    } while (opcion != 2);
}