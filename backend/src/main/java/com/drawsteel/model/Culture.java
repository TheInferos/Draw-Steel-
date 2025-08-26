package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.drawsteel.model.enums.Skill;
import com.drawsteel.model.enums.SkillGroup;

@Entity
@Table(name = "cultures")
@Data
@EqualsAndHashCode(callSuper = true)
public class Culture extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id", nullable = true)
    private Language language;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "culture_skill_groups", joinColumns = @JoinColumn(name = "culture_id"))
    @Column(name = "skill_group")
    private List<SkillGroup> skillGroups;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill")
    private Skill quickBuild;
}
