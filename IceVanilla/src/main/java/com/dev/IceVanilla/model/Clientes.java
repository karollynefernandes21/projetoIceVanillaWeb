
package com.dev.IceVanilla.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="clientes")
public class Clientes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="O nome é obrigatório.")
    private String nome;
    
    @NotBlank(message="O email é obrigatório.")
    @Email(message="Digite um email válido.")
    private String email;
    
    @NotBlank(message="O cpf é obrigatório.")
    @Size(min = 11, max = 14, message = "O cpf deve ter entre 11 e 14 caracteres.")
    private String cpf;
    
    @NotBlank(message="O telefone é obrigatório.")
    private String telefone;
    
}
