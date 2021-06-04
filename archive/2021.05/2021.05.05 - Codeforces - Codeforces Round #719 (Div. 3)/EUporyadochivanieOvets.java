package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EUporyadochivanieOvets {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                a.add(i);
            }
        }
        int t1 = 0;
        int t2 = n - a.size();
        while (t1 < t2) {
            int m1, m2;
            if (t1 + 1 == t2) {
                m1 = t1;
                m2 = t2;
            }
            int m = (t2 - t1) / 3;


        }
    }

}
