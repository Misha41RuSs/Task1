package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    private final double x, y, width, height;
    private final boolean fill;

    public Rectangle(Color color, double lineWidth, double x, double y, double width, double height, boolean fill) {
        super(color, lineWidth);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fill = fill;
    }

    @Override
    double area() {
        return width * height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setFill(color);
        gc.setLineWidth(lineWidth);
        if (fill)
            gc.fillRect(x, y, width, height);
        else
            gc.strokeRect(x, y, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle[color=" + color + ", area=" + area() + "]";
    }
}
