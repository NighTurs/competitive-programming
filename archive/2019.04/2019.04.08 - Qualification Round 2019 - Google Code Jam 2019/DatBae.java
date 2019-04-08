package task;

import java.io.PrintWriter;

public class DatBae {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int b = in.nextInt();
        int f = in.nextInt();

        String[] ans = new String[5];

        for (int p = 4; p >= 0; p--) {
            int pow = 1 << p;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(check(i, pow));
            }
            out.println(sb);
            out.flush();
            ans[p] = in.next();
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int h = 0;
        outer:
        while (h < n) {
            for (int p = 4; p >= 0; p--) {
                int pow = 1 << p;
                if (i >= ans[p].length() || ans[p].charAt(i) - '0' != check(h, pow)) {
                    sb.append(h);
                    sb.append(' ');
                    h++;
                    continue outer;
                }
            }
            h++;
            i++;
        }
        out.println(sb);
        out.flush();
        in.nextInt();
    }

    public int check(int pos, int pow) {
        return (pos / pow) % 2;
    }
}
