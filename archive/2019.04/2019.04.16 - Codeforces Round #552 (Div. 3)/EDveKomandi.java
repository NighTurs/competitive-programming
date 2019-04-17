package task;

import java.io.PrintWriter;
import java.util.PriorityQueue;

public class EDveKomandi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> -Integer.compare(a[x], a[y]));
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }


        Item[] idxs = new Item[n];
        for (int i = 0; i < n; i++) {
            idxs[i] = new Item();
        }
        for (int i = 0; i < n; i++) {
            idxs[i].idx = i;
            if (i != 0) {
                idxs[i].prev = idxs[i - 1];
            }
            if (i != n - 1) {
                idxs[i].next = idxs[i + 1];
            }
        }
        int[] ans = new int[n];

        int count = 0;
        int team = 1;
        while (count < n) {
            while (ans[queue.peek()] != 0) {
                queue.poll();
            }
            int idx = queue.poll();
            ans[idx] = team;
            count++;

            Item cur = idxs[idx];
            int took = 0;
            while (cur.next != null && took < k) {
                cur = cur.next;
                ans[cur.idx] = team;
                took++;
            }
            count += took;
            Item last = cur.next;
            cur = idxs[idx];
            took = 0;
            while (cur.prev != null && took < k) {
                cur = cur.prev;
                ans[cur.idx] = team;
                took++;
            }
            count += took;
            Item first = cur.prev;
            if (first != null) {
                first.next = last;
            }
            if (last != null) {
                last.prev = first;
            }


            team = team == 1 ? 2 : 1;
        }
        for (int i = 0; i < n; i++) {
            out.print(ans[i]);
        }

    }

    public static class Item {
        int idx;
        Item next;
        Item prev;
    }
}
