package task;

import java.io.PrintWriter;
import java.util.ArrayList;

public class DDosugVShkole41 {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.next();
        boolean[] a = new boolean[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) != 'L';
        }
        ArrayList<ArrayList<Integer>> steps = new ArrayList<>();
        int overall = 0;
        while (true) {
            ArrayList<Integer> step = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                if (diff(a[i], a[i + 1])) {
                    step.add(i);
                    overall++;
                }
            }
            for (Integer i : step) {
                a[i] = !a[i];
                a[i + 1] = !a[i + 1];
            }
            if (step.isEmpty()) {
                break;
            }
            steps.add(step);
        }
        if (k > overall || steps.size() > k) {
            out.println(-1);
            return;
        }

        int excess = k - steps.size();
        int lastI = 0;
        int lastH = -1;
        ArrayList<Integer> step = null;
        if (excess > 0) {
            label:
            for (int i = 0; i < steps.size(); i++) {
                step = steps.get(i);
                for (int h = 0; h < step.size(); h++) {
                    out.println("1 " + (step.get(h) + 1));
                    if (h != step.size() - 1) {
                        excess--;
                    }
                    if (excess == 0) {
                        lastI = i;
                        lastH = h;
                        break label;
                    }
                }
            }
        }

        step = steps.get(lastI);
        if (lastH != step.size()) {
            out.print(step.size() - lastH - 1);
            for (int h = lastH + 1; h < step.size(); h++) {
                out.print(' ');
                out.print(step.get(h) + 1);
            }
            out.println();
        }
        for (int i = lastI + 1; i < steps.size(); i++) {
            step = steps.get(i);
            out.print(step.size());
            for (int h = 0; h < step.size(); h++) {
                out.print(' ');
                out.print(step.get(h) + 1);
            }
            out.println();
        }
    }

    boolean diff(boolean a, boolean b) {
        return a && !b;
    }
}
