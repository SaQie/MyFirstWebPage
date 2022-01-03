package pl.saqie.SaQieBlog.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.saqie.SaQieBlog.domain.Category;
import pl.saqie.SaQieBlog.domain.Comments;
import pl.saqie.SaQieBlog.domain.Post;
import pl.saqie.SaQieBlog.domain.User;
import pl.saqie.SaQieBlog.repository.CategoryRepository;
import pl.saqie.SaQieBlog.repository.CommentRepository;
import pl.saqie.SaQieBlog.repository.PostRepository;
import pl.saqie.SaQieBlog.repository.UserRepository;
import pl.saqie.SaQieBlog.service.comment.CommentService;
import pl.saqie.SaQieBlog.service.post.PostService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class Home {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final CommentService commentService;


    @GetMapping("/home/{name}")
    public String getHomeWithCategory(@PathVariable String name, Model model){
        List<Post> postByCategoryName = postRepository.findByCategoryName(name);
        model.addAttribute("categoryName", name);
        model.addAttribute("posts", postByCategoryName);
        return "/view/inside/category";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/home/{name}/add")
    public String getAddPostForm(@PathVariable String name, Model model){
        Category categoryByName = categoryRepository.findByName(name);
        model.addAttribute("category", categoryByName);
        model.addAttribute(new Post());
        return "/view/inside/add/post";
    }

    @GetMapping("/accessDenied")
    public String getAccesDeniedPage(){
        return "/error/accessDenied";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/home/{name}/add")
    public String postAddPostForm(@PathVariable String name, @ModelAttribute @Valid Post post, BindingResult bindingResult, Model model, Principal principal){
        if (bindingResult.hasErrors()){
            Category categoryByName = categoryRepository.findByName(name);
            model.addAttribute("category", categoryByName);
            return "/view/inside/add/post";
        }
        Category categoryByName = categoryRepository.findByName(name);
        String username = principal.getName();
        User userByUsername = userRepository.findByUsername(username).get();
        userByUsername.setWritedPosts(userByUsername.getWritedPosts() + 1);
        postService.saveNewPost(userByUsername, categoryByName, post);
        return "redirect:/home/" + name;
    }

    @GetMapping("/home/{name}/{id}")
    public String getAddPostForm(@PathVariable Long id, Model model){
        Post postById = postRepository.findById(id).get();
        model.addAttribute(new Comments());
        model.addAttribute("post", postById);
        List<Comments> comments = commentRepository.findByPostId(postById.getId());
        model.addAttribute("comment", comments);
        return "/view/inside/post";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/home/user/settings/{name}")
    public String showUserSettings(@PathVariable String name, Model model){
        User user = userRepository.findByUsername(name).get();
        model.addAttribute("userByUsername", user);
        return "/view/home/user/settings";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/home/user/settings/{name}")
    public String saveNewUserData(@PathVariable String name, @ModelAttribute User user){
        userRepository.updateUserData(user.getFirstName(), user.getLastName(), user.getId());
        return "redirect:/home";
    }


    @PostMapping("/home/{name}/{id}")
    public String addNewComments(@PathVariable String name , @PathVariable Long id, @ModelAttribute Comments comments, Principal principal){
        User byUsername = userRepository.findByUsername(principal.getName()).get();
        byUsername.setWritedComments(byUsername.getWritedComments() + 1);
        commentService.saveNewComment(comments,id,byUsername);
        return "redirect:/home/" + name + "/" + id;
    }

    @GetMapping("/home/user/{name}")
    public String showUserProfile(@PathVariable String name, Model model){
        User user = userRepository.findByUsername(name).get();
        model.addAttribute("userByUsername", user);
        return "/view/home/user/profile";
    }


    @PostMapping()
    public String homeRequest(){
        return "redirect:/home";
    }


    @GetMapping()
    public String ahomeRequest(){
        return "redirect:/home";
    }


    @GetMapping("/home")
    public String agetHome(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "/view/home/index";
    }

}
