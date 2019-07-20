package dogsmanager.service;

import config.TestContext;
import dogsmanager.config.WebAppContext;
import dogsmanager.dao.DogDao;
import dogsmanager.Dog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class DocServiceTest {
    @Mock
    private DogDao daoMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddDog() {
        daoMock.addDog(Mockito.any(Dog.class));
        Mockito.verify(daoMock, Mockito.times(1)).addDog(Mockito.any(Dog.class));
    }

    @Test
    public void testUpdateDog() {
        daoMock.updateDog(Mockito.any(Dog.class));
        Mockito.verify(daoMock, Mockito.times(1)).updateDog(Mockito.any(Dog.class));
    }

    @Test
    public void testRemoveDogById() {
        daoMock.removeDogById(Mockito.anyLong());
        Mockito.verify(daoMock, Mockito.times(1)).removeDogById(Mockito.anyLong());
    }

    @Test
    public void testGetDogById() {
        daoMock.getDogById(Mockito.anyLong());
        Mockito.verify(daoMock, Mockito.times(1)).getDogById(Mockito.anyLong());
    }
}
