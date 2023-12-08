package com.aaronbujatin.beaems.booking;


import com.aaronbujatin.beaems.payments.Payment;
import com.aaronbujatin.beaems.payments.PaymentRepository;
import com.aaronbujatin.beaems.registration.RegistrationFee;
import com.aaronbujatin.beaems.registration.RegistrationFeeRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingService {

    private final JavaMailSender emailSender;
    private final BookingRepository bookingRepository;
    private final RegistrationFeeRepository registrationPaymentRepository;
    private final PaymentRepository paymentRepository;

    public Booking save(Booking booking) throws MessagingException {

        boolean isBookingEventNameAlreadyExistSabiNiJephineson = bookingRepository.existsByEventName(booking.getEventName());

        if(isBookingEventNameAlreadyExistSabiNiJephineson){
            throw new IllegalStateException("Event name already exist");
        }

        RegistrationFee registrationPayment = new RegistrationFee();
        registrationPayment.setCardNumber(booking.getRegistrationFee().getCardNumber());
        registrationPayment.setFullName(booking.getRegistrationFee().getFullName());
        registrationPayment.setExpirationDate(booking.getRegistrationFee().getExpirationDate());
        registrationPayment.setCvv(booking.getRegistrationFee().getCvv());
        registrationPayment.setAmountFee(booking.getRegistrationFee().getAmountFee());
        registrationPaymentRepository.save(registrationPayment);

        Payment payment = new Payment();
        payment.setDate(LocalDate.now());
        payment.setEventName(booking.getEventName());
        payment.setAmount(booking.getRegistrationFee().getAmountFee());
        payment.setSelectedPackage(booking.getSelectedPackage());
        payment.setPackageValue(booking.getPackageRate());
        payment.setClientName(booking.getClientName());
        payment.setModeOfPayment("Cash");
        paymentRepository.save(payment);

        booking.setBookingDate(LocalDate.now());
        booking.setBookingStatus("PENDING");
        booking.setOrganizerName("PENDING");
        booking.setBookingSource("Online");
        booking.setPaymentStatus("Partially Paid");

        sendEmail(booking);
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(String id){
        return bookingRepository.findById(id).get();
    }

    public List<Booking> getAllBooking(){
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingByOrganizerName(String organizerName){
        return bookingRepository.findByOrganizerName(organizerName);
    }

    private void sendEmail(Booking booking) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        LocalDate meetingDeadline = booking.getBookingDate().plusMonths(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        String formattedDateDeadline = meetingDeadline.format(formatter);

        helper.setTo(booking.getEmail());
        helper.setSubject("Your Reservation");
        String emailText = "Dear " + booking.getClientName() + ",\n\n" +
                "We hope this message finds you well and that you are excited about your upcoming wedding event! We understand that planning a wedding can be a busy and exciting time, but there are some important details that require your immediate attention to ensure the success of your event.\n\n" +
                "As your dedicated event organizer, we have been working diligently to make sure your wedding day is nothing short of perfect. To help us make this dream a reality, we kindly request that you schedule a meeting with us within the next month.\n\n" +
                "Why the Meeting is Important:\n" +
                "1. Payment Confirmation: During this meeting, we will finalize the payment details. It is essential to confirm and complete the payment to secure your reservation and all the services we've arranged for your special day.\n\n" +
                "2. Last-Minute Details: We will discuss any last-minute details and preferences you might have, ensuring that everything is in place for your big day.\n\n" +
                "3. Vendor Coordination: We will provide updates on vendor coordination, ensuring a smooth flow of events on your wedding day.\n\n" +
                "Please schedule this meeting by " + formattedDateDeadline +" to avoid any inconvenience. If we do not receive a confirmation of your payment and a meeting scheduled by this deadline, we will regretfully have to nullify your reservation.\n\n" +
                "To schedule the meeting, please reply to this email. We are here to answer any questions or concerns you may have and to assist you every step of the way.\n\n" +
                "We appreciate your prompt attention to this matter, as it will enable us to continue our preparations for your wedding day. We are committed to making your event a memorable and joyous occasion.\n\n" +
                "Thank you for choosing us as your event organizer, and we look forward to meeting with you soon to ensure the success of your wedding.\n\n" +
                "Warm regards,\n\n" +

                "Sweet Serenity Wedding Event";
        helper.setText(emailText);

        emailSender.send(message);
    }



}
