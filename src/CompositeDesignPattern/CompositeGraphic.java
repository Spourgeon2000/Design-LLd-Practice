package CompositeDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class CompositeGraphic implements Graphic {
    private List<Graphic> graphics = new ArrayList<>();

    public void add(Graphic graphic) {
        graphics.add(graphic);
    }

    public void remove(Graphic graphic) {
        graphics.remove(graphic);
    }

    @Override
    public void draw() {
        System.out.println("Drawing Composite Graphic:");
        for (Graphic graphic : graphics) {
            graphic.draw();  // Delegating the drawing to child graphics
        }
    }
}