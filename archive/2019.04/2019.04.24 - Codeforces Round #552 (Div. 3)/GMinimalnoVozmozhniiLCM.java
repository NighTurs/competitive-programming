package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class GMinimalnoVozmozhniiLCM {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] idx = new int[(int)1e7 + 1];
        Arrays.fill(idx, -1);

        long min = Long.MAX_VALUE;
        long fs = 0, sc = 0;

        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            if (idx[a] == -1) {
                idx[a] = i;
            } else {
                if (min > a) {
                    min = a;
                    fs = idx[a];
                    sc = i;
                }
            }
        }

        for (int i = 1; i <= (int) 1e7; i++) {
            long firstIdx = -1;
            long firstNum = -1;
            for (int h = i; h <= (int) 1e7; h += i) {
                if (idx[h] == -1) {
                    continue;
                }
                if (firstIdx == -1) {
                    firstIdx = idx[h];
                    firstNum = h;
                } else {
                    if (min > firstNum * h / i) {
                        min = firstNum * h / i;
                        fs = firstIdx;
                        sc = idx[h];
                    }
                    break;
                }
            }
        }
        if (sc < fs) {
            long z = fs;
            fs = sc;
            sc = z;
        }
        out.println(String.format("%d %d", fs + 1, sc + 1));
    }
}
