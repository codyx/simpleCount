package simpleCount;
import java.util.Stack;

/*
 * Reverse Polish Notation(RPN) class
 * Handle expressions
 * @author Aimeric Seguin
 * @version 1.0
 * @see Calculator
 */
public class RPN {
	
	/**
	 *	Left associativity matcher
	 * @param ch: a character
	 * @return	(true) if the character is left associated or (false) else
	 */
	private boolean	isLeftAssociated(final char ch) {
		return (ch == '%' || ch == '*' || ch == '/' || ch == '+' || ch == '-');
	}
	
	/**
	 *	Right associativity matcher
	 * @param op: a character
	 * @return	(true) if the character is right associated or (false) else
	 */
	private boolean	isRightAssociated(final char op) {
		return (op == '^');
	}

	/**
	 *	Precedence getter
	 * @param op: the precedence of the operator
	 * @return the precedence of the operator
	 */
	private int		getPrecedence(final char op) {
		if (op != '^' && op != '*' && op != '/'
			&& op != '%' && op != '+' && op != '-')
			throw new RuntimeException("Invalid operator");
		if (op == '^')
			return 4;
		else if (op == '*' || op == '/' || op == '%')
			return 3;
		else if (op == '+' || op == '-')
			return 2;
		return -1;
	}
	
	/**
	*	Operator matcher
	*	@param ch: a character
	*	@return	(true) if the character is an operator or (false) else
	 */
	private boolean	isOperator(final char ch) {
		return (ch == '%' || ch == '*' || ch == '/' || ch == '+' || ch == '-' || ch == '^');
	}

	/**
	*	Convert from infix notation to postfix notation
	*	@param ipt: an infix noted string
	*	@return	infix noted string converted into a postfix noted string
	 */
	public String	toRPN(final String ipt) {
		String[]		tokens = ipt.split(String.valueOf(" "));
		String			opt = new String();
		Stack<String>	operators = new Stack<String>();
		boolean 		validP = false;

		for (int idx = 0; idx < tokens.length; ++idx)
		{
			String token = tokens[idx];
			if (token.matches("(\\+|-)?\\d+(\\.\\d+)?"))
			{
				opt += token + " ";
			}
				
			else if (token.matches("[\\+|\\-|\\*|\\/|\\%]")) {
				while (!operators.isEmpty() && isOperator(operators.peek().charAt(0)))
				{
					char o1 = token.charAt(0);
					char o2 = operators.peek().charAt(0);
					if ((isLeftAssociated(o1)&& getPrecedence(o1) <= getPrecedence(o2))
						|| (isRightAssociated(o1) && getPrecedence(o1) < getPrecedence(o2))) {
						operators.pop();
						opt += o2 + " ";
					}
					else break;
				}
				operators.push(token);
			}
			else if (token.matches("\\("))
				operators.push(token);
			else if (token.matches("\\)")) {
				while (!operators.isEmpty()) {
					if (operators.peek().charAt(0) == '(') {
						validP = true;
						operators.pop();
						break;
					}
					opt += operators.pop() + " ";
				}
				if (!validP)
					throw new RuntimeException("Mismatched parenthesis.");
				validP = false;
			}
			//else
				//throw new RuntimeException("Error.");
		}
		while (!operators.isEmpty()) {
			if (operators.peek().equals("(") || operators.peek().equals(")"))
				throw new RuntimeException("Mismatched parenthesis.");
			opt += operators.pop() + " ";	
		}
		return (opt.length() > 1) ? opt.substring(0, opt.length() - 1) : opt;
	}
	
	/**
	*	Compute two operands despite their operator
	*	@param n1: a decimal number
	*	@param n2: "
	*	@param op: an operator
	*	@return the result of the calculus
	 */
	public double	compute(final Double n1, final Double n2, final char op)
	{
		if (n2 == 0 && (op == '/' || op == '%'))
			throw new RuntimeException("We can't divide or modulo by zero.");
		if (op == '*')
			return n1 * n2;
		else if (op == '/')
			return n1 / n2;
		else if (op == '%')
			return n1 % n2;
		else if (op == '+')
			return n1 + n2;
		else if (op == '-')
			return n1 - n2;
		return 0;
	}
	
	/**
	*	Evaluate a postfix expression
	*	@param rpnExpr: a reverse polished noted string
	*	@return	the result of the evaluation as string
	 */
	public String	evaluateRPN(final String rpnExpr) {
	      String[]		tokens = rpnExpr.split(String.valueOf(" "));
	      Stack<String>	stack = new Stack<String>();
	      
	      for (int idx = 0; idx < tokens.length; ++idx) {
	    	  String token = tokens[idx];
	    	  if (token.matches("(\\+|-)?\\d+(\\.\\d+)?"))
	    		  stack.push(token);
	    	  else if (isOperator(token.charAt(0))) {
	    		  if (stack.size() < 2)
					throw new RuntimeException("Error.");
	    		  double n2 = Double.parseDouble(stack.pop());
	    		  double n1 = Double.parseDouble(stack.pop());
	    		  stack.push(String.valueOf(compute(n1, n2, token.charAt(0))));
	    	  }
	    	  else
	    		  throw new RuntimeException("Error.");
	      }
	      if (stack.size() != 1)
	    	  throw new RuntimeException("Input has too many values.");
	      return stack.pop();
	}
	RPN() {}
}
