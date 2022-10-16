package sbsecurity.hospital.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sbsecurity.hospital.Reposiroty.AppRoleRepository;
import sbsecurity.hospital.Reposiroty.AppUserRepository;
import sbsecurity.hospital.Reposiroty.RoleRepository;
import sbsecurity.hospital.Reposiroty.UserParamsRepository;
import sbsecurity.hospital.dao.AppUserDAO;
import sbsecurity.hospital.dao.UserParamsDAO;
import sbsecurity.hospital.entity.AppRole;
import sbsecurity.hospital.entity.AppUser;
import sbsecurity.hospital.entity.UserParams;
import sbsecurity.hospital.entity.UserRole;
import sbsecurity.hospital.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private UserParamsDAO userParamsDAO;

    @Autowired
    private AppUserRepository AppUserRepository;

    @Autowired
    private AppRoleRepository AppRoleRepository;

    @Autowired
    private RoleRepository userRoleRepository;

    @Autowired
    private UserParamsRepository paramRepository;




    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {

        return "loginPage";
    }

    @GetMapping(value = "/about")
    public String viewAbout(Model model) {

        return "aboutPage";
    }



    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping(value = "/userInfo")
    public String userInfo( Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser appUser = appUserDAO.findByUserName(user.getUsername());
        Long id = appUser.getUserId();
        model.addAttribute("userParams", userParamsDAO.FindAllParamsById(id));

        return "userInfoPage";
    }

    @GetMapping(value = "/userInfo/{id}/edit" )
    public String userInfoview(@PathVariable(value = "id")long id, Model model) {
        if(!paramRepository.existsById(id))
        {
            return "redirect:/userInfo";
        }
        Optional<UserParams> params = paramRepository.findById(id);
        ArrayList<UserParams> userParams = new ArrayList<>();
        params.ifPresent(userParams::add);
        model.addAttribute("userParams", userParams);

        return "userInfoEdit";
    }

    @PostMapping(value = "/userInfo/{id}/edit" )
    public String userInfoEdit(@PathVariable(value = "id")long id,
                               @RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String phone, @RequestParam String email,
                               @RequestParam String adress, Model model) {
        UserParams userParams = paramRepository.findById(id).orElseThrow();
        userParams.setFirstName(firstName);
        userParams.setLastName(lastName);
        userParams.setPhone(phone);
        userParams.setEmail(email);
        userParams.setAdress(adress);
        paramRepository.save(userParams);
        return "redirect:/userInfo";
    }



    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

    @GetMapping("/members")
    public String viewMembers(Model model) {
        Iterable<AppUser> users = AppUserRepository.findAll();
        model.addAttribute("members", users);
        return "membersPage";
    }

    @GetMapping("/register")
        public String viewRegister (Model model){

        return "registerPage";
        }

    @GetMapping("/registerSuccessfulPage")
    public String success (Model model){

        return "registerSuccessfulPage";
    }

    @GetMapping("/addUser")
    public String viewAdd (Model model){

        return "addUser";
    }
    @PostMapping("/addUser")
    public String addUser(@RequestParam String userName,@RequestParam String password, @RequestParam int role, Model model) {
        AppUser user = new AppUser(userName, password);
        model.addAttribute("App_User", user);
        if (appUserDAO.findByUserName(user.getUserName()) != null){

            return "redirect:/addUser?error=true";
        }
        else {
            AppUserRepository.save(user);
            AppRole appRole = new AppRole((long) role,"ROLE_USER");
            UserRole userRole = new UserRole(user,appRole);
            userRoleRepository.save(userRole);
            return "redirect:/addUser?succ=true";
        }

    }

    @PostMapping("/register")
    public String Register(@RequestParam String userName,@RequestParam String password, Model model) {
        AppUser user = new AppUser(userName, password);
        model.addAttribute("App_User", user);
        if (appUserDAO.findByUserName(user.getUserName()) != null){

            return "redirect:/register?error=true";
        }
        else {
            AppUserRepository.save(user);
            AppRole appRole = new AppRole(2L,"ROLE_USER");
            UserRole userRole = new UserRole(user,appRole);
            userRoleRepository.save(userRole);
            return "redirect:/registerSuccessfulPage";
        }

    }
}