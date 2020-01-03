package com.rkddlsgur983.test.view.memo.entity

enum class MemoCategory(val value: String) {
    PLEASE_CLICK("선택하세요"),
    WORK_TO_DO("할일"),
    IDEA("아이디어"),
    ETC("기타");

    companion object {
        fun fromString(value: String): MemoCategory {
            for (category in values()) {
                if (category.name.equals(value, true)) {
                    return category
                }
            }
            return ETC
        }
    }
}