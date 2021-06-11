package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BPereuporyadochenieMassiva {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int cur = in.nextInt();
            if (cur % 2 == 0) {
                left.add(cur);
            } else {
                right.add(cur);
            }
        }
        left.addAll(right);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int h = i + 1; h < n; h++) {

                if (Euclid.gcd(left.get(i), 2 * left.get(h)) > 1) {
                    ans++;
                }
            }
        }
        out.println(ans);
    }
}
