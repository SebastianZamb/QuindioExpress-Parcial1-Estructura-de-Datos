package co.edu.uniquindio.quindioexpress.Structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<T> implements Iterable<T> {

    private int size;
    private NodeSimple<T> firstNode;

    public SimpleLinkedList() {
        this.size = 0;
        this.firstNode = null;
    }

    public int getSize() {
        return size;
    }

    public NodeSimple<T> getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(NodeSimple<T> firstNode) {
        this.firstNode = firstNode;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    public void addFirst(T data) {
        NodeSimple<T> newNode = new NodeSimple<>(data);
        newNode.setNext(firstNode);
        firstNode = newNode;
        size++;
    }


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


    public void add(T data) {
        addLast(data);
    }

    public void add(T data, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Invalid position");
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

    public T getNodeValue(int position) {
        if (!isValidIndex(position)) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        NodeSimple<T> aux = firstNode;
        for (int i = 0; i < position; i++) {
            aux = aux.getNext();
        }

        return aux.getData();
    }

    public boolean isValidIndex(int position) {
        return position >= 0 && position < size;
    }

    public void deleteFirst() {
        if (isEmpty()) {
            return;
        }
        firstNode = firstNode.getNext();
        size--;
    }

    public void deleteLast() {
        if (isEmpty()) {
            return;
        }

        if (size == 1) {
            firstNode = null;
            size--;
            return;
        }

        NodeSimple<T> aux = firstNode;
        while (aux.getNext().getNext() != null) {
            aux = aux.getNext();
        }

        aux.setNext(null);
        size--;
    }

    public void deleteList() {
        firstNode = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodeSimple<T> aux = firstNode;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

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