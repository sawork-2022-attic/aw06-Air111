package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class PosController {

    private PosService posService;

    private HttpSession session;

    private Cart cart;

    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
        //session.setAttribute("cart", this.cart);
    }

    private Cart getCart() {
        Cart ret = (Cart) session.getAttribute("cart");
        if (ret == null) {
            ret = this.cart;
            session.setAttribute("cart", ret);
        }
        return ret;
    }

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @GetMapping("/")
    public String pos(Model model) {
        model.addAttribute("products", posService.products());
        //model.addAttribute("cart", cart);
        Cart c = getCart();
        model.addAttribute("cartItems", c.getItems());
        model.addAttribute("total", c.getTotal());
        return "index";
    }

    @GetMapping("/add")
    public String addByGet(@RequestParam(name = "pid") String pid,
                           @RequestParam(name = "amount") int amount,
                           Model model)
    {
        Cart c = getCart();
        session.setAttribute("cart", posService.add(c, pid, amount));
        model.addAttribute("products", posService.products());
        model.addAttribute("cartItems", c.getItems());
        model.addAttribute("total", c.getTotal());
        return "index";
    }

    @GetMapping("/remove")
    public String removeByGet(@RequestParam(name = "pid") String pid, Model model) {
        Cart c = getCart();
        session.setAttribute("cart", posService.remove(c, pid));
        model.addAttribute("products", posService.products());
        model.addAttribute("cartItems", c.getItems());
        model.addAttribute("total", c.getTotal());
        return "index";
    }
}
