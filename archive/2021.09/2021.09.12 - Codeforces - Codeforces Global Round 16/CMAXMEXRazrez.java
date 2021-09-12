package task;

import java.io.PrintWriter;

public class CMAXMEXRazrez {
    int doit(String a, String b, int pos) {
        int sum = (a.charAt(pos) - '0') + (b.charAt(pos) - '0');
        return sum;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String a = in.next();
        String b = in.next();

        long ans = 0;
        boolean prev1 = false;
        boolean prev0 = false;

        for (int i = 0; i < n; i++) {
            int sum = doit(a, b, i);
            if (sum == 1) {
                ans += 2;
                prev0 = false;
                prev1 = false;
            } else if (sum == 2) {
                if (prev0) {
                    prev0 = false;
                    ans += 2;
                } else {
                    prev1 = true;
                }
            } else {
                if (prev1) {
                    ans += 2;
                    prev1 = false;
                } else {
                    if (i == n - 1) {
                        ans += 1;
                    } else {
                        int next = doit(a, b, i + 1);
                        if (next <= 1) {
                            ans += 1;
                        } else {
                            prev0 = true;
                        }
                    }
                }
            }

        }
        out.println(ans);
    }
}
