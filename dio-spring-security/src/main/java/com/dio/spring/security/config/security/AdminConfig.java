package com.dio.spring.security.config.security;

import com.dio.spring.security.model.Role;
import com.dio.spring.security.model.Usuario;
import com.dio.spring.security.model.enums.RoleName;
import com.dio.spring.security.repository.RoleRepository;
import com.dio.spring.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class AdminConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        criarRoleSeNaoExistir(RoleName.ROLE_ADMIN);
        criarRoleSeNaoExistir(RoleName.ROLE_BASIC);
        criarUsuarioAdminSeNaoExistir("admin@admin.com", "admin123", RoleName.ROLE_ADMIN);
    }

    private void criarRoleSeNaoExistir(RoleName roleNome) {
        roleRepository.findByNome(roleNome).orElseGet(() -> {
            Role role = new Role();
            role.setNome(roleNome);
            return roleRepository.save(role);
        });
    }

    private void criarUsuarioAdminSeNaoExistir(String email, String rawPassword, RoleName roleNome) {
        if (usuarioRepository.findByEmail(email).isEmpty()) {
            Role roleFromDb = roleRepository.findByNome(roleNome)
                    .orElseThrow(() -> new RuntimeException("Role não encontrada: " + roleNome));

            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail(email);
            admin.setSenha(passwordEncoder.encode(rawPassword));
            admin.setRoles(Set.of(roleFromDb));

            usuarioRepository.save(admin);
            System.out.println("Usuário admin criado com sucesso.");
        }
    }

}
