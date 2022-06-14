package ru.dina;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Data
//object ready for storage in a JPA-based data store
@Entity
public class Person {
    //the primary key and automatically populated by the JPA provider
    private @Id
    @GeneratedValue Long id;
    private String name;
    private String adress;

    public Person() {
    }

    //a custom constructor is created when we need to create a new instance,
    //but don’t yet have an id.
    public Person(String name, String adress) {

        this.name = name;
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Person))
            return false;
        Person person = (Person) o;
        return Objects.equals(this.id, person.id) && Objects.equals(this.name, person.name)
                && Objects.equals(this.adress, person.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.adress);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + this.id + ", name='" + this.name + '\'' + ", adress='" + this.adress + '\'' + '}';
    }
}
