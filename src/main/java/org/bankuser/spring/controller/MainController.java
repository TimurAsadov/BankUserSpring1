package org.bankuser.spring.controller;

import org.bankuser.spring.DAO.DebitCardDao;
import org.bankuser.spring.DAO.LoanDao;
import org.bankuser.spring.entity.DebitCard;
import org.bankuser.spring.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*@Controller*/
@RequestMapping("/main")
@Controller
public class MainController {
    /*private final DebitCardDao debitCardDao;

    @Autowired
    public MainController(DebitCardDao debitCardDao, LoanDao loanDao) {
        this.debitCardDao = debitCardDao;
        this.loanDao = loanDao;
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

    private final LoanDao loanDao;

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
    *//*@DeleteMapping("/deleteLoan")
    public String delete(Loan loan) {
        loanDao.delete(loan);
        return "redirect:/loans";
    }*/
}
