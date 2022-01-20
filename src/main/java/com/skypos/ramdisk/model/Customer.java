package com.skypos.ramdisk.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
   /* @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @SequenceGenerator(name = "seqGen",sequenceName = "customer_id_seq",initialValue = 1,allocationSize = 1)*/
    //@GeneratedValue(strategy = GenerationType.TABLE)
    @GeneratedValue(generator = "userGen")
    @GenericGenerator(name="userGen",strategy = "increment")
    @Column(name = "customer_id")
    private Long id;
    @Column(name= "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
}
