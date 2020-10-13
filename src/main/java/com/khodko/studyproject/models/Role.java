package com.khodko.studyproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "role")
    private List<User> user;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(name, role.name) &&
                Objects.equals(user, role.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user);
    }

    @Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
    
    
}
