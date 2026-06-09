package org.swe.figures.Model;

import javafx.scene.paint.Color;

/**
 * Интерфейс абстрактной фабрики для создания графических фигур.
 * Реализует паттерн Factory Method.
 */
public interface IShapeFactory {
    /**
     * Возвращает массив прототипов всех доступных фигур (используется для инициализации UI).
     *
     * @return Массив объектов Shape.
     */
    Shape[] createAllShapes();

    /**
     * Создает конкретную фигуру по ее строковому типу и переданным параметрам.
     *
     * @param shapeType Тип фигуры (например, "Круг").
     * @param color     Цвет фигуры.
     * @param lineWidth Толщина линии.
     * @param fill      Флаг заливки.
     * @param array     Массив параметров (координаты, размеры и т.д.).
     * @return Созданный объект Shape.
     */
    Shape createShape(String shapeType, Color color, double lineWidth, boolean fill, double[] array);
}
