package factory.support;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        for (int i=0; i < propertyValueList.size(); i++) {
            PropertyValue currPropertyValue = propertyValueList.get(i);
            if (currPropertyValue.getName().equals(pv.getName())) {
                // 新PropertyValue覆盖旧PropertyValue
                propertyValueList.set(i, pv);
                return;
            }
        }
        propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        return propertyValueList.stream()
                .filter(it->propertyName.equals(it.getName()))
                .findAny()
                .orElse(null);
    }
}
