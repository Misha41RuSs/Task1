package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    private double radius;

    public Circle(Color color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw(GraphicsContext gr) {

    }

    @Override
    public void draw(GraphicsContext graphicsContext, double x, double y) {
        graphicsContext.setStroke(super.color);
        graphicsContext.strokeOval(x, y, radius, radius);
    }
}
