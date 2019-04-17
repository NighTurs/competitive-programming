package task;

public class OneHandRoadPainting {

    public long fastest(int[] dStart, int[] dEnd, int paintPerBrush) {
        int lineIdx = dEnd.length - 1;
        long lastUnpainted = dEnd[dEnd.length - 1];
        long ans = 0;
        while (true) {
            long st = dStart[lineIdx];
            long times = (lastUnpainted - st) / paintPerBrush;
            if (times > 0) {
                long lastGoPos = lastUnpainted - (times - 1) * paintPerBrush;
                ans += times * lastGoPos + (times - 1) * times / 2 * paintPerBrush;
                lastUnpainted = lastGoPos - paintPerBrush;
            }
            if (lastUnpainted == st) {
                lineIdx--;
                if (lineIdx >= 0) {
                    st = dStart[lineIdx];
                    lastUnpainted = dEnd[lineIdx];
                } else {
                    return ans * 2;
                }
            }

            ans += lastUnpainted;
            long brush = paintPerBrush;
            while (lastUnpainted - st <= brush) {
                brush -= lastUnpainted - st;
                lineIdx--;
                if (lineIdx >= 0) {
                    st = dStart[lineIdx];
                    lastUnpainted = dEnd[lineIdx];
                } else {
                    return ans * 2;
                }
            }
            lastUnpainted -= brush;
        }
    }
}
