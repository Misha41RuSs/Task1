package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {

    private final double x1, y1, x2, y2, x3, y3;

    public Triangle(Color color, double x1, double y1, double x2, double y2, double x3, double y3) {
        super(color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    double area() {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)) / 2.0);
    }

    @Override
    public String toString() {
        return "Triangle color is " + super.color + " and area is : " + area();
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(super.color);
        gr.strokePolygon(
                new double[]{x1, x2, x3},
                new double[]{y1, y2, y3},
                3
        );
    }

    @Override
    public void draw(GraphicsContext graphicsContext, double min, double min1) {

    }
}
