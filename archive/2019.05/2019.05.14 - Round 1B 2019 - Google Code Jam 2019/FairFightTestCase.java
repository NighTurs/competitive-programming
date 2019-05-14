package task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

public class FairFightTestCase {
    @TestCase
    public Collection<Test> createTests() {

        List<Test> tests = new ArrayList<>();
        Random rnd = new Random(1234);

        for (int tt = 0; tt < 100; tt++) {
            StringBuilder sb = new StringBuilder();
            sb.append("1\n");
            int n = 5;
            int k = rnd.nextInt(10);
            sb.append(String.format("%d %d\n", n, k));
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = rnd.nextInt(10);
                sb.append(a[i]).append(' ');
            }
            sb.append('\n');
            for (int i = 0; i < n; i++) {
                b[i] = rnd.nextInt(10);
                sb.append(b[i]).append(' ');
            }
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int h = i; h < n; h++) {
                    int maxA = Integer.MIN_VALUE;
                    int maxB = Integer.MIN_VALUE;
                    for (int j = i; j <= h; j++) {
                        maxA = Math.max(a[j], maxA);
                        maxB = Math.max(b[j], maxB);
                    }
                    if (Math.abs(maxA - maxB) <= k) {
                        ans++;
                    }
                }
            }
            tests.add(new Test(sb.toString(), String.format("Case #%d: %d", 1, ans)));
        }
        return tests;
    }
}
