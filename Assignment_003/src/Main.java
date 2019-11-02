public class Main {
  public static void main(String[] args) {
    Queue<String> test_0 = new Queue<String>();
    Queue<String> test_1 = new Queue<String>();
    Queue<String> test_2 = new Queue<String>();
    Queue<String> test_3 = new Queue<String>();
    Queue<String> test_4 = new Queue<String>();
    Queue<String> test_5 = new Queue<String>();

    test_0.enqueue("1", "+", "2");
    test_1.enqueue("7", "/", "13", "+", "9");
    test_2.enqueue("(", "9", "-", "2", ")", "^", "3");
    test_3.enqueue("4", "-", "3", "^", "7", "+", "1");
    test_4.enqueue("2", "^", "(", "5", "+", "2", ")", "-", "1");
    test_5.enqueue("4", "*", "3", "*", "2", "*", "1");

    System.out.print("1 + 2 = ");
    System.out.println(evaluate(toPostfix(test_0)));
    
    System.out.print("7 / 13 + 9 = ");
    System.out.println(evaluate(toPostfix(test_1)));

    System.out.print("(9 - 2)^3 = ");
    System.out.println(evaluate(toPostfix(test_2)));

    System.out.print("4 - 3^7 + 1 = ");
    System.out.println(evaluate(toPostfix(test_3)));

    System.out.print("2^(5 + 2) - 1 = ");
    System.out.println(evaluate(toPostfix(test_4)));

    System.out.print("4 * 3 * 2 * 1 = ");
    System.out.println(evaluate(toPostfix(test_5)));
  }

  
  // Converts a queue provided in infix form into a
  // queue in postfix form.
  private static Queue<String> toPostfix(Queue<String> infix) {
    Queue<String> postfixQueue = new Queue<String>();
    Stack<String> postfixStack = new Stack<String>();

    for (String token = infix.dequeue(); token != null; token = infix.dequeue()) {
      if (isNumeric(token)) {
        postfixQueue.enqueue(token);
      }

      else if (token.equals("(")) {
        postfixStack.push(token);
      }

      else if (token.equals(")")) {
        while (postfixStack.peek() != null && postfixStack.peek() != "(") {
          postfixQueue.enqueue(postfixStack.pop());
        }

        if (postfixStack.peek() != null && postfixStack.peek() != "(") {
          throw new Error("Unmached parenthisis");
        }
        else {
          postfixStack.pop();
        }
      }

      else {
        while (postfixStack.peek() != null && precedence(token) <= precedence(postfixStack.peek())){ 
          if(postfixStack.peek() == "(") {
            throw new Error("Unmached parenthisis");
          }
          postfixQueue.enqueue(postfixStack.pop());
        } 
        postfixStack.push(token);
      }
    }

    while (postfixStack.peek() != null) {
      if (postfixStack.peek() == "(") {
        throw new Error("Unmached parenthisis");
      }
      else {
        postfixQueue.enqueue(postfixStack.pop());
      }
    }

    return postfixQueue;
  }


  // Evaluate an expression provided in the form of
  // a well-formed postix queue.
  private static double evaluate(Queue<String> postfix) {
    Stack<Double> operandStack = new Stack<Double>();

    for (String token = postfix.dequeue(); token != null; token = postfix.dequeue()) {
      if (isNumeric(token)) {
        operandStack.push(Double.parseDouble(token));
      }
      else {
        double rhs = operandStack.pop();
        double lhs = operandStack.pop();

        if (token.equals("+")) operandStack.push(lhs + rhs);
        if (token.equals("-")) operandStack.push(lhs - rhs);
        if (token.equals("*")) operandStack.push(lhs * rhs);
        if (token.equals("/")) operandStack.push(lhs / rhs);
        if (token.equals("^")) operandStack.push(Math.pow(lhs, rhs));
      }
    }

    return operandStack.pop();
  }


  // Defines the precedence of every valid operator.
  // Returns -1 of the operator is not implemented.
  private static int precedence(String operator) {
    if (operator.equals("+")) return 0;
    if (operator.equals("-")) return 0;
    if (operator.equals("*")) return 1;
    if (operator.equals("/")) return 1;
    if (operator.equals("^")) return 2;
    return -1;
  }


  // Tests to determine wheather a given sting is
  // numeric or not.
  private static boolean isNumeric(String string) {
    try {
      Double.parseDouble(string);
      return true;
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
}