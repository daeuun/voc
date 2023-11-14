package com.teamfresh.voccompensation.compensation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teamfresh.voccompensation.voc.ClaimReasonType;
import com.teamfresh.voccompensation.voc.ClaimType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CompensationResponse {
    private Long id;

    private ClaimType claimType; // VOC 귀책 당사자

    private ClaimReasonType claimReasonCode; // VOC 귀책 내용

    private int charge;

    private String companyName;

    private String deliveryMan;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateAt;

    public static CompensationResponse from(Compensation compensation) {
        return CompensationResponse.builder()
                .id(compensation.getId())
                .claimType(compensation.getVoc().getType())
                .claimReasonCode(compensation.getVoc().getReasonType())
                .charge(compensation.getCharge())
                .companyName(compensation.getDeliveryMan().getCompany())
                .deliveryMan(compensation.getDeliveryMan().getName())
                .createAt(compensation.getCreateAt())
                .updateAt(compensation.getUpdateAt())
                .build();
    }
}
