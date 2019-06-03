package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DIhabIObichnayaZadachaNaIsklyuchayusheeILI {

    //    List<Long> ans = new ArrayList<>();

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long x = in.nextInt();

        List<Integer> pows = new ArrayList<>();
        boolean excluded = x > (1 << n) - 1;
        for (int p = 0; p < n; p++) {
            int d = 1 << p;
            if ((x & d) != 0 && !excluded) {
                excluded = true;
            } else {
                pows.add(d);
            }
        }


        int m = (1 << pows.size()) - 1;
        if (m == 0) {
            out.println(0);
            return;
        }
        int[] ans = new int[m];
        doit(ans, 0, m - 1, 0, pows);
        out.println(m);
        for (int i = 0; i < m; i++) {
            out.print(ans[i]);
            out.print(' ');
        }
        //
        //        for (long x = 1; x <= (1 << n) - 1; x++) {
        //            ans = new ArrayList<>();
        //            List<Long> a = new ArrayList<>();
        //            doit(a, n, x);
        //
        //            out.println(String.format("%d %d", x, ans.size()));
        //            for (int i = 0; i < ans.size(); i++) {
        //                out.print(ans.get(i));
        //                out.print(' ');
        //            }
        //            out.println();
        //        }
    }

    private void doit(int[] ans, int l, int r, int pos, List<Integer> pows) {
        if (l == r) {
            ans[l] = pows.get(pos);
            return;
        }
        int mid = (l + r) / 2;
        ans[mid] = pows.get(pos);
        doit(ans, l, mid - 1, pos + 1, pows);
        doit(ans, mid + 1, r, pos + 1, pows);
    }

    //    private void doit(List<Long> a, int n, long x) {
    //        if (a.size() > ans.size()) {
    //            ans.clear();
    //            ans.addAll(a);
    //        }
    //        outer:
    ////        for (long p = 0; p < n; p++) {
    ////            long i = 1 << p;
    //        for (long i = 1; i < (1 << n); i++) {
    //            if (i == x) {
    //                continue;
    //            }
    //            for (int h = 0; h < a.size(); h++) {
    //                long xor = 0;
    //                for (int j = h; j < a.size(); j++) {
    //                    xor = (xor ^ a.get(j));
    //                }
    //                xor = (xor ^ i);
    //                if (xor == 0 || xor == x) {
    //                    continue outer;
    //                }
    //            }
    //            a.add(i);
    //            doit(a, n, x);
    //            a.remove(a.size() - 1);
    //        }
    //    }
}
