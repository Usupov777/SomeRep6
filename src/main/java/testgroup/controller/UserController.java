package testgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import testgroup.model.User;
import testgroup.model.UserForm;
import testgroup.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String allUsers(Model model){
        List<User> users = userService.allUsers();
        model.addAttribute("usersList", users);
        return "users";
    }
    @GetMapping("/addUser")
    public String showUserAddPage(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute(userForm);
        return "addUser";
    }
    @PostMapping("/addUser")
    public String saveUser(Model model,
                           @ModelAttribute("userForm") UserForm userFormFromModel){
        String name = userFormFromModel.getName();
        String surname = userFormFromModel.getSurname();
        int age = userFormFromModel.getAge();
        if (name != null && name.length() > 0 && surname != null && surname.length() > 0 && age > 0){
            userService.add(new User(name,surname,age));
        }
        return "redirect:/";
    }
    @GetMapping("/editUser/{id}")
    public String showUserEditPage(@PathVariable(value = "id") int id, Model model){
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "editUser";
    }
    @PostMapping("/editUser/{id}")
    public String editUser(@ModelAttribute User user,
                           Model model){
        userService.edit(user);
        return "redirect:/";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") int id,
                             Model model){
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/";
    }

}
