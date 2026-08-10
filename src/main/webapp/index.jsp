<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Basic Calculator</title>
</head>
<body>
<form class="calculate-form" action="calculator" method="post">
    <div class="clac-display-container">
        <input id="display" name="display-value" type="text" readonly>
    </div>
    <div class="calculator-btn-container">
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">

                <button class="num-button" type="button" value="AC" onclick="clickOp('AC')">AC</button>
                <button class="num-button" type="button" value="()" onclick="clickOp('(')">(</button>
                <button class="num-button" type="button" value="()" onclick="clickOp(')')">)</button>
                <button class="num-button" type="button" value="%" onclick="clickOp('%')">%</button>
                <button class="num-button" type="button" value="/" onclick="clickOp('/')">/</button>
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <button class="num-button" type="button" value="7" onclick="clickNum('7')">7</button>
                <button class="num-button" type="button" value="8" onclick="clickNum('8')">8</button>
                <button class="num-button" type="button" value="9" onclick="clickNum('9')">9</button>
                <button class="num-button" type="button" value="X" onclick="clickOp('x')">x</button>
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <button class="num-button" type="button" value="4" onclick="clickNum('4')">4</button>
                <button class="num-button" type="button" value="5" onclick="clickNum('5')">5</button>
                <button class="num-button" type="button" value="6" onclick="clickNum('6')">6</button>
                <button class="num-button" type="button" value="-" onclick="clickOp('-')">-</button>
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <button class="num-button" type="button" value="1" onclick="clickNum('1')">1</button>
                <button class="num-button" type="button" value="2" onclick="clickNum('2')">2</button>
                <button class="num-button" type="button" value="3" onclick="clickNum('3')">3</button>
                <button class="num-button" type="button" value="+" onclick="clickOp('+')">+</button>
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <button class="num-button" type="button" value="0" onclick="clickNum('0')">0</button>
                <button class="num-button" type="button" value="." onclick="clickOp('.')">.</button>
                <button class="num-button" type="button" value="Del" onclick="clickDel()">Del</button>
                <button class="num-button" type="submit" value="=">=</button>
            </div>
        </div>
    </div>
</form>
<script>
    function clickNum(num) {
        let display = document.getElementById("display");

        display.value = display.value + num;
    }

    function clickOp(op) {
        let display = document.getElementById("display");

        if (op === "AC") {
            display.value = "";
        } else {
            display.value = display.value + " " + op + " ";
        }
        document.getElementById("display").value = value + " " + op + " ";
    }

    function clickDel() {
        let display = document.getElementById("display");

        display.value = display.value.slice(0, -1)
    }
</script>
</body>
</html>