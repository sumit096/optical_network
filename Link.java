import java.util.ArrayList;

public class Link {
    public static ArrayList<Link> links = new ArrayList<>();

    private String name;
    private Node node1;
    private Node node2;
    
    private Fiber [] fiber = new Fiber[2];

    public Link(Node node1, Node node2) {
        //System.out.println(node1.getName() + " and " + node2.getName() + " are connected");
        this.name = node1.getName() + "-" + node2.getName();
        this.node1 = node1;
        this.node2 = node2;

        fiber[0] = new Fiber("fib-1", 0);
        fiber[1] = new Fiber("fib-2", 1);
       

        node1.addAdjacencyNode(node2);
        node2.addAdjacencyNode(node1);

        node1.addLink(this);
        node2.addLink(this);

        links.add(this);
    }

    public String getName() {
        return this.name;
    }

    public Fiber getFib(int direction) {
        for (Fiber fib : this.fiber) {
            if (fib.getDirection() == direction) {
                return fib;
            }
        }

        return null;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }
}
