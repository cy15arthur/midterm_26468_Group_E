package auca.ac.rw.onlineJobPortal.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue
    @Column(name = "person_id", nullable = false, updatable = false)
    private UUID personId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    public Person() {
    }

    public UUID getPersonId() {
        return personId;
    }

    public Person(UUID personId, String firstName, String lastName, Date dateOfBirth, String phoneNumber) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
}
