package common;

import java.math.BigDecimal;
import java.util.List;

/**
 * Интерфейс SqrtSolverInterface служит для получения доступа к функционалу
 * по вычислению корней.
 */
public interface SqrtSolverInterface {
    /**
     * Метод calculateArithmeticalRoot служит для вычисления корней от обычных чисел
     *
     * @param number Число, корень от которого необходимо вычислить
     * @param degree Степень корня
     * @param precision Величина, характеризующая точность вычисления корня
     *
     * @return Возвращает арифметический корень степени n от числа number, вычисленный с точностью e
     *
     * @throws RootException, если корень желаемой степени не может быть вычислен от числа
     */
    double calculateArithmeticalRoot(double number, double degree, double precision) throws RootException;

    /**
     * Метод calculateRootOfComplexNumbe</code> служит для вычисления корней от комплексных чисел
     *
     * @param number Комплексное число, корни от которого необходимо вычислить
     * @param degree Степень корня
     * @param precision Величина, характеризующая точность вычисления корня
     *
     * @return Возвращает список корней степени n от комплексного числа, вычисленных с точностью e
     *
     * @throws RootException, если корень желаемой степени не может быть вычислен от числа
     */
    List<Complex> calculateRootOfComplexNumber(Complex number, double degree, double precision) throws RootException;

    /**
     * Метод calculateRootOfComplexNumber служит для вычисления корней от длинных чисел
     *
     * @param number Длинное число, корень от которого необходимо вычислить
     * @param degree Степень корня
     * @param precision Величина, характеризующая точность вычисления корня
     *
     * @return Возвращает корень степени n от длинного числа number, вычисленный с точностью e
     *
     * @throws RootException, если корень желаемой степени не может быть вычислен от числа
     */
    BigDecimal calculateRootOfLongNumber(BigDecimal number, double degree, double precision) throws RootException;
}
