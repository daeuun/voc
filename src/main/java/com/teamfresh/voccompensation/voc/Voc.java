package com.teamfresh.voccompensation.voc;

import com.teamfresh.voccompensation.BaseTime;
import com.teamfresh.voccompensation.compensation.Compensation;
import com.teamfresh.voccompensation.penalty.Penalty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Voc extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private Long clientId;

    /** 귀책 대상 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClaimType type;

    /** 귀책 사유 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClaimReasonType reasonType;

    /** 패널티 */
    @OneToMany(mappedBy = "voc", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Penalty> penalties  = new ArrayList<>();

    /** 배상정보 */
    @OneToMany(mappedBy = "voc", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compensation> compensations = new ArrayList<>();

    @Builder
    public Voc(String content, Long clientId, ClaimType type, ClaimReasonType reasonType, List<Penalty> penalties, List<Compensation> compensations) {
        this.content = content;
        this.clientId = clientId;
        this.type = type;
        this.reasonType = reasonType;
        this.penalties = new ArrayList<>();
        this.compensations = new ArrayList<>();
    }

    public void addPenalty(Penalty penalty) {
        penalties.add(penalty);
    }
}
