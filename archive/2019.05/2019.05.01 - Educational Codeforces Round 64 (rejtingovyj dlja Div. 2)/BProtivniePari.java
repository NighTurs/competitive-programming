package task;

import java.io.PrintWriter;

public class BProtivniePari {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        outer:
        for (int tt = 0; tt < t; tt++) {
            String s = in.next();
            int[] c = new int[26];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i) - 'a']++;
            }
            StringBuilder sb = doit(c, 0);
            if (sb == null) {
                sb = doit(c, 1);
            }
            if (sb == null) {
                out.println("No answer");
            } else {
                out.println(sb);
            }
        }
    }

    StringBuilder doit(int[] c, int first) {
        int last = -2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (c[i] == 0 || i % 2 == first) {
                continue;
            }
            if (Math.abs(last - i) == 1) {
                return null;
            }
            for (int h = 0; h < c[i]; h++) {
                sb.append((char) ('a' + i));
            }
            last = i;
        }
        for (int i = 0; i < 26; i++) {
            if (c[i] == 0 || i % 2 == Math.abs((first - 1))) {
                continue;
            }
            if (Math.abs(last - i) == 1) {
                return null;
            }
            for (int h = 0; h < c[i]; h++) {
                sb.append((char) ('a' + i));
            }
            last = i;
        }
        return sb;
    }
}
