package factory.mine;

import cn.hutool.core.util.ClassUtil;
import factory.annotation.Component;
import factory.annotation.Controller;

import java.util.Set;

@Controller
public class TempController {
    public static void main(String[] args) {
        Set<Class<?>> classSet = ClassUtil.scanPackageByAnnotation("factory", Component.class);
        System.out.println(TempController.class.isAnnotationPresent(Component.class));
        return;
    }
}
