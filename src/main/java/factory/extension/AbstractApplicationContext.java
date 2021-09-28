package factory.extension;

import factory.io.DefaultResourceLoader;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() {

    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableL();
}
