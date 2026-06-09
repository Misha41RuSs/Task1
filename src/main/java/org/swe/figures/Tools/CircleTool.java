package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.swe.figures.Model.IShapeFactory;
import org.swe.figures.Model.Shape;

import java.util.function.Consumer;

/**
 * Инструмент для создания окружности/круга.
 * Ожидает 2 клика мыши: центр и точка на окружности (определяющая радиус).
 */
public class CircleTool extends ShapeTool {

    public CircleTool(GraphicsContext gc, Color color, double lineWidth, boolean fill,
                      Consumer<Shape> onShapeReady, IShapeFactory shapeFactory) {
        super(gc, color, lineWidth, fill, onShapeReady, shapeFactory, "Окружность/круг");
    }

    @Override
    protected int requiredPoints() {
        return 2;
    }
}
