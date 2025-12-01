public class AVL {

    private NodoAVL raiz;

    public AVL() {
        raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public NodoAVL buscar(int llaveBuscar) {
        NodoAVL nodoTemp = raiz;
        while (nodoTemp != null) {
            if (llaveBuscar == nodoTemp.getLlave()) return nodoTemp;
            nodoTemp = (llaveBuscar < nodoTemp.getLlave())
                    ? nodoTemp.getHijoIzquierdo()
                    : nodoTemp.getHijoDerecho();
        }
        return null;
    }

    // INSERTAR
    public void insertar(int llave) {
        raiz = insertarRec(raiz, llave);
    }

    private NodoAVL insertarRec(NodoAVL nodo, int llave) {
        if (nodo == null) return new NodoAVL(llave);

        if (llave < nodo.getLlave()) {
            nodo.setHijoIzquierdo(insertarRec(nodo.getHijoIzquierdo(), llave));
        } else if (llave > nodo.getLlave()) {
            nodo.setHijoDerecho(insertarRec(nodo.getHijoDerecho(), llave));
        } else {
            return nodo; // No duplicados
        }

        nodo.actualizarAltura();

        int balance = nodo.evaluarBalance(nodo);

        NodoAVL helper = new NodoAVL(0);

        // Casos de desbalance
        if (balance > 1 && llave < nodo.getHijoIzquierdo().getLlave())
            return helper.rotarDerecha(nodo); // LL

        if (balance < -1 && llave > nodo.getHijoDerecho().getLlave())
            return helper.rotarIzquierda(nodo); // RR

        if (balance > 1 && llave > nodo.getHijoIzquierdo().getLlave())
            return helper.rotarIzquierdaDerecha(nodo); // LR

        if (balance < -1 && llave < nodo.getHijoDerecho().getLlave())
            return helper.rotarDerechaIzquierda(nodo); // RL

        return nodo;
    }

    // ELiminar o remover
    public void eliminar(int llave) {
        raiz = eliminarRec(raiz, llave);
    }

    private NodoAVL eliminarRec(NodoAVL nodo, int llave) {
        if (nodo == null) return null;

        if (llave < nodo.getLlave()) {
            nodo.setHijoIzquierdo(eliminarRec(nodo.getHijoIzquierdo(), llave));
        } else if (llave > nodo.getLlave()) {
            nodo.setHijoDerecho(eliminarRec(nodo.getHijoDerecho(), llave));
        } else {
            // Caso 1: sin hijos
            if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null)
                return null;

            // Caso 2: un hijo
            if (nodo.getHijoIzquierdo() == null)
                return nodo.getHijoDerecho();
            if (nodo.getHijoDerecho() == null)
                return nodo.getHijoIzquierdo();

            // Caso 3: dos hijos (sucesor)
            NodoAVL sucesor = getSucesor(nodo);
            nodo = sucesor;
        }

        nodo.actualizarAltura();
        int balance = nodo.evaluarBalance(nodo);
        NodoAVL helper = new NodoAVL(0);

        // Rebalanceo después de eliminar
        if (balance > 1 && nodo.getHijoIzquierdo().evaluarBalance(nodo.getHijoIzquierdo()) >= 0)
            return helper.rotarDerecha(nodo); // LL

        if (balance > 1 && nodo.getHijoIzquierdo().evaluarBalance(nodo.getHijoIzquierdo()) < 0)
            return helper.rotarIzquierdaDerecha(nodo); // LR

        if (balance < -1 && nodo.getHijoDerecho().evaluarBalance(nodo.getHijoDerecho()) <= 0)
            return helper.rotarIzquierda(nodo); // RR

        if (balance < -1 && nodo.getHijoDerecho().evaluarBalance(nodo.getHijoDerecho()) > 0)
            return helper.rotarDerechaIzquierda(nodo); // RL

        return nodo;
    }

    private NodoAVL getSucesor(NodoAVL nodoBorrar) {
        NodoAVL actual = nodoBorrar.getHijoDerecho();
        while (actual.getHijoIzquierdo() != null) {
            actual = actual.getHijoIzquierdo();
        }
        return new NodoAVL(actual.getLlave());
    }

    // Recorridos del arbol AVL
    public void enOrden() { enOrden(raiz); }
    public void preOrden() { preOrden(raiz); }
    public void postOrden() { postOrden(raiz); }

    private void enOrden(NodoAVL n) {
        if (n != null) {
            enOrden(n.getHijoIzquierdo());
            System.out.print(n.getLlave() + " ");
            enOrden(n.getHijoDerecho());
        }
    }

    private void preOrden(NodoAVL n) {
        if (n != null) {
            System.out.print(n.getLlave() + " ");
            preOrden(n.getHijoIzquierdo());
            preOrden(n.getHijoDerecho());
        }
    }

    private void postOrden(NodoAVL n) {
        if (n != null) {
            postOrden(n.getHijoIzquierdo());
            postOrden(n.getHijoDerecho());
            System.out.print(n.getLlave() + " ");
        }
    }
}
