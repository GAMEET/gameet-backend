package gameet.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gameet.entity.Usuario;
import gameet.service.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/getUsuarioById")
    public Usuario getUsuarioById(@RequestParam Long id) throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarioById(id);
    }

    @GetMapping("/getUsuarioByDescripcion")
    public Usuario getDescripcion(@RequestParam String descripcion) throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarioByDescripcion(descripcion);
    }

    @GetMapping("/getUsuarioByEmail")
    public Usuario getEmail(@RequestParam String email) throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarioByEmail(email);
    }

    @GetMapping("/getUsuarioByImagenPerfil")
    public Usuario getImagenPerfil(@RequestParam String imagenPerfil) throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarioByImagenPerfil(imagenPerfil);
    }

    @GetMapping("/getUsuarioByPassword")
    public Usuario getPassword(@RequestParam String password) throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarioByPassword(password);
    }

    @GetMapping("/getUsuarioByTelefono")
    public Usuario getTelefono(@RequestParam Integer telefono) throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarioByTelefono(telefono);
    }

    @GetMapping("/getUsuarioByUsername")
    public Usuario getUsername(@RequestParam String username) throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarioByUsername(username);
    }

    @GetMapping("/getUsuarioByCaracteristicas")
    public Usuario getCaracteristicas(@RequestParam String caracteristicas) throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarioByCaracteristicas(caracteristicas);
    }

    @GetMapping("/getUsuarioByHorarioJuego")
    public Usuario getHorarioJuego(@RequestParam String a) throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarioByHorarioJuego(a);
    }
    
    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) throws InterruptedException, ExecutionException {
        Usuario usuarioEncontrado = usuarioService.getUsuarioByUsername(usuario.getUsername());
        if (usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(usuario.getPassword())) {
            return "Inicio de sesión exitoso";
        } else {
            return "Nombre de usuario o contraseña incorrectos";
        }
    }
}
