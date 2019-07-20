package dogsmanager;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Dog {
    private Long id;

    @Size(min = 3, max = 100)
    private String dogName;
    private float dogHeight;
    private float dogWeight;

    @Past(message = "Date of birth should be set in past")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dogBirth;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public float getDogHeight() {
        return dogHeight;
    }

    public void setDogHeight(float dogHeight) {
        this.dogHeight = dogHeight;
    }

    public float getDogWeight() {
        return dogWeight;
    }

    public void setDogWeight(float dogWeight) {
        this.dogWeight = dogWeight;
    }

    public Date getDogBirth() {
        return dogBirth;
    }

    public void setDogBirth(Date dogBirth) {
        this.dogBirth = dogBirth;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", dogName='" + dogName + '\'' +
                ", dogBirth='" + dogBirth + '\'' +
                ", dogHeight=" + dogHeight +
                ", dogWeight=" + dogWeight +
                '}';
    }
}
