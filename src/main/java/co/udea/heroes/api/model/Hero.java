package co.udea.heroes.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "heroes")
public class Hero {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "nombre")
    private String name;

    public Hero() {
    }

    public Hero(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
