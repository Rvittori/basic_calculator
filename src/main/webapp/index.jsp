<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Basic Calculator</title>
</head>
<body>
<div class="clac-display-container">

</div>
<form class="calculate-form" action="calculator" method="post">
    <div class="calculator-btn-container">
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">

                <button id="num-button" type="button" onclick="clickNum()">AC</button>
                <button id="num-button" type="button" onclick="clickNum()">()</button>
                <button id="num-button" type="button" onclick="clickNum()">%</button>
                <button id="num-button" type="button" onclick="clickNum()">/</button>
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <button id="num-button" type="button" onclick="clickNum()">7</button>
                <button id="num-button" type="button" onclick="clickNum()">8</button>
                <button id="num-button" type="button" onclick="clickNum()">9</button>
                <button id="num-button" type="button" onclick="clickNum()">X</button>
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <button id="num-button" type="button" onclick="clickNum()">4</button>
                <button id="num-button" type="button" onclick="clickNum()">5</button>
                <button id="num-button" type="button" onclick="clickNum()">6</button>
                <button id="num-button" type="button" onclick="clickNum()">-</button>
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <button id="num-button" type="button" onclick="clickNum()">1</button>
                <button id="num-button" type="button" onclick="clickNum()">2</button>
                <button id="num-button" type="button" onclick="clickNum()">3</button>
                <button id="num-button" type="button" onclick="clickNum()">+</button>
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <button id="num-button" type="button" onclick="clickNum()">0</button>
                <button id="num-button" type="button" onclick="clickNum()">.</button>
                <button id="num-button" type="button" onclick="clickNum()">Del</button>
                <button id="num-button" type="button" onclick="clickNum()">=</button>
            </div>
        </div>
    </div>
</form>
<script>
function clickNum(num) {
    document.getElementById("display").value = value + num;
}

function
</script>
</body>
</html>