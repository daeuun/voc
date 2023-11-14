package com.teamfresh.voccompensation.voc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teamfresh.voccompensation.compensation.CompensationResponse;
import com.teamfresh.voccompensation.penalty.PenaltyResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class VocResponse {
    private Long id;

    private String content;

    private Long clientId;

    private ClaimType type; // 귀책 대상

    private ClaimReasonType reasonType; // 귀책 사유

    private List<PenaltyResponse> penalties; // 패널티

    private List<CompensationResponse> compensations; // 배상정보

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateAt;

    public static VocResponse from(Voc voc) {
        return VocResponse.builder()
                .id(voc.getId())
                .content(voc.getContent())
                .clientId(voc.getClientId())
                .type(voc.getType())
                .reasonType(voc.getReasonType())
                .penalties(voc.getPenalties().stream().map(PenaltyResponse::from).toList())
                .compensations(voc.getCompensations().stream().map(CompensationResponse::from).toList())
                .createAt(voc.getCreateAt())
                .updateAt(voc.getUpdateAt())
                .build();
    }
}
