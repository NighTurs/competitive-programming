package task;

public class ReconstructNumber {

    public String smallest(String comparisons) {
        int n = comparisons.length() + 1;
        int[][] dp = new int[n][10];
        for (int i = 0; i < n - 1; i++) {
            for (int d = 0; d <= 9; d++) {
                dp[i][d] = -1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int d = 0; d <= 9; d++) {
                outer:
                for (int dd = 0; dd <= 9; dd++) {
                    if (dp[i + 1][dd] == -1) {
                        continue;
                    }
                    switch (comparisons.charAt(i)) {
                        case '=':
                            if (d == dd) {
                                dp[i][d] = dd;
                                break outer;
                            } else {
                                continue;
                            }
                        case '<':
                            if (d < dd) {
                                dp[i][d] = dd;
                                break outer;
                            } else {
                                continue;
                            }
                        case '>':
                            if (d > dd) {
                                dp[i][d] = dd;
                                break outer;
                            } else {
                                continue;
                            }
                        case '!':
                            if (d != dd) {
                                dp[i][d] = dd;
                                break outer;
                            } else {
                                continue;
                            }
                        default:
                            throw new RuntimeException();
                    }
                }
            }
        }
        int cur = 0;
        int d = -1;
        for (int i = 1; i <= 9; i++) {
            if (dp[cur][i] != -1) {
                d = i;
                break;
            }
        }
        if (d == -1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        while (cur < n) {
            sb.append(d);
            d = dp[cur][d];
            cur++;
        }
        return sb.toString();
    }
}
