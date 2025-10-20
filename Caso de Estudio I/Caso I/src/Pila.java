public class Pila {
    private Nodo tope;

    public void apilar(String valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public String desapilar() {
        if (estaVacia()) {
            return null;
        }
        String valor = tope.valor;
        tope = tope.siguiente;
        return valor;
    }

    public String tope() {
        return (tope != null) ? tope.valor : null;
    }

    public boolean estaVacia() {
        return tope == null;
    }
}
