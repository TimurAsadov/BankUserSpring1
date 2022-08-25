package org.bankuser.spring.controller;

import org.bankuser.spring.DAO.UserDao;
import org.bankuser.spring.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
/*@RequestMapping("/auth")*/
public class AuthRegisterController {
    private final UserDao userDao;

    public AuthRegisterController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, User user){
        model.addAttribute("user",user);
        return ("/login");
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") User user, Model model){
        model.addAttribute("email",user.getEmail());

        if(user.getEmail() == userDao.);
        return ("/login");
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model, User user){
        model.addAttribute("user",user);
        return ("register");
    }
    @PostMapping("/register")
    public String doRegister(@ModelAttribute("user") User user){
        userDao.save(user);
        return ("register");
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users",userDao.showAll());
        //debitCardDao.showAll();
        return ("/users");
    }
    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") User user){
        userDao.save(user);
        return "redirect:/users";
    }
}
