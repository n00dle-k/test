package com.test.controllers;

import com.test.models.House;
import com.test.services.AccountService;
import com.test.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class HouseController extends ControllerBase {

    private final HouseService houseService;

    @Autowired
    public HouseController(
            AccountService accountService,
            HouseService houseService) {
        super(accountService);
        this.houseService = houseService;
    }

    @GetMapping("/house")
    public String getHousePage(
            Authentication authentication,
            Model model) {
        final var account = getAccount(authentication);
        model.addAttribute("account", account);
        model.addAttribute("house", account.getHouses());
        return "house";
    }


    @GetMapping("/house/add")
    public String getAddHousePage(
            Authentication authentication,
            Model model
    ) {
        final var account = getAccount(authentication);
        model.addAttribute("account", account);
        model.addAttribute("house", new House());
        return "/house/add";
    }

    @PostMapping("/house/add")
    public String addNewHouse(
            Authentication authentication,
            @ModelAttribute("house") House newHouse
    ) throws IOException {
        final var account = getAccount(authentication);
        houseService.saveNewHouse(newHouse, account);
        return "redirect:/house";
    }


    @GetMapping("/house/edit/{id}")
    public String getEditHousePage(
            Authentication authentication,
            Model model,
            @PathVariable("id") long id
    ) {
        final var account = getAccount(authentication);
        model.addAttribute("account", account);
        model.addAttribute("house", houseService.findById(id).orElseThrow());
        return "/house/edit";
    }

    @PostMapping("/house/edit/{id}")
    public String updateHouse(
            Authentication authentication,
            @ModelAttribute("house") House newHouse
    ) throws IOException {
        final var account = getAccount(authentication);
        houseService.updateHouse(newHouse, account);
        return "redirect:/house";
    }


}
