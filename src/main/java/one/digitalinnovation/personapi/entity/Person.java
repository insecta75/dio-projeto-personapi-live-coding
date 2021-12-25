package one.digitalinnovation.personapi.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity //Informa que a classe e uma entidade;
@Data //Insere automaticamente getter e setter;
@Builder //Fornece um padrao de projeto;
@AllArgsConstructor //Insere constructor com parametros;
@NoArgsConstructor //Insere constructor sem parametros;
public class Person { //Mapeamento dos atributos;
    @Id //Diz que o atributo e um Id;
    @GeneratedValue(strategy = GenerationType.IDENTITY) //E que esse Id tem autoincremento;
    private Long id;
    @Column(nullable = false) //Campo: not null;
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true) //cpf deve ser unico;
    private String cpf;

    private LocalDate birthDate; //Por padrao e null;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}) //UMA pessoa possui MUITOS telefones;
    private List<Phone> phones;
}
