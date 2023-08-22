package si.pingisfun.nez.events.title;

import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Objects;

public class TitleEvent extends Event {
    private final String eventTitle;
    private final String eventSubtitle;

    public TitleEvent(String title, String subtitle) {
        this.eventTitle = title;
        this.eventSubtitle = subtitle;
    }

    public String getTitle() {
        return this.eventTitle;
    }

    public String getSubtitle() {
        return this.eventSubtitle;
    }

    public String toString() {
        return this.eventTitle + "\n" + this.eventSubtitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleEvent that = (TitleEvent) o;
        return Objects.equals(eventTitle, that.eventTitle) && Objects.equals(eventSubtitle, that.eventSubtitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventTitle, eventSubtitle);
    }
}
