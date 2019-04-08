package task;

import java.io.PrintWriter;

public class DMinimalnayaTriangulyaciya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long ans = 0;
        for (int i = 2; i < n; i++) {
            ans += i * (i + 1);
        }
        out.println(ans);
    }

}
