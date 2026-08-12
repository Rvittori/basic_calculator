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

                int count = 0;
                for (int i = 0; i < expressionList.length; i++) {

                    if (isNumeric(expressionList[i])) {
                        output.push((expressionList[i]));
                        count = count + 1;

                    } else if (expressionList[i].equals(" ")) {
                        continue;

                    } else if ((expressionList[i].equals("("))) {
                        operators.push(expressionList[i]);
                        count = count + 1;

                    } else if (expressionList[i].equals(")")) {
                        while (!operators.isEmpty()) {
                            String currentOnStack = operators.pop();
                            if (!currentOnStack.equals("(")) {
                                output.push(currentOnStack);
                            }
                            count = count + 1;
                        }
                    } else if (expressionList[i].equals("+") || expressionList[i].equals("-") || expressionList[i].equals("*") || expressionList[i].equals("/") || expressionList[i].equals("^")) {

                        // put first operator on the op stack
                        if (operators.isEmpty()) {
                            operators.push(expressionList[i]);
                            count = count + 1;
                            // handle subsequent operators
                        } else {
                            String onStack = operators.peek();
                            if (onStack.equals("(")) {
                                operators.push(expressionList[i]);
                                count = count + 1;
                            } else {
                                int onStackPrecedence = getPrecedence(onStack);
                                String currentOperator = expressionList[i];
                                int currentOperatorPrecedence = getPrecedence(currentOperator);

                                if (onStackPrecedence < currentOperatorPrecedence) {
                                    operators.push(currentOperator);
                                    count = count + 1;
                                } else if (onStackPrecedence == currentOperatorPrecedence) {
                                    String lastOperatorOnStack = operators.pop();
                                    output.push(lastOperatorOnStack);
                                    operators.push(currentOperator);
                                    count = count + 1;
                                }
                            }

                        }
                    }
                }


                System.out.print("Output stack: " + output);
                System.out.print("0perators stack: " + operators);
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

