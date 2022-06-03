package tn.esprit.onlinebookservice.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import tn.esprit.onlinebookservice.model.OnlineBook;
import tn.esprit.onlinebookservice.service.ImpOnlineBookService;

import java.util.ArrayList;

@RequestMapping("api/onlinebook")
@RestController
public class OnlineBookController {
    @Autowired
    ImpOnlineBookService impOnlineBookService;

    @PostMapping("/addOnlineBook")
    public void addOnlineBook(@RequestBody OnlineBook b) {
        impOnlineBookService.addBook(b);
    }

    @PostMapping("/deleteOnlineBook")
    public void deletOnlineBook(@RequestBody OnlineBook b) {
        impOnlineBookService.deleteBook(b);
    }

    @PostMapping("/updateOnlineBook")
    public void updateOnlineBook(@RequestBody OnlineBook b) {
        impOnlineBookService.updateBook(b);
    }

    @GetMapping("/getOnlineBookList")
    public ArrayList<OnlineBook> getBookList() {
        return impOnlineBookService.getBookList();
    }

    @GetMapping("/getOnlineBook/{id}")
    public OnlineBook getOnlineBookbyId(@PathVariable int id) {
        return impOnlineBookService.getBookByID(id);
    }

    @GetMapping("/getOnlineQuantity/{id}")
    public int getOnlineQuantity(@PathVariable int id) {
        return impOnlineBookService.getQuantity(id);
    }


}

