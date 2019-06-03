package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EEarthWindAndFire {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        List<Stone> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new Stone(in.nextInt(), i + 1));
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        ArrayUtils.sort(b);
        a.sort(Comparator.comparingLong(x -> x.val));
        long balance = 0;
        for (int i = 0; i < n; i++) {
            balance += b[i] - a.get(i).val;
        }
        if (balance != 0) {
            out.println("NO");
            return;
        }

        List<Pull> ans = new ArrayList<>();
        int l = n - 1, r = n - 1;
        while (true) {
            while (l >= 0 && a.get(l).val >= b[l]) {
                l--;
            }
            while (r >= 0 && a.get(r).val <= b[r]) {
                r--;
            }
            if (l < 0 || r < 0) {
                break;
            }
            if (a.get(l).val >= a.get(r).val) {
                break;
            }
            long d = Math.min((a.get(r).val - a.get(l).val) / 2,
                    Math.min(b[l] - a.get(l).val, a.get(r).val - b[r]));
            ans.add(new Pull(a.get(l).idx, a.get(r).idx, d));
            a.get(l).val += d;
            a.get(r).val -= d;
        }
        for (int i = 0; i < n; i++) {
            if (a.get(i).val != b[i]) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
        out.println(ans.size());
        for (Pull p : ans) {
            out.println(String.format("%d %d %d", p.left, p.right, p.d));
        }
    }

    class Stone {

        long val;
        int idx;

        public Stone(long val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    class Pull {

        int left;
        int right;
        long d;

        public Pull(int left, int right, long d) {
            this.left = left;
            this.right = right;
            this.d = d;
        }
    }
}
