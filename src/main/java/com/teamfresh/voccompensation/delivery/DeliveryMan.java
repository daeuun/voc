package com.teamfresh.voccompensation.delivery;

import com.teamfresh.voccompensation.BaseTime;
import com.teamfresh.voccompensation.compensation.Compensation;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class DeliveryMan extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "deliveryMan", cascade = CascadeType.ALL)
    private List<Compensation> compensations;
}
