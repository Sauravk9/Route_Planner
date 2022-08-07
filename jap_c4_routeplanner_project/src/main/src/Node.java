public class Node {

    private Route data;
    private Node nodeNext;

    public Node(Route data) {
        this.data = data;

    }
    public Route getData()
    {
        return data;
    }

    public void setData(Route data) {
        this.data = data;
    }

    public Node getNodeNext() {
        return nodeNext;
    }

    public void setNodeNext(Node nodeNext) {
        this.nodeNext = nodeNext;
    }
}
