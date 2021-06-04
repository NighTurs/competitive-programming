package task;

import java.io.PrintWriter;

public class Matrygons {
    int N = 1000 * 1000 + 10;

    int[] mem = new int[N];

    public int first(int n) {
        if (n == 0) {
            return 0;
        }
        int mmax = Integer.MIN_VALUE;
        for (int i = 3; i <= (int) Math.sqrt(n) + 1; i++) {
            if (n % i != 0) {
                continue;
            }
            int rest = n / i - 1;
            int d = doit(rest);
            if (d > mmax) {
                mmax = d;
            }
            d = doit(n / (rest + 1) - 1);
            if (d > mmax) {
                mmax = d;
            }
        }
        if (mmax == Integer.MIN_VALUE) {
            return 1;
        } else {
            return mmax + 1;
        }
    }

    public int doit(int n) {
        if (n <= 1) {
            return 0;
        }
        if (mem[n] != 0) {
            return mem[n];
        }
        int mmax = Integer.MIN_VALUE;
        for (int i = 2; i <= (int) Math.sqrt(n) + 1; i++) {
            if (n % i != 0) {
                continue;
            }
            int rest = n / i - 1;
            int d = doit(rest);
            if (d > mmax) {
                mmax = d;
            }
            d = doit(n / (rest + 1) - 1);
            if (d > mmax) {
                mmax = d;
            }
        }
        if (mmax == Integer.MIN_VALUE) {
            mem[n] = 1;
        } else {
            mem[n] = mmax + 1;
        }
        return mem[n];
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        mem[2] = 1;
        int n = in.nextInt();
        out.println(String.format("Case #%d: %d", testNumber, first(n)));
//        for (int i = 0; i < 200; i++) {
//            out.println(i + " " + mem[i]);
//        }
    }
}
