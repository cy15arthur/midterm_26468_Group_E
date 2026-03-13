package auca.ac.rw.onlineJobPortal.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "job_seeker_profile")
public class JobSeekerProfile {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "resume", nullable = false)
    private String resume;

    @Column(name = "skills", nullable = false)
    private String skills;

    @Column(name = "photo")
    private String photo;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "person_id")
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }



    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    

    
}

