package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    double length;
    double width;

    public Rectangle(Color color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }

    @Override
    public String toString() {
        return "Rectangle color is " + super.color +  " and area is : " + area();
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(super.color);
        gr.strokeRect(50, 50, length, width);
    }

    public void draw(GraphicsContext gr, double x, double y) {
        gr.setStroke(super.color);
        gr.strokeRect(x, y, length, width);
    }
}
