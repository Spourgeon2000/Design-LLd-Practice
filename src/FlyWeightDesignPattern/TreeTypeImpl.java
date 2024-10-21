package FlyWeightDesignPattern;

public class TreeTypeImpl implements TreeType {
    private String name;
    private String color;
    private String texture;

    public TreeTypeImpl(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Displaying a " + name + " tree at (" + x + ", " + y + ") with color " + color + " and texture " + texture);
    }
}