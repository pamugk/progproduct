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
     * �������� ����������� �����
     * @param number ���������
     * @return ��������� ��������
     */
    public Complex add(Complex number) {
        return new Complex(
                this.getReal() + number.getReal(),
                this.getImaginary() + number.getImaginary()
        );
    }

    /**
     * ��������� ����������� �����
     * @param number ����������
     * @return ��������� ���������
     */
    public Complex subtract(Complex number) {
        return new Complex(
                this.getReal() - number.getReal(),
                this.getImaginary() - number.getImaginary()
        );
    }

    /**
     * ��������� ����������� �����
     * @param number ���������
     * @return ��������� ���������
     */
    public Complex multiply(Complex number) {
        return new Complex(
                this.getReal() * number.getReal() - this.getImaginary() * number.getImaginary(),
                this.getImaginary() * number.getReal() + this.getReal() * number.getImaginary()
        );
    }

    /**
     * ������� ����������� �����
     * @param number ��������
     * @return ��������� �������
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
     * ���������� ������ ����������� �����
     * @param number �����
     * @return ������
     */
    public static double abs(Complex number) {
        return Math.sqrt(number.getReal() * number.getReal() + number.getImaginary() * number.getImaginary());
    }

    @Override
    public String toString(){
        return String.format("%s%si", real, imaginary == 0 ? "" : imaginary > 0 ? "+" + imaginary:imaginary);
    }
}
