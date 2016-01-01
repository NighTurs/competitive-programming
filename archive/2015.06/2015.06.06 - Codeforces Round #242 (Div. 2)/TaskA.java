package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        in.next();
        String str = in.next();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'x') {
                count++;
            } else {
                count--;
            }
        }
        out.println(Math.abs(count / 2));
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'x' && count > 0) {
                count -= 2;
                out.print('X');
            } else if (str.charAt(i) == 'X' && count < 0) {
                count += 2;
                out.print('x');
            } else {
                out.print(str.charAt(i));
            }
        }
    }
}
