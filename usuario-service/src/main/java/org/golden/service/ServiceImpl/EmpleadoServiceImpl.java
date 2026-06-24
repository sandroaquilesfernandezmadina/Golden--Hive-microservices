package org.golden.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.golden.dto.empleado.EmpleadoRequest;
import org.golden.dto.empleado.EmpleadoResponse;
import org.golden.dto.usuario.UsuarioResponse;
import org.golden.entity.Empleado;
import org.golden.entity.EstadoUsuario;
import org.golden.entity.Rol;
import org.golden.entity.Usuario;
import org.golden.exeption.BadRequestException;
import org.golden.exeption.ResourceNotFoundException;
import org.golden.repository.EmpleadoRepository;
import org.golden.repository.RolRepository;
import org.golden.repository.UsuarioRepository;
import org.golden.service.EmpleadoService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Marca esta clase como un componente de
// lógica de negocio para que Spring la gestione.
@Service

// Crea un constructor automático solo para los campos 'final'
// (ideal para inyectar dependencias). del constructor.
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final UsuarioRepository usuarioRepository;
    private final EmpleadoRepository empleadoRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<EmpleadoResponse> findAll() {
        return empleadoRepository.findAll()
                .stream()
                .map(empleado -> new EmpleadoResponse(
                        empleado.getEmpleadoId(),
                        empleado.getNombres(),
                        empleado.getApellidos(),
                        empleado.getTelefono(),
                        empleado.getCargo(),
                        empleado.getFechaContratacion(),
                        empleado.getSalario(),
                        empleado.getUsuario().getEstado().name()
                ))
                .toList();
    }

    @Override
    @Transactional // usuario  + empleado se guardan juntos o ninguno
    //(rollback si falla)
    public EmpleadoResponse register(EmpleadoRequest request) {
        //validacion de correo duplicado
        if(usuarioRepository.findByCorreo(request.getCorreo()).isPresent()){
            throw new BadRequestException("El correo ya esta registrado");
        }

        if(usuarioRepository.findByAlias(request.getAlias()).isPresent()){
            throw new BadRequestException("El nombre ya existe");
        }

        // crear usuario
        Usuario usuario = new Usuario();
        usuario.setAlias(request.getAlias());
        //Minsma normalizacion que en registro de cliente
        usuario.setCorreo(request.getCorreo());
        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado(EstadoUsuario.ACTIVO);

        Rol rolEmpleado = rolRepository.findByNombreRol("EMPLEADO")
                .orElseThrow(()  -> new ResourceNotFoundException("El rol EMPLEADO no encontrado"));
        usuario.setRol(rolEmpleado);

        Usuario guardado = usuarioRepository.save(usuario);

        //crear empleado
        Empleado empleado = new Empleado();
        empleado.setUsuario(guardado);
        empleado.setNombres(request.getNombres());
        empleado.setApellidos(request.getApellidos());
        empleado.setTelefono(request.getTelefono());
        empleado.setCargo(request.getCargo());
        empleado.setSalario(request.getSalario());

        Empleado empleadoGuardado = empleadoRepository.save(empleado);

        return new EmpleadoResponse(
                empleadoGuardado.getEmpleadoId(),
                empleadoGuardado.getNombres(),
                empleadoGuardado.getApellidos(),
                empleadoGuardado.getTelefono(),
                empleadoGuardado.getCargo(),
                empleadoGuardado.getFechaContratacion(),
                empleadoGuardado.getSalario(),
                empleado.getUsuario().getEstado().name()
        );
    }

    @Override
    public EmpleadoResponse findById(Integer id) {
        Empleado empleado  = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Empleado no encontrado con el id: " + id));

        return  new EmpleadoResponse(
                empleado.getEmpleadoId(),
                empleado.getNombres(),
                empleado.getApellidos(),
                empleado.getTelefono(),
                empleado.getCargo(),
                empleado.getFechaContratacion(),
                empleado.getSalario(),
                empleado.getUsuario().getEstado().name()
        );
    }

    @Override
    public EmpleadoResponse update(Integer id, EmpleadoRequest request) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con el id: " + id));

        //crear empleado
        empleado.setNombres(request.getNombres());
        empleado.setApellidos(request.getApellidos());
        empleado.setTelefono(request.getTelefono());
        empleado.setCargo(request.getCargo());
        empleado.setSalario(request.getSalario());

        Empleado actualizado = empleadoRepository.save(empleado);

        return new EmpleadoResponse(
                actualizado.getEmpleadoId(),
                actualizado.getNombres(),
                actualizado.getApellidos(),
                actualizado.getTelefono(),
                actualizado.getCargo(),
                actualizado.getFechaContratacion(),
                actualizado.getSalario(),
                empleado.getUsuario().getEstado().name()
        );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Empleado empleado  = empleadoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Empleado no encontrado con el id: " + id));

        Usuario usuario = empleado.getUsuario();

        empleadoRepository.delete(empleado);
        usuarioRepository.delete(usuario);
    }



    @Override
    public void cambiarEstado(Integer empleadoId, EstadoUsuario estado) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() ->
                new ResourceNotFoundException("empleado no encontrado"));

        Usuario usuario = empleado.getUsuario();

        usuario.setEstado(estado);

        usuarioRepository.save(usuario);
    }
}
