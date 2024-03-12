package boteco.tilapia.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import boteco.tilapia.enums.Perfil;

public class VendedorUserDetailsImpl implements UserDetails{

	private Vendedor vendedor;
	
	
	public VendedorUserDetailsImpl(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

    public Integer getId(){
        return vendedor.getId();
    }

    public String getNome(){
        return vendedor.getNome();
    }

    public String displayImage(){
        return vendedor.getImagem();
    }
//	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Perfil perfil = vendedor.getPerfil();
		if (perfil == Perfil.ADMIN) {
			perfil = Perfil.ADMIN;
		} else {
			perfil = Perfil.VENDEDOR;
		}
		return AuthorityUtils.createAuthorityList(perfil.toString());
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