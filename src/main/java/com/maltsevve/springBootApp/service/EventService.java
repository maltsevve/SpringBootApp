package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Event;

import java.util.List;

public interface EventService {
    Event save(Event event);

    Event getById(Long id);

    List<Event> getAll();

    Event deleteById(Long id);
}
