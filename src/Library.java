public class Library implements Maths {


    //*****************************Basic methods********************************
    @Override
    public double Add(double a, double b) {
        return a+b;
    }

    @Override
    public double subtract(double a, double b) {
        return a-b;
    }

    @Override
    public double multiply(double a, double b) {
        return a*b;
    }

    @Override
    public double division(double a, double b) {
        if(b==0)
            throw new ArithmeticException("Can't divides by zero");
        return a/b;
    }
    //*****************************Basic methods********************************


    //*****************************Advanced methods*****************************
    @Override
    public double fact(double a) {
        if(a<0)
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        if(a==0 || a==1)
            return 1;
        return a*fact(a-1);
    }

    @Override
    public double power(double base, double exponent) {
       return Math.pow(base,exponent);
    }

    @Override
    public double choose(double n, double k) {
        if(k>n)
            throw new IllegalArgumentException("k cannot be greater than n");
        if(k==0 || k==n)
            return 1;
        return choose(n-1,k-1) + choose(n-1,k);
    }

    @Override
    public double squareRoot(double a) {
        return power(a,0.5);
    }

    @Override
    public double absoluteValue(double a) {
        if(a>=0)
            return a;
        return a*(-1);
    }

    public double logBase10(double a) {
        if (a<=0) {
            throw new IllegalArgumentException("Input must be greater than 0");
        }
        return Math.log10(a);
    }

    @Override
    public double sin(double a) {
        return Math.sin(Math.toRadians(a));
    }

    @Override
    public double cos(double a) {
        if (a == 90 || a == 270)
            return 0.0;
        return Math.cos(Math.toRadians(a));
    }

    @Override
    public double tan(double a) {
        return Math.tan(Math.toRadians(a));
    }

    @Override
    public double arcsin(double a) {
        return Math.asin(a);
    }

    @Override
    public double arccos(double a) {
        return Math.acos(a);
    }

    @Override
    public double arctan(double a) {
        return Math.atan(a);
    }

    @Override
    public double percentage(double value, double percent) {
        return value*(percent/100);
    }

    //*****************************Advanced methods*****************************


    //*****************************Symbols methods******************************
    @Override
    public double PI() {
        return Math.PI;
    }

    @Override
    public double e() {
        return Math.E;
    }

    //*****************************Symbols methods******************************


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isOperator(String str) {
        return str.matches("[+\\-*/^√]");
    }

    public static double applyOperation(double a, double b, String operator) {
        Library l= new Library();
        switch (operator) {
            case "+":
                return l.Add(a,b);
            case "-":
                return l.subtract(a,b);
            case "*":
                return l.multiply(a,b);
            case "/":
                if (b == 0) throw new ArithmeticException("Cannot divide by zero");
                return l.division(a,b);
            case "^":
                return l.power(a,b);
            case "√":
                return l.squareRoot(a);
            case "%":
                return l.percentage(a,b);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    public static boolean hasPrecedence(String op1, String op2) {
        // Prioritize exponentiation and square root
        if (op1.equals("^") || op1.equals("√")) {
            return true;
        }
        // Prioritize multiplication, division, and percentage over addition and subtraction
        if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) && (op2.equals("+") || op2.equals("-"))) {
            return false;
        }
        // Addition and subtraction have lower precedence than multiplication, division, and percentage
        return true;
    }


    public boolean isFunction(String str) {
        return str.matches("(sin|cos|tan|sqrt|log|abs)");
    }

    public static double applyFunction(String function, double arg) {
        Library library = new Library();
        switch (function) {
            case "sin":
                return library.sin(arg);
            case "cos":
                return library.cos(arg);
            case "tan":
                return library.tan(arg);
            case "sqrt":
                return library.squareRoot(arg);
            case "log":
                return library.logBase10(arg);
            case "abs":
                return library.absoluteValue(arg);
            default:
                throw new IllegalArgumentException("Unknown function: " + function);
        }
    }


}
