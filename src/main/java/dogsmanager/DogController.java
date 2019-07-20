package dogsmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DogController {
    @Autowired
    private DogService dogService;

    @RequestMapping("/") //value = "index", method = RequestMethod.GET
    public String listOfDogs(Model model) {
        List<Dog> listOfDogs = dogService.listDogs();
        model.addAttribute("listOfDogs", listOfDogs);

        return "index";
    }

    @RequestMapping(value = "/add")  //, method = RequestMethod.POST
    public String addDog(Model model) {
        Dog dog = new Dog();
        model.addAttribute("dog", dog);

        return "add_dog";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewDog(@ModelAttribute("dog") Dog dog) {
        dogService.addDog(dog);

        return "redirect:/";
    }

    @RequestMapping("edit/{id}")
    public ModelAndView modifyDog(@PathVariable(name = "id") int id) {
        ModelAndView model = new ModelAndView("edit_dog");
        Dog dog = dogService.getDogById(id);
        model.addObject("dog", dog);

        return model;
    }

    @RequestMapping("/remove/{id}")
    public String removeDog(@PathVariable(name = "id") int id) {
        dogService.removeDogById(id);

        return "redirect:/";
    }
}
