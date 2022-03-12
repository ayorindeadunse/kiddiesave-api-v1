package com.kiddiesave.kiddiesave.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @Id // Marks the Id field as primary key and therefore the identifier of this entity.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;

    //update with the rest of user properties.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
