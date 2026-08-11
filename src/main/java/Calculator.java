public class Calculator {

    private double num1;
    private double num2;
    private char operator;

    public Calculator(){

    }

    public Calculator (double num1, double num2, char operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }


    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }

    public char getOperator() {
        return operator;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public double addition(double num1, double num2) {

        double culculatedNum  = num1 + num2;
        return culculatedNum;
    }

    public double subtraction(double num1, double num2) {

        double culculatedNum = num1 - num2;
        return culculatedNum;
    }

    public double multiplication(double num1, double num2) {

        double culculatedNum = num1 * num2;
        return culculatedNum;
    }

    public double division(double num1, double num2) {

        if (num2 == 0) {
            throw new IllegalArgumentException("Division by zero is undefined. Please try again.");
        } else {
            double culculatedNum = num1 / num2;
            return culculatedNum;
        }

    }

}
