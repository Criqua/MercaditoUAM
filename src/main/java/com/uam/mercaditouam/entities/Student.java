package com.uam.mercaditouam.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Estudiante")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student extends User{
    @Column(name = "Telefono")
    private String phoneNumber;

    @Column(name = "Descripcion_Personal")
    private String personalDescription;

    @ElementCollection
    @Column(name = "ID_Seguidor")
    private Set<Long> follower;

    @ElementCollection
    @Column(name = "ID_Seguido")
    private Set<Long> following;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "student")
    private Image profileImage;
    //@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "studentCIF",fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Publication> publicationList;

    @OneToMany(mappedBy = "senderStudent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Messaging> sentMessages;

    @OneToMany(mappedBy = "recipientStudent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Messaging> receivedMessages;

    @OneToMany(mappedBy = "requesterStudent", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;

    //@OneToMany(mappedBy = "studentId", fetch = FetchType.LAZY)
    //private List<MainComment> mainCommentList;

    @OneToMany(mappedBy = "studentId", fetch = FetchType.LAZY)
    private List<Purchase> purchaseList;
}