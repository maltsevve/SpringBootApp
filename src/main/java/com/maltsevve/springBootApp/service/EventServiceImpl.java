package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Event;
import com.maltsevve.springBootApp.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public void save(Event event) {
        log.info("IN EventServiceImpl save {}", event);
        event.setEventTime(new Date());
        eventRepository.save(event);
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
    public void deleteById(Long id) {
        log.info("IN EventServiceImpl delete {}", id);
        eventRepository.deleteById(id);
    }
}
