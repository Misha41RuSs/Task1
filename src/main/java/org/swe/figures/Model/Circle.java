package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Класс, представляющий фигуру "Круг".
 * Наследуется от базового класса {@link Shape}.
 */
public class Circle extends Shape {
    private double radius;
    private boolean fill;

    /**
     * Создает новый круг с заданными параметрами.
     *
     * @param color     Цвет круга.
     * @param lineWidth Толщина линии.
     * @param centerX   X-координата центра круга.
     * @param centerY   Y-координата центра круга.
     * @param radius    Радиус круга.
     * @param fill      Флаг заливки (true - залитый, false - только контур).
     */
    public Circle(Color color, double lineWidth, double centerX, double centerY, double radius, boolean fill) {
        super(color, lineWidth, centerX, centerY, "Окружность/круг");
        this.radius = radius;
        this.fill = fill;
    }

    /**
     * Конструктор для инициализации по имени.
     *
     * @param name Имя фигуры.
     */
    public Circle(String name) {
        super(name);
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw(GraphicsContext gc) {
        double topLeftX = getStartX() - radius;
        double topLeftY = getStartY() - radius;
        double diameter = radius * 2;

        gc.setStroke(getColor());
        gc.setFill(getColor());
        gc.setLineWidth(getLineWidth());

        if (fill)
            gc.fillOval(topLeftX, topLeftY, diameter, diameter);
        else
            gc.strokeOval(topLeftX, topLeftY, diameter, diameter);
    }

    // Геттеры и сеттеры
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }
    
    public boolean isFill() { return fill; }
    public void setFill(boolean fill) { this.fill = fill; }
}
