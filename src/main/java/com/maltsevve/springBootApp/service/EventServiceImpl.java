package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Event;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        if (event.getCreated() == null) {
            event.setCreated(new Date());
        }

        event.setUpdated(new Date());
        event.setStatus(Status.ACTIVE);

        log.info("IN EventServiceImpl save {}", event);

        return eventRepository.save(event);
    }

    @Override
    public Event getById(Long id) {
        log.info("IN EventServiceImpl getById {}", id);
        return eventRepository.getById(id);
    }

    @Override
    public List<Event> getAll() {
        log.info("IN EventServiceImpl getAll");
        return eventRepository.findAll();
    }

    @Override
    public Event deleteById(Long id) {
        Event event = getById(id);
        event.setStatus(Status.DELETED);
        event.setUpdated(new Date());
        eventRepository.save(event);

        log.info("IN EventServiceImpl delete {}", id);
//        eventRepository.deleteById(id);
        return event;
    }
}
