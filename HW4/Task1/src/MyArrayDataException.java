public class MyArrayDataException extends NumberFormatException {
    private final String element;
    private final String index;

    public MyArrayDataException(String element, String index) {
        this.element = element;
        this.index = index;
    }

    public String getMESSAGE() {
        return String.format("Ошибка: Невозможно осуществить преобразование %s под индексом %s",element,index);
    }
}
