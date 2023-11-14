package com.teamfresh.voccompensation.compensation;

import com.teamfresh.voccompensation.BaseTime;
import com.teamfresh.voccompensation.delivery.DeliveryMan;
import com.teamfresh.voccompensation.penalty.Penalty;
import com.teamfresh.voccompensation.voc.Voc;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Compensation extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** 배상 금액 */
    @Column(nullable = false)
    private int charge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voc_id", nullable = false)
    private Voc voc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "penalty_id", nullable = false)
    private Penalty penalty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_man_id", nullable = false)
    private DeliveryMan deliveryMan;

    public Compensation(Voc voc, Penalty penalty, int charge, DeliveryMan deliveryMan) {
        this.voc = voc;
        this.penalty = penalty;
        this.charge = charge;
        this.deliveryMan = deliveryMan;
    }

    public static Compensation update(Voc voc, Penalty penalty, int charge, DeliveryMan deliveryMan) {
        return new Compensation(voc, penalty, charge, deliveryMan);
    }
}
