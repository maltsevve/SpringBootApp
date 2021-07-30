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
public class UserEventDto {
    private Long id;
    private Status status;
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

    public static UserEventDto fromEvent(Event event) {
        UserEventDto eventDto = new UserEventDto();
        eventDto.setId(event.getId());
        eventDto.setStatus(event.getStatus());
        eventDto.setFile(event.getFile());
        eventDto.setCreated(event.getCreated());
        eventDto.setUpdated(event.getUpdated());

        return eventDto;
    }

    public static List<UserEventDto> fromEvents(List<Event> events) {
        return events.stream().map(UserEventDto::fromEvent).collect(Collectors.toList());
    }
}
