package com.generation.lojadegames.lojadegames.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.lojadegames.lojadegames.model.Usuario;
import com.generation.lojadegames.lojadegames.repository.usuarioRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private usuarioRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
	  
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));


		return usuario.map(UserDetailsImpl::new).get();
	}
}
