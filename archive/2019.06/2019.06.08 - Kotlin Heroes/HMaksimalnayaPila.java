package task;

import java.io.PrintWriter;
import java.util.LinkedList;

public class HMaksimalnayaPila {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();

        for (int t = 0; t < tt; t++) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            ArrayUtils.sort(a);

            int t1 = 0;
            int t2 = n / 2;
            while (t1 < t2) {
                int m = (t1 + t2 + 1) / 2;
                boolean possible = true;
                for (int i = 0; i < m; i++) {
                    int opp = n - m + i;
                    if (a[i] == a[opp]) {
                        possible = false;
                        break;
                    }
                }
                if (possible) {
                    t1 = m;
                } else {
                    t2 = m - 1;
                }
            }

            LinkedList<Integer> opt1 = doit(0, t1, t1, a);
            LinkedList<Integer> opt2 = doit(t1 - 1, -1, t1, a);
            if (t1 * 2 < n) {
                tryAdd(opt1, a[t1]);
                tryAdd(opt2, a[t1]);
            }
            if (t1 * 2 + 1 < n) {
                tryAdd(opt1, a[n - t1 - 1]);
                tryAdd(opt2, a[n - t1 - 1]);
            }
            if (opt1.size() < opt2.size()) {
                opt1 = opt2;
            }
            out.println(opt1.size());
            for (Integer val : opt1) {
                out.print(val);
                out.print(' ');
            }
            out.println();
        }
    }

    private void tryAdd(LinkedList<Integer> a, int val) {
        if (a.isEmpty()) {
            a.add(val);
            return;
        }
        if (a.size() == 1 && val != a.getFirst()) {
            a.addLast(val);
            return;
        }
        if (a.peekFirst() != val && a.peekFirst() < a.get(1) == a.peekFirst() < val) {
            a.addFirst(val);
        } else if (a.peekLast() != val && a.peekLast() < a.get(a.size() - 2) == a.peekLast() < val) {
            a.addLast(val);
        }
    }

    private LinkedList<Integer> doit(int from, int to, int m, int[] a) {
        int inc = from < to ? 1 : -1;
        LinkedList<Integer> out = new LinkedList<>();
        for (int i = from; i != to; i += inc) {
            int opp = a.length - m + i;
            if (!out.isEmpty() && out.peekLast() == a[i]) {
                break;
            }
            out.addLast(a[i]);
            out.addLast(a[opp]);
        }
        return out;
    }
}

// 5 4 ** 4 3
// 6 5 4 **** 4 3 2
//4 3 5 4 4 5 3 4
//        6 4 5 3 4 2
