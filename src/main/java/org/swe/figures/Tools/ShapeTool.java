package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.swe.figures.Model.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ShapeTool implements DrawTool {
    protected final GraphicsContext gc;
    protected final List<Double> points = new ArrayList<>();
    protected final Consumer<Shape> onShapeReady;

    protected Color color;
    protected double lineWidth;
    protected boolean fill;

    public ShapeTool(GraphicsContext gc, Color color, double lineWidth, boolean fill,
                     Consumer<Shape> onShapeReady) {
        this.gc = gc;
        this.color = color;
        this.lineWidth = lineWidth;
        this.fill = fill;
        this.onShapeReady = onShapeReady;
    }

    protected abstract int requiredPoints();
    protected abstract Shape buildShape(double[] coords);

    @Override
    public void onMouseClicked(MouseEvent e) {
        points.add(e.getX());
        points.add(e.getY());
        drawHelperPoints();

        if (points.size() == requiredPoints() * 2) {
            Shape shape = buildShape(points.stream().mapToDouble(Double::doubleValue).toArray());
            onShapeReady.accept(shape);
            points.clear();
        }
    }

    @Override public void onMousePressed(MouseEvent e) {}
    @Override public void onMouseDragged(MouseEvent e) {}
    @Override public void onMouseReleased(MouseEvent e) {}

    @Override
    public void drawHelperPoints() {
        gc.setFill(color);
        for (int i = 0; i < points.size(); i += 2) {
            gc.fillOval(points.get(i) - 3, points.get(i + 1) - 3, 6, 6);
        }
    }
}
