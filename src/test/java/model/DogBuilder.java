package model;

import dogsmanager.Dog;
import java.util.Date;

public class DogBuilder {
    private Dog model;

    public DogBuilder() {
        model = new Dog();
    }

    public  DogBuilder id(Long id) {
        model.setId(id);
        return this;
    }

    public DogBuilder dogName(String name) {
        model.setDogName(name);
        return this;
    }

    public DogBuilder dogWeight(float weight) {
        model.setDogWeight(weight);
        return this;
    }

    public DogBuilder dogHeight(float height) {
        model.setDogHeight(height);
        return this;
    }

    public DogBuilder dogBirth(Date date) {
        model.setDogBirth(date);
        return this;
    }

    public Dog build() {
        return model;
    }

}
