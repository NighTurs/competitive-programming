package task;

import java.io.PrintWriter;

public class AAzartnieStavki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        if (a == b) {
            out.println("0 0");
            return;
        }
        long gcd = Math.abs(a - b);
        long step = Math.min(a % gcd, gcd - (a % gcd));
        out.println(gcd + " " + step);
    }
}



//    public void solve(int testNumber, InputReader in, PrintWriter out) {
//
//    }