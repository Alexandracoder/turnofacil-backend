package com.alexandra.turnofacil.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "shifts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double totalHours;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User employee;

    @PrePersist
    @PreUpdate
    public void calculateShiftDuration() {
        if (startTime != null && endTime != null) {
            Duration duration = Duration.between(startTime, endTime);
            this.totalHours = duration.toMinutes() / 60.0;
        }
    }
}
