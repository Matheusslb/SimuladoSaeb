package com.simu.java.Service;

import com.simu.java.DTO.UsuarioDTO;
import com.simu.java.Entity.Usuario;
import com.simu.java.Repository.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
       return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Optional<Usuario> buscarPorID(Integer id){
        return usuarioRepository.findById(id);
    }

    public ResponseEntity<Boolean> editar(Integer id, Usuario usuario){
        if(!usuarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        usuarioRepository.save(usuario);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    public ResponseEntity<Object> delete(Integer id, Usuario usuario){
        if(!usuarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    public Boolean login (UsuarioDTO usuarioDTO){
        return usuarioRepository.findByEmail(usuarioDTO.getEmail())
                .map(usuario -> usuario.getSenha().equals(usuarioDTO.getSenha()))
                .orElse(false);
    }

}
