package com.aaronbujatin.beaems.confirmation;

import com.aaronbujatin.beaems.guest.Guest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Document("confirmation_tokens")
public class ConfirmationToken {

    @Id
    private String tokenId;
    private String confirmationToken;
    private String guestId;
    private LocalDate createdDate;

}
