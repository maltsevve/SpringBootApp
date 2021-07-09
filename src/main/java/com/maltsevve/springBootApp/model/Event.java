package com.maltsevve.springBootApp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class Event extends BaseEntity{
    @Column(name = "event_time")
    private Date eventTime;
    @Column(name = "status")
    Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;
}
