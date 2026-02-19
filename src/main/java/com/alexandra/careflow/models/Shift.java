package com.alexandra.careflow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "shifts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime starTime;

    private LocalDateTime endTime;

    private long totalHours;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User employee;

    @PrePersist
    @PreUpdate
    public void calculateShiftDuration() {
        if (starTime != null && endTime != null) {
            Duration duration = Duration.between(starTime, endTime);
            this.totalHours = duration.toMinutes() / 60 - 0;
        }
        }
}
