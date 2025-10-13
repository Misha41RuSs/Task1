package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.swe.figures.Model.Shape;
import org.swe.figures.Model.Triangle;

import java.util.function.Consumer;

public class TriangleTool extends ShapeTool {
    public TriangleTool(GraphicsContext gc, Color color, double lineWidth, boolean fill,
                        Consumer<Shape> onShapeReady) {
        super(gc, color, lineWidth, fill, onShapeReady);
    }

    @Override
    protected int requiredPoints() {
        return 3;
    }

    @Override
    protected Shape buildShape(double[] coords) {
        double[] xPoints = {coords[0], coords[2], coords[4]};
        double[] yPoints = {coords[1], coords[3], coords[5]};
        return new Triangle(color, lineWidth, xPoints, yPoints, fill);
    }
}
