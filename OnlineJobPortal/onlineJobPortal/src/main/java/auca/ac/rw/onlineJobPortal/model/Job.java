
package auca.ac.rw.onlineJobPortal.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import auca.ac.rw.onlineJobPortal.enums.EmploymentType;
import auca.ac.rw.onlineJobPortal.enums.LocationEnum;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue
    @Column(name = "job_id", nullable = false, updatable = false)
    private UUID jobId;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type", nullable = false)
    private EmploymentType employmentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "location", nullable = false)
    private LocationEnum location;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "requirements", nullable = false)
    private String requirements;

    @Column(name = "min_salary")
    private Double minSalary;

    @Column(name = "max_salary")
    private Double maxSalary;

    @Column(name = "posted_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date postedDate;

    @Column(name = "application_deadline", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date applicationDeadline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recruiter_id")
    private User recruiter;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "job_category",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @JsonIgnore
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Application> applications;

    public Job() {}

    public Job(UUID jobId, String title, EmploymentType employmentType, LocationEnum location, String description, 
               String requirements, Double minSalary, Double maxSalary, Date postedDate,
               Date applicationDeadline, User recruiter, List<Category> categories, List<Application> applications) {
        this.jobId = jobId;
        this.title = title;
        this.employmentType = employmentType;
        this.location = location;
        this.description = description;
        this.requirements = requirements;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.postedDate = postedDate;
        this.applicationDeadline = applicationDeadline;
        this.recruiter = recruiter;
        this.categories = categories;
        this.applications = applications;
    }

    public UUID getJobId() {
        return jobId;
    }

    public void setJobId(UUID jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(Date applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public User getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(User recruiter) {
        this.recruiter = recruiter;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public LocationEnum getLocation() {
        return location;
    }

    public void setLocation(LocationEnum location) {
        this.location = location;
    }
}



