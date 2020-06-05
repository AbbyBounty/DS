
public class DynamicFibonacci {
    private static int cntr;

    public static int fibRec(int n) {
        cntr++;
        if(n==1 || n==2)
            return 1;
        int tn_1 = fibRec(n-1);
        int tn_2 = fibRec(n-2);
        return tn_1 + tn_2;
    }

    public static int fibRecMemoized(int n, int terms[]) {
        cntr++;
        if(terms[n] != 0)
            return terms[n];
        return terms[n] = fibRecMemoized(n-1, terms) + fibRecMemoized(n-2, terms);
    }

    public static int fibRecMemoized(int n) {
        int terms[] = new int[n+1];
        terms[1] = terms[2] = 1;
        return fibRecMemoized(n, terms);
    }

    public static int fibDynamic(int n, int terms[]) {
        for(int i=3; i<=n; i++)
            terms[i] = terms[i-1] + terms[i-2];
        return terms[n];    
    }

    public static int fibDynamic(int n) {
        int terms[] = new int[n+1];
        terms[1] = terms[2] = 1;
        return fibDynamic(n, terms);
    }

    public static void main(String[] args) {
        int res, n = 40;
        long start, end, diff;
        start = System.currentTimeMillis();
        cntr = 0;
        res = fibRec(n);
        System.out.println("Recursive Result : " + res + " with Number of recursive calls : " + cntr);
        end = System.currentTimeMillis();
        diff = end - start;
        System.out.println("Execution time : " + diff + " ms");

        start = System.currentTimeMillis();
        cntr = 0;
        res = fibRecMemoized(n);
        System.out.println("Recursive Memoized Result : " + res + " with Number of recursive calls : " + cntr);
        end = System.currentTimeMillis();
        diff = end - start;
        System.out.println("Execution time : " + diff + " ms");

        start = System.currentTimeMillis();
        res = fibDynamic(n);
        System.out.println("Dynamic Result : " + res);
        end = System.currentTimeMillis();
        diff = end - start;
        System.out.println("Execution time : " + diff + " ms");
    }    
}


