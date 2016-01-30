package task;

public class Pair<T, K> {
    T fs;
    K sc;

    public Pair(T fs, K sc) {
        this.fs = fs;
        this.sc = sc;
    }

    public static <T, K> Pair<T, K> of(T fs, K sc) {
        return new Pair<>(fs, sc);
    }
}
