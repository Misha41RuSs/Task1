package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.swe.figures.Model.Circle;
import org.swe.figures.Model.Shape;

import java.util.function.Consumer;

public class CircleTool extends ShapeTool {

    public CircleTool(GraphicsContext gc, Color color, double lineWidth, boolean fill,
                      Consumer<Shape> onShapeReady) {
        super(gc, color, lineWidth, fill, onShapeReady);
    }

    @Override
    protected int requiredPoints() {
        return 2;
    }

    @Override
    protected Shape buildShape(double[] coords) {
        double cx = coords[0];
        double cy = coords[1];
        double ex = coords[2];
        double ey = coords[3];
        double radius = Math.hypot(ex - cx, ey - cy);
        return new Circle(color, lineWidth, cx, cy, radius, fill);
    }
}
