ALTER TABLE topico DROP FOREIGN KEY topico_ibfk_2;
ALTER TABLE topico MODIFY COLUMN curso VARCHAR(255) NOT NULL;
