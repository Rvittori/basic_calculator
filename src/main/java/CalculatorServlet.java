import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("calculate")) {
            String expression = req.getParameter("display-value");

            System.out.println("DEBUG: expression: " + expression);
            if (expression != null && !expression.isEmpty()) {

                Stack<String> output = new Stack<>();
                Stack<String> operators = new Stack<>();

                String[] expressionList = expression.split("");


                for (int i = 0; i < expressionList.length; i++) {

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
                            int lastItemOnOperatorsStackPrecedence =getPrecedence(operators.peek());

                            if (currentExpressionItemPrecedence > lastItemOnOperatorsStackPrecedence) {
                                output.push(currentExpressionItem);
                            } else if (currentExpressionItemPrecedence <= lastItemOnOperatorsStackPrecedence) {
                                String lastItemOnOperatorsStack = operators.pop();
                                output.push(lastItemOnOperatorsStack);
                                operators.push(currentExpressionItem);
                            }
                        }
                    }
                }
                while (!operators.isEmpty()) {
                    String currentOperatorItem = (operators.pop());
                    output.push(currentOperatorItem);
                }

                System.out.print("Output stack: " + output);
                System.out.print("Operators stack: " + operators);
                    String url = "/index.jsp";
                    getServletContext().getRequestDispatcher(url)
                            .forward(req, resp);
                }
            }
        }

    private static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        } else {
            try {
                Double.parseDouble(s);
                return true;
            } catch (NumberFormatException e){
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
}

