package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Color color;

    abstract double area();
    public abstract void draw(GraphicsContext gr);
    public abstract void draw(GraphicsContext graphicsContext, double min, double min1);

    public Shape(Color color) {
        System.out.println("Shape constructor called");
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
