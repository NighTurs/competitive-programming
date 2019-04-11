package task;

import java.io.PrintWriter;

public class MysteriousRoadSigns {

    Sign[] signs;
    Sign[] continueSigns;
    static final int OTHER = Integer.MAX_VALUE;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        signs = new Sign[n];
        for (int i = 0; i < n; i++) {
            int d = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            signs[i] = new Sign(d + a, d - b);
        }

        continueSigns = new Sign[n];
        continueSigns[n - 1] = new Sign(signs[n - 1].val1, signs[n - 1].val2);
        for (int i = n - 2; i >= 0; i--) {
            continueSigns[i] = new Sign(0, 0);
            if (signs[i].val1 == signs[i + 1].val1) {
                continueSigns[i].val2 = continueSigns[i + 1].val2;
            } else {
                continueSigns[i].val2 = signs[i + 1].val2;
            }
            if (signs[i].val2 == signs[i + 1].val2) {
                continueSigns[i].val1 = continueSigns[i + 1].val1;
            } else {
                continueSigns[i].val1 = signs[i + 1].val1;
            }
        }

        int[][][] mem = new int[n][3][3];
        for (int j1 = 0; j1 < 3; j1++) {
            for (int j2 = 0; j2 < 3; j2++) {
                mem[n - 1][j1][j2] = 1;
            }
        }

        int maxDist = 1;
        int maxCount = 1;
        for (int i = n - 2; i >= 0; i--) {
            boolean counted = false;
            for (int j1 = 0; j1 < 3; j1++) {
                for (int j2 = 0; j2 < 3; j2++) {
                    if ((j1 == 0) == (j2 == 0)) {
                        continue;
                    }
                    mem[i][j1][j2] = 1;
                    int ival1 = j1 == 0 ? signs[i].val1 : j1 == 1 ? OTHER : continueSigns[i].val1;
                    int ival2 = j2 == 0 ? signs[i].val2 : j2 == 1 ? OTHER : continueSigns[i].val2;

                    if (ival1 == signs[i + 1].val1) {
                        if (ival2 == continueSigns[i + 1].val2) {
                            mem[i][j1][j2] = 1 + mem[i + 1][0][2];
                        } else {
                            mem[i][j1][j2] = 1 + mem[i + 1][0][1];
                        }
                    }

                    if (ival2 == signs[i + 1].val2) {
                        if (ival1 == continueSigns[i + 1].val1) {
                            mem[i][j1][j2] = Math.max(mem[i][j1][j2], 1 + mem[i + 1][2][0]);
                        } else {
                            mem[i][j1][j2] = Math.max(mem[i][j1][j2], 1 + mem[i + 1][1][0]);
                        }
                    }

                    if (mem[i][j1][j2] > maxDist) {
                        maxDist = mem[i][j1][j2];
                        maxCount = 0;
                        counted = false;
                    }
                    if (!counted && mem[i][j1][j2] == maxDist) {
                        maxCount++;
                        counted = true;
                    }
                }
            }
        }

        out.println(String.format("Case #%d: %d %d", testNumber, maxDist, maxCount));
    }

    static class Sign {

        int val1;
        int val2;

        public Sign(int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }
    }
}
