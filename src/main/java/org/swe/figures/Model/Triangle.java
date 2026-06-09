package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Класс, представляющий фигуру "Треугольник".
 * Наследуется от базового класса {@link Shape}.
 */
public class Triangle extends Shape {
    private double[] xPoints;
    private double[] yPoints;
    private boolean fill;

    /**
     * Создает новый треугольник.
     *
     * @param color     Цвет треугольника.
     * @param lineWidth Толщина линии.
     * @param xPoints   Массив X-координат вершин.
     * @param yPoints   Массив Y-координат вершин.
     * @param fill      Флаг заливки (true - залитый, false - только контур).
     */
    public Triangle(Color color, double lineWidth, double[] xPoints, double[] yPoints, boolean fill) {
        super(color, lineWidth, xPoints[0], yPoints[0], "Треугольник");
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.fill = fill;
    }

    /**
     * Конструктор для инициализации по имени.
     *
     * @param name Имя фигуры.
     */
    public Triangle(String name) {
        super(name);
    }

    @Override
    public double area() {
        return Math.abs((xPoints[0]*(yPoints[1]-yPoints[2]) +
                xPoints[1]*(yPoints[2]-yPoints[0]) +
                xPoints[2]*(yPoints[0]-yPoints[1])) / 2.0);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getColor());
        gc.setFill(getColor());
        gc.setLineWidth(getLineWidth());
        if (fill)
            gc.fillPolygon(xPoints, yPoints, 3);
        else
            gc.strokePolygon(xPoints, yPoints, 3);
    }

    // Геттеры и сеттеры
    public double[] getXPoints() { return xPoints; }
    public void setXPoints(double[] xPoints) { this.xPoints = xPoints; }
    
    public double[] getYPoints() { return yPoints; }
    public void setYPoints(double[] yPoints) { this.yPoints = yPoints; }
    
    public boolean isFill() { return fill; }
    public void setFill(boolean fill) { this.fill = fill; }
}
