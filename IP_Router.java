import java.util.ArrayList;

public class IP_Router extends Node {
    public static ArrayList<IP_Router> routers = new ArrayList<>();

    public IP_Router(String name) {
        super(name);
        routers.add(this);
    }
}
