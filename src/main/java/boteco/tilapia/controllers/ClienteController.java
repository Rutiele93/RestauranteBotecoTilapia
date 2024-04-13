package boteco.tilapia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import boteco.tilapia.enums.Perfil;
import boteco.tilapia.model.Cliente;
import boteco.tilapia.repository.ClienteRepository;
import util.PasswordUtil;
import util.UploadUtil;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepo;

    @GetMapping("/cadastro")
    public ModelAndView cadastro(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/cadastro");
        mv.addObject("usuario", new Cliente());
        Perfil[] perfilCliente = { Perfil.CLIENTE };
        mv.addObject("perfils", perfilCliente);
        return mv;
    }

    @PostMapping("/cadastro-cliente")
    public ModelAndView cadastro(@ModelAttribute Cliente cliente, @RequestParam("file") MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("cliente/cadastro");

        String hashSenha = PasswordUtil.encoder(cliente.getSenha());
        cliente.setSenha(hashSenha);

        mv.addObject("usuario", cliente);

        try {
            if (UploadUtil.fazerUploadImagem(imagem)) {
                cliente.setImagem(imagem.getOriginalFilename());
            }
            clienteRepo.save(cliente);
            System.out.println("Salvo com sucesso: " + cliente.getNome() + " " + cliente.getImagem());
            return home();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }
    }

    @GetMapping("/listClientes")
    public ModelAndView clientesList() {
        ModelAndView mv = new ModelAndView("cliente/clienteList");
        mv.addObject("clientes", clienteRepo.findAll());
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable("id") Integer id) {
        clienteRepo.deleteById(id);
        return "cliente/clienteList";
    }

    @RequestMapping("/inicio-cliente")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home/index");
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("cliente/editarCliente");
        mv.addObject("perfils", Perfil.values());
        mv.addObject("usuario", clienteRepo.findById(id));
        return mv;
    }

    @PostMapping("/editar-cliente")
    public ModelAndView editarCliente(@ModelAttribute("usuario") Cliente cliente,
            @RequestParam("file") MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("cliente/editarCliente");

        try {
            if (UploadUtil.fazerUploadImagem(imagem)) {
                cliente.setImagem(imagem.getOriginalFilename());
            }
            clienteRepo.save(cliente);
            System.out.println("Salvo com sucesso: " + cliente.getNome() + " " + cliente.getImagem());
            return home();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }

    }

    @GetMapping("/editar-perfil")
    public ModelAndView editarPerfil(@RequestParam("id") Integer id) {
        ModelAndView mv = new ModelAndView("cliente/editProfile");
        mv.addObject("usuario", clienteRepo.findById(id));
        return mv;
    }

    @PostMapping("/editar-perfil")
    public ModelAndView editarPerfil(@ModelAttribute Cliente cliente, @RequestParam("file") MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("cliente/editProfile");

        try {
            if (UploadUtil.fazerUploadImagem(imagem)) {
                cliente.setImagem(imagem.getOriginalFilename());
            }
            clienteRepo.save(cliente);
            System.out.println("Salvo com sucesso: " + cliente.getNome() + " " + cliente.getImagem());
            return home();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }

    }

}