package com.example.myapplication.data

import com.example.myapplication.R

 object listOfPics{
    val list:List<Picture> =generateSamples()
}

 fun generateSamples():List<Picture>{
    return listOf(Picture(R.drawable.glass,R.string.glass_author,R.string.glass),
        Picture(R.drawable.the_scream,R.string.scream_author,R.string.scream),
        Picture(R.drawable.ninth_wave,R.string.ninth_wave_author,R.string.ninth_wave),
        Picture(R.drawable.sadko,R.string.sadko_author,R.string.sadko)
    )
}