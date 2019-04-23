package task;

public class EllysAndXor {

    int max = 0;

    public int getMax(int[] numbers) {
        max = 0;
        doit(1, numbers, numbers[0]);
        return max;
    }

    public void doit(int pos, int[] a, int cur) {
        if (pos >= a.length) {
            max = Math.max(cur, max);
            return;
        }

        doit(pos + 1, a, cur & a[pos]);
        doit(pos + 1, a, cur ^ a[pos]);
    }
}
