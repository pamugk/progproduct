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

    @Override
    public String toString(){
        return String.format("%s%s", real, imaginary == 0 ? "" : imaginary > 0 ? "+" + imaginary:imaginary);
    }
}
