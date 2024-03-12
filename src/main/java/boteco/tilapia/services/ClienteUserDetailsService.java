package boteco.tilapia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import boteco.tilapia.model.Cliente;
import boteco.tilapia.model.ClienteUserDetailsImpl;
import boteco.tilapia.repository.ClienteRepository;

@Service
public class ClienteUserDetailsService implements UserDetailsService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email)
        .orElseThrow( () -> new UsernameNotFoundException("Usuário não foi encontrado na base de dados"));
        return new ClienteUserDetailsImpl(cliente);
    }
	

}