package com.teamfresh.voccompensation.penalty;

import com.teamfresh.voccompensation.BaseTime;
import com.teamfresh.voccompensation.delivery.DeliveryMan;
import com.teamfresh.voccompensation.voc.Voc;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
public class Penalty extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voc_id", nullable = false)
    private Voc voc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_man_id", nullable = false)
    private DeliveryMan deliveryMan;

    /** 패널티 금액 */
    @Column(nullable = false)
    private int charge;

    /** 기사가 확인한 날짜 (확인여부 판별) */
    @Column
    private LocalDateTime readAt;

    /** 기사 이의제기 여부 */
    @Column
    private boolean approved;

    public Penalty(Voc voc, DeliveryMan deliveryMan, int charge) {
        this.voc = voc;
        this.deliveryMan = deliveryMan;
        this.charge = charge;
    }

    public static Penalty create(Voc voc, DeliveryMan deliveryMan, int charge) {
        return new Penalty(voc, deliveryMan, charge);
    }

    public void approvePenalty() {
        this.readAt = LocalDateTime.now();
        this.approved = true;
    }

    public void holdPenalty() {
        this.readAt = LocalDateTime.now();
        this.approved = false;
    }

    public void updatePenaltyToApprove() {
        this.approved = true;
    }
}
