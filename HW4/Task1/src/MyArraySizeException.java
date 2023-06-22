public class MyArraySizeException extends RuntimeException {
    private final String MESSAGE = "Ошибка: Данный массив не является массивом 4x4";

    public String getMESSAGE() {
        return MESSAGE;
    }
}
