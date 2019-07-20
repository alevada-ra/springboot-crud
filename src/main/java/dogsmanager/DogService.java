package dogsmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DogService {
    @Autowired
    private DogsRepository repository;

    public void addDog(Dog dog) {
        repository.save(dog);
    }

    public void updateDog(Dog dog) {
        repository.save(dog);
    }

    public void removeDogById(long id) {
        repository.deleteById(id);
    }

    public Dog getDogById(long id) {
        return repository.findById(id).get();
    }

    public List<Dog> listDogs() {
        return repository.findAll();
    }
}
