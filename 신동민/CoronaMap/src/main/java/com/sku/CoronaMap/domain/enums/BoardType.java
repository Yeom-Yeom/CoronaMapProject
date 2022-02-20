package com.sku.CoronaMap.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardType {
    notice("공지사항"),
    free("자유게시판");

    private String value;
}
