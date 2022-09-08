package org.bankuser.spring.controller;

import org.bankuser.spring.dao.DebitCardDao;
import org.bankuser.spring.dao.LoanDao;
import org.bankuser.spring.dao.UserDao;
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

public class AuthRegisterController {
    private User loggedInUser = null;
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
        loggedInUser = userDao.login(user.getEmail(),user.getPassword());
        return ("redirect:/");
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model, User user){
        model.addAttribute("user",user);
        return ("register");
    }
    @PostMapping("/register")
    public String doRegister(@ModelAttribute("user") User user){
        user.registerDate();
        userDao.save(user);
        return ("redirect:/login");
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users",userDao.showAll());
        loggedInUser.registerDate();
        loggedInUser.toString();
        return ("/users");
    }
    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") User user){
        userDao.save(user);
        user.getRegistrationDate();
        user.registerDate();
        return "redirect:/users";
    }


    @GetMapping("/cards")
    public String cards(Model model){
        model.addAttribute("cards",loggedInUser.getCards());
        //debitCardDao.showAll();
        return ("/cards");
    }
    @PostMapping("/cards")
    public String addCard(@ModelAttribute("card") DebitCard debitCard){
        debitCard.generateCardNumber();
        debitCard.generateExpireDate();
        debitCard.generateCVV();
        debitCard.setCardUser(loggedInUser);
        debitCardDao.save(debitCard);
        loggedInUser.getCards().add(debitCard);

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
        if(loggedInUser != null){
            return ("/mainpage");
        }
        return ("redirect:/login");
    }
    @GetMapping("/loans")
    public String loans(Model model){
        model.addAttribute("loans", loggedInUser.getLoans());
        return ("/loans");
    }
    @PostMapping("/loans")
    public String addLoan(@ModelAttribute("loan") Loan loan){
        loan.setLoanUser(loggedInUser);
        loan.applicationDate();
        loanDao.save(loan);
        loggedInUser.getLoans().add(loan);
        return "redirect:/loans";
    }
    @GetMapping("/newLoan")
    public String newLoan(Model model){
        model.addAttribute("loan",new Loan());
        return "/CreateLoan";
    }
}
