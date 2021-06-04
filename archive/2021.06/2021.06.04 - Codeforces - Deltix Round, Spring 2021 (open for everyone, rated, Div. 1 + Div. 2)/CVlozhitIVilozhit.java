package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CVlozhitIVilozhit {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        List<Integer> stack = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ans.add(null);
        }
        int[] to = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = a[i];
            if (cur == 1) {
                stack.add(i);
            } else {

                while (a[stack.get(stack.size() - 1)] != cur - 1) {
                    List<Integer> removed = new ArrayList<>();
                    while (a[stack.get(stack.size() - 1)] != 1) {
                        removed.add(stack.get(stack.size() - 1));
                        stack.remove(stack.size() - 1);
                    }
                    removed.add(stack.get(stack.size() - 1));
                    stack.remove(stack.size() - 1);
                    for (int idx : removed) {
                        if (stack.isEmpty()) {
                            to[idx] = -1;
                        } else {
                            to[idx] = stack.get(stack.size() - 1);
                        }
                    }
                }
                stack.add(i);
            }

        }
        List<Integer> removed = new ArrayList<>();
        while (!stack.isEmpty()) {
            removed.add(stack.get(stack.size() - 1));
            stack.remove(stack.size() - 1);
            if (a[removed.get(removed.size() - 1)] == 1) {
                for (int idx : removed) {
                    if (stack.isEmpty()) {
                        to[idx] = -1;
                    } else {
                        to[idx] = stack.get(stack.size() - 1);
                    }
                }
                removed.clear();
            }
        }

        for (int i = 0; i < n; i++) {
            int cur = i;
            List<Integer> tail = new ArrayList<>();
            tail.add(a[i]);
            while (to[cur] != -1) {
                cur = to[cur];
                tail.add(a[cur]);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(tail.get(tail.size() - 1));
            for (int h = tail.size() - 2; h >= 0; h--) {
                sb.append('.');
                sb.append(tail.get(h));
            }
            out.println(sb);
        }
    }
}
