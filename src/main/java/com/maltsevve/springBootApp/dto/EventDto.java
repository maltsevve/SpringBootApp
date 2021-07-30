package com.maltsevve.springBootApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maltsevve.springBootApp.model.Event;
import com.maltsevve.springBootApp.model.File;
import com.maltsevve.springBootApp.model.Status;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {
    private Long id;
    private Status status;
    private UserDto userDto;
    private File file;
    private Date created;
    private Date updated;

    public Event toEvent() {
        Event event = new Event();
        event.setId(id);
        event.setStatus(status);
        event.setFile(file);
        event.setCreated(created);
        event.setUpdated(updated);

        return event;
    }

    public static EventDto fromEvent(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setStatus(event.getStatus());
        eventDto.setUserDto(UserDto.fromUser(event.getUser()));
        eventDto.setFile(event.getFile());
        eventDto.setCreated(event.getCreated());
        eventDto.setUpdated(event.getUpdated());

        return eventDto;
    }

    public static List<EventDto> fromEvents(List<Event> events) {
        return events.stream().map(EventDto::fromEvent).collect(Collectors.toList());
    }
}
