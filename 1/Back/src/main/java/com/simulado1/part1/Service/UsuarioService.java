package com.simulado1.part1.Service;

import com.simulado1.part1.DTO.UsuarioLoginDTO;
import com.simulado1.part1.Entity.Usuario;
import com.simulado1.part1.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //get
    public List<Usuario> getAll(){
        List<Usuario> usuario = usuarioRepository.findAll();
        return usuario;
    }

    //post
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //buscar por id
    public Optional<Usuario> BuscarId(Integer id){
        return usuarioRepository.findById(id);
    }

    //put
    public ResponseEntity<Boolean> editarUsuPorID(Usuario usuario, Integer id){
            if(!usuarioRepository.existsById(id)){
                return ResponseEntity.notFound().build();
            }
            usuario.setId(id);
            usuarioRepository.save(usuario);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }

    //delete
    public ResponseEntity<Object> deletar(Integer id){
        if(!usuarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
            }
            usuarioRepository.deleteById(id);
            return null;
        }

    //login
    public Boolean login(UsuarioLoginDTO usuarioDTO){
        return usuarioRepository.findByEmail(usuarioDTO.getEmail())
                .map(usuario -> usuario.getSenha().equals(usuarioDTO.getSenha()))
                .orElse(false);
        }

}
