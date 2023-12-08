package com.aaronbujatin.beaems.util;

import com.aaronbujatin.beaems.booking.Booking;
import com.aaronbujatin.beaems.booking.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class PdfGeneratorService {

    private final BookingRepository bookingRepository;

    public void generateWeddingContractPdf(String id) {

        Booking booking = bookingRepository.findById(id).get();

        if(booking != null) {
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    // Set font and font size
                    contentStream.setFont(PDType1Font.HELVETICA, 14);

//                    String imagePathString  = "resources\\images\\sweet-serenity-pdf-logo.png";
//                    Path imagePath = Paths.get(imagePathString);
//                    PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath.toString(), document);
                    InputStream inputStream = getClass().getResourceAsStream("/images/sweet-serenity-pdf-logo.png");
                    PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, IOUtils.toByteArray(inputStream), "sweet-serenity-pdf-logo");

                    contentStream.drawImage(pdImage, 25, 570, 220, 220);

                    contentStream.beginText();
                    contentStream.newLineAtOffset(435, 690);
                    contentStream.setNonStrokingColor(Color.decode("#681B16"));
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 20);
                    contentStream.showText("Rodriguez, Rizal");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(450, 620);
                    contentStream.setNonStrokingColor(Color.decode("#F480E8"));
                    contentStream.setFont(PDType1Font.HELVETICA, 20);
                    contentStream.showText("09212924651");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(442, 600);
                    contentStream.setNonStrokingColor(Color.decode("#681B16"));
                    contentStream.setFont(PDType1Font.HELVETICA, 11);
                    contentStream.showText("sweetserenity@gmail.com");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(413, 580);
                    contentStream.setNonStrokingColor(Color.decode("#681B16"));
                    contentStream.setFont(PDType1Font.HELVETICA, 11);
                    contentStream.showText("https://sweet-serenity.vercel.app");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(190, 525);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
                    contentStream.showText("Wedding Planning Contract");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 490);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("Agreement mode between Sweet Serenity Wedding Event Planning and the bride ");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 475);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText(booking.getBrideName() + " and the groom " + booking.getGroomName() + ".");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 445);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                    contentStream.showText("CHANGE/CANCELLATIONS");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 430);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("Any changes/cancellation made to this contract must be made in writing and signed by all parties.");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 405);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("In the event the wedding couple is forced to change the date of the wedding, every effort will be made by");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 390);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("Sweet Serenity Wedding Event Planning to transfer location reservations, sub-contractors and The Wedding");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 375);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("Coordinator support to the new date. The wedding couple agrees that in the event of a date change any");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 360);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("expenses including but not limited to deposits and fees that are non-refundable and non-transferable are");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 345);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("the sole responsibility of the wedding couple. There may also be additional charges above and beyond those");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 330);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("set in the original contract. The wedding couple further understands that last minute changes can impact the");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 315);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("quality of the event and that The Wedding Coordinator is not responsible for these compromises in quality. If");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 300);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("the bridal couple changes the date/location of the wedding and the wedding planner is unavailable to provide");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 285);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("services, then the wedding planner is released from all contract obligations and shall in no way be held");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 270);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("responsible or liable for non-performance. The couple also forfeits all costs for this agreement.");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 245);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("Sweet Serenity Wedding Event Planning will always do its best to find the top suppliers wherever the");
                    contentStream.endText();
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 230);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("wedding location is, but we cannot be held responsible for any supplier/service provider's performance.");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 205);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                    contentStream.showText("Cancellations by the bride & groom:");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 190);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("In the event of a cancellation, refunds are limited to unearned fees. Deposits are non refundable and any");
                    contentStream.endText();
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 175);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("earned fee will have to be paid in full within 30 days of cancellation.");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 150);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                    contentStream.showText("Cancellations by Sweet Serenity Wedding Event Planning : ");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 135);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("Should planner Creations be unable to perform any specific tasks in the planning of a wedding, due to the");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 120);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("wedding planner illness or hospitalization, unearned fees will be refunded and we will do our");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 105);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("our best to find a substitute Wedding Planner.");
                    contentStream.endText();

                    float lineWidth = 1.5f; // Adjust as needed
                    float startX = 50;
                    float endX = 570; // Adjust the ending point of the underline as needed
                    float yPosition = 80;
                    // Set the line dash pattern to create a solid line
                    // Set the line width
                    contentStream.setLineWidth(lineWidth);
                    // Draw the underline
                    contentStream.moveTo(startX, yPosition);
                    contentStream.lineTo(endX, yPosition);
                    contentStream.stroke();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(230, 60);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    contentStream.showText("Sweet Serenity Wedding Event Planning");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(170, 45);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
                    contentStream.showText("Rodriguez, Rizal | Contact# 09212924651 | sweetserenity@gmail.com");
                    contentStream.endText();
                }
                String filePath = "src\\main\\resources\\pdfs\\" + booking.getGroomName() + "_" +booking.getBrideName() + "_" +"wedding_contract.pdf";
                File file = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(file);
                document.save(outputStream); // Save the PDF
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new UsernameNotFoundException("The user with id of " + id + " was not found!");
        }
    }
}
