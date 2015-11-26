package simpleCount;

/*
 * Calculator program class
 * MVC pattern
 * Resolution used: 450*300
 * @author Aimeric Seguin
 * @version 1.0
 */
public final class Program {
	public static void main(String[] args) {
		Calculator	model = (Calculator) Calculator.getCalculator();
		View		view = new View(model, 450, 300);
		model.addObserver(view);
		model.addOperand("0");
	}
}
