package domains;

import lombok.*;
import org.springframework.stereotype.Component;

import static domains.EventState.DRAFTED;
import static domains.EventState.PROPOSED;
import static domains.EventState.TEST;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EventDto {

    private EventState state;

    @Mandatory.List(values = {@Mandatory(state = PROPOSED)})
    private String proposed;

    @Mandatory.List(values = {@Mandatory(state = PROPOSED), @Mandatory(state = DRAFTED)})
    private String drafted;

    @Mandatory.List(values = {@Mandatory(state = TEST)})
    private String test;

    private String field;
}
