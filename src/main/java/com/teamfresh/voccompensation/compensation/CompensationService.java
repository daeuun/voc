package com.teamfresh.voccompensation.compensation;

import com.teamfresh.voccompensation.delivery.DeliveryMan;
import com.teamfresh.voccompensation.penalty.Penalty;
import com.teamfresh.voccompensation.penalty.PenaltyRepository;
import com.teamfresh.voccompensation.penalty.PenaltyService;
import com.teamfresh.voccompensation.voc.Voc;
import com.teamfresh.voccompensation.voc.VocRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompensationService {
    private final CompensationRepository compensationRepository;
    private final VocRepository vocRepository;
    private final PenaltyRepository penaltyRepository;
    private final PenaltyService penaltyService;

    /**
     * 배상 목록 조회
     * @return 배상 응답 리스트
     */
    public List<CompensationResponse> getCompensations() {
        return compensationRepository.findAll().stream().map(CompensationResponse::from).toList();
    }

    /**
     * 배상 정보 등록
     * @param vocId voc 번호
     * @param penaltyId 패널티 번호
     * @return 배상정보 응답
     */
    public CompensationResponse createCompensation(Long vocId, Long penaltyId) {
        Voc voc = vocRepository.findById(vocId)
                .orElseThrow(() -> new EntityNotFoundException("Voc not found with id: " + vocId));
        Penalty penalty = penaltyRepository.findById(penaltyId)
                .orElseThrow(() -> new EntityNotFoundException("Penalty not found with id: " + penaltyId));
        penaltyService.checkContains(voc, penalty);
        if (!penalty.isApproved()) {
            new IllegalArgumentException("패널티 승인 완료되어야 배상정보를 등록할 수 있다.");
        }
        int charge = penalty.getCharge(); // 패널티 금액이 배상금액
        DeliveryMan deliveryMan = penalty.getDeliveryMan();
        Compensation compensation = Compensation.update(voc, penalty, charge, deliveryMan);
        compensationRepository.save(compensation);
        return CompensationResponse.from(compensation);
    }

}
