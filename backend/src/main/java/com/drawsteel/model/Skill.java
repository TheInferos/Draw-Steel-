package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "skills")
@Data
@EqualsAndHashCode(callSuper = true)
public class Skill extends BaseModel {
    
}
