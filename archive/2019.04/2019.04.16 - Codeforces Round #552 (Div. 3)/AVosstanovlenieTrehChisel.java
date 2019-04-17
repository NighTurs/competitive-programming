package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AVosstanovlenieTrehChisel {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            a.add(in.nextInt());
        }
        a.sort(Integer::compareTo);
        int last = a.get(a.size() - 1);
        out.print(String.format("%d ", last - a.get(0)));
        out.print(String.format("%d ", last - a.get(1)));
        out.print(String.format("%d ", last - a.get(2)));
    }
}
