package task;

import java.io.PrintWriter;

public class PatternMatching {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String[] ss = new String[n];
        String prefix = "";
        String syffix = "";
        for (int i = 0; i < n; i++) {
            String s = in.next();
            int h = 0;
            while (h < s.length() && s.charAt(h) != '*') {
                h++;
            }
            if (h > prefix.length()) {
                prefix = s.substring(0, h);
            }
            h = s.length() - 1;
            while (h >= 0 && s.charAt(h) != '*') {
                h--;
            }
            if (s.length() - h - 1 > syffix.length()) {
                syffix = s.substring(h + 1);
            }
            ss[i] = s;
        }
        int[] st = new int[n];
        int[] ed = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int h = 0; h < prefix.length(); h++) {
                if (cur >= ss[i].length()) {
                    printBad(testNumber, out);
                    return;
                }
                if (ss[i].charAt(cur) == prefix.charAt(h)) {
                    cur++;
                } else if (ss[i].charAt(cur) != '*') {
                    printBad(testNumber, out);
                    return;
                }
            }
            st[i] = cur;
            cur = ss[i].length() - 1;
            for (int h = syffix.length() - 1; h >= 0; h--) {
                if (cur < 0) {
                    printBad(testNumber, out);
                    return;
                }
                if (ss[i].charAt(cur) == syffix.charAt(h)) {
                    cur--;
                } else if (ss[i].charAt(cur) != '*') {
                    printBad(testNumber, out);
                    return;
                }
            }
            ed[i] = cur;
        }
        out.print(String.format("Case #%d: %s", testNumber, prefix));
        for (int i = 0; i < n; i++) {
            for (int h = st[i]; h <= ed[i]; h++) {
                if (ss[i].charAt(h) != '*') {
                    out.print(ss[i].charAt(h));
                }
            }
        }
        out.println(syffix);
    }

    public static void printBad(int testNumber, PrintWriter out) {
        out.println(String.format("Case #%d: *", testNumber));
    }
}
