package com.simulado1.part1.Controller;


import com.simulado1.part1.DTO.UsuarioLoginDTO;
import com.simulado1.part1.Entity.Usuario;
import com.simulado1.part1.Service.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> Listar(){
        List<Usuario> usuarios = usuarioService.getAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Usuario> buscarPorid(@RequestParam Integer id){
        Optional<Usuario> usuario = usuarioService.BuscarId(id);
        return usuario.map(ResponseEntity:: ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.salvar(usuario);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> delete(@RequestParam Integer id){
    usuarioService.deletar(id);
    return new ResponseEntity<String>("deletado com sucesso!", HttpStatus.OK);
    }

    @PutMapping("/id")
    public ResponseEntity<String> edit(@RequestParam Integer id, @RequestBody Usuario usuario){
        usuarioService.editarUsuPorID(usuario, id);
        return new ResponseEntity<String>("atualizado com sucesso!", HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UsuarioLoginDTO usuDTO){
        Map<String, Object> response = new HashMap<>();
        boolean isAutenticado = usuarioService.login(usuDTO);

        if(isAutenticado){
            response.put("message", "Login realizado com sucesso!");
            return ResponseEntity.ok(response);
        }else {
            response.put("message", "Credenciais inválidas: e-mail ou senha incorretos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        }
    }

}
