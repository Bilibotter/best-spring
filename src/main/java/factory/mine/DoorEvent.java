package factory.mine;

import java.util.EventListener;
import java.util.EventObject;

public class DoorEvent extends EventObject {
    private boolean isOpen = false;

    public DoorEvent(Object source) {
        super(source);
    }

    public DoorEvent(Object source, boolean isOpen) {
        super(source);
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
