package com.gitlab.vasilievpavel96.extensions

import java.util.*

operator fun ResourceBundle.get(key: String) = getString(key)