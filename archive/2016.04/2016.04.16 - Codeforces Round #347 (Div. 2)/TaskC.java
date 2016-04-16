package task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

        Map<String, String> s = new HashMap<>();
        int i = 1989;
        while (i <= 4000) {
            String sub = "";
            String str = Integer.toString(i);
            for (int h = str.length() - 1; h >= 0; h--) {
                sub = str.substring(h);
                if (!s.containsKey(sub)) {
                    s.put(sub, str);
                    break;
                }
            }
            i++;
        }

        int n = in.nextInt();
        for (int ii = 0; ii < n; ii++) {
            String val = in.next().split("'")[1];
            if (s.containsKey(val)) {
                out.println(s.get(val));
            } else {
                StringBuilder left = new StringBuilder();
                for (int h = 0; h < val.length(); h++) {
                    left.append("0");
                }
                StringBuilder right = new StringBuilder("3098");
                while (right.length() < val.length()) {
                    right.insert(0, "1");
                }
                if (left.toString().compareTo(val) <= 0 && right.toString().compareTo(val) >= 0) {
                    out.println("1" + val);
                } else {
                    out.println(val);
                }
            }
        }
    }
}
