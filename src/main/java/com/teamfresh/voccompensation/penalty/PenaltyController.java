package com.teamfresh.voccompensation.penalty;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/penalty")
@RequiredArgsConstructor
public class PenaltyController {
    private final PenaltyService penaltyService;

    /**
     * 패널티 확인 및 승인 여부 등록
     * @param penaltyId
     * @param vocId
     * @param approved
     */
    @PostMapping("/{penaltyId}")
    public PenaltyResponse checkPenalty(
            @PathVariable Long penaltyId,
            @RequestParam Long vocId,
            @RequestParam Boolean approved) {
        return penaltyService.checkPenalty(penaltyId, vocId, approved);
    }
}
