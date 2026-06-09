package org.swe.figures.Tools;

import javafx.scene.paint.Color;
import org.swe.figures.Model.Shape;

import java.util.function.Consumer;

/**
 * Интерфейс фабрики инструментов.
 * Реализует паттерн Abstract Factory для создания инструментов рисования.
 */
public interface IToolFactory {
    /**
     * Создает инструмент рисования на основе выбранного режима.
     *
     * @param mode         Режим рисования (имя фигуры или "Свободное рисование").
     * @param color        Цвет фигуры.
     * @param lineWidth    Толщина линии.
     * @param fill         Флаг заливки.
     * @param sides        Количество сторон (используется для Многоугольника).
     * @param onShapeReady Callback для добавления готовой фигуры на холст.
     * @return Инструмент для рисования (DrawTool).
     */
    DrawTool createTool(String mode, Color color, double lineWidth, boolean fill,
                        int sides, Consumer<Shape> onShapeReady);
}
