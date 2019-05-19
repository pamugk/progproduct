package common;

import java.math.BigDecimal;
import java.util.List;

/**
 * ��������� SqrtSolverInterface ������ ��� ��������� ������� � �����������
 * �� ���������� ������.
 */
public interface SqrtSolverInterface {
    /**
     * ����� calculateArithmeticalRoot ������ ��� ���������� ������ �� ������� �����
     *
     * @param number �����, ������ �� �������� ���������� ���������
     * @param degree ������� �����
     * @param precision ��������, ��������������� �������� ���������� �����
     *
     * @return ���������� �������������� ������ ������� n �� ����� number, ����������� � ��������� e
     *
     * @throws RootException, ���� ������ �������� ������� �� ����� ���� �������� �� �����
     */
    double calculateArithmeticalRoot(double number, double degree, double precision) throws RootException;

    /**
     * ����� calculateRootOfComplexNumber ������ ��� ���������� ������ �� ����������� �����
     *
     * @param number ����������� �����, ����� �� �������� ���������� ���������
     * @param degree ������� �����
     * @param precision ��������, ��������������� �������� ���������� �����
     *
     * @return ���������� ������ ������ ������� n �� ������������ �����, ����������� � ��������� e
     *
     * @throws RootException, ���� ������ �������� ������� �� ����� ���� �������� �� �����
     */
    List<Complex> calculateRootOfComplexNumber(Complex number, double degree, double precision) throws RootException;

    /**
     * ����� calculateRootOfComplexNumber ������ ��� ���������� ������ �� ������� �����
     *
     * @param number ������� �����, ������ �� �������� ���������� ���������
     * @param degree ������� �����
     * @param precision ��������, ��������������� �������� ���������� �����
     *
     * @return ���������� ������ ������� n �� �������� ����� number, ����������� � ��������� e
     *
     * @throws RootException, ���� ������ �������� ������� �� ����� ���� �������� �� �����
     */
    BigDecimal calculateRootOfLongNumber(BigDecimal number, double degree, double precision) throws RootException;
}
