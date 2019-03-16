package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BCirk {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Pair<Integer, Integer>> arr = new ArrayList<>();
        String s = in.next();
        for (int i = 0; i < n; i++) {
            arr.add(new Pair<>(s.charAt(i) - '0', 0));
        }
        s = in.next();
        for (int i = 0; i < n; i++) {
            arr.get(i).sc = s.charAt(i) - '0';
        }
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < n; i++) {
            Pair<Integer, Integer> p = arr.get(i);
            if (p.fs == 0 && p.sc == 0) {
                a++;
            } else if (p.fs == 1 && p.sc == 1) {
                b++;
            } else if (p.fs == 0 && p.sc == 1) {
                c++;
            } else {
                d++;
            }
        }
        for (int a1 = 0; a1 <= a; a1++) {
            for (int b1 = 0; b1 <= b; b1++) {
                int a2 = a - a1;
                int b2 = b - b1;
                int c1 = 0;
                int c2 = 0;
                int d1 = 0;
                int d2 = 0;

                if (a1 > a2) {
                    d2 = a1 - a2;
                } else {
                    c1 = a2 - a1;
                }
                if (b1 > b2) {
                    c2 = b1 - b2;
                } else {
                    d1 = b2 - b1;
                }
                if (d - d1 - d2 < 0 || c - c1 - c2 < 0 || d - d1 - d2 != c - c1 - c2) {
                    continue;
                }
                c1 += c - c1 - c2;
                for (int i = 0; i < n; i++) {
                    Pair<Integer, Integer> p = arr.get(i);
                    boolean print = false;
                    if (p.fs == 0 && p.sc == 0 && a1 > 0) {
                        print = true;
                        a1--;
                    } else if (p.fs == 1 && p.sc == 1 && b1 > 0) {
                        print = true;
                        b1--;
                    } else if (p.fs == 0 && p.sc == 1 && c1 > 0) {
                        print = true;
                        c1--;
                    } else if (p.fs == 1 && p.sc == 0 && d1 > 0) {
                        print = true;
                        d1--;
                    }
                    if (print) {
                        out.print(i + 1);
                        out.print(' ');
                    }
                }
                return;
            }
        }
        out.println(-1);
    }
}
