package task;

import java.io.PrintWriter;

public class EMediannayaStroka {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String a = in.next();
        String b = in.next();

        int[] ans = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int d = a.charAt(i) - 'a' + b.charAt(i) - 'a';
            ans[i] = d / 2;
            if (d % 2 == 1) {
                ans[i + 1] += 13;
                ans[i] += ans[i + 1] / 26;
                ans[i + 1] %= 26;
            }
        }
        for (int i = 0; i < n; i++) {
            out.print((char)(ans[i] + 'a'));
        }
    }
}
