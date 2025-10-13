package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.swe.figures.Model.Rectangle;
import org.swe.figures.Model.Shape;

import java.util.function.Consumer;

public class RectangleTool extends ShapeTool {
    public RectangleTool(GraphicsContext gc, Color color, double lineWidth, boolean fill,
                         Consumer<Shape> onShapeReady) {
        super(gc, color, lineWidth, fill, onShapeReady);
    }

    @Override
    protected int requiredPoints() {
        return 2;
    }

    @Override
    protected Shape buildShape(double[] coords) {
        double x1 = coords[0];
        double y1 = coords[1];
        double x2 = coords[2];
        double y2 = coords[3];
        double minX = Math.min(x1, x2);
        double minY = Math.min(y1, y2);
        double width = Math.abs(x2 - x1);
        double height = Math.abs(y2 - y1);
        return new Rectangle(color, lineWidth, minX, minY, width, height, fill);
    }
}
