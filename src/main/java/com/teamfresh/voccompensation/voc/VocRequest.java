package com.teamfresh.voccompensation.voc;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VocRequest {
    private String content;

    private Long clientId;

    /** 귀책 대상 */
    private ClaimType type;

    /** 귀책 사유 */
    private ClaimReasonType reasonType;

    public Voc toEntity() {
        return Voc.builder()
                .clientId(clientId)
                .content(content)
                .type(type)
                .reasonType(reasonType)
                .build();
    }
}
