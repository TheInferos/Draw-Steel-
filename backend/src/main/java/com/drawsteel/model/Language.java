package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "languages")
@Data
@EqualsAndHashCode(callSuper = true)
public class Language extends BaseModel {

}
