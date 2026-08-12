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

            Stack<String> reversedPostfix = getReversedPostfix(req);

            Calculator calculator = new Calculator();
            int operand1;
            int operand2 = 0;
            String operator;
            double result = 0.00;
            while (!reversedPostfix.isEmpty()) {
                if (isNumeric(reversedPostfix.peek())) {
                    operand1  = Integer.parseInt(reversedPostfix.pop());
                    if (isNumeric(reversedPostfix.peek())) {
                        operand2 = Integer.parseInt(reversedPostfix.pop());
                        while (isNumeric(reversedPostfix.peek())) {
                            continue;
                        }
                        } if (!isNumeric(reversedPostfix.peek())) {
                        operator = reversedPostfix.pop();
                        switch (operator) {
                            case "+" -> result = calculator.addition(operand1, operand2);
                            case "-" -> result = calculator.subtraction(operand1, operand2);
                            case "*" -> result = calculator.multiplication(operand1, operand2);
                            case "/" -> result = calculator.division(operand1, operand2);
                            case "^" -> result = Math.pow(operand1, operand2);
                        }
                    }
                }
            }
            System.out.print(result);

            String url = "/index.jsp";
            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        }
    }

    private static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        } else {
            try {
                Double.parseDouble(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    private static int getPrecedence(String s) {
        return switch (s) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> -1;
        };
    }


    private Stack<String> getReversedPostfix(HttpServletRequest req) {
        String expression = req.getParameter("display-value");
        Stack<String> reversedPostfix = new Stack<>(); // so first off is start of expression

        System.out.println("DEBUG: expression: " + expression);
        if (expression != null && !expression.isEmpty()) {

            Stack<String> output = new Stack<>();
            Stack<String> operators = new Stack<>();

            String[] expressionList = expression.split("");


            int listLength = expressionList.length;
            for (int i = 0; i < listLength; i++) {

                if (expressionList[i].equals("(")) {
                    operators.push(expressionList[i]);

                } else if (isNumeric(expressionList[i])) {
                    output.push(expressionList[i]);
                } else if (expressionList[i].equals("+") || expressionList[i].equals("-") ||
                        expressionList[i].equals("*") || expressionList[i].equals("/") ||
                        expressionList[i].equals("^")) {

                    if (operators.isEmpty()) {
                        operators.push(expressionList[i]);
                    } else {
                        String currentExpressionItem = expressionList[i];
                        int currentExpressionItemPrecedence = getPrecedence(currentExpressionItem);
                        int lastItemOnOperatorsStackPrecedence = getPrecedence(operators.peek());

                        if (currentExpressionItemPrecedence > lastItemOnOperatorsStackPrecedence) {
                            operators.push(currentExpressionItem);
                        } else if (currentExpressionItemPrecedence <= lastItemOnOperatorsStackPrecedence) {
                            String lastItemOnOperatorsStack = operators.pop();
                            output.push(lastItemOnOperatorsStack);
                            operators.push(currentExpressionItem);
                        }
                    }
                } else if (expressionList[i].equals(")")) {
                    while (!operators.peek().equals("(")) {
                        String currentOperatorStackItem = operators.pop();
                        output.push(currentOperatorStackItem);
                    }
                    operators.pop();
                    String currentOperatorStackItem = operators.pop();
                    output.push(currentOperatorStackItem);
                }
            }
            while (!operators.isEmpty()) {
                String currentOperatorItem = (operators.pop());
                if (currentOperatorItem.equals("(")) {
                    continue;
                }
                output.push(currentOperatorItem);
            }



            while (!output.isEmpty()) {
                reversedPostfix.push(output.pop());
            }


            System.out.print("Output stack: " + output);
            System.out.print("Operators stack: " + operators);
            System.out.print("ReverseStack: " + reversedPostfix);
        }
        return reversedPostfix;
    }
}

