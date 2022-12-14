package hansol9.restfulapi.events;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import java.net.URI;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //    @PostMapping("/api/events")
    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event) {

        // linkTo for method on
        // URI createdUri = linkTo(methodOn(EventController.class).createEvent(null)).slash("{id}").toUri();

        // linkTo for class
        Event newEvent = this.eventRepository.save(event);
        URI createdUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
//        event.setId(10);
        return ResponseEntity.created(createdUri).body(event);
    }
}
