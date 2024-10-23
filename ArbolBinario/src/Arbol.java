import javax.swing.*;
import java.awt.*;
public class Arbol extends JPanel {
    Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    public void agregarNodo(int valor) {
        raiz = agregarRecursivo(raiz, valor);
    }

    private Nodo agregarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }

        if (valor < actual.valor) {
            actual.izquierdo = agregarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = agregarRecursivo(actual.derecho, valor);
        }

        return actual;
    }

    public void recorridoInOrden(Nodo nodo) {
        if (nodo != null) {
            recorridoInOrden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            recorridoInOrden(nodo.derecho);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        dibujarArbol(g, raiz, getWidth() / 2, 30, getWidth() / 4);
    }

    private void dibujarArbol(Graphics g, Nodo nodo, int x, int y, int xOffset) {
        if (nodo == null) return;

        g.setColor(Color.BLACK);
        g.fillOval(x - 15, y - 15, 30, 30); // Dibuja un círculo para el nodo
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(nodo.valor), x - 5, y + 5); // Dibuja el valor del nodo

        if (nodo.izquierdo != null) {
            g.drawLine(x, y, x - xOffset, y + 50); // Dibuja línea hacia la izquierda
            dibujarArbol(g, nodo.izquierdo, x - xOffset, y + 50, xOffset / 2); // Llama recursivamente para el subárbol izquierdo
        }

        if (nodo.derecho != null) {
            g.drawLine(x, y, x + xOffset, y + 50); // Dibuja línea hacia la derecha
            dibujarArbol(g, nodo.derecho, x + xOffset, y + 50, xOffset / 2); // Llama recursivamente para el subárbol derecho
        }
    }

    public static void main(String[] args) {
        Arbol arbol = new Arbol();

        arbol.agregarNodo(60);
        arbol.agregarNodo(41);
        arbol.agregarNodo(74);
        arbol.agregarNodo(16);
        arbol.agregarNodo(53);
        arbol.agregarNodo(65);
        arbol.agregarNodo(25);
        arbol.agregarNodo(46);
        arbol.agregarNodo(55);
        arbol.agregarNodo(63);
        arbol.agregarNodo(70);
        arbol.agregarNodo(42);
        arbol.agregarNodo(62);
        arbol.agregarNodo(64);

        JFrame frame = new JFrame("Árbol Binario Gráfico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(arbol);
        frame.setVisible(true);

        System.out.println("Oeden De Recorrido Del Arbol:");
        arbol.recorridoInOrden(arbol.raiz);
    }
}
