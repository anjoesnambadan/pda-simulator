import java.util.*;

public class PDASim
{
    public static char stack[];
    public static int top, state;
    
    PDASim() // constructor to initialize the member variables
    {
        stack = new char[100];
        top = -1;
        state = 0;
        push('0');
    }
    
    void push(char c)// push an element into the stack
    {
        stack[++top] = c;
    }
    
    char pop()// pop an element from the stack
    {
        return stack[top--];
    }
    
    void printStack()// print the stack for the instantaneous notation
    {
        for(int i = top; i >= 0; i--)
        {
            System.out.print(stack[i]);
        }
        System.out.print(")|- ");
    }
    
    boolean sim(String str, PDASim p)
    {
        for(int i = 0; i < str.length(); i++)// reads each character one by one
        {
            System.out.print("(q" + state + "," + str.substring(i, str.length()) + ",");// print instantaneous notation
            p.printStack();
            
            char c = str.charAt(i);
            if(c == 'a' || c == 'b') // check if characters are a or b
            {
                if(state == 0)// push if in state q0
                {
                    p.push(c);
                }
                else // else if in state q1
                {
                    if(stack[top] == c)// pop if stack top is same as element
                    {
                       p.pop(); 
                    }
                    else // string does not belong to language
                    {
                        System.out.println();
                        return false;
                    }
                }
            }
            if(c == 'c')// switch state if value is c
            {
                state = 1;
            }
        }
        p.pop();
        System.out.println("(q1,ε,0)|- (q0,ε,ε)");
        
        if(top == -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static void main(String args[])
    {
        PDASim p = new PDASim();
        Scanner kb = new Scanner(System.in);
        String str;
        System.out.print("Enter the string: ");// accept the string
        str = kb.nextLine();
        if(p.sim(str, p))
        {
            System.out.println("The given string belongs to the language");
        }
        else
        {
            System.out.println("The given string does not belongs to the language");
        }
    }
}
