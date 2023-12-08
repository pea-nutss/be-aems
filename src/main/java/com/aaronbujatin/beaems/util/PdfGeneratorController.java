package com.aaronbujatin.beaems.util;

import com.aaronbujatin.beaems.booking.Booking;
import com.aaronbujatin.beaems.booking.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/generate")
@CrossOrigin(origins = "*")
public class PdfGeneratorController {

    private final PdfGeneratorService pdfGeneratorService;
    private final BookingRepository bookingRepository;

    @GetMapping("/view/{id}")
    public ResponseEntity<InputStreamResource> viewWeddingContract(@PathVariable String id) throws IOException {

        Booking booking = bookingRepository.findById(id).get();

        String isFilePath = "src\\main\\resources\\pdfs\\" + booking.getGroomName() + "_" +booking.getBrideName() +  "_" +"wedding_contract.pdf";
        Path path = Paths.get(isFilePath);

        if (Files.exists(path)){
            FileInputStream fileInputStream = new FileInputStream(isFilePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            headers.add("Content-Disposition", "inline; filename=" + booking.getGroomName() + "_" +booking.getBrideName() +  "_" +"wedding_contract.pdf");

            InputStreamResource pdfFile = new InputStreamResource(fileInputStream);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfFile);
        } else {
            pdfGeneratorService.generateWeddingContractPdf(id);
            String filePath = "src\\main\\resources\\pdfs\\" + booking.getGroomName() + "_" +booking.getBrideName() +  "_" +"wedding_contract.pdf";
            FileInputStream fileInputStream = new FileInputStream(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=" + booking.getGroomName() + "_" +booking.getBrideName() +  "_" +"wedding_contract.pdf");

            InputStreamResource pdfFile = new InputStreamResource(fileInputStream);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfFile);
        }

    }

    @GetMapping("/view")
    public ResponseEntity<InputStreamResource> viewWeddingContract() throws IOException {
        String filePath = "src\\main\\resources\\pdfs\\wedding_contract.pdf";
        Path path = Paths.get(filePath);

        if (Files.exists(path)){
            FileInputStream fileInputStream = new FileInputStream(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=wedding_contract.pdf");

            InputStreamResource pdfFile = new InputStreamResource(fileInputStream);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfFile);
        }
        return null;
    }
}
