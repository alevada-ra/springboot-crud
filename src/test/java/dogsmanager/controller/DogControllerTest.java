package dogsmanager.controller;

import config.TestContext;
import dogsmanager.DogController;
import dogsmanager.dao.DogDao;
import model.DogBuilder;
import dogsmanager.config.WebAppContext;
import dogsmanager.Dog;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration

@RunWith(Parameterized.class)
public class DogControllerTest {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private DogDao dogServiceMock;

    @InjectMocks
    @Autowired
    private DogController dogController;

    @Parameterized.Parameters
    public static Collection<Object[]> dataFirst() {
        return Arrays.asList(new Object[][]{
                {0, 1, 0},
                {1, 0, 1}
        });
    }

    private Dog dog;
    private Long id;
    private int addTimes;
    private int updateTimes;
    private List<Dog> dogList;

    public DogControllerTest(Long id, int addTimes, int updateTimes) {
        this.id = id;
        this.addTimes = addTimes;
        this.updateTimes = updateTimes;
        this.dog = new DogBuilder().id(id).build();
        this.dogList = Collections.singletonList(dog);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Mockito.when(dogServiceMock.getDogById(id)).thenReturn(dog);
        Mockito.when(dogServiceMock.listDogs()).thenReturn(dogList);
    }

    @Test
    public void testShowDogPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("default"));
    }

//    @Test
//    public void testAddDog() {
//        dogController.addDog(dog);
//        Mockito.verify(dogServiceMock, Mockito.times(addTimes)).addDog(dog);
//        Mockito.verify(dogServiceMock, Mockito.times(updateTimes)).updateDog(dog);
//    }
//
//    @Test
//    public void testModifyDog() {
//        Model model =  Mockito.mock(Model.class);
//        dogController.modifyDog(id);
//        Mockito.verify(dogServiceMock, Mockito.times(1)).getDogById(id);
//        Mockito.verify(dogServiceMock, Mockito.times(1)).listDogs();
//        Mockito.verify(model, Mockito.times(1)).addAttribute("dog", dog);
//        Mockito.verify(model, Mockito.times(1)).addAttribute("listDogs", dogList);
//    }

    @Test
    public void testRemoveDog() {
        dogController.removeDog(Mockito.anyInt());
        Mockito.verify(dogServiceMock, Mockito.times(1)).removeDogById(Mockito.anyLong());
    }

    @Test
    public void testListOfDogs() {
        Model model = Mockito.mock(Model.class);
        dogController.listOfDogs(model);
        Mockito.verify(dogServiceMock, Mockito.times(1)).listDogs();
        Mockito.verify(model, Mockito.times(1)).addAttribute("dog", new Dog());
        Mockito.verify(model, Mockito.times(1)).addAttribute("listOfDogs", dogList);
    }
}


