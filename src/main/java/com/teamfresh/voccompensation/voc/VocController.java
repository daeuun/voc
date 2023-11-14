package com.teamfresh.voccompensation.voc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voc")
@RequiredArgsConstructor
public class VocController {
    private final VocService vocService;

    /**
     * VOC 목록 조회
     * @return VOC 응답 리스트
     */
    @GetMapping
    public List<VocResponse> getVocs() {
        return vocService.getVocs();
    }

    /**
     * VOC 등록
     * @param request 요청
     * @return VOC 응답
     */
    @PostMapping
    public VocResponse createVoc(@RequestBody VocRequest request) {
        return vocService.createVoc(request);
    }
}
