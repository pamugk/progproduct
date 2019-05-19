package common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SqrtSolver implements SqrtSolverInterface {
    @Override
    public double calculateArithmeticalRoot(double number, double precision) throws RootException {
        if (number < 0.0)
            throw new RootException();
        if (number == 0.0)
            return 0.0;
        return sqrt(number, precision);
    }

    @Override
    public List<Complex> calculateRootOfComplexNumber(Complex number, double precision) throws RootException {
        if (Complex.abs(number) == 0.0){
            List<Complex> result = new ArrayList<>();
            result.add(new Complex(0,0));
            result.add(new Complex(0,0));
            return result;
        }
        return sqrt(number, precision);
    }

    @Override
    public BigDecimal calculateRootOfLongNumber(BigDecimal number, double precision) throws RootException {
        if (number.compareTo(BigDecimal.ZERO) < 0)
            throw new RootException();
        if (number.compareTo(BigDecimal.ZERO) == 0)
            return BigDecimal.ZERO;
        return sqrt(number, precision);
    }

    /**
     * Вычисление корня с заданной точностью
     * @param number Действительное число
     * @param precision Точность
     * @return Корень заданной степени
     * @throws RootException, если превышен лимит времени
     */
    private static double sqrt(double number, double precision) throws RootException {
        double x = Math.sqrt(number);
        int iterations = 0;
        while (Math.abs(x * x - number) > precision && iterations != 10000) {
            x = 0.5 * (x + number / x);
            iterations++;
        }
        if (iterations == 10000)
            throw new RootException();
        return x;
    }

    /**
     * Вычисление корня с заданной точностью
     * @param number Комплексное число
     * @param precision Точность
     * @return Корень заданной степени
     * @throws RootException, если превышен лимит времени
     */
    private static List<Complex> sqrt(Complex number, double precision) throws RootException {
        List<Complex> result = new ArrayList<>();
        result.add(new Complex(0,0));
        result.add(new Complex(0,0));
        int iterations = 0;
        while (Complex.abs(result.get(0).multiply(result.get(0))) - Complex.abs(number) > precision && iterations != 50000) {
            double c = Math.sqrt((number.getReal() + Complex.abs(number)) / 2);
            double d = Math.signum(number.getImaginary()) * Math.sqrt((-number.getReal() + Complex.abs(number)) / 2);
            result.set(0, new Complex(c,d));
            iterations++;
        }
        while (Complex.abs(result.get(1).multiply(result.get(1))) - Complex.abs(number) > precision && iterations != 50000) {
            double c = Math.sqrt((number.getReal() + Complex.abs(number)) / 2);
            double d = Math.signum(number.getImaginary()) * Math.sqrt((-number.getReal() + Complex.abs(number)) / 2);
            result.set(1, new Complex(-c,-d));
            iterations++;
        }
        if (iterations == 100000)
            throw new RootException();
        return result;
    }

    /**
     * Вычисление корня с заданной точностью
     * @param number Действительное длинное число
     * @param precision Точность
     * @return Корень заданной степени
     * @throws RootException, если превышен лимит времени
     */
    private static BigDecimal sqrt(BigDecimal number, double precision) throws RootException {
        BigDecimal x = number.divide(new BigDecimal(2.0));
        int iterations = 0;
        while (abs(x.multiply(x).subtract(number)).compareTo(BigDecimal.valueOf(precision)) > 0 && iterations != 100000) {
            x = BigDecimal.valueOf(0.5).multiply(x.add(number.divide(x)));
            iterations++;
        }
        if (iterations == 100000)
            throw new RootException();
        return x;
    }

    /**
     * Вычисление модуля для длинной арифтетики
     * @param number Число
     * @return Модуль
     */
    private static BigDecimal abs(@org.jetbrains.annotations.NotNull BigDecimal number) {
        return number.compareTo(BigDecimal.ZERO) < 0 ? number.multiply(new BigDecimal(-1.0)) : number;
    }
}
