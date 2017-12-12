package net.example.template.persons.boundary;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import net.example.template.persons.control.PersonPhotoUploader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@AllArgsConstructor
public class PersonProfileResource {
    private final PersonPhotoUploader personPhotoUploader;


    @ApiOperation(
            value = "Upload profile photo",
            notes = "REST service is created to check combination of Azure BLOB Storage and Azure Search Service"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "The file is uploaded and saved.")
    })
    @PostMapping(value = "/{personId}/photo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity uploadFile(@PathVariable("personId") String personId, @RequestParam("file") MultipartFile file) {
        personPhotoUploader.upload(personId, extractFrom(file), file.getSize(), file.getOriginalFilename());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static InputStream extractFrom(MultipartFile file) {
        try {
            return file.getInputStream();
        } catch (IOException e) {
            throw new AttachmentContentException("There is a problem with attached file", e);
        }
    }

    private static class AttachmentContentException extends RuntimeException {
        AttachmentContentException(String s, IOException e) {
            super(s, e);
        }
    }
}
