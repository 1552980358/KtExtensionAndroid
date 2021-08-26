package lib.github1552980358.ktExtension.android.os

import android.os.Bundle

fun bundle(block: Bundle.(Bundle) -> Unit) = Bundle().apply { block(this) }