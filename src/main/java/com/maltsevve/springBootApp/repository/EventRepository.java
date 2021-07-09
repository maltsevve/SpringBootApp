package com.maltsevve.springBootApp.repository;

import com.maltsevve.springBootApp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
