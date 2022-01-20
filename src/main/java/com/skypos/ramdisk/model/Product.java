package com.skypos.ramdisk.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
   /* @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @SequenceGenerator(name = "seqGen",sequenceName = "customer_id_seq",initialValue = 1,allocationSize = 1)*/
    //@GeneratedValue(strategy = GenerationType.TABLE)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @GeneratedValue(generator = "userGen")
    @GenericGenerator(name="userGen",strategy = "increment")
    @Column(name = "product_id")
    private Long id;
    @Column(name= "name")
    private String name;

}
