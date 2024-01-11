package com.examle.esa.controller.version2;

import com.examle.esa.service.PharmacyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import com.examle.esa.dto.PharmacyDTO;
import com.examle.esa.service.PharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.TEXT_HTML;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/version2/pharmacy")
public class PharmacyRestController {

    private final PharmacyService pharmacyService;
    private final XmlMapper xmlMapper;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestHeader("Accept") String acceptHeader) {
        var pharmacies = pharmacyService.getAll()
                .stream()
                .map(PharmacyDTO::toDTO)
                .collect(Collectors.toList());

        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            try {
                Transformer transformer = TransformerFactory
                        .newInstance()
                        .newTemplates(new StreamSource("src/main/resources/xslt/pharmacies.xslt"))
                        .newTransformer();
                StringWriter writer = new StringWriter();
                transformer.transform(new StreamSource(
                                new ByteArrayInputStream(xmlMapper.writeValueAsBytes(pharmacies))),
                        new StreamResult(writer));

                return ResponseEntity.ok().contentType(TEXT_HTML).body(writer.toString());
            }
            catch (TransformerException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        else if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) ||
                acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE) ||
                acceptHeader.contains(MediaType.ALL_VALUE))
            return ResponseEntity.ok(pharmacies);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id,
                                     @RequestHeader("Accept") String acceptHeader) {
        var pharmacy = PharmacyDTO.toDTO(pharmacyService.getById(id));

        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            try {
                Transformer transformer = TransformerFactory
                        .newInstance()
                        .newTemplates(new StreamSource("src/main/resources/xslt/pharmacy.xslt"))
                        .newTransformer();
                StringWriter writer = new StringWriter();
                transformer.transform(new StreamSource(
                                new ByteArrayInputStream(xmlMapper.writeValueAsBytes(pharmacy))),
                        new StreamResult(writer));
                return ResponseEntity.ok().contentType(TEXT_HTML).body(writer.toString());
            } catch (TransformerException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        else if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) ||
                acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE) ||
                acceptHeader.contains(MediaType.ALL_VALUE))
            return ResponseEntity.ok(pharmacy);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(path = "/create",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> postCreate(@RequestBody PharmacyDTO dto) {
        pharmacyService.create(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        pharmacyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}