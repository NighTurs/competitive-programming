package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {
    int N = (int) 1e6;

    public static class Node {
        List<Integer> primes;
        int left;
        boolean check = false;
        boolean isPrime = false;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Node nodes[] = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node();
            nodes[i].primes = new ArrayList<>();
            nodes[i].left = i;
            nodes[i].check = false;
            nodes[i].isPrime = true;
        }
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            nodes[a[i]].check = true;
        }
        for (int i = 2; i <= N; i++) {
            if (nodes[i].isPrime) {
                for (int h = i + i; h <= N; h += i) {
                    Node node = nodes[h];
                    node.isPrime = false;
                    if (node.check) {
                        int pow = 1;
                        while (node.left % i == 0) {
                            node.left /= i;
                            pow *= i;
                        }
                        node.primes.add(pow);
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (nodes[i].check) {
                nodes[i].primes.add(nodes[i].left);
            }
        }
        HashMap<Integer, Pair<Integer, Integer>> mp = new HashMap<>();
        Set<Integer> set = new TreeSet<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int max_l = 0, min_r = n - 1;
            Node cur = nodes[a[i]];
            for (int h : cur.primes) {
                Pair<Integer, Integer> lr = explore(h, i, mp, nodes, a);
                max_l = Math.max(max_l, lr.fs);
                min_r = Math.min(min_r, lr.sc);
            }
            if (min_r - max_l > max) {
                max = min_r - max_l;
                set.clear();
                set.add(max_l);
            } else if (min_r - max_l == max) {
                set.add(max_l);
            }
        }

        out.println(set.size() + " " + max);
        for (int i : set) {
            out.print((i + 1) + " ");
        }
    }

    public Pair<Integer, Integer> explore(int pow,
                        int p,
                        Map<Integer, Pair<Integer, Integer>> mp,
                        Node nodes[],
                        int a[]) {
        if (mp.containsKey(pow)) {
            Pair<Integer, Integer> pr =  mp.get(pow);
            if (pr.fs <= p && pr.sc >= p) {
                return pr;
            }
        }

        int l = 0, r = 0;
        int i = p + 1;
        while (i < a.length) {
            if (!has(nodes[a[i]], pow)) {
                break;
            }
            i++;
        }
        r = i - 1;
        i = p - 1;
        while (i >= 0) {
            if (!has(nodes[a[i]], pow)) {
                break;
            }
            i--;
        }
        l = i + 1;

        mp.put(pow, new Pair<>(l, r));
        return mp.get(pow);
    }

    public boolean has(Node node, int pow) {
        for (int h = 0; h < node.primes.size(); h++) {
            if (node.primes.get(h) % pow == 0) {
                return true;
            }
        }
        return false;
    }
}
