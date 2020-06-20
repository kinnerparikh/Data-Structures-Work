package Unit_3_Labs_Stacks_Queues;

import java.util.Stack;

public class ExpressionEvaluator {

    public static int eval(String expression) {
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<String> opStack = new Stack<String>();

        String[] expArray = expression.split(" ");

        for (int i = 0; i < expArray.length; i++) 
        {
            try 
            {
                Integer characterToAdd = Integer.parseInt(expArray[i]);
                numStack.push(characterToAdd);
            } 
            catch (Exception e)
            {
                if (expArray[i].equals("(")) opStack.push(expArray[i]);
                else if (expArray[i].equals(")")) 
                {
                    while (!opStack.peek().equals("("))
                    {
                        String operator = opStack.pop();
                        Integer num2 = numStack.pop();
                        Integer num1 = numStack.pop();
                        switch(operator)
                        {
                            case "+": numStack.push(num1 + num2); break;
                            case "-": numStack.push(num1 - num2); break;
                            case "*": numStack.push(num1 * num2); break;
                            case "/": 
                                if (num2 == 0) throw new UnsupportedOperationException();
                                numStack.push(num1 / num2);
                                break;
                            default: throw new IllegalStateException("not a valid operator");
                        }
                    }
                    opStack.pop();
                } 
                else 
                {
                    if (expArray[i].equals("+") || expArray[i].equals("-")) 
                    {
                        while (!opStack.empty() && !opStack.peek().equals("(")) 
                        {
                            String operator = opStack.pop();
                            Integer num2 = numStack.pop();
                            Integer num1 = numStack.pop();
                            switch(operator)
                            {
                                case "+": numStack.push(num1 + num2); break;
                                case "-": numStack.push(num1 - num2); break;
                                case "*": numStack.push(num1 * num2); break;
                                case "/": 
                                    if (num2 == 0) throw new UnsupportedOperationException();
                                    numStack.push(num1 / num2);
                                    break;
                                default: break;
                            }
                        }
                        opStack.push(expArray[i]);
                    } 
                    else if (expArray[i].equals("*") || expArray[i].equals("/"))
                    {
                        // ew
                        while (!opStack.empty() && !opStack.peek().equals("(") && !opStack.peek().equals("+") && !opStack.peek().equals("-")) 
                        {
                            String operator = opStack.pop();
                            Integer num2 = numStack.pop();
                            Integer num1 = numStack.pop();
                            switch(operator)
                            {
                                case "+": numStack.push(num1 + num2); break;
                                case "-": numStack.push(num1 - num2); break;
                                case "*": numStack.push(num1 * num2); break;
                                case "/": 
                                    if (num2 == 0) throw new UnsupportedOperationException();
                                    numStack.push(num1 / num2);
                                    break;
                                default: break;
                            }
                        }
                        opStack.push(expArray[i]);
                    }
                }
            }
        }

        while (!opStack.empty()) 
        {
            String operator = opStack.pop();
            Integer num2 = numStack.pop();
            Integer num1 = numStack.pop();
            switch(operator)
            {
                case "+": numStack.push(num1 + num2); break;
                case "-": numStack.push(num1 - num2); break;
                case "*": numStack.push(num1 * num2); break;
                case "/": 
                    if (num2 == 0) throw new UnsupportedOperationException();
                    numStack.push(num1 / num2);
                    break;
                default: break;
            }
        }

        return numStack.pop();
    }
}