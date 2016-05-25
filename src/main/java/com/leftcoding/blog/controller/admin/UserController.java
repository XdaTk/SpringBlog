package com.leftcoding.blog.controller.admin;

import com.leftcoding.blog.form.UserForm;
import com.leftcoding.blog.model.User;
import com.leftcoding.blog.repositories.UserRepository;
import com.leftcoding.blog.service.UserService;
import com.leftcoding.blog.utils.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by XdaTk on 16/5/22.
 */
@Controller("adminUserController")
@RequestMapping("admin/users")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping("profile")
    public String profile(Model model){
        model.addAttribute("user", userService.currentUser());

        return "admin/users/profile";
    }

    @RequestMapping(value = "{userId:[0-9]+}", method = POST)
    public String update(@PathVariable Long userId, @Valid UserForm userForm, Errors errors, RedirectAttributes ra){
        User user = userRepository.findOne(userId);
        Assert.notNull(user);

        if (errors.hasErrors()){
            // do something

            return "admin/users/profile";
        }

        if (!userForm.getNewPassword().isEmpty()){

            if (!userService.changePassword(user, userForm.getPassword(), userForm.getNewPassword()))
                MessageHelper.addErrorAttribute(ra, "Change password failed.");
            else
                MessageHelper.addSuccessAttribute(ra, "Change password successfully.");

        }

        return "redirect:profile";
    }
}