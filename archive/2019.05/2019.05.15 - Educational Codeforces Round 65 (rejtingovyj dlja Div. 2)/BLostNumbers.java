package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BLostNumbers {
    int[] a = new int[]{4, 8, 15, 16, 23, 42};

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            out.println(String.format("? %d %d", i + 1, i + 2));
            out.flush();
            ans.add(in.nextInt());
        }
        Set<Integer> vals = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            vals.add(a[i]);
        }
        out.print("! ");
        for (int i = 0; i < 3; i++) {
            for (int h = 0; h < a.length; h++) {
                if (ans.get(i) % a[h] == 0 && ans.get(i) / a[h] != a[h] && vals.contains(ans.get(i) / a[h])) {
                    int first = a[h];
                    int second = ans.get(i) / first;
                    vals.remove(first);
                    vals.remove(second);
                    if (ans.get(i + 1) % first == 0 && vals.contains(ans.get(i + 1) / first)) {
                        if (i == 0) {
                            out.print(second);
                            out.print(' ');
                        }
                        out.print(first);
                        out.print(' ');
                        if (i == 2) {
                            out.print(ans.get(i + 1) / first);
                            vals.remove(ans.get(i + 1) / first);
                        }
                    } else {
                        if (i == 0) {
                            out.print(first);
                            out.print(' ');
                        }
                        out.print(second);
                        out.print(' ');
                        if (i == 2) {
                            out.print(ans.get(i + 1) / second);
                            vals.remove(ans.get(i + 1) / second);
                        }
                    }
                    break;
                }
            }
        }
        out.print(' ');
        out.println(vals.toArray()[0]);
        out.flush();
    }
}
