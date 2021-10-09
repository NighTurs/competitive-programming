package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class FStabiliziruiMassivIVersiya {

    int doit(int pos, int d, int[] mem, int[] a) {
        if (a[pos] == 0) {
            return 0;
        }

        if (mem[pos] == -2) {
            return -2;
        }
        if (mem[pos] != -1) {
            return mem[pos];
        }

        mem[pos] = -2;
        int val = doit((pos + mem.length - d) % mem.length, d, mem, a);
        if (val == -2) {
            return -2;
        }
        mem[pos] = val + 1;

        return mem[pos];
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int d = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] mem = new int[n];
        Arrays.fill(mem, -1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int val = doit(i, d, mem, a);
            if (val == -2) {
                out.println(-1);
                return;
            }
            max = Math.max(max, val);
        }
        out.println(max);
    }
}
