package com.elhawany.core.utils.ui

import com.elhawany.core.utils.NotifyType

data class ErrorMsg(
    val text: UiText,
    var type: NotifyType = NotifyType.Dialog,
    var cancelable: Boolean = true,
)










