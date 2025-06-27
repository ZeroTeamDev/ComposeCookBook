package com.base.project.core.design.theme

import androidx.compose.ui.graphics.Color

/**
 * Base Color Palette
 * Using Material 3 color system with custom brand colors
 */

// Primary Brand Colors
val PrimaryLight = Color(0xFF1976D2)
val PrimaryDark = Color(0xFF0D47A1)
val OnPrimaryLight = Color(0xFFFFFFFF)
val OnPrimaryDark = Color(0xFFFFFFFF)

val PrimaryContainerLight = Color(0xFFE3F2FD)
val PrimaryContainerDark = Color(0xFF1A237E)
val OnPrimaryContainerLight = Color(0xFF0D47A1)
val OnPrimaryContainerDark = Color(0xFFE3F2FD)

// Secondary Colors
val SecondaryLight = Color(0xFF757575)
val SecondaryDark = Color(0xFF424242)
val OnSecondaryLight = Color(0xFFFFFFFF)
val OnSecondaryDark = Color(0xFFFFFFFF)

val SecondaryContainerLight = Color(0xFFE0E0E0)
val SecondaryContainerDark = Color(0xFF303030)
val OnSecondaryContainerLight = Color(0xFF424242)
val OnSecondaryContainerDark = Color(0xFFE0E0E0)

// Tertiary Colors
val TertiaryLight = Color(0xFF4CAF50)
val TertiaryDark = Color(0xFF2E7D32)
val OnTertiaryLight = Color(0xFFFFFFFF)
val OnTertiaryDark = Color(0xFFFFFFFF)

val TertiaryContainerLight = Color(0xFFE8F5E8)
val TertiaryContainerDark = Color(0xFF1B5E20)
val OnTertiaryContainerLight = Color(0xFF2E7D32)
val OnTertiaryContainerDark = Color(0xFFE8F5E8)

// Error Colors
val ErrorLight = Color(0xFFE53935)
val ErrorDark = Color(0xFFD32F2F)
val OnErrorLight = Color(0xFFFFFFFF)
val OnErrorDark = Color(0xFFFFFFFF)

val ErrorContainerLight = Color(0xFFFFEBEE)
val ErrorContainerDark = Color(0xFFB71C1C)
val OnErrorContainerLight = Color(0xFFD32F2F)
val OnErrorContainerDark = Color(0xFFFFEBEE)

// Background Colors
val BackgroundLight = Color(0xFFFFFFFF)
val BackgroundDark = Color(0xFF121212)
val OnBackgroundLight = Color(0xFF212121)
val OnBackgroundDark = Color(0xFFE0E0E0)

// Surface Colors
val SurfaceLight = Color(0xFFFAFAFA)
val SurfaceDark = Color(0xFF1E1E1E)
val OnSurfaceLight = Color(0xFF212121)
val OnSurfaceDark = Color(0xFFE0E0E0)

val SurfaceVariantLight = Color(0xFFF5F5F5)
val SurfaceVariantDark = Color(0xFF2C2C2C)
val OnSurfaceVariantLight = Color(0xFF616161)
val OnSurfaceVariantDark = Color(0xFFBDBDBD)

// Outline Colors
val OutlineLight = Color(0xFFE0E0E0)
val OutlineDark = Color(0xFF404040)
val OutlineVariantLight = Color(0xFFF5F5F5)
val OutlineVariantDark = Color(0xFF2C2C2C)

// Inverse Colors
val InverseSurfaceLight = Color(0xFF2C2C2C)
val InverseSurfaceDark = Color(0xFFF5F5F5)
val InverseOnSurfaceLight = Color(0xFFE0E0E0)
val InverseOnSurfaceDark = Color(0xFF2C2C2C)
val InversePrimaryLight = Color(0xFF90CAF9)
val InversePrimaryDark = Color(0xFF1976D2)

// Additional Semantic Colors
val SuccessLight = Color(0xFF4CAF50)
val SuccessDark = Color(0xFF2E7D32)
val WarningLight = Color(0xFFFF9800)
val WarningDark = Color(0xFFE65100)
val InfoLight = Color(0xFF2196F3)
val InfoDark = Color(0xFF0D47A1)

// Custom Brand Colors (can be customized per project)
object BrandColors {
    val Accent = Color(0xFFFF5722)
    val AccentVariant = Color(0xFFBF360C)
    
    // Social Media Colors
    val Facebook = Color(0xFF1877F2)
    val Twitter = Color(0xFF1DA1F2)
    val Instagram = Color(0xFFE4405F)
    val LinkedIn = Color(0xFF0077B5)
    val YouTube = Color(0xFFFF0000)
    val WhatsApp = Color(0xFF25D366)
    
    // Common UI Colors
    val Like = Color(0xFFE91E63)
    val Share = Color(0xFF2196F3)
    val Bookmark = Color(0xFFFF9800)
    val Comment = Color(0xFF4CAF50)
}

// Gradient Colors
object GradientColors {
    val PrimaryGradient = listOf(PrimaryLight, PrimaryDark)
    val SecondaryGradient = listOf(SecondaryLight, SecondaryDark)
    val SuccessGradient = listOf(SuccessLight, SuccessDark)
    val WarningGradient = listOf(WarningLight, WarningDark)
    val ErrorGradient = listOf(ErrorLight, ErrorDark)
    
    // Custom gradients
    val SunsetGradient = listOf(Color(0xFFFF6B6B), Color(0xFFFFE66D))
    val OceanGradient = listOf(Color(0xFF667eea), Color(0xFF764ba2))
    val ForestGradient = listOf(Color(0xFF134E5E), Color(0xFF71B280))
} 