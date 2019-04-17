package task;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class CDvePeremeshanniePosledovatelnosti {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Set<Integer> first = new HashSet<>();
        Set<Integer> second = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            if (first.contains(a)) {
                if (second.contains(a)) {
                    out.println("NO");
                    return;
                }
                second.add(a);
            } else {
                first.add(a);
            }
        }
        out.println("YES");
        out.println(first.size());
        first.stream().sorted().forEach(a -> out.print(String.format("%d ", a)));
        out.println();
        out.println(second.size());
        second.stream().sorted(Comparator.reverseOrder()).forEach(a -> out.print(String.format("%d ", a)));
        out.println();
    }
}
