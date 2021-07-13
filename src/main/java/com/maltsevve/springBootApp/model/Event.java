package com.maltsevve.springBootApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Event extends BaseEntity{
    @Column(name = "time")
    private Date eventTime;
    @Column(name = "status")
    Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;
    @OneToOne
    @JoinColumn(name = "fileid")
    private File file;
}
