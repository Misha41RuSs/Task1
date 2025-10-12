package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    private final double centerX, centerY, radius;
    private final boolean fill;

    public Circle(Color color, double lineWidth, double centerX, double centerY, double radius, boolean fill) {
        super(color, lineWidth);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.fill = fill;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw(GraphicsContext gc) {
        double topLeftX = centerX - radius;
        double topLeftY = centerY - radius;
        double diameter = radius * 2;

        gc.setStroke(color);
        gc.setFill(color);
        gc.setLineWidth(lineWidth);

        if (fill)
            gc.fillOval(topLeftX, topLeftY, diameter, diameter);
        else
            gc.strokeOval(topLeftX, topLeftY, diameter, diameter);
    }

    @Override
    public String toString() {
        return "Circle[color=" + color + ", area=" + area() + "]";
    }
}
