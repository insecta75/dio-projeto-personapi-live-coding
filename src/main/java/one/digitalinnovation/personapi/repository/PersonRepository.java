package one.digitalinnovation.personapi.repository;
import one.digitalinnovation.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/*Funcionalidade de criacao de Pessoas (para gerenciar o BD);
 Simplifica o processo antes trabalhoso (abrir e fechar a conexao, operacoes de CRUD)*/
public interface PersonRepository extends JpaRepository<Person, Long> { //Person: entidade, Long: devido ao Id;

}
