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
import boteco.tilapia.model.Vendedor;
import boteco.tilapia.repository.VendedorRepository;
import util.PasswordUtil;
import util.UploadUtil;

@Controller
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorRepository vendRepository;

    @GetMapping("/cadastro")
    public ModelAndView cadastro(Vendedor vendedor) {
        ModelAndView mv = new ModelAndView("vendedor/cadastro");
        mv.addObject("vendedor", new Vendedor());
        Perfil[] perfilVendedor = { Perfil.ADMIN, Perfil.VENDEDOR };
        mv.addObject("perfils", perfilVendedor);
        return mv;
    }

    @PostMapping("/cadastroVendedor")
    public ModelAndView cadastro(@ModelAttribute Vendedor vendedor, @RequestParam("file") MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("vendedor/cadastro");
        String hashSenha = PasswordUtil.encoder(vendedor.getSenha());
        vendedor.setSenha(hashSenha);

        mv.addObject("vendedor", vendedor);

        try {
            if (UploadUtil.fazerUploadImagem(imagem)) {
                vendedor.setImagem(imagem.getOriginalFilename());
            }
            vendRepository.save(vendedor);
            System.out.println("Salvo com sucesso: " + vendedor.getNome() + " " + vendedor.getImagem());
            return homeVend();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }
    }

    @GetMapping("/listVendedor")
    public ModelAndView vendedorList() {
        ModelAndView mv = new ModelAndView("vendedor/vendedorList");
        mv.addObject("vendedores", vendRepository.findAll());
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluirVendedor(@PathVariable("id") Integer id) {
        vendRepository.deleteById(id);
        return "vendedor/vendedorList";
    }

    @RequestMapping("/inicio-vend")
    public ModelAndView homeVend() {
        ModelAndView mv = new ModelAndView("home/index");
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("vendedor/editarVendedor");
        mv.addObject("perfils", Perfil.values());
        mv.addObject("vendedor", vendRepository.findById(id));
        return mv;
    }

    @PostMapping("/editar-vendedor")
    public ModelAndView editar(@ModelAttribute Vendedor vendedor, @RequestParam("file") MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("vendedor/editarVendedor");
        try {
            if (UploadUtil.fazerUploadImagem(imagem)) {
                vendedor.setImagem(imagem.getOriginalFilename());
            }
            vendRepository.save(vendedor);
            System.out.println("Salvo com sucesso: " + vendedor.getNome() + " " + vendedor.getImagem());
            return homeVend();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }

    }

    @GetMapping("/editar-perfil")
    public ModelAndView editarPerfil(@RequestParam("id") Integer id) {
        ModelAndView mv = new ModelAndView("vendedor/editProfile");
        mv.addObject("usuario", vendRepository.findById(id));
        return mv;
    }

    @PostMapping("/editar-perfil")
    public ModelAndView editarPerfil(@ModelAttribute Vendedor vendedor, @RequestParam("file") MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("vendedor/editProfile");

        try {
            if (UploadUtil.fazerUploadImagem(imagem)) {
                vendedor.setImagem(imagem.getOriginalFilename());
            }
            vendRepository.save(vendedor);
            System.out.println("Salvo com sucesso: " + vendedor.getNome() + " " + vendedor.getImagem());
            return homeVend();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }

    }

}