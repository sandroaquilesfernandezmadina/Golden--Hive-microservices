package org.golden.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.golden.exeption.BadRequestException;
import org.golden.dto.auth.LoginRequest;
import org.golden.dto.auth.LoginResponse;
import org.golden.dto.auth.RegisterRequest;
import org.golden.dto.usuario.UsuarioResponse;
import org.golden.entity.Cliente;
import org.golden.entity.EstadoUsuario;
import org.golden.entity.Rol;
import org.golden.entity.Usuario;
import org.golden.exeption.ResourceNotFoundException;
import org.golden.repository.ClienteRepository;
import org.golden.repository.RolRepository;
import org.golden.repository.UsuarioRepository;
import org.golden.service.AuthService;
import org.golden.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Transactional
    @Override
    public UsuarioResponse register(RegisterRequest request) {
        // 🔍 Validar correo duplicado
        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            throw new BadRequestException("El correo ya está registrado");
        }
        // validad el nombre del usuario
        if (usuarioRepository.findByAlias(request.getAlias()).isPresent()){
            throw new BadRequestException("el nombre de usuario ya existe");
        }

        //crear usuario
        Usuario usuario = new Usuario();
        usuario.setAlias(request.getAlias());
        usuario.setCorreo(request.getCorreo().toLowerCase().trim());
        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado(EstadoUsuario.ACTIVO);

        Rol rolCliente = rolRepository.findByNombreRol("CLIENTE")
                .orElseThrow(() -> new ResourceNotFoundException("el rol CLIENTE no encontrado"));

        usuario.setRol(rolCliente);


        Usuario guardado = usuarioRepository.save(usuario);

        //crear cliente
        Cliente cliente  = new  Cliente();
        cliente.setUsuario(guardado);
        cliente.setNombres(request.getNombres());
        cliente.setApellidos(request.getApellidos());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion(request.getDireccion());

        clienteRepository.save(cliente);

        return new UsuarioResponse(
                guardado.getUsuarioId(),
                guardado.getAlias(),
                guardado.getCorreo()
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado"));

        //validacion de contraseña
        if (!passwordEncoder.matches(
                request.getPassword(),
                usuario.getPasswordHash()
        )) {
            throw new BadRequestException("Credenciales incorrectas");
        }

        // ⚠️ JWT aún no implementado
        String token = jwtService.generateToken(usuario);

        return new LoginResponse(
                token,
                usuario.getAlias(),
                usuario.getRol().getNombreRol()
        );
    }
}
