package com.simu.java.Controller;


import com.simu.java.DTO.UsuarioDTO;
import com.simu.java.Entity.Usuario;
import com.simu.java.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/Usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarAll(Usuario usuario){
        List<Usuario> usuarios = usuarioService.findAll();
        return usuarios;
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        usuarioService.salvar(usuario);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }

    @GetMapping("/id")
    public Optional<Usuario> buscarId(@RequestParam Integer id){
        return usuarioService.buscarPorID(id);
    }

    @PutMapping("/id")
    public ResponseEntity<String> edit(@RequestBody Usuario usuario, @RequestParam Integer id){
    usuarioService.editar(id, usuario);
    return new ResponseEntity<String>("editado", HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> delete(@RequestBody Usuario usuario, @RequestParam Integer id){
        usuarioService.delete(id, usuario);
        return new ResponseEntity<String>("deletado", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UsuarioDTO usuarioDTO){
        Map<String, Object> reponse = new HashMap<>();
        boolean autenticado = usuarioService.login(usuarioDTO);

        if (autenticado){
            reponse.put("messsage", "login realizado com sucesso");
            return ResponseEntity.ok(reponse);
        }else {
            reponse.put("message", "Credencias invalida");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reponse);
        }
    }
}
