package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Event;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

class EventServiceImplTest {
    @Mock
    private EventRepository eventRepository = Mockito.mock(EventRepository.class);

    @InjectMocks
    private final EventServiceImpl eventService = new EventServiceImpl(eventRepository);

    @Test
    void save() {
        Event event = getEvent();

        when(eventRepository.save(any())).thenReturn(getEvent());

        eventService.save(event);

        Assertions.assertEquals(Status.ACTIVE, event.getStatus());
        Assertions.assertNotNull(event.getId());
        Assertions.assertNotNull(event.getCreated());
        Assertions.assertNotNull(event.getUpdated());

        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void getById() {
        Event event = getEvent();

        when(eventRepository.getById(1L)).thenReturn(event);

        eventService.getById(event.getId());

        Assertions.assertEquals(1L, event.getId());

        verify(eventRepository, times(1)).getById(1L);
    }

    @Test
    void getAll() {
        Event event1 = getEvent();
        Event event2 = getEvent();
        event2.setId(1L);

        List<Event> events = new ArrayList<>();

        events.add(event1);
        events.add(event2);

        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.getAll();

        Assertions.assertEquals(events.size(), result.size());

        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        Event event = getEvent();

        when(eventRepository.getById(1L)).thenReturn(event);

        event = eventService.deleteById(event.getId());

        Assertions.assertEquals(Status.DELETED, event.getStatus());

        verify(eventRepository, times(1)).save(event);
    }

    private Event getEvent() {
        Event event = new Event();
        event.setId(1L);
        event.setStatus(Status.ACTIVE);
        event.setCreated(new Date());
        event.setUpdated(new Date());

        return event;
    }
}