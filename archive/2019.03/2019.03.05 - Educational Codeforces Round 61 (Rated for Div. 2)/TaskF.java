package task;



import java.io.PrintWriter;

public class TaskF {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        n = s.length();
        int dp[][] = new int[n + 1][n + 1];
        for (int j1 = n - 1; j1 >= 0; j1--) {
            for (int j2 = j1; j2 < n; j2++) {
                dp[j1][j2] = 1 + dp[j1 + 1][j2];
                for (int j3 = j1 + 1; j3 <= j2; j3++) {
                    if (s.charAt(j1) != s.charAt(j3)) {
                        continue;
                    }
                    int cand = dp[j1 + 1][j3 - 1] + dp[j3][j2];
                    if (dp[j1][j2] > cand) {
                        dp[j1][j2] = cand;
                    }
                }
            }
        }
        out.println(dp[0][n - 1]);
    }

}
