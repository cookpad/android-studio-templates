package com.cookpad.astemplates.recipes.screen

enum class Delegation {
    None, VMDelegate, VMDelegateAndViewDelegate
}

fun Delegation.ifNotNone(callback: () -> String): String {
    return if (this != Delegation.None) callback()
    else ""
}

fun Delegation.ifVMDelegateAndViewDelegate(callback: () -> String): String {
    return if (this == Delegation.VMDelegateAndViewDelegate) callback()
    else ""
}

