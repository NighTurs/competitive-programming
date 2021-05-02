package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MedianSort {

    InputReader in;
    PrintWriter out;

    int request(int x, int y, int z) {
//        System.err.println(String.format("%d %d %d", x, y, z));
        out.println(String.format("%d %d %d", x, y, z));
        out.flush();
        int ans = in.nextInt();
//        System.err.println(ans);
        if (ans == -1) {
            System.exit(0);
        }
        return ans;
    }

    Node rec(List<Integer> a) {
        if (a.isEmpty()) {
            return null;
        }
        if (a.size() == 1) {
            return Node.get(a.get(0), a.get(0));
        }
        if (a.size() == 2) {
            return Node.get(a.get(0), a.get(1));
        }
        int x = a.get(0);
        int y = a.get(1);
        Node d = Node.get(x, y);

        List<Integer> mid = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 2; i < a.size(); i++) {
            int v = a.get(i);
            int m = request(x, y, v);
            if (m == v) {
                mid.add(v);
            } else if (m == x) {
                left.add(v);
            } else {
                right.add(v);
            }
        }
        d.mid = rec(mid);
        d.left = rec(left);
        d.right = rec(right);
        return d;
    }

    void unwrap(Node n, List<Integer> ans) {
        if (n == null) {
            return;
        }
        if (n.isSingle()) {
            ans.add(n.x);
            return;
        }
        boolean flip = false;
        boolean sure = false;
        if (!ans.isEmpty()) {
            int first = ans.get(0);
            int m = request(n.x, n.y, first);
            assert (first != m);
            if (m == n.y) {
                flip = true;
            }
            sure = true;
        }
        if (!flip) {
            unwrap(n.left, ans);
            if (!sure && ans.size() > 1) {
                int z = request(ans.get(0), ans.get(1), n.x);
                if (z != ans.get(1)) {
                    int t1 = 0;
                    int t2 = ans.size() - 1;
                    while (t1 < t2) {
                        int zz = ans.get(t1);
                        ans.set(t1, ans.get(t2));
                        ans.set(t2, zz);
                        t1++;
                        t2--;
                    }
                }
            }
            ans.add(n.x);
            unwrap(n.mid, ans);
            ans.add(n.y);
            unwrap(n.right, ans);
        } else {
            unwrap(n.right, ans);
            ans.add(n.y);
            unwrap(n.mid, ans);
            ans.add(n.x);
            unwrap(n.left, ans);
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        int n = in.nextInt();
        int q = in.nextInt();
        this.in = in;
        this.out = out;
        for (int tt = 0; tt < t; tt++) {
            List<Integer> full = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                full.add(i + 1);
            }
            Node root = rec(full);
            List<Integer> ans = new ArrayList<>();
            unwrap(root, ans);
            for (int i = 0; i < n; i++) {
                out.print(ans.get(i));
//                System.err.print(ans.get(i));
                out.print(' ');
//                System.err.print(' ');
            }
            out.println();
            out.flush();
//            System.err.println();
            int res = in.nextInt();
            if (res != 1) {
                return;
            }
        }

    }

    public static class Node {
        int x;
        int y;

        Node left;
        Node right;
        Node mid;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Node get(int x, int y) {
            return new Node(x, y);
        }

        public boolean isSingle() {
            return x == y;
        }
    }
}
