package boteco.tilapia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/")
	public String home(){
		return "home/index";
	}
	
	@RequestMapping("/sobre")
	public String sobre(){
		return "paginasNavbar/sobre";
	}
	
//	@RequestMapping("/menu")
//	public String menu(){
//		return "menu/list-menu";
//	}

	@RequestMapping("/events")
	public String events(){
		return "paginasNavbar/events";
	}
	
	@RequestMapping("/chefs")
	public String chefs(){
		return "paginasNavbar/chefs";
	}

	@RequestMapping("/gallery")
	public String gallery(){
		return "paginasNavbar/gallery";
	}

	@RequestMapping("/contato")
	public String contato(){
		return "paginasNavbar/contato";
	}
	
//	@RequestMapping("/pedido")
//	public String pedido(){
//		return "pedido/cadastro";
//	}

}