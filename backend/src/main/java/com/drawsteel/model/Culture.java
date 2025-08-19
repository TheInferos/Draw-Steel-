package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.drawsteel.model.enums.Skill;

@Entity
@Table(name = "cultures")
@Data
@EqualsAndHashCode(callSuper = true)
public class Culture extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill")
    private Skill skill;
}
