package dogsmanager.dao;

import dogsmanager.Dog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogDaoImplementation implements DogDao {
    private static final Logger logger = LoggerFactory.getLogger(DogDaoImplementation.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addDog(Dog dog) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        currentSession.persist(dog);

        logger.info("Dog was sucesfully added");
    }

    @Override
    public void updateDog(Dog dog) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        currentSession.update(dog);

        logger.info("Dog was sucesfully updated");
    }

    @Override
    public void removeDogById(Long id) {
        Dog dog = new Dog();
        Session currentSession = this.sessionFactory.getCurrentSession();
        dog.setId(id);

        currentSession.delete(dog);
        logger.info("Dog was sucesfully removed");
    }

    @Override
    public Dog getDogById(Long id) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        Dog dog = currentSession.load(Dog.class, new Long(id));

        logger.info("Dog was sucesfully loaded: " + dog);

        return dog;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Dog> listDogs() {
        Session currentSession = this.sessionFactory.getCurrentSession();
        List<Dog> dogsList = currentSession.createQuery("from dog").list();

        for(Dog dog : dogsList) {
            logger.info("Dogs list: " + dog);
        }

        return dogsList;
    }
}
