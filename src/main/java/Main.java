import domains.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        EventDto bean = context.getBean(EventDto.class);

        System.out.println(bean.getState()+": "+bean.getField());


    }

}
