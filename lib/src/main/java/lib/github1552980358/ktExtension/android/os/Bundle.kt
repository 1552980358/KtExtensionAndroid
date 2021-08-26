package lib.github1552980358.ktExtension.android.os

import android.os.Bundle

fun bundle(block: Bundle.(Bundle) -> Bundle) = Bundle().apply { block(this) }