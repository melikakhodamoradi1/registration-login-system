package net.guides.springboot.notificationsystem.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.guides.springboot.notificationsystem.model.Notif;
import net.guides.springboot.notificationsystem.service.model.Grade;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
    )

    private List <Role> roles = new ArrayList<>();
    @Enumerated(value = EnumType.STRING)
    private Grade grade;
    @ManyToMany(mappedBy = "users")
    private Set<Notif> notifs = new HashSet<>();

}