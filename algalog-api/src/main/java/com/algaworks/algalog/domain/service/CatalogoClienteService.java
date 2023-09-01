package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ***************************************************************************************************
// * Essa classe foi criada, para que seja colocada nela, toda a regra de negócio do objeto cliente. *
// * Não se coloca regra de negócio, dentro do controlador, por isso devemos criar um Service.       *
// ***************************************************************************************************
@AllArgsConstructor
@Service
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;
    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso =  clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }
        return clienteRepository.save(cliente);
    }
    @Transactional
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
