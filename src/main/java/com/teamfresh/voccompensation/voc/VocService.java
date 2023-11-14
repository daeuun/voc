package com.teamfresh.voccompensation.voc;

import com.teamfresh.voccompensation.delivery.DeliveryMan;
import com.teamfresh.voccompensation.delivery.DeliveryManRepository;
import com.teamfresh.voccompensation.penalty.Penalty;
import com.teamfresh.voccompensation.penalty.PenaltyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocService {
    private final VocRepository vocRepository;
    private final PenaltyService penaltyService;
    private final DeliveryManRepository deliveryManRepository;

    /**
     * voc 목록 조회
     * @return VOC 응답 리스트
     */
    public List<VocResponse> getVocs() {
        return vocRepository.findAll().stream().map(VocResponse::from).toList();
    };

    /**
     * voc 등록 요청
     * @param request (귀책 정보)
     * @return VOC 응답
     */
    @Transactional
    public VocResponse createVoc(VocRequest request) {
        Voc voc = request.toEntity();
        // 운송사에 대한 귀책이라면 해당 기사에게 패널티 등록 (임의의 패널티 정보 등록)
        if (voc.getType().name().equals("DELIVERY")) {
            int charge = 3000;
            DeliveryMan deliveryMan = deliveryManRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("DeliveryMan not found with id: " + 1L));
            Penalty penalty = penaltyService.createPenaltyByDelivery(voc, deliveryMan, charge);
            voc.addPenalty(penalty);
        }
        vocRepository.save(voc);
        return VocResponse.from(voc);
    }
}
