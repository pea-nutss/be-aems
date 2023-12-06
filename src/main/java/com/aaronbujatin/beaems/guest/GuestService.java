package com.aaronbujatin.beaems.guest;


import com.aaronbujatin.beaems.confirmation.ConfirmationToken;
import com.aaronbujatin.beaems.confirmation.ConfirmationTokenRepository;
import com.aaronbujatin.beaems.util.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GuestService {

    @Autowired
    private JavaMailSender emailSender;

    private final GuestRepository guestRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;


    public Guest saveGuest(Guest guest) throws IOException, WriterException, MessagingException {

        Random random = new Random();

        // Generate a random integer (e.g., within the range of 100000 and 999999)
        int minRange = 100000;
        int maxRange = 999999;
        int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;

        int qrCodeText = randomNumber;
        guest.setQrCodeValue(qrCodeText);

        byte[] qrCodeImageBytes = QRCodeGenerator.generateQRCodeImage(String.valueOf(qrCodeText), 300, 300);
        guest.setQrCodeImage(QRCodeGenerator.generateQRCodeImage(generateQRCodeText(guest), 300, 300));
        guest.setStatus("undecided");
        Guest savedGuest = guestRepository.save(guest);
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setGuestId(guest.getId());
        confirmationToken.setConfirmationToken(UUID.randomUUID().toString());
        confirmationToken.setCreatedDate(LocalDate.now());
        confirmationTokenRepository.save(confirmationToken);

        sendEmail(qrCodeImageBytes, guest, confirmationToken);

        return savedGuest;
    }
    private String generateQRCodeText(Guest guest) {
        return "Name: " + guest.getFirstName() + " " + guest.getLastName() +
                "\nEmail: " + guest.getEmail() +
                "\nRelatedness: " + guest.getRelatedness();
    }

    public Guest getGuestById(String id){
        return guestRepository.findById(id).get();
    }

    private void sendEmail(byte[] qrCodeImageBytes, Guest guest, ConfirmationToken confirmationToken ) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(guest.getEmail());
        helper.setSubject("Important Wedding Event Notice");

        String emailText = "Dear " + guest.getFirstName() + " " +guest.getLastName() + ",\n\n" +
            "We hope this message finds you in good spirits. As the date of our upcoming event draws near, \nwe want to ensure that your experience is both enjoyable and hassle-free.\n\n"+
                "1. ID Requirement:\n"+
                "To facilitate a smooth check-in process and enhance the overall security of the event,\n"+
                "we kindly request that you bring a valid form of identification with you. Accepted forms\n"+
                "include a driver's license, passport, or any government-issued ID.\n\n"+

                "2. Confirm Your event Attendance:\n"+
                "To confirm your attendance and provide any additional details, please click \n on the following link: " +
                "https://be-aems-production.up.railway.app/api/v1/guests/confirm-account?token="+confirmationToken.getConfirmationToken() +"\n\n"+

                "3. Your qrcode:\n"+
                "Additionally, we have attached a QR code to this email, which serves as your\n"+
                "personalized confirmation for attending the event. Please keep this QR code \n"+
                "accessible, either on your mobile device or in printed form, as it will expedite your\n"+
                "entry.Please see below.\n";
        // Attach the QR code image to the email
        ByteArrayResource resource = new ByteArrayResource(qrCodeImageBytes);
        helper.addAttachment("qrcode.png", resource);

        emailText += "\nThank you for your cooperation, and we look forward to welcoming you to the event for an unforgettable experience.\n\n" +
                "Best regards,\nWedding event management system";
//
//        helper.setText("Dear " + guest.getFirstName() + " " + guest.getLastName() +",\n\nPlease find the attached QR code.");
//        helper.setText("To confirm your attendance, please click here : "
//                +"https://be-aems-production.up.railway.app/api/v1/guests/confirm-account?token="+confirmationToken.getConfirmationToken());
//        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());
        helper.setText(emailText);
        emailSender.send(message);
    }

    public String confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null){
            Guest guest = guestRepository.findById(token.getGuestId()).orElse(null);
            guest.setStatus("confirmed");
            guestRepository.save(guest);
            return "You successfully confirm your attendance!";
        }
        return "Error: Couldn't verify email";
    }

    public List<Guest> getAllByEventNameAndStatus(String eventName, String status){
        return guestRepository.findByEventNameReferenceAndStatus(eventName, status);
    }

    public List<Guest> getAllGuest(){
        return guestRepository.findAll();
    }

    public List<Guest> searchGuestsByFirstNameOrLastName(String eventName,String query) {
        return guestRepository.findByEventNameReferenceAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                eventName, query, eventName, query);
    }

    public List<Guest> getAllByEventNameReference(String eventNameReference){
        return guestRepository.findByEventNameReference(eventNameReference);
    }





}
