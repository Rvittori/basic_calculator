<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Basic Calculator</title>
</head>
<body>
<form class="calculate-form" action="calculator" method="post">
    <div class="calculator-btn-container">
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <input type="button" name="all-clear" value="AC">
                <input type="button" name="parenthesis" value="()">
                <input type="button" name="percent" value="%">
                <input type="button" name="divide" value="/">
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <input type="button" name="seven" value="7">
                <input type="button" name="eight" value="8">
                <input type="button" name="nine" value="9">
                <input type="button" name="multiply" value="X">
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <input type="button" name="four" value="4">
                <input type="button" name="five" value="5">
                <input type="button" name="six" value="6">
                <input type="button" name="subtract" value="-">
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <input type="button" name="one" value="1">
                <input type="button" name="two" value="2">
                <input type="button" name="three" value="3">
                <input type="button" name="add" value="+">
            </div>
        </div>
        <div class="calculator-btn-row">
            <div class="calculator-btn-col">
                <input type="button" name="zero" value="0">
                <input type="button" name="decimal" value=".">
                <input type="button" name="delete" value="<-">
                <input type="submit" name="equals" value="=">
            </div>
        </div>
    </div>
</form>
</body>
</html>