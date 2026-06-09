package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.swe.figures.Model.IShapeFactory;
import org.swe.figures.Model.Shape;
import java.util.function.Consumer;

/**
 * Класс-фабрика для создания инструментов рисования.
 * Инкапсулирует логику выбора конкретного инструмента.
 */
public class ToolFactory implements IToolFactory {
    private final GraphicsContext gc;
    private final IShapeFactory shapeFactory;

    /**
     * Создает фабрику инструментов.
     *
     * @param gc           Графический контекст для отрисовки "резиновой нити" и фигур.
     * @param shapeFactory Фабрика фигур, передаваемая инструментам.
     */
    public ToolFactory(GraphicsContext gc, IShapeFactory shapeFactory) {
        this.gc = gc;
        this.shapeFactory = shapeFactory;
    }

    @Override
    public DrawTool createTool(String mode, Color color, double lineWidth, boolean fill,
                               int sides, Consumer<Shape> onShapeReady) {
        return switch (mode) {
            case "Свободное рисование" -> new FreeDrawTool(gc, color, lineWidth, fill);
            case "Окружность/круг" -> new CircleTool(gc, color, lineWidth, fill, onShapeReady, shapeFactory);
            case "Прямоугольник" -> new RectangleTool(gc, color, lineWidth, fill, onShapeReady, shapeFactory);
            case "Треугольник" -> new TriangleTool(gc, color, lineWidth, fill, onShapeReady, shapeFactory);
            case "Многоугольник" -> new NPolygonTool(gc, color, lineWidth, fill, sides, onShapeReady, shapeFactory);
            default -> null;
        };
    }
}
