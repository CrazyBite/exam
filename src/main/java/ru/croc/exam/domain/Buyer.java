package ru.croc.exam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "buyers")
public class Buyer {

    public Buyer(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Min(18)
    @Max(98)
    private Integer age; // 18 > 99

    @Column(nullable = false)
    private String name; // not null
}
