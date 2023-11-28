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
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GuestService {

    @Autowired
    private JavaMailSender emailSender;

    private final GuestRepository guestRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;


    public Guest saveGuest(Guest guest) throws IOException, WriterException, MessagingException {

        String qrCodeText = "Name: " + guest.getFirstName() + " " +  guest.getLastName() +
                "\nEmail: " + guest.getEmail() +
                "\nRelatedness: " + guest.getRelatedness();

        byte[] qrCodeImageBytes = QRCodeGenerator.generateQRCodeImage(qrCodeText, 300, 300);
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

    public Guest getGuestById(String id){
        return guestRepository.findById(id).get();
    }

    private void sendEmail(byte[] qrCodeImageBytes, Guest guest, ConfirmationToken confirmationToken ) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(guest.getEmail());
        helper.setSubject("Your Guest QrCode");
        helper.setText("Dear " + guest.getFirstName() + " " + guest.getLastName() +",\n\nPlease find the attached QR code.");
        helper.setText("To confirm your attendance, please click here : "
                +"https://be-aems-production.up.railway.app/api/v1/guests/confirm-account?token="+confirmationToken.getConfirmationToken());
        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        // Attach the QR code image to the email
        ByteArrayResource resource = new ByteArrayResource(qrCodeImageBytes);
        helper.addAttachment("qrcode.png", resource);

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
