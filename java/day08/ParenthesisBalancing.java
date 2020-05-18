import java.util.Stack;

public class ParenthesisBalancing {
    public static boolean checkParenthesis(String in) {
        String opening = "([{<", closing = ")]}>";
        Stack<Character> s = new Stack<>();
        int i, open_index, close_index;
        //1. proces each sym from left to right
        for (i = 0; i<in.length(); i++) {
            char sym = in.charAt(i);
            //2. if it is opening par then push on stack
            open_index = opening.indexOf(sym);
            if (open_index >= 0)
                s.push(sym);
            else {
                //3. if it is closing par then check if it corresponds to topmost par on stack
                close_index = closing.indexOf(sym);
                if (close_index >= 0) {
                    if (s.empty()) // extra closing paranthesis
                        return false;
                    open_index = opening.indexOf(s.peek());
                    if (open_index == close_index)
                        s.pop();
                    else
                        return false; // mismaptch of parenthesis
                }
            }
        }
        //4. if still some par on stack, invalid expr
        if (!s.empty()) // extra opening paranthesis
            return false;
        return true;
    }

    public static void main(String[] args) {
        String expr = "5 + ( [ 9 - 4 ] * ( 8 - { 6 / 2 } ) )";
        boolean success = checkParenthesis(expr);
        if (success)
            System.out.println("Valid expression!");
        else
            System.out.println("Invalid expression!");
    }   
}