//package nl.bioinf.diseasefinderSpring.verification;
//
//import nl.bioinf.diseasefinderSpring.domain.User;
//
//import javax.persistence.*;
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.util.Calendar;
//
///**
// * Created by henridupon on 5/9/2016.
// */
//@Entity
//public class VerificationToken {
//    private static final int EXPIRATION = 60 * 24;
//    private boolean verified;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String token;
//
//    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "user_id")
//    private User user;
//
//    private Date expiryDate;
//
//    public VerificationToken() {
//        super();
//    }
//    public VerificationToken(String token, User user) {
//        super();
//        this.token = token;
//        this.user = user;
//        this.expiryDate = calculateExpiryDate(EXPIRATION);
//        this.verified = false;
//    }
//
//    private Date calculateExpiryDate(int expiryTimeInMinutes) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Timestamp(cal.getTime().getTime()));
//        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
//        return new Date(cal.getTime().getTime());
//    }
//
//    // standard getters and setters
//}