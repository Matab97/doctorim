package mr.doctorim.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.doctorim.demo.model.Role;
import mr.doctorim.demo.model.User;
import mr.doctorim.demo.model.enumeration.RolesEnum;
import mr.doctorim.demo.repository.RoleRepository;
import mr.doctorim.demo.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        //if username is null we use the phone number to identify the user
        if(user.getUsername() == null){
            user.setUsername(user.getPhoneNumber());
        }
        log.info("saving user {}",user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        log.info("saving new role {}",role.getName().toString());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        User user = getUser(username);
        Role role = roleRepository.findRoleByName(RolesEnum.valueOf(roleName));
        user.getRoles().add(role);
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> getUsers(){
        log.info("fetching users");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        if(user ==null){
            log.error("user not found in the database");
            throw new UsernameNotFoundException("user not found in the database");
        }
        else{
            log.info("loading user {} with username",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName().toString())));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
