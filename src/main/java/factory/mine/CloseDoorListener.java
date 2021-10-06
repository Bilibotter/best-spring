package factory.mine;

public class CloseDoorListener implements DoorListener {
    @Override
    public void doorEvent(DoorEvent doorEvent) {
        if (doorEvent.isOpen()) {
            doorEvent.setOpen(false);
            System.out.println("Close the door");
        }
    }
}
