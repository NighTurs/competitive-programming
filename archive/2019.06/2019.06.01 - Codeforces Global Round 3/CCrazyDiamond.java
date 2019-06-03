package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CCrazyDiamond {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] pos = new int[n];
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt() - 1;
            pos[a[i]] = i;
        }
        List<Pair<Integer, Integer>> swp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            alloc(i, a, pos, swp, n);
        }
        out.println(swp.size());
        for (Pair<Integer, Integer> p : swp) {
            out.print(p.fs);
            out.print(' ');
            out.print(p.sc);
            out.println();
        }
    }

    void alloc(int i, int[] a, int[] pos, List<Pair<Integer, Integer>> swp, int n) {
        if (a[i] == i) {
            return;
        }
        if ((pos[i] - i) * 2 >= n) {
            swap(i, pos[i], a, pos, swp);
        } else {
            if (i > n - i - 1) {
                if (pos[i] < n - pos[i] - 1) {
                    swap(pos[i], n - 1, a, pos, swp);
                }
                swap(pos[i], 0, a, pos, swp);
                swap(i, pos[i], a, pos, swp);
                alloc(0, a, pos, swp, n);
            } else {
                if (pos[i] > n - pos[i] - 1) {
                    swap(pos[i], 0, a, pos, swp);
                }
                swap(pos[i], n - 1, a, pos, swp);
                swap(i, pos[i], a, pos, swp);
                alloc(0, a, pos, swp, n);
            }
        }
    }

    void swap(int i, int h, int[] a, int[] pos, List<Pair<Integer, Integer>> swp) {
        swp.add(new Pair<>(i + 1, h + 1));
        int z1 = a[i];
        int z2 = a[h];
        a[h] = z1;
        a[i] = z2;
        pos[z1] = h;
        pos[z2] = i;
    }
}
