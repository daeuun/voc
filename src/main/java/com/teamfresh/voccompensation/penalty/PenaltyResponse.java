package com.teamfresh.voccompensation.penalty;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PenaltyResponse {
    private Long id;

    private int charge;

    private LocalDateTime readAt;

    private boolean approved;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateAt;

    public static PenaltyResponse from(Penalty penalty) {
        return PenaltyResponse.builder()
                .id(penalty.getId())
                .charge(penalty.getCharge())
                .readAt(penalty.getReadAt())
                .approved(penalty.isApproved())
                .createAt(penalty.getCreateAt())
                .updateAt(penalty.getUpdateAt())
                .build();
    }
}
