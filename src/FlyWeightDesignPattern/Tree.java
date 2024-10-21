package FlyWeightDesignPattern;

public class Tree {
    private int x;
    private int y;
    private TreeType type;  // This is the shared intrinsic state

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void display() {
        type.display(x, y);  // Pass extrinsic state (x, y) to the flyweight
    }
}