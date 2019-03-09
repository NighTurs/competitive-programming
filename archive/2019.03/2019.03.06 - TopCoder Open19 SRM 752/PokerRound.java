package task;

public class PokerRound {
    public int amount(int T) {
        if ((10000 - T) % 8 == 0) {
            return 10000 - (10000 - T) / 8;
        } else {
            return -1;
        }
    }
}
