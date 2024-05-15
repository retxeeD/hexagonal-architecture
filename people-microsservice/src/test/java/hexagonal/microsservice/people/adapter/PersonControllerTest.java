package hexagonal.microsservice.people.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexagonal.microsservice.people.adapter.dtos.PersonDto;
import hexagonal.microsservice.people.adapter.dtos.RentBookDto;
import hexagonal.microsservice.people.domain.ports.logger.PersonLogger;
import hexagonal.microsservice.people.domain.ports.services.PersonServicePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonServicePort service;

    @MockBean
    PersonLogger logger;

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void validHandleValidationErrorRegisterDocumentInvalidValue() throws Exception {
        PersonDto request = new PersonDto("ABC", "Teste teste", 1);
        List<String> messages = new ArrayList<>();
        messages.add("O campo 'document' deve ser informado com valor válido.");
        ResponseEntity<Object> apiErrors = new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/person/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors.getBody())));
    }

    @Test
    void validHandleValidationErrorRegisterDocumentNullValue() throws Exception {
        PersonDto request = new PersonDto(null, "Teste teste",1);
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'document' é obrigatório.", HttpStatus.BAD_REQUEST);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/person/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRegisterWithoutDocument() throws Exception {
        String request = "{\"name\": \"Teste teste\", \"rentBook\": 1}";
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'document' é obrigatório.", HttpStatus.BAD_REQUEST);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/person/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRegisterDocumentBlankValue() throws Exception {
        String request = "{\"document\": \"     \",\"name\": \"Teste teste\",\"rentBook\": 1}";
        List<String> messages = Arrays.asList("O campo 'document' deve ser informado com valor válido.","O campo 'document' é obrigatório.");
        ResponseEntity<Object> apiErrors = new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/person/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRegisterNameBlankValue() throws Exception {
        String request = "{\"document\": \"42166595863\",\"name\": \"     \"}";
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'name' é obrigatório.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/person/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRegisterNameNullValue() throws Exception {
        String request = "{\"document\": \"42166595863\", \"rentBook\": 1,\"name\": null}";
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'name' é obrigatório.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/person/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRegisterWithoutName() throws Exception {
        String request = "{\"document\": \"42166595863\", \"rentBook\": 1,}";
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'name' é obrigatório.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/person/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    /** /consult/{personDocument} **/

    @Test
    void validHandleValidationErrorPathParamPersonDocumentInvalidValue() throws Exception {
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("Informe documento válido.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/person/consult/ABC"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorPathParamPersonDocumentBlankValue() throws Exception {
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("Informe documento válido.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/person/consult/     "))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    /** /delete/{id} **/

    @Test
    void validHandleValidationErrorPathParamIdInvalidValue() throws Exception {
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O parametro 'id' aceita somente dados do tipo UUID.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/person/delete/ABC"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorPathParamIdBlankValue() throws Exception {
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O parametro 'id' é obrigatório na rota.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/person/delete/      "))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    /** /rent-book **/

    @Test
    void validHandleValidationErrorRentBookDocumentInvalidValue() throws Exception {
        RentBookDto request = new RentBookDto("ABC", 1);
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'document' deve ser informado com valor válido.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/rent-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRentBookDocumentNullValue() throws Exception {
        RentBookDto request = new RentBookDto(null, 1);
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'document' é obrigatório.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/rent-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRentBookWithoutDocument() throws Exception {
        String request = "{\"rentBook\": 1}";
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'document' é obrigatório.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/rent-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRentBookDocumentBlankValue() throws Exception {
        String request = "{\"document\": \"      \", \"rentBook\": 1}";
        List<String> messages = Arrays.asList("O campo 'document' deve ser informado com valor válido.","O campo 'document' é obrigatório.");
        ResponseEntity<Object> apiErrors = new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/rent-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRentBookRentBookInvalidValue() throws Exception {
        String request = "{\"document\": \"42166595863\", \"rentBook\": \"ABC\"}";
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'rentBook' aceita somente dados do tipo Integer.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/rent-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRentBookRentBookNullValue() throws Exception {
        RentBookDto request = new RentBookDto("422166595863", null);
        List<String> messages = Arrays.asList("O campo 'document' deve ser informado com valor válido.", "O campo 'rentBook' é obrigatório.");
        ResponseEntity<Object> apiErrors = new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/rent-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorRentBookWithoutRentBook() throws Exception {
        String request = "{\"document\": \"42166595863\"}";
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'rentBook' é obrigatório.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/rent-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    /** /return-book **/

    @Test
    void validHandleValidationErrorReturnBookDocumentInvalidValue() throws Exception {
        RentBookDto request = new RentBookDto("ABC", 1);
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'document' deve ser informado com valor válido.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/return-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorReturnBookDocumentNullValue() throws Exception {
        RentBookDto request = new RentBookDto(null, 1);
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'document' é obrigatório.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/return-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorReturnBookWithoutDocument() throws Exception {
        String request = "{\"rentBook\": 1}";
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'document' é obrigatório.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/return-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorReturnBookDocumentBlankValue() throws Exception {
        String request = "{\"document\": \"      \", \"rentBook\": 1}";
        List<String> messages = Arrays.asList("O campo 'document' deve ser informado com valor válido.","O campo 'document' é obrigatório.");
        ResponseEntity<Object> apiErrors = new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/return-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorReturnBookRentBookInvalidValue() throws Exception {
        String request = "{\"document\": \"42166595863\", \"rentBook\": \"ABC\"}";
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'rentBook' aceita somente dados do tipo Integer.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/return-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorReturnBookRentBookNullValue() throws Exception {
        RentBookDto request = new RentBookDto("422166595863", null);
        List<String> messages = Arrays.asList("O campo 'document' deve ser informado com valor válido.", "O campo 'rentBook' é obrigatório.");
        ResponseEntity<Object> apiErrors = new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/return-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }

    @Test
    void validHandleValidationErrorReturnBookWithoutRentBook() throws Exception {
        String request = "{\"document\": \"42166595863\"}";
        ResponseEntity<Object> apiErrors = new ResponseEntity<>("O campo 'rentBook' é obrigatório.", HttpStatus.BAD_REQUEST);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/return-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(apiErrors)));
    }
}
