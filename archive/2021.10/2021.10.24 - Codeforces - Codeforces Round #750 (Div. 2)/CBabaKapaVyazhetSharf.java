package task;

import java.io.PrintWriter;

public class CBabaKapaVyazhetSharf {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        int min = Integer.MAX_VALUE;
        label:
        for (char i = 'a'; i <= 'z'; i++) {
            int st = 0;
            int ed = s.length() - 1;
            while (true) {
                while (s.charAt(st) == i && st < ed) {
                    st++;
                }
                while (s.charAt(ed) == i && st < ed) {
                    ed--;
                }
                if (s.charAt(st) != s.charAt(ed)) {
                    continue label;
                }
                st++;
                ed--;
                if (st >= ed) {
                    break;
                }
            }

            st = 0;
            ed = s.length() - 1;
            int ct = 0;
            while (true) {
                if (s.charAt(st) != s.charAt(ed)) {
                    while (s.charAt(st) == i && st < ed) {
                        st++;
                        ct++;
                    }
                    while (s.charAt(ed) == i && st < ed) {
                        ed--;
                        ct++;
                    }
                    if (s.charAt(st) != s.charAt(ed)) {
                        throw new RuntimeException("wtf");
                    }
                }
                st++;
                ed--;
                if (st >= ed) {
                    break;
                }
            }
            min = Math.min(min, ct);
        }

        if (min == Integer.MAX_VALUE) {
            out.println(-1);
        } else {
            out.println(min);
        }
    }
}
