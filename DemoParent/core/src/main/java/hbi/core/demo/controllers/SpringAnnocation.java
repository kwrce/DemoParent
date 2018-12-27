package hbi.core.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
@RequestMapping
public class SpringAnnocation {
    @Autowired
    String string;
}
