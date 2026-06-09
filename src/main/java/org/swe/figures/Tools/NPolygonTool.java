package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.swe.figures.Model.IShapeFactory;
import org.swe.figures.Model.Shape;

import java.util.function.Consumer;

/**
 * Инструмент для создания правильных N-угольников.
 * Собирает две точки (центр и крайнюю) и использует фабрику для создания фигуры.
 */
public class NPolygonTool extends ShapeTool {

    private final int sides;

    /**
     * Создает инструмент для рисования многоугольника.
     *
     * @param gc           Графический контекст.
     * @param color        Цвет.
     * @param lineWidth    Толщина линии.
     * @param fill         Флаг заливки.
     * @param sides        Количество сторон.
     * @param onShapeReady Callback при завершении рисования.
     * @param shapeFactory Фабрика фигур.
     */
    public NPolygonTool(GraphicsContext gc, Color color, double lineWidth, boolean fill,
                        int sides, Consumer<Shape> onShapeReady, IShapeFactory shapeFactory) {
        super(gc, color, lineWidth, fill, onShapeReady, shapeFactory, "Многоугольник");
        this.sides = sides;
    }

    @Override
    protected int requiredPoints() {
        return 2; // Центр и радиус
    }

    @Override
    public void onMouseClicked(javafx.scene.input.MouseEvent e) {
        points.add(e.getX());
        points.add(e.getY());
        drawHelperPoints();

        if (points.size() == requiredPoints() * 2) {
            // Передаем координаты и количество сторон
            double[] coords = new double[]{
                points.get(0), points.get(1), // center
                points.get(2), points.get(3), // edge
                sides
            };
            Shape shape = shapeFactory.createShape(shapeType, color, lineWidth, fill, coords);
            onShapeReady.accept(shape);
            points.clear();
        }
    }
}
