package task;

import java.io.PrintWriter;

public class BDlinnoeChislo {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        int[] map = new int[10];
        for (int i = 1; i <= 9; i++) {
            map[i] = in.nextInt();
        }
        StringBuilder sb = new StringBuilder(s);
        boolean started = false;
        for (int i = 0; i < s.length(); i++) {
            int d = sb.charAt(i) - '0';
            if (started) {
                if (map[d] < d) {
                    break;
                }
                sb.setCharAt(i, (char)('0' + map[d]));
            } else {
                if (map[d] > d) {
                    started = true;
                    sb.setCharAt(i, (char)('0' + map[d]));
                }
            }
        }

        out.println(sb);
    }
}
