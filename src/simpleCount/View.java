package simpleCount;
import javax.swing.JFrame;

import java.util.Observable;
import java.util.Observer;
import static simpleCount.ListenerProxy.actionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

/*
 * View class
 * View for MVC pattern
 * @author Aimeric Seguin
 * @version 1.0
 */
public class View extends JFrame implements Observer {
	private static final long 	serialVersionUID = -8025787168822226745L;
	private ICalculator			_model = null;
	private Controller			_controller = null;
	private JTextField 			_screen = new JTextField();

	View(ICalculator calc, final int height, final int width) {
		_model = calc;
		_controller = new Controller(this, _model);
		
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("cos");
		btnNewButton.setBounds(20, 54, 89, 23);
		btnNewButton.addActionListener(actionListener(_controller, "cos"));
		getContentPane().add(btnNewButton);
		
		JButton btnSin = new JButton("sin");
		btnSin.setBounds(20, 88, 89, 23);
		btnSin.addActionListener(actionListener(_controller, "sin"));
		getContentPane().add(btnSin);
		
		JButton btnTan = new JButton("tan");
		btnTan.setBounds(20, 122, 89, 23);
		btnTan.addActionListener(actionListener(_controller, "tan"));
		getContentPane().add(btnTan);
		
		JButton btnSqrt = new JButton("sqrt");
		btnSqrt.setBounds(20, 156, 89, 23);
		btnSqrt.addActionListener(actionListener(_controller, "sqrt"));
		getContentPane().add(btnSqrt);
		
		JButton btnSqr = new JButton("pow");
		btnSqr.setBounds(20, 190, 89, 23);
		btnSqr.addActionListener(actionListener(_controller, "pow"));
		getContentPane().add(btnSqr);
		
		JButton btnLog = new JButton("log");
		btnLog.setBounds(20, 224, 89, 23);
		btnLog.addActionListener(actionListener(_controller, "log"));
		getContentPane().add(btnLog);
		
		JButton btnExp = new JButton("EXP");
		btnExp.setBounds(20, 20, 89, 23);
		btnExp.addActionListener(actionListener(_controller, "exp"));
		getContentPane().add(btnExp);
		
		JButton btnC = new JButton("C");
		btnC.setBackground(new Color(255, 0, 0));
		btnC.setBounds(335, 54, 89, 23);
		btnC.addActionListener(actionListener(_controller, "clear"));
		getContentPane().add(btnC);
		
		JButton button = new JButton("%");
		button.setBounds(335, 88, 89, 23);
		button.addActionListener(actionListener(_controller, "operator"));
		getContentPane().add(button);
		
		JButton button_1 = new JButton("/");
		button_1.setBounds(335, 122, 89, 23);
		button_1.addActionListener(actionListener(_controller, "operator"));
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("*");
		button_2.setBounds(335, 156, 89, 23);
		button_2.addActionListener(actionListener(_controller, "operator"));
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("-");
		button_3.setBounds(335, 190, 89, 23);
		button_3.addActionListener(actionListener(_controller, "operator"));
		getContentPane().add(button_3);
		
		JButton button_4 = new JButton("+");
		button_4.setBounds(335, 224, 89, 23);
		button_4.addActionListener(actionListener(_controller, "operator"));
		getContentPane().add(button_4);
		
		JButton button_5 = new JButton("=");
		button_5.setBackground(Color.GREEN);
		button_5.setBounds(239, 224, 90, 23);
		button_5.addActionListener(actionListener(_controller, "equals"));
		getContentPane().add(button_5);
		
		JButton button_6 = new JButton("(");
		button_6.setBounds(189, 88, 66, 23);
		button_6.addActionListener(actionListener(_controller, "parenthesis"));
		getContentPane().add(button_6);
		
		JButton button_7 = new JButton(")");
		button_7.setBounds(259, 88, 66, 23);
		button_7.addActionListener(actionListener(_controller, "parenthesis"));
		getContentPane().add(button_7);
		
		JButton button_8 = new JButton("0");
		button_8.setBounds(119, 224, 49, 23);
		button_8.addActionListener(actionListener(_controller, "operand"));
		getContentPane().add(button_8);
		
		JButton btnNewButton_1 = new JButton(".");
		btnNewButton_1.setBounds(180, 224, 49, 23);
		btnNewButton_1.addActionListener(actionListener(_controller, "dot"));
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("1");
		btnNewButton_2.setBounds(119, 190, 66, 23);
		btnNewButton_2.addActionListener(actionListener(_controller, "operand"));
		getContentPane().add(btnNewButton_2);
		
		JButton button_9 = new JButton("2");
		button_9.setBounds(189, 190, 66, 23);
		button_9.addActionListener(actionListener(_controller, "operand"));
		getContentPane().add(button_9);
		
		JButton button_10 = new JButton("3");
		button_10.setBounds(259, 190, 66, 23);
		button_10.addActionListener(actionListener(_controller, "operand"));
		getContentPane().add(button_10);
		
		JButton button_11 = new JButton("4");
		button_11.setBounds(119, 156, 66, 23);
		button_11.addActionListener(actionListener(_controller, "operand"));
		getContentPane().add(button_11);
		
		JButton button_12 = new JButton("5");
		button_12.setBounds(189, 156, 66, 23);
		button_12.addActionListener(actionListener(_controller, "operand"));
		getContentPane().add(button_12);
		
		JButton button_13 = new JButton("6");
		button_13.setBounds(259, 156, 66, 23);
		button_13.addActionListener(actionListener(_controller, "operand"));
		getContentPane().add(button_13);
		
		JButton button_14 = new JButton("7");
		button_14.setBounds(119, 122, 66, 23);
		button_14.addActionListener(actionListener(_controller, "operand"));
		getContentPane().add(button_14);
		
		JButton button_15 = new JButton("8");
		button_15.setBounds(189, 122, 66, 23);
		button_15.addActionListener(actionListener(_controller, "operand"));
		getContentPane().add(button_15);
		
		JButton button_16 = new JButton("9");
		button_16.setBounds(259, 122, 66, 23);
		button_16.addActionListener(actionListener(_controller, "operand"));
		getContentPane().add(button_16);
		
		JButton btnNewButton_3 = new JButton("\u03C0");
		btnNewButton_3.setBounds(119, 88, 66, 23);
		btnNewButton_3.addActionListener(actionListener(_controller, "pi"));
		getContentPane().add(btnNewButton_3);
		_screen.setBackground(new Color(255, 255, 255));
		
		_screen.setHorizontalAlignment(SwingConstants.RIGHT);
		_screen.setEditable(false);
		_screen.setBounds(117, 11, 307, 32);
		_screen.setColumns(10);
		getContentPane().add(_screen);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Calculator");
		setResizable(false);
		setSize(height, width);
		setVisible(true);
	}

	/**
	 * Update the display screen when an Observed object needs it
	 * @param obj: Observed object
	 * @param display: value to display on the Calculator screen
	 */
	@Override
	public void update(Observable obj, Object display) {
		if (obj instanceof Calculator)
			_screen.setText((String) display);
	}
}
