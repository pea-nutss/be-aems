package com.aaronbujatin.beaems.client;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InquiriesService {


    private final InquiriesRepository userRepository;
    private final JavaMailSender javaMailSender;


    public Inquiries save(Inquiries user) {

        try {

            String emailContent = "Dear " + user.getBrideName() +" & " + user.getGroomName() + ",\n\n"
                    + "Thank you for your inquiry. We have received your details.\n"
                    + "Your Selected package: " + user.getSelectedPackage() + "\n\n"
                    + "We will get back to you with more information soon.\n\n"
                    + "Best regards,\n"
                    + "Sweet Serenity Wedding Event";

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Confirmation for your Wedding Event Inquiry");
            message.setText(emailContent);
            javaMailSender.send(message);

            user.setStartDate(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }

    public Inquiries findById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public List<Inquiries> findAll(){
        return userRepository.findAll();
    }

    public String deleteById(String id){
        userRepository.deleteById(id);
        return "User id:" + id + " was successfully deleted";

    }

}
