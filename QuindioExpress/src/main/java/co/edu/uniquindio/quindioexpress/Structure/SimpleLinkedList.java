package co.edu.uniquindio.quindioexpress.Structure;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class SimpleLinkedList implements Iterable<Integer> {

    private int size;
    private NodeSimple firstNode;

    public SimpleLinkedList() {
        this.size = 0;
        this.firstNode = null;
    }

    public int getSize() {
        return size;
    }

    public NodeSimple getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(NodeSimple firstNode) {
        this.firstNode = firstNode;
    }

    private boolean isEmpty() {
        return firstNode == null;
    }

    public void addFirst(int data) {
        NodeSimple newNode = new NodeSimple(data);

        newNode.setNext(firstNode);
        firstNode = newNode;

        size++;
    }

    public void addLast(int data) {
        NodeSimple newNode = new NodeSimple(data);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            NodeSimple aux = firstNode;

            while (aux.getNext() != null) {
                aux = aux.getNext();
            }

            aux.setNext(newNode);
        }

        size++;
    }

    public void add(int data, int position) {
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

        NodeSimple aux = firstNode;

        for (int i = 0; i < position - 1; i++) {
            aux = aux.getNext();
        }

        NodeSimple newNode = new NodeSimple(data);

        newNode.setNext(aux.getNext());
        aux.setNext(newNode);

        size++;
    }

    public int getNodeValue(int position) {
        if (!isValidIndex(position)) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        NodeSimple aux = firstNode;

        for (int i = 0; i < position; i++) {
            aux = aux.getNext();
        }

        return aux.getData();
    }

    public int getNodePosition(int data) {
        NodeSimple aux = firstNode;
        int position = 0;

        while (aux != null) {
            if (aux.getData() == data) {
                return position;
            }

            aux = aux.getNext();
            position++;
        }

        return -1;
    }

    public boolean isValidIndex(int position) {
        return position >= 0 && position < size;
    }

    public boolean isEmptyList() {
        return firstNode == null;
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

        NodeSimple aux = firstNode;

        while (aux.getNext().getNext() != null) {
            aux = aux.getNext();
        }

        aux.setNext(null);
        size--;
    }

    public void delete(int data) {
        if (isEmpty()) {
            return;
        }

        if (firstNode.getData() == data) {
            deleteFirst();
            return;
        }

        NodeSimple aux = firstNode;

        while (aux.getNext() != null) {
            if (aux.getNext().getData() == data) {
                aux.setNext(aux.getNext().getNext());
                size--;
                return;
            }

            aux = aux.getNext();
        }
    }

    public void modifyNode(int position, int data) {
        if (!isValidIndex(position)) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        NodeSimple aux = firstNode;

        for (int i = 0; i < position; i++) {
            aux = aux.getNext();
        }

        aux.setData(data);
    }

    public void sortList() {
        NodeSimple aux = firstNode;

        while (aux != null) {
            NodeSimple next = aux.getNext();

            while (next != null) {
                if (aux.getData() > next.getData()) {
                    int temp = aux.getData();
                    aux.setData(next.getData());
                    next.setData(temp);
                }

                next = next.getNext();
            }

            aux = aux.getNext();
        }
    }

    public void print() {
        NodeSimple aux = firstNode;

        while (aux != null) {
            System.out.print(aux.getData() + " -> ");
            aux = aux.getNext();
        }

        System.out.println("null");
    }

    public void deleteList() {
        firstNode = null;
        size = 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            private NodeSimple aux = firstNode;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public Integer next() {
                if (aux == null) {
                    throw new NoSuchElementException();
                }

                int data = aux.getData();
                aux = aux.getNext();

                return data;
            }
        };
    }

    public void invertList() {
        if (size <= 1) {
            return;
        }

        firstNode = invert(firstNode, null);
    }

    private NodeSimple invert(NodeSimple actual, NodeSimple anterior) {
        if (actual == null) {
            return anterior;
        }

        NodeSimple siguiente = actual.getNext();

        actual.setNext(anterior);

        return invert(siguiente, actual);
    }

}