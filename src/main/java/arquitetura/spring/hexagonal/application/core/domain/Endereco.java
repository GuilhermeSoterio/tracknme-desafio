package arquitetura.spring.hexagonal.application.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Endereco implements Serializable {

    private String cep;

    private String bairro;

    private String uf;

    @JsonProperty("localidade")
    private String cidade;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setLBairro(String logradouro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
