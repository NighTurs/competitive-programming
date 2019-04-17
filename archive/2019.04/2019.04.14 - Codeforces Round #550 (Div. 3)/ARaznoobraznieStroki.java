package task;

import java.io.PrintWriter;

public class ARaznoobraznieStroki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        outer:
        for (int t = 0; t < n; t++) {
            String s = in.next();
            int[] a = new int['z' - 'a' + 1];


            for (int i = 0; i < s.length(); i++) {
                a[s.charAt(i) - 'a']++;
            }
            for (int i = 0; i <= 'z' - 'a'; i++) {
                if (a[i] > 1) {
                    out.println("No");
                    continue outer;
                }
            }

            int i = 0;
            int count = 0;
            while (a[i] == 0 && i <= 'z' - 'a') {
                i++;
                count++;
            }
            i = 'z' - 'a';
            while (a[i] == 0 && i >= 0) {
                i--;
                count++;
            }
            if ('z' - 'a' + 1 - count == s.length()) {
                out.println("Yes");
            } else {
                out.println("No");
            }
        }
    }
}
