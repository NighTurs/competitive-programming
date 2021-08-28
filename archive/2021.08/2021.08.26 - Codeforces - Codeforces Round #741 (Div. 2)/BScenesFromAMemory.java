package task;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class BScenesFromAMemory {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        Set<Integer> singl = new HashSet<>();
        Set<Integer> dbl = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - '0';
            if (val != 2 && val != 3 && val != 5 && val != 7) {
                out.println(1);
                out.println(val);
                return;
            }
            for (Integer h : singl) {
                dbl.add(h * 10 + val);
            }

            singl.add(val);
        }
        for (Integer v : dbl) {
            if (v != 37 && v != 53 && v != 73 && v != 23) {
                out.println(2);
                out.println(v);
                return;
            }
        }

    }
}
