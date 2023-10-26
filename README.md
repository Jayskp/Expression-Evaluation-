# Expression-Evaluation-
Expression evaluation has been done by using two stacks 
Expression evaluation using two stacks is a common method to compute the value of mathematical expressions, including infix expressions (e.g., 2 + 3) and postfix expressions (e.g., 2 3 +). Here's a brief overview of how it works:

Create two stacks: one for operands and another for operators.
Scan the expression from left to right.
For each symbol:
If it's an operand, push it onto the operand stack.
If it's an operator, push it onto the operator stack after handling operator precedence and associativity.
If it's an open parenthesis '(', push it onto the operator stack.
If it's a close parenthesis ')', pop operators from the operator stack and operands from the operand stack and apply the operators to the operands until an open parenthesis is encountered.
After processing the entire expression, there should be only one value left in the operand stack, which is the result of the expression.
Here's an example of evaluating the infix expression "2 + 3 * (4 - 1)":
