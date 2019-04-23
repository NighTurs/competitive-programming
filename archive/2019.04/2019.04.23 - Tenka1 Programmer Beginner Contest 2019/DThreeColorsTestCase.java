package task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

public class DThreeColorsTestCase {

    @TestCase
    public Collection<Test> createTests() {
        int n = 6;
        Random rand = new Random(123);

        List<Test> tests = new ArrayList<>();
        for (int t = 0; t < 1000; t++) {
            int[] a = new int[n];

            StringBuilder sb = new StringBuilder();
            sb.append(n);
            sb.append('\n');

            for (int i = 0; i < n; i++) {
                a[i] = 1 + rand.nextInt(20);
                sb.append(a[i]);
                sb.append('\n');
            }

            Test test = new Test(sb.toString(), Long.toString(doit(a, 0, 0, 0, 0)));
            tests.add(test);
        }
        return tests;
    }

    private long doit(int[] a, int pos, int r, int g, int b) {
        if (pos >= a.length) {
            if (r < g + b && g < r + b && b < r + g) {
                return 1;
            } else {
                return 0;
            }
        }
        long sum = 0;
        sum += doit(a, pos + 1, r + a[pos], g, b);
        sum += doit(a, pos + 1, r, g + a[pos], b);
        sum += doit(a, pos + 1, r, g, b + a[pos]);
        return sum;
    }
}
