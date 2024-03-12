package boteco.tilapia.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boteco.tilapia.enums.Perfil;
import boteco.tilapia.model.Cliente;
import boteco.tilapia.model.Vendedor;
import boteco.tilapia.repository.ClienteRepository;
import boteco.tilapia.repository.VendedorRepository;
import util.PasswordUtil;

@Service
public class InjectDB {

	@Autowired
	private VendedorRepository vendedorRepo;

	@Autowired
	private ClienteRepository clienteRepo;

	public void injectDB() {
		Vendedor vendedor1 = new Vendedor(null, "Rute", "rute@email.com", PasswordUtil.encoder("1234"), null,
				Perfil.VENDEDOR);

		Cliente cliente1 = new Cliente(null, "Tereza", "tereza@email.com", PasswordUtil.encoder("1234"), null,
				Perfil.CLIENTE);

		vendedorRepo.saveAll(Arrays.asList(vendedor1));
		clienteRepo.saveAll(Arrays.asList(cliente1));
	}

//	public void injectDBTest() {
//		Vendedor vendedor1 = new Vendedor(null, "ruteTeste", "rutetest@email.com", PasswordUtil.encoder("1234"), null,
//				Perfil.VENDEDOR);
//
//		Cliente cliente1 = new Cliente(null, "terezaTeste", "terezatest@email.com", PasswordUtil.encoder("1234"), null,
//				Perfil.CLIENTE);
//
//		vendedorRepo.saveAll(Arrays.asList(vendedor1));
//		clienteRepo.saveAll(Arrays.asList(cliente1));
//	}

}
