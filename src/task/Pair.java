package task;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(fs, pair.fs) && Objects.equals(sc, pair.sc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fs, sc);
    }
}
