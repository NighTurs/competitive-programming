package task;

import java.io.PrintWriter;

public class EFourierDoodles {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append("10011010011010011010");
        }
        for (int i = 20; i < 50; i++) {
            if (i == 49) {
                out.print(sb.charAt(i));
            } else {
                out.println(sb.charAt(i));
            }
        }
    }
}
