# Классы фигур

## Демонстрация работы

В Main.kt объявляется ассоциативный массив фигур:
```kotlin
val figures = mapOf<String, Figure>(
    "circle" to Circle(0, 1, 4),
    "rect" to Rect(3, 2, 5, 3),
    "square" to Square(5, 3, 2),
)
```

Затем он перебирается, и у каждого элемента-фигуры 
вызываются методы класса-родителя Figure и интерфейсов
Movable и Transforming:
```kotlin
for (key in figures.keys) {
    val figure = figures[key]
    ...
}
```

Вывод в консоль Main.kt:
```
start circle: (0, 1), area = 50.265484, radius = 4
resized: (0, 1), area = 201.06194, radius = 8
moved: (-2, -1), area = 201.06194, radius = 8
rotated: (-1, 2), area = 201.06194, radius = 8

start rect: (3, 2), area = 15.0, w = 5, h = 3
resized: (3, 2), area = 60.0, w = 10, h = 6
moved: (1, 0), area = 60.0, w = 10, h = 6
rotated: (0, -1), area = 60.0, w = 6, h = 10

start square: (5, 3), area = 4.0, side = 2
resized: (5, 3), area = 16.0, side = 4
moved: (3, 1), area = 16.0, side = 4
rotated: (1, -3), area = 16.0, side = 4
```