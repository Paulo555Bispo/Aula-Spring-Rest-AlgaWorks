package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@RestController
//@RequestMapping("/clientes")  //Usando essa anotação, não é necessário repetir o endereço "/clientes" dentro de @GetMapping
public class ClienteController {

    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;

    @GetMapping("/clientes")  // Listar clientes
//    @GetMapping   -->> Se usar a anotação @ReqquestMapping, não é necessário repeetir o endereço "/clientes"
    public List<Cliente> listar() {
        return clienteRepository.findAll(); // Retorna uma lista completa da tabela
//        return clienteRepository.findByNome("Paulo Bispo");  // Retorna um registro específico
//        return clienteRepository.findByNomeContaining("a");  // Retorna todos os registrox que satisfizerem a pesquisa
    }
    @GetMapping("/clientes/{clienteId}")   // Pesquisar um cliente específico
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        return clienteRepository.findById(clienteId)
//                .map(cliente -> ResponseEntity.ok(cliente))  // `como a linha inferior, porem mais detalhado
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        // Este bloco inferior, é igual ao superior, fazem as mesmas coisas.
//        Optional<Cliente> cliente =  clienteRepository.findById(clienteId);
//
//        if (cliente.isPresent()) {
//            return ResponseEntity.ok(cliente.get()); // Se encontrar o registro, retorna o cliente com o código 200
//        }
//
//        return ResponseEntity.notFound().build();  // Se nenhum registro for encontrado, retorna o código 404
    }

    @PostMapping("/clientes")  // Incluir um novo cliente na tabela
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return catalogoClienteService.salvar(cliente);
    }

    @PutMapping("/clientes/{clienteId}")  // Alterar um cliente especifíco na tabela
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,
                                             @Valid @RequestBody Cliente cliente){

        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        cliente = catalogoClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{clienteId}")  // Excluir um cliente especifíco na tabela
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> excluir(@PathVariable Long clienteId){

        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        catalogoClienteService.excluir(clienteId);
        return ResponseEntity.accepted().build();
    }

}
