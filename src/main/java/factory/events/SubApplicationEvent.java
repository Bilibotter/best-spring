package factory.events;

// @Deprecated
public class SubApplicationEvent extends ApplicationEvent {
    public SubApplicationEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }
}
