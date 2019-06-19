package task;

import java.io.PrintWriter;

public class G1PleilistDlyaPolikarpaUproshennayaVersiya {

    static final int MOD = (int)1e9 + 7;
    int n;
    int t;
    int[][] a = new int[15][2];
    long[][] mem = new long[1 << 15][4];
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        for (int i = 0; i < (1 << 15); i++) {
            for (int h = 0; h < 4; h++) {
                mem[i][h] = -1;
            }
        }
        n = in.nextInt();
        t = in.nextInt();
        for (int i = 0; i < n; i++) {
            a[i][0] = in.nextInt();
            a[i][1] = in.nextInt();
        }
        out.println(doit(0, 0, 0));
    }

    private long doit(int mask, int genre, int sum) {
        if (mem[mask][genre] != -1) {
            return mem[mask][genre];
        }
        if (sum > t) {
            mem[mask][genre] = 0;
            return mem[mask][genre];
        }
        if (sum == t) {
            mem[mask][genre] = 1;
            return mem[mask][genre];
        }
        long ct = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) > 0 || genre == a[i][1]) {
                continue;
            }
            ct = (ct + doit(mask | (1 << i), a[i][1], sum + a[i][0])) % MOD;
        }

        mem[mask][genre] = ct;
        return mem[mask][genre];
    }
}
