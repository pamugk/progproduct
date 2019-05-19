package common;

public class Complex {
    private final double real;
    private final double imaginary;

    public Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal(){
        return real;
    }

    public double getImaginary(){
        return imaginary;
    }

    /**
     * Сложение комплексных чисел
     * @param number Слагаемое
     * @return Результат сложения
     */
    public Complex add(Complex number) {
        return new Complex(
                this.getReal() + number.getReal(),
                this.getImaginary() + number.getImaginary()
        );
    }

    /**
     * Вычитание комплексных чисел
     * @param number Вычитаемое
     * @return Результат вычитания
     */
    public Complex subtract(Complex number) {
        return new Complex(
                this.getReal() - number.getReal(),
                this.getImaginary() - number.getImaginary()
        );
    }

    /**
     * Умножение комплексных чисел
     * @param number Множитель
     * @return Результат умножения
     */
    public Complex multiply(Complex number) {
        return new Complex(
                this.getReal() * number.getReal() - this.getImaginary() * number.getImaginary(),
                this.getImaginary() * number.getReal() + this.getReal() * number.getImaginary()
        );
    }

    /**
     * Деление комплексных чисел
     * @param number Делитель
     * @return Результат деления
     */
    public Complex divide(Complex number) {
        double a = this.getReal();
        double b = this.getImaginary();
        double c = number.getReal();
        double d = number.getImaginary();
        return new Complex(
                (a * c + b * d) / (c * c + d * d),
                (b * c - a * d) / (c * c + d * d)
        );
    }

    /**
     * Вычисление модуля комплексных чисел
     * @param number Число
     * @return Модуль
     */
    public static double abs(Complex number) {
        return Math.sqrt(number.getReal() * number.getReal() + number.getImaginary() * number.getImaginary());
    }

    @Override
    public String toString(){
        return String.format("%s%si", real, imaginary == 0 ? "" : imaginary > 0 ? "+" + imaginary:imaginary);
    }
}
