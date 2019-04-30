package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class DThreeReligions {

    static final int CHAR = 26;
    static final int MAX = 251;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        String s = in.next();

        int[][] nearest = new int[s.length()][CHAR];
        int[] last = new int[CHAR];
        Arrays.fill(last, -1);
        for (int i = s.length() - 1; i >= 0; i--) {
            last[s.charAt(i) - 'a'] = i;
            System.arraycopy(last, 0, nearest[i], 0, CHAR);
        }

        StringBuilder[] sb = new StringBuilder[3];
        for (int i = 0; i < 3; i++) {
            sb[i] = new StringBuilder();
        }
        int[][][] dp = new int[MAX][MAX][MAX];
        int[] idx = new int[3];

        for (int i = 0; i < q; i++) {
            String type = in.next();
            int r = in.nextInt() - 1;
            if (type.equals("+")) {
                String c = in.next();
                sb[r].append(c);

                idx[r] = sb[r].length();
                int i1;
                int i2;
                switch (r) {
                    case 0:
                        i1 = 1;
                        i2 = 2;
                        break;
                    case 1:
                        i1 = 0;
                        i2 = 2;
                        break;
                    case 2:
                        i1 = 0;
                        i2 = 1;
                        break;
                    default:
                        throw new RuntimeException();
                }

                for (idx[i1] = 0; idx[i1] <= sb[i1].length(); idx[i1]++) {
                    for (idx[i2] = 0; idx[i2] <= sb[i2].length(); idx[i2]++) {
                        dp[idx[0]][idx[1]][idx[2]] = Integer.MAX_VALUE;
                        for (int h = 0; h < 3; h++) {
                            if (idx[h] == 0) {
                                continue;
                            }
                            idx[h]--;
                            int prev = dp[idx[0]][idx[1]][idx[2]];
                            idx[h]++;
                            if (prev == Integer.MAX_VALUE || prev == n) {
                                continue;
                            }
                            int nc = sb[h].charAt(idx[h] - 1) - 'a';
                            if (nearest[prev][nc] == -1) {
                                continue;
                            }
                            dp[idx[0]][idx[1]][idx[2]] = Math.min(dp[idx[0]][idx[1]][idx[2]], nearest[prev][nc] + 1);
                        }
                    }
                }
            } else {
                sb[r].deleteCharAt(sb[r].length() - 1);
            }

            if (dp[sb[0].length()][sb[1].length()][sb[2].length()] != Integer.MAX_VALUE) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
