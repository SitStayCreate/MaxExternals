package SSCMusic.Modes;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Mode {
    private final Map<Integer, Integer> intervals;

    protected Mode() {
        this.intervals = new HashMap<>(7);
        assign();
    }

    abstract void assign();

    public Map<Integer, Integer> getIntervals() {
        return intervals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mode)) return false;
        Mode mode = (Mode) o;
        return getIntervals().equals(mode.getIntervals());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIntervals());
    }
}
