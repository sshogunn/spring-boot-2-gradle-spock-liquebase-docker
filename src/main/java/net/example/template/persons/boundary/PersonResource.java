package net.example.template.persons.boundary;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import net.example.template.persons.control.PersonCreateService;
import net.example.template.persons.entity.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@AllArgsConstructor
public class PersonResource {
    private final PersonCreateService createService;

    @ApiOperation(
            value = "An example of simple REST method with Swagger documentation",
            notes = "Don't try to find any logic in this REST service, it is just an example"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Use API response to explain REST behavior")
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Person person) {
        createService.create(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
