import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Stack;


@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("calculate")) {




            String url = "/index.jsp";
            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        }
    }

    private static boolean isDigit(char ch) {
        if (Character.isDigit(ch)){
            return true;
        } else {
            return false;
        }
    }

    private static int getPrecedence(char c) {
        if (c == '+' || c =='-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '^') {
            return 3;
        } else {
            return -1;
        }
    }

    private static Stack<Character> getReversePostFix(HttpServletRequest req) {

        String expression = req.getParameter("display-value");

        System.out.print("Expression: " + expression);
        Stack<Character> output = new Stack();
        Stack<Character> operators = new Stack<>();

        for (int i =0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '(') {
                operators.push(c);
            } else if (isDigit(c)) {
                output.push(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                if (operators.isEmpty()) {
                    operators.push(c);
                } else {

                }
            }
        }
    }


}



