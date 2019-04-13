package task;

import java.io.PrintWriter;
import java.util.LinkedList;

public class CServalISkobochnayaPosledovatelnost {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        StringBuilder sb = new StringBuilder(s);
        if (sb.charAt(0) != '?' && sb.charAt(0) != '(') {
            out.println(":(");
            return;
        }
        if (sb.charAt(n - 1) != '?' && sb.charAt(n - 1) != ')') {
            out.println(":(");
            return;
        }
        sb.setCharAt(0, '(');
        sb.setCharAt(n - 1, ')');
        int balance = 0;
        LinkedList<Integer> questions = new LinkedList<>();
        for (int i = 1; i < n - 1; i++) {
            if (sb.charAt(i) == '?') {
                questions.add(i);
            } else if (sb.charAt(i) == ')') {
                balance--;
            } else {
                balance++;
            }
            if (balance < 0) {
                if (questions.isEmpty()) {
                    out.println(":(");
                    return;
                }
                int idx = questions.pollLast();
                sb.setCharAt(idx, '(');
                balance++;
            }
        }
        while (balance > 0) {
            if (questions.isEmpty()) {
                out.println(":(");
                return;
            }
            int idx = questions.pollLast();
            sb.setCharAt(idx, ')');
            balance--;
        }
        balance = 0;
        for (int i = 1; i < n - 1; i++) {
            if (sb.charAt(i) == ')') {
                balance--;
            } else if (sb.charAt(i) == '(') {
                balance++;
            }
            if (balance < 0) {
                out.println(":(");
                return;
            }
        }
        if (balance != 0 ) {
            out.println(":(");
            return;
        }
        if (questions.size() % 2 != 0) {
            out.println(":(");
            return;
        }
        int border = questions.size() / 2;
        while (!questions.isEmpty()) {
            int idx = questions.pollLast();
            if (questions.size() >= border) {
                sb.setCharAt(idx, ')');
            } else {
                sb.setCharAt(idx, '(');
            }
        }
        balance = 0;
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) == ')') {
                balance--;
            } else if (sb.charAt(i) == '(') {
                balance++;
            }
            if (balance < 0) {
                out.println(":(");
                return;
            }
        }
        if (balance != 0 ) {
            out.println(":(");
            return;
        }

        out.println(sb);
    }
}
