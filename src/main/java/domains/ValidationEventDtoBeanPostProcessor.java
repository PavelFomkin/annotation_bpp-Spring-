package domains;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

@Component
public class ValidationEventDtoBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if (!(o instanceof EventDto)) {
            return o;
        }
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation instanceof Mandatory.List) {
                    Mandatory[] values = ((Mandatory.List) annotation).values();
                    EventState realState = ((EventDto) o).getState();
                    // if one of required states in Annotation equals to current state - the field will check to null.
                    boolean result = Arrays.stream(values)
                            .map(Mandatory::state)
                            .anyMatch(annState -> annState.equals(realState));
                    if (result) {
                        field.setAccessible(true);
                        String value = (String) ReflectionUtils.getField(field, o);
                        if (value == null) {
                            throw new IllegalArgumentException("The field " + field.getName() + " is mandatory.");
                        }
                    }
                }
            }
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}
