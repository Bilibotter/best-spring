package factory.mine;

import java.util.EventListener;

public interface DoorListener extends EventListener {
    void doorEvent(DoorEvent doorEvent);
}
