package com.noveogroup.tulupov.addressbook.model;

import com.noveogroup.tulupov.addressbook.validator.ContentType;
import com.noveogroup.tulupov.addressbook.validator.Length;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.noveogroup.tulupov.addressbook.model.Contact.*;

/**
 * Contact entity.
 */
@Entity
@Table(name = TABLE_NAME)
@ToString
@SuppressWarnings("unused")
public class Contact {
    public static final String TABLE_NAME = "Contact";
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String IP = "ip";
    public static final String PHOTO = "photo";


    @Id
    @Column(name = ID)
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Column(name = FIRST_NAME)
    @Getter
    @Setter
    @Size(min = 3, max = 30, message = "{error.contact.firstname.invalid_size}")
    private String firstname;

    @Column(name = LAST_NAME)
    @Getter
    @Setter
    @Size(min = 3, max = 30, message = "{error.contact.lastname.invalid_size}")
    private String lastname;

    @Column(name = EMAIL)
    @Getter
    @Setter
    @Email(message = "{error.contact.email.invalid_email}")
    @NotEmpty(message = "{error.contact.email.empty}")
    private String email;

    @Column(name = PHONE)
    @Getter
    @Setter
    @Pattern(regexp = "^[0-9\\-\\+]{3,15}$",
            message = "{error.contact.phone.invalid_format}")
    @NotEmpty(message = "{error.contact.phone.empty}")
    private String phone;

    @Column(name = IP)
    @Getter
    @Setter
    private Integer ip;

    @Column(name = PHOTO)
    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Getter
    @Setter
    private byte[] photo;

    @Getter
    @Setter
    @ContentType(value = "image/jpeg", message = "{error.contact.file.invalid_type}")
    @Length(value = 1048576, message = "{error.contact.file.invalid_size}")
    private transient MultipartFile file;
}
