package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class DPleiOffTurnir {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int k = in.nextInt();
        String s = in.next();
        char[] a = s.toCharArray();

        int off = 0;

        int[] path = new int[s.length()];
        for (int r = k - 1; r >= 1; r--) {
            int ct = (int) (Math.pow(2, r) + 0.9);
            int h = off + ct;
            for (int i = off; i < off + ct; i += 2) {
                path[i] = h;
                path[i + 1] = h;
                h++;
            }
            off += ct;
        }


        int[] left = new int[s.length()];
        int[] right = new int[s.length()];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 0; i < s.length() - 1; i++) {
            int cur = i;
            int up = 0;
            char curC = s.charAt(cur);

            if (curC == '?') {
                up = left[cur] + right[cur];
            } else if (curC == '0') {
                up = left[cur];
            } else {
                up = right[cur];
            }
            if (cur % 2 == 0) {
                left[path[cur]] = up;
            } else {
                right[path[cur]] = up;
            }
        }

        int n = in.nextInt();
        int LAST = s.length() - 1;
        for (int i = 0; i < n; i++) {
            int cur = in.nextInt() - 1;
            String ss = in.next();
            char c = ss.charAt(0);
            a[cur] = c;
            while (cur != s.length() - 1) {
                int up = 0;
                if (a[cur] == '?') {
                    up = left[cur] + right[cur];
                } else if (a[cur] == '0') {
                    up = left[cur];
                } else {
                    up = right[cur];
                }
                if (cur % 2 == 0) {
                    left[path[cur]] = up;
                } else {
                    right[path[cur]] = up;
                }
                cur = path[cur];
            }
            switch (a[LAST]) {
                case '0':
                    out.println(left[LAST]);
                    break;
                case '1':
                    out.println(right[LAST]);
                    break;
                case '?':
                    out.println(left[LAST] + right[LAST]);
                    break;
            }
        }

    }
}
