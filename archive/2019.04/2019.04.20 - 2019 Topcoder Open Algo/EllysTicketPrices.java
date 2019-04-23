package task;

public class EllysTicketPrices {
    public double getPrice(int N, int[] C, int target) {
        long t1 = 1;
        long t2 = 10000 * 100 * 200;

        while (t1 < t2) {
            long m = (t1 + t2) / 2;
            long st = m;
            long sum = st;
            for (int i = 0; i < N - 1; i++) {
                st = Math.round(st * (double)(C[i] + 100) / 100);
                sum += st;
            }
            long end = sum / N;
            if (end < target * 100) {
                t1 = m + 1;
            } else {
                t2 = m;
            }
        }
        for (long i = t1 - 300; i <= t1 + 300; i++) {
            if (i <= 0) {
                continue;
            }
            long st = i;
            long sum = i;
            for (int h = 0; h < N - 1; h++) {
                st = Math.round(st * (double) (C[h] + 100) / 100);
                sum += st;
            }
            long end = Math.round((double) sum / N);
            if (end == target * 100) {
                return (double)i / 100;
            }
        }
        return 0;
    }
}
