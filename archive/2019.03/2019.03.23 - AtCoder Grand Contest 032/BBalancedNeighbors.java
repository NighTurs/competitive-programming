package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BBalancedNeighbors {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        List<Pair<Integer, Integer>> ans = new ArrayList<>();
        int notTake;


        if (n % 2 == 1) {
            notTake = n - 1;
        } else {
            notTake = n;
        }

        for (int i = 1; i < n; i++) {
            for (int h = i + 1; h <= n; h++) {
                if (h != notTake) {
                    ans.add(new Pair<>(i, h));
                }
            }
            notTake--;
        }

        out.println(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            out.println(String.format("%d %d", ans.get(i).fs, ans.get(i).sc));
        }

    }

    private void doit(int pos, List<Item> items, int n) {
        if (pos == items.size()) {
            int[] sum = new int[n + 1];
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).taken) {
                    sum[items.get(i).from] += items.get(i).to;
                    sum[items.get(i).to] += items.get(i).from;
                }
            }
            int ss = sum[1];
            for (int i = 2; i <= n; i++) {
                if (ss != sum[i]) {
                    return;

                }
            }
            System.out.println(ss);
            for (int h = 0; h < items.size(); h++) {
                if (items.get(h).taken) {
                    System.out.println(String.format("%d %d", items.get(h).from, items.get(h).to));
                }
            }
            return;
        }
        items.get(pos).taken = true;
        doit(pos + 1, items, n);
        items.get(pos).taken = false;
        doit(pos + 1, items, n);
    }

    public static class Item {
        int from;
        int to;
        boolean taken;

        public Item(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
