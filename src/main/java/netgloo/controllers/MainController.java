package netgloo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import netgloo.models.User;
import netgloo.models.UserDao;

@Controller
public class MainController {
	
	 @Autowired
	  private UserDao userDao;

  @RequestMapping("/")
  @ResponseBody
  public String Index() {
    return "Proudly handcrafted by " +
        "<a href='http://netgloo.com/en'>Netgloo</a> :)";
  }

  @RequestMapping("/home")
  public String home(Model model) {
      List<User> list = userDao.findAll();
      String s = "";
      for(User u:list) {
    	  
    	  s=s + u.getEmail()+" "+u.getName()+" "+u.getId()+"\n";
      }
      model.addAttribute("name", s);
      return "home";
  }
  
  @RequestMapping("/greeting")
  public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
      model.addAttribute("name", name);
      return "greeting";
  }
}
