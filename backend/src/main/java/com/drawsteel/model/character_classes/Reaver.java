package com.drawsteel.model.character_classes;

import com.drawsteel.model.enums.Skill;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Table(name = "reaver")
@Data
@EqualsAndHashCode(callSuper = true)
public class Reaver extends CharacterSubClass {
    
    public Reaver() {
        super();
        this.name = "Reaver";
        this.description = "You channel your ferocity into instinct and cunning, challenging the order of civilization.";
        this.subClassSkills = List.of(Skill.HIDE);
    }
}
