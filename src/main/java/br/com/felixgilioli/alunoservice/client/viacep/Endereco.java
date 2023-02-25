package br.com.felixgilioli.alunoservice.client.viacep;

public record Endereco(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf
) {
}
