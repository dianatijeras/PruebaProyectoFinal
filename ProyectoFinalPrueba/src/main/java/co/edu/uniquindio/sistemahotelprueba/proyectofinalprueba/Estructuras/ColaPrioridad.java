package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras;


import java.util.ArrayList;
import java.util.List;

public class ColaPrioridad<T extends Comparable<T>> {

    private final ArrayList<T> heap;

    public ColaPrioridad() {
        this.heap = new ArrayList<>();
    }

    public void insertar(T elemento) {
        heap.add(elemento);
        heapifyUp(heap.size() - 1);
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (heap.get(i).compareTo(heap.get(padre)) < 0) {
                intercambiar(i, padre);
                i = padre;
            } else {
                break;
            }
        }
    }

    public T extraerMinimo() {
        if (estaVacia()) return null;

        T minimo = heap.get(0);

        T ultimo = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, ultimo);
            heapifyDown(0);
        }
        return minimo;
    }

    private void heapifyDown(int i) {
        int n = heap.size();
        while (true) {
            int menor = i;
            int hijoIzq = 2 * i + 1;
            int hijoDer = 2 * i + 2;

            if (hijoIzq < n && heap.get(hijoIzq).compareTo(heap.get(menor)) < 0) {
                menor = hijoIzq;
            }
            if (hijoDer < n && heap.get(hijoDer).compareTo(heap.get(menor)) < 0) {
                menor = hijoDer;
            }

            if (menor != i) {
                intercambiar(i, menor);
                i = menor;
            } else {
                break;
            }
        }
    }

    public T peek() {
        return estaVacia() ? null : heap.get(0);
    }

    public List<T> obtenerTodos() {
        return new ArrayList<>(heap);
    }

    public void vaciar() {
        heap.clear();
    }

    private void intercambiar(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean estaVacia() {
        return heap.isEmpty();
    }

    public int getTamanio() {
        return heap.size();
    }

    public ArrayList<T> getHeap() {
        return heap;
    }

    @Override
    public String toString() {
        return "ColaPrioridad{" +
                "heap=" + heap +
                '}';
    }
}

