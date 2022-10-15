package journal.domain;

import java.util.Objects;

public class Hotkeys {
    private String name;

    public Hotkeys() {
    }

    public Hotkeys(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotkeys hotkeys = (Hotkeys) o;
        return Objects.equals(name, hotkeys.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
