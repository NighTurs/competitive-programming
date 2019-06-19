package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DLishniiElement {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Item> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new Item(in.nextInt(), i + 1));
        }
        a.sort(Comparator.comparingInt(x -> x.val));
        Map<Long, Integer> ct = new HashMap<>();
        for (int i = 1; i < n; i++) {
            long diff = a.get(i).val - a.get(i - 1).val;
            ct.putIfAbsent(diff, 0);
            ct.put(diff, ct.get(diff) + 1);

            if (ct.size() > 3) {
                out.println(-1);
                return;
            }
        }
        if (ct.size() == 1) {
            out.println(a.get(0).idx);
            return;
        }
        LinkedList<Long> remove = new LinkedList<>();
        LinkedList<Long> add = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                remove.add(a.get(i).val *1L - a.get(i - 1).val);
            }
            if (i != n - 1) {
                remove.add(a.get(i + 1).val * 1L - a.get(i).val);
            }
            if (i != 0 && i != n - 1) {
                add.add(a.get(i + 1).val * 1L - a.get(i - 1).val);
            }
            for (Long val : remove) {
                ct.put(val, ct.get(val) - 1);
                if (ct.get(val) == 0) {
                    ct.remove(val);
                }
            }
            for (Long val : add) {
                ct.putIfAbsent(val, 0);
                ct.put(val, ct.get(val) + 1);
            }
            if (ct.size() == 1) {
                out.println(a.get(i).idx);
                return;
            }
            for (Long val : remove) {
                ct.putIfAbsent(val, 0);
                ct.put(val, ct.get(val) + 1);
            }
            for (Long val : add) {
                ct.put(val, ct.get(val) - 1);
                if (ct.get(val) == 0) {
                    ct.remove(val);
                }
            }
            remove.clear();
            add.clear();
        }
        out.println(-1);
    }

    class Item {

        int val;
        int idx;

        public Item(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}
