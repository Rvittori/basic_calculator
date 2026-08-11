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

                    if (expressionList[i].equals(" ")) {
                        continue;
                    } else if (expressionList[i].equals("(") || expressionList[i].equals(")")){
                        continue;
                    } else if (isNumeric(expressionList[i])) {
                        output.push((expressionList[i]));

                    } else if (expressionList[i].equals("+") || expressionList[i].equals("-") || expressionList[i].equals("*") || expressionList[i].equals("/") || expressionList[i].equals("^")) {


                        if (operators.isEmpty()) {
                            operators.push(expressionList[i]);
                        } else {
                            String onStack = operators.peek();
                            int onStackPrecedence = getPrecedence(onStack);
                            String currentOperator = expressionList[i];
                            int currentOperatorPrecedence = getPrecedence(currentOperator);

                            if (onStackPrecedence < currentOperatorPrecedence) {
                                operators.push(currentOperator);
                            } else if (onStackPrecedence == currentOperatorPrecedence) {
                                String lastOperator = operators.pop();
                                output.push(lastOperator);
                            }
                        }

                    }
                }

                System.out.print(output);
                System.out.print(operators);
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

