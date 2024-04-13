package boteco.tilapia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import boteco.tilapia.enums.CategoriaPedido;
import boteco.tilapia.enums.Pagamento;
import boteco.tilapia.model.Pedido;
import boteco.tilapia.repository.ClienteRepository;
import boteco.tilapia.repository.PedidoRepository;
import boteco.tilapia.repository.ProdutoRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@GetMapping("/cadastro")
	public ModelAndView cadastro(Pedido pedido) {
		ModelAndView mv = new ModelAndView("pedido/cadastro");
		mv.addObject("pedidos", pedido);
		mv.addObject("status", CategoriaPedido.values());
		mv.addObject("pagamento", Pagamento.values());
		mv.addObject("clientes", clienteRepo.findAll());
		mv.addObject("produtoselc", produtoRepo.findAll());
		return mv;
	}

	@PostMapping("/cadastroPedido")
	public ModelAndView save(Pedido pedido) {
		pedidoRepo.save(pedido);
		return list(1);
	}

	@GetMapping("/listPedidos")
	public ModelAndView list(@RequestParam(defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView("pedido/pedidoList");
		Pageable pageReq = PageRequest.of((page - 1), 2);
		Page<Pedido> resultPage = pedidoRepo.findAll(pageReq);
		mv.addObject("pedidosList", resultPage);
		return mv;
	}

}
