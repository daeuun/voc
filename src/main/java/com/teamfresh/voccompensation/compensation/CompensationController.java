package com.teamfresh.voccompensation.compensation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compensation")
@RequiredArgsConstructor
public class CompensationController {
    private final CompensationService compensationService;

    /**
     * 배상 목록 조회
     * @return 배상정보 응답 리스트
     */
    @GetMapping
    public List<CompensationResponse> getCompensations() {
        return compensationService.getCompensations();
    }

    /**
     * 배상정보 등록
     * @param vocId VOC 번호
     * @param penaltyId
     * @return 배상정보 응답
     */
    @PostMapping
    public CompensationResponse createCompensation(
            @RequestParam("vocId") Long vocId,
            @RequestParam("penaltyId") Long penaltyId) {
        return compensationService.createCompensation(vocId, penaltyId);
    }
}
