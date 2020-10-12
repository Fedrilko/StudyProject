package com.khodko.studyproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "role")
    private List<User> user;

    public Role(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
    
    
}
