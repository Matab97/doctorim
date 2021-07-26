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
        log.info("saving user {}",user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        log.info("saving new role {}",role.getName().toString());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String phoneNumber, String roleName) {
        User user = getUser(phoneNumber);
        Role role = roleRepository.findRoleByName(RolesEnum.valueOf(roleName));
        user.getRoles().add(role);
    }

    public User getUser(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }

    public List<User> getUsers(){
        log.info("fetching users");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = getUser(phoneNumber);
        if(user ==null){
            log.error("user not found in the database");
            throw new UsernameNotFoundException("user not found in the database");
        }
        else{
            log.info("loading user {} with phoneNumber",phoneNumber);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName().toString())));
        return new org.springframework.security.core.userdetails.User(
                user.getPhoneNumber(),
                user.getPassword(),
                authorities);
    }
}
