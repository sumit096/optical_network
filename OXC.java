import java.util.ArrayList;

public class OXC extends Node {
    public static ArrayList<OXC> oxcs = new ArrayList<>();

    public OXC(String name) {
        super(name);
        oxcs.add(this);
    }
}
