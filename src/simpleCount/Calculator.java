package simpleCount;
import java.util.Observable;
import java.math.BigDecimal;

/*
 * Calculator class
 * Model for MVC pattern
 * Singleton pattern
 * @author Aimeric Seguin
 * @version 1.0
 */
public class Calculator extends Observable implements ICalculator {
	private final static ICalculator	_calculator = new Calculator();
	private String						_display = new String("0");
	private	final RPN					_rpn = new RPN();
	private boolean						_error = false;

	/**
	 * Handle decimal values
	 * Notify the observers
	 */
	@Override
	public void addDecimal() {
		if (_error)
			return;
		int len = _display.length();
		char lastChar = (len >= 1) ? _display.charAt(len - 1) : 0;
		if (lastChar != '.')
			_display += ".";
		setChanged();
		notifyObservers(_display);
	}
	
	/**
	*	Handle Pi
	*	Notify the observers
	 */
	@Override
	public void	addPi() {
		if (_error)
			return;
		int len = _display.length();
		char lastChar = (len >= 1) ? _display.charAt(len - 1) : 0;
		if (_display.equals("0")) {
			if (isOperator(lastChar))
				_display = " " + String.valueOf(new BigDecimal(Math.PI).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue());
			else
				_display = String.valueOf(new BigDecimal(Math.PI).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		else {
			if (isOperator(lastChar))
				_display += " " + String.valueOf(new BigDecimal(Math.PI).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue());
			else
				_display += String.valueOf(new BigDecimal(Math.PI).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue());
			
		}
		setChanged();
		notifyObservers(_display);
	}
	
	/**
	 * Handle an operand
	 * Notify the observers
	 * @param operand: an operand
	 */
	@Override
	public void	addOperand(final String operand)
	{
		if (_error)
			return;
		if (_display.equals("0"))
			_display = operand;
		else {
			int len = _display.length();
			char lastChar = (len >= 1) ? _display.charAt(len - 1) : 0;
			if (len > 1 && isOperator(lastChar) && !(len >= 4 && _display.charAt(len - 4) == '('))
				_display += " ";
			_display += operand;
		}
		setChanged();
		notifyObservers(_display);
	}
	
	/**
	 * Handle an operator
	 * @param operator: an operator
	 * Notify the observers
	 */
	@Override
	public void	addOperator(final String operator)
	{
		if (_error)
			return;
		if (_display.equals("0"))
			_display = operator;
		else {
			int len = _display.length();
			char lastChar = (len >= 1) ? _display.charAt(len - 1) : 0;
			if (isOperator(lastChar)) {
				char[] newDisplay = _display.toCharArray();
				newDisplay[len - 1] = operator.charAt(0);
				_display = String.valueOf(newDisplay);
				setChanged();
				notifyObservers(_display);
				return;
			}
			if (lastChar != 0)
				_display += " ";
			_display += operator;
		}
		setChanged();
		notifyObservers(_display);
	}
	
	/**
	 * Handle a parenthesis
	 * @param parenthesis: a left or right parenthesis
	 * Notify the observers
	 */
	@Override
	public void addParenthesis(final String parenthesis) {
		if (_error)
			return;
		int len = _display.length();
		char lastChar = (len >= 1) ? _display.charAt(len - 1) : 0;
		if (_display.equals("0")) {
			if (isOperator(lastChar))
				_display = (parenthesis.charAt(0) == '(') ? (" " + parenthesis + " ") : (" " + parenthesis); 
			else
				_display = (parenthesis.charAt(0) == '(') ? (parenthesis + " ") : (" " + parenthesis); 
		}
		else {
			if (isOperator(lastChar))
				_display += (parenthesis.charAt(0) == '(') ? (" " + parenthesis + " ") : (" " + parenthesis);
			else
				_display += (parenthesis.charAt(0) == '(') ? (parenthesis + " ") : (" " + parenthesis);
		}
		setChanged();
		notifyObservers(_display);
	}
	
	/**
	*	Set the expression to its fallback value
	*	Notify the observers
	 */
	@Override
	public void	clear() {
		_display = new String("0");
		_error = false;
		setChanged();
		notifyObservers(_display);
	}
	
	/**
	 * Evaluate the current expression
	 * Transform the infix current expression
	 * into a postfix expression and evaluate it
	 * Notify the observers
	 */
	@Override
	public void evalExpr() {
	if (_error)
		return;
	try {
	      double result = new Double(Double.parseDouble(_rpn.evaluateRPN(_rpn.toRPN(_display))));
	      double formatedResult = new BigDecimal(result).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue();
	      _display = (result % 1 == 0) ? String.valueOf((int) result) : String.valueOf(formatedResult);
		  setChanged();
		  notifyObservers(_display);
		}
	catch (Exception err) {
		_display = err.getLocalizedMessage();
		_error = true;
		setChanged();
		notifyObservers(_display);
		}
    }
	
	/**
	*	Set the expression to the power of 2
	*	Notify the observers
	 */
	@Override
	public void	square() {
		if (_error)
			return;
		evalExpr();
		double result = Math.pow(Double.parseDouble(_display), 2);
		double formatedResult = new BigDecimal(result).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue();
		_display = (result % 1 == 0) ? String.valueOf((int) result) : String.valueOf(formatedResult);
		setChanged();
		notifyObservers(_display);
	}
	
	/**
	*	Set the expression to its square root
	*	Notify the observers
	 */
	@Override
	public void	squareRoot() {
		if (_error)
			return;
		evalExpr();
		double result = Math.sqrt(Double.parseDouble(_display));
		double formatedResult = new BigDecimal(result).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue();
		_display = (result % 1 == 0) ? String.valueOf((int) result) : String.valueOf(formatedResult);
		setChanged();
		notifyObservers(_display);
	}
	
	/**
	*	Set the expression to its logarithm
	*	Notify the observers
	 */
	@Override
	public void	logarithm() {
		if (_error)
			return;
		evalExpr();
		try {
		double result = Math.log(Double.parseDouble(_display));
		double formatedResult = new BigDecimal(result).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue();
		_display = (result % 1 == 0) ? String.valueOf((int) result) : String.valueOf(formatedResult);
		setChanged();
		notifyObservers(_display);
		}
		catch (Exception err) {
			_display = "Error";
			_error = true;
			setChanged();
			notifyObservers(_display);
		}
	}
	
	/**
	*	Set the expression to its exponential
	*	Notify the observers
	 */
	@Override
	public void	exponential() {
		if (_error)
			return;
		evalExpr();
		try {
		double result = Math.exp(Double.parseDouble(_display));
		double formatedResult = new BigDecimal(result).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue();
		_display = (result % 1 == 0) ? String.valueOf((int) result) : String.valueOf(formatedResult);
		setChanged();
		notifyObservers(_display);
		}
		catch (Exception err) {
			_display = "Error";
			_error = true;
			setChanged();
			notifyObservers(_display);
		}
	}
	
	/**
	*	Set the expression transformed in radians to its cosinus
	*	Notify the observers
	 */
	@Override
	public void cosinus() {
		if (_error)
			return;
		evalExpr();
		try {
		double result = Math.cos(Math.toRadians(Double.parseDouble(_display)));
		double formatedResult = new BigDecimal(result).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue();
		_display = (result % 1 == 0) ? String.valueOf((int) result) : String.valueOf(formatedResult);
		setChanged();
		notifyObservers(_display);
		}
		catch (Exception err) {
			_display = "Error";
			_error = true;
			setChanged();
			notifyObservers(_display);
		}
	}
	
	/**
	*	Set the expression transformed in radians to its sinus
	*	Notify the observers
	 */
	@Override
	public void sinus() {
		if (_error)
			return;
		evalExpr();
		try {
		double result = Math.sin(Math.toRadians(Double.parseDouble(_display)));
		double formatedResult = new BigDecimal(result).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue();
		_display = (result % 1 == 0) ? String.valueOf((int) result) : String.valueOf(formatedResult);
		setChanged();
		notifyObservers(_display);
		}
		catch (Exception err) {
			_display = "Error";
			_error = true;
			setChanged();
			notifyObservers(_display);
		}
	}
	
	/**
	*	Set the expression transformed in radians to its tangent
	*	Notify the observers
	 */
	@Override
	public void tangent() {
		if (_error)
			return;
		evalExpr();
		try {
		double result = Math.tan(Math.toRadians(Double.parseDouble(_display)));
		double formatedResult = new BigDecimal(result).setScale(_precision, BigDecimal.ROUND_HALF_UP).doubleValue();
		_display = (result % 1 == 0) ? String.valueOf((int) result) : String.valueOf(formatedResult);
		setChanged();
		notifyObservers(_display);
		}
		catch (Exception err) {
			_display = "Error";
			_error = true;
			setChanged();
			notifyObservers(_display);
		}
	}
	
	/**
	*	Operator matcher
	*	@param ch: a character
	*	@return	(true) if the character is an operator or (false) else
	 */
	public boolean	isOperator(final char ch) {
		return (ch == '%' || ch == '*' || ch == '/' || ch == '+' || ch == '-' || ch == '^');
	}
	
	private Calculator() {}

	public static ICalculator getCalculator() {
		return _calculator;
	}
	
	@Override
	public String getDisplay() {
		return _display;
	}

	@Override
	public void setDisplay(final String disp) {
		_display = disp;
	}
}