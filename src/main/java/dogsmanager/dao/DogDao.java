package dogsmanager.dao;

        import dogsmanager.Dog;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface DogDao {
    void addDog(Dog dog);
    void updateDog(Dog dog);
    void removeDogById(Long id);
    Dog getDogById(Long id);
    List<Dog> listDogs();
}
