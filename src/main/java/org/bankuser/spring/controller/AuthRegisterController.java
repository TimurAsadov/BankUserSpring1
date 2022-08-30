package org.bankuser.spring.controller;

import org.bankuser.spring.DAO.DebitCardDao;
import org.bankuser.spring.DAO.LoanDao;
import org.bankuser.spring.DAO.UserDao;
import org.bankuser.spring.entity.DebitCard;
import org.bankuser.spring.entity.Loan;
import org.bankuser.spring.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
/*@RequestMapping("/auth")*/
public class AuthRegisterController {
    private final LoanDao loanDao;
    private final UserDao userDao;
    private final DebitCardDao debitCardDao;


    public AuthRegisterController(LoanDao loanDao, UserDao userDao, DebitCardDao debitCardDao) {
        this.loanDao = loanDao;
        this.userDao = userDao;
        this.debitCardDao = debitCardDao;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, User user){
        model.addAttribute("user",user);
        return ("/login");
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") User user, Model model){
        /*model.addAttribute("email",user.getEmail());*/
       userDao.checkEmail(user.getEmail(),user.getPassword());
        /*if(user.getEmail() == userDao.);*/
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
        return ("redirect:/register");
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

    @GetMapping("/cards")
    public String cards(Model model){
        model.addAttribute("cards",debitCardDao.showAll());
        //debitCardDao.showAll();
        return ("/cards");
    }
    @PostMapping("/cards")
    public String addCard(@ModelAttribute("card") DebitCard debitCard){
        debitCardDao.save(debitCard);
        return "redirect:/cards";
    }
    @GetMapping("/newCard")
    public String newCard(Model model){
        model.addAttribute("card",new DebitCard());
        return "/CreateCard";
    }
    @DeleteMapping("/deletCard")
    public String delete(DebitCard cardnumber) {
        debitCardDao.delete(cardnumber);
        return "redirect:/cards";
    }
    @GetMapping
    public String mainPage(){
        return ("/mainpage");
    }
    @GetMapping("/loans")
    public String loans(Model model){
        model.addAttribute("loans", loanDao.showAll());
        loanDao.showAll();
        return ("/loans");
    }
    @PostMapping("/loans")
    public String addLoan(@ModelAttribute("loan") Loan loan){
        loanDao.save(loan);
        return "redirect:/loans";
    }
    @GetMapping("/newLoan")
    public String newLoan(Model model){
        model.addAttribute("loan",new Loan());
        return "/CreateLoan";
    }
}
