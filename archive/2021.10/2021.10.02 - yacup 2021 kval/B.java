package task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class B {

    int ch(char c) {
        switch (c) {
            case 'W':
                return 0;
            case 'B':
                return 1;
            case 'R':
                return 2;
            default:
                throw new RuntimeException();
        }
    }

    int doit(String s, int pos) {
        return ch(s.charAt(pos));
    }

    int doit2(int[] c) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            int cur = 0;
            for (int h = 0; h < 4; h++) {
                cur = cur * 3 + c[i + h];
            }
            max = Math.max(cur, max);
        }
        return max;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] c = new int[8];

        Map<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String a = in.next();
            String b = in.next();
            c[0] = doit(a, 0);
            c[1] = doit(a, 1);
            c[2] = doit(b, 1);
            c[3] = doit(b, 0);
            for (int h = 4; h < 8; h++) {
                c[h] = c[h - 4];
            }
            int code = doit2(c);
            mp.putIfAbsent(code, 0);
            mp.put(code, mp.get(code) + 1);
        }

        n = in.nextInt();
        int m = in.nextInt();
        int[][] d = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int h = 0; h < m; h++) {
                d[i][h] = ch(s.charAt(h));
            }
        }
        for (int i = 0; i < n; i += 2) {
            for (int h = 0; h < m; h += 2) {
                c[0] = d[i][h];
                c[1] = d[i][h + 1];
                c[2] = d[i + 1][h + 1];
                c[3] = d[i + 1][h];
                for (int j = 4; j < 8; j++) {
                    c[j] = c[j - 4];
                }
                int code = doit2(c);
                int cur = mp.getOrDefault(code, 0);
                if (cur > 0) {
                    mp.put(code, cur - 1);
                } else {
                    out.println("No");
                    return;
                }
            }
        }
        out.println("Yes");
    }
}
