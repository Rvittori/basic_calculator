import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Stack;
import java.lang.Math;


@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("calculate")) {


            String cleanedExpression = getCleanExpression(req.getParameter("display-value"));
            Stack<String> reversePostFixExpression = getReversePostFix(cleanedExpression);
            double result = calculate(reversePostFixExpression);

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

    private Stack<String> getReversePostFix(String expression) {
        System.out.println("Expression: " + expression);
        Stack<String> output = new Stack<>();
        Stack<String> operators = new Stack<>();


        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (c == '(') {
                operators.push(String.valueOf(c));
                i++;

            } else if (isDigit(c)) {
                StringBuilder stringBuilder = new StringBuilder();

                while (i < expression.length() && isDigit(expression.charAt(i))) {
                    stringBuilder.append(expression.charAt(i));
                    i++;
                }
                System.out.println("Contents of string builder: " + stringBuilder);
                output.push(String.valueOf(stringBuilder));
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                if (operators.isEmpty()) {
                    operators.push(String.valueOf(c));
                    i++;
                } else {
                    int currentCharPrecedence = getPrecedence(String.valueOf(c));
                    int currentOpOnStackPrecedence = getPrecedence(operators.peek());

                    if (currentCharPrecedence > currentOpOnStackPrecedence) {
                        operators.push(String.valueOf(c));
                        i++;
                    } else if (currentCharPrecedence <= currentOpOnStackPrecedence) {
                        while (!operators.isEmpty() && currentCharPrecedence <= currentOpOnStackPrecedence) {
                            if (operators.peek().equals("(")) {
                                break;
                            } else {
                                String currentOpOnStack = operators.pop();
                                output.push(currentOpOnStack);
                                if (!operators.isEmpty()) {
                                    currentOpOnStackPrecedence = getPrecedence(operators.peek());
                                }

                            }
                        }
                        operators.push(String.valueOf(c));
                        i++;

                    }
                }
            } else if (c == ')') {
                while (!operators.isEmpty()) {
                    String currentOnOpStack = operators.peek();

                    if (!currentOnOpStack.equals("(")) {
                        output.push(operators.pop());
                    } else {
                        operators.pop();
                    }
                }
                i++;
            }
        }
        while (!operators.isEmpty()) {
            output.push(operators.pop());
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


    private double calculate(Stack<String> rpn) {
        double result = 0.00;
        Calculator calculator = new Calculator();
        Stack<Double> operands = new Stack<>();
        String operator = "";

        while (!rpn.isEmpty()) {
            if (!isDigit(rpn.peek())) {
                operator = rpn.pop();

                double rightOperand = Double.parseDouble(rpn.pop());
                double leftOperand = Double.parseDouble(rpn.pop());

                if (!operator.isEmpty()) {
                    switch (operator) {
                        case "+" -> result = calculator.addition(leftOperand, rightOperand);
                        case "-" -> result = calculator.subtraction(leftOperand, rightOperand);
                        case "*" -> result = calculator.multiplication(leftOperand, rightOperand);
                        case "/" -> result = calculator.division(leftOperand, rightOperand);
                        case "^" -> result = Math.pow(leftOperand, rightOperand);
                    }
                    operands.push(result);
                }
            } else {
                operands.push(Double.valueOf(rpn.pop()));
            }

        }
        result = operands.pop();
        System.out.println("Result: " + result);
        return result;
    }
}







