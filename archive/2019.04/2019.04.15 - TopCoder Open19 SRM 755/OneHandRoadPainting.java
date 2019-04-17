package task;

public class OneHandRoadPainting {
    public long fastest(int[] dStart, int[] dEnd, int paintPerBrush) {
        int n = dStart.length;
        long lastUnpainted = dEnd[n - 1];
        int lastIdx = n - 1;
        long ans = 0;

        while (true) {
            ans += lastUnpainted * 2;
            int brush = paintPerBrush;
            while (lastIdx >= 0 && brush >= lastUnpainted - dStart[lastIdx]) {
                brush -= lastUnpainted- dStart[lastIdx];
                lastIdx--;
                if (lastIdx >= 0) {
                    lastUnpainted = dEnd[lastIdx];
                }
            }
            if (lastIdx < 0) {
                return ans;
            }
            lastUnpainted -= brush;
        }
    }
}
