import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

class ExpressionConversion {
    public static int pri(char op) {
        switch (op)
        {
        case '$':	return 10;
        case '*':	return 7;
        case '/':	return 7;
        case '+':	return 2;
        case '-':	return 2;
        }
        return 0;
    }
    
    public static int calc(int a, int b, char op) {
        switch (op)
        {
        case '$':	return (int)Math.pow(a, b);
        case '*':	return a * b;
        case '/':	return a / b;
        case '+':	return a + b;
        case '-':	return a - b;
        }
        return 0;
    }
    
    public static String infix_to_postfix(String infix) {
        int i, k = 0;
        char in[] = infix.toCharArray();
        char post[] = new char[in.length]; 
        //0. create stack of operator
        Stack<Character> s = new Stack<>();
        //1. process each sym in infix from left to right
        for (i = 0; i < in.length; i++) {
            //2. if sym in operand, append to postfix string
            if (Character.isDigit(in[i]))
                post[k++] = in[i];
            //4. if opening '(', push on stack.
            else if (in[i] == '(')
                s.push(in[i]);
            //5. if closing ')', pop all ops from stack and append to postfix until '(' is found on stack. Also pop & discard '(' from stack.
            else if (in[i] == ')') {
                while (s.peek() != '(') {
                    post[k++] = s.pop();
                }
                s.pop();
            }
            //3. if sym is operator, push on the stack.
            else { // operator
                //3a. can push current op only if its priority is higher than topmost op in stack.
                //3b. if priority of topmost op is greater or equal, pop it and append to postfix.
                while (!s.empty() && pri(s.peek()) >= pri(in[i])) {
                    post[k++] = s.pop();
                }
                s.push(in[i]);
            }
        }
        //6. at the end pop all ops from stack and append to postfix.
        while (!s.empty()) {
            post[k++] = s.pop();
        }
        post[k] = '\0';
        return new String(post, 0, k);
    }
    
    public static String infix_to_prefix(String infix) {
        int i, k = 0;
        char in[] = infix.toCharArray();
        char pre[] = new char[in.length];
        //0. create stack of operator
        Stack<Character> s = new Stack<>();
        //1. process each sym in infix from right to left
        for (i = in.length-1; i>=0; i--) {
            //2. if sym in operand, append to prefix string
            if (Character.isDigit(in[i]))
                pre[k++] = in[i];
            //4. if closing ')', push on stack.
            else if (in[i] == ')')
                s.push(in[i]);
            //5. if opening '(', pop all ops from stack and append to prefix until ')' is found on stack. Also pop & discard ')' from stack.
            else if (in[i] == '(') {
                while (s.peek() != ')') {
                    pre[k++] = s.pop();
                }
                s.pop();
            }
            //3. if sym is operator, push on the stack.
            else { // operator
                //3a. can push current op only if its priority is higher or equal than topmost op in stack.
                //3b. if priority of topmost op is greater, pop it and append to prefix.
                while (!s.empty() && pri(s.peek()) > pri(in[i])) {
                    pre[k++] = s.pop();
                }
                s.push(in[i]);
            }
        }
        //6. at the end pop all ops from stack and append to prefix.
        while (!s.empty()) {
            pre[k++] = s.pop();
        }
        pre[k] = '\0';
        //7. reverse the prefix string
        return new StringBuilder(new String(pre, 0, k)).reverse().toString();
    }
    
    public static int postfix_eval(String postfix) {
        char post[] = postfix.toCharArray();
        //0. create stack of operands
        Stack<Integer> s = new Stack<>();
        //1. process sym in postfix from left to right
        for (int i = 0; i < post.length; i++) {
            //2. if operand is found, push on stack
            if (Character.isDigit(post[i]))
                s.push(post[i] - '0'); // '5' != 5
            //3. if operator is found, pop two values from stack, calc result and push it on stack
            else {
                int b = s.pop();
                int a = s.pop();
                int r = calc(a, b, post[i]);
                s.push(r);
            }
        }
        //4. at the end, pop last value from stack & return (result)
        int r = s.pop();
        return r;
    }

    public static int prefix_eval(String prefix) {
        char pre[] = prefix.toCharArray();
        //0. create stack of operands
        Stack<Integer> s = new Stack<>();
        //1. process sym in prefix from right to left
        for (int i = pre.length-1; i >= 0; i--) {
            //2. if operand is found, push on stack
            if (Character.isDigit(pre[i]))
                s.push(pre[i] - '0'); // '5' != 5
            //3. if operator is found, pop two values from stack, calc result and push it on stack
            else {
                int a = s.pop();
                int b = s.pop();
                int r = calc(a, b, pre[i]);
                s.push(r);
            }
        }
        //4. at the end, pop last value from stack & return (result)
        int r = s.pop();
        return r;
    }
    
    public static void main(String[] args) {
        String infix = "5+9-4*(8-6/2)+1$(7-3)";
        String postfix = "", prefix = "";
        int res;
        System.out.println("infix : " + infix);
        postfix = infix_to_postfix(infix);
        System.out.println("postfix : " + postfix);
        res = postfix_eval(postfix);
        System.out.println("Postfix Result: " + res);
        prefix = infix_to_prefix(infix);
        System.out.println("prefix : " + prefix);
        res = prefix_eval(prefix);
        System.out.println("Prefix Result: " + res);
    }
}
