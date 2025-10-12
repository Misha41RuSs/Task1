package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Color color;
    protected double lineWidth;

    public Shape(Color color, double lineWidth) {
        this.color = color;
        this.lineWidth = lineWidth;
    }

    abstract double area();
    public abstract void draw(GraphicsContext gc);

    public void setColor(Color color) { this.color = color; }
    public void setLineWidth(double lineWidth) { this.lineWidth = lineWidth; }
}
