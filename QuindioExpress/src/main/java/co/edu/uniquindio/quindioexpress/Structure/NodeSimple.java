package co.edu.uniquindio.quindioexpress.Structure;

public class NodeSimple {

    private int data;
    private NodeSimple next;

    public NodeSimple(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public NodeSimple getNext() {
        return next;
    }

    public void setNext(NodeSimple next) {
        this.next = next;
    }
}