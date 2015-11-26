package simpleCount;

/*
 * Interface for Calculator class
 * @author Aimeric Seguin
 * @version 1.0
 */
public interface ICalculator {
	/* Precision of the Calculator */
	static int 		_precision = 8;

	/* Getters / Setters */
	public String	getDisplay();
	public void		setDisplay(final String disp);

	/* Input management */
	public void		addOperand(final String entry);
	public void		addOperator(final String entry);
	public void		addParenthesis(final String entry);
	public void		addDecimal();
	public void		addPi();
	public void		clear();

	/* Scientific */
	public void		square();
	public void		squareRoot();
	public void		logarithm();
	public void		exponential();
	public void		cosinus();
	public void		sinus();
	public void		tangent();

	/* Expression evaluator */
	public void		evalExpr();
}
