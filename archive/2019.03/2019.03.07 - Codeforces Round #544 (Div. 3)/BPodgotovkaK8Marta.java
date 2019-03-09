package task;



import java.io.PrintWriter;

public class BPodgotovkaK8Marta {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[k];
        for (int i = 0; i < n; i++) {
            a[in.nextInt() % k]++;
        }
        int ans = 0;
        for (int i = 0; i <= k / 2; i++) {
            int h = (k - i) % k;
            if (i == h) {
                ans += (a[i] / 2) * 2;
            } else {
                ans += Math.min(a[i], a[h]) * 2;
            }
        }
        out.println(ans);
    }
}
