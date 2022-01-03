package pl.saqie.SaQieBlog.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.saqie.SaQieBlog.domain.Category;
import pl.saqie.SaQieBlog.domain.User;
import pl.saqie.SaQieBlog.repository.CategoryRepository;
import pl.saqie.SaQieBlog.repository.PostRepository;
import pl.saqie.SaQieBlog.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class Admin {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/delete/")
    public String deleteSelectedUser(@RequestParam Long Id){
        userRepository.deleteById(Id);
        return "redirect:/admin/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/reset/")
    public String resetStatistics(@RequestParam Long Id){
        userRepository.resetUserStatistics(Id);
        return "redirect:/admin/users/?Id=" + Id;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/block/")
    public String blockUser(@RequestParam Long Id){
        userRepository.blockUser(Id);
        return "redirect:/admin/users/?Id=" + Id;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/post/delete/")
    public String deleteSelectedPost(@RequestParam Long Id){
        postRepository.deleteById(Id);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/unlock/")
    public String unlockUser(@RequestParam Long Id){
        userRepository.unlockUser(Id);
        return "redirect:/admin/users/?Id=" + Id;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/admin/users/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/")
    public String getSingleUser(@RequestParam Long Id, Model model){
        User user = userRepository.findById(Id).get();
        model.addAttribute("user", user);
        return "/admin/users/view";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/role/admin/")
    public String setAdminRole(@RequestParam Long Id, Model model){
        userRepository.setAdminRole(Id);
        return "redirect:/admin/users/?Id=" + Id;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/role/user/")
    public String setUserRole(@RequestParam Long Id, Model model){
        userRepository.setUserRole(Id);
        return "redirect:/admin/users/?Id=" + Id;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/categories")
    public String getCategories(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "/admin/categories/categories";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/categories/add")
    public String getAddCategory(Model model){
        model.addAttribute(new Category());
        return "/admin/categories/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/categories/add")
    public String postAddCategory(@ModelAttribute @Valid Category category, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "/admin/categories/add";
        }
        categoryRepository.save(category);
        model.addAttribute("succes", true);
        return "/admin/categories/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/categories/delete")
    public String deleteSelectedCategory(@RequestParam Long Id){
        categoryRepository.deleteById(Id);
        return "redirect:/admin/categories";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/categories/")
    public String getSelectedCategory(@RequestParam Long Id, Model model){
        Category categoryById = categoryRepository.getById(Id);
        model.addAttribute("categoryById", categoryById);
        return "/admin/categories/view";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/categories/edit")
    public String editSelectedCategory(@RequestParam Long Id, Model model){
        Category byId = categoryRepository.findById(Id).get();
        model.addAttribute("category", byId);
        return "/admin/categories/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/categories/edit")
    public String postEditSelectedCategory(@ModelAttribute @Valid Category category, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/admin/categories/edit";
        }
        categoryRepository.updateCategory(category.getName(), category.getDescription(), category.getId());
        return "redirect:/admin/categories";
    }



}
