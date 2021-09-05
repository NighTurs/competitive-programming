package task;

import java.io.PrintWriter;

public class BMEXorMassiva {
    int N = 4 * (int) 1e5;

    int[] cum = new int[N];
    {
        int cur = 0;
        cum[0] = 0;
        for (int i = 1; i < N; i++) {
            cum[i] = cum[i - 1] ^ i;
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int mex = in.nextInt();
        int xor = in.nextInt();
        int cur = cum[mex - 1];
        if (cur == xor) {
            out.println(mex);
            return;
        }
        int cand = xor ^ cur;
        if (cand == mex) {
            out.println(mex + 2);
        } else {
            out.println(mex + 1);
        }
    }
}
