package ru.geekbrains.lesson2;

import java.util.*;

public class Program {


    private static final int WIN_COUNT = 4;
    private static final String DOT_HUMAN = "X";
    private static final String DOT_AI = "O";
    private static final String DOT_EMPTY = "•";

    public static HashMap<Integer, List<Integer>> HumanTurnsX; //сохраняет все ходы человека по горизонтали
    public static HashMap<Integer, List<Integer>> HumanTurnsY; //сохраняет все ходы человека по вертикали
    public static HashMap<Integer, List<Integer>> AITurnsX; //сохраняет все ходы ai по горизонтали
    public static HashMap<Integer, List<Integer>> AITurnsY; //сохраняет все ходы ai по вертикали

    private static final Scanner SCANNER = new Scanner(System.in);

    private static String[][] field; // Двумерный массив хранит текущее состояние игрового поля

    private static final Random random = new Random();

    private static int fieldSizeX; // Размерность игрового поля
    private static int fieldSizeY; // Размерность игрового поля


    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();

            HumanTurnsX = new HashMap<>();
            HumanTurnsY = new HashMap<>();
            AITurnsX = new HashMap<>();
            AITurnsY = new HashMap<>();

            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы победили!", HumanTurnsX, HumanTurnsY)) {
                    break;
                }
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Компьютер победил!", AITurnsX, AITurnsY)) {
                    break;
                }
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
            if (!SCANNER.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    /**
     * Инициализация игрового поля
     */
    private static void initialize() {
        // Установим размерность игрового поля
        fieldSizeX = 9;
        fieldSizeY = 9;


        field = new String[fieldSizeX][fieldSizeY];
        // Пройдем по всем элементам массива
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                // Проинициализируем все элементы массива DOT_EMPTY (признак пустого поля)
                field[x][y] = DOT_EMPTY;
            }
        }

    }

    /**
     * Отрисовка игрового поля
     * //TODO: Поправить отрисовку игрового поля
     */
    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print(i + 1 + "|");

            for (int j = 0; j < fieldSizeY; j++)
                System.out.print(field[i][j] + "|");

            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();

    }

    /**
     * Обработка хода игрока (человек)
     */
    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты хода X и Y (от 1 до 3) через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;

        if (HumanTurnsX.get(x) == null) {
            HumanTurnsX.put(x, new ArrayList<>(List.of(y)));
        } else {
            List<Integer> xRow = HumanTurnsX.get(x);
            xRow.add(y);
            xRow.sort(Comparator.naturalOrder());
        }

        if (HumanTurnsY.get(y) == null) {
            HumanTurnsY.put(y, new ArrayList<>(List.of(x)));
        } else {
            List<Integer> yColumn = HumanTurnsY.get(y);
            yColumn.add(x);
            yColumn.sort(Comparator.naturalOrder());
        }
    }

    /**
     * Проверка, ячейка является пустой
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность массива, игрового поля)
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Ход компьютера
     */
    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;

        if (AITurnsX.get(x) == null) {
            AITurnsX.put(x, new ArrayList<>(List.of(y)));

        } else {
            List<Integer> xRow = AITurnsX.get(x);
            xRow.add(y);
            xRow.sort(Comparator.naturalOrder());
        }

        if (AITurnsY.get(y) == null) {
            AITurnsY.put(y, new ArrayList<>(List.of(x)));
        } else {
            List<Integer> yColumn = AITurnsY.get(y);
            yColumn.add(x);
            yColumn.sort(Comparator.naturalOrder());
        }
    }

    /**
     * Проверка победы
     * TODO: Переработать метод в домашнем задании
     *
     * @param c
     * @return
     */
    static boolean checkWin(String c, HashMap<Integer, List<Integer>> turnsX, HashMap<Integer, List<Integer>> turnsY) { //HumanTurnsX , HumanTurnsY

        int counter, previouseTurn, anotherTurn;

        // Проверка по диагоналям
        if (field[0][0].equals(c) && field[1][1].equals(c) && field[2][2].equals(c)) return true;
        if (field[0][2].equals(c) && field[1][1].equals(c) && field[2][0].equals(c)) return true;


        // Проверка по x горизонталям
        counter = 1;

        for (int el : turnsX.keySet()) {
            List<Integer> arrayX = turnsX.get(el);
            for (int j = 1; j < arrayX.size(); j++) {
                previouseTurn = arrayX.get(j - 1);
                anotherTurn = arrayX.get(j);
                if (field[el][previouseTurn].equals(field[el][anotherTurn])) {
                    counter += 1;
                } else {
                    counter = 1;
                }
                if (counter == WIN_COUNT) {
                    return true;
                }
            }
        }

        // Проверка по y вертикалям

        counter = 1;

        for (int el : turnsY.keySet()) {
            List<Integer> arrayY = turnsY.get(el);

            for (int j = 1; j < arrayY.size(); j++) {
                previouseTurn = arrayY.get(j - 1);
                anotherTurn = arrayY.get(j);
                if (field[previouseTurn][el].equals(field[anotherTurn][el])) {
                    counter += 1;
                } else {
                    counter = 0;
                }
                if (counter == WIN_COUNT) {
                    return true;
                }
            }
        }

        //если не набралось нужного количества для win

        return false;
    }

    /**
     * Проверка на ничью
     *
     * @return
     */
    static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++)
                if (isCellEmpty(x, y)) return false;
        }
        return true;
    }

    /**
     * Метод проверки состояния игры
     *
     * @param c
     * @param str
     * @return
     */
    static boolean gameCheck(String c, String str, HashMap<Integer, List<Integer>> turnsX, HashMap<Integer, List<Integer>> turnsY) {
        if (checkWin(c, turnsX, turnsY)) {
            System.out.println(str);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }

        return false; // Игра продолжается
    }

}
