package co.edu.uniquindio.quindioexpress.Structure;

public class NodeSimple<T> {

    private T data;
    private NodeSimple<T> next;

    public NodeSimple(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeSimple<T> getNext() {
        return next;
    }

    public void setNext(NodeSimple<T> next) {
        this.next = next;
    }
}