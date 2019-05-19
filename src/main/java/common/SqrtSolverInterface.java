package common;

import java.math.BigDecimal;
import java.util.List;

/**
 * »нтерфейс SqrtSolverInterface служит дл€ получени€ доступа к функционалу
 * по вычислению корней.
 */
public interface SqrtSolverInterface {
    /**
     * ћетод calculateArithmeticalRoot служит дл€ вычислени€ корней от обычных чисел
     *
     * @param number „исло, корень от которого необходимо вычислить
     * @param precision ¬еличина, характеризующа€ точность вычислени€ корн€
     *
     * @return ¬озвращает арифметический корень степени n от числа number, вычисленный с точностью e
     *
     * @throws RootException, если корень желаемой степени не может быть вычислен от числа
     */
    double calculateArithmeticalRoot(double number, double precision) throws RootException;

    /**
     * ћетод calculateRootOfComplexNumbe</code> служит дл€ вычислени€ корней от комплексных чисел
     *
     * @param number  омплексное число, корни от которого необходимо вычислить
     * @param precision ¬еличина, характеризующа€ точность вычислени€ корн€
     *
     * @return ¬озвращает список корней степени n от комплексного числа, вычисленных с точностью e
     *
     * @throws RootException, если корень желаемой степени не может быть вычислен от числа
     */
    List<Complex> calculateRootOfComplexNumber(Complex number, double precision) throws RootException;

    /**
     * ћетод calculateRootOfComplexNumber служит дл€ вычислени€ корней от длинных чисел
     *
     * @param number ƒлинное число, корень от которого необходимо вычислить
     * @param precision ¬еличина, характеризующа€ точность вычислени€ корн€
     *
     * @return ¬озвращает корень степени n от длинного числа number, вычисленный с точностью e
     *
     * @throws RootException, если корень желаемой степени не может быть вычислен от числа
     */
    BigDecimal calculateRootOfLongNumber(BigDecimal number, double precision) throws RootException;
}
