package boteco.tilapia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import boteco.tilapia.model.Vendedor;
import boteco.tilapia.model.VendedorUserDetailsImpl;
import boteco.tilapia.repository.VendedorRepository;

@Service
public class VendedorUserDetailsService implements UserDetailsService{
	
	@Autowired
	private VendedorRepository vendRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Vendedor vendedor = vendRepository.findByEmail(email)
        .orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));
        return new VendedorUserDetailsImpl(vendedor);
	}
	

}