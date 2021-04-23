
import java.util.Stack;
import java.util.*;

class Node {
	char value;
	Node left, right;
	Node(char item) {
		value = item;
		left = right = null;
	}
}

public class ExpressionTree {

	static boolean isOperator(char c) {
		if (c == '+' || c == '-'|| c == '*' || c == '/'|| c == '^') {
			return true;
		}
		return false;
	}
	static int Prec(char ch)
	{
		switch (ch)
		{
		case '+': 
		case '-':
			return 1;
	
		case '*':
		case '/':
			return 2;
	
		case '^':
			return 3;
		}
		return -1;
	}
	void inorder(Node t) {
		if (t != null) {
			inorder(t.left);
			System.out.print(t.value + " ");
			inorder(t.right);
		}
	}
	Node constructTree(char postfix[]) {
		Stack<Node> st = new Stack<Node>();
		Node t, t1, t2;
		for (int i = 0; i < postfix.length; i++) {

			if (!isOperator(postfix[i])) {
				t = new Node(postfix[i]);
				st.push(t);
			} else 
			{
				t = new Node(postfix[i]);
				t1 = st.pop();	 
				t2 = st.pop();

				t.right = t1;
				t.left = t2;
				st.push(t);
			}
		}
		t = st.peek();
		st.pop();
		return t;
	}
/* converting  infix expression to postfix*/
	
	static String infixToPostfix(String exp)
	{
		String result = new String("");
		
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i<exp.length(); ++i)
		{
			char c = exp.charAt(i);
			if (Character.isLetterOrDigit(c))
				result += c;
			else if (c == '(')
				stack.push(c);
			else if (c == ')')
			{
				while (!stack.isEmpty() &&
						stack.peek() != '(')
					result += stack.pop();
					stack.pop();
			}
			else 
			{
				while (!stack.isEmpty() && Prec(c)<= Prec(stack.peek())){
					
					result += stack.pop();
			}
				stack.push(c);
			}
	
		} 
		while (!stack.isEmpty()){
			if(stack.peek() == '(')
				return "Invalid Expression";
			result += stack.pop();
		}
		return result;
	} 
//..............

// Convert prefix to Infix expression
public static String convert(String str)
{
    Stack<String> stack = new Stack<>();
     int l = str.length();
    for(int i = l - 1; i >= 0; i--)
    {
        char c = str.charAt(i);
        if (isOperator(c))
        {
            String op1 = stack.pop();
            String op2 = stack.pop();
            String temp = "(" + op1 + c + op2 + ")";
            stack.push(temp);
        }
        else
        {    stack.push(c + ""); }
    }
    return stack.pop();
}

//..............

// Function that converts infix
// expression to prefix expression.
static String infixToPrefix(String infix)
{
    // stack for operators.
    Stack<Character> operators = new Stack<Character>();
 
    // stack for operands.
    Stack<String> operands = new Stack<String>();
 
    for (int i = 0; i < infix.length(); i++)
    {
 
        // If current character is an
        // opening bracket, then
        // push into the operators stack.
        if (infix.charAt(i) == '(')
        {
            operators.push(infix.charAt(i));
        }
 
        // If current character is a
        // closing bracket, then pop from
        // both stacks and push result
        // in operands stack until
        // matching opening bracket is
        // not found.
        else if (infix.charAt(i) == ')')
        {
            while (!operators.empty() &&
                operators.peek() != '(')
                {
 
                // operand 1
                String op1 = operands.peek();
                operands.pop();
 
                // operand 2
                String op2 = operands.peek();
                operands.pop();
 
                // operator
                char op = operators.peek();
                operators.pop();
 
                // Add operands and operator
                // in form operator +
                // operand1 + operand2.
                String tmp = op + op2 + op1;
                operands.push(tmp);
            }
 
            // Pop opening bracket
            // from stack.
            operators.pop();
        }
 
        // If current character is an
        // operand then push it into
        // operands stack.
        else if (!isOperator(infix.charAt(i)))
        {
            operands.push(infix.charAt(i) + "");
        }
 
        // If current character is an
        // operator, then push it into
        // operators stack after popping
        // high priority operators from
        // operators stack and pushing
        // result in operands stack.
        else
        {
            while (!operators.empty() &&
                Prec(infix.charAt(i)) <=
                    Prec(operators.peek()))
                {
 
                String op1 = operands.peek();
                operands.pop();
 
                String op2 = operands.peek();
                operands.pop();
 
                char op = operators.peek();
                operators.pop();
 
                String tmp = op + op2 + op1;
                operands.push(tmp);
            }
 
            operators.push(infix.charAt(i));
        }
    }
 
    // Pop operators from operators
    // stack until it is empty and
    // operation in add result of
    // each pop operands stack.
    while (!operators.empty())
    {
        String op1 = operands.peek();
        operands.pop();
 
        String op2 = operands.peek();
        operands.pop();
 
        char op = operators.peek();
        operators.pop();
 
        String tmp = op + op2 + op1;
        operands.push(tmp);
    }
 
    // Final prefix expression is
    // present in operands stack.
    return operands.peek();
}
//..............

static void printing()
	{

		ExpressionTree et = new ExpressionTree();
		System.out.println("------------W E L C O M E------------\n");
		System.out.println("Type A >>>>> convert Infix to postfix ");
		System.out.println("Type B >>>>> convert Postfix to Infix ");
		System.out.println("Type C >>>>> convert Prefix to Infix ");
		System.out.println("Type D >>>>> convert Infix to Prefix ");
		Scanner input1 = new Scanner (System.in);
		System.out.println("\nEnter your choice:   ");
		char ch = input1.next().charAt(0);

switch(ch)
{
	case 'A':
	case 'a': {
				Scanner input2 = new Scanner (System.in);
				System.out.print("Enter Infix:    ");
				String infix = input2.nextLine();
		
				System.out.println();
		
				System.out.print("Postfix expression is:    ");
				System.out.print(infixToPostfix(infix));
			
				System.out.println("\n \n ");
		
				printing();

			} 	break;

	case 'B':
	case 'b': {
				//String postfix = "ab+ef*g*-";
				Scanner input = new Scanner (System.in);
				System.out.print("Enter postfix:    ");
				String postfix = input.nextLine();
			
				System.out.println();
		
				char[] charArray = postfix.toCharArray();
				Node root = et.constructTree(charArray);
				System.out.print("infix expression is:    ");
				et.inorder(root);
		
				System.out.println("\n \n ");
		
				printing();
			}	break;

	case 'C':
	case 'c': {
		//String postfix = "ab+ef*g*-";
				Scanner inp = new Scanner (System.in);
				System.out.print("Enter prefix:    ");
				String exp = inp.nextLine();
			
				 System.out.println("Infix expression is: " + convert(exp));

			System.out.println("\n \n ");
				System.out.println();

				printing();
			}	break;
	case 'D':
	case 'd': {
				Scanner inputt = new Scanner (System.in);
				System.out.print("Enter Infix:    ");
				String h = inputt.nextLine();

				System.out.println("Prefix expression is:  " + infixToPrefix(h));

			System.out.println("\n \n ");
				System.out.println();

				printing();
			}	break;
	} //end of switch
} //end of printing method

	public static void main(String args[]) {

		printing();
											} //end of main method
 } //end of class 

