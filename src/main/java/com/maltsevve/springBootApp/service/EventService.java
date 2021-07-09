package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Event;

import java.util.List;

public interface EventService extends GenericService<Event, Long>{
    @Override
    void save(Event event);

    @Override
    Event getById(Long id);

    @Override
    List<Event> getAll();

    @Override
    void deleteById(Long id);
}
