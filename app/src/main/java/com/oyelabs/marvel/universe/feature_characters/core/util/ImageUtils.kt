package com.oyelabs.marvel.universe.feature_characters.core.util

sealed class ImageUtils(){
    class PortraitAspectRatio() :ImageUtils() {
        companion object{
            val PORTRAIT_SMALL =  "portrait_small"
            val PORTRAIT_MEDIUM =  "portrait_medium"
            val PORTRAIT_X_LARGE =  "portrait_xlarge"
            val PORTRAIT_FANTASTIC =  "portrait_fantastic"
            val PORTRAIT_UNCANNY =  "portrait_uncanny"
            val PORTRAIT_INCREDIBLE = "portrait_incredible"
        }
    }
    class SquareAspectRation() :ImageUtils(){
        companion object{

          val STANDARD_SMALL =  "standard_small"
          val STANDARD_MEDIUM =  "standard_medium"
          val STANDARD_LARGE =  "standard_large"
          val STANDARD_X_LARGE =  "standard_xlarge"
          val STANDARD_FANTASTIC =  "standard_fantastic"
          val STANDARD_AMAZING =  "standard_amazing"

        }
    }

}