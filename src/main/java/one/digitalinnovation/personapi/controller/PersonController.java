package one.digitalinnovation.personapi.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Controlador que sera acessado por uma API REST;
@RequestMapping("/api/v1/people") //O caminho de acesso principal da API (atende ao level 1 de Richardson);
public class PersonController {//Anotacao: ponto de entrada do projeto;

    @GetMapping //Mapeando uma operacao HTTP do tipo GET;
    public String getBook() {
        return "API Test!";
    }
}
