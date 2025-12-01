public class NodoAVL {

    private int llave;
    private NodoAVL hijoIzquierdo;
    private NodoAVL hijoDerecho;
    private int altura;

    public NodoAVL(int llave) {
        this.llave = llave;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.altura = 1;
    }

    public int getLlave() { return llave; }
    public NodoAVL getHijoIzquierdo() { return hijoIzquierdo; }
    public NodoAVL getHijoDerecho() { return hijoDerecho; }
    public int getAltura() { return altura; }

    public void setHijoIzquierdo(NodoAVL hijoIzquierdo) { this.hijoIzquierdo = hijoIzquierdo; }
    public void setHijoDerecho(NodoAVL hijoDerecho) { this.hijoDerecho = hijoDerecho; }
    public void setAltura(int altura) { this.altura = altura; }

    public int evaluarAltura(NodoAVL n) {
        return (n == null) ? 0 : n.getAltura();
    }

    public int evaluarBalance(NodoAVL n) {
        return (n == null) ? 0 :
                evaluarAltura(n.getHijoIzquierdo()) - evaluarAltura(n.getHijoDerecho());
    }

    public void actualizarAltura() {
        this.altura = Math.max(
                evaluarAltura(hijoIzquierdo),
                evaluarAltura(hijoDerecho)
        ) + 1;
    }

    //ROTACIONES DEL ARBOL AVL

    // Rotación Derecha (RR)
    public NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.getHijoIzquierdo();
        NodoAVL T2 = x.getHijoDerecho();

        x.setHijoDerecho(y);
        y.setHijoIzquierdo(T2);

        y.actualizarAltura();
        x.actualizarAltura();

        return x;
    }

    // Rotación Izquierda (LL)
    public NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.getHijoDerecho();
        NodoAVL T2 = y.getHijoIzquierdo();

        y.setHijoIzquierdo(x);
        x.setHijoDerecho(T2);

        x.actualizarAltura();
        y.actualizarAltura();

        return y;
    }

    // Rotación Izquierda-Derecha (LR)
    public NodoAVL rotarIzquierdaDerecha(NodoAVL nodo) {
        nodo.setHijoIzquierdo(rotarIzquierda(nodo.getHijoIzquierdo()));
        return rotarDerecha(nodo);
    }

    // Rotación Derecha-Izquierda (RL)
    public NodoAVL rotarDerechaIzquierda(NodoAVL nodo) {
        nodo.setHijoDerecho(rotarDerecha(nodo.getHijoDerecho()));
        return rotarIzquierda(nodo);
    }
}
