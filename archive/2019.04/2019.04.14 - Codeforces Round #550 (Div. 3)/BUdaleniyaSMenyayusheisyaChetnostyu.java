package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BUdaleniyaSMenyayusheisyaChetnostyu {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int d = in.nextInt();
            if (d % 2 == 0) {
                even.add(d);
            } else {
                odd.add(d);
            }
        }
        odd.sort(Integer::compareTo);
        even.sort(Integer::compareTo);

        int diff = Math.abs(odd.size() - even.size());

        List<Integer> z;

        if (odd.size() < even.size()) {
            z = even;
        } else if (even.size() < odd.size()) {
            z = odd;
        } else {
            out.println(0);
            return;
        }

        int sum = 0;
        for (int i = 0; i < diff - 1; i++) {
            sum += z.get(i);
        }
        out.println(sum);
    }
}
