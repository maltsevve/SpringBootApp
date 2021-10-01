package com.maltsevve.springBootApp.rest.events;

import com.maltsevve.springBootApp.dto.EventDto;
import com.maltsevve.springBootApp.model.Event;
import com.maltsevve.springBootApp.service.EventService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/events")
@RequiredArgsConstructor
@Api(tags = { "Events" })
public class EventRestControllerV1 {
    private final EventService eventService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDto> getEvent(@PathVariable("id") Long eventId) {
        if (eventId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Event event = this.eventService.getById(eventId);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(EventDto.fromEvent(event), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<Event> events = this.eventService.getAll();

        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(EventDto.fromEvents(events), HttpStatus.OK);
    }
}
