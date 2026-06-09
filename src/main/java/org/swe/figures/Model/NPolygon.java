package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Класс, представляющий правильный многоугольник с N сторонами.
 * Реализует требование задания: 0 - круг, 1 - отрезок, 2 - угол, 3 - треугольник и т.д.
 * Наследуется от базового класса {@link Shape}.
 */
public class NPolygon extends Shape {
    private int sides;
    private double radius;
    private boolean fill;
    // Используем startX и startY как центр фигуры.
    private double endX;
    private double endY;

    /**
     * Создает новый N-угольник.
     *
     * @param color     Цвет фигуры.
     * @param lineWidth Толщина линии.
     * @param centerX   X-координата центра.
     * @param centerY   Y-координата центра.
     * @param endX      X-координата конца радиуса.
     * @param endY      Y-координата конца радиуса.
     * @param sides     Количество сторон.
     * @param fill      Флаг заливки (для фигур, где она применима).
     */
    public NPolygon(Color color, double lineWidth, double centerX, double centerY, double endX, double endY, int sides, boolean fill) {
        super(color, lineWidth, centerX, centerY, "Многоугольник");
        this.endX = endX;
        this.endY = endY;
        this.sides = sides;
        this.fill = fill;
        this.radius = Math.hypot(endX - centerX, endY - centerY);
    }

    /**
     * Конструктор для инициализации по имени.
     *
     * @param name Имя фигуры.
     */
    public NPolygon(String name) {
        super(name);
    }

    @Override
    public double area() {
        if (sides <= 2) return 0;
        // Площадь правильного многоугольника: 1/2 * N * R^2 * sin(2*PI/N)
        return 0.5 * sides * radius * radius * Math.sin(2 * Math.PI / sides);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getColor());
        gc.setFill(getColor());
        gc.setLineWidth(getLineWidth());

        double cx = getStartX();
        double cy = getStartY();

        if (sides == 0) {
            // Круг
            double topLeftX = cx - radius;
            double topLeftY = cy - radius;
            double diameter = radius * 2;
            if (fill) gc.fillOval(topLeftX, topLeftY, diameter, diameter);
            else gc.strokeOval(topLeftX, topLeftY, diameter, diameter);
        } else if (sides == 1) {
            // Отрезок
            gc.strokeLine(cx, cy, endX, endY);
        } else if (sides == 2) {
            // Угол (две линии из центра)
            double angle = Math.atan2(endY - cy, endX - cx);
            double angle1 = angle - Math.PI / 4;
            double angle2 = angle + Math.PI / 4;
            double x1 = cx + radius * Math.cos(angle1);
            double y1 = cy + radius * Math.sin(angle1);
            double x2 = cx + radius * Math.cos(angle2);
            double y2 = cy + radius * Math.sin(angle2);
            gc.strokeLine(cx, cy, x1, y1);
            gc.strokeLine(cx, cy, x2, y2);
        } else {
            // N-угольник (N >= 3)
            double[] xPoints = new double[sides];
            double[] yPoints = new double[sides];
            // Начальный угол для того, чтобы вершина смотрела вверх
            double startAngle = -Math.PI / 2;
            for (int i = 0; i < sides; i++) {
                double currentAngle = startAngle + i * 2 * Math.PI / sides;
                xPoints[i] = cx + radius * Math.cos(currentAngle);
                yPoints[i] = cy + radius * Math.sin(currentAngle);
            }
            if (fill) gc.fillPolygon(xPoints, yPoints, sides);
            else gc.strokePolygon(xPoints, yPoints, sides);
        }
    }

    // Геттеры и сеттеры
    public int getSides() { return sides; }
    public void setSides(int sides) { this.sides = sides; }
    
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }
    
    public double getEndX() { return endX; }
    public void setEndX(double endX) { this.endX = endX; }
    
    public double getEndY() { return endY; }
    public void setEndY(double endY) { this.endY = endY; }
    
    public boolean isFill() { return fill; }
    public void setFill(boolean fill) { this.fill = fill; }
}
