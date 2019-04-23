package task;

import java.io.PrintWriter;

public class AOnTheWay {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        if ((a <= c && c <= b) || (b <= c && a >= c)) {
            out.println("Yes");
        } else {
            out.println("No");
        }
    }
}
