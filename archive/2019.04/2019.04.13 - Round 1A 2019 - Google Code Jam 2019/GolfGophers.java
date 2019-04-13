package task;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GolfGophers {
    int n, m;
    public static final int NUM = 18;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        if (testNumber == 1) {
            n = in.nextInt();
            m = in.nextInt();
        }

        List<Integer> primes = Arrays.asList(4, 3, 5, 7, 11, 13, 17);
        int[] nums = new int[m + 1];

        for (Integer prime : primes) {
            StringBuilder sb = new StringBuilder();
            for (int h = 0; h < NUM; h++) {
                sb.append(prime);
                sb.append(' ');
            }
            out.println(sb);
            out.flush();

            int sum = 0;
            for (int h = 0; h < NUM; h++) {
                sum += in.nextInt();
            }
            sum %= prime;

            while (sum <= m) {
                nums[sum]++;
                sum += prime;
            }
        }
        for (int i = 0; i <= m; i++) {
            if (nums[i] == primes.size()) {
                out.println(i);
                out.flush();
                in.nextInt();
                return;
            }
        }
    }
}
