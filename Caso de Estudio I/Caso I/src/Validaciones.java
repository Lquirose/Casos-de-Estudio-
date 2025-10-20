import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Validaciones {

    private static final Set<String> OPERADORES = Set.of("+", "-", "*", "/"); //Asigno los operadores en la lista.

    public static void validarExpresion(String expresion) { //Acá se hace el sistema de las validaciones
        Pila pilaParentesis = new Pila(); //Creo mi objeto(pila en este caso)

        List<String> tokens = tokenizar(expresion); //Hago mi lista de tokens
        if (tokens.isEmpty()) {
            System.out.println("Error: expresión vacía.");
            return;
        }

        boolean ultimoFueOperador = true;
        int pos = 0; //Esta es la posicion del token, es como los indices de los arrays.

        for (String token : tokens) { //se va iterando para que analice cada uno de los que separó antes.
            pos++;

            if (token.equals("(")) { //Acá se revisa si le sigue o no un numero al parentesis (
                pilaParentesis.apilar(token);
                ultimoFueOperador = true;
            }
            else if (token.equals(")")) {
                if (pilaParentesis.estaVacia()) { //Aqui es ver si antes del ) hay algo porque si no lo da como error
                    System.out.println("Error: paréntesis de cierre sin apertura en posición " + pos);
                    return;
                }
                pilaParentesis.desapilar();
                ultimoFueOperador = false; //Acá es si en efecto hay una apertura!
            }
            else if (OPERADORES.contains(token)) { //Este revisa si tengo más de un operador llamando el set OPERADORES del inicio o sea es como si tuviera una lista array con ellos adentro.
                if (ultimoFueOperador) {
                    System.out.println("Error: operador doble '" + token + "' en posición " + pos);
                    return;
                }
                ultimoFueOperador = true;
            }
            else if (token.matches("[0-9]+") /*revisa los numeros*/ || token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) { //letras de la a a la z
                if (!ultimoFueOperador) {
                    System.out.println("Error: falta operador antes de '" + token + "' en posición " + pos); //Esto es si están separados o sea 2 0 en lugar de 20
                    return;
                }
                ultimoFueOperador = false;
            }
            else {
                System.out.println("Error: token no reconocido '" + token + "' en posición " + pos);
                return; //El último si ya es algo que no está acá.
            }
        }

        if (ultimoFueOperador) { //Que no termine con un +,-,/ ó *
            System.out.println("Error: la expresión termina con un operador.");
            return;
        }

        if (!pilaParentesis.estaVacia()) {
            System.out.println("Error: falta cerrar un paréntesis.");
            return;
        }

        System.out.println("La expresión es válida.");
    }

    private static List<String> tokenizar(String expresion) { //Aquí es donde se separan, es como los que leen los chars pero para cualquier string o conjunto, entonces si ingreso en el menu 3+2 el tokens separa la expresionesión en 3, +, 2 y los va a analizando
        List<String> tokens = new ArrayList<>(); //Lista donde guardo los operadores de arriba.

        StringBuilder actual = new StringBuilder(new String()); //Profe acá yo lo que tenía era esto

        /*
        String actual = new String();
           for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (Character.isWhitespace(c)) continue;

            if ("()+-".indexOf(c) != -1) {
                if (!actual.isEmpty()) {
                    tokens.add(actual.toString());
                    actual = "";
                }
                tokens.add(String.valueOf(c));
            } else {
                actual += c; pero acá e daba de sugerencia cambiarlo a StringBuilder y eso hice,
                al parecer es más eficiente porque no "crea" un objeto cada vez como el string normal.
            }
        }

                if (!actual.isEmpty()) {
                tokens.add(actual);
                }

                        return tokens;
            }

        */

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (Character.isWhitespace(c)) continue; //ignorar espacios

            if ("()+-*/".indexOf(c) != -1) { //Acá voy agregando las partes de la expresion. y corriendo los que ya etsán para ir cambiando el actual, como si fuera una dobre sustitucion.
                if (!actual.isEmpty()) {
                    tokens.add(actual.toString());
                    actual.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else {
                actual.append(c);
            }

        }

        if (!actual.isEmpty()) {
            tokens.add(actual.toString());
        }

        return tokens; //Lista completa
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese una expresión aritmética:");
        String expresion = sc.nextLine();

        validarExpresion(expresion);
    }
}

