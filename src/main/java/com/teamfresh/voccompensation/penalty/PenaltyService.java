package com.teamfresh.voccompensation.penalty;

import com.teamfresh.voccompensation.compensation.CompensationService;
import com.teamfresh.voccompensation.delivery.DeliveryMan;
import com.teamfresh.voccompensation.voc.Voc;
import com.teamfresh.voccompensation.voc.VocRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PenaltyService {
    private final VocRepository vocRepository;
    private final CompensationService compensationService;
    private final PenaltyRepository penaltyRepository;

    /**
     * 귀책 금액 기사에게 패널티 등록
     * @param voc
     * @param deliveryMan
     * @param charge
     * @return 패널티
     */
    public Penalty createPenaltyByDelivery(Voc voc, DeliveryMan deliveryMan, int charge) {
        return Penalty.create(voc, deliveryMan, charge);
    }

    /**
     * 배송기사의 패널티 확인 및 승인 여부 등록
     * @param penaltyId 패널티 번호
     * @param vocId voc 번호
     * @Param approved 기사의 패널티 승인 여부
     * @return 패널티 응답
     */
    @Transactional
    public PenaltyResponse checkPenalty(Long penaltyId, Long vocId, boolean approved) {
        Voc voc = vocRepository.findById(vocId)
                .orElseThrow(() -> new EntityNotFoundException("Voc not found with id: " + vocId));
        Penalty penalty = penaltyRepository.findById(penaltyId)
                .orElseThrow(() -> new EntityNotFoundException("Penalty not found with id: " + penaltyId));
        checkContains(voc, penalty);
        if (approved) {
            if (penalty.getReadAt() == null) {
                penalty.approvePenalty();
            } else {
                penalty.updatePenaltyToApprove();
            }
            // 패널티 승인 시 배상 정보 등록
            compensationService.createCompensation(voc.getId(), penalty.getId());
        } else {
            penalty.holdPenalty();
        }
        penaltyRepository.save(penalty);
        return PenaltyResponse.from(penalty);
    }

    /**
     * 해당 패널티가 voc에 존재하는지 검증
     * @param voc
     * @param penalty
     */
    public void checkContains(Voc voc, Penalty penalty) {
        if (!voc.getPenalties().contains(penalty)) {
            throw new IllegalArgumentException("Penalty: " + penalty.getId() + " does not contain Voc with id: " + voc.getId());
        }
    }
}
