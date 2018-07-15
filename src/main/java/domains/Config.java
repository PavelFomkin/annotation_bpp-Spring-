package domains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "domains")
public class Config {
    @Bean
    EventDto eventDto(){
        return EventDto.builder()
                .state(EventState.TEST)
                .drafted("")
                .proposed("")
                .field("field")
                .test("")
                .build();
    }
}
