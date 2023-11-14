package com.teamfresh.voccompensation.client;

import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    /** 담당자 */
    @Column(nullable = false)
    private String manager;

    @Column(nullable = false)
    private String managerPhoneNumber;

    @Column(nullable = false)
    private String managerEmail;
}
