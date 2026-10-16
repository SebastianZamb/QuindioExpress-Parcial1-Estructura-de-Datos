package co.edu.uniquindio.quindioexpress.Structure;

// Clase que representa un nodo de una lista enlazada
public class NodeSimple<T> {

    // Dato que guarda el nodo y referencia al siguiente
    private T data;
    private NodeSimple<T> next;

    // Constructor para inicializar el dato y el siguiente nodo
    public NodeSimple(T data) {
        this.data = data;
        this.next = null;
    }

    // Obtiene el dato del nodo
    public T getData() {
        return data;
    }

    // Modifica el dato del nodo
    public void setData(T data) {
        this.data = data;
    }

    // Obtiene el siguiente nodo
    public NodeSimple<T> getNext() {
        return next;
    }

    // Modifica la referencia al siguiente nodo
    public void setNext(NodeSimple<T> next) {
        this.next = next;
    }
}