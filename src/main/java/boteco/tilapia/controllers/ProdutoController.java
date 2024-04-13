package boteco.tilapia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import boteco.tilapia.enums.CategoriaProduto;
import boteco.tilapia.enums.StatusProduto;
import boteco.tilapia.model.Produto;
import boteco.tilapia.repository.ProdutoRepository;
import util.UploadUtil;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepo;

	@GetMapping("/cadastro")
	public ModelAndView cadastro(Produto produto) {
		ModelAndView mv = new ModelAndView("menu/cadastro");
		mv.addObject("cardapio", new Produto());
		mv.addObject("categoriaProdutos", CategoriaProduto.values());
		mv.addObject("statusProdutos", StatusProduto.values());
		return mv;
	}

	@PostMapping("/cadastro-produto")
	public ModelAndView cadastro(@ModelAttribute Produto produto, @RequestParam("file") MultipartFile imagem) {
		ModelAndView mv = new ModelAndView("menu/cadastro");
		mv.addObject("cardapio", produto);

		try {
			if (UploadUtil.fazerUploadImagem(imagem)) {
				produto.setImagem(imagem.getOriginalFilename());
			}
			produtoRepo.save(produto);
			System.out.println("Salvo com sucesso: " + produto.getNome() + " " + produto.getImagem());
			return produtosList();

		} catch (Exception e) {
			mv.addObject("msgErro", e.getMessage());
			System.out.println("Erro ao salvar " + e.getMessage());
			return produtosList();
		}
	}

	@GetMapping("/list-produtos")
	public ModelAndView produtosList() {
		ModelAndView mv = new ModelAndView("menu/list-menu");
		mv.addObject("produtos", produtoRepo.findAll());
		mv.addObject("categoriaProdutos", CategoriaProduto.values());
		return mv;
	}

}
