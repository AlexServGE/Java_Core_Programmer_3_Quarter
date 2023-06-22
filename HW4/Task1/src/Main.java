import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[][] stringTaskArray = new String[][]{
                {"1", "t", "1", "2"},
                {"t", "t", "1", "2"},
                {"t", "t", "1", "2"},
                {"t", "t", "1", "2"},
        };
        printSquareArray(stringTaskArray);
        Integer[][] integerTaskArray = null;
        try {
            integerTaskArray = universalTaskMethod(stringTaskArray);
            System.out.println("Результат:");
            printSquareArray(integerTaskArray);
        } catch (MyArrayDataException ex) {
            System.out.println(ex.getMESSAGE());
        } catch (MyArraySizeException ex) {
            System.out.println(ex.getMESSAGE());
        }
        System.out.println("------------------next test---------------------");

        String[][] stringTaskArray2 = new String[][]{
                {"1", "1", "1", "2"},
                {"1", "1", "1", "2"},
                {"1", "1", "1", "2"},
                {"1", "1", "1", "2"},
        };
        printSquareArray(stringTaskArray2);

        try {
            integerTaskArray = universalTaskMethod(stringTaskArray2);
            System.out.println("Результат:");
            printSquareArray(integerTaskArray);
        } catch (MyArrayDataException ex) {
            System.out.println(ex.getMESSAGE());
        } catch (MyArraySizeException ex) {
            System.out.println(ex.getMESSAGE());
        }

        System.out.println("------------------next test---------------------");

        String[][] stringTaskArray3 = new String[][]{
                {"1", "1"},
                {"1", "1", "1", "2"},
                {"1", "1", "1", "2"},
                {"1", "1", "1", "2"},
        };
        printSquareArray(stringTaskArray3);

        try {
            integerTaskArray = universalTaskMethod(stringTaskArray3);
            System.out.println("Результат:");
            printSquareArray(integerTaskArray);
        } catch (MyArrayDataException ex) {
            System.out.println(ex.getMESSAGE());
        } catch (MyArraySizeException ex) {
            System.out.println(ex.getMESSAGE());
        }
    }


    public static Integer[][] universalTaskMethod(String[][] someArray) throws MyArraySizeException, MyArrayDataException {
        checkIf4x4(someArray);
        return castArrayElsToInts(someArray);
    }


    public static void checkIf4x4(String[][] someArray) throws MyArraySizeException {
        if (someArray.length != 4) {
            throw new MyArraySizeException();
        }
        for (String[] strings : someArray) {
            if (strings.length != 4) {
                throw new MyArraySizeException();
            }
        }
    }

    public static Integer[][] castArrayElsToInts(String[][] someStringArray) throws MyArrayDataException {
        Integer[][] someIntArray = new Integer[4][4];
        for (int i = 0; i < someStringArray.length; i++) {
            for (int j = 0; j < someStringArray[i].length; j++) {
                try {
                    someIntArray[i][j] = Integer.valueOf(someStringArray[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException(someStringArray[i][j], String.format("[%d][%d]", i, j));
                }
            }
        }
        return someIntArray;
    }

    public static <T> void printSquareArray(T[][] someArray) {
        if (someArray == null) {
            System.out.println("Не проинициализирован массив");
        } else {
            for (T[] array : someArray) {
                System.out.println(Arrays.toString(array));
            }
        }
    }
}