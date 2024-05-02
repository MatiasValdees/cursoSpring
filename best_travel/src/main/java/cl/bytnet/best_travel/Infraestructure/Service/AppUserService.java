package cl.bytnet.best_travel.Infraestructure.Service;

import cl.bytnet.best_travel.Domain.Entities.Document.AppsUserDocument;
import cl.bytnet.best_travel.Domain.Repositories.Document.AppUserRepository;
import cl.bytnet.best_travel.Infraestructure.AbstractService.ModifyUserService;
import cl.bytnet.best_travel.Util.Exception.IdNotFoundException;
import cl.bytnet.best_travel.Util.Exception.UsernameNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class AppUserService implements ModifyUserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    @Override
    public Map<String, Boolean> enabled(String username) {
        var user=appUserRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        user.setEnabled(!user.isEnabled());
        var userSaved=appUserRepository.save(user);
        return Collections.singletonMap(userSaved.getUsername(),userSaved.isEnabled());
    }

    @Override
    public Map<String, Set<String>> addRole(String username, String role) {
        var user=appUserRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        user.getRole().getGrantedAuthorities().add(role);
        var userSaved=appUserRepository.save(user);
        var authorities=userSaved.getRole().getGrantedAuthorities();
        log.info("User {} add role {}", userSaved.getUsername(),userSaved.getRole().getGrantedAuthorities().toString());
        return Collections.singletonMap(userSaved.getUsername(),authorities);
    }

    @Override
    public Map<String, Set<String>> removeRole(String username, String role) {
        var user=appUserRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        user.getRole().getGrantedAuthorities().remove(role);
        var userSaved=appUserRepository.save(user);
        var authorities=userSaved.getRole().getGrantedAuthorities();
        log.info("User {} remove role {}", userSaved.getUsername(),userSaved.getRole().getGrantedAuthorities().toString());
        return Collections.singletonMap(userSaved.getUsername(),authorities);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username){
       var user=appUserRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        return mapUserToUserDetails(user);
    }

    private static UserDetails mapUserToUserDetails(AppsUserDocument user){
        Set<GrantedAuthority>authorities=user.getRole()
                .getGrantedAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                authorities
        );
    }
}
