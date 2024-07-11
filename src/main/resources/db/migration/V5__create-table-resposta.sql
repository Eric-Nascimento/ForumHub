CREATE TABLE Resposta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensagem TEXT NOT NULL,
    topico BIGINT NOT NULL,
    dataCriacao DATETIME NOT NULL,
    autor BIGINT NOT NULL,
    solucao BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (topico) REFERENCES Topico(id),
    FOREIGN KEY (autor) REFERENCES Usuario(id)
);