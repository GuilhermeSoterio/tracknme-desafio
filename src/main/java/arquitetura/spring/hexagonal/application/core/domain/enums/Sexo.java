package arquitetura.spring.hexagonal.application.core.domain.enums;

public enum Sexo {
    MASCULINO(1),
    FEMININO(2);

    private int id;

    Sexo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
