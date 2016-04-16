package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int pluses = 1;
        int minuses = 0;

        List<String> signs = new ArrayList<>();
        int res = 0;

        while (true) {
            String str = null;
            try {
                str = in.next();
            } catch (NullPointerException e) {
                break;
            }
            switch (str) {
                case "?":
                    break;
                case "+":
                    pluses++;
                    signs.add("+");
                    break;
                case "-":
                    minuses++;
                    signs.add("-");
                    break;
                case "=":
                    signs.add("=");
                    break;
                default:
                    res = Integer.valueOf(str);
            }
        }

        int bank = res + minuses;
        int step = bank / pluses;
        int firstStep = step + bank % pluses;

        if (firstStep > res || step < 1) {
            out.print("Impossible");
            return;
        } else {
            out.println("Possible");
        }

        out.print(firstStep);
        out.print(" ");
        for (int i = 0; i < signs.size() - 1; i++) {
            if (signs.get(i).equals("+")) {
                out.print("+ " + step + " ");
            } else {
                out.print("- 1 ");
            }
        }
        out.print("= " + res);
    }
}
