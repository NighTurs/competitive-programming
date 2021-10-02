package task;

import java.io.PrintWriter;
import java.math.BigInteger;

public class A {
    BigInteger doit(String s) {
        int cur = 0;
        StringBuilder sb = new StringBuilder();
        while (cur < s.length()) {
            if (s.charAt(cur) == 'o') {
                sb.append(1);
                cur += 3;
            } else {
                sb.append(0);
                cur += 4;
            }
        }
        return new BigInteger(sb.toString(), 2);
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        BigInteger a = doit(in.next());
        BigInteger b = doit(in.next());
        int cmp = a.compareTo(b);
        if (cmp == 0) {
            out.println("=");
        } else if (cmp > 0) {
            out.println(">");
        } else {
            out.println("<");
        }
    }
}
