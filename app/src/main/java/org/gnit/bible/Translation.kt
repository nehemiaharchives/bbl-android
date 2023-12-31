package org.gnit.bible

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import org.gnit.bible.ui.widgets.devanagariSansFontFamily
import org.gnit.bible.ui.widgets.devanagariSerifFontFamily
import org.gnit.bible.ui.widgets.enSansFontFamily
import org.gnit.bible.ui.widgets.enSerifFontFamily
import org.gnit.bible.ui.widgets.jaSansFontFamily
import org.gnit.bible.ui.widgets.jaSerifFontFamily
import org.gnit.bible.ui.widgets.koSansFontFamily
import org.gnit.bible.ui.widgets.koSerifFontFamily
import org.gnit.bible.ui.widgets.scSansFontFamily
import org.gnit.bible.ui.widgets.scSerifFontFamily
import org.gnit.bible.ui.widgets.thaiSansFontFamily
import org.gnit.bible.ui.widgets.thaiSerifFontFamily
import org.gnit.bible.Translation.*

fun Translation.books() = when(this){
    webus, kjv -> Books.ENGLISH_NUMBER_NAME_MAP
    rvr09 -> Books.SPANISH_NUMBER_NAME_MAP
    tb -> Books.PORTUGUESE_NUMBER_NAME_MAP
    delut -> Books.GERMAN_NUMBER_NAME_MAP
    lsg -> Books.FRENCH_NUMBER_NAME_MAP
    sinod -> Books.RUSSIAN_NUMBER_NAME_MAP
    svrj -> Books.DUTCH_NUMBER_NAME_MAP
    rdv24 -> Books.ITALIAN_NUMBER_NAME_MAP
    ubg -> Books.POLISH_NUMBER_NAME_MAP
    ukrk -> Books.UKRAINIAN_NUMBER_NAME_MAP
    sven -> Books.SWEDISH_NUMBER_NAME_MAP
    cunp /*TODO ,cunpsa, cunpte, cunpta */ -> Books.CHINESE_NUMBER_NAME_MAP
    krv -> Books.KOREAN_NUMBER_NAME_MAP
    jc -> Books.JAPANESE_JC_NUMBER_NAME_MAP
    kttv -> Books.VIETNAMESE_NUMBER_NAME_MAP
    abtag -> Books.TAGALOG_NUMBER_NAME_MAP
    itb -> Books.INDONESIAN_NUMBER_NAME_MAP
    th1971 -> Books.THAI_NUMBER_NAME_MAP
    //20 TODO Hindi
    //21 TODO Bengali
    //22 TODO Urdu
    //23 TODO Marathi
    //24 TODO Telugu
    //25 TODO Tamil
    npiulb -> Books.NEPALI_NUMBER_NAME_MAP
    //27 TODO Modern Standard Arabic
    //28 TODO Egyptian Arabic
    //29 TODO Turkish
}

fun Language.serifFontFamily() = when(this){
    Language.en, Language.es, Language.pt, Language.de, Language.fr, Language.ru, Language.nl, Language.it, Language.pl, Language.uk, Language.sv, Language.vi, Language.tl, Language.id -> enSerifFontFamily
    Language.zh -> scSerifFontFamily
    Language.ko -> koSerifFontFamily
    Language.ja -> jaSerifFontFamily
    Language.ne -> devanagariSerifFontFamily
    Language.th -> thaiSerifFontFamily
}

fun Language.sansFontFamily() = when(this){
    Language.en, Language.es, Language.pt, Language.de, Language.fr, Language.ru, Language.nl, Language.it, Language.pl, Language.uk, Language.sv, Language.vi, Language.tl, Language.id -> enSansFontFamily
    Language.zh -> scSansFontFamily
    Language.ko -> koSansFontFamily
    Language.ja -> jaSansFontFamily
    Language.ne -> devanagariSansFontFamily
    Language.th -> thaiSansFontFamily
}

@Composable
fun getAssetTranslations() = LocalContext.current.assets.list("") !!.filterNot { it.equals("images") || it.equals("webkit") }.map { Translation.valueOf(it) }.sortedBy { it.defaultSortOrder }

enum class ReadingMode { SINGLE, BILINGUAL_SIDE, BILINGUAL_UNDER }

fun main(){
    Translation.entries.forEach { println(it.toString()) }
}