package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DDirtyDeedsDoneDirtCheap {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        List<Pair> asc = new ArrayList<>();
        List<Pair> desc = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int val1 = in.nextInt();
            int val2 = in.nextInt();
            if (val1 < val2) {
                asc.add(new Pair(val1, val2, i + 1));
            } else {
                desc.add(new Pair(val1, val2, i + 1));
            }
        }

        asc.sort(Comparator.comparingInt(a -> -a.sc));
        desc.sort(Comparator.comparingInt(a -> a.sc));

        int szA = Math.min(1, asc.size());
        while (asc.size() > szA && asc.get(szA).fs < asc.get(szA - 1).sc) {
            szA++;
        }
        int szD = Math.min(1, desc.size());
        while (desc.size() > szD && desc.get(szD).fs > desc.get(szD - 1).sc) {
            szD++;
        }
        int sz;
        List<Pair> ans;
        if (szA > szD) {
            sz = szA;
            ans = asc;
        } else {
            sz = szD;
            ans = desc;
        }
        out.println(sz);
        for (int i = 0; i < sz; i++) {
            out.print(ans.get(i).idx);
            out.print(' ');
        }
    }

    class Pair {
        int fs;
        int sc;
        int idx;

        public Pair(int fs, int sc, int idx) {
            this.fs = fs;
            this.sc = sc;
            this.idx = idx;
        }
    }
}
