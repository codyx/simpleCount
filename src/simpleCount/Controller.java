package simpleCount;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import javax.swing.JButton;

/*
 * Controller class
 * Controller for MVC pattern
 * @author Aimeric Seguin
 * @version 1.0
 */
public class Controller {	
	/**
	 * Handle the "tan" button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void tan(final ActionEvent event) {
		try {
			Class<?> params[] = {};
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("tangent", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "sin" button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void sin(final ActionEvent event) {
		try {
			Class<?> params[] = {};
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("sinus", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "cos" button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void cos(final ActionEvent event) {
		try {
			Class<?> params[] = {};
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("cosinus", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "EXP" button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void exp(final ActionEvent event) {
		try {
			Class<?> params[] = {};
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("exponential", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "log" button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void log(final ActionEvent event) {
		try {
			Class<?> params[] = {};
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("logarithm", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "sqrt" button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void sqrt(final ActionEvent event) {
		try {
			Class<?> params[] = {};
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("squareRoot", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "pow" button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void pow(final ActionEvent event) {
		try {
			Class<?> params[] = {};
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("square", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "." button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void	dot(final ActionEvent event) {
		try {
			Class<?> params[] = {};
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("addDecimal", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "Pi" button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void	pi(final ActionEvent event) {
		try {
			Class<?> params[] = {};
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("addPi", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "C" button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void	clear(final ActionEvent event) {
		try {
			Class<?> params[] = {};
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("clear", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the [+/*%+-] buttons
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void	operator(final ActionEvent event) {
		try {
			JButton btn = (JButton) event.getSource();
			@SuppressWarnings("rawtypes")
			Class[] param = new Class[1];
			param[0] = String.class;
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("addOperator", param);
			method.invoke(model, btn.getText());
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "(|)" buttons
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void	parenthesis(final ActionEvent event) {
		try {
			JButton btn = (JButton) event.getSource();
			@SuppressWarnings("rawtypes")
			Class[] param = new Class[1];
			param[0] = String.class;
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("addParenthesis", param);
			method.invoke(model, btn.getText());
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}

	/**
	 * Handle the "[0-9]" buttons
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void	operand(final ActionEvent event) {
		try {
			JButton btn = (JButton) event.getSource();
			@SuppressWarnings("rawtypes")
			Class[] param = new Class[1];
			param[0] = String.class;
			Object model = _model;
			Method method = model.getClass().getDeclaredMethod("addOperand", param);
			method.invoke(model, btn.getText());
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	/**
	 * Handle the "=" button
	 * @param event: button event
	 * Invoke the model related method by reflection
	 */
	public void	equals(final ActionEvent event) {
		try {
			Object model = _model;
			Class<?> params[] = {};
			Method method = model.getClass().getDeclaredMethod("evalExpr", params);
			method.invoke(model);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	View	getView() {
		return _view;
	}
	
	ICalculator	getModel() {
		return _model;
	}

	Controller(final View view, final ICalculator model) {
		_view = view;
		_model = model;
	}
	private final View		_view;
	private final ICalculator	_model;
}