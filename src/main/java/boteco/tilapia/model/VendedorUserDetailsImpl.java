package boteco.tilapia.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import boteco.tilapia.enums.Perfil;

public class VendedorUserDetailsImpl implements UserDetails {

	private Vendedor vendedor;

	public VendedorUserDetailsImpl(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Integer getId() {
		return vendedor.getId();
	}

	public String getNome() {
		return vendedor.getNome();
	}

	public String displayImage() {
		return vendedor.getImagem();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();

		// Adiciona a autoridade 'VENDEDOR' para todos os vendedores
		authorities.add(new SimpleGrantedAuthority("VENDEDOR"));

		// Verifica se o vendedor tem perfil de administrador e adiciona a autoridade
		// 'ADMIN' se tiver
		if (vendedor.getPerfil() == Perfil.ADMIN) {
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return vendedor.getSenha();
	}

	@Override
	public String getUsername() {
		return vendedor.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}