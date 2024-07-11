CREATE TABLE Topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    dataCriacao DATETIME NOT NULL,
    status VARCHAR(255) NOT NULL,
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (autor) REFERENCES Usuario(id),
    FOREIGN KEY (curso) REFERENCES Curso(id)
);