package com.elhawany.core.utils.ui

import com.elhawany.core.utils.NotifyType

data class UiError(
    val text: UiText,
    var type: NotifyType = NotifyType.SnackBar,
    var cancelable: Boolean = true,
)










