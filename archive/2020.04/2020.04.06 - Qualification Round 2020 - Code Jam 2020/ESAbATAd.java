package task;

import java.io.PrintWriter;

public class ESAbATAd {
    int n = 0;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        if (testNumber == 1) {
            n = in.nextInt();
        }
        System.err.println(testNumber);
        System.err.println(n);
        int nQueries = 0;
        int last = 0;
        int checkSame = -1;
        int checkDiff = -1;

        int[] ans = new int[n + 1];
        while (true) {
            if ((nQueries + 1) % 10 > 1 || nQueries == 0) {
                last++;
                nQueries++;
                ans[last] = interact(in, out, last);
                int r = n - last + 1;
                nQueries++;
                ans[r] = interact(in, out, r);
                if (ans[last] != ans[r]) {
                    checkDiff = last;
                } else {
                    checkSame = last;
                }
                if (last >= (n + 1) / 2) {
                    break;
                }
            } else {
                if ((nQueries + 1) % 10 == 0) {
                    nQueries++;
                    interact(in, out, 1);
                }
                boolean sameDiff = false;
                if (checkSame != -1) {
                    nQueries++;
                    int val = interact(in, out, checkSame);
                    if (ans[checkSame] != val) {
                        sameDiff = true;
                    }
                }
                boolean diffDiff = false;
                if (checkDiff != -1) {
                    nQueries++;
                    int val = interact(in, out, checkDiff);
                    if (ans[checkDiff] != val) {
                        diffDiff = true;
                    }
                }
                for (int i = 1; i <= (n + 1) / 2; i++) {
                    int r = n - i + 1;
                    if (ans[i] == ans[r]) {
                        ans[i] = sameDiff ? flip(ans[i]) : ans[i];
                        ans[r] = sameDiff ? flip(ans[r]) : ans[r];
                    } else {
                        ans[i] = diffDiff ? flip(ans[i]) : ans[i];
                        ans[r] = diffDiff ? flip(ans[r]) : ans[r];
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            out.print(ans[i]);
        }
        out.println();
        out.flush();
        String o = in.next();
        System.err.println(o);
        if (o.equals("N")) {
            System.err.println("here");
            System.exit(1);
        }
        System.err.println("here2");
    }

    private static int flip(int val) {
        return (val + 1) % 2;
    }

    private static int interact(InputReader in, PrintWriter out, int pos) {
        out.println(pos);
        out.flush();
        int ans = in.nextInt();
        System.err.println(String.format("%d %d", pos, ans));
        return ans;
    }
}
