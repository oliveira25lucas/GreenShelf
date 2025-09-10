package br.com.oliveira25lucas.greenShelf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Person")
@Getter
@Setter
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(name = "birth_date", nullable = false, length = 80)
    private Date birthDate;

    @Column(nullable = false, length = 250)
    private String address;

    @Column(nullable = false, length = 20)
    private String gender;

    @Column(length = 40)
    private String phoneNumber;

    public Person() {}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getBirthDate(), person.getBirthDate()) && Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getGender(), person.getGender()) && Objects.equals(getPhoneNumber(), person.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getBirthDate(), getAddress(), getGender(), getPhoneNumber());
    }
}