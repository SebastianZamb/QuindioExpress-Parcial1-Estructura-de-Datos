package co.edu.uniquindio.quindioexpress.Structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Lista enlazada simple que permite guardar datos de cualquier tipo
public class SimpleLinkedList<T> implements Iterable<T> {

    // Cantidad de elementos y referencia al primer nodo
    private int size;
    private NodeSimple<T> firstNode;

    // Inicializa la lista vacia
    public SimpleLinkedList() {
        this.size = 0;
        this.firstNode = null;
    }

    // Devuelve la cantidad de elementos de la lista
    public int getSize() {
        return size;
    }

    // Obtiene el primer nodo de la lista
    public NodeSimple<T> getFirstNode() {
        return firstNode;
    }

    // Cambia el primer nodo y actualiza el tamaño de la lista
    public void setFirstNode(NodeSimple<T> firstNode) {
        this.firstNode = firstNode;
        size = 0;

        NodeSimple<T> aux = firstNode;

        while (aux != null) {
            size++;
            aux = aux.getNext();
        }
    }

    // Comprueba si la lista esta vacia
    public boolean isEmpty() {
        return firstNode == null;
    }

    // Agrega un elemento al principio de la lista
    public void addFirst(T data) {
        NodeSimple<T> newNode = new NodeSimple<>(data);
        newNode.setNext(firstNode);
        firstNode = newNode;
        size++;
    }

    // Agrega un elemento al final de la lista
    public void addLast(T data) {
        NodeSimple<T> newNode = new NodeSimple<>(data);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            NodeSimple<T> aux = firstNode;

            while (aux.getNext() != null) {
                aux = aux.getNext();
            }

            aux.setNext(newNode);
        }

        size++;
    }

    // Agrega un elemento al final de la lista
    public void add(T data) {
        addLast(data);
    }

    // Inserta un elemento en la posicion indicada
    public void add(T data, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Posicion invalida.");
        }

        if (position == 0) {
            addFirst(data);
            return;
        }

        if (position == size) {
            addLast(data);
            return;
        }

        NodeSimple<T> aux = firstNode;

        for (int i = 0; i < position - 1; i++) {
            aux = aux.getNext();
        }

        NodeSimple<T> newNode = new NodeSimple<>(data);
        newNode.setNext(aux.getNext());
        aux.setNext(newNode);
        size++;
    }

    // Obtiene el dato que se encuentra en la posicion indicada
    public T getNodeValue(int position) {
        if (!isValidIndex(position)) {
            throw new IndexOutOfBoundsException("Posicion invalida.");
        }

        NodeSimple<T> aux = firstNode;

        for (int i = 0; i < position; i++) {
            aux = aux.getNext();
        }

        return aux.getData();
    }

    // Comprueba si una posicion pertenece a la lista
    public boolean isValidIndex(int position) {
        return position >= 0 && position < size;
    }

    // Elimina el primer elemento de la lista
    public void deleteFirst() {
        if (isEmpty()) {
            return;
        }

        firstNode = firstNode.getNext();
        size--;
    }

    // Elimina el ultimo elemento de la lista
    public void deleteLast() {
        if (isEmpty()) {
            return;
        }

        if (size == 1) {
            firstNode = null;
            size = 0;
            return;
        }

        NodeSimple<T> aux = firstNode;

        while (aux.getNext().getNext() != null) {
            aux = aux.getNext();
        }

        aux.setNext(null);
        size--;
    }

    // Elimina la primera coincidencia del dato indicado
    public boolean delete(T data) {
        if (isEmpty()) {
            return false;
        }

        if ((firstNode.getData() == null && data == null)
                || (firstNode.getData() != null
                && firstNode.getData().equals(data))) {
            deleteFirst();
            return true;
        }

        NodeSimple<T> aux = firstNode;

        while (aux.getNext() != null) {
            T currentData = aux.getNext().getData();

            if ((currentData == null && data == null)
                    || (currentData != null && currentData.equals(data))) {
                aux.setNext(aux.getNext().getNext());
                size--;
                return true;
            }

            aux = aux.getNext();
        }

        return false;
    }

    // Elimina todos los elementos de la lista
    public void deleteList() {
        firstNode = null;
        size = 0;
    }

    // Permite recorrer los elementos de la lista usando un for-each
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            // Nodo actual durante el recorrido
            private NodeSimple<T> aux = firstNode;

            // Indica si quedan elementos por recorrer
            @Override
            public boolean hasNext() {
                return aux != null;
            }

            // Devuelve el dato actual y avanza al siguiente nodo
            @Override
            public T next() {
                if (aux == null) {
                    throw new NoSuchElementException();
                }

                T data = aux.getData();
                aux = aux.getNext();

                return data;
            }
        };
    }
}