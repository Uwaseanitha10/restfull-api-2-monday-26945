package com.afanyu.question3_restaurant_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afanyu.question3_restaurant_api.model.MenuItem;

@RestController
@RequestMapping("/api/menu/")
public class MenuController {
    private List<MenuItem> menuItems = new ArrayList<>();
}
