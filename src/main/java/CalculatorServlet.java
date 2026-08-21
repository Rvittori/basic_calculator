import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;


@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("calculate")) {


            String cleanedExpression = getCleanExpression(req.getParameter("display-value"));
            ArrayList<String> reversePostFixExpression = getReversePostFix(cleanedExpression);
            double result = calculate(reversePostFixExpression);

            req.setAttribute("result", result);

            String url = "/index.jsp";
            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        }
    }


    private boolean isDigit(char ch) {
        if (Character.isDigit(ch)) {
            return true;
        } else {
            return false;
        }
    }

    private int getPrecedence(String s) {
        if (s.equals("+") || s.equals("-")) {
            return 1;
        } else if (s.equals("*") || s.equals("/")) {
            return 2;
        } else if (s.equals("^")) {
            return 3;
        } else {
            return -1;
        }
    }

    private static String getCleanExpression(String e) {
        String cleanedExpression = e.replaceAll("\\s+", "");
        return cleanedExpression;
    }

    private ArrayList<String> getReversePostFix(String expression) {
        System.out.println("Expression: " + expression);
        ArrayList<String> output = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();


        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (c == '(') {
                operators.add(String.valueOf(c));
                i++;
            } else if (isDigit(c)) {
                StringBuilder stringBuilder = new StringBuilder();
                while (i < expression.length() && isDigit(expression.charAt(i))) {
                    stringBuilder.append(expression.charAt(i));
                    i++;
                }
                System.out.println("Contents of string builder: " + stringBuilder);
                output.add(String.valueOf(stringBuilder));
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                if (operators.isEmpty()) {
                    operators.add(String.valueOf(c));
                    i++;
                } else {
                    int currentCharPrecedence = getPrecedence(String.valueOf(c));
                    int currentOpOnStackPrecedence = getPrecedence(operators.get(operators.size() - 1));

                    if (currentCharPrecedence > currentOpOnStackPrecedence) {
                        operators.add(String.valueOf(c));
                        i++;
                    } else if (currentCharPrecedence <= currentOpOnStackPrecedence) {
                        while (!operators.isEmpty() && currentCharPrecedence <= currentOpOnStackPrecedence) {
                            if (operators.get(operators.size() - 1).equals("(")) {
                                break;
                            } else {
                                String currentOpOnStack = operators.remove(operators.size() - 1);
                                output.add(currentOpOnStack);
                                if (!operators.isEmpty()) {
                                    currentOpOnStackPrecedence = getPrecedence(operators.get(operators.size() - 1));
                                }
                            }
                        }
                        operators.add(String.valueOf(c));
                        i++;
                    }
                }
            } else if (c == ')') {
                while (!operators.isEmpty()) {
                    String currentOnOpStack = operators.get(operators.size() - 1);

                    if (!currentOnOpStack.equals("(")) {
                        output.add(operators.remove(operators.size() - 1));
                    } else {
                        operators.remove(operators.size() - 1);
                        break;
                    }
                }
                i++;
            }
        }
        while (!operators.isEmpty()) {
            output.add(operators.remove(operators.size() - 1));
        }

        System.out.println("Output stack: " + output);
        System.out.println("Operators stack: " + operators);
        return output;
    }

    private boolean isDigit(String str) {

        try {
            double operand = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private double calculate(ArrayList<String> rpn) {
        System.out.println("Incoming rpn list: " + rpn);
        double result = 0.00;
        Calculator calculator = new Calculator();
        ArrayList<String> operands = new ArrayList<>();
        String operator = "";

        for (String s: rpn) {
            if (isDigit(s)) {
                operands.add(s);
            } else {
                operator = s;

                double rightOperand = Double.parseDouble(operands.remove(0));
                double leftOperand = Double.parseDouble(operands.remove(0));

                switch (operator) {
                    case "+" -> result = calculator.addition(leftOperand, rightOperand);
                    case "-" -> result = calculator.subtraction(leftOperand, rightOperand);
                    case "*" -> result = calculator.multiplication(leftOperand, rightOperand);
                    case "/" -> result = calculator.division(leftOperand, rightOperand);
                    case "^" -> result = Math.pow(leftOperand, rightOperand);
                }

                operands.add(String.valueOf(result));
            }

        }
        System.out.println("Result: " + result);
        return result;
    }
}







