import java.util.*;

public class LightPath {
    public static int noOfLightPathsReq = 0;
    public static int noOfLightPathsCreated = 0;
    public static ArrayList<LightPath> lightPaths = new ArrayList<>();

    private Node src;
    private Node dst;
    private int wavLen;

    public LightPath(IP_Router src, IP_Router dst) {
        System.out.println("Requested to create a lightpath from " + src.getName() + " to " + dst.getName());
        this.src = src;
        this.dst = dst;

        lightPaths.add(this);
        noOfLightPathsReq++;
    }

    public LightPath(Node src, Node dst) {
        this.src = src;
        this.dst = dst;

        lightPaths.add(this);
        noOfLightPathsReq++;
    }

    



    public void findLightPath() {
        Node currNode = src;
        ArrayList<Node> tempPath = new ArrayList<>();
        ArrayList<Node> gray = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> parentRelateMap = new HashMap<>();

        gray.add(src);
        List<Node> adjacencyNodes = currNode.getConnectedNodes();

        if(adjacencyNodes == null || adjacencyNodes.get(0) == null) {
            System.out.println("Node " + currNode.getName() + " is not connected to the WDM network");

            return;
        }

        currNode = adjacencyNodes.get(0);
        gray.add(currNode);
        queue.add(currNode);

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            for (Node n : node.getConnectedNodes()) {
                if (!gray.contains(n)) {
                    for (Link link : node.getLinks()) {
                        if (link.getFib(0).isWavLensFree() && (link.getNode1() == n || link.getNode2() == n)) {
                            if (!gray.contains(n)) {
                                queue.add(n);
                                gray.add(n);
                                parentRelateMap.put(n, node);
                            }
                        }
                    }
                }

                if (dst == node) {
                    break;
                }
            }
        }

        Node curr = dst;
        int tempWavLen = 0;
        boolean flag1 = true;

        for (Node ignored : parentRelateMap.keySet()) {
            for (Node n : parentRelateMap.keySet()) {
                if (n == curr) {
                    Node val = parentRelateMap.get(n);
                    tempPath.add(val);
                    if (n != dst) {
                        for (Link link : n.getLinks()) {
                            if (link.getNode1() == val || link.getNode2() == val) {
                                link.getFib(0).consumeWavLen();
                                System.out.println("In '" + link.getName() + "' link " + "consumed wavelengths = " + link.getFib(0).getConsumedWavLens());
                            }
                        }
                    }

                    curr = val;
                }
            }
        }

        if (tempPath.size() == 0) {
            System.out.println("Cannot create the lightpath");
            System.out.println("Lightpaths requested = " + noOfLightPathsReq + " Lightpaths created = " + noOfLightPathsCreated);
        } else {
            Collections.reverse(tempPath);
            noOfLightPathsCreated++;
            System.out.print("Lightpath -> " + src.getName() + " ");
            for (Node n : tempPath) {
                System.out.print(n.getName() + " ");
            }

            System.out.print(dst.getName() + " ");
            System.out.println("\nLightpaths requested = " + noOfLightPathsReq + " Lightpaths created = " + noOfLightPathsCreated);
        }
    }
}
