package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class F1MikrotranzakciiUproshennayaVersiya {

    int[] a;
    List<Item> items;
    int sum;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        a = new int[n];
        sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            sum += a[i];
        }

        items = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            items.add(new Item(in.nextInt(), in.nextInt() - 1));
        }
        items.sort(Comparator.comparingInt(x -> x.day));

        int[] last = new int[n];
        Arrays.fill(last, -1);
        for (int i = m - 1; i >= 0; i--) {
            Item item = items.get(i);
            if (last[item.game] != -1) {
                item.nextDay = last[item.game];
            }
            last[item.game] = item.day;
        }

        int t1 = 0;
        int t2 = (int)4e5 + 1;
        while (t1 < t2) {
            int mid = (t1 + t2) / 2;
            if (isPossible(mid)) {
                t2 = mid;
            } else {
                t1 = mid + 1;
            }
        }
        out.println(t1);
    }

    private boolean isPossible(int until) {
        int idx = 0;
        int lastStop = 0;
        int money = 0;
        int taken = 0;
        while (idx < items.size() && items.get(idx).day <= until) {
            Item item = items.get(idx);
            if (item.nextDay <= until) {
                idx++;
                continue;
            }
            money += item.day - lastStop;
            lastStop = item.day;
            int toTake = Math.min(money, a[item.game]);
            taken += toTake;
            money -= toTake;
            idx++;
        }
        money += until - lastStop;
        return (sum - taken) * 2 <= money;
    }

    class Item {

        int day;
        int game;
        int nextDay = (int)1e6;

        public Item(int day, int game) {
            this.day = day;
            this.game = game;
        }
    }
}
