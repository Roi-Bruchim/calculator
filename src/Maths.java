public interface Maths {

    //*****************************Basic methods********************************

    /**
     * This method adds two double numbers
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public double Add(double a, double b);

    /**
     * This method subtract two double numbers
     * @param a first number
     * @param b second number
     * @return subtraction of a and b
     */
    public double subtract(double a, double b);


    /**
     * This method multiply two double numbers
     * @param a first number
     * @param b second number
     * @return multiplication of a and b
     */
    public double multiply(double a, double b);

    /**
     * This method divides two double numbers
     * @param a first number
     * @param b second number
     * @return division of a and b
     */
    public double division(double a, double b);

    //*****************************Basic methods********************************


    //*****************************Advanced methods*****************************

    /**
     * This method gives fact of given number
     * @param a the number we want his factorial
     * @return the factorial of the number
     */
    public double fact(double a);

    /**
     * This method gives fact of number by chosen exponent
     * @param base the number we want to power
     * @param exponent how many times we multiply him
     * @return the result of the base power of exponent
     */

    public double power(double base, double exponent);

    /**
     * This method give the number of options to choose k from n items
     * @param n The total number of items
     * @param k The number of items to choose
     * @return The number of combinations
     */
    public double choose(double n ,double k);
    /**
     *  This method gives the square root of given number
     * @param a the number we want to get his root
     * @return the square root
     */
    public double squareRoot(double a);

    /**
     * This method gives the absolut value of given number
     * @param a the number we want to get his absolute value
     * @return the absolute value
     */
    public double absoluteValue(double a);

    /**
     * This method calculates the base-10 logarithm of a number
     * @param a The number for which the base-10 logarithm is calculated
     * @return The base-10 logarithm of the given number
     */
    public double logBase10(double a);

    /**
     * This method Calculates the sine of an angle.
     * @param a The angle in radians.
     * @return The sine of the angle.
     */
    public double sin(double a);

    /**
     * This method Calculates the cosine of an angle.
     * @param a The angle in radians.
     * @return The cosine of the angle.
     */
    public double cos(double a);

    /**
     * This method Calculates the tangent of an angle.
     * @param a The angle in radians.
     * @return The tangent of the angle.
     */
    public double tan(double a);

    /**
     * This method Calculates the arcsine of a value.
     * @param a The value for which the arcsine is calculated.
     * @return The angle in radians whose sine is the given value.
     */
    public double arcsin(double a);

    /**
     * This method Calculates the arccosine of a value.
     * @param a The value for which the arccosine is calculated.
     * @return The angle in radians whose cosine is the given value.
     */
    public double arccos(double a);

    /**
     * This method Calculates the arctangent of a value.
     * @param a The value for which the arctangent is calculated.
     * @return The angle in radians whose tangent is the given value.
     */
    public double arctan(double a);

    /**
     * This method Calculates the percentage of a value
     * @param value The original value
     * @param percent The percentage to calculate
     * @return The result of the percentage calculation
     */
    public double percentage(double value, double percent);





    //*****************************Advanced methods*****************************


    //*****************************Symbols methods******************************

    /**
     *
     * @return π value
     */
    public double PI();

    /**
     *
     * @return e value
     */
    public double e();


    //*****************************Symbols methods******************************






}
