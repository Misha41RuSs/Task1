package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.swe.figures.Model.Shape;
import java.util.function.Consumer;

public class ToolFactory {
    private final GraphicsContext gc;

    public ToolFactory(GraphicsContext gc) {
        this.gc = gc;
    }

    public DrawTool createTool(String mode, Color color, double lineWidth, boolean fill,
                               Consumer<Shape> onShapeReady) {
        return switch (mode) {
            case "Свободное рисование" -> new FreeDrawTool(gc, color, lineWidth, fill);
            case "Окружность/круг" -> new CircleTool(gc, color, lineWidth, fill, onShapeReady);
            case "Прямоугольник" -> new RectangleTool(gc, color, lineWidth, fill, onShapeReady);
            case "Треугольник" -> new TriangleTool(gc, color, lineWidth, fill, onShapeReady);
            default -> null;
        };
    }
}
