package config;

import dogsmanager.DogService;
import dogsmanager.dao.DogDao;
import dogsmanager.dao.DogDaoImplementation;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {
    @Bean
    public DogService dogServiceService() {
        return Mockito.mock(DogService.class);
    }

    @Bean
    public DogDao dogDao() {
        return  Mockito.mock(DogDaoImplementation.class);
    }
}
