
import java.util.Stack;
import java.util.*;

class EvaluateString {
	public static int evaluate(String expression) {
		char[] tokens = expression.toCharArray();

		Stack<Integer> values = new Stack<Integer>();

		Stack<Character> ops = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++) {

			if (tokens[i] == ' ')
				continue;

			if (tokens[i] >= '0' &&
					tokens[i] <= '9') {
				StringBuffer sbuf = new StringBuffer();

				while (i < tokens.length &&
						tokens[i] >= '0' &&
						tokens[i] <= '9')
					sbuf.append(tokens[i++]);
				values.push(Integer.parseInt(sbuf.toString()));

				i--;
			}

			// Current token is an opening bracket,
			// push it to Stack Ops
			else if (tokens[i] == '(')
				ops.push(tokens[i]);

			// Closing bracket is found ,
			// solve entire expression in present in brackets
			else if (tokens[i] == ')') {
				while (ops.peek() != '(')
					values.push(applyOp(ops.pop(),
							values.pop(),
							values.pop()));
				ops.pop();
			}

			// Current token is an operator.
			else if (tokens[i] == '+' ||
					tokens[i] == '-' ||
					tokens[i] == '*' ||
					tokens[i] == '/') {
				// While top of Stack Ops has same
				// or greater precedence to current
				// token, which is an operator.
				// Apply operator on top of Ops
				// to top two elements in values stack
				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));

				// Push current token to Ops
				ops.push(tokens[i]);
			}
		}

		// Entire expression has been
		// parsed at this point, apply remaining
		// ops to remaining values
		while (!ops.empty())
			values.push(applyOp(ops.pop(),
					values.pop(),
					values.pop()));

		// Top of Stack Vakues contains
		// result, return it
		return values.pop();
	}

	// Returns true if 'op2' has higher
	// or same precedence as 'op1',
	// otherwise returns false.
	public static boolean hasPrecedence(
			char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') &&
				(op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	// A utility method to apply an
	// operator 'op' on operands 'a'
	// and 'b'. Return the result.
	public static int applyOp(char op,
			int b, int a) {
		switch (op) {
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				try {
					if (b == 0)
						System.out.println("second operand found zero");
				} catch (ArithmeticException e) {

					System.out.println("Can't divide by zero");
					return 0;
				}
				return a / b;

		}
		return 0;
	}

	// Main method to test above methods
	public static void main(String[] args) {
		System.out.println(EvaluateString.evaluate("10 + 2 * 6"));
		System.out.println(EvaluateString.evaluate("(100 * 2) + (12 + 4)"));
		System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 )"));
		System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 ) / 14"));
		Scanner sin = new Scanner(System.in);
		System.out.println("Enter your expression");
		String exp = sin.nextLine();
		System.out.println(EvaluateString.evaluate(exp));
	}
}
