package com.examle.esa.controller.version2;

import com.examle.esa.service.DrugService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import com.examle.esa.dto.DrugDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.TEXT_HTML;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/version2/drug")
public class DrugRestController {

    private final DrugService drugService;
    private final XmlMapper xmlMapper;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestHeader("Accept") String acceptHeader) {
        var drugs = drugService.getAll()
                .stream()
                .map(DrugDTO::toDTO)
                .collect(Collectors.toList());

        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            try {
                Transformer transformer = TransformerFactory
                        .newInstance()
                        .newTemplates(new StreamSource("src/main/resources/xslt/drugs.xslt"))
                        .newTransformer();
                StringWriter writer = new StringWriter();
                transformer.transform(new StreamSource(
                                new ByteArrayInputStream(xmlMapper.writeValueAsBytes(drugs))),
                        new StreamResult(writer));

                return ResponseEntity.ok().contentType(TEXT_HTML).body(writer.toString());
            } catch (TransformerException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        else if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) ||
                acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE) ||
                acceptHeader.contains(MediaType.ALL_VALUE))
            return ResponseEntity.ok(drugs);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id,
                                     @RequestHeader("Accept") String acceptHeader) {
        var drug = DrugDTO.toDTO(drugService.getById(id));

        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            try {
                Transformer transformer = TransformerFactory
                        .newInstance()
                        .newTemplates(new StreamSource("src/main/resources/xslt/drug.xslt"))
                        .newTransformer();
                StringWriter writer = new StringWriter();
                transformer.transform(new StreamSource(
                                new ByteArrayInputStream(xmlMapper.writeValueAsBytes(drug))),
                        new StreamResult(writer));

                return ResponseEntity.ok().contentType(TEXT_HTML).body(writer.toString());
            } catch (TransformerException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        else if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) ||
                acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE) ||
                acceptHeader.contains(MediaType.ALL_VALUE))
            return ResponseEntity.ok(drug);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(path = "/create",
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> postCreate(@RequestBody DrugDTO dto) {
        drugService.create(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        drugService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}

