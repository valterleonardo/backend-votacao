package br.com.votacao.core.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SecurityContextLogin {

    public static String getUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        if(principal instanceof UsernamePasswordAuthenticationToken){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
            User userLogado = (User) usernamePasswordAuthenticationToken.getPrincipal();
            return userLogado.getUsername();
        }
        throw new SecurityException("Não foi possível recuperar o usuário logado");
    }

    public static List<String> getPermissoesUsuarioLogado(){
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        if(principal instanceof UsernamePasswordAuthenticationToken){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
            User userLogado = (User) usernamePasswordAuthenticationToken.getPrincipal();
            Collection<GrantedAuthority> authorities = userLogado.getAuthorities();
            List<String> permissoes = new ArrayList<>();
            for(GrantedAuthority grantedAuthority : authorities){
                permissoes.add(grantedAuthority.getAuthority());
            }
            return permissoes;
        }
        throw new SecurityException("Não foi possível recuperar as permissões do usuário logado");
    }

}
