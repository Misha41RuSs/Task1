package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.swe.figures.Model.IShapeFactory;
import org.swe.figures.Model.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Абстрактный базовый класс для инструментов, создающих фигуры по точкам.
 * Накапливает координаты кликов мыши и при достижении нужного количества
 * делегирует создание фигуры фабрике.
 */
public abstract class ShapeTool implements DrawTool {
    protected final GraphicsContext gc;
    protected final List<Double> points = new ArrayList<>();
    protected final Consumer<Shape> onShapeReady;
    protected final IShapeFactory shapeFactory;
    protected final String shapeType;

    protected Color color;
    protected double lineWidth;
    protected boolean fill;

    /**
     * Инициализирует базовые параметры инструмента фигуры.
     *
     * @param gc           Графический контекст.
     * @param color        Цвет фигуры.
     * @param lineWidth    Толщина линии.
     * @param fill         Флаг заливки.
     * @param onShapeReady Callback при готовности фигуры.
     * @param shapeFactory Фабрика для создания фигуры.
     * @param shapeType    Строковый тип фигуры.
     */
    public ShapeTool(GraphicsContext gc, Color color, double lineWidth, boolean fill,
                     Consumer<Shape> onShapeReady, IShapeFactory shapeFactory, String shapeType) {
        this.gc = gc;
        this.color = color;
        this.lineWidth = lineWidth;
        this.fill = fill;
        this.onShapeReady = onShapeReady;
        this.shapeFactory = shapeFactory;
        this.shapeType = shapeType;
    }

    /**
     * @return Количество точек (кликов), необходимых для построения данной фигуры.
     */
    protected abstract int requiredPoints();


    @Override
    public void onMouseClicked(MouseEvent e) {
        points.add(e.getX());
        points.add(e.getY());
        drawHelperPoints();

        if (points.size() == requiredPoints() * 2) {
            double[] coords = points.stream().mapToDouble(Double::doubleValue).toArray();
            Shape shape = shapeFactory.createShape(shapeType, color, lineWidth, fill, coords);
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
