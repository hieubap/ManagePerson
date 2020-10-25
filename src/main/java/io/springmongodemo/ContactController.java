package io.springmongodemo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private ContactRepository repository;

    @RequestMapping(value = {"/","/personList"}, method = RequestMethod.GET)
    public String showhome(Model model){
        model.addAttribute("persons", getAllContacts());

        return "personList";
    }
    public List<Contact> getAllContacts()
    {
        return repository.findAll();
    }

    @RequestMapping(value = "/addPerson" , method = RequestMethod.GET)
    public String showaddperson(Model model){
        Contact personForm = new Contact();
        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String showupdate(Model model,@PathVariable("id") ObjectId id){
        model.addAttribute("personForm",repository.findBy_id(id));
        return "updatePerson";
    }

    @RequestMapping(value = "/put/{id}", method = RequestMethod.POST)
    public String put(@PathVariable("id") ObjectId id,
                      @Valid Contact contact){
        contact.set_id(id);
        repository.save(contact);
        return "redirect:/personList";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String addPerson(Model model, //
                             @ModelAttribute("personForm") Contact personForm) {

        ObjectId id = personForm.get_ID();
        String name = personForm.getName();
        Integer age = personForm.getAge();
        String email = personForm.getEmail();

        repository.save(new Contact(id,name,age,email));

        System.out.println("line 74");
//        if (firstName != null && firstName.length() > 0 //
//                && lastName != null && lastName.length() > 0) {
//            Person newPerson = new Person(firstName, lastName);
//            persons.add(newPerson);
//
//            return "redirect:/personList";
//        }

        return "redirect:/personList";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable ObjectId id){
        repository.delete(repository.findBy_id(id));
        return "redirect:/personList";
    }
}
