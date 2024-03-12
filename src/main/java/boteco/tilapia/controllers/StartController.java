package boteco.tilapia.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import boteco.tilapia.enums.Perfil;
import boteco.tilapia.model.Cliente;
import boteco.tilapia.model.ResetPassowordUserDAO;
import boteco.tilapia.model.Vendedor;
import boteco.tilapia.repository.ClienteRepository;
import boteco.tilapia.repository.PedidoRepository;
import boteco.tilapia.repository.VendedorRepository;
import util.PasswordUtil;

@Controller
public class StartController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private VendedorRepository vendRepository;

//	Redefinir senha de Cliente
	
    @GetMapping("/redefinir-senha/cliente")
    public ModelAndView resetPassword(Principal principal){
        ModelAndView mv = new ModelAndView("home/resetPasswordClie");
        Cliente clientAuth = this.clienteRepository.findByEmail(principal.getName()).get();
        ResetPassowordUserDAO currentSenhaDAO = new ResetPassowordUserDAO(clientAuth.getSenha());
        mv.addObject("senhaAtual", currentSenhaDAO);
        mv.addObject("resetPasswordUserDAO", new ResetPassowordUserDAO());
        return mv;
    }

	@PostMapping("/redefinir-senha/cliente")
    public String resetPassword(ResetPassowordUserDAO formularioUserDAO, Principal principal){
        Cliente clientAuth = this.clienteRepository.findByEmail(principal.getName()).get();
        if(PasswordUtil.matchersPassword(formularioUserDAO.getSenhaAtual(), clientAuth.getSenha())){
            clientAuth.setSenha(PasswordUtil.encoder(formularioUserDAO.getNovaSenha()));
            clientAuth.setPerfil(Perfil.CLIENTE);
            clienteRepository.save(clientAuth);
        }
        return "redirect:/";
    }
	
//	Redefinir senha de Vendedor
	
    @GetMapping("/redefinir-senha/vendedor")
    public ModelAndView resetPasswordVend(Principal principal){
        ModelAndView mv = new ModelAndView("home/resetPasswordVend");
        Vendedor vendedortAuth = this.vendRepository.findByEmail(principal.getName()).get();
        ResetPassowordUserDAO currentSenhaDAO = new ResetPassowordUserDAO(vendedortAuth.getSenha());
        mv.addObject("senhaAtual", currentSenhaDAO);
        mv.addObject("resetPasswordUserDAO", new ResetPassowordUserDAO());
        return mv;
    }
	
	@PostMapping("/redefinir-senha/vendedor")	
	public String resetPasswordVend(ResetPassowordUserDAO formularioUserDAO, Principal principal) {
		Vendedor vendedortAuth = this.vendRepository.findByEmail(principal.getName()).get();
		if (PasswordUtil.matchersPassword(formularioUserDAO.getSenhaAtual(), vendedortAuth.getSenha())) {
			vendedortAuth.setSenha(PasswordUtil.encoder(formularioUserDAO.getNovaSenha()));
			vendedortAuth.setPerfil(Perfil.VENDEDOR);
			vendRepository.save(vendedortAuth);
		}
		return "redirect:/";
	}
	
}
	
//	@GetMapping("/redefinir-senha")
//	public ModelAndView resetPassword(Principal principal) {
//		ModelAndView mv = new ModelAndView("home/resetPasswordClie");
//		
//		if (clienteRepository.existsByEmail(principal.getName())) {
//			Cliente clientAuth = this.clienteRepository.findByEmail(principal.getName()).get();
//			ResetPassowordUserDAO currentSenhaDAO = new ResetPassowordUserDAO(clientAuth.getSenha());
//			mv.addObject("senhaAtual", currentSenhaDAO);
//		} 
//		else if (vendRepository.existsByEmail(principal.getName())) {			
//			Vendedor vendedortAuth = this.vendRepository.findByEmail(principal.getName()).get();
//			ResetPassowordUserDAO currentSenhaDAO = new ResetPassowordUserDAO(vendedortAuth.getSenha());
//			mv.addObject("senhaAtual", currentSenhaDAO);
//		}			
//		mv.addObject("resetPasswordUserDAO", new ResetPassowordUserDAO());
//		return mv;
//	}
	