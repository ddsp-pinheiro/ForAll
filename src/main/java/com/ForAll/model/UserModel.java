package com.ForAll.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class UserModel {

    @Id
    @Column(name = "idt_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "des_name")
    @NotNull
    private String name;

    @Column(name = "des_email")
    @NotNull
    private String email;

    @Column(name = "num_ddd", length = 3)
    private String ddd;
    @Column(name = "num_tell", length = 9)
    private String tell;
}
