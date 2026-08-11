import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("calculate")) {
            String expression = req.getParameter("display-value");

            if (expression != null && !expression.isEmpty()) {
                if (expression.contains("+")) {
                    String[] expressionArray = expression.split("\\+");

                    Calculator calculator = new Calculator();
                    double num1 = Double.parseDouble(expressionArray[0]);
                    double num2 = Double.parseDouble(expressionArray[1]);
                    double result = calculator.addition(num1, num2);
                    if (expressionArray.length > 2) {
                        int i = 3;
                        while (i < expressionArray.length) {
                            num1 = i;
                            result = calculator.addition(num1, result);
                            i++;
                        }
                    }
                } else if (expression.contains("-")) {
                    String[] expressionArray = expression.split("-");

                    Calculator calculator = new Calculator();
                    double num1 = Double.parseDouble(expressionArray[0]);
                    double num2 = Double.parseDouble(expressionArray[1]);
                    double result = calculator.subtraction(num1, num2);
                    if (expressionArray.length > 2) {
                        int i = 3;
                        while (i < expressionArray.length) {
                            num1 = i;
                            result = calculator.subtraction(num1, result);
                            i++;
                        }
                    }
                } else if (expression.contains("*")) {
                        String[] expressionArray = expression.split("\\*");

                        Calculator calculator = new Calculator();
                        double num1 = Double.parseDouble(expressionArray[0]);
                        double num2 = Double.parseDouble(expressionArray[1]);
                        double result = calculator.multiplication(num1, num2);
                        if (expressionArray.length > 2) {
                            int i = 3;
                            while (i < expressionArray.length) {
                                num1 = i;
                                result = calculator.multiplication(num1, result);
                                i++;
                            }
                        }
                } else if (expression.contains("/")) {
                    String[] expressionArray = expression.split("/");

                    Calculator calculator = new Calculator();
                    double num1 = Double.parseDouble(expressionArray[0]);
                    double num2 = Double.parseDouble(expressionArray[1]);
                    double result = calculator.multiplication(num1, num2);
                    if (expressionArray.length > 2) {
                        int i = 3;
                        while (i < expressionArray.length) {
                            num1 = i;
                            result = calculator.division(num1, result);
                            i++;
                        }
                    }
                    req.setAttribute("result", result);
                    String url = "/index.jsp";
                    getServletContext().getRequestDispatcher(url)
                            .forward(req, resp);
                }
            }
        }
    }
}
