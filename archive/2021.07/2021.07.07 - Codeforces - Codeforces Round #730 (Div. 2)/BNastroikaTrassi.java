package task;

import java.io.PrintWriter;

public class BNastroikaTrassi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += in.nextInt();
        }
        long res = sum % n;
        out.println(res * (n - res));
    }
}
