package gameet.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gameet.entity.Caracteristicas;
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
    public Boolean login(@RequestParam String username, @RequestParam String password) throws InterruptedException, ExecutionException {
        if (username != null && password != null) {
        	Usuario usuario = usuarioService.getUsuarioByUsername(username);
        	if(usuario != null && usuario.getPassword().equals(password)) {
        		return true;
        	}
        	else return false;

        } else {
            return false;
        }
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam(required = true) String username,
                            @RequestParam(required = true) String password,
                            @RequestParam(required = true) String descripcion,
                            @RequestParam(required = true) String email,
                            @RequestParam(required = true) String imagenPerfil,
                            @RequestParam(required = true) String telefono,
                            @RequestParam(required = true) Caracteristicas caracteristicas,
                            @RequestParam(required = true) String horarioJuego) throws InterruptedException, ExecutionException {

        // Verificamos que ningun parámetro es nulo
        if (username == null || password == null || descripcion == null || email == null || imagenPerfil == null
                || telefono == null || caracteristicas == null || horarioJuego == null) {
            return "Faltan campos";
        }

        // Verificamos que el username tiene más de 4 letras
        if (username.length() <= 4) {
            return "Username incorrecto";
        }

        // Verificamos que la contraseña tiene al menos 8 caracteres
        if (password.length() < 8) {
            return "Password incorrecto";
        }

        if (!esUsernameUnico(username)) {
            return "Username repetido";
        }
        
        // Verificamos que el correo electrónico tenga el formato adecuado
        if (!isValidEmail(email)) {
            return "Email invalido";
        }
        
        // Verificamos que el número de teléfono tenga 9 cifras
        if (telefono.length() != 9) {
            return "Telefono invalido";
        }

        Usuario usuario = new Usuario(descripcion, email, imagenPerfil, password, Integer.valueOf(telefono),
    			username, caracteristicas, horarioJuego);
        usuarioService.guardarUsuario(usuario);
        return "Usuario correctamente creado";
    }
    
    // Método de ejemplo para verificar si el username es único en la base de datos
    private boolean esUsernameUnico(String username) {
    	try {
			Usuario usuario = usuarioService.getUsuarioByUsername(username);
			if(usuario != null) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return true;
    }
    
    // Método para verificar el formato del correo electrónico
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
}
