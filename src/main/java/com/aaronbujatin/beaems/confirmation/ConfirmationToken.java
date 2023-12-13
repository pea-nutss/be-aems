package com.aaronbujatin.beaems.confirmation;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
@Data
@Document("confirmation_tokens")
public class ConfirmationToken {

    @Id
    private String tokenId;
    private String confirmationToken;
    private String guestId;
    private LocalDate createdDate;

}


